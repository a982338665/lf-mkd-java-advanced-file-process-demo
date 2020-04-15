/*
package pers.li.util;

import lombok.Cleanup;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.common.anno.KNFieldDesc;
import org.jingniu.vo.export.PurchaseApplyVoExport;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

*/
/**
 * exportUtil.exportExcel("sheet", "用户表", userList, headers, includeFieldNames, new Integer[]{200}, res.getOutputStream(), null);
 *
 * @program: api
 * @description:
 * @author: GJQ
 * @create: 2020-03-23 20:46
 *
 *  @Override
 *     @SneakyThrows
 *     public void exportData(PurchaseApplyVo purchaseApplyVo, HttpServletResponse res) {
 *         List<PurchaseApplyVoExport> list = exportDao.exportDataPuschaseApply(purchaseApplyVo);
 *         ExportUtil exportUtil = new ExportUtil();
 *         String s = exportUtil.exportExcel(list, PurchaseApplyVoExport.class);
 *         exportUtil.exportExcelDataFinal(res, "导出请购申请.xls", s);
 *     }
 **//*

public class ExportUtil3 {


    private String sheetName = "sheet";
    private String firstRowTitle = null;
    private String datetimePattern = "yyyy-MM-dd HH:mm:ss";
    private Integer[] widths = new Integer[]{200};
    private String[] head = null;
    private String[] colume = null;

    */
