package pers.li.io.duandian_xuchuan;

import java.io.RandomAccessFile;

/**
 * java流体系中功能最丰富的文件内容访问类：
 *  1.支持文件内容读取
 *  2.支持文件内容写入
 *  3.支持随机访问：可跳转到文件的任意位置进行操作
 *  ————————————————————————
 *  因此，如要访问文件部分内容，则使用此类最好
 *  若要进行文件内容追加，也建议使用此类
 *  缺点：
 *      1.只能读写文件，不能读写其他io节点
 *  ————————————————————————
 *  long getFilePointer（）；返回文件记录指针的当前位置
 *  void seek(long pos):将文件记录指针定位到pos位置
 */
public class RandomAccessFileTest2 {

    public static void main(String[] args) {
        try(

                RandomAccessFile raf=new RandomAccessFile("D:\\BaiduNetdiskDownload\\1803短信\\untitled\\out.txt","rw");
                ){
            raf.seek(raf.length());
           raf.write("追加内容".getBytes());

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
