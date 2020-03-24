package pers.li.util;

import lombok.Cleanup;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * exportUtil.exportExcel("sheet", "用户表", userList, headers, includeFieldNames, new Integer[]{200}, res.getOutputStream(), null);
 *
 * @program: api
 * @description:
 * 测试用例：
 *     @Resource
 *     private HttpServletResponse res;
 *     @RequestMapping(value = "/exclExport", method = RequestMethod.GET)
 *     @SneakyThrows
 *     public void ExclExport() {
 *         List<User> userList = userService.queryAllByLimit(1, 100000);
 *         String[] headers = { "用户名", "用户密码", "用户编码", "创建人", "创建时间"};
 *         String[] includeFieldNames = { "userName", "userPwd", "userCode", "createUser", "createTime"};
 *         String fileNames = "用户列表.xls";
 *         // 导出订单Excel
 *         ExportUtil exportUtil = new ExportUtil();
 *         //有异常
 *         String s = exportUtil.exportExcel("sheet", "用户表", userList, headers, includeFieldNames);
 *         // 设置文件后缀并编码
 *         exportUtil.exportExcelDataFinal(res, fileNames,s );
 *     }
 *
 *
 **/
public class ExportUtil2 {


    private String sheetName = "sheet";
    private String firstRowTitle = null;
    private String datetimePattern = "yyyy-MM-dd HH:mm:ss";
    private Integer[] widths = new Integer[]{200};

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
     */
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

        /*设置列头样式*/
        sb.append("<Style ss:ID=\"header\" ss:Name=\"header\">\n");//ss:ID=“header”对应下面的Row ss:StyleID=“header”
        sb.append("<Interior ss:Color=\"#cccccc\" ss:Pattern=\"Solid\"/>\n");// 设置背景颜色
        sb.append("<Font ss:FontName=\"微软雅黑\" x:CharSet=\"134\" ss:Bold=\"Bolder\" ss:Size=\"12\"/>\n");//设置字体
        sb.append("</Style>\n");

        /*其它默认样式设置*/
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

    public void exportExcelData(HttpServletResponse response, String fileName, String content) throws IOException {
        response.setHeader("Content-disposition", "attachment;fileName=" + fileName);
        //通知客服文件的MIME类型
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        //导出文件
        OutputStream out = response.getOutputStream();
        out.write(content.getBytes());
        out.flush();
        out.close();
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

    public static void main(String[] args) {
        /**
         * 测试用例：
         */
    }

}
