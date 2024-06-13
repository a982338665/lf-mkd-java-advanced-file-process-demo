package pers.li.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class BarCodeUtils {
	/**
	 * generateCode 根据code生成相应的一维码
	 *
	 * @param code   一维码内容
	 * @param width  图片宽度
	 * @param height 图片高度
	 */
	public static BitMatrix generateCode(String code, int width, int height) {
		//定义位图矩阵BitMatrix
		BitMatrix matrix = null;
		try {
			// 使用code_128格式进行编码生成100*25的条形码
			MultiFormatWriter writer = new MultiFormatWriter();

			matrix = writer.encode(code, BarcodeFormat.CODE_128, width, height, null);
			return matrix;
			//matrix = writer.encode(code,BarcodeFormat.EAN_13, width, height, null);
		} catch (WriterException e) {
			e.printStackTrace();
			throw new RuntimeException("生成一维码失败！");
		}
	}

	/**
	 * generateCode 根据code生成相应的一维码
	 *
	 * @param code   一维码内容
	 * @param width  图片宽度
	 * @param height 图片高度
	 */
	public static void generateCode(String code, HttpServletResponse response, int width, int height) throws IOException {
		//定义位图矩阵BitMatrix
		BitMatrix matrix = null;
		try {
			// 使用code_128格式进行编码生成100*25的条形码
			MultiFormatWriter writer = new MultiFormatWriter();

			matrix = writer.encode(code, BarcodeFormat.CODE_128, width, height, null);
			//matrix = writer.encode(code,BarcodeFormat.EAN_13, width, height, null);
		} catch (WriterException e) {
			e.printStackTrace();
			throw new RuntimeException("生成一维码失败！");
		}
		MatrixToImageWriter.writeToStream(matrix, "png", response.getOutputStream());
	}

	/**
	 * readCode 读取一张一维码图片
	 *
	 * @param file 一维码图片名字
	 */
	public static void readCode(File file) {
		try {
			BufferedImage image = ImageIO.read(file);
			if (image == null) {
				return;
			}
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

			Map<DecodeHintType, Object> hints = new HashMap<>();
			hints.put(DecodeHintType.CHARACTER_SET, "GBK");
			hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
			hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);

			Result result = new MultiFormatReader().decode(bitmap, hints);
			System.out.println("条形码内容: " + result.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		BitMatrix bitMatrix = generateCode("GYS_FH_2024041900005", 500, 250);
		Path path = new File("1dcode.png").toPath();
		MatrixToImageWriter.writeToPath(bitMatrix, "png", path);
		readCode(new File("1dcode.png"));
	}
}
