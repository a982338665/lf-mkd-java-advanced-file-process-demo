package pers.li.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 条形码生成工具类
 */
public class BarCodeUtil {

    /**
     * generateCode 根据code生成相应的一维码
     *
     * @param code   一维码内容
     * @param width  图片宽度
     * @param height 图片高度
     */
    public static BufferedImage generateCode(String code, int width, int height) {
        //定义位图矩阵BitMatrix
        BitMatrix matrix = null;
        try {
            // 使用code_128格式进行编码生成100*25的条形码
            MultiFormatWriter writer = new MultiFormatWriter();

            matrix = writer.encode(code, BarcodeFormat.CODE_128, width, height, null);
            //matrix = writer.encode(code,BarcodeFormat.EAN_13, width, height, null);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(matrix);
        return bufferedImage;
    }

    /**
     * readCode 读取一张一维码图片
     *
     * @param file 一维码图片名字
     */
    public static String readCode(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            if (image == null) {
                return null;
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            Map<DecodeHintType, Object> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, "GBK");
            hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
            hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);

            Result result = new MultiFormatReader().decode(bitmap, hints);
            System.out.println("条形码内容: " + result.getText());
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        BufferedImage bufferedImage = generateCode("123456789012", 500, 250);
        //将位图矩阵BitMatrix保存为图片
        try (FileOutputStream outStream = new FileOutputStream(new File("1dcode.png"))) {
            //一维码保存为文件
            ImageIO.write(bufferedImage, "png", outStream);
            //通过网络写到浏览器
            //ImageIO.write(buffImg, "jpg", response.getOutputStream());
            outStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //一维码内容解析
        String s = readCode(new File("1dcode.png"));

    }
}
