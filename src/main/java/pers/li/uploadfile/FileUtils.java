package pers.li.uploadfile;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * 文件工具类
 * @author luolei
 * @date 2019年1月31日
 */
public class FileUtils {


    public static final String file = "D:/go-20191030/AdvancedFileProcessDemo/file/";

	/**
	 * 文件上传方法
	 * @param file 文件byte[]
	 * @param filePath 文件上传路径
	 * @param fileName 文件保存路径
	 * @throws IOException 
	 * @throws Exception
	 * void
	 */
    public static void uploadFile(byte[] file, String filePath, String fileName) throws IOException{
 
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
   }
}
