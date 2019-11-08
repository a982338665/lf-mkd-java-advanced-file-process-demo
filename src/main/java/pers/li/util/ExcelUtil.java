package pers.li.util;

import org.apache.bcel.generic.BasicType;
import org.apache.bcel.generic.Type;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Excel导出
 */
public class ExcelUtil {

    public static <T> XSSFWorkbook getWorkbook(Collection<T> dataSet, String[] params, String[] titles) {
        // 校验变量和预期输出excel列数是否相同
        if (params.length != titles.length) {
            System.out.println("变量参数有误");
            return null;
        }
        // 存储每一行的数据
        List<String[]> list = new ArrayList<>();
        for (Object obj : dataSet) {
            // 获取到每一行的属性值数组
            list.add(getValues(obj, params));
        }
        return getWorkbook(titles, list);
    }

    public static XSSFWorkbook getWorkbook(String[] titles, List<String[]> list) {
        // 定义表头
        String[] title = titles;
        // 创建excel工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 创建工作表sheet
        XSSFSheet sheet = workbook.createSheet();
        // 创建第一行
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = null;
        // 插入第一行数据的表头
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
        }
        int idx = 1;
        for (String[] strings : list) {
            XSSFRow nrow = sheet.createRow(idx++);
            XSSFCell ncell = null;
            for (int i = 0; i < strings.length; i++) {
                ncell = nrow.createCell(i);
                ncell.setCellValue(strings[i]);
            }
        }
        // 设置自动列宽
        for (int i = 0; i < title.length; i++) {
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 16 / 10);
        }
        return workbook;
    }

    // 根据需要输出的变量名数组获取属性值
    public static String[] getValues(Object object, String[] params) {
        String[] values = new String[params.length];
        try {
            for (int i = 0; i < params.length; i++) {
                Field field = object.getClass().getDeclaredField(params[i]);
                // 设置访问权限为true
                field.setAccessible(true);
                // 获取属性
                // 如果属性有涉及基本变量的做一个转换
                Class<? extends BasicType> aClass = Type.INT.getClass();
                if (field.getType() == aClass)
                    values[i] = String.valueOf((int) field.get(object));
                values[i] = field.get(object).toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return values;
    }

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person("张三", 15, "学生"));
        list.add(new Person("李四", 20, "实习生"));
        list.add(new Person("王五", 26, "Java工程师"));
        list.add(new Person("小明", 30, "主管"));

        XSSFWorkbook workbook = getWorkbook(list, new String[]{"name", "age", "job"}, new String[]{"姓名", "年龄", "职业"});
        if (workbook != null) {
            try {
                OutputStream out = new FileOutputStream("D://TestOutputExcel.xlsx");
                workbook.write(out);
                System.out.println("导出完成");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
