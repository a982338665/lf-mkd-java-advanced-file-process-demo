package pers.li.docx;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject;

public class TextRead {

	public static void main(String[] args) throws Exception {
		readDocx();
	}
	
	public static void readDocx() throws Exception {
		InputStream is;
		is = new FileInputStream("test.docx");
		XWPFDocument xwpf = new XWPFDocument(is);
		
		List<IBodyElement> ibs= xwpf.getBodyElements();
		for(IBodyElement ib:ibs)
    	{
			BodyElementType bet = ib.getElementType();
			if(bet== BodyElementType.TABLE)
    		{
				//表格
				System.out.println("table" + ib.getPart());
    		}
			else
			{				
				//段落
				XWPFParagraph para = (XWPFParagraph) ib;
				System.out.println("It is a new paragraph....The indention is " 
				         + para.getFirstLineIndent() + "," + para.getIndentationFirstLine() );
				//System.out.println(para.getCTP().xmlText());
				
				List<XWPFRun> res = para.getRuns();
				//System.out.println("run");
				if(res.size()<=0)
				{
					System.out.println("empty line");
				}
				for(XWPFRun re: res)
				{							
					if(null == re.text()||re.text().length()<=0)
					{
						if(re.getEmbeddedPictures().size()>0)
						{
							System.out.println("image***" + re.getEmbeddedPictures().size());							
						} else
						{
							System.out.println("objects:" + re.getCTR().getObjectList().size());
							if(re.getCTR().xmlText().indexOf("instrText") > 0) {
								System.out.println("there is an equation field");
							}
							else
							{
								//System.out.println(re.getCTR().xmlText());
							}												
						}
					}
					else
					{						
						System.out.println("==="+ re.getCharacterSpacing() + re.text());
					}
				}				
			}
    	}		
		is.close();
	}	
}
