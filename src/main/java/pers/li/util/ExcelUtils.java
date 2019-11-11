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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Excel导出：多sheet数据导出
 */
public class ExcelUtils {

    public static boolean isShow = true;

    public static <T> XSSFWorkbook getWorkbook(Collection<T> dataSet, String[] params, String[] titles) {
        // 校验变量和预期输出excel列数是否相同
//        if (params.length != titles.length) {
//            System.out.println("变量参数有误");
//            return null;
//        }
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
        //遍历数据，并且添加值
        for (Object obj : dataSet) {
            // 获取到每一行的属性值数组
            String[] strings = getValues(obj, params);
            XSSFRow nrow = sheet.createRow(idx++);
            XSSFCell ncell = null;
            for (int i = 0; i < strings.length; i++) {
                ncell = nrow.createCell(i);
                ncell.setCellValue(strings[i]);
            }
        }
//
        // 设置自动列宽
        for (int i = 0; i < title.length; i++) {
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 16 / 10);
        }
        return workbook;
    }

    // 根据需要输出的变量名数组获取属性值
    public static String[] getValues(Object object, String[] params) {
        try {
            if (params != null && params.length > 0) {
                String[] values = new String[params.length];
                for (int i = 0; i < params.length; i++) {
                    Field field = object.getClass().getDeclaredField(params[i]);
                    // 设置访问权限为true，可以访问私有变量
                    field.setAccessible(true);
                    // 获取属性
                    convertValues(object, values, i, field);
                }
                isShow = false;
                return values;
            } else {
                Field[] ms = object.getClass().getDeclaredFields();
                String[] values = new String[ms.length];
                for (int i = 0; i < ms.length; i++) {
                    if (isShow) {
                        System.err.println(ms[i].getName());
                    }
                    Field field = object.getClass().getDeclaredField(ms[i].getName());
                    // 设置访问权限为true，可以访问私有变量
                    field.setAccessible(true);
                    // 获取属性
                    convertValues(object, values, i, field);
                }
                isShow = false;
                return values;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void convertValues(Object object, String[] values, int i, Field field) throws IllegalAccessException {
        if (i == 0 && isShow) {
            System.err.println("【type==】指的是，当前需要导出数据的数据类型:【您可能会根据数据类型的不同去调整此处对应关系】");
        }
        Class<?> type = field.getType();
        if (isShow) {
            System.err.println("type==" + type);
        }
        if (type == int.class) {
            values[i] = String.valueOf((int) field.get(object));
        } else if (type == Date.class) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            String format = sdf.format((Date) field.get(object));
            values[i] = format;
        } else {
            values[i] = field.get(object).toString();
        }
    }

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
//        	public Person(String name, int age, String job, Integer num, Date createTime, boolean aBoolean, Double aDouble) {
        list.add(new Person("张三", 15, "学生", 24, new Date(), 2.333, false));
        list.add(new Person("李四", 20, "实习生", 23, new Date(), 3.444, true));
        list.add(new Person("王五", 26, "Java工程师", 45, new Date(), 3.444, true));
        list.add(new Person("小明", 30, "主管", 44, new Date(), 3.444, true));

        //属性对应数组：
        String[] field = new String[]{"name", "age", "job", "num", "createTime",  "aBoolean","aDouble"};
        //列名title对应数组
        String[] colume = new String[]{"姓名", "年龄", "职业", "数量", "创建时间",  "boolean","double"};
        //当写了列对应关系，则按列对应关系处理
        XSSFWorkbook workbook1 = getWorkbook(list, field, colume);
        //当没有列对应关系时，则默认按顺序取字段，即成员变量从上到下一次取数据
        XSSFWorkbook workbook2 = getWorkbook(list, null, colume);
        if (workbook1 != null) {
            try {
                OutputStream out = new FileOutputStream("D://TestOutputExcel.xlsx");
                workbook1.write(out);
                System.out.println("导出完成");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (workbook2 != null) {
            try {
                OutputStream out = new FileOutputStream("D://TestOutputExcel2.xlsx");
                workbook2.write(out);
                System.out.println("导出完成");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
