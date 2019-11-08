package pers.li.docx;

/**
 * 本类 完成docx的图片读取工作
 */
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;

public class ImageRead {


    public static void imageRead() throws IOException, InvalidFormatException {
        File docFile = new File("simple.docx");

        XWPFDocument doc = new XWPFDocument(OPCPackage.openOrCreate(docFile));
        
        int i = 0;
        for (XWPFParagraph p : doc.getParagraphs()) {
            for (XWPFRun run : p.getRuns()) {
            	System.out.println("a new run");
                for (XWPFPicture pic : run.getEmbeddedPictures()) {
                	System.out.println(pic.getCTPicture().xmlText());
                	//image EMU(English Metric Unit)
                	System.out.println(pic.getCTPicture().getSpPr().getXfrm().getExt().getCx());
                	System.out.println(pic.getCTPicture().getSpPr().getXfrm().getExt().getCy());
                	//image 显示大小  以厘米为单位
                	System.out.println(pic.getCTPicture().getSpPr().getXfrm().getExt().getCx()/360000.0);
                	System.out.println(pic.getCTPicture().getSpPr().getXfrm().getExt().getCy()/360000.0);
                	int type = pic.getPictureData().getPictureType();
                    byte [] img = pic.getPictureData().getData();
                    BufferedImage bufferedImage= ImageRead.byteArrayToImage(img);
                    System.out.println(bufferedImage.getWidth());
                    System.out.println(bufferedImage.getHeight());
                    String extension = "";
                    switch(type)
                    {
	                    case Document.PICTURE_TYPE_EMF: extension = ".emf";
	                    								break;
	                    case Document.PICTURE_TYPE_WMF: extension = ".wmf";
							break;
	                    case Document.PICTURE_TYPE_PICT: extension = ".pic";
							break;
	                    case Document.PICTURE_TYPE_PNG: extension = ".png";
							break;
	                    case Document.PICTURE_TYPE_DIB: extension = ".dib";
							break;
							default: extension = ".jpg";
                    }
                    //outputFile = new File ( );                
                    //BufferedImage image = ImageIO.read(new File(img));
                    //ImageIO.write(image , "png", outputfile);
                    FileOutputStream fos = new FileOutputStream("test" + i + extension);
                    fos.write(img);
                    fos.close();
                    i++;
                }
            }
        }
    }
    public static BufferedImage  byteArrayToImage(byte[] bytes){  
        BufferedImage bufferedImage=null;
        try {
            InputStream inputStream = new ByteArrayInputStream(bytes);
            bufferedImage = ImageIO.read(inputStream);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return bufferedImage;
}
    public static void main(String[] args) throws Exception {
    	imageRead();
    }
}
