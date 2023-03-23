package com.zx.upload.filter;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;

/**
 * @author zhangxin
 * @date 2023-03-16 14:19
 */
public class FileFilterImpl implements FileFilter {

    public String currentTime;

    public String lastUploadTime;

    public FileFilterImpl(String currentTime, String lastUploadTime) {
        this.currentTime = currentTime;
        this.lastUploadTime = lastUploadTime;
    }

    @Override
    public boolean accept(File pathname) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMddHHmmss");
        FileTime t = null;
        try {
            t = Files.readAttributes(Paths.get(pathname.getAbsolutePath()), BasicFileAttributes.class).creationTime();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String createTime = dateFormat.format(t.toMillis());
        System.out.println("创建时间 ： " + createTime);
        return createTime.compareTo(currentTime) <= 0 && createTime.compareTo(lastUploadTime)>0;

    }
}