/**
     * @param sheetName         sheel名称
     * @param firstRowTitle     头名称（可空）
     * @param list              内容对象数组
     * @param headers           头数组
     * @param includeFieldNames 字段顺序数组
     * @param widths            列宽数组
     * @param datetimePattern   时间输入格式默认 yyyy-MM-dd HH:mm:ss
     * @param <T>
     * @throws Exception
     *//*

    public <T> String exportExcel(String sheetName, String firstRowTitle, List<T> list, String[] headers, String[] includeFieldNames, Integer[] widths,
                                  String datetimePattern) throws Exception {
        StringBuffer sb = makeStyle();
        makeTable(sheetName, firstRowTitle, list, headers, includeFieldNames, widths, datetimePattern, sb);
        return sb.toString();
    }

    public <T> String exportExcel(String sheetName, String firstRowTitle, List<T> list, String[] headers, String[] includeFieldNames) throws Exception {
        StringBuffer sb = makeStyle();
        makeTable(sheetName, firstRowTitle, list, headers, includeFieldNames, widths, datetimePattern, sb);
        return sb.toString();
    }

    public <T> String exportExcel(String firstRowTitle, List<T> list, String[] headers, String[] includeFieldNames, Integer[] widths,
                                  String datetimePattern) throws Exception {
        StringBuffer sb = makeStyle();
        makeTable(sheetName, firstRowTitle, list, headers, includeFieldNames, widths, datetimePattern, sb);
        return sb.toString();
    }

    public <T> String exportExcel(List<T> list, String[] headers, String[] includeFieldNames, Integer[] widths,
                                  String datetimePattern) throws Exception {
        StringBuffer sb = makeStyle();
        makeTable(sheetName, firstRowTitle, list, headers, includeFieldNames, widths, datetimePattern, sb);
        return sb.toString();
    }

    public <T> String exportExcel(List<T> list, String[] headers, String[] includeFieldNames, Integer[] widths
    ) throws Exception {
        StringBuffer sb = makeStyle();
        makeTable(sheetName, firstRowTitle, list, headers, includeFieldNames, widths, datetimePattern, sb);
        return sb.toString();
    }

    public <T> String exportExcel(List<T> list, String[] headers, String[] includeFieldNames
    ) throws Exception {
        StringBuffer sb = makeStyle();
        makeTable(sheetName, firstRowTitle, list, headers, includeFieldNames, widths, datetimePattern, sb);
        return sb.toString();
    }
    public <T> String exportExcel(List<T> list,Class c
    ) throws Exception {
        setHeadAndColume(c);
        StringBuffer sb = makeStyle();
        makeTable(sheetName, firstRowTitle, list, this.head,this.colume, widths, datetimePattern, sb);
        return sb.toString();
    }

    private <T> void makeTable(String sheetName, String firstRowTitle, List<T> list, String[] headers, String[] includeFieldNames, Integer[] widths, String datetimePattern, StringBuffer sb) throws IllegalAccessException, InvocationTargetException {
        // 生成表格
        int headersLength = headers.length;
        // 默认输出格式
        if (StringUtils.isBlank(datetimePattern)) {
            datetimePattern = "yyyy-MM-dd HH:mm:ss";
        }
        sb.append("<Worksheet ss:Name=\"" + sheetName + "\">");
        sb.append("\n");
        sb.append("<Table ss:ExpandedColumnCount=\"" + headersLength
                + "\" ss:ExpandedRowCount=\"1000000\" x:FullColumns=\"1\" x:FullRows=\"1\">");
        sb.append("\n");

        if (widths.length > 0) {
            if (widths.length > 1) {
                for (int i = 0; i < headersLength; i++) {
                    sb.append("<Column ss:AutoFitWidth=\"0\" ss:Width=\"" + widths[i] + "\"/>");
                }
            } else {
                for (int i = 0; i < headersLength; i++) {
                    sb.append("<Column ss:AutoFitWidth=\"0\" ss:Width=\"" + widths[0] + "\"/>");
                }
            }
        }


        // 输出第一行的标题
        if (!StringUtils.isBlank(firstRowTitle)) {
            //ss:StyleID可以加row或者Cell，加在Row，整行（包括空的Cell）都有，加上Cell，只有Cell才有。
            sb.append("<Row  ss:Height=\"30\">");
            sb.append("<Cell ss:StyleID=\"header\" ss:MergeAcross=\"" + (headersLength - 1) + "\"><Data ss:Type=\"String\">" + firstRowTitle + "</Data></Cell>");
            sb.append("</Row>");
        }

        // 输出列头
        sb.append("<Row>");
        for (int i = 0; i < headersLength; i++) {
            sb.append("<Cell ss:StyleID=\"header\"><Data ss:Type=\"String\">" + headers[i] + "</Data></Cell>");
        }
        sb.append("</Row>");

        // 构建表体数据
        for (int j = 0; j < list.size(); j++) {

            sb.append("<Row>");

            T t = (T) list.get(j);

            for (int i = 0; i < includeFieldNames.length; i++) {

                // 获取属性名称
                String fieldName = includeFieldNames[i];

                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                // 获取class对象
                Class tCls = t.getClass();

                // 获取属性值
                Object value = null;

                try {
                    // 获取class方法
                    Method getMethod = tCls.getMethod(getMethodName, new Class[]{});

                    // 获取属性值
                    value = getMethod.invoke(t, new Object[]{});

                } catch (NoSuchMethodException e) {
                    // 继续循环
                    continue;
                }

                // 判断值的类型后进行强制类型转换
                String textValue = "";
                if (value instanceof Integer) {
                    // int value = ((Integer) value).intValue();
                    textValue = value.toString();
                } else if (value instanceof String) {
                    // String s = (String) value;
                    textValue = value.toString();
                } else if (value instanceof Double) {
                    // double d = ((Double) value).doubleValue();
                    textValue = String.format("%.2f", value);
                } else if (value instanceof Float) {
                    // float f = ((Float) value).floatValue();
                    textValue = value.toString();
                } else if (value instanceof Long) {
                    // long l = ((Long) value).longValue();
                    textValue = value.toString();
                } else if (value instanceof Boolean) {
                    // boolean b = ((Boolean) value).booleanValue();
                    textValue = value.toString();
                } else if (value instanceof Date) {
                    Date date = (Date) value;
                    SimpleDateFormat sdf = new SimpleDateFormat(datetimePattern);
                    textValue = sdf.format(date);
                } else if ((value instanceof BigDecimal)) {
                    textValue = value.toString();
                } else {
                    if (value != null) {
                        continue;
                    }
                }

                sb.append("<Cell><Data ss:Type=\"String\">");

                // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                if (StringUtils.isNotBlank(textValue)) {

                    Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                    Matcher matcher = p.matcher(textValue);
                    if (matcher.matches()) {
                        // 是数字当作double处理
                        sb.append(Double.parseDouble(textValue));
                    } else {
                        sb.append(textValue);
                    }

                }

                sb.append("</Data></Cell>");

            }

            sb.append("</Row>");
            sb.append("\n");

        }

        sb.append("</Table>");
        sb.append("<WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">");
        sb.append("\n");
        sb.append("<ProtectObjects>False</ProtectObjects>");
        sb.append("\n");
        sb.append("<ProtectScenarios>False</ProtectScenarios>");
        sb.append("\n");
        sb.append("</WorksheetOptions>");
        sb.append("\n");
        sb.append("</Worksheet>");
        sb.append("</Workbook>");
        sb.append("\n");
    }

    private StringBuffer makeStyle() {
        // 创建一个excel应用文件
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\"?>");
        sb.append("\n");
        sb.append("<?mso-application progid=\"Excel.Sheet\"?>");
        sb.append("\n");
        sb.append("<Workbook xmlns=\"urn:schemas-microsoft-com:office:spreadsheet\"");
        sb.append("\n");
        sb.append(" xmlns:o=\"urn:schemas-microsoft-com:office:office\"");
        sb.append("\n");
        sb.append(" xmlns:x=\"urn:schemas-microsoft-com:office:excel\"");
        sb.append("\n");
        sb.append(" xmlns:ss=\"urn:schemas-microsoft-com:office:spreadsheet\"");
        sb.append("\n");
        sb.append(" xmlns:html=\"http://www.w3.org/TR/REC-html40\">");
        sb.append("\n");

        sb.append("<Styles>\n");

        */
