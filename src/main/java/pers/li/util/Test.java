package pers.li.util;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jingniu.entity.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {

    //10 0000 2028
    //104 0000 10721
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        User user = new User();
        for (int i = 0; i < 10000; i++) {
            user.setId(1);
            user.setUserName("li");
            user.setUserPwd("pwd");
            user.setUserCode("code");
            user.setCreateUser("people");
            user.setCreateTime(new Date());
            userList.add(user);
        }
        // 设置文件后缀并编码
        String fileName = null;
        long start = System.currentTimeMillis();
        String[] headers = { "用户名", "用户密码", "用户编码", "创建人", "创建时间"};
        String[] includeFieldNames = { "userName", "userPwd", "userCode", "createUser", "createTime"};
        // 设置文件后缀并编码
        try {
            fileName = new String("用户列表.xls".getBytes("UTF-8"), "iso8859-1");
            // 导出订单Excel
            ExportUtil exportUtil = new ExportUtil();
            OutputStream out = new FileOutputStream("./TestOutputExcel.xlsx");
            exportUtil.exportExcel("sheet", "用户表", userList, headers, includeFieldNames, new Integer[]{200},  out, null);
            long end = System.currentTimeMillis();
            System.out.println("导出完成:"+(end-start));
            XSSFWorkbook workbook = ExcelUtil.getWorkbook(userList, includeFieldNames,headers);
            if (workbook != null) {
                try {
                    OutputStream out1 = new FileOutputStream("./TestOutputExce1l.xlsx");
                    workbook.write(out1);
                    long end2 = System.currentTimeMillis();
                    System.out.println("导出完成:"+(end2-end));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
