package com.zx.upload.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangxin
 * @date 2023-03-16 11:38
 */
@Configuration
@ConditionalOnClass(TemplateSftp.class)
public class SftpConfiguration {

    @Autowired
    private SftpProperties sftpProperties;

    @Bean
    public TemplateSftp sftp() {
        TemplateSftp sftp = new TemplateSftp();
        sftp.setSftpHost(sftpProperties.getHost());
        sftp.setSftpUsername(sftpProperties.getUsername());
        sftp.setSftpPassword(sftpProperties.getPassword());
        sftp.setSftpPort(sftpProperties.getPort());
        sftp.setSftpDirectory(sftpProperties.getDirectory());
        return sftp;

    }

}
