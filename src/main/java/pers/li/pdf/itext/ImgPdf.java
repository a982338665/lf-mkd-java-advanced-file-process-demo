/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pers.li.pdf.itext;

import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author kattering
 */
public class ImgPdf {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) throws Exception {
		Document document = new Document();// 创建document对象
		Image img = Image.getInstance("leehom.jpg");// 创建Image图片对象
		img.scalePercent(40);// 缩放到百分之40大小
		img.setRotationDegrees(180);// 旋转180度
		img.setAlignment(Image.ALIGN_CENTER);// 剧中显示
		PdfWriter.getInstance(document, new FileOutputStream("imgPdf.pdf"));
		document.open();// 打开document文档对象
		document.add(img);// 将图片添加到文档中
		document.close();// 关闭文档
	}
}
