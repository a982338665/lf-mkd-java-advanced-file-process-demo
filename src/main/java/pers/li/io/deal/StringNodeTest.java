package pers.li.io.deal;

import java.io.StringReader;
import java.io.StringWriter;

/**
 * 创建对象时传入的是字符串节点而不是文件节点，由于String是不可变的字符串对象
 * 所以StringWriter使用StringBuffer作为输出节点
 */
public class StringNodeTest {
    public static void main(String[] args) {
        String src="授课计划的结婚卡老师发暗杀和拉开啊啊了啥看法好看、\n"
                +"盛开的局开始大V是考虑到\n"
                +"盛开的局开始大V是考虑到\n";
        char [] buf=new char[32];
        int has=0;
        try(
                //传入的是字符串节点而不是文件节点
                StringReader sr=new StringReader(src);
                ){
            while ((has=sr.read(buf))>0){
                System.out.println(new String(buf,0,has));
            }

        }   catch (Exception e){

        }
        try(
                StringWriter sr=new StringWriter();
                ){
           sr.write("有一个顾念");
           System.out.println(sr.toString());
        }   catch (Exception e){

        }
    }
}
