package pers.li.uploadfile;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author:luofeng
 * @createTime : 2018/10/9 16:02
 * 测试优化瓶颈：SUser user结果封装测试
 */
@RestController
@RequestMapping("/test3vo")
@Slf4j
public class TestController3 {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String index1() {
        return "1";
    }

    /**
     * 单文件上传
     *
     * @param file
     * @param name
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/test3", method = RequestMethod.POST)
    @ResponseBody
    public String index3(MultipartFile file, @RequestParam("name") String name) throws IOException {
        //获取文件名
        String fileName = file.getOriginalFilename();
        //获取表单提交文件使用的字段
        String partName = file.getName();
        //判断文件是否为空
        boolean empty = file.isEmpty();
        //获取ContentType
        String contentType = file.getContentType();
        //获取文件直接数
        Long size = file.getSize();
        //获取文件所有字节
        byte[] bytes = file.getBytes();
        //获取InputStream
        InputStream in = file.getInputStream();
        //根据文件头获取文件类型
        String type = FileType.getFileType(in);
        System.err.println("获取文件名：" + fileName);
        System.err.println("上传文件类型：" + type);
        System.err.println("获取表单提交文件使用的字段：" + partName);
        System.err.println("获取ContentType：" + contentType);
        System.err.println("获取文件大小：" + size);
        System.err.println("传值名称：" + name);
        StringBuilder builder = new StringBuilder();
        //存储文件
        File root = new File(FileUtils.file);
        if (!root.isDirectory()) {
            root.mkdirs();
        }
        try {
            file.transferTo(new File(root, name.concat(".").concat(type)));
            return String.format("Upload to %s", fileName);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Upload Failed";
    }


    /**
     * 单文件上传
     *
     * @param file
     * @return
     * @throws IllegalStateException
     * @throws IOException           String
     */
    @PostMapping("/upload.do")
    //@RequestMapping(value="/upload.do", method = RequestMethod.POST)
    //上传的文件会转换成MultipartFile对象，file名字对应html中上传控件的name
    public String upload(MultipartFile file) throws IllegalStateException, IOException {
        //取得当前上传文件的文件名称
        String originalFilename = file.getOriginalFilename();
        //transferTo是保存文件，参数就是要保存到的目录和名字
        String filePath = FileUtils.file;
        System.out.println("文件类型：" + file.getContentType());
        System.out.println("原文件名：" + originalFilename);
        System.out.println("是否为空：" + file.isEmpty());
        System.out.println("文件大小：" + file.getSize());
        System.out.println("存储路径：" + filePath + originalFilename);
        file.transferTo(new File(filePath + originalFilename));
        return "文件上传完毕";

    }

    /**
     * 多文件上传，大量文件时，防止文件名相同，重新修改存储文件名
     * @param files
     * @return
     * @throws IOException
     * String
     */
    @PostMapping("/upload2.do")
    //@RequestMapping(value="/upload2.do", method = RequestMethod.POST)
    //上传的文件会转换成MultipartFile对象，file名字对应html中上传控件的name
    public String upload2(MultipartFile[] files) throws IOException{
        if(files.length == 0){
            return "请选择要上传的文件";
        }
        for (MultipartFile multipartFile : files) {
            if(multipartFile.isEmpty()){
                return "文件上传失败";
            }
            byte[] fileBytes = multipartFile.getBytes();
            String filePath = FileUtils.file;
            //取得当前上传文件的文件名称
            String originalFilename = multipartFile.getOriginalFilename();
            //生成文件名
            String fileName = UUID.randomUUID() +"&"+ originalFilename;
            FileUtils.uploadFile(fileBytes, filePath, fileName);
        }
        return "文件上传完毕";
    }

}
