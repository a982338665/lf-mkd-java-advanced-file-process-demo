package pers.li.io.nio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * s使用FileVister遍历文件与目录
 * 实现了对文件或是目录的搜索
 */
public class FileVisterTest {


    public static void main(String[] args) throws Exception{

        //遍历以下文件的所有文件和子目录
        Files.walkFileTree(Paths.get("D:\\git-266178"),new SimpleFileVisitor<Path>(){

            //访问文件时触发该方法
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException{
             System.out.println("正在访问--"+file+"---文件");
             if(file.endsWith("UserService.java")){
                 System.out.println("找到目标文件："+file);
                 return FileVisitResult.TERMINATE;
             }
                return FileVisitResult.CONTINUE;
            }

            //开始访问目录时触发该方法
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException{
                System.out.println("正在访问--"+dir+"---/路径");
                return FileVisitResult.CONTINUE;
            }


        });
    }
}
