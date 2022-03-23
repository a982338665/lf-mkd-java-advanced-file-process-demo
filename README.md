# AdvancedFileProcessDemo 
#excel数据导出-xml https://www.cnblogs.com/fanshuyao/p/7516212.html

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
    8.可能会用于准考证打印，文档替换生成等
    
**4.表格文件：**
    
    1.xls/xlsx文件：excel生成，xlsx以xml为标准，主要研究
    2.csv文件：以逗号分隔的文本文件
    3.数据：
        sheet：行，列，单元格
    4.常见功能
        -解析
        -生成
    5.第三方包：poi较流行，早期使用jxl
        -免费：POI，jxl
        -windows：com4j
        -收费：Aspose
    6.主要类：
        ·XSSFWorkbook   整个文档对象
        ·XSSFSheet      单个sheet对象
        ·XSSFRow        一行对象
        ·XSSFCell       一个单元格对象
    7.csv文件：用来存储文本数据，可以文本文件打开，也可以excel打开
        广义csv文件：可以由空格、tab键，分等完成字段分隔
        第三方包：Apache Commons CSV
            -文档格式 CSVFormat
            -解析文档 CSVParser
            -一行记录 CSVRecord
            -写入文档 CSVPrinter

**5.pdf文件：**    
    
    1，介绍：
        1.Portable Document Format的简称，意思为 便携式文档格式
        2.Adobe公司发明的
        3.PostScript，用以生成和输出图形，在任何打印机上都可保证精确地颜色和准确地打印效果
        4.字形嵌入系统，可使字型随文件一起传输
        5.结构化的存储系统，绑定元素和任何相关内容到单个文件
        6.版本：一般为1.4+
    2.pdf处理
        1.常见功能
            -解析
            -生成
        2.第三方包：
            -免费：Apache PDFBox           pdfbox.apache.org
            -收费：iText                   itextpdf.com/en
            -XdocReport(将docx转化为pdf)   github.com/opensagres/xdocreport 
    3.Apache PDFBox :
        1.纯java类库
        2.主要功能：春节，提取文本，分隔,合并、删除
        3.主要类
            ·PDDocument         PDF文档对象
            ·PDFTextStripper    pdf文本对象
            ·PDFMergerUtility   合并工具
    4.XdocReport：
        1.将docx文档合并输出为其他数据格式（pdf、html）
        2.PdfConverter
        3.基于poi和iText完成


**6.excel导出方式比较对比：**
    
    xml导出方式最快 - 优选
    poi次之

**7.excel读取写入：**
 
    <!-- 引入poi，解析workbook视图 -->
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi</artifactId>
        <version>3.16</version>
    </dependency>
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi-ooxml</artifactId>
        <version>3.14</version>
    </dependency>
    <!-- 处理excel和上面功能是一样的-->
    <dependency>
        <groupId>net.sourceforge.jexcelapi</groupId>
        <artifactId>jxl</artifactId>
        <version>2.6.10</version>
    </dependency>
    
**8.pers.li.io**

    四个基础节点流：
    基础节点流均为抽象类，不能被实例化
        ·InputStream : 输入字节流, 也就是说它既属于输入流, 也属于字节流
        ·OutputStream: 输出字节流, 既属于输出流, 也属于字节流
        ·Reader: 输入字符流, 既属于输入流, 又属于字符流
        ·Writer: 输出字符流, 既属于输出流, 又属于字符流

    处理流：高级流，让程序员只关心这个该如何，让节点流与底层io设备文件交互
        deal
        1.对开发，使用处理流更简单
        2.执行效率更高
    —————————————————
              字节输入流        字节输出流         字符输入流      字符输出流
    抽象基类  InputStream       OutputStream        Reader          Writer
    访问文件  FileInputStream   FileOutputStream    FileReader      FileWriter
    数组      ByteArray--       ByteArray--         CharArray--     CharArray--
    管道      Piped--           Piped--             Piped--         Piped--
    字符串       -                -                 String--        String--
    缓冲流    Buffered--        Buffered--          Buffered--      Buffered--
    转换流       -                -                 InputStreamReader OutputStreamWriter
    对象流    Object--          Object--               -               -
    抽象基类  FilterInputStream Filter--             Filter--        Filter--
    打印流       -              PrintStream            -             PrintWriter
    推回输入流PushbackInputStream  -                 PushbackReader    -
    特殊流   DataInputStream    DataOutputStream       -               -
    
    ·对象流主要用来实现序列化
    ·管道流主要实现进程之间的通信功能
    ·缓冲流增加缓冲功能，提高输入输出效率
    ·增加缓冲功能后，要是用flush才可将缓冲区的内容写入实际的物理节点
    此上均为io包下的流：别的在JDK其他包下，暂不介绍
    ---------------------------------------------------------------------------
    字节流功能>字符流：
    1.计算机数据为二进制（字节流可以处理所有二进制文件）：
        ·文本文件难处理：可使用字符流
        ·二进制内容：字节流
    ——————————————————————————————————————
       /**
         * 节点流和处理流：
         * 节点流：直接和输入输出的数据节点进行连接，低级流
         * 处理流也是包装流，程序进行输入输出时不会接入数据节点，会通过处理流来访问不同的数据源
         * 通过使用处理流，java程序就不用理会输入输出节点是磁盘，网络还是其他，只要将这些节点流
         * 包装成处理流，就可以使用相同的输入输出代码来读写不同的输入输出设备数据
         * 处理流的好处：
         *  1.性能的提高：增加缓冲方式提高输入输出效率
         *  2.操作的便捷：处理流可能提供了一系列便捷的方法来一次输入或输出大批量内容
         * 流的水滴模型概念：
         *  1.input/reader  --读入 字节、字符流
         *  2.output/writer --写出 字节、字符流
    ——————————————————————————————————————  */
    断点续传：多线程的网络下载工具
        ·new RandomAccessFile("path","rw")
             raf.seek(300);
             raf.write("追加内容".getBytes());
    文件或目录搜索：
        ·Files.walkFileTree(Paths.get("D:\\git-266178"),new SimpleFileVisitor<Path>(){@Override两个遍历方法}
    高效监听文件变化：
        ·WatchService service=FileSystems.getDefault().newWatchService();
        ·Paths.get("C:/").register(...)
        ·while(true){WatchKey key=service.take();
                    for (WatchEvent<?> event:key.pollEvents()
                          ) {
                         System.out.println(event.context()+"文件发生了 "+event.kind()+" 事件");
                     }
                         //重设watchKey
                     boolean b=key.reset();
                     //如果重设失败退出监听
                     if(!b){
                         break;
                     }
                 }
    文件属性访问：详见D:\BaiduNetdiskDownload\1803短信\untitled\src\pers\li\io\nio\AttributeViewTest.java

**9.验证码：数字，字符，行为验证码等**
    
    ValidateCodeUtil 工具类查看
