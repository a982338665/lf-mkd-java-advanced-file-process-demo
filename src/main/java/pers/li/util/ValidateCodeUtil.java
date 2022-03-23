package pers.li.util;

import com.sun.org.apache.regexp.internal.RE;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;


/**
 * 验证码生成工具类
 * 可以通过：response.getOutputStream()响应到浏览器
··· * @author luofeng
 */
@SuppressWarnings("AlibabaAvoidCommentBehindStatement")
public class ValidateCodeUtil {

    public static Random random = new Random();

    public static void main(String[] a) throws IOException {
        BufferedImage buffImg = generateCode(60, 200, 4,5);
        ImageIO.write(buffImg, "jpg", new File("valCode.jpg"));
        System.err.println("生成成功!");
        //通过网络写到浏览器
        //ImageIO.write(buffImg, "jpg", response.getOutputStream());
    }

    /**
     * 验证码生成并返回
     * @param height
     * @param width
     * @param type
     * @param codeCount
     * @param outputStream
     * @throws IOException
     */
    public static void generateAndResponse(int height, int width, int type, int codeCount, OutputStream outputStream) throws IOException {
        BufferedImage buffImg = generateCode(height, width, type,codeCount);
        ImageIO.write(buffImg, "jpg", outputStream);
    }

    /**
     * 验证码生成
     * @param height    图片高度
     * @param width     图片宽度
     * @param type      图片类型
     * @param codeCount 字符个数
     * @return          生成的校验字符
     * @throws IOException
     */
    public static BufferedImage generateCode(int height, int width, int type,int codeCount) throws IOException {
        // 首先定义验证码图片框
        if (width == 0) {
            // 验证码图片的宽度
            width = 80;
        }
        if (height == 0) {
            height = 32; /* 验证码图片的高度 */
        }
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //定义图片上的图形和干扰线
        Graphics2D gd = buffImg.createGraphics();
        gd.setColor(Color.LIGHT_GRAY);   // 将图像填充为浅灰色
        gd.fillRect(0, 0, width, height);
        gd.setColor(Color.BLACK);        // 画边框。
        gd.drawRect(0, 0, width - 1, height - 1);
        // 随机产生16条灰色干扰线，使图像中的认证码不易识别
        gd.setColor(Color.gray);
        // 创建一个随机数生成器类   用于随机产生干扰线
        Random random = new Random();
        for (int i = 0; i < 70; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gd.drawLine(x, y, x + xl, y + yl);
        }
        //没有1 I L 0 o
        //计算字的位置坐标
        // 字符个数
        int fontHeight; // 字体高度
        int codeX; // 第一个字符的x坐标，因为后面的字符坐标依次递增，所以它们的x轴值是codeX的倍数
        int codeY; // 验证字符的y坐标，因为并排所以值一样
        // width-4 除去左右多余的位置，使验证码更加集中显示，减得越多越集中。
        // codeCount+1 //等比分配显示的宽度，包括左右两边的空格
        codeX = (width - 4) / (codeCount + 1); //第一个字母的起始位置
        fontHeight = height - 10;  // height - 10 高度中间区域显示验证码
        codeY = height /3*2;


        // 创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
        gd.setFont(font);
        String s = null;
        switch (type) {
            case 1:
                s = generateCodeOne(codeX,codeY,codeCount, gd);
                break;
            case 2:
                s = generateCodeTwo(codeX,codeY,codeCount, gd);
                break;
            case 3:
                s = generateCode3(codeX,codeY,codeCount, gd);
                break;
            case 4:
                s = generateCode4(codeX,codeY,codeCount, gd);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        System.err.println("text:" + s);
        gd.dispose();
        return buffImg;
    }

    private static String generateCodeOne(int codeX, int codeY,  int codeCount ,Graphics2D gd) {
        char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9', '李'};
        int charNum = codeSequence.length;
        StringBuffer text = new StringBuffer("");
        // 随机产生codeCount数字的验证码。
        for (int i = 0; i < codeCount; i++) {
            // 每次随机拿一个字母，赋予随机的颜色
            String strRand = String.valueOf(codeSequence[random.nextInt(charNum)]);
            commonSet(codeX, codeY, gd, i, strRand);
            text.append(strRand);
        }

        return text.toString();
    }

    private static void commonSet(int codeX, int codeY, Graphics2D gd, int i, String strRand) {
        int red = random.nextInt(255);
        int green = random.nextInt(255);
        int blue = random.nextInt(255);
        gd.setColor(new Color(red, red, blue));
        //把字放到图片上!!!
        gd.drawString(strRand, (i + 1) * codeX, codeY);
    }

    private static String generateCodeTwo(int codeX, int codeY,  int codeCount ,Graphics2D g) {
        String[] letter = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z"};
        StringBuffer text = new StringBuffer("");
        for (int i = 0; i < codeCount; i++) {
            String tempRand = "";
            if (random.nextBoolean()) {
                tempRand = String.valueOf(random.nextInt(10));
            } else {
                tempRand = letter[random.nextInt(25)];
                /* if (random.nextBoolean()) {// 随机将该字母变成小写
                     tempRand = tempRand.toLowerCase();
                 }  */
            }
            text.append(tempRand);
            commonSet(codeX, codeY, g, i, tempRand);
        }
        return text.toString();
    }
    private static String generateCode3(int codeX, int codeY,  int codeCount ,Graphics2D g) {
        StringBuffer text = new StringBuffer("");
        for (int i=0;i<codeCount;i++)
        {
            int itmp = random.nextInt(26) + 65;
            char ctmp = (char)itmp;
            text.append(ctmp);
            commonSet(codeX, codeY, g, i, String.valueOf(ctmp));
        }
        return text.toString();
    }
    private static String generateCode4(int codeX, int codeY,  int codeCount ,Graphics2D g) {
        String sRand="";
        String[] cn = { "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖", "拾" };
        int x = random.nextInt(10) + 1;
        int y = random.nextInt(30);
        int red = random.nextInt(255);
        int green = random.nextInt(255);
        int blue = random.nextInt(255);
       /*  //把字放到图片上!!!
        gd.drawString(strRand, (i + 1) * codeX, codeY);*/
        sRand = String.valueOf(y);
        g.setFont(new Font("楷体", Font.PLAIN, 25));// 设定字体
        g.setColor(new Color(red, green, blue));
        g.drawString(cn[x - 1], 1 * codeX, codeY);
        g.drawString("+", 2 * codeX, codeY);
        g.drawString("？", 3 * codeX, codeY);
        g.drawString("=", 4 * codeX, codeY);
        g.drawString(String.valueOf(x + y), 5 * codeX, codeY);
        return sRand;
    }



}
