package com.zx.upload.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zx.upload.entity.Upload;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangxin
 * @since 2023-03-17
 */
@Mapper
public interface UploadMapper extends BaseMapper<Upload> {

    public  String getLastUploadTime();


}
