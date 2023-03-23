package com.zx.upload.task;

import com.jcraft.jsch.SftpException;
import com.zx.upload.config.TemplateSftp;
import com.zx.upload.entity.Upload;
import com.zx.upload.filter.FileFilterImpl;
import com.zx.upload.mapper.UploadMapper;
import com.zx.upload.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

/**
 * @author zhangxin
 * @date 2023-03-15 18:02
 */
@Component
public class SyncUploadTask {


    private static final String NULL_FILE = "";

    @Resource
    TemplateSftp templateSftp;

    @Resource
    UploadMapper uploadMapper;


    @Value("${upload.uploadPathWin}")
    private String uploadPathWin;

    @Value("${upload.uploadPathLinux}")
    private String uploadPathLinux;


    @Scheduled(cron = "0 0 0/1 * *")
    public void task() {

        System.out.println(Thread.currentThread().getName() + "zhangxinhhh");

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
                    try {
                        templateSftp.upload(new FileInputStream(file), currentDir, file.getName());
                    } catch (SftpException e) {
                        e.printStackTrace();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println(file.getName());
                    System.out.println(currentDir);
                }
            }
        }
        uploadMapper.insert(new Upload(new Date(), currentTime));
        templateSftp.logout();

    }


}
