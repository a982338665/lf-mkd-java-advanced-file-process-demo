package pers.li.io.duandian_xuchuan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

/**
 * 多线程的网络下载工具就可以通过此种方式来实现：
 * 原理：会创建两个文件
 * 1.一个与被下载文件大小相同的空文件---A
 * 2.一个是记录文件指针的位置文件-------B
 * --------------------------------
 * 下载工具：
 * 1.多线程启动输入流读取网络数据‘
 * 2.使用此类将读到的数据写入之前的空文件A中，
 * 3.将指针位置记录在----位置文件B中
 * ----
 * 4.网络断开后再次下载时，根据文件B中的指针位置继续向下写数据
 * ----
 * 当具备多线程和网络知识后，会详细说明类似于FlashGet的多线程断点传输工具
 */
public class RandomAccessFileTest3 {

    public static void main(String[] args) throws Exception {
        insert("D:\\BaiduNetdiskDownload\\1803短信\\untitled\\out.txt",25,"插入指定内容测试");
    }


    /**
     * 向文件指定位置插入内容
     * @param name    文件名
     * @param pos     位置
     * @param content 内容
     * @throws Exception
     */
    public static void insert(String name,long pos,String content) throws Exception{
        File tmp=File.createTempFile("tmp",null) ;
        //在JVM进程退出的时候自动删除文件,通常用在临时文件的删除.
        tmp.deleteOnExit();
        try(
            RandomAccessFile raf=new RandomAccessFile(name,"rw");
            //使用临时文件保存插入点后的数据
            FileOutputStream out=new FileOutputStream(tmp);
            FileInputStream in=new FileInputStream(tmp);
        ){
            raf.seek(pos);
            //将插入点后的文件内容读入临时文件保存++++++++++++++++++++++++++++
            byte[] buff=new byte[64];
            int has=0;
            while ((has=raf.read(buff))>0){
                out.write(buff,0,has);
            }
            //插入内容——————————————————————————
            //指针重新定位到插入点
            raf.seek(pos);
            //追加需要插入内容
            raf.write(content.getBytes());
            //追加临时文件内容
            while ((has=in.read(buff))>0){
                raf.write(buff,0,has);
            }




        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
