package pers.li.docx;

import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHighlight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHighlightColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STUnderline;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

/**
 * docx测试类
 */
public class DocxTestAll {
    public static void main(String[] args) throws Exception {
        DocxTestAll t = new DocxTestAll();
        XWPFDocument doc = new XWPFDocument();
        // 测试下划线样式
        t.testSetParagraphUnderLineStyle(doc);
        t.addNewPage(doc, BreakType.PAGE);
        // 测试文本高亮
        t.testSetParagraphTextHightLightStyle(doc);
        t.addNewPage(doc, BreakType.PAGE);
        // 测试字符底纹
        t.testSetParagraphTextShdStyle(doc);
        // 测试段落底纹
        t.testSetParagraphShdStyle(doc);
        // 测试表格样式
        t.testTableStyle(doc);
        t.saveDocument(doc,
                "xxxxxx.docx");
    }

    public void testSetParagraphUnderLineStyle(XWPFDocument doc) {
        String[] colors = new String[] { "CCA6EF", "DD999D", "4FCEF0",
                "7A7A7A", "F3C917", "FFA932", "C7B571", "535354", "5FD2F1",
                "A6DBFF", "FEF8B6" };
        Random random = new Random();
        // TODO 这里为了方便测试写了数字,推荐写英文样式
        for (int i = 1; i <= 18; i++) {
            XWPFParagraph p = doc.createParagraph();
            setParagraphTextStyleInfo(p, "测试下划线_" + i, "宋体", "1D8C56", "22",
                    false, false, false, true, i,
                    colors[Math.abs(random.nextInt(colors.length))], false, 0);
            setParagraphSpacingInfo(p, true, "0", "50", false, "0", "0", true,
                    "240", STLineSpacingRule.Enum.forString("auto"));
            setParagraphAlignInfo(p, ParagraphAlignment.LEFT,
                    TextAlignment.CENTER);
        }
    }

    public void testSetParagraphTextHightLightStyle(XWPFDocument doc) {
        // TODO 这里为了方便测试写了数字,推荐写英文样式
        for (int i = 1; i <= 17; i++) {
            XWPFParagraph p = doc.createParagraph();
            setParagraphTextStyleInfo(p, "测试文本高亮_" + i, "宋体", "1D8C56", "22",
                    false, false, false, false, 0, null, true, i);
            setParagraphSpacingInfo(p, true, "0", "50", false, "0", "0", true,
                    "240", STLineSpacingRule.Enum.forString("auto"));
            setParagraphAlignInfo(p, ParagraphAlignment.LEFT,
                    TextAlignment.CENTER);
        }
    }

    public void testSetParagraphTextShdStyle(XWPFDocument doc) {
        String[] colors = new String[] { "CCA6EF", "DD999D", "4FCEF0",
                "7A7A7A", "F3C917", "FFA932", "C7B571", "535354", "5FD2F1",
                "A6DBFF", "FEF8B6" };
        Random random = new Random();
        // TODO 这里为了方便测试写了数字,推荐写英文样式
        for (int i = 1; i <= 38; i++) {
            XWPFParagraph p = doc.createParagraph();
            setParagraphTextStyleInfo(p, "测试文本底纹_" + i, "宋体", "1D8C56", "22",
                    false, false, false, false, 0, null, false, 0);
            setParagraphTextShdStyle(p, true, i,
                    colors[Math.abs(random.nextInt(colors.length))]);
            setParagraphSpacingInfo(p, true, "0", "50", false, "0", "0", true,
                    "240", STLineSpacingRule.Enum.forString("auto"));
            setParagraphAlignInfo(p, ParagraphAlignment.LEFT,
                    TextAlignment.CENTER);
        }
    }

    public void testSetParagraphShdStyle(XWPFDocument doc) {
        String[] colors = new String[] { "CCA6EF", "DD999D", "4FCEF0",
                "7A7A7A", "F3C917", "FFA932", "C7B571", "535354", "5FD2F1",
                "A6DBFF", "FEF8B6" };
        Random random = new Random();
        // TODO 这里为了方便测试写了数字,推荐写英文样式
        for (int i = 1; i <= 38; i++) {
            XWPFParagraph p = doc.createParagraph();
            setParagraphTextStyleInfo(p, "测试段落底纹_" + i, "宋体", "1D8C56", "22",
                    false, false, false, false, 0, null, false, 0);
            setParagraphShdStyle(p, true, i,
                    colors[Math.abs(random.nextInt(colors.length))]);
            setParagraphSpacingInfo(p, true, "0", "50", false, "0", "0", true,
                    "240", STLineSpacingRule.Enum.forString("auto"));
            setParagraphAlignInfo(p, ParagraphAlignment.CENTER,
                    TextAlignment.CENTER);
        }
    }

