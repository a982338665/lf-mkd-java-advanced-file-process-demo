package pers.li.docx;

import lombok.Data;

/**
 * 设置文件路径的单例模式
 */
@Data
public class SetDocxConf {


    /**
     * 默认的文件导出路径
     */
    private String filePath = "InterfaceFile.docx";

    /**
     * 首页介绍的 key （一级标题：固定名称）
     */
    private String firstName = "系统简介";
    private String docxDesc = "【文档描述】：";
    private String docxVersion = "【文档版本】：";
    private String swaggVersion = "【Swagger版本】：";
    private String contactName = "【联系人】：";
    private String contactUrl = "【联系地址】：";
    private String contactEmail = "【联系邮件】：";
    private String docxTime = "【创建时间】：";


    /**
     * 避免标题重复的 标题分隔符
     */
    private String splitTitle = "@@@WE-";


    /**
     * 接口文档前缀
     */
    private String interDesc = "【协议描述】：";
    private String interUrl = "【接口地址】：";
    private String interMethod = "【请求方式】：";
    private String interType = "【请求类型】：";
    private String interReq = "【请求参数】：";
    private String interRes = "【响应参数】：";


    /**
     * 字体设置；==========================================
     */
    /**
     * 行间距
     */
    private int textPosition = 0;
    /**
     * 标题字体
     */
    private String textTitleFont = "宋体";
    /**
     * 标题颜色
     */
    private String textTitleColor = "000000";
    /**
     * 内容字体
     */
    private String textContentFont = "宋体";

    /**
     * 请求参数列表行头颜色
     */
    private String reqRowColor = "2EC6BD";
    /**
     * 请求参数列表行体颜色
     */
    private String reqBodyColor = "CCCCCC";
    /**
     * 响应参数列表行头颜色
     */
    private String resRowColor = "C2C3FD";
    /**
     * 响应参数列表行体颜色
     */
    private String resBodyColor = "CCCCCC";

    private SetDocxConf() {

    }

    private static SetDocxConf setDocxConf = new SetDocxConf();

    public static SetDocxConf getInstance() {
        return setDocxConf;
    }


}
