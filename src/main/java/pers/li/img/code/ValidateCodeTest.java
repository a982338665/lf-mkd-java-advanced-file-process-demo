package pers.li.img.code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;



public class ValidateCodeTest {

	//没有1 I L 0 o
	static char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T',
			'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9' };
	static int charNum = codeSequence.length;
	
	public static void main(String[] a) throws IOException
	{
		generateCode("c:/temp/code.jpg");
	}	
	

	public static void generateCode(String filePath) throws IOException {
		// 首先定义验证码图片框  
		int width = 80; // 验证码图片的宽度
		int height = 32; // 验证码图片的高度
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
        for (int i = 0; i < 16; i++) {   
            int x = random.nextInt(width);   
            int y = random.nextInt(height);   
            int xl = random.nextInt(12);   
            int yl = random.nextInt(12);   
            gd.drawLine(x, y, x + xl, y + yl);   
        }   
        
        
        //计算字的位置坐标
        int codeCount = 4; // 字符个数
    	int fontHeight; // 字体高度
    	int codeX; // 第一个字符的x坐标，因为后面的字符坐标依次递增，所以它们的x轴值是codeX的倍数
    	int codeY; // 验证字符的y坐标，因为并排所以值一样
    	// width-4 除去左右多余的位置，使验证码更加集中显示，减得越多越集中。
    	// codeCount+1 //等比分配显示的宽度，包括左右两边的空格
    	codeX = (width - 4) / (codeCount + 1); //第一个字母的起始位置    	
    	fontHeight = height - 10;  // height - 10 高度中间区域显示验证码
    	codeY = height - 7;
    			
    			
        // 创建字体，字体的大小应该根据图片的高度来定。   
        Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);           
        gd.setFont(font);   
        
        // 随机产生codeCount数字的验证码。   
        for (int i = 0; i < codeCount; i++) {   
            // 每次随机拿一个字母，赋予随机的颜色  
            String strRand = String.valueOf(codeSequence[random.nextInt(charNum)]);   
            int red = random.nextInt(255);   
            int green = random.nextInt(255);   
            int blue = random.nextInt(255);   
            gd.setColor(new Color(red,green,blue));   
            //把字放到图片上!!!
            gd.drawString(strRand, (i + 1) * codeX, codeY);              
        }   
        
        ImageIO.write(buffImg, "jpg", new File(filePath));             
	}
}
