package pers.li.docx;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/7/17 10:10
 * @Description : 文档标题测试
 */
public class DocxTitle {

    public static void main(String[] args) throws IOException {
        FileOutputStream out = new FileOutputStream("test-text2.docx");
        XWPFDocument document = new XWPFDocument();
        String heading1 = "My Heading 1";
        String heading2 = "My Heading 2";
        String heading3 = "My Heading 3";
        String heading4 = "My Heading 4";
        //添加预置样式
        addCustomHeadingStyle(document, heading1, 1, 36, "4288BC","宋体");
        addCustomHeadingStyle(document, heading2, 2, 28, "4288BC","宋体");
        addCustomHeadingStyle(document, heading3, 3, 24, "4288BC","宋体");
        addCustomHeadingStyle(document, heading4, 4, 20, "000000","宋体");
        addDefaultHeadingStyle(document, "1", 1);
        addDefaultHeadingStyle(document, "2", 2);
        addDefaultHeadingStyle(document, "3", 3);
        addDefaultHeadingStyle(document, "4", 4);

        //自定义一级标题
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setStyle(heading1);
        XWPFRun run = paragraph.createRun();
        run.setText("Nice header!");
        //自定义2级标题
        XWPFParagraph paragraph1 = document.createParagraph();
        paragraph1.setStyle(heading2);
        XWPFRun run1 = paragraph1.createRun();
        run1.setText("Nice header!");
        //自定义3级标题
        XWPFParagraph paragraph2 = document.createParagraph();
        paragraph2.setStyle(heading3);
        XWPFRun run2 = paragraph2.createRun();
        run2.setText("Nice header!");
        //自定义4级标题
        XWPFParagraph paragraph3 = document.createParagraph();
        paragraph3.setStyle(heading4);
        XWPFRun run3 = paragraph3.createRun();
        run3.setText("Nice header!");
        //默认标题
        XWPFParagraph paragraph4 = document.createParagraph();
        paragraph4.setStyle("1");
        XWPFRun run4= paragraph4.createRun();
        run4.setText("Nice header! 哈啊哈");
        document.write(out);
    }


    /**
     * 添加自定义标题
     * @param docxDocument  文档对象
     * @param strStyleId    自定义标题名称
     * @param headingLevel  标题级别 1,2,3...
     * @param pointSize     标题大小
     * @param hexColor      标题颜色
     */
    private static void addCustomHeadingStyle(XWPFDocument docxDocument, String strStyleId, int headingLevel, int pointSize, String hexColor,String font) {

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
    /**
     * 添加默认标题
     * @param docxDocument  文档对象
     * @param strStyleId    自定义标题名称
     * @param headingLevel  标题级别 1,2,3...
     */
    private static void addDefaultHeadingStyle(XWPFDocument docxDocument, String strStyleId, int headingLevel) {

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

//        CTHpsMeasure size = CTHpsMeasure.Factory.newInstance();
//        size.setVal(new BigInteger(String.valueOf(pointSize)));
//        CTHpsMeasure size2 = CTHpsMeasure.Factory.newInstance();
//        size2.setVal(new BigInteger("24"));

//        CTFonts fonts = CTFonts.Factory.newInstance();
//        fonts.setAscii("Loma");
//
//        CTRPr rpr = CTRPr.Factory.newInstance();
//        rpr.setRFonts(fonts);
//        rpr.setSz(size);
//        rpr.setSzCs(size2);

//        CTColor color = CTColor.Factory.newInstance();
//        color.setVal(hexToBytes(hexColor));
//        rpr.setColor(color);
//        style.getCTStyle().setRPr(rpr);
        // is a null op if already defined

        style.setType(STStyleType.PARAGRAPH);
        styles.addStyle(style);

    }


}
