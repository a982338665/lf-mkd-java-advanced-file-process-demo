package pers.li.pdf.itext;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class SetPDFAttributes {
	public static void main(String[] args) {
		Document document = new Document();
		try
		{
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("SetAttributeExample.pdf"));
			document.open();
			document.add(new Paragraph("Some content here"));

			//Set attributes here
			document.addAuthor("Lokesh Gupta");
			document.addCreationDate();
			document.addCreator("HowToDoInJava.com");
			document.addTitle("Set Attribute Example");
			document.addSubject("An example to show how attrinutes can be added to pdf files.");

			document.close();
			writer.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
