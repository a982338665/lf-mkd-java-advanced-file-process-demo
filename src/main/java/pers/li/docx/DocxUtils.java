package pers.li.docx;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.*;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/7/16 19:22
 * @Description : docx
 */
public class DocxUtils {

    public static void main(String[] args) throws FileNotFoundException {

        List<Map<String, Object>> reqList = new ArrayList<>();
        Map<String, Object> mreqMap = new HashMap<>();
        mreqMap.put(GetDocxConf.REQ_NAME, "id");
        mreqMap.put(GetDocxConf.REQ_DATA_TYPE, "string");
        mreqMap.put(GetDocxConf.REQ_PARAM_TYPE, "query");
        mreqMap.put(GetDocxConf.REQ_DESC, "主键id");
        mreqMap.put(GetDocxConf.REQ_ISFILL, "是");
        reqList.add(mreqMap);
        reqList.add(mreqMap);
        reqList.add(mreqMap);


        SetDocxConf instance = SetDocxConf.getInstance();
        Map<String, List<Map<String, Object>>> mapp = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        addList(list, reqList, reqList, "老师新建接口", "老师新建接口", "/_API_API_API_API_API_API_API_API_API/xkexternalimport/addTeacher", "Get", "application/json");
        addList(list, reqList, reqList, "老师新建接口", "老师新建接口", "2_EXTERNAL_API/xkexternalimport/addTeacher", "Get", "application/json");
        addList(list, reqList, reqList, "老师新建接口", "老师新建接口", "EXTERNAL_API/xkexternalimport/addTeacher", "Get", "application/json");
        mapp.put("1级标题测试" + SetDocxConf.getInstance().getSplitTitle() + UUID.randomUUID(), list);
        mapp.put("1级标题测试" + SetDocxConf.getInstance().getSplitTitle() + UUID.randomUUID(), list);
        //头部信息
        Map<String, String> map = new HashMap<>();
        map.put(GetDocxConf.INDEX_TITLE, "MES系统接口文档");
        map.put(GetDocxConf.INDEX_DESC, "描述");
        map.put(GetDocxConf.INDEX_VERSIONSWAGGER, "2.0");
        map.put(GetDocxConf.INDEX_VERSIONDOCX, "1.0");
        map.put(GetDocxConf.INDEX_NAME, "luofeng");
        map.put(GetDocxConf.INDEX_URL, "http://pingpingpang.cn");
        map.put(GetDocxConf.INDEX_EMAIL, "982338665@qq.com");
        map.put(GetDocxConf.INDEX_TIME, "2020 07-17");
        try (
                XWPFDocument doc = new XWPFDocument();
                //组装文件名称
                FileOutputStream out = new FileOutputStream(instance.getFilePath());

        ) {
            //添加预置标题
            addStyle(doc);
            //生成文档标题
            addDocxTitle(map, doc);
            //首页介绍
            addIndexDocx(instance, map, doc);
            //生成文档内容
            generateDocx(doc, mapp);
            //写文档
            doc.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void addIndexDocx(SetDocxConf instance, Map<String, String> map, XWPFDocument doc) {
        //添加一级标题
        XWPFParagraph paragraph1 = doc.createParagraph();
        paragraph1.setStyle("heading1");
        addInsertNewRun(paragraph1, 22, true, "1.".concat(instance.getFirstName()), true, false);
        //添加一级标题下的内容
        XWPFParagraph paragraph1c = doc.createParagraph();
        addInsertNewRun(paragraph1c, 12, false, instance.getDocxDesc().concat(map.get(GetDocxConf.INDEX_DESC)), true, false);
        addInsertNewRun(paragraph1c, 12, false, instance.getDocxVersion().concat(map.get(GetDocxConf.INDEX_VERSIONDOCX)), true, false);
        addInsertNewRun(paragraph1c, 12, false, instance.getSwaggVersion().concat(map.get(GetDocxConf.INDEX_VERSIONSWAGGER)), true, false);
        addInsertNewRun(paragraph1c, 12, false, instance.getContactName().concat(map.get(GetDocxConf.INDEX_NAME)), true, false);
        addInsertNewRun(paragraph1c, 12, false, instance.getContactEmail().concat(map.get(GetDocxConf.INDEX_EMAIL)), true, false);
        addInsertNewRun(paragraph1c, 12, false, instance.getContactUrl().concat(map.get(GetDocxConf.INDEX_URL)), true, false);
        addInsertNewRun(paragraph1c, 12, false, instance.getDocxTime().concat(map.get(GetDocxConf.INDEX_TIME)), true, false);
    }

    private static void addDocxTitle(Map<String, String> map, XWPFDocument doc) {
        XWPFParagraph ptitle1 = doc.createParagraph();
        ptitle1.setAlignment(ParagraphAlignment.CENTER);
        addInsertNewRun(ptitle1, 26, true, map.get(GetDocxConf.INDEX_TITLE), true, false);
    }

    public static void addList(List<Map<String, Object>> list, List<Map<String, Object>> reqList, List<Map<String, Object>> resList, String... param) {
        Map<String, Object> map = new HashMap<>();
        map.put(GetDocxConf.INTERFACE_NAME, param[0]);
        map.put(GetDocxConf.INTERFACE_DESC, param[1]);
        map.put(GetDocxConf.INTERFACE_URL, param[2]);
        map.put(GetDocxConf.INTERFACE_METHOD, param[3]);
        map.put(GetDocxConf.INTERFACE_REQ, reqList);
        map.put(GetDocxConf.INTERFACE_RES, resList);
        map.put(GetDocxConf.INTERFACE_TYPE, param[4]);
        list.add(map);
    }

    public static void generateDocx(XWPFDocument doc, Map<String, List<Map<String, Object>>> mapp) {
        int num = 1;
        for (Map.Entry<String, List<Map<String, Object>>> entry : mapp.entrySet()) {
            num++;
            //添加一级标题
            addFirstTitle(doc, num, entry);
            List<Map<String, Object>> list = entry.getValue();
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                String name = map.get(GetDocxConf.INTERFACE_NAME).toString();
                String desc = map.get(GetDocxConf.INTERFACE_DESC).toString();
                String method = map.get(GetDocxConf.INTERFACE_METHOD).toString();
                String url = map.get(GetDocxConf.INTERFACE_URL).toString();
                String type = map.get(GetDocxConf.INTERFACE_TYPE).toString();
                Object o = map.get(GetDocxConf.INTERFACE_REQ);
                List<Map<String, Object>> req = (List<Map<String, Object>>) o;
                Object o1 = map.get(GetDocxConf.INTERFACE_RES);
                List<Map<String, Object>> res = (List<Map<String, Object>>) o1;
                SetDocxConf instance = SetDocxConf.getInstance();
                String title = new StringBuilder().append((i + 1)).append(". ").append(name).toString();
                String pdesc = new StringBuilder().append(instance.getInterDesc()).append(desc).toString();
                String purl = new StringBuilder().append(instance.getInterUrl()).append(url).toString();
                String pmethod = new StringBuilder().append(instance.getInterMethod()).append(method).toString();
                String ptype = new StringBuilder().append(instance.getInterType()).append(type).toString();
                String preq = instance.getInterReq();
                String pres = instance.getInterRes();
                //添加二级标题
                addSecondTitle(doc, title);
                //添加内容
                addInterfaceContent(doc, pdesc, purl, pmethod, ptype, preq);
                //创建表格
//                XWPFTable table = doc.createTable(50, 5);
                XWPFTable table = doc.createTable();
                CTTblWidth width = table.getCTTbl().addNewTblPr().addNewTblW();
                width.setType(STTblWidth.DXA);
                width.setW(BigInteger.valueOf(10000));
                // create first row
                XWPFTableRow tableRowOne = table.getRow(0);

                setCellText(tableRowOne.getCell(0), GetDocxConf.REQ_NAME, -1, true, 6, instance.getReqRowColor());
                setCellText(tableRowOne.addNewTableCell(), GetDocxConf.REQ_DATA_TYPE, -1, true, 0, instance.getReqRowColor());
                setCellText(tableRowOne.addNewTableCell(), GetDocxConf.REQ_PARAM_TYPE, -1, true, 1, instance.getReqRowColor());
                setCellText(tableRowOne.addNewTableCell(), GetDocxConf.REQ_ISFILL, -1, true, 15, instance.getReqRowColor());
                setCellText(tableRowOne.addNewTableCell(), GetDocxConf.REQ_DESC, -1, true, 38, instance.getReqRowColor());
//                tableRowOne.getCell(0).setText();
                //首次进来需要添加列 ，否则会报null
//                tableRowOne.addNewTableCell().setText(GetDocxConf.REQ_DATA_TYPE);
//                tableRowOne.addNewTableCell().setText(GetDocxConf.REQ_PARAM_TYPE);
//                tableRowOne.addNewTableCell().setText(GetDocxConf.REQ_ISFILL);
//                tableRowOne.addNewTableCell().setText(GetDocxConf.REQ_DESC);

                for (int j = 0; j < req.size(); j++) {
                    //获取map数据
                    Map<String, Object> mapReq = req.get(j);
                    XWPFTableRow tableRowTwo = table.createRow();
                    setCellText(tableRowTwo.getCell(0), mapReq.get(GetDocxConf.REQ_NAME).toString(), -1, true, 0, instance.getReqBodyColor());
                    setCellText(tableRowTwo.getCell(1), mapReq.get(GetDocxConf.REQ_DATA_TYPE).toString(), -1, true, 0, instance.getReqBodyColor());
                    setCellText(tableRowTwo.getCell(2), mapReq.get(GetDocxConf.REQ_PARAM_TYPE).toString(), -1, true, 0, instance.getReqBodyColor());
                    setCellText(tableRowTwo.getCell(3), mapReq.get(GetDocxConf.REQ_ISFILL).toString(), -1, true, 0, instance.getReqBodyColor());
                    setCellText(tableRowTwo.getCell(4), mapReq.get(GetDocxConf.REQ_DESC).toString(), -1, true, 0, instance.getReqBodyColor());
//                    tableRowTwo.getCell(1).setText(mapReq.get(GetDocxConf.REQ_DATA_TYPE).toString());
//                    tableRowTwo.getCell(2).setText(mapReq.get(GetDocxConf.REQ_PARAM_TYPE).toString());
//                    tableRowTwo.getCell(3).setText(mapReq.get(GetDocxConf.REQ_ISFILL).toString());
//                    tableRowTwo.getCell(4).setText(mapReq.get(GetDocxConf.REQ_DESC).toString());
                }

                //响应参数
                addInterfaceResponse(doc, pres);
                //创建表格
//                XWPFTable table = doc.createTable(50, 5);
                XWPFTable tableRes = doc.createTable();
                CTTblWidth widthRes = tableRes.getCTTbl().addNewTblPr().addNewTblW();
                widthRes.setType(STTblWidth.DXA);
                widthRes.setW(BigInteger.valueOf(10000));
                // create first row
                XWPFTableRow tableRowOneRes = tableRes.getRow(0);

                setCellText(tableRowOneRes.getCell(0), GetDocxConf.REQ_NAME, -1, true, 6, instance.getResRowColor());
                setCellText(tableRowOneRes.addNewTableCell(), GetDocxConf.REQ_DATA_TYPE, -1, true, 0, instance.getResRowColor());
                setCellText(tableRowOneRes.addNewTableCell(), GetDocxConf.REQ_DESC, -1, true, 1, instance.getResRowColor());

                for (int j = 0; j < res.size(); j++) {
                    //获取map数据
                    Map<String, Object> mapReq = res.get(j);
                    XWPFTableRow tableRowTwo = tableRes.createRow();
                    setCellText(tableRowTwo.getCell(0), mapReq.get(GetDocxConf.REQ_NAME).toString(), -1, true, 0, instance.getResBodyColor());
                    setCellText(tableRowTwo.getCell(1), mapReq.get(GetDocxConf.REQ_DATA_TYPE).toString(), -1, true, 0, instance.getResBodyColor());
                    setCellText(tableRowTwo.getCell(2), mapReq.get(GetDocxConf.REQ_DESC).toString(), -1, true, 0, instance.getResBodyColor());
//                    tableRowTwo.getCell(0).setText(mapReq.get(GetDocxConf.REQ_NAME).toString());
//                    tableRowTwo.getCell(1).setText(mapReq.get(GetDocxConf.REQ_DATA_TYPE).toString());
//                    tableRowTwo.getCell(2).setText(mapReq.get(GetDocxConf.REQ_DESC).toString());
                }
            }
        }
    }

    /**
     * 设置单元格
     *
     * @param cell
     * @param text     文本内容
     * @param width    宽度
     * @param isShd    是否:底色,阴影
     * @param shdValue 阴影值
     * @param shdColor 底色
     */
    public static void setCellText(XWPFTableCell cell, String text, int width,
                                   boolean isShd, int shdValue, String shdColor) {
        CTTc cttc = cell.getCTTc();
        CTTcPr ctPr = cttc.isSetTcPr() ? cttc.getTcPr() : cttc.addNewTcPr();
        CTShd ctshd = ctPr.isSetShd() ? ctPr.getShd() : ctPr.addNewShd();
        if (width != -1) {
            ctPr.addNewTcW().setW(BigInteger.valueOf(width));
        }
        if (isShd) {
            if (shdValue > 0 && shdValue <= 38) {
                ctshd.setVal(STShd.Enum.forInt(shdValue));
            }
            if (shdColor != null) {
                ctshd.setFill(shdColor);
                // ctshd.setColor("auto");
                ctshd.setColor(shdColor);
            }
        }

        ctPr.addNewVAlign().setVal(STVerticalJc.CENTER);
        cttc.getPList().get(0).addNewPPr().addNewJc().setVal(STJc.CENTER);
        cell.setText(text);
    }


    private static void addInterfaceContent(XWPFDocument doc, String pdesc, String purl, String pmethod, String ptype, String preq) {
        //新建段落
        XWPFParagraph p = doc.createParagraph();
        //布局靠左
        p.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun r2 = addInsertNewRun(p, 12, false, pdesc, true, false);
        XWPFRun r3 = addInsertNewRun(p, 12, false, purl, true, false);
        XWPFRun r4 = addInsertNewRun(p, 12, false, pmethod, true, false);
        XWPFRun r6 = addInsertNewRun(p, 12, false, ptype, true, false);
        XWPFRun r5 = addInsertNewRun(p, 12, false, preq, false, false);
    }

    private static void addInterfaceResponse(XWPFDocument doc, String pres) {
        //新建段落
        XWPFParagraph p = doc.createParagraph();
        //布局靠左
        p.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun r5 = addInsertNewRun(p, 12, false, pres, false, false);
    }

    private static void addSecondTitle(XWPFDocument doc, String title) {
        XWPFParagraph p2 = doc.createParagraph();
        p2.setStyle("heading2");
        XWPFRun r1 = addInsertNewRun(p2, 18, true, title, true, false);
    }

    private static void addFirstTitle(XWPFDocument doc, int num, Map.Entry<String, List<Map<String, Object>>> entry) {
        String key = entry.getKey();
        String pkey = key.split(SetDocxConf.getInstance().getSplitTitle())[0];
        XWPFParagraph ptitle1 = doc.createParagraph();
        ptitle1.setStyle("heading1");
        //换页写 首次进来不换行
        if (num != 2) {
            ptitle1.setPageBreak(true);
        }
        addInsertNewRun(ptitle1, 22, true, num + "." + pkey, true, false);
    }

    /**
     * 添加内容
     *
     * @param p
     * @param fontSize 字体大小
     * @param bold     是否加粗
     * @param text     文本内容
     * @return
     */
    private static XWPFRun addInsertNewRun(XWPFParagraph p, int fontSize, boolean bold, String text, boolean huanhang, boolean suojin) {
        SetDocxConf instance = SetDocxConf.getInstance();
//               XWPFRun r1 = p.insertNewRun(i);
        XWPFRun r1 = p.createRun();
        //设置字体大小
        r1.setFontSize(fontSize);
        //是否阴影
//            r1.setShadow(true);
        //是否粗体
        r1.setBold(bold);
        //文本内容
        r1.setText(text);
        //是否斜体
//        r1.setItalic(true);
        //字体
        r1.setFontFamily(instance.getTextContentFont());
        //设置下划线类型
//        r1.setUnderline(UnderlinePatterns.DOT_DOT_DASH);
        //设置文本高度位置
        r1.setTextPosition(instance.getTextPosition());
        //换行
        if (huanhang) r1.addBreak();
        //缩进
        if (suojin) r1.addTab();
        return r1;
    }

    private static void addStyle(XWPFDocument document) {
        SetDocxConf instance = SetDocxConf.getInstance();
        //添加预置样式
        addCustomHeadingStyle(document, "heading1", 1, 22, instance.getTextTitleColor(), instance.getTextTitleFont());
        addCustomHeadingStyle(document, "heading2", 2, 18, instance.getTextTitleColor(), instance.getTextTitleFont());
        addCustomHeadingStyle(document, "heading3", 3, 14, instance.getTextTitleColor(), instance.getTextTitleFont());
        addCustomHeadingStyle(document, "heading4", 4, 10, instance.getTextTitleColor(), instance.getTextTitleFont());
    }

    /**
     * 添加自定义标题
     *
     * @param docxDocument 文档对象
     * @param strStyleId   自定义标题名称
     * @param headingLevel 标题级别 1,2,3...
     * @param pointSize    标题大小
     * @param hexColor     标题颜色
     */
    private static void addCustomHeadingStyle(XWPFDocument docxDocument, String strStyleId, int headingLevel, int pointSize, String hexColor, String font) {

        XWPFStyles styles = docxDocument.createStyles();


        CTStyle ctStyle = CTStyle.Factory.newInstance();
        ctStyle.setStyleId(strStyleId);


        CTString styleName = CTString.Factory.newInstance();
        styleName.setVal(strStyleId);
        ctStyle.setName(styleName);

        CTDecimalNumber indentNumber = CTDecimalNumber.Factory.newInstance();
        indentNumber.setVal(BigInteger.valueOf(headingLevel));

        // lower number > style is more prominent in the formats bar
        ctStyle.setUiPriority(indentNumber);

        CTOnOff onoffnull = CTOnOff.Factory.newInstance();
        ctStyle.setUnhideWhenUsed(onoffnull);

        // style shows up in the formats bar
        ctStyle.setQFormat(onoffnull);

        // style defines a heading of the given level
        CTPPr ppr = CTPPr.Factory.newInstance();
        ppr.setOutlineLvl(indentNumber);
        ctStyle.setPPr(ppr);

        XWPFStyle style = new XWPFStyle(ctStyle);

        //设置大小
        CTHpsMeasure size = CTHpsMeasure.Factory.newInstance();
        size.setVal(new BigInteger(String.valueOf(pointSize)));
        CTHpsMeasure size2 = CTHpsMeasure.Factory.newInstance();
        size2.setVal(new BigInteger("24"));

        //设置字体
        CTFonts fonts = CTFonts.Factory.newInstance();
//        fonts.setAscii("Loma");
        fonts.setAscii(font);

        CTRPr rpr = CTRPr.Factory.newInstance();
        rpr.setRFonts(fonts);
        rpr.setSz(size);
        rpr.setSzCs(size2);

        CTColor color = CTColor.Factory.newInstance();
        color.setVal(hexToBytes(hexColor));
        rpr.setColor(color);
        style.getCTStyle().setRPr(rpr);
        // is a null op if already defined

        style.setType(STStyleType.PARAGRAPH);
        styles.addStyle(style);

    }

    public static byte[] hexToBytes(String hexString) {
        HexBinaryAdapter adapter = new HexBinaryAdapter();
        byte[] bytes = adapter.unmarshal(hexString);
        return bytes;
    }

}
