package pers.li.text;

import java.io.*;

public class TextTest {


    public static void testWrite() {
        try {
            //打开文件
            File wFile = new File("Foo.txt");
            //创建FileWriter
            FileWriter writer = new FileWriter(wFile);
            //使用BufferedWriter加速
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            //写入
            bufferedWriter.write("Hello\n");
            bufferedWriter.write("World");
            //刷新缓冲区
            bufferedWriter.flush();
            //关闭BufferedReader
            bufferedWriter.close();
            //关闭FileWriter
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();// TODO: handle exception
        }
    }

    public static void testReader(){
        try{
            //打开文件
            File myFile=new File("Foo.txt");
            //创建FileReader
            FileReader fileReader=new FileReader(myFile);
            //使用BufferedReader加速
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            //逐行读取文本
            String lineString=null;
            while((lineString=bufferedReader.readLine())!=null) {
                System.out.println(lineString);
            }
            //关闭BudderedReader
            bufferedReader.close();
            //关闭fileReader
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();// TODO: handle exception
        }

    }

    public static void main(String[] args) {
        testWrite();
        testReader();
    }
}
