# AdvancedFileProcessDemo 

**1.图形图像处理：**
    
    1.图形：Graph
        矢量图，根据几何特性来画的，比如点，直线，弧线等
    2.图像:IMage        
        由像素点组成
        格式：jpg，png，bmp，svg，wmf，gif，tiff等
        颜色：RGB（red,green.blue）
    3.java图形关键类：
        1.图形：Graph
            -java.awt包 --> 开发桌面应用程序，较老，逐步废弃
            -java 2D库：关于图形库，被保留下来了
            -Colar,Stroke
        2.图像：Image
            -javax.imageio包 
            -ImageIO，BufferedImage,ImageReader,ImageWriter
    4.java图像关键类描述：
        1.java原生支持jpg,png,bmo,wbmp,gif
        2.javax.imageio.imageIO
            - 自动封装多种ImageReader和ImageWriter，读写图像文件
        3.java.awt.image.BUfferedImage,图像在内存中表示的类
            -getHeight 获取高度
            -getWidth 获取宽度
        4.图像文件读写，截取，合并
    5.验证码生成：
    6.统计图生成：
        -柱状图,饼图,折线图
        -java原生的Graphics 2D可以画，比较繁琐
        -基于jFreeChart可以快速实现统计图生成
            1.设定数据集
            2. 调用ChartFactory

**2.条形码和二维码：**

    1.条形码barcode：
        1.上世纪40年代发明
        2.通常代表一串数字或字母，每一位都含有特殊意义
        3.一般数据容量为30个数字/字母
        4.专门机构管理：中国物品编码中心
    2.二维码及二维条形码
        1.几何分布
        2.比一维码能存更多信息
        3.能够存储数字，字母，汉字，图片等信息
        4.字符集128个字符
        5.可存储几百到几十kb的字符
        6.抗损坏
    3.第三方包：
        1.Zxing
            -google出品
            -支持1d，2d的barcode
            -主要类：
                ·BitMatrix          位图矩阵
                ·MultiFormatWriter  位图编写器
                ·MatrixToImageWriter写入图片
        2.Barcode4j
             -http://barcode4j.sourceforge.net
             -纯java实现的条形码生成
             -只负责生成，不负责解析
             -主要类：
                ·BarcodeUtil
                ·BarcodeGenerator
                ·DefaultConfiguration
    4.总结：
        注意条形码种类
        高并发的时候，注意产生图片的速度
        api很多，需要多练习
       
**3.docx文件：**
    
    1.以Microsoft Office的doc为主要处理对象
    2.word2003之前都是doc，文档格式不公开
    3.word2007之后都是docx，遵循xml路线，文档格式公开
    4.docx为主要研究对象
        -文字样式
        -表格
        -图片
        -公式
    5.常见功能：
        -docx解析
        -docx生成：完全生成 模板加部分生成
    6.处理第三方库：
        -jacob，COM4j(两者都调用windows平台的com组件)
        -POI，doc4j，OpenOffice/libre Office SDK （全部免费）   
        -Aspose(收费的)
        -一些开源的OpenXMl包
    7.主要介绍：POI
        1.Apache出品，poi.apache.org
        2.可处理docx，xlsx，pptx，visio等office套件
        3.纯java工具包，无需第三方依赖
        4.主要类：
            XWPFDocument：整个文档对象
            XWPFParagraph：段落
            XWPFRun：一个片段
            XWPFPicture：图片
            XWPFTable：表格
    8.
    
