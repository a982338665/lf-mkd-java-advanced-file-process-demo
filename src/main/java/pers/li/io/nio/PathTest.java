package pers.li.io.nio;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * nio2 提供的path接口弥补File的不足
 * path接口代表一个平台无关的平台路径
 * Files,Paths两个工具类
 */
public class PathTest {
    public static void main(String[] args) throws Exception{
        //以当前路径创建path对象
        Path path= Paths.get(".");
        System.out.println("path里包含的路经数量："+path.getNameCount());
        System.out.println("path的根路径："+path.getRoot());
        System.out.println("path对应的绝对路径："+path.toAbsolutePath());
        System.out.println("path绝对路径的根路径："+path.toAbsolutePath().getRoot());
        System.out.println("pathpath绝对路径包含的路经数量："+path.toAbsolutePath().getNameCount());
        System.out.println(path.toAbsolutePath().getName(3));
        //以多个String来构建Path对象
        Path path2= Paths.get("G:","linux");
        System.out.println(path2);

    }
}
