package com.zx.upload.utils;

import java.io.File;

/**
 * @author zhangxin
 * @date 2023-03-15 17:41
 */
public class FileUtils {


    /**
     * 判断文件路径是否存在 不存在就新建路径
     * @param filePath
     * @return
     */
    public static boolean isExist(String filePath) {
        File file = new File(filePath);
        file.setWritable(true, false); //写入权限
        if (!file.exists()) {
            return file.mkdirs();    //新建文件路径
        }
        return false;
    }

}
