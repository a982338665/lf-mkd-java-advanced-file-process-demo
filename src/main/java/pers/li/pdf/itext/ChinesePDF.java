/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pers.li.pdf.itext;

import java.awt.Color;
import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

/**
 *
 * @author kattering
 */
public class ChinesePDF {

	public static void main(String[] args) throws Exception {
		// 创建一个对中文字符集支持的基础字体
		BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font font = new Font(bfChinese);
		// 使用基础字体对象创建新字体对像，粗体12号红色字
		Document document = new Document(PageSize.A4); // 创建document对象
		PdfWriter.getInstance(document, new FileOutputStream("chinesePDF.pdf"));// 创建书写器
		document.open(); // 打开文档
		String context = "华东师范大学60年校庆！"; // 文档内容
		Paragraph paragraph = new Paragraph(context, font); // 创建段落，并设置字体
		paragraph.setAlignment(Paragraph.ALIGN_CENTER); // 设置段落居中
		document.add(paragraph); // 将段落添加到文档中
		document.close();
		
	}
}
