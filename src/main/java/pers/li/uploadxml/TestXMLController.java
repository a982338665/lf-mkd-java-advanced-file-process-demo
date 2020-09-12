package pers.li.uploadxml;


import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pers.li.uploadfile.FileType;
import pers.li.uploadfile.FileUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.sax.SAXResult;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author:luofeng
 * @createTime : 2018/10/9 16:02
 * 测试xml格式内容
 */
@RestController
@RequestMapping("/testxmlvo")
@Slf4j
public class TestXMLController {

    /**
     * 处理xml格式参数
     *
     * @param xmlRequest
     * @return
     */
    @RequestMapping(value = "/handleXmlParam", produces = {"application/xml;charset=UTF-8"})
    @ResponseBody
    public String handleXmlParam(@RequestBody XmlRequest xmlRequest) throws Exception {
        log.info("XmlRequest:{}", xmlRequest);
        XmlResponse response = new XmlResponse("1", "success");
        return ojbectToXmlWithCDATA(XmlResponse.class, response);
    }

    /**
     * Java对象转换为CDATA包裹XML
     *
     * @param clazz
     * @param obj
     * @return
     * @throws Exception
     */
    public static String ojbectToXmlWithCDATA(Class clazz, Object obj) throws Exception {

        JAXBContext context = JAXBContext.newInstance(clazz);
        // 配置OutputFormat处理CDATA
        OutputFormat of = new OutputFormat();
        of.setCDataElements(new String[]{"^code", "^msg"});
        of.setPreserveSpace(true);
        of.setIndenting(true);
        // 创建序列化
        ByteArrayOutputStream op = new ByteArrayOutputStream();
        XMLSerializer serializer = new XMLSerializer(op, of);
        SAXResult result = new SAXResult(serializer.asContentHandler());
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        mar.marshal(obj, result);

        return op.toString("utf-8");
    }

}
