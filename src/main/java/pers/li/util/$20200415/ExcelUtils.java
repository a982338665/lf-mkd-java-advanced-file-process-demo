package pers.li.util.$20200415;
 
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
import java.io.*;
 
import java.util.ArrayList;
import java.util.List;
 
 
public class ExcelUtils {
 
    public static int totalRows; //sheet中总行数
    public static int totalCells; //每一行总单元格数
 
 
    /**
     * read the Excel .xlsx,.xls
     * @param file jsp中的上传文件
     * @return
     * @throws IOException
     */
    public static List<ArrayList<String>> readExcel(File file) throws IOException {
        if(file==null){
            return null;
        }else{
            String postfix = ExcelTool.getPostfix(file.getName());
            if(!ExcelTool.EMPTY.equals(postfix)){
                if(ExcelTool.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)){
                    return readXls(file);
                }else if(ExcelTool.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)){
                    return readXlsx(file);
                }else{
                    return null;
                }
            }
        }
        return null;
    }
 
 
    /**
     * read the Excel 2010 .xlsx
     * @param file
     * @return
     * @throws IOException
     */
    @SuppressWarnings("deprecation")
    public static List<ArrayList<String>> readXlsx(File file){
        List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        // IO流读取文件
        InputStream input = null;
        XSSFWorkbook wb = null;
        ArrayList<String> rowList = null;
        try {
            input = new FileInputStream(file);
            // 创建文档
            wb = new XSSFWorkbook(input);
            //读取sheet(页)
            for(int numSheet=0;numSheet<wb.getNumberOfSheets();numSheet++){
                XSSFSheet xssfSheet = wb.getSheetAt(numSheet);
                if(xssfSheet == null){
                    continue;
                }
                totalRows = xssfSheet.getLastRowNum();
                //读取Row,从第二行开始
                for(int rowNum = 0;rowNum <= totalRows;rowNum++){
                    XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                    if(xssfRow!=null){
                        rowList = new ArrayList<String>();
                        totalCells = xssfRow.getLastCellNum();
                        //读取列，从第一列开始
                        for(int c=0;c<=totalCells+1;c++){
                            XSSFCell cell = xssfRow.getCell(c);
                            if(cell==null){
                                rowList.add(ExcelTool.EMPTY);
                                continue;
                            }
                            rowList.add(ExcelTool.getXValue(cell).trim());
                        }
                        list.add(rowList);
                    }
                }
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
 
    }
    /**
     * read the Excel 2003-2007 .xls
     * @param file
     * @return
     * @throws IOException
     */
    public static List<ArrayList<String>> readXls(File file){
        List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        // IO流读取文件
        InputStream input = null;
        HSSFWorkbook wb = null;
        ArrayList<String> rowList = null;
        try {
            input = new FileInputStream(file);
            // 创建文档
            wb = new HSSFWorkbook(input);
            //读取sheet(页)
            for(int numSheet=0;numSheet<wb.getNumberOfSheets();numSheet++){
                HSSFSheet hssfSheet = wb.getSheetAt(numSheet);
                if(hssfSheet == null){
                    continue;
                }
                totalRows = hssfSheet.getLastRowNum();
                //读取Row,从第二行开始
                for(int rowNum = 0;rowNum <= totalRows;rowNum++){
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if(hssfRow!=null){
                        rowList = new ArrayList<String>();
                        totalCells = hssfRow.getLastCellNum();
                        //读取列，从第一列开始
                        for(short c=0;c<=totalCells+1;c++){
                            HSSFCell cell = hssfRow.getCell(c);
                            if(cell==null){
                                rowList.add(ExcelTool.EMPTY);
                                continue;
                            }
                            rowList.add(ExcelTool.getHValue(cell).trim());
                        }
                        list.add(rowList);
                    }
                }
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static void main(String[] args) throws Exception {
//        File file=new File("D://33.xls");
//        File file=new File("D://44.xls");
        File file=new File("D://4.xlsx");
        List<ArrayList<String>>  list = ExcelUtils.readExcel(file);
        System.out.println(list.toString());
    }


//    JavaWeb调用方式（只需把上面的方法中File类型改成MultipartFile类型,对应的方法进行修改即可）
//    @RequestMapping(value = "o_import.do",method = RequestMethod.POST)
//    public String importXls(
//            @RequestParam(value = "Filedata", required = false) MultipartFile file){
//        ExcelUtil excelUtil = new ExcelUtil();
//        try {
//            //list为excel数据集合
//            List<ArrayList<String>>  list = ExcelUtils.readExcel(file);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }<br>}
//


 
}
