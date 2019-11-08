package pers.li.pdf.pdfbox;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;


public class RemovePdf {

	public static void main(String[] args) throws Exception {
		File file = new File("merge.pdf");
		PDDocument document = PDDocument.load(file);

		int noOfPages = document.getNumberOfPages();
		System.out.println("total pages: " + noOfPages);

		// 删除第1页
		document.removePage(1);  // 页码索引从0开始算

		System.out.println("page removed");

		// 另存为新文档
		document.save("merge2.pdf");

		document.close();
	}
}
