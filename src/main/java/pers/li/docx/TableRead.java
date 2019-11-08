package pers.li.docx;

/**
 * 本类完成docx的表格内容读取
 */
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;


public class TableRead {
	public static void main(String[] args) throws Exception {
		testTable();
	}
	
	public static void testTable() throws Exception {
		InputStream is = new FileInputStream("simple2.docx");
		XWPFDocument xwpf = new XWPFDocument(is);
		List<XWPFParagraph> paras = xwpf.getParagraphs();
		//List<POIXMLDocumentPart> pdps = xwpf.getRelations();
		
		List<IBodyElement> ibs= xwpf.getBodyElements();
		for(IBodyElement ib:ibs)
    	{
			BodyElementType bet = ib.getElementType();
			if(bet== BodyElementType.TABLE)
    		{
				//表格
				System.out.println("table" + ib.getPart());
				XWPFTable table = (XWPFTable) ib;
	    		List<XWPFTableRow> rows=table.getRows(); 
	    		 //读取每一行数据
	    		for (int i = 0; i < rows.size(); i++) {
	    			XWPFTableRow  row = rows.get(i);
	    			//读取每一列数据
		    	    List<XWPFTableCell> cells = row.getTableCells(); 
		    	    for (int j = 0; j < cells.size(); j++) {
		    	    	XWPFTableCell cell=cells.get(j);
		    	    	System.out.println(cell.getText());
		    	    	List<XWPFParagraph> cps = cell.getParagraphs();
		    	    	System.out.println(cps.size());
					}
	    		}
    		}
			else
			{				
				//段落
				XWPFParagraph para = (XWPFParagraph) ib;
				System.out.println("It is a new paragraph....The indention is " 
				   + para.getFirstLineIndent() + "," + para.getIndentationFirstLine() + "," 
				   + para.getIndentationHanging()+"," + para.getIndentationLeft() + "," 
				   + para.getIndentationRight() + "," + para.getIndentFromLeft() + ","
				   + para.getIndentFromRight()+"," + para.getAlignment().getValue());
				
				//System.out.println(para.getAlignment());
				//System.out.println(para.getRuns().size());

				List<XWPFRun> res = para.getRuns();
				System.out.println("run");
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
							
						}
						else
						{
							System.out.println("objects:" + re.getCTR().getObjectList().size());
							System.out.println(re.getCTR().xmlText());
												
						}
					}
					else
					{
						System.out.println("===" + re.text());
					}
				}
				
			}
    	}		
		is.close();
	}
}
