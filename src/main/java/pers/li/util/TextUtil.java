package pers.li.util;

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
        ArrayList<String> list1 = readTxt(relFilePath+"ss");
        System.err.println(list1.toString());
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
}
