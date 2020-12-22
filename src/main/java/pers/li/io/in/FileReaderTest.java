package pers.li.io.in;

import java.io.FileReader;

/**
 * java7以后便提供了try语句关闭流操作，所以不用再finally进行close
 */
public class FileReaderTest {

    public static void main(String[] args) {
        System.out.println("--------------");

        try(  //创建字符输入流
              FileReader fileReader=new FileReader("D:\\BaiduNetdiskDownload\\1803短信\\untitled\\src\\pers\\li\\io\\FileReaderTest.java");
        ){
             //创建一个长度为32的竹筒
            char [] cbuf=new char[32];
            //保存实际读取的字符数
            int hashRead=0;
            while((hashRead=fileReader.read(cbuf))>0){
            String string = new String(cbuf, 0, hashRead);
            System.out.println(string);
            }

        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
