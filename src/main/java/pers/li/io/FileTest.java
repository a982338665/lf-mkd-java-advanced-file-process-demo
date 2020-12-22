package pers.li.io;

import java.io.File;
import java.io.FilenameFilter;

public class FileTest {

    public static void main(String[] args) throws Exception{

        File file=new File(".");
        System.out.println("文件名称："+file.getName());
        System.out.println("获取相对路径的父路径：错误"+file.getParent());
        System.out.println("获取绝对路径："+file.getAbsolutePath());
        System.out.println("获取绝对路径："+file.getAbsoluteFile());
        System.out.println("获取上一级路径："+file.getAbsoluteFile().getParent());

        //在file路径下创建临时文件
        File tem =File.createTempFile("aaa",".txt",file);
        //指定jvm退出时删除该文件
        tem.deleteOnExit();

        File newFile=new File(System.currentTimeMillis()+"");
        System.out.println("newFile文件是否存在："+newFile.exists());
        newFile.createNewFile();
        boolean rename = newFile.renameTo(new File("rename"));
        System.out.println("重命名是否成功："+rename);
        System.out.println("createNewFile后newFile文件是否存在："+newFile.exists());
        boolean mkdir = newFile.mkdir();
        System.out.println("createNewFile后mkdir是否成功："+mkdir);
        String [] list=file.list();
        for (String filename:list
             ) {
            System.out.println("打印出当前工程下所有的filename："+filename);
        }
        System.out.println("以上打印不包含使用deleteOnExit方法的文件，因为JVM退出被删除");
        File [] roots=File.listRoots();
        //列出所有磁盘根路径
        for (File f:roots
             ) {
            System.out.println(f);//C:\
        }
        newFile.delete();

        System.out.println("--------文件过滤器---------------------------------");
        String [] nameList=file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                boolean b = name.endsWith(".java");
//                boolean directory = new File(name).isDirectory();
                return b;

            }
        } );
        for (String name:
            nameList ) {
            System.out.println(name);
        }
        System.out.println("过滤结束-------------------------------------------");
        /**
         * 节点流和处理流：
         * 节点流：直接和输入输出的数据节点进行连接，低级流
         * 处理流也是包装流，程序进行输入输出时不会接入数据节点，会通过处理流来访问不同的数据源
         * 通过使用处理流，java程序就不用理会输入输出节点是磁盘，网络还是其他，只要将这些节点流
         * 包装成处理流，就可以使用相同的输入输出代码来读写不同的输入输出设备数据
         * 处理流的好处：
         *  1.性能的提高：增加缓冲方式提高输入输出效率
         *  2.操作的便捷：处理流可能提供了一系列便捷的方法来一次输入或输出大批量内容
         * 流的水滴模型概念：
         *  1.input/reader  --读入 字节、字符流
         *  2.output/writer --写出 字节、字符流
         */



    }

}
