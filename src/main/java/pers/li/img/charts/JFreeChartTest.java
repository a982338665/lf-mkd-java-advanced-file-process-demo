package pers.li.img.charts;

import java.awt.Font;
import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class JFreeChartTest {

	public static void main(String[] args) {
		writeBar("c:/temp/bar.jpg"); // 柱状图
		writePie("c:/temp/pie.jpg"); // 饼图
		writeLine("c:/temp/line.jpg");// 折线图
	}
	
	public static StandardChartTheme getChineseTheme()
	{
		StandardChartTheme chineseTheme = new StandardChartTheme("CN");
		chineseTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20));
		chineseTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));
		chineseTheme.setLargeFont(new Font("宋书", Font.PLAIN, 15));
		return chineseTheme;
	}
	
	public static void writeBar(String fileName) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(11, "", "第一季度");
		dataset.addValue(41, "", "第二季度");
		dataset.addValue(51, "", "第三季度");
		dataset.addValue(4, "", "第四季度");

		// PlotOrientation.HORIZONTAL横向 PlotOrientation.VERTICAL 竖向
		// 引入中文主题样式
		ChartFactory.setChartTheme(getChineseTheme());
		JFreeChart chart = ChartFactory.createBarChart3D("柱状图", "2018年", "产品总量", dataset, PlotOrientation.VERTICAL,
				false, false, false);

		try {
			ChartUtilities.saveChartAsJPEG(new File(fileName), chart, 600, 300);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void writePie(String fileName) {
		DefaultPieDataset pds = new DefaultPieDataset();
		pds.setValue("C人数", 100);
		pds.setValue("C++人数", 200);
		pds.setValue("Java人数", 300);
		try {
			ChartFactory.setChartTheme(getChineseTheme());
			JFreeChart chart = ChartFactory.createPieChart("饼图", pds);
			
			ChartUtilities.saveChartAsJPEG(new File(fileName), chart, 600, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeLine(String fileName) {
		DefaultCategoryDataset lines = new DefaultCategoryDataset();
		//第一条线
		lines.addValue(100, "Java核心技术", "1月");
		lines.addValue(200, "Java核心技术", "2月");
		lines.addValue(400, "Java核心技术", "3月");
		lines.addValue(500, "Java核心技术", "4月");
		
		//第二条线
		lines.addValue(100, "Java核心技术(进阶)", "1月");
		lines.addValue(400, "Java核心技术(进阶)", "2月");
		lines.addValue(900, "Java核心技术(进阶)", "3月");
		try {
			ChartFactory.setChartTheme(getChineseTheme());
			JFreeChart chart = ChartFactory.createLineChart("折线图", "时间", "人数", lines);
			ChartUtilities.saveChartAsJPEG(new File(fileName), chart, 600, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
