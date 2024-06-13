package pers.li.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 *  //生成二维码
 */
public class QRCodeUtils {


    // //生成二维码
    public static BitMatrix generateQRCode(String content,int height ,int width) {
        Map<EncodeHintType, Object> hints = new HashMap<>();

        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        } catch (WriterException e) {
            e.printStackTrace();
            throw new RuntimeException("生成二维码失败！");
        }
        return bitMatrix;
    }
    public static void generateQRCode(String content, HttpServletResponse response,int height , int width) throws IOException {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        } catch (WriterException e) {
            e.printStackTrace();
            throw new RuntimeException("生成二维码失败！");
        }
        MatrixToImageWriter.writeToStream(bitMatrix,"png",response.getOutputStream());
    }

    public static String readQrCode(File file) {
        MultiFormatReader reader = new MultiFormatReader();
        try {
            BufferedImage image = ImageIO.read(file);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            Map<DecodeHintType, Object> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
            Result result = reader.decode(binaryBitmap, hints);
            System.out.println("解析结果:" + result.toString());
            System.out.println("二维码格式:" + result.getBarcodeFormat());
            System.out.println("二维码文本内容:" + result.getText());
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            BitMatrix bitMatrix = generateQRCode("https://www.baidu.com",300,300);
            Path path = new File("2dcode.png").toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, "png", path);//д��ָ��·����
            //ͨ通过网络写到浏览器
//            MatrixToImageWriter.writeToStream(bitMatrix,"png",response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String s = readQrCode(new File("2dcode.png"));
        System.err.println(s);
        //readQrCode(new File("2dcode.jpg"));
    }
}
