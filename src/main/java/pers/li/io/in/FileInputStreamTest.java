package pers.li.io.in;

import com.sun.org.apache.xpath.internal.operations.String;

import java.io.FileInputStream;

/**
 * 此文件长度不到1024，所以可一次读完
 * 若调整为较小字节数组，在输出中文时就可能会乱码，因为每个中文字符占两个字节
 * 读到半个的时候就会出现乱码。
 *
 * 必须调用close关闭原因：
 * 1.程序打开的文件io资源不属于内存资源，不会进行GC回收，所以要显式关闭文件io资源
 * 2.java7 改写了所有的io资源类，实现了AutoCloseable接口，因为都可通过自动关闭资源的
 * try语句来关闭io流
 *
 *  3.如要写close则最好写在finally中
 *
 *
 */
public class FileInputStreamTest {

    public static void main(String[] args) throws  Exception{
        //创建字节输入流
        FileInputStream fis=new FileInputStream("FileInputStreamTest.java");
        //创建长度为1204的竹筒
        byte[] bytes = new byte[1024];
        //用来保存实际读取的字节数
        int hashRead=0;
//        int read = fis.read(bytes);
//        hashRead=read;
        //循坏取水
        //将读取的值赋值给hashRead，如果hashRead>0,则循坏，直到小于0位置
        while((hashRead=fis.read(bytes))>0){
//            String string = new String(bytes, 0, hashRead);
            System.out.println();
        }
        fis.close();

    }


}
