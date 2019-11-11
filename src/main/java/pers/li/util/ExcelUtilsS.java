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
 * Excel导出：多sheet数据导出
 */
public class ExcelUtilsS {

    public static boolean isShow = true;

    public static <T> XSSFWorkbook getWorkbook(List<Map<String, Object>> listObj) {
//        Collection<T> dataSet, String[] params, String[] titles
        // 创建excel工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        for (Map<String, Object> map : listObj) {
            // 定义表头
            String[] title = (String[]) map.get("titles");
            //定义sheet
            String sheetName = (String) map.get("sheetName");
            //数据
            Collection<T> dataSet = (Collection<T>) map.get("list");
            //列对应属性字段
            String[] params = (String[]) map.get("field");
            // 创建工作表sheet
            XSSFSheet sheet = workbook.createSheet(sheetName);
            // 创建第一行
            XSSFRow row = sheet.createRow(0);
            XSSFCell cell = null;
            if (title == null || title.length == 0) {
                System.err.println("titles不能为空！");
                return null;
            }
            if (sheetName == null ) {
                System.err.println("sheetName不能为空！");
                return null;
            }
            // 插入第一行数据的表头
            for (int i = 0; i < title.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(title[i]);
            }
            int idx = 1;
            //遍历数据，并且添加值
            if(dataSet!=null){
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
            }
            // 设置自动列宽
            for (int i = 0; i < title.length; i++) {
                sheet.autoSizeColumn(i);
                sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 16 / 10);
            }
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
        String[] field = new String[]{"name", "age", "job", "num", "createTime", "aBoolean", "aDouble"};
        //列名title对应数组
        String[] colume = new String[]{"姓名", "年龄", "职业", "数量", "创建时间", "boolean", "double"};


        List<Map<String, Object>> listObj = new ArrayList<>();

        //第一个sheet数据**************************************************
        HashMap<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("field", field);
        map.put("titles", colume);
        map.put("sheetName", "属性取值-1");
        //第2个sheet数据**************************************************
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("list", null);
        map2.put("field", null);
        map2.put("titles", colume);
        map2.put("sheetName", "属性取值-2");

        listObj.add(map);
        listObj.add(map2);
        //当写了列对应关系，则按列对应关系处理
        XSSFWorkbook workbook1 = getWorkbook(listObj);
        if (workbook1 != null) {
            try {
                OutputStream out = new FileOutputStream("D://TestOutputExcel3.xlsx");
                workbook1.write(out);
                System.out.println("导出完成");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