    public void setParagraphAlignInfo(XWPFParagraph p,
                                      ParagraphAlignment pAlign, TextAlignment valign) {
        p.setAlignment(pAlign);
        p.setVerticalAlignment(valign);
    }

    public void setParagraphSpacingInfo(XWPFParagraph p, boolean isSpace,
                                        String before, String after, boolean isPLine, String beforeLines,
                                        String afterLines, boolean isLine, String line,
                                        STLineSpacingRule.Enum lineValue) {
        CTPPr pPPr = null;
        if (p.getCTP() != null) {
            if (p.getCTP().getPPr() != null) {
                pPPr = p.getCTP().getPPr();
            } else {
                pPPr = p.getCTP().addNewPPr();
            }
        }
        CTSpacing pSpacing = pPPr.getSpacing() != null ? pPPr.getSpacing()
                : pPPr.addNewSpacing();
        if (isSpace) {
            // 段前磅数
            if (before != null) {
                pSpacing.setBefore(new BigInteger(before));
            }
            // 段后磅数
            if (after != null) {
                pSpacing.setAfter(new BigInteger(after));
            }
        }
        if (isPLine) {
            // 段前行数
            if (beforeLines != null) {
                pSpacing.setBeforeLines(new BigInteger(beforeLines));
            }
            // 段后行数
            if (afterLines != null) {
                pSpacing.setAfterLines(new BigInteger(afterLines));
            }
        }
        // 间距
        if (isLine) {
            if (line != null) {
                pSpacing.setLine(new BigInteger(line));
            }
            if (lineValue != null) {
                pSpacing.setLineRule(lineValue);
            }
        }
    }

    public void setParagraphTextStyleInfo(XWPFParagraph p, String content,
                                          String fontFamily, String colorVal, String fontSize,
                                          boolean isBlod, boolean isItalic, boolean isStrike,
                                          boolean isUnderLine, int underLineStyle, String underLineColor,
                                          boolean isHightLight, int hightLightValue) {
        XWPFRun pRun = null;
        if (p.getRuns() != null && p.getRuns().size() > 0) {
            pRun = p.getRuns().get(0);
        } else {
            pRun = p.createRun();
        }
        pRun.setText(content);

        CTRPr pRpr = null;
        if (pRun.getCTR() != null) {
            pRpr = pRun.getCTR().getRPr();
            if (pRpr == null) {
                pRpr = pRun.getCTR().addNewRPr();
            }
        }

        // 设置字体
        CTFonts fonts = pRpr.isSetRFonts() ? pRpr.getRFonts() : pRpr
                .addNewRFonts();
        fonts.setAscii(fontFamily);
        fonts.setEastAsia(fontFamily);
        fonts.setHAnsi(fontFamily);

        // 设置字体大小
        CTHpsMeasure sz = pRpr.isSetSz() ? pRpr.getSz() : pRpr.addNewSz();
        sz.setVal(new BigInteger(fontSize));

        CTHpsMeasure szCs = pRpr.isSetSzCs() ? pRpr.getSzCs() : pRpr
                .addNewSzCs();
        szCs.setVal(new BigInteger(fontSize));

        if (colorVal != null) {
            pRun.setColor(colorVal);
        }

        // 设置字体样式
        if (isBlod) {
            pRun.setBold(isBlod);
        }
        if (isItalic) {
            pRun.setItalic(isItalic);
        }
        if (isStrike) {
            pRun.setStrike(isStrike);
        }
        if (colorVal != null) {
            pRun.setColor(colorVal);
        }

        // 设置下划线样式
        if (isUnderLine) {
            CTUnderline u = pRpr.isSetU() ? pRpr.getU() : pRpr.addNewU();
            u.setVal(STUnderline.Enum.forInt(Math.abs(underLineStyle % 19)));
            if (underLineColor != null) {
                u.setColor(underLineColor);
            }
        }
        // 设置字突出显示文本
        if (isHightLight) {
            if (hightLightValue > 0 && hightLightValue < 17) {
                CTHighlight hightLight = pRpr.isSetHighlight() ? pRpr
                        .getHighlight() : pRpr.addNewHighlight();
                hightLight
                        .setVal(STHighlightColor.Enum.forInt(hightLightValue));
            }
        }
    }

