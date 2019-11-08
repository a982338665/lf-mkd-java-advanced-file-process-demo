package pers.li.docx;

import java.io.*;

import org.apache.poi.xwpf.usermodel.*;

public class SpanTable {
	public static void main(String aaa[]) {
		System.out.println("This is Word To Document Class");

		File file = null;
		FileOutputStream fos = null;
		XWPFDocument document = null;
		XWPFParagraph para = null;
		XWPFRun run = null;
		try {
			// Create the first paragraph and set it's text.
			document = new XWPFDocument();
			para = document.createParagraph();

			para.setAlignment(ParagraphAlignment.CENTER);

			para.setSpacingAfter(100);

			para.setSpacingAfterLines(10);
			run = para.createRun();
			run.addBreak(); // similar to new line
			run.addBreak();

			XWPFTable table = document.createTable(4, 3);

			table.setRowBandSize(1);
			table.setWidth(1);
			table.setColBandSize(1);
			table.setCellMargins(1, 1, 100, 30);

			table.setStyleID("finest");

			table.getRow(1).getCell(1).setText("EXAMPLE OF TABLE");
			table.getRow(2).getCell(1).setText("fine");
			XWPFParagraph p1 = table.getRow(0).getCell(0).getParagraphs()
					.get(0);
			p1.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun r1 = p1.createRun();
			r1.setBold(true);
			r1.setText("Test Name");
			r1.setItalic(true);
			r1.setFontFamily("Courier");
			r1.setUnderline(UnderlinePatterns.DOT_DOT_DASH);
			r1.setTextPosition(100);

			// Locating the cell values
			table.getRow(0).getCell(1).setText("Value");
			table.getRow(0).getCell(2).setText("Normal Ranges");

			table.getRow(2).getCell(2).setText("numeric values");

			table.setWidth(120);

			file = new File("c:/temp/spantable.docx");
			if (file.exists())
				file.delete();

			FileOutputStream out = new FileOutputStream(file);
			document.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
