package pers.li.web;


import com.github.lfexecleasy.entity.ExcelRuleInfo;
import com.github.lfexecleasy.entity.ExportDataALL;
import com.github.lfexecleasy.entity.ExportDataBase;
import com.github.lfexecleasy.entity.ExportDataPart;
import com.github.lfexecleasy.util.ExportExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pers.li.util.ValidateCodeUtil;
import pers.li.web.entity.TestDept;
import pers.li.web.entity.TestUser;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author:luofeng
 * @createTime : 2018/10/9 16:02
 */
@RestController
@RequestMapping("/excel")
@Slf4j
public class ExcelExportController {


    /**
     * excel 导出
     * http://localhost:8080/excel/export
     *
     * @throws IOException
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    @ResponseBody
    public void index3(HttpServletResponse response) throws IOException {
        ExportExcelUtils exportExcelUtils = new ExportExcelUtils();
        String s = getExcelInfo(exportExcelUtils);
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("name" + ".xls", "utf-8"));
        exportExcelUtils.outPutResponse(response.getOutputStream(), s);
    }

    private String getExcelInfo(ExportExcelUtils exportExcelUtils) {
        //数据准备
        List<TestUser> testUsers = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            TestUser testUser = new TestUser();
            testUser.setAge("18");
            testUser.setId("12");
            testUser.setName("name");
            testUser.setTime(new Date());
            testUsers.add(testUser);
        }
        for (int i = 0; i < 1; i++) {
            TestUser testUser = new TestUser();
            testUser.setAge("1");
            testUser.setId("12");
            testUser.setName("name");
            testUser.setTime(new Date());
            testUsers.add(testUser);
        }
        //数据准备
        List<TestDept> testDept = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            TestDept testUser = new TestDept();
            testUser.setId("12");
            testUser.setDeptName("部门+" + i);
            testDept.add(testUser);
        }

        //导出全部
        ExportDataBase all1 = new ExportDataALL("sheet1", testUsers, TestUser.class);
        ExportDataBase all2 = new ExportDataALL("sheet2", testUsers, TestUser.class);
        ExportDataBase all3 = new ExportDataPart("sheet3", new String[]{"id", "部门名称"}, new String[]{"id", "deptName"}, testDept);

        all1.setExcelRuleInfo(new ExcelRuleInfo("hhh"));

        //        System.err.println(s);
        return exportExcelUtils.makeTable(all1, all2, all3);
    }

    public static void main(String[] args) {

        //数据准备
        List<TestUser> testUsers = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            TestUser testUser = new TestUser();
            testUser.setAge("18");
            testUser.setId("12");
            testUser.setName("name");
            testUser.setTime(new Date());
            testUsers.add(testUser);
        }
        for (int i = 0; i < 1; i++) {
            TestUser testUser = new TestUser();
            testUser.setAge("1");
            testUser.setId("12");
            testUser.setName("name");
            testUser.setTime(new Date());
            testUsers.add(testUser);
        }
        //数据准备
        List<TestDept> testDept = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            TestDept testUser = new TestDept();
            testUser.setId("12");
            testUser.setDeptName("部门+" + i);
            testDept.add(testUser);
        }

        //导出全部
        ExportDataBase all1 = new ExportDataALL("sheet1", testUsers, TestUser.class);
        ExportDataBase all2 = new ExportDataALL("sheet2", testUsers, TestUser.class);
        ExportDataBase all3 = new ExportDataPart("sheet3", new String[]{"id", "部门名称"}, new String[]{"id", "deptName"}, testDept);

        all1.setExcelRuleInfo(new ExcelRuleInfo("hhh"));

        String fileName = "./TestOutputExcel.xlsx";
        ExportExcelUtils exportExcelUtils = new ExportExcelUtils();
        String s = exportExcelUtils.makeTable(all1, all2, all3);
//        System.err.println(s);
        exportExcelUtils.outPutFile(fileName, s);
        //多sheet导出
//        export(all1, all2);

    }

}
