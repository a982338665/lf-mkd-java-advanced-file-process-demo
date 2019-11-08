/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pers.li.pdf.itext;

import java.awt.Color;
import java.io.FileOutputStream;

import com.itextpdf.text.pdf.*;
import com.itextpdf.text.*;

/**
 *
 * @author kattering
 */
public class TablePdf {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) throws Exception {
		String[][] context = { { "第一节", "j2ee", "基础日语", "高数", "专业英语", " " }, { "第二节", "高数", "计网", " ", "基础日语", "高数" },
				{ "第三节", "毛概", "大学英语", "系列讲座", "数学思维", "马原" }, { "第四节", "军理", "嵌入式", "体育", "实践", "计网" } };
		// 创建一个对中文字符集支持的基础字体
		BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		// 使用基础字体对象创建新字体对像，粗体12号红色字
		Font font = new Font(bfChinese);

		Document document = new Document(PageSize.A4); // 创建document对象
		PdfWriter.getInstance(document, new FileOutputStream("tablePDF.pdf"));// 创建书写器
		document.open(); // 打开文档
		String title = "103班课表"; // 文档内容
		Paragraph paragraph = new Paragraph(title, font); // 创建段落，并设置字体
		paragraph.setAlignment(Paragraph.ALIGN_CENTER); // 设置段落居中
		document.add(paragraph); // 将段落添加到文档中
		PdfPTable table = new PdfPTable(6); // 建立一个6列的空白表格对象
		table.setSpacingBefore(30f); // 设置表格上面空白宽度
		String[] tableTitle = { "", "星期一", "星期二", "星期三", "星期四", "星期五" };

		for (int i = 0; i < tableTitle.length; i++) {
			paragraph = new Paragraph(tableTitle[i], new Font(bfChinese, 10, Font.BOLD));
			PdfPCell cell = new PdfPCell(paragraph); // 建立一个单元格
			cell.setHorizontalAlignment(Element.ALIGN_CENTER); // 设置内容水平居中显示
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
			table.addCell(cell);
		}
		for (int i = 0; i < context.length; i++) {
			for (int j = 0; j < context[i].length; j++) {
				PdfPCell cell = new PdfPCell(new Paragraph(context[i][j], new Font(bfChinese, 10))); // 建立一个单元格
				cell.setHorizontalAlignment(Element.ALIGN_CENTER); // 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
			}
		}
		document.add(table);
		document.close();
	}
}
