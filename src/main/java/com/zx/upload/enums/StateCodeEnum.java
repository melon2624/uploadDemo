package com.zx.upload.enums;

/**
 * @author zhangxin
 * @date 2023-03-15 17:09
 */
public enum StateCodeEnum {


    FAIL(500,"上传文件失败"),

    SUCCESS(200,"上传成功");


    private  int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    StateCodeEnum(int code , String msg){
        this.code = code;
        this.msg = msg;
    }


}
