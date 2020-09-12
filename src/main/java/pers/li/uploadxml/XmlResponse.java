package pers.li.uploadxml;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "xml")
public class XmlResponse {

    private String code;
    private String msg;

    public XmlResponse() {
    }

    public XmlResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
