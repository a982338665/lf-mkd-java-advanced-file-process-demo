package pers.li.excel;
import com.github.lfexecleasy.anno.LFColume;
import java.util.Date;

/**
 * @author : Mr huangye
 * URL : CSDN 皇夜_
 * createTime : 2020/5/29 16:19
 */
public class TestUser {

    @LFColume("id")
    private String id;
    @LFColume("名称")
    private String name;
    @LFColume("年龄@1-1岁 2-2岁")
    private String age;
    @LFColume("时间")
    private Date time;

    private long idcard;

    public long getIdcard() {
        return idcard;
    }

    public void setIdcard(long idcard) {
        this.idcard = idcard;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
