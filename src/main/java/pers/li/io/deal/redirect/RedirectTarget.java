package pers.li.io.deal.redirect;

import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * 重定向標準輸入輸出：
 *
 */
public class RedirectTarget {

    public static void main(String[] args) {
        try (
                //创建输出包装流
                PrintStream out= new PrintStream(new FileOutputStream("out.txt"));
                ){
            //标准输出重定向到out输出流
            System.setOut(out);
            //标准输出一个字符串
            System.out.println("hello,to out.txt");

            System.setErr(out);
            System.out.println("fuck red");


        }catch (Exception e){

        }
    }
}
