package pers.li.pdf.itext;

import java.io.FileOutputStream;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfStyingExample {
	public static void main(String[] args) {
		Font blueFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new CMYKColor(255, 0, 0, 0));
		Font redFont = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, new CMYKColor(0, 255, 0, 0));
		Font yellowFont = FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(0, 0, 255, 0));
		Document document = new Document();
		try
		{
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("StylingExample.pdf"));
			document.open();
			//document.add(new Paragraph("Styling Example"));

			//Paragraph with color and font styles
			Paragraph paragraphOne = new Paragraph("Some colored paragraph text", redFont);
			document.add(paragraphOne);

			//Create chapter and sections
			Paragraph chapterTitle = new Paragraph("Chapter Title", yellowFont);
			Chapter chapter1 = new Chapter(chapterTitle, 1);
			chapter1.setNumberDepth(0);

			Paragraph sectionTitle = new Paragraph("Section Title", redFont);
			Section section1 = chapter1.addSection(sectionTitle);

			Paragraph sectionContent = new Paragraph("Section Text content", blueFont);
			section1.add(sectionContent);

			document.add(chapter1);

			document.close();
			writer.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
