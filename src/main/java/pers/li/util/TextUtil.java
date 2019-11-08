package pers.li.util;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件解析和生成
 */
public class TextUtil {
    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();
        list.add("Hello,World!");
        list.add("Hello,World!");
        list.add("Hello,World!");
        list.add("Hello,World!");
        String filePath = "D:/txt/1/";//最后一个/可加可不加
        String fileName = java.util.UUID.randomUUID().toString().replaceAll("-", "") + ".csv";
        String relFilePath = filePath + File.separator + fileName;
        boolean isSuccess = makeTxt(filePath, fileName, list);
        System.err.println(isSuccess);
        System.err.println("数据读取++++++++++++++++++++++++++++++++++++++++++=");
        ArrayList<String> list1 = readTxt(relFilePath + "ss");
        System.err.println(list1.toString());

        System.err.println("浏览器响应：=======================================");
//        HttpServletResponse response = null;
//        exportTxt(response,"dddddd");

    }


    public static ArrayList<String> readTxt(String path) {
        ArrayList<String> list = new ArrayList<>();
        try {
            //打开文件
            File myFile = new File(path);
            //创建FileReader
            FileReader fileReader = new FileReader(myFile);
            //使用BufferedReader加速
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //逐行读取文本
            String lineString = null;
            while ((lineString = bufferedReader.readLine()) != null) {
//                System.out.println(lineString);
                list.add(lineString);
            }
            //关闭BudderedReader
            bufferedReader.close();
            //关闭fileReader
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.err.println("找不到文件...");
        } catch (Exception e) {
            e.printStackTrace();// TODO: handle exception
        }
        return list;
    }


    /**
     * 导出
     *
     * @param dataList 数据
     * @return
     */
    public static boolean makeTxt(String filePath, String fileName, List<String> dataList) {
        try {
            File pathFile = new File(filePath);
            //如果路径不存在，新建
            if (!pathFile.exists() && !pathFile.isDirectory()) {
                pathFile.mkdirs();
            }
            String relFilePath = filePath + File.separator + fileName;
            System.err.println("relPath:" + relFilePath);
            File file = new File(relFilePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream out = null;
            out = new FileOutputStream(file);
            return exportTxtByOS(out, dataList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 导出
     *
     * @param out      输出流
     * @param dataList 数据
     * @return
     */
    public static boolean exportTxtByOS(FileOutputStream out, List<String> dataList) {
        boolean isSucess = false;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            osw = new OutputStreamWriter(out);
            bw = new BufferedWriter(osw);
            // 循环数据
            for (int i = 0; i < dataList.size(); i++) {
                bw.append(dataList.get(i)).append("\r\n");
            }

            isSucess = true;
        } catch (Exception e) {
            e.printStackTrace();
            isSucess = false;

        } finally {
            if (bw != null) {
                try {
                    bw.close();
                    bw = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (osw != null) {
                try {
                    osw.close();
                    osw = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                    out = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return isSucess;
    }


    /* 导出txt文件
     * @author
     * @param	response
     * @param	text 导出的字符串
     * @return
     */
    public static void exportTxt(HttpServletResponse response, String text) {
        response.setCharacterEncoding("utf-8");
        //设置响应的内容类型
        response.setContentType("text/plain");
        //设置文件的名称和格式
        response.addHeader("Content-Disposition", "attachment;filename="
                + genAttachmentFileName("文件名称", "JSON_FOR_UCC_")//设置名称格式，没有这个中文名称无法显示
                + ".txt");
        BufferedOutputStream buff = null;
        ServletOutputStream outStr = null;
        try {
            outStr = response.getOutputStream();
            buff = new BufferedOutputStream(outStr);
            buff.write(text.getBytes("UTF-8"));
            buff.flush();
            buff.close();
        } catch (Exception e) {
        //LOGGER.error("导出文件文件出错:{}",e);
        } finally {
            try {
                buff.close();
                outStr.close();
            } catch (Exception e) {
                //LOGGER.error("关闭流对象出错 e:{}",e);
            }
        }

    }

    //防止中文文件名显示出错

    public static String genAttachmentFileName(String cnName, String defaultName) {
        try {
            cnName = new String(cnName.getBytes("gb2312"), "ISO8859-1");
        } catch (Exception e) {
            cnName = defaultName;
        }
        return cnName;
    }

}
