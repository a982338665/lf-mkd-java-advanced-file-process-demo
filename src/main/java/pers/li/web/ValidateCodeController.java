package pers.li.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pers.li.uploadfile.FileType;
import pers.li.uploadfile.FileUtils;
import pers.li.util.ValidateCodeUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author:luofeng
 * @createTime : 2018/10/9 16:02
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class ValidateCodeController {


    /**
     * 验证码生成
     *  http://localhost:8080/test/generate
     *
     * @throws IOException
     */
    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    @ResponseBody
    public void index3(HttpServletResponse response) throws IOException {
        ValidateCodeUtil.generateAndResponse(60, 200, 3, 5, response.getOutputStream());
//        ValidateCodeUtil.generateAndResponse(60, 200, 4, 5, response.getOutputStream());
    }


}
