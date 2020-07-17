package pers.li.docx;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/7/16 17:57
 * @Description :
 * http://poi.apache.org/apidocs/dev/
 */
public class DocxTest {

    public static void main(String[] args) throws Exception {
        new DocxTest().test();
    }

    public void test() throws Exception {
        int level = 1;
        String styleName = "标题 1";
        String name = "标题内容";
        String filePath = "./0530.docx";
        // 获得word的pack对象
        OPCPackage pack = POIXMLDocument.openPackage( filePath );
        // 获得XWPFDocument对象
        XWPFDocument doc = new XWPFDocument( pack );
        addCustomHeadingStyle( doc, styleName, level );
        XWPFParagraph paragraph = doc.getParagraphs().get( 0 );
        // 段落的格式,下面及个设置,将使新添加的文字向左对其,无缩进.
        paragraph.setIndentationLeft( 0 );
        paragraph.setIndentationHanging( 0 );
        paragraph.setAlignment( ParagraphAlignment.LEFT );
        // paragraph.setWordWrap( true );
        paragraph.setStyle( styleName );
        // 在段落中新插入一个run,这里的run我理解就是一个word文档需要显示的个体,里面可以放文字,参数0代表在段落的最前面插入
        XWPFRun run = paragraph.insertNewRun( 0 );
        // 设置run内容
        run.setText( "中国" );
        run.setFontFamily( "宋体" );
        run.setBold( true );
        run.setFontSize( 20 );
        run.addBreak( BreakType.TEXT_WRAPPING );
        // 生成的标题文件
        File newFile = new File( "./222.docx" );
        FileOutputStream fos = new FileOutputStream( newFile );
        doc.write( fos );
        fos.flush();
        fos.close();
        pack.close();
        newFile.delete();
    }

    /**
     * 设置标题样式
     * @param docxDocument
     * @param strStyleId
     * @param headingLevel
     */
    public void addCustomHeadingStyle( XWPFDocument docxDocument, String strStyleId, int headingLevel ) {

        CTStyle ctStyle = CTStyle.Factory.newInstance();
        ctStyle.setStyleId( strStyleId );

        CTString styleName = CTString.Factory.newInstance();
        styleName.setVal( strStyleId );
        ctStyle.setName( styleName );

        CTDecimalNumber indentNumber = CTDecimalNumber.Factory.newInstance();
        indentNumber.setVal( BigInteger.valueOf( headingLevel ) );

        // lower number > style is more prominent in the formats bar
        ctStyle.setUiPriority( indentNumber );

        CTOnOff onoffnull = CTOnOff.Factory.newInstance();
        ctStyle.setUnhideWhenUsed( onoffnull );

        // style shows up in the formats bar
        ctStyle.setQFormat( onoffnull );

        // style defines a heading of the given level
        CTPPr ppr = CTPPr.Factory.newInstance();
        ppr.setOutlineLvl( indentNumber );
        ctStyle.setPPr( ppr );

        XWPFStyle style = new XWPFStyle( ctStyle );

        // is a null op if already defined
        XWPFStyles styles = docxDocument.createStyles();

        style.setType( STStyleType.PARAGRAPH );
        styles.addStyle( style );

    }


    public void  ptherCOntent(){
        XWPFDocument document = new XWPFDocument();
        XWPFTable xtab2 = document.createTable(1, 2);
//        1.表格或单元格宽度：
//        默认TblW的type属性为STTblWidth.AUTO,即自动伸缩。所以要调整为指定类型：STTblWidth.DXA 1）表格宽：
        CTTblPr tblPr = xtab2.getCTTbl().getTblPr();
        tblPr.getTblW().setType(STTblWidth.DXA);
        tblPr.getTblW().setW(new BigInteger("7000"));

//        2.单元格宽：
//        CTTcPr tcpr = cell.getCTTc().addNewTcPr();
//        CTTblWidth cellw = tcpr.addNewTcW();
//        cellw.setType(STTblWidth.DXA);
//        cellw.setW(BigInteger.valueOf(360*5));

//        2.表格风格
//        注：如果不设置风格，将采用默认的Normal风格
//        CTTblPr tblPr = xtab2.getCTTbl().getTblPr();
//        CTString styleStr = tblPr.addNewTblStyle();
//        styleStr.setVal("StyledTable");

//        2.表格行高：获取表格行的CTTrPr.增加CTHeight属性
//        List rows = xtab2.getRows();
//        for (XWPFTableRow row : rows) {
//            CTTrPr trPr = row.getCtRow().addNewTrPr();
//            CTHeight ht = trPr.addNewTrHeight();
//            ht.setVal(BigInteger.valueOf(360));
//        }

//        3.表格行内容垂直居中：
//        CTVerticalJc va = tcpr.addNewVAlign();
//        va.setVal(STVerticalJc.CENTER);
//        3.表格单元格颜色
//                例如下面的标题行与奇偶行颜色设置
//        CTShd ctshd = tcpr.addNewShd();
//        ctshd.setColor("auto");
//        ctshd.setVal(STShd.CLEAR);
//        if (rowCt == 0) {
//              标题行
//            ctshd.setFill("A7BFDE");
//        }else if (rowCt % 2 == 0) {
        // even row
//            ctshd.setFill("D3DFEE");
//
//        }else {
    // odd row
//            ctshd.setFill("EDF2F8");
//        }

//5.获取某指定位置对象并生成新的光标位置
//    注:这个更新或插入操作比较有用,比如更新文档目录.
//            XmlCursor cursor = doc.getDocument().getBody().getPArray(0).newCursor();
//    XWPFParagraph cP = doc.insertNewParagraph(cursor);
//6.插入图片：
//    XWPFParagraph parapictest = document.createParagraph();
//    XWPFRun runtest = parapictest.createRun();
//    runtest.setText("图片:");
//    XWPFRun pictest = document.createParagraph().createRun();
//    XWPFPicture picture = pictest.addPicture(new FileInputStream("D://563.jpg"), Document.PICTURE_T YPE_JPEG, "D://563.jpg", 1000*360*10,1000*360*10);
    }


}
