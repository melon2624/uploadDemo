package com.zx.upload.service;

import com.zx.upload.utils.DateUtils;
import com.zx.upload.utils.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;

/**
 * @author zhangxin
 * @date 2023-03-15 17:25
 */
@Service
public class FileServiceImpl implements FileService {

    @Value("${upload.uploadPathWin}")
    private String uploadPathWin;

    @Value(("${upload.uploadPathWin}"))
    private String uploadPathLinux;

    @Override
    public void saveFile(MultipartFile file) throws Exception {

        String currentMonth = DateUtils.getCurrentMonth();

        String uploadPath = System.getProperty("os.name").toLowerCase().startsWith("win") ? uploadPathWin + currentMonth + "/"
                : uploadPathLinux + currentMonth + "/";

        FileUtils.isExist(uploadPath);

        String uploadFileName = uploadPath + file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(".")) + System.currentTimeMillis() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        FileOutputStream out = new FileOutputStream(uploadFileName);
        out.write(file.getBytes());
        out.flush();
        out.close();

    }

    @Override
    public void download(HttpServletResponse response, String filename) {

    }
}
