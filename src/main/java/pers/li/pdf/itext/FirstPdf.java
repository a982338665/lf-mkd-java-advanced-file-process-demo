/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pers.li.pdf.itext;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author kattering
 */
public class FirstPdf {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args)
			// TODO code application logic here
			throws Exception {
		Document document = new Document(PageSize.A4);// 创建document对象
		PdfWriter.getInstance(document, new FileOutputStream("firstPdf.pdf"));// 创建书写器
		document.open();// 打开文档
		String context = "This is my first PDF document!";// 文档内容
		Paragraph paragraph = new Paragraph(context);// 创建段落
		document.add(paragraph);// 将段落添加到文档中
		document.close();// 关闭文档
	}
}
