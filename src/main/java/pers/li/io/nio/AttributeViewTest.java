package pers.li.io.nio;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Date;
import java.util.List;

/**
 * 文件属性访问
 * create by lishengbo 2018/5/17
 */
public class AttributeViewTest {

    public static void main(String[] args) throws Exception{
        try{
            //获取要操作的文件
            Path path= Paths.get("D:\\BaiduNetdiskDownload\\1803短信\\untitled\\aaa.txt");
            //获取访问基本属性的对象BasicFileAttributeView
            BasicFileAttributeView basicFileAttributeView = Files.getFileAttributeView(path, BasicFileAttributeView.class);
            //获取访问基本属性的对象BasicFileAttribute
            BasicFileAttributes basicFileAttributes = basicFileAttributeView.readAttributes();
            //访问文件的基本属性
            System.out.println("创建时间："+new Date(basicFileAttributes.creationTime().toMillis()));
            System.out.println("最后访问时间："+new Date(basicFileAttributes.lastAccessTime().toMillis()));
            System.out.println("最后修改时间："+new Date(basicFileAttributes.lastModifiedTime().toMillis()));
            System.out.println("文件大小："+basicFileAttributes.size());

            //-----------访问文件从自定义属性
            UserDefinedFileAttributeView userDefinedFileAttributeView=Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
            List<String> list = userDefinedFileAttributeView.list();
            for (String s:list
                 ) {
                ByteBuffer allocate = ByteBuffer.allocate(userDefinedFileAttributeView.size(s));
                userDefinedFileAttributeView.read(s,allocate);
                allocate.flip();
                String value= Charset.defaultCharset().decode(allocate).toString();
                System.out.println(s+"------"+value);
            }
            //添加一个自定义属性
            userDefinedFileAttributeView.write("发行者",Charset.defaultCharset().encode("李生"));
            //获取访问dos属性的对象
            DosFileAttributeView dos=Files.getFileAttributeView(path, DosFileAttributeView.class);
            //设置文件，隐藏只读
            dos.setHidden(true);
            dos.setReadOnly(true);
        }catch(Exception e){
            e.printStackTrace();
        }finally{

        }
        Path path= Paths.get("AttributeViewTest.java");
    }
}
