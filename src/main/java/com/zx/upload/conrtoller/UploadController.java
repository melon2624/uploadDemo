package com.zx.upload.conrtoller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jcraft.jsch.*;
import com.zx.upload.config.TemplateSftp;
import com.zx.upload.entity.Upload;
import com.zx.upload.filter.FileFilterImpl;
import com.zx.upload.mapper.UploadMapper;
import com.zx.upload.result.ResultWrapper;
import com.zx.upload.service.FileService;
import com.zx.upload.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;


/**
 * @author zhangxin
 * @date 2023-03-15 16:21
 */
@RestController
public class UploadController {

    private Logger logger = LoggerFactory.getLogger(UploadController.class);

    private static final String NULL_FILE = "";

    @Resource
    FileService fileService;

    @Resource
    TemplateSftp templateSftp;

    @Resource
    UploadMapper uploadMapper;


    @Value("${upload.uploadPathWin}")
    private String uploadPathWin;

    @Value("${upload.uploadPathLinux}")
    private String uploadPathLinux;


    @RequestMapping("/uploadfile")
    public ResultWrapper uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        logger.info("文件上传接口----");
        if (file == null || NULL_FILE.equals(file.getOriginalFilename())) {
            return ResultWrapper.getFailBuilder().data("文件或文件名为空").build();
        }
        fileService.saveFile(file);
        return ResultWrapper.getSuccessBuilder().build();
    }


    @RequestMapping("/sync")
    public ResultWrapper sync() throws FileNotFoundException, SftpException {

        String lastUploadTime = uploadMapper.getLastUploadTime();
        if (StringUtils.isEmpty(lastUploadTime)) {
            lastUploadTime = "19991111125900";
        }

        templateSftp.login();

        File rootDir = new File(uploadPathWin);

        String currentTime = DateUtils.getCurrentTimeString();

        File[] rootDirList = rootDir.listFiles();
        for (File dir : rootDirList) {
            if (dir.isDirectory()) {
                File[] fileList = dir.listFiles(new FileFilterImpl(currentTime, lastUploadTime));
                for (File file : fileList) {
                    String currentDir = file.getParent().substring(file.getParent().lastIndexOf("\\") + 1);
                    templateSftp.upload(new FileInputStream(file), currentDir, file.getName());
                    System.out.println(file.getName());
                    System.out.println(currentDir);
                }
            }
        }
        uploadMapper.insert(new Upload(new Date(), currentTime));
        templateSftp.logout();
        return null;
    }


    @RequestMapping("/test")
    public ResultWrapper test() {
        Upload upload = new Upload();
        upload.setCreateTime(new Date());
        upload.setUploadTime("20230317134011");
        uploadMapper.insert(upload);
        String lastUploadTime = uploadMapper.getLastUploadTime();

        System.out.println(upload.getId());
        return null;
    }


  /*  public static void main(String[] args) throws JSchException, SftpException {
        JSch jsch = new JSch();
        ChannelSftp sftp;
        Session session = jsch.getSession("zhangxin", "127.0.0.1", 22);

        session.setPassword("123456");
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect();
        Channel channel = session.openChannel("sftp");
        channel.connect();
        sftp = (ChannelSftp) channel;
        //  sftp.mkdir("/202304");
        SftpATTRS lstat = sftp.lstat("/202304");

    }*/

    public static void main(String[] args) {

        String filePath = "D:/upload/202303/1678875623225.pdf";

        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMddHHmmss");
        FileTime t = null;
        try {
            t = Files.readAttributes(Paths.get(filePath), BasicFileAttributes.class).creationTime();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String createTime = dateFormat.format(t.toMillis());
        System.out.println(createTime.compareTo("20230315183500"));
        System.out.println("创建时间 ： " + createTime);

    }


}
