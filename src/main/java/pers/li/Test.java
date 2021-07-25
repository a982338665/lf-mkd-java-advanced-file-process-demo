package pers.li;

import com.github.generatecode.model.OutTableInfo;
import com.github.generatecode.out.GenerateCode;
import com.github.generatecode.out.OutPipeFunction;
import com.github.generatecode.out.SetGenerateConf;
import com.github.generatecode.template.TypeCovert;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2021/4/7 13:53
 * @Description :
 */
public class Test {

    public static void main(String[] args) {

        SetGenerateConf instance = SetGenerateConf.getInstance();
        //================================================================================================= 公共部分
        instance.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC");
        instance.setUser("root");
        instance.setPassword("root123");
        instance.setDriver("com.mysql.jdbc.Driver");

        //数据库类型 == 类类型的对应关系 可覆盖
        Map<String, String> types = TypeCovert.getInstance();
        types.put("datetime", "java.time.LocalDateTime");
        types.put("timestamp", "java.time.LocalDateTime");

        //预置模板可能会用到的管道符
        Map<String, Function> pipeMap = OutPipeFunction.PIPE_MAP;
        Function<Boolean, String> judgeTrue = (e) -> e==true ? "true" : "false";
        Function<String, String> judgeDateTime = (e) -> e.equals("LocalDateTime") ? "true" : "false";
        pipeMap.put("judgeTrue", judgeTrue);
        pipeMap.put("judgeDateTime", judgeDateTime);
        //以下为管道符的使用方式


        //================================================================================================ 私有部分
        //自定义模板存放位置
        instance.setTemplateUrl("./template");
        //此处不支持反斜杠，预置包名的  默认值，此处配置则不需要 针对每个人出不同给的模板,模板预置的初始路径
        SetGenerateConf.put_dynamic_map("basePath", ".\\xxxx\\");
        //定义作者信息
        SetGenerateConf.put_dynamic_map("author", "皇夜_");
        //需要生成的表
        instance.setTableList(Arrays.asList(
                new OutTableInfo("t_s_test", "t_s_")
        ));
        GenerateCode.generateCode();
    }
}
