package pers.li.uploadxml;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;

@Data //lombok注解，无需手动添加getter/setter
@XmlRootElement(name = "xml")
@ToString //lombok注解，无需手动添加toString方法
public class XmlRequest {

    private String order_id;
    private String sign;
    private String data;
    private String uuid;
}
