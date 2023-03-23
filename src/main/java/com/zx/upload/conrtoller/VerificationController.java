package com.zx.upload.conrtoller;

import com.google.gson.Gson;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @author zhangxin
 * @date 2023-03-22 9:25
 */
@RestController
public class VerificationController {


    @RequestMapping("/upload")
    public String upload(@RequestParam("fileName") MultipartFile file) throws IOException {

        //定义工作簿
        XSSFWorkbook xssfWorkbook = null;
        xssfWorkbook = new XSSFWorkbook(file.getInputStream());


        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);

        XSSFRow row = sheet.getRow(1);

        XSSFCell cell = row.getCell(0);

        Field[] declaredFields = cell.getClass().getDeclaredFields();




       /* XSSFSheet sheetAt = xssfWorkbook.getSheetAt(1);
        XSSFRow row = sheetAt.getRow(7);
        XSSFCell cell = row.getCell(3);
         XSSFCellStyle cellStyle1 = cell.getCellStyle();
        // cellStyle1.setLocked(false);
        Boolean locked = cellStyle1.getLocked();
        XSSFCell cell1 = row.getCell(4);
        XSSFCell cell2 = row.getCell(5);
        XSSFCell cell3 = row.getCell(6);
         boolean locked1 = cell3.getCellStyle().getLocked();
*/
        System.out.println("zzzzzzzzzzz");
        return null;
    }

    @RequestMapping("/upload1")
    public String upload1(@RequestParam("fileName") MultipartFile file) throws IOException, IllegalAccessException {

        //定义工作簿
        XSSFWorkbook xssfWorkbook = null;
        xssfWorkbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheetAt = xssfWorkbook.getSheetAt(0);
        XSSFRow row = sheetAt.getRow(0);
        XSSFCell cell = row.getCell(0);

        String path = "C:\\Users\\zhangxin\\Desktop\\工作簿3.xlsm";
        File file11 = new File(path);

        XSSFWorkbook xssfWorkbook1 = new XSSFWorkbook(new FileInputStream(file11));
        XSSFSheet sheetAt1 = xssfWorkbook1.getSheetAt(0);
        XSSFRow row1 = sheetAt1.getRow(0);
        XSSFCell cell1 = row1.getCell(0);

         XSSFCellStyle cellStyle = cell1.getCellStyle();
        Gson gson=new Gson();
        Field[] declaredFields = cell.getClass().getDeclaredFields();
        Field[] declaredFields1 = cell1.getClass().getDeclaredFields();

        for (int i=0;i<declaredFields.length;i++) {
            declaredFields[i].setAccessible(true);
            declaredFields1[i].setAccessible(true);
            System.out.println(declaredFields[i].get(cell));
            System.out.println(declaredFields1[i].get(cell1));
            System.out.println("------------");
        }


        XSSFCellStyle cellStyle1 = cell.getCellStyle();
        boolean locked = cellStyle1.getLocked();

        System.out.println("zzzzzzzzzzz");

        return null;
    }


}
