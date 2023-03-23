package com.zx.upload.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhangxin
 * @date 2023-03-15 17:34
 */
public class DateUtils {


    public static final String DATE_FORMAT = "yyyyMM";

    public static final  String  DATE_FORMAT1="yyyyMMddHHmmss";

    public static String getCurrentMonth() {

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Date date = new Date();
        return sdf.format(date);
    }

    public  static  String getCurrentTimeString(){
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT1);
        Date date = new Date();
        return sdf.format(date);
    }





    public static void main(String[] args) {

        System.out.println(getCurrentMonth());

    }


}