    public void setParagraphShdStyle(XWPFParagraph p, boolean isShd,
                                     int shdValue, String shdColor) {
        XWPFRun pRun = null;
        if (p.getRuns() != null && p.getRuns().size() > 0) {
            pRun = p.getRuns().get(0);
        }
        if (pRun == null) {
            return;
        }
        CTPPr pPpr = null;
        if (pRun.getCTR() != null) {
            pPpr = p.getCTP().getPPr();
            if (pPpr == null) {
                pPpr = p.getCTP().addNewPPr();
            }
        }

        CTShd shd = pPpr.isSetShd() ? pPpr.getShd() : pPpr.addNewShd();
        if (shdValue > 0 && shdValue <= 38) {
            shd.setVal(STShd.Enum.forInt(shdValue));
        }
        if (shdColor != null) {
            shd.setColor(shdColor);
            // shd.setFill(shdColor);
            // shd.setColor("auto");
        }
    }

    public void setParagraphTextShdStyle(XWPFParagraph p, boolean isShd,
                                         int shdValue, String shdColor) {
        XWPFRun pRun = null;
        if (p.getRuns() != null && p.getRuns().size() > 0) {
            pRun = p.getRuns().get(0);
        }
        if (pRun == null) {
            return;
        }

        CTRPr pRpr = null;
        if (pRun.getCTR() != null) {
            pRpr = pRun.getCTR().getRPr();
            if (pRpr == null) {
                pRpr = pRun.getCTR().addNewRPr();
            }
        }

        if (isShd) {
            // 设置底纹
            CTShd shd = pRpr.isSetShd() ? pRpr.getShd() : pRpr.addNewShd();
            if (shdValue > 0 && shdValue <= 38) {
                shd.setVal(STShd.Enum.forInt(shdValue));
            }
            if (shdColor != null) {
                shd.setColor(shdColor);
                // shd.setColor("auto");
                // shd.setFill(shdColor);
            }
        }
    }

    public void testTableStyle(XWPFDocument doc) {
        String[] colors = new String[] { "CCA6EF", "DD999D", "4FCEF0",
                "7A7A7A", "F3C917", "FFA932", "C7B571", "535354", "5FD2F1",
                "A6DBFF", "FEF8B6" };
        Random random = new Random();
        List<String> columnList = new ArrayList<String>();
        columnList.add("序号");
        columnList.add("姓名信息|姓甚|名谁");
        columnList.add("名刺信息|籍贯|营生");
        columnList.add("字");
        XWPFTable table = doc.createTable(7, 6);
        setTableWidth(table, "8000");

        XWPFTableRow firstRow = table.getRow(0);
        XWPFTableRow secondRow = table.getRow(1);
        firstRow.setHeight(400);
        secondRow.setHeight(400);
        XWPFTableCell firstCell = null;
        XWPFTableCell secondCell = null;
        int firstCellIndex = 0;
        for (String str : columnList) {
            if (str.indexOf("|") == -1) {
                firstCell = firstRow.getCell(firstCellIndex);
                setCellText(firstCell, str, 1600, true, 6, "CCCCCC");
                firstCellIndex++;
            } else {
                String[] strArr = str.split("\\|");
                for (int i = 1; i < strArr.length; i++) {
                    firstCell = firstRow.getCell(firstCellIndex);
                    setCellText(firstCell, strArr[0], 1600, true, 6, "CCCCCC");
                    secondCell = secondRow.getCell(firstCellIndex);
                    setCellText(secondCell, strArr[i], 1600, true, 6, "CCCCCC");
                    firstCellIndex++;
                }
            }
        }

        // 合并行(跨列)
        firstCellIndex = 0;
        for (String str : columnList) {
            if (str.indexOf("|") == -1) {
                firstCellIndex++;
            } else {
                String[] strArr = str.split("\\|");
                mergeCellsHorizontal(table, 0, firstCellIndex, firstCellIndex
                        + strArr.length - 2);
                firstCellIndex += strArr.length - 1;
            }
        }

        // 合并列(跨行)
        firstCellIndex = 0;
        for (String str : columnList) {
            if (str.indexOf("|") == -1) {
                mergeCellsVertically(table, firstCellIndex, 0, 1);
                firstCellIndex++;
            } else {
                String[] strArr = str.split("\\|");
                firstCellIndex += strArr.length - 1;
            }
        }

        int k = 0;
        // 数据
        for (int i = 2; i < 7; i++) {
            firstRow = table.getRow(i);
            firstRow.setHeight(380);
            for (int j = 0; j < 6; j++) {
                firstCell = firstRow.getCell(j);
                setCellText(firstCell, "测试", 1600, true, k % 38,
                        colors[Math.abs(random.nextInt(colors.length))]);
                k++;
            }
        }
    }

