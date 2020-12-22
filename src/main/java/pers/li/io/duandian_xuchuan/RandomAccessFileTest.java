package pers.li.io.duandian_xuchuan;

import java.io.RandomAccessFile;

/**
 * java流体系中功能最丰富的文件内容访问类：
 *  1.支持文件内容读取
 *  2.支持文件内容写入
 *  3.支持随机访问：可跳转到文件的任意位置进行操作
 *  注意：不能向文件指定位置插入内容，因为新内容会覆盖原内容
 *  若要实现随机插入，则必须要把插入后的文件内容读入缓存区，等把需要插入的内容写完后
 *  在将缓存区内容进行追加----实现位置RandomAccessFileTest3
 *  ————————————————————————
 *  因此，如要访问文件部分内容，则使用此类最好
 *  若要进行文件内容追加，也建议使用此类
 *  缺点：
 *      1.只能读写文件，不能读写其他io节点
 *  ————————————————————————
 *  long getFilePointer（）；返回文件记录指针的当前位置
 *  void seek(long pos):将文件记录指针定位到pos位置
 */
public class RandomAccessFileTest {

    public static void main(String[] args) {
        try(
                /**
                 * 构造中第二个参数：
                 * r   以只读方式打开文件
                 * rw  读写方式打开文件，不存在则创建
                 * rws 读写方式打开文件，对文件内容或元数据的每个更新都同步写入到底层存储设备
                 * red 读写方式打开文件,对文件内容的每个更新都同步写入到底层存储设备
                 */
                RandomAccessFile raf=new RandomAccessFile("D:\\BaiduNetdiskDownload\\1803短信\\untitled\\src\\pers\\li\\io\\deal\\RandomAccessFileTest.java","r");
                ){
            //获取文件对象指针-初始位置为0
            System.err.println("初始位置："+raf.getFilePointer());
            //移动指针位置
            raf.seek(300);
            byte[] b=new byte[1024];
            //用于保存实际读取的字节数
            int has=0;
            //循环取水
            while ((has=raf.read(b))>0){
                System.out.println(new String(b,0,has));
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
