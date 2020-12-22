package pers.li.io.deal.zhuanhuan;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 转换流：
 * 1.InputStreamReader-将字节输入流转换为字符输入流
 * 2.OutputStreamWriter-将字节输出流转换为字符输出流
 * ————————————————————————
 * 将普通的Reader包装成BufferedReader：
 * 使用其readLine()f方法进行行读取
 * ————————————————————————
 * BufferReader流具有緩衝功能，且能進行行讀取：
 *  ·所以經常用來
 *  把讀取文本內容的輸入流包裝成BufferReader進行文本內容讀取
 */
public class BufferReader {

    public static void main(String[] args) {
        try(
                //將System.in對象轉換為Reader對象
                InputStreamReader in = new InputStreamReader(System.in);
                //將普通reader包裝
                BufferedReader read=new BufferedReader(in);
        ){
            String line=null;
            while ((line=read.readLine())!=null){
                //如果輸入的是exit。則退出
                if(line.equals("exit")){
                    System.exit(1);
                }
                System.out.println("輸入內容為："+line);
            }




        }catch(Exception e){

        }
    }
}
