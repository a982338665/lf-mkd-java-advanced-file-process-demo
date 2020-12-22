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
public class FileWriterTest {

    public static void main(String[] args) {

        System.out.println("***********************************************");
        try (FileWriter fileWriter = new FileWriter("poem.txt");
        ) {
            fileWriter.write("李生波");

        }catch (Exception e){

        }


    }


}
