package pers.li.io.nio;

import java.nio.file.*;

/**
 * 监听文件变化：
 * 以前处理方式：后台启动线程，每隔一段时间去遍历一次制定目录的文件
 *              此种方式繁琐并且性能差
 * 如今处理方式：
 *
 */
public class WatchServiceTest {
    public static void main(String[] args) throws  Exception{
        //获取文件系统的 WatchService对象
        WatchService service=FileSystems.getDefault().newWatchService();
        //为c:盘根路径注册监听
        Paths.get("C:/").register(
                service,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE
        );
        while(true){
                //获取下一个文件变化的事件
            /**
             * take():如果没有watchkey，就一直等待
             * poll():如果没有watchkey，立即返回null
             * poll(long timeout,TimeUnit unit): 尝试等待多长时间去获取下一个watchkey
             * --------------------
             * 综上，若要一直监控则选take
             */
            WatchKey key=service.take();
//            WatchKey key=service.poll();
//            WatchKey key=service.poll();
            for (WatchEvent<?> event:key.pollEvents()
                 ) {
                System.out.println(event.context()+"文件发生了 "+event.kind()+" 事件");
            }
                //重设watchKey
            boolean b=key.reset();
            //如果重设失败退出监听
            if(!b){
                break;
            }
        }
    }
}
