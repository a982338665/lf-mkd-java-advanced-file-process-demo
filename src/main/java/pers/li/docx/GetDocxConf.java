package pers.li.docx;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/7/17 12:04
 * @Description : 常量集合
 */
public interface GetDocxConf {

    /**
     * 首页目录信息定义魔法值
     */
    String INDEX_TITLE = "title";
    String INDEX_DESC = "desc";
    String INDEX_VERSIONSWAGGER = "version_swagger";
    String INDEX_VERSIONDOCX = "version_docx";
    String INDEX_NAME = "name";
    String INDEX_URL = "url";
    String INDEX_EMAIL = "email";
    String INDEX_TIME = "time";


    /**
     * 接口信息 定义魔法值
     */
    String INTERFACE_NAME = "name";
    String INTERFACE_DESC = "desc";
    String INTERFACE_URL = "url";
    String INTERFACE_METHOD = "method";
    String INTERFACE_REQ = "req";
    String INTERFACE_RES = "res";
    String INTERFACE_TYPE = "type";


    /**
     * 接口参数 列名
     */
    String REQ_NAME = "参数名";
    String REQ_DATA_TYPE = "数据类型";
    String REQ_PARAM_TYPE = "参数类型";
    String REQ_ISFILL = "是否必填";
    String REQ_DESC = "说明";


}
