package pers.li.io.nio;

import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Files工具类的使用
 */
public class FilesTest {

    public static void main(String[] args) throws  Exception{
        //文件复制---指的是上级目录的FileTest
        Files.copy(Paths.get("FileTest.java"),new FileOutputStream("a.txt"));
        //是否为隐藏文件
        Files.isHidden(Paths.get("FileTest.java"));
        //一次性读取此文件的所有行
        List<String> lines=Files.readAllLines(Paths.get("FileTest.java"),
                Charset.forName("utf8"));
        System.out.println(lines);
        //判断指定文件的大小
        Files.size(Paths.get("FileTest.java"));
        //将多个字符串内容写入文件
        List<String> list=new ArrayList<>();
        list.add("ppd打飞地方");
        list.add("hhh哈哈哈");
        Files.write(Paths.get("aaa.txt"),list,Charset.forName("utf8"));

        FileStore f=Files.getFileStore(Paths.get("C:"));
        System.out.println("c盘可用空间："+f.getUsableSpace()/1024/1024/1024);
        System.out.println("c盘总共空间："+f.getTotalSpace());


    }
}
