package pers.li.io.out;

import java.io.FileInputStream;
import java.io.FileWriter;

/**
 * try关闭流：
 * 1.保证流的物理资源被回收
 * 2.可能还可以将输出流缓冲区的数据flush到物理节点里
 *    (因为在执行close之前会自动flush)
 * ————————————————————————
 * FileOutputStream和FileWriter比对：
 * 若要直接输出字符串内容，则使用Writer会有更好的效果
 */
public class FileOutputStream {

    public static void main(String[] args) {
        try ( //创建字节输入流
              FileInputStream fis=new FileInputStream("D:\\BaiduNetdiskDownload\\1803短信\\untitled\\src\\pers\\li\\io\\FileOutputStream.java");
              //创建字节输出流+++++++++++++++++++++++++++++++++++++++++++++++++
              java.io.FileOutputStream fos =new java.io.FileOutputStream("newFile.txt");){

            byte[] bbuf=new byte[32];
            int hasRead=0;
            while((hasRead=fis.read(bbuf))>0){
                fos.write(bbuf,0,hasRead);
            }
            System.out.println("***********************************************");
            try(FileWriter fileWriter=new FileWriter("poem.txt");
                    ){
                fileWriter.write("李生波");

            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
