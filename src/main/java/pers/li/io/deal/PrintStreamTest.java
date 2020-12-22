package pers.li.io.deal;

import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * 使用PrintStream包装OutputStream
 * 处理流：
 *      1.简化编程
 *      2.使用处理流来包装节点流，
 *          程序通过处理流进行输入输出，
 *          通过节点流与底层的I/O设备，文件交互
 *      3.所有节点流都是直接以物理io节点作为构造器参数的
 *      4.处理流的执行效率更高
 * 我们平时使用的System.out的类型就是处理流PrintStream
 */
public class PrintStreamTest {

    public static void main(String[] args) {
        try(
                //定义节点输出流
                FileOutputStream fileOutputStream=new FileOutputStream("test.txt");
                //包装输出流
                PrintStream printStream=new PrintStream(fileOutputStream);
                ){
                //使用包装流进行输入输出
                printStream.println("普通字符串");
                printStream.println(new PrintStreamTest());


        }catch (Exception e){

        }
    }
}
