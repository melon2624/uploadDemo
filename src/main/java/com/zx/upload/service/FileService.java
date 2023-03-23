package com.zx.upload.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangxin
 * @date 2023-03-15 17:16
 */

public interface FileService {

    void saveFile(MultipartFile file) throws Exception;

    void download(HttpServletResponse response, String filename);

}
