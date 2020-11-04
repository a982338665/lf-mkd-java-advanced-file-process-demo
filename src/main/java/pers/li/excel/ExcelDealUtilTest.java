package pers.li.excel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pers.li.util.Person;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Excel数据处理测试类
 */
public class ExcelDealUtilTest {

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person("张三", 15, "学生", 24, new Date(), 2.333, false));
        list.add(new Person("李四", 20, "实习生", 23, new Date(), 3.444, true));
        list.add(new Person("王五", 26, "Java工程师", 45, new Date(), 3.444, true));
        list.add(new Person("小明", 30, "主管", 44, new Date(), 3.444, true));

        //属性对应数组：
        String[] field = new String[]{"name", "age", "job", "num", "createTime", "aBoolean", "aDouble"};
        //列名title对应数组
        String[] colume = new String[]{"姓名", "年龄", "职业", "数量", "创建时间", "boolean", "double"};

		//第一种使用方式：多sheet+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        List<Map<String, Object>> listObj = new ArrayList<>();
        //第一个sheet数据**************************************************
        HashMap<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("field", field);
        map.put("titles", colume);
        map.put("sheetName", "属性取值-1");
        //第2个sheet数据**************************************************
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("list", list);
        map2.put("field", null);
        map2.put("titles", colume);
        map2.put("sheetName", "属性取值-2");
        listObj.add(map);
        listObj.add(map2);
        //当写了列对应关系，则按列对应关系处理
        XSSFWorkbook workbook1 = ExcelDealUtil.getWorkbook(listObj);
        if (workbook1 != null) {
            try (
                    OutputStream out = new FileOutputStream("D://3.xlsx");
            ) {
                workbook1.write(out);
                System.out.println("导出完成");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	  //第一种使用方式：多sheet+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	  //第二种使用方式：单sheet+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //单个sheet的数据导出:列值对应关系取 类的属性【按顺序取默认值】
        XSSFWorkbook workbook2 = ExcelDealUtil.getWorkbook(list, null, colume);
        if (workbook2 != null) {
            try (
                    OutputStream out = new FileOutputStream("D://4.xlsx");
            ) {
                workbook2.write(out);
                System.out.println("导出完成");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
     //第二种使用方式：单sheet+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     //第三种使用方式：单sheet+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //单个sheet的数据导出:列值对应关系取 类的属性【列值对应值】
        XSSFWorkbook workbook3 = ExcelDealUtil.getWorkbook(list, field, colume);
        if (workbook3 != null) {
            try (
                    OutputStream out = new FileOutputStream("D://5.xlsx");
            ) {
                workbook3.write(out);
                System.out.println("导出完成");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
      //第三种使用方式：单sheet+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    }

}
