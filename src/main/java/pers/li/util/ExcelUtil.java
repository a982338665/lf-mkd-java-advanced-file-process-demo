package pers.li.util;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Excel导出
 */
public class ExcelUtil {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

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
//        HashSet<Object> objects = new HashSet<>();
        try {
            for (int i = 0; i < params.length; i++) {
                Field field = object.getClass().getDeclaredField(params[i]);
                // 设置访问权限为true
                field.setAccessible(true);
                // 获取属性
                Class<?> type = field.getType();
//                objects.add(type);
//                System.err.println("type=" + type);
//                System.out.println(type == String.class);
                if (type == int.class) {
                    values[i] = String.valueOf((int) field.get(object));
//                    System.err.println(int.class == Integer.class);
                } else if (type == Date.class) {
                    String format = sdf.format((Date) field.get(object));
                    values[i] = format;
                } else {
                    values[i] = field.get(object).toString();
                }
            }
//            System.err.println(objects.toString());;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return values;
    }

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
//        	public Person(String name, int age, String job, Integer num, Date createTime, boolean aBoolean, Double aDouble) {
        list.add(new Person("张三", 15, "学生", 24,new Date(), 2.333, false));
        list.add(new Person("李四", 20, "实习生", 23,new Date(), 3.444, true));
        list.add(new Person("王五", 26, "Java工程师", 45,new Date(), 3.444, true));
        list.add(new Person("小明", 30, "主管",44, new Date(), 3.444, true));

        XSSFWorkbook workbook = getWorkbook(list, new String[]{"name", "age", "job","num", "createTime", "aDouble", "aBoolean"}, new String[]{"姓名", "年龄", "职业", "数量","创建时间", "double", "boolean"});
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
