package com.zx.upload.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangxin
 * @since 2023-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Upload implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("create_time")
    private Date createTime;

    @TableField("upload_time")
    private String uploadTime;

    public Upload(Date createTime, String uploadTime) {
        this.createTime = createTime;
        this.uploadTime = uploadTime;
    }

    public  Upload(){

    }
}