    public void setCellText(XWPFTableCell cell, String text, int width,
                            boolean isShd, int shdValue, String shdColor) {
        CTTc cttc = cell.getCTTc();
        CTTcPr ctPr = cttc.isSetTcPr() ? cttc.getTcPr() : cttc.addNewTcPr();
        CTShd ctshd = ctPr.isSetShd() ? ctPr.getShd() : ctPr.addNewShd();
        ctPr.addNewTcW().setW(BigInteger.valueOf(width));
        if (isShd) {
            if (shdValue > 0 && shdValue <= 38) {
                ctshd.setVal(STShd.Enum.forInt(shdValue));
            }
            if (shdColor != null) {
                // ctshd.setFill(shdColor);
                // ctshd.setColor("auto");
                ctshd.setColor(shdColor);
            }
        }

        ctPr.addNewVAlign().setVal(STVerticalJc.CENTER);
        cttc.getPList().get(0).addNewPPr().addNewJc().setVal(STJc.CENTER);
        cell.setText(text);
    }

    /**
     * @Description: 跨列合并
     */
    public void mergeCellsHorizontal(XWPFTable table, int row, int fromCell,
                                     int toCell) {
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
            if (cellIndex == fromCell) {
                cell.getCTTc().addNewTcPr().addNewHMerge()
                        .setVal(STMerge.RESTART);
            } else {
                cell.getCTTc().addNewTcPr().addNewHMerge()
                        .setVal(STMerge.CONTINUE);
            }
        }
    }

    /**
     * @Description: 跨行合并
     */
    public void mergeCellsVertically(XWPFTable table, int col, int fromRow,
                                     int toRow) {
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
            if (rowIndex == fromRow) {
                cell.getCTTc().addNewTcPr().addNewVMerge()
                        .setVal(STMerge.RESTART);
            } else {
                cell.getCTTc().addNewTcPr().addNewVMerge()
                        .setVal(STMerge.CONTINUE);
            }
        }
    }

    public void setTableWidth(XWPFTable table, String width) {
        CTTbl ttbl = table.getCTTbl();
        CTTblPr tblPr = ttbl.getTblPr() == null ? ttbl.addNewTblPr() : ttbl
                .getTblPr();
        CTTblWidth tblWidth = tblPr.isSetTblW() ? tblPr.getTblW() : tblPr
                .addNewTblW();
        CTJc cTJc = tblPr.addNewJc();
        cTJc.setVal(STJc.Enum.forString("center"));
        tblWidth.setW(new BigInteger(width));
        tblWidth.setType(STTblWidth.DXA);
    }

    // 设置页面背景色
    public void setDocumentbackground(XWPFDocument document, String bgColor) {
        CTBackground bg = document.getDocument().isSetBackground() ? document
                .getDocument().getBackground() : document.getDocument()
                .addNewBackground();
        bg.setColor(bgColor);
    }

    public void addNewPage(XWPFDocument document, BreakType breakType) {
        XWPFParagraph xp = document.createParagraph();
        xp.createRun().addBreak(breakType);
    }

    public void saveDocument(XWPFDocument document, String savePath)
            throws Exception {
        FileOutputStream fos = new FileOutputStream(savePath);
        document.write(fos);
        fos.close();
    }
}
