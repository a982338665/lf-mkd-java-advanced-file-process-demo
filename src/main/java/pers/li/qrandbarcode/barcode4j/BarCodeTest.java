package pers.li.qrandbarcode.barcode4j;

import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;


public class BarCodeTest {

	public static void main(String[] args) {
		String msg = "hhh";
		String path = "1dcode.png";
		generateFile(msg, path);
	}

	public static void generateFile(String msg, String path) {
		File file = new File(path);
		try {
			Code39Bean bean = new Code39Bean();
			//EAN13Bean bean = new EAN13Bean();

			// dpi����
			final int dpi = 150;
			// module���
			//bean.setModuleWidth(0.2);
			final double width = UnitConv.in2mm(2.0f / dpi);
			bean.setWideFactor(3);
			bean.setModuleWidth(width);
			bean.doQuietZone(false);

			String format = "image/png";
			// �������
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(new FileOutputStream(file), format, dpi,
					BufferedImage.TYPE_BYTE_BINARY, false, 0);

			// ����������
			bean.generateBarcode(canvas, msg);

			// ��������
			canvas.finish();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