/*设置列头样式*//*

        sb.append("<Style ss:ID=\"header\" ss:Name=\"header\">\n");//ss:ID=“header”对应下面的Row ss:StyleID=“header”
        sb.append("<Interior ss:Color=\"#cccccc\" ss:Pattern=\"Solid\"/>\n");// 设置背景颜色
        sb.append("<Font ss:FontName=\"微软雅黑\" x:CharSet=\"134\" ss:Bold=\"Bolder\" ss:Size=\"12\"/>\n");//设置字体
        sb.append("</Style>\n");

        */
/*其它默认样式设置*//*

        sb.append("<Style ss:ID=\"Default\" ss:Name=\"Normal\">\n");
        //sb.append("<Alignment ss:Vertical=\"Center\"/>\n");
        sb.append("<Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\" ss:WrapText=\"1\"/>\n");// 左中右设置，一个是水平，一个是垂直
        sb.append("<Borders>\n");
        sb.append("<Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Color=\"#666\" ss:Weight=\"1\"/>\n");//左边框设置
        sb.append("<Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Color=\"#666\" ss:Weight=\"1\"/>\n");//右边框设置
        sb.append("<Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Color=\"#666\" ss:Weight=\"1\"/>\n");//下边框设置
        sb.append("<Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Color=\"#666\" ss:Weight=\"1\"/>\n");//上边框设置
        sb.append("</Borders>\n");
        sb.append("<Font ss:FontName=\"宋体\" x:CharSet=\"134\" ss:Size=\"12\"/>\n");
        sb.append("<Interior/>\n");
        sb.append("<NumberFormat/>\n");
        sb.append("<Protection/>\n");
        sb.append("</Style>\n");

        sb.append("</Styles>\n");
        return sb;
    }

    public void exportExcelDataFinal(HttpServletResponse res, String fileName, String content) throws IOException {
        String file = new String(fileName.getBytes("UTF-8"), "iso8859-1");
        // 设置响应的编码方式;
        res.setCharacterEncoding("gb2312");
        res.setHeader("Content-disposition", "attachment; filename=" + file);
        res.setContentType("application/msexcel;charset=UTF-8");
        //导出文件
        @Cleanup OutputStream out = res.getOutputStream();
        out.write(content.getBytes());
        out.flush();
        out.close();
    }

    @SneakyThrows
    public Map<String, String[]> setHeadAndColume(Class t) {
        Map<String, String[]> map = new HashMap<>();
        //获取本类所有声明的字段
        Field[] fs2 = t.getDeclaredFields();
        List<Field> list = new ArrayList<>();
        //过滤要排除的字段：没有注解的，序列化id
        for (int i = 0; i < fs2.length; i++) {
            Field f = fs2[i];
            f.setAccessible(true);//可以使得private临时变成public，变成可访问的
            //获取属性名
            KNFieldDesc annotationff = f.getAnnotation(KNFieldDesc.class);
            if (annotationff != null) {
                list.add(f);
            }
        }
        String[] head = new String[list.size()];
        String[] colume = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Field f = list.get(i);
            f.setAccessible(true);//可以使得private临时变成public，变成可访问的
            //获取属性名
            KNFieldDesc annotationff = f.getAnnotation(KNFieldDesc.class);
            if (annotationff != null) {
                String valueff = annotationff.value();
                head[i] = valueff;
                colume[i] = f.getName();
            }
        }
        map.put("head", head);
        map.put("colume", colume);
        this.head = head;
        this.colume = colume;
        return map;
    }

    public static void main(String[] args) {
        Map<String, String[]> headAndColume = new ExportUtil().setHeadAndColume(PurchaseApplyVoExport.class);
        System.err.println(Arrays.toString(headAndColume.get("head")));
        System.err.println(Arrays.toString(headAndColume.get("colume")));
    }

}
*/
