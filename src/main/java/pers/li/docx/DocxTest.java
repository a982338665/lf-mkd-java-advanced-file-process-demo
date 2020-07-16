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
 */
public class DocxTest {

    public void test() throws Exception {
        int level = 1;
        String styleName = "标题 1";
        String name = "标题内容";
        String filePath = "C:/Users/Desktop/0530测试.docx";
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
        File newFile = new File( "C:/Users/Desktop/222.docx" );
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


}
