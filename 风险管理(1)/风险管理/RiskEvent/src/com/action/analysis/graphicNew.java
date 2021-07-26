package com.action.analysis;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.action.riskStatistics.info;
import com.entity.ReportViewNew;
import com.entity.RiAnalysis;
import com.entity.RuleFile;

import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.AbstractCategoryItemRenderer;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.util.ShadowGenerator;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.lowagie.text.Cell;
import com.model.ReportView;
import com.model.Users;
import com.sun.image.codec.jpeg.*;

@SuppressWarnings("unused")
public class graphicNew extends HttpServlet{

	private static int _heightA;

	public static void drawTable(int width, int height,
			List<ReportViewNew> lists) throws IOException {
		BufferedImage bi = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		g.setBackground(Color.white);
		g.clearRect(0, 0, width, height);
		g.setColor(Color.black);
		Stroke stroke = new BasicStroke(2.0f);
		g.setStroke(stroke);
		// start draw:
		g.drawLine(60, height - 50, width - 150, height - 50);
		g.drawLine(width - 150, height - 50, width - 160, height - 55);
		g.drawLine(width - 150, height - 50, width - 160, height - 45);
		g.setColor(Color.black);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setFont(new Font("黑体", Font.BOLD, 17));
		g.drawString("影响程度", width - 145, height - 50);
		g.drawLine(60, height - 50, 60, 50);
		g.drawLine(60, 50, 55, 60);
		g.drawLine(60, 50, 65, 60);
		g.setColor(Color.black);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setFont(new Font("黑体", Font.BOLD, 17));
		g.drawString("可能性", 70, 50);
		g.drawString("极低", 100, height - 30);
		g.drawString("低", 230, height - 30);
		g.drawString("中等", 380, height - 30);
		g.drawString("高", 500, height - 30);
		g.drawString("极高", 640, height - 30);
		String[][] infos = { { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" } };
		for (int n = 0; n < lists.size(); n++) {
			if (lists.get(n).getRiskPro() > 5 || lists.get(n).getRiskPro() < 1)
				continue;// 风险可能性的值在1到5之间
			if (lists.get(n).getRiskValuex() > 5
					|| lists.get(n).getRiskValuex() < 1)
				continue;// 风险评定的值在1到5之间
			infos[lists.get(n).getRiskPro() - 1][lists.get(n).getRiskValuex() - 1] += "("
					+ String.valueOf(lists.get(n).getRiskQueue()) + ")*";
		}
		// end draw:
		int rowheight_max = height - 50;
		for (int i = 0; i < 5; i++) {
			int temp_height = cal_Max_height(infos[i]) * 20;
			rowheight_max -= temp_height + 100;
			for (int j = 0; j < 5; j++) {
				/*
				 * 计算最大高度应该是多少
				 */
				g.drawRect(60 + j * 130, rowheight_max, 130, temp_height + 100);
				/*
				 * 开始遍历lists 定义a[5][5]数据保存25个节点数据
				 */
				String[] sequence = infos[i][j].split("\\*");
				int count = sequence.length;
				int start_x = 60 + j * 130 + 1;
				int start_y = rowheight_max + 20;
				int x = start_x;
				int y = start_y;
				for (int n = 0; n < count; n++) {
					if ((n) % 3 == 0) {
						g.drawString(sequence[n], start_x, y + 20);
						y = y + 20;
						x = start_x + 41;
					} else {
						g.drawString(sequence[n], x, y);
						x = x + 41;
					}
				}
			}
			String Y_str = "";
			if (0 == i)
				Y_str = "极低";
			else if (1 == i)
				Y_str = "低";
			else if (2 == i)
				Y_str = "中等";
			else if (3 == i)
				Y_str = "高";
			else {
				Y_str = "极高";
			}
			g.drawString(Y_str, 8, rowheight_max + 50 + temp_height / 2);
		}
		g.dispose();
		bi.flush();
		// encode:
		// OutputStream out=
		// ServletActionContext.getResponse().getOutputStream();
		String savePath = ServletActionContext.getServletContext().getRealPath(
				"/upload/reportImg.jpg");

		FileOutputStream out = new FileOutputStream(savePath);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);
		param.setQuality(1.0f, false);
		encoder.setJPEGEncodeParam(param);
		try {
			encoder.encode(bi);

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}


	public static String drawTableShadow(int width, List<ReportViewNew> lists)
			throws IOException {
		if (!lists.isEmpty()) {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Users us = (Users) session.getAttribute("loginUser");
			String userId = us.getUserId();
			String[][] infos = { { "", "", "", "", "" },
					{ "", "", "", "", "" }, { "", "", "", "", "" },
					{ "", "", "", "", "" }, { "", "", "", "", "" } };
			for (int n = 0; n < lists.size(); n++) {
				if (lists.get(n).getRiskPro() > 5
						|| lists.get(n).getRiskPro() < 1)
					continue;// 风险可能性的值在1到5之间
				if (lists.get(n).getRiskValuex() > 5
						|| lists.get(n).getRiskValuex() < 1)
					continue;// 风险评定的值在1到5之间

				infos[lists.get(n).getRiskPro() - 1][lists.get(n)
						.getRiskValuex() - 1] += "("
						+ String.valueOf(lists.get(n).getRiskQueue()) + ")*";
			}
			int height = 150;
			for (int i = 0; i < 5; i++) {
				// 根据每行的所有单元格中最大风险数来动态分配高度
				height += cal_Max_height(infos[i]);
			}

			BufferedImage bi = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bi.createGraphics();
			g.setBackground(Color.white);
			g.clearRect(0, 0, width, height);
			g.setColor(Color.black);
			// 定义线条的粗细
			Stroke stroke = new BasicStroke(2.0f);
			g.setStroke(stroke);
			// start draw:
			// 画横轴坐标线
			g.drawLine(60, height - 50, width - 150, height - 50);
			// 画横轴箭头线
			g.drawLine(width - 150, height - 50, width - 160, height - 55);
			g.drawLine(width - 150, height - 50, width - 160, height - 45);
			// 设置线的颜色
			g.setColor(Color.black);
			// 对线条抗锯齿
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g.setFont(new Font("宋体", Font.BOLD, 25));
			g.drawString("影响程度", width - 145, height - 50);
			// 画纵轴坐标线
			g.drawLine(60, height - 50, 60, 50);
			// 画纵轴箭头线
			g.drawLine(60, 50, 55, 60);
			g.drawLine(60, 50, 65, 60);
			g.setColor(Color.black);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g.drawString("可能性", 70, 50);
			g.setFont(new Font("宋体", Font.PLAIN, 20));
			g.drawString("极低", 100, height - 30);
			g.drawString("低", 230, height - 30);
			g.drawString("中等", 380, height - 30);
			g.drawString("高", 500, height - 30);
			g.drawString("极高", 640, height - 30);
			// g.drawString("极高", 760, height - 30);

			// end draw:
			int rowheight_max = height - 50;
			for (int i = 0; i < 5; i++) {
				// 根据每行的所有单元格中最大风险数来动态分配高度
				int temp_height = cal_Max_height(infos[i]);
				rowheight_max -= temp_height;
				for (int j = 0; j < 5; j++) {
					/*
					 * 计算最大高度应该是多少
					 */
					g.drawRect(60 + j * 130, rowheight_max, 130, temp_height);
					/*
					 * 绘制阴影区
					 */
					if (1 >= i && 1 >= j) {
						g.setBackground(new Color(198, 249, 184));// 绿色
						g.clearRect(60 + j * 130, rowheight_max, 130,
								temp_height);
						Rectangle2D rect = new Rectangle2D.Float(60 + j * 130,
								rowheight_max, 130, temp_height);
						g.draw(rect);
					} else if (3 <= i && 3 <= j) {
						g.setBackground(new Color(248, 195, 195));// 红色
						g.clearRect(60 + j * 130, rowheight_max, 130,
								temp_height);
						Rectangle2D rect = new Rectangle2D.Float(60 + j * 130,
								rowheight_max, 130, temp_height);
						g.draw(rect);
					} else {
						g.setBackground(new Color(253, 236, 182));// 黄色
						g.clearRect(60 + j * 130, rowheight_max, 130,
								temp_height);
						Rectangle2D rect = new Rectangle2D.Float(60 + j * 130,
								rowheight_max, 130, temp_height);
						g.draw(rect);
					}
					/*
					 * 绘制区域标识区
					 */
					if (1 == i && 1 == j) {
						_heightA = rowheight_max;
						// A区域的标识生成在循环外
					} else if (0 == i && 4 == j) {

						g.setBackground(new Color(253, 227, 142));
						g.drawLine(60 + j * 130 + 80, rowheight_max + 50,
								width - 150, rowheight_max + 50);
						g.drawLine(width - 150, rowheight_max + 50,
								width - 160, rowheight_max + 45);
						g.drawLine(width - 150, rowheight_max + 50,
								width - 160, rowheight_max + 55);
						g.setBackground(new Color(253, 236, 182));// 黄色
						g.clearRect(width - 150, rowheight_max + 25, 80, 50);
						Rectangle2D rect = new Rectangle2D.Float(width - 150,
								rowheight_max + 25, 80, 50);
						g.draw(rect);
						g.setFont(new Font("宋体", Font.BOLD, 25));
						g.drawString("B区域", width - 140, rowheight_max + 50);
					} else if (4 == i && 4 == j) {
						// g.setBackground(new Color(246,144,158));
						g.drawLine(60 + j * 130 + 80, rowheight_max + 50,
								width - 150, rowheight_max + 50);
						g.drawLine(width - 150, rowheight_max + 50,
								width - 160, rowheight_max + 45);
						g.drawLine(width - 150, rowheight_max + 50,
								width - 160, rowheight_max + 55);
						g.setBackground(new Color(248, 195, 195));// 红色
						g.clearRect(width - 150, rowheight_max + 25, 80, 50);
						Rectangle2D rect = new Rectangle2D.Float(width - 150,
								rowheight_max + 25, 80, 50);
						g.draw(rect);
						g.setFont(new Font("宋体", Font.BOLD, 25));
						g.drawString("C区域", width - 140, rowheight_max + 50);
					}
					/*
					 * 开始遍历lists 定义a[5][5]数据保存25个节点数据
					 */
					String[] sequence = infos[i][j].split("\\*");
					int count = sequence.length;
					int start_x = 60 + j * 130 + 1;
					int start_y = rowheight_max;
					int x = start_x;
					int y = start_y;
					for (int n = 0; n < count; n++) {
						if ((n) % 3 == 0) {
							g.setFont(new Font("宋体", Font.BOLD, 17));
							g.drawString(sequence[n], start_x, y + 20);
							y = y + 20;
							x = start_x + 41;
						} else {
							g.setFont(new Font("宋体", Font.BOLD, 17));
							g.drawString(sequence[n], x, y);
							x = x + 41;
						}
					}
				}
				String Y_str = "";
				if (0 == i)
					Y_str = "极低";
				else if (1 == i)
					Y_str = "低";
				else if (2 == i)
					Y_str = "中等";
				else if (3 == i)
					Y_str = "高";
				else if (4 == i)
					Y_str = "极高";
				g.setFont(new Font("宋体", Font.PLAIN, 20));
				g.drawString(Y_str, 8, rowheight_max + 10 + temp_height / 2);
			}

			g.drawLine(60 + 130 + 80, _heightA + 50, width - 150, _heightA - 50);
			g.drawLine(width - 150, _heightA - 50, width - 162, _heightA - 53);
			g.drawLine(width - 150, _heightA - 50, width - 160, _heightA - 40);
			g.setBackground(new Color(198, 249, 184));// 绿色
			g.clearRect(width - 150, _heightA - 70, 80, 50);
			Rectangle2D rect = new Rectangle2D.Float(width - 150,
					_heightA - 70, 80, 50);
			g.draw(rect);
			g.setFont(new Font("宋体", Font.BOLD, 25));
			g.drawString("A区域", width - 140, _heightA - 40);

			g.dispose();
			bi.flush();
			Date dt = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			String filename = "upload/" + userId + "reportImgShadow"
					+ df.format(dt) + ".jpg";
			String savePath = ServletActionContext.getServletContext()
					.getRealPath(filename);
			FileOutputStream out = new FileOutputStream(savePath);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);
			param.setQuality(1.0f, false);
			encoder.setJPEGEncodeParam(param);
			try {
				encoder.encode(bi);

			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			return filename;
		} else {
			return "";
		}
	}

	// public static int cal_Max_height(String[] infos) {
	// int max = 0;
	// for (int n = 0; n < infos.length; n++) {
	// String temp_str = infos[n];
	// if (temp_str.length() > 0) {
	// int temp = (temp_str.split("\\*")).length;
	// if (temp > max)
	// max = temp;
	// }
	// }
	// if (max % 3 == 0)
	// return max / 3;
	// else {
	// return (max / 3) + 1;
	// }
	// }

	public static int cal_Max_height(String[] infos) {
		int max = 0;
		int lineNumber = 0;
		for (int n = 0; n < infos.length; n++) {
			String temp_str = infos[n];
			if (temp_str.length() > 0) {
				int temp = (temp_str.split("\\*")).length;
				if (temp > max)
					max = temp;
			}
		}
		if (max % 3 == 0)
			lineNumber = max / 3;
		else {
			lineNumber = (max / 3) + 1;
		}
		if (lineNumber <= 5) {
			return 100;
		} else {
			return lineNumber * 20;
		}

	}

	public static void main(String[] args) throws IOException {
		List lists = new LinkedList<info>();
		for (int n = 0; n < 30; n++) {
			info in = new info();
			in.setId(String.valueOf(n));
			in.setName("name" + n);
			Random r = new Random();
			in.setPossibility_num(r.nextInt(4) + 1);
			Random r2 = new Random();
			in.setNfluence_num(r2.nextInt(4) + 1);
			in.setSequence(n + 1);
			lists.add(in);
		}
		drawTable(800, 800, lists);
	}

	public static String drawPieChart(int i, int j,
			List<ReportViewNew> reportList) {
		// TODO Auto-generated method stub
		if (!reportList.isEmpty()) {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Users us = (Users) session.getAttribute("loginUser");
			String userId = us.getUserId();
			int aNum = 0, bNum = 0, cNum = 0;
			String graphURL = null;
			for (ReportViewNew reportViewNew : reportList) {
				if (reportViewNew.getRiskPro() < 3
						&& reportViewNew.getRiskValuex() < 3)
					aNum++;
				else if (reportViewNew.getRiskPro() > 3
						&& reportViewNew.getRiskValuex() > 3)
					cNum++;
				else
					bNum++;
			}
			DefaultPieDataset dataset = new DefaultPieDataset();
			dataset.setValue("A区域", aNum);
			dataset.setValue("B区域", bNum);
			dataset.setValue("C区域", cNum);
			// String title="分区风险比例饼状图";
			JFreeChart chart = ChartFactory.createPieChart("", dataset, false,
					false, false);
			// 设置pieChart的标题和字体
			// Font font=new Font("宋体",Font.BOLD,25);
			// TextTitle textTitle=new TextTitle(title);
			// textTitle.setFont(font);
			// chart.setTitle(textTitle);
			chart.setTextAntiAlias(false);
			//  设置背景色  
			// chart.setBackgroundPaint(new Color(255,255,255));
			chart.setBackgroundPaint(ChartColor.WHITE);
			// 设置图例字体
			// LegendTitle legend = chart.getLegend(0);
			// legend.setItemFont(new Font("黑体", Font.BOLD, 17));
			//  设置标签字体  
			PiePlot plot = (PiePlot) chart.getPlot();
			plot.setLabelFont(new Font("宋体", Font.BOLD, 17));
			// 设置饼图是圆的（true），还是椭圆的（false）；默认为true
			plot.setCircular(true);
			//  指定图片的透明度(0.0-1.0)          
			plot.setForegroundAlpha(1);
			//  图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位 
			plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
					"{0}={1}({2})", NumberFormat.getNumberInstance(),
					new DecimalFormat("0.00%")));
			//  图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例  
			// plot.setLegendLabelGenerator(new
			// StandardPieSectionLabelGenerator(
			// "{0} ({2})", NumberFormat.getNumberInstance(),
			// new DecimalFormat("0.00%")));
			//  设置第一个饼块截面开始的位置，默认是12点钟方向          
			plot.setStartAngle(90);
			plot.setSectionPaint("A区域", new Color(198, 249, 184));
			plot.setSectionPaint("B区域", new Color(253, 236, 182));
			plot.setSectionPaint("C区域", new Color(248, 195, 195));
			// 第一个参数是key,第二个参数是突出显示的大小（可以自己调整一下看看效果就明白了）
			// plot.setExplodePercent("A区域", 0.23);
			plot.setBackgroundPaint(new Color(231, 253, 199));
			plot.setLabelFont(new Font("宋体", Font.BOLD, 17));
			plot.setMaximumLabelWidth(0.15);
			// plot.setLabelGap(0.02);
			plot.setShadowGenerator(new ShadowGenerator() {

				@Override
				public BufferedImage createDropShadow(BufferedImage arg0) {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public int calculateOffsetY() {
					// TODO Auto-generated method stub
					return 0;
				}

				@Override
				public int calculateOffsetX() {
					// TODO Auto-generated method stub
					return 0;
				}
			});

			try {
				Date dt = new Date();
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd-HH-mm-ss");
				graphURL = "upload/" + userId + "sectionPiechart"
						+ df.format(dt) + ".png";
				String filename = ServletActionContext.getServletContext()
						.getRealPath(graphURL);
				ChartUtilities.saveChartAsPNG(new File(filename), chart, i, j);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return graphURL;
		} else {
			return "";
		}

	}

	// 避免缓存错乱
	public static String drawKeyPieChart(int i, int j,
			List<ReportViewNew> reportList) {
		// TODO Auto-generated method stub
		if (!reportList.isEmpty()) {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Users us = (Users) session.getAttribute("loginUser");
			String userId = us.getUserId();
			int aNum = 0, bNum = 0, cNum = 0;
			String graphURL = null;
			for (ReportViewNew reportViewNew : reportList) {
				if (reportViewNew.getRiskPro() < 3
						&& reportViewNew.getRiskValuex() < 3)
					aNum++;
				else if (reportViewNew.getRiskPro() > 3
						&& reportViewNew.getRiskValuex() > 3)
					cNum++;
				else
					bNum++;
			}
			DefaultPieDataset dataset = new DefaultPieDataset();
			dataset.setValue("A区域", aNum);
			dataset.setValue("B区域", bNum);
			dataset.setValue("C区域", cNum);
			// String title="分区风险比例饼状图";
			JFreeChart chart = ChartFactory.createPieChart("", dataset, true,
					false, false);
			// 设置pieChart的标题和字体
			// Font font=new Font("宋体",Font.BOLD,25);
			// TextTitle textTitle=new TextTitle(title);
			// textTitle.setFont(font);
			// chart.setTitle(textTitle);
			chart.setTextAntiAlias(false);
			//  设置背景色  
			// chart.setBackgroundPaint(new Color(255,255,255));
			chart.setBackgroundPaint(new Color(238, 241, 242));
			// 设置图例字体
			LegendTitle legend = chart.getLegend(0);
			legend.setItemFont(new Font("黑体", Font.BOLD, 17));
			//  设置标签字体  
			PiePlot plot = (PiePlot) chart.getPlot();
			plot.setLabelFont(new Font("黑体", Font.BOLD, 17));
			// 设置饼图是圆的（true），还是椭圆的（false）；默认为true
			plot.setCircular(true);
			//  指定图片的透明度(0.0-1.0)          
			plot.setForegroundAlpha(0.95f);
			//  图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位 
			plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
					"{0}={1}({2})", NumberFormat.getNumberInstance(),
					new DecimalFormat("0.00%")));
			//  图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例  
			plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(
					"{0} ({2})", NumberFormat.getNumberInstance(),
					new DecimalFormat("0.00%")));
			//  设置第一个饼块截面开始的位置，默认是12点钟方向          
			plot.setStartAngle(90);
			plot.setSectionPaint("A区域", new Color(198, 249, 184));
			plot.setSectionPaint("B区域", new Color(253, 236, 182));
			plot.setSectionPaint("C区域", new Color(248, 195, 195));
			// 第一个参数是key,第二个参数是突出显示的大小（可以自己调整一下看看效果就明白了）
			plot.setExplodePercent("A区域", 0.23);
			plot.setBackgroundPaint(new Color(238, 241, 242));
			try {
				Date dt = new Date();
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd-HH-mm-ss");
				graphURL = "upload/" + userId + "keySectionPiechart"
						+ df.format(dt) + ".png";
				String filename = ServletActionContext.getServletContext()
						.getRealPath(graphURL);
				ChartUtilities.saveChartAsPNG(new File(filename), chart, i, j);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return graphURL;
		} else {
			return "";
		}

	}

	public static String drawKeyBarChart(List<ReportViewNew> reportList) {
		// TODO Auto-generated method stub
		if (!reportList.isEmpty()) {
			// int width = 0;
			// int height = 0;
			// if (reportList.size() <= 5) {
			// width = 500;
			// height = 550;
			// } else {
			// width = 1000;
			// height = 550;
			// }
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Users us = (Users) session.getAttribute("loginUser");
			String userId = us.getUserId();
			String filename = "";
			String graphURL = "";
			// 柱状图中文乱码问题
			StandardChartTheme theme = new StandardChartTheme("CN") {
				// 重写apply(...)方法借机消除文字锯齿.VALUE_TEXT_ANTIALIAS_OFF
				public void apply(JFreeChart chart) {
					chart.getRenderingHints().put(
							RenderingHints.KEY_TEXT_ANTIALIASING,
							RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
					super.apply(chart);
				}
			};
			// 标题乱码解决
			// theme.setExtraLargeFont(new Font("宋体",Font.BOLD,25));
			// 图例乱码解决
			theme.setRegularFont(new Font("宋体", Font.PLAIN, 14));
			theme.setLargeFont(new Font("宋体", Font.BOLD, 20));
			theme.setSmallFont(new Font("宋体", Font.PLAIN, 14));
			theme.setBaselinePaint(java.awt.Color.white);
			// 应用主题样式
			ChartFactory.setChartTheme(theme);

			// 柱状图
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			// 讲二级风险按照风险事件数大小从高到低排序
			Collections.sort(reportList, new Comparator<ReportViewNew>() {
				public int compare(ReportViewNew o1, ReportViewNew o2) {
					return Integer.valueOf(o2.getRiskNum())
							- Integer.valueOf(o1.getRiskNum());
				}
			});
			for (ReportViewNew reportViewNew : reportList) {
				dataset.addValue(Integer.valueOf(reportViewNew.getRiskNum()),
						"风险事件数", reportViewNew.getRiskName());
			}

			JFreeChart chart = ChartFactory.createBarChart3D("", "", "风险事件数",
					dataset, PlotOrientation.VERTICAL, false, true, true);

			// HttpSession session=request.getSession();

			CategoryPlot cp = chart.getCategoryPlot();
			// cp.setBackgroundPaint(ChartColor. WHITE );
			cp.setRangeGridlinePaint(ChartColor.GRAY); // 网格线色设置
			cp.setNoDataMessage("No data available");
			// 使纵坐标的最小单位格为整数
			NumberAxis numberaxis = (NumberAxis) cp.getRangeAxis();
			numberaxis
					.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			BarColorRenderer barrenderer = new BarColorRenderer();
			barrenderer
					.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
			barrenderer.setBaseItemLabelsVisible(true);
			barrenderer.setMaximumBarWidth(0.03);
			// 设置柱子的倒影不可见
			barrenderer.setShadowVisible(false);
			// GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F,
			// Color.blue,
			// 0.0F, 0.0F, new Color(135, 206, 235)); //设定特定颜色
			// barrenderer.setSeriesPaint(0, gradientpaint);
			barrenderer
					.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());// 显示每个柱的数值
			barrenderer.setBaseItemLabelsVisible(true);
			// 注意：此句很关键，若无此句，那数字的显示会被覆盖，给人数字没有显示出来的问题
			barrenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
					ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
			barrenderer.setItemLabelAnchorOffset(10D);// 设置柱形图上的文字偏离值
			barrenderer.setItemLabelsVisible(true);
			barrenderer.setDrawBarOutline(false);
			barrenderer.setItemLabelFont(new Font("宋体", Font.BOLD, 12));// 12号黑体加粗
			barrenderer.setItemLabelPaint(Color.black);// 字体为黑色
			cp.setRenderer(barrenderer);
			// 背景色设置
			cp.setBackgroundPaint(new Color(221, 253, 177));
			CategoryAxis domainAxis = cp.getDomainAxis();
			domainAxis.setCategoryLabelPositions(CategoryLabelPositions
					.createDownRotationLabelPositions(Math.PI / 2));
			domainAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 8));
			domainAxis.setLabel("");
			domainAxis.setCategoryLabelPositionOffset(10);
			domainAxis.setCategoryMargin(0.5);
			cp.setForegroundAlpha(1);

			ValueAxis rangeAxis = cp.getRangeAxis();
			// 设置最高的一个 Item 与图片顶端的距离
			rangeAxis.setUpperMargin(0.1);
			// rangeAxis.setMinorTickCount(2);
			// if(Integer.valueOf(reportList.get(0).getRiskNum())<=3){
			// rangeAxis.setRange(0, 4);
			// }
			rangeAxis.setRange(0,
					Integer.valueOf(reportList.get(0).getRiskNum()) + 1);

			try {
				Date dt = new Date();
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd-HH-mm-ss");
				graphURL = "upload/" + userId + "keyRiskNumBarchart"
						+ df.format(dt) + ".png";
				filename = ServletActionContext.getServletContext()
						.getRealPath(graphURL);
				ChartUtilities.saveChartAsPNG(new File(filename), chart, 810,
						600);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return graphURL;
		} else {
			return "";
		}
	}

	public static String drawBarChart(List<ReportViewNew> reportList) {
		// TODO Auto-generated method stub
		if (!reportList.isEmpty()) {
			// int width=0;
			// int height=0;
			// if(reportList.size()<=5){
			// width=500;
			// height=550;
			// }else{
			// width=1000;
			// height=550;
			// }
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Users us = (Users) session.getAttribute("loginUser");
			String userId = us.getUserId();
			String filename = "";
			// String filename1="";
			String graphURL = "";
			String imageUrl = "";
			String imageUrl1 = "";

			// 柱状图中文乱码问题
			StandardChartTheme theme = new StandardChartTheme("CN") {
				// 重写apply(...)方法借机消除文字锯齿.VALUE_TEXT_ANTIALIAS_OFF
				public void apply(JFreeChart chart) {
					chart.getRenderingHints().put(
							RenderingHints.KEY_TEXT_ANTIALIASING,
							RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
					super.apply(chart);
				}
			};
			// 标题乱码解决
			theme.setExtraLargeFont(new Font("宋体", Font.BOLD, 25));
			// 图例乱码解决
			theme.setRegularFont(new Font("宋体", Font.PLAIN, 14));
			theme.setLargeFont(new Font("宋体", Font.BOLD, 20));
			theme.setSmallFont(new Font("宋体", Font.PLAIN, 14));
			theme.setBaselinePaint(java.awt.Color.white);
			// 应用主题样式
			ChartFactory.setChartTheme(theme);

			// 柱状图

			DefaultCategoryDataset dataset = new DefaultCategoryDataset();

			List<ReportViewNew> useList = new ArrayList<ReportViewNew>();
			useList.addAll(reportList);
			// 將二级风险按照风险事件数大小从高到低排序
			Collections.sort(useList, new Comparator<ReportViewNew>() {
				public int compare(ReportViewNew o1, ReportViewNew o2) {
					return Integer.valueOf(o2.getRiskNum())
							- Integer.valueOf(o1.getRiskNum());
				}
			});
			for (ReportViewNew reportViewNew : useList) {
				dataset.addValue(Integer.valueOf(reportViewNew.getRiskNum()),
						"风险事件数", reportViewNew.getRiskName());
			}
			// new
			// StringBuilder(reportViewNew.getRiskName()).reverse().toString()

			JFreeChart chart = ChartFactory.createBarChart3D("", "", "风险事件数",
					dataset, PlotOrientation.VERTICAL, false, true, true);

			// HttpSession session=request.getSession();

			CategoryPlot cp = chart.getCategoryPlot();
			CategoryItemRenderer renderer = cp.getRenderer();
			cp.setBackgroundPaint(new Color(221, 253, 177)); // 背景色设置
			cp.setRangeGridlinePaint(ChartColor.GRAY); // 网格线色设置
			cp.setNoDataMessage("No data available");
			// cp.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
			// cp.setDomainAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
			// 使纵坐标的最小单位格为整数
			NumberAxis numberaxis = (NumberAxis) cp.getRangeAxis();
			numberaxis
					.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			BarColorRenderer barrenderer = new BarColorRenderer();
			// barrenderer
			// .setBaseItemLabelGenerator(new
			// StandardCategoryItemLabelGenerator());
			barrenderer.setBaseItemLabelsVisible(true);
			barrenderer.setMaximumBarWidth(0.06);
			// 设置柱子的倒影不可见
			barrenderer.setShadowVisible(false);
			// barrenderer.setBasePositiveItemLabelPosition(new
			// ItemLabelPosition(ItemLabelAnchor.CENTER,
			// TextAnchor.BASELINE_CENTER));
			barrenderer
					.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());// 显示每个柱的数值
			barrenderer.setBaseItemLabelsVisible(true);
			// 注意：此句很关键，若无此句，那数字的显示会被覆盖，给人数字没有显示出来的问题
			barrenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
					ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
			barrenderer.setItemLabelAnchorOffset(10D);// 设置柱形图上的文字偏离值
			barrenderer.setItemLabelsVisible(true);
			barrenderer.setDrawBarOutline(false);
			barrenderer.setItemLabelFont(new Font("宋体", Font.PLAIN, 10));// 12号黑体加粗
			barrenderer.setItemLabelPaint(Color.black);// 字体为黑色
			// barrenderer.setItemLabelAnchorOffset(-30.0f);
			// barrenderer.setItemLabelsVisible(true);
			// barrenderer.setDrawBarOutline(false);
			// barrenderer.setItemMargin(-0.01);
			// barrenderer.setBaseSeriesVisible(false);
			// GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F,
			// Color.blue,
			// 0.0F, 0.0F, new Color(135, 206, 235)); //设定特定颜色
			// barrenderer.setSeriesPaint(0, gradientpaint);
			cp.setRenderer(barrenderer);

			CategoryAxis domainAxis = cp.getDomainAxis();
			domainAxis.setCategoryLabelPositions(CategoryLabelPositions
					.createDownRotationLabelPositions(Math.PI / 2));
			domainAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 12));
			domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
			domainAxis.setLabel("");
			domainAxis.setCategoryLabelPositionOffset(10);
			domainAxis.setCategoryMargin(0.5);
			domainAxis.setLowerMargin(0.03);

			cp.setForegroundAlpha(1);
			// cp.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);

			ValueAxis rangeAxis = cp.getRangeAxis();
			// 设置最高的一个 Item 与图片顶端的距离
			rangeAxis.setUpperMargin(0.1);
			rangeAxis.setMinorTickCount(2);
			rangeAxis.setRange(0,
					Integer.valueOf(useList.get(0).getRiskNum()) + 1);
			// rangeAxis.setLowerBound(3);
			// LegendTitle legendTitle = new LegendTitle(chart.getPlot());//创建图例
			// legendTitle.setPosition(RectangleEdge.RIGHT); //设置图例的位置
			// chart.getLegend().setVisible(false);
			// chart.getLegend().setPosition(RectangleEdge.LEFT);


			try {
				Date dt = new Date();
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd-HH-mm-ss");
				graphURL = "upload/" + userId + "riskNumBarchart"
						+ df.format(dt) + ".png";
				filename = ServletActionContext.getServletContext()
						.getRealPath(graphURL);
				ChartUtilities.saveChartAsPNG(new File(filename), chart, 810,
						600);

			} catch (IOException e) {
				e.printStackTrace();
			}
			return graphURL;
			// return imageUrl1;

		} else {
			return "";
		}
	}
	
	@SuppressWarnings("deprecation")
	public static String drawBarChart_111(List<ReportViewNew> reportList)  {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		
		HttpSession session = request.getSession();
		Users us = (Users) session.getAttribute("loginUser");
		String userId = us.getUserId();
		String filename = "";
		// String filename1="";
		//String graphURL = "";
		//String imageUrl = "";
		String imageUrl1 = "";

		// 柱状图中文乱码问题
		StandardChartTheme theme = new StandardChartTheme("CN") {
			// 重写apply(...)方法借机消除文字锯齿.VALUE_TEXT_ANTIALIAS_OFF
			public void apply(JFreeChart chart) {
				chart.getRenderingHints().put(
						RenderingHints.KEY_TEXT_ANTIALIASING,
						RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
				super.apply(chart);
			}
		};
		// 标题乱码解决
		theme.setExtraLargeFont(new Font("宋体", Font.BOLD, 25));
		// 图例乱码解决
		theme.setRegularFont(new Font("宋体", Font.PLAIN, 14));
		theme.setLargeFont(new Font("宋体", Font.BOLD, 20));
		theme.setSmallFont(new Font("宋体", Font.PLAIN, 14));
		theme.setBaselinePaint(java.awt.Color.white);
		// 应用主题样式
		ChartFactory.setChartTheme(theme);

		// 柱状图

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		List<ReportViewNew> useList = new ArrayList<ReportViewNew>();
		useList.addAll(reportList);
		// 將二级风险按照风险事件数大小从高到低排序
		Collections.sort(useList, new Comparator<ReportViewNew>() {
			public int compare(ReportViewNew o1, ReportViewNew o2) {
				return Integer.valueOf(o2.getRiskNum())
						- Integer.valueOf(o1.getRiskNum());
			}
		});
		for (ReportViewNew reportViewNew : useList) {
			dataset.addValue(Integer.valueOf(reportViewNew.getRiskNum()),
					"风险事件数", reportViewNew.getRiskName());
		}
		// new
		// StringBuilder(reportViewNew.getRiskName()).reverse().toString()

		JFreeChart chart = ChartFactory.createBarChart3D("", "", "风险事件数",
				dataset, PlotOrientation.VERTICAL, false, true, true);

		CategoryPlot plot=chart.getCategoryPlot();
		CategoryItemRenderer renderer=plot.getRenderer();    
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions
				.createDownRotationLabelPositions(Math.PI / 2));
		domainAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 12));
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		domainAxis.setLabel("");
		domainAxis.setCategoryLabelPositionOffset(10);
		domainAxis.setCategoryMargin(0.5);
		domainAxis.setLowerMargin(0.03);	
		plot.setBackgroundPaint(new Color(221, 253, 177)); // 背景色设置
		plot.setRangeGridlinePaint(ChartColor.GRAY); // 网格线色设置
		plot.setNoDataMessage("No data available");
		// cp.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
		// cp.setDomainAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
		// 使纵坐标的最小单位格为整数
		NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();
		numberaxis
				.setStandardTickUnits(NumberAxis.createIntegerTickUnits());	
		renderer.setItemLabelGenerator( new  StandardCategoryItemLabelGenerator()); 
		renderer.setItemLabelsVisible( true ); 
		renderer.setItemLabelPaint(Color.BLACK);
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.CENTER_LEFT)); 
		//下面可以进一步调整数值的位置，但是得根据ItemLabelAnchor选择情况. 
		//renderer.setItemLabelAnchorOffset(10); 
		//使不产生url链接
		renderer.setBaseItemURLGenerator(null);		
		//定义url连接位置和参数
		renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator("{1}",NumberFormat.getIntegerInstance()));				
		//创建chartRenderingInfo对象
		ChartRenderingInfo info=new ChartRenderingInfo(new StandardEntityCollection());
		
		//设置生成图片，并返回图片信息
		
		try {

			filename = ServletUtilities.saveChartAsPNG(chart, 810, 600,info, request.getSession());		
		//创建热点的区域位置信息
		String map=ChartUtilities.getImageMap(filename, info);

		String imageUrl="upload/keyRiskNumBarchartx.png";
		String filename1 = ServletActionContext.getServletContext()
				.getRealPath(imageUrl);
		ChartUtilities.saveChartAsPNG(new File(filename1), chart, 810,
				600);
		request.setAttribute("imageurl", imageUrl);
		request.setAttribute("filename", filename);
		request.setAttribute("map",map);
		return imageUrl;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	
	
	

	

	public static String drawBarChart2(List<ReportViewNew> reportList) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if (!reportList.isEmpty()) {
			// int width = 0;
			// int height = 0;
			// if (reportList.size() <= 5) {
			// width = 500;
			// height = 550;
			// } else {
			// width = 1000;
			// height = 550;
			// }
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Users us = (Users) session.getAttribute("loginUser");
			String userId = us.getUserId();
			String filename = "";
			String graphURL = "";
			// 柱状图中文乱码问题
			StandardChartTheme theme = new StandardChartTheme("CN") {
				// 重写apply(...)方法借机消除文字锯齿.VALUE_TEXT_ANTIALIAS_OFF
				public void apply(JFreeChart chart) {
					chart.getRenderingHints().put(
							RenderingHints.KEY_TEXT_ANTIALIASING,
							RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
					super.apply(chart);
				}
			};
			// 标题乱码解决
			// theme.setExtraLargeFont(new Font("宋体", Font.BOLD, 25));
			// 图例乱码解决
			theme.setRegularFont(new Font("宋体", Font.PLAIN, 14));
			theme.setLargeFont(new Font("宋体", Font.BOLD, 20));
			theme.setSmallFont(new Font("宋体", Font.PLAIN, 14));
			theme.setBaselinePaint(java.awt.Color.white);
			// 应用主题样式
			ChartFactory.setChartTheme(theme);
			// 讲二级风险按照风险事件数大小从高到低排序
			Collections.sort(reportList, new Comparator<ReportViewNew>() {
				public int compare(ReportViewNew o1, ReportViewNew o2) {
					double cha = o2.getRiAllvalue() - o1.getRiAllvalue();
					if (cha > 0)
						return 1;
					else if (cha < 0)
						return -1;
					return 0;
				}
			});

			// 柱状图
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for (ReportViewNew reportViewNew : reportList) {
				// System.out.println("综合评定值————————————————————"+reportViewNew.getRiAllvalue());
				dataset.addValue(reportViewNew.getRiAllvalue(), "风险综合评定值",
						reportViewNew.getRiskName());
			}

			JFreeChart chart = ChartFactory.createBarChart3D("", "", "风险综合评定值",
					dataset, PlotOrientation.VERTICAL, false, true, true);

			// HttpSession session=request.getSession();

			CategoryPlot cp = chart.getCategoryPlot();
			cp.setBackgroundPaint(new Color(221, 253, 177)); // 背景色设置
			cp.setRangeGridlinePaint(ChartColor.GRAY); // 网格线色设置
			cp.setNoDataMessage("No data available");
			BarColorRenderer barrenderer = new BarColorRenderer();
			barrenderer
					.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
			barrenderer.setBaseItemLabelsVisible(true);
			barrenderer.setMaximumBarWidth(0.03);
			// 设置柱子的倒影不可见
			barrenderer.setShadowVisible(false);
			// GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F,
			// Color.blue,
			// 0.0F, 0.0F, new Color(135, 206, 235)); //设定特定颜色
			// barrenderer.setSeriesPaint(0, gradientpaint);
			barrenderer
					.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());// 显示每个柱的数值
			barrenderer.setBaseItemLabelsVisible(true);
			// 注意：此句很关键，若无此句，那数字的显示会被覆盖，给人数字没有显示出来的问题
			barrenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
					ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
			barrenderer.setItemLabelAnchorOffset(10D);// 设置柱形图上的文字偏离值
			barrenderer.setItemLabelsVisible(true);
			barrenderer.setDrawBarOutline(false);
			barrenderer.setItemLabelFont(new Font("宋体", Font.BOLD, 12));// 12号黑体加粗
			barrenderer.setItemLabelPaint(Color.black);// 字体为黑色
			cp.setRenderer(barrenderer);
			CategoryAxis domainAxis = cp.getDomainAxis();
			domainAxis.setCategoryLabelPositions(CategoryLabelPositions
					.createDownRotationLabelPositions(Math.PI / 2));
			domainAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 8));
			domainAxis.setLabel("");
			domainAxis.setCategoryLabelPositionOffset(10);
			domainAxis.setCategoryMargin(0.5);
			domainAxis.setLowerMargin(0.03);
			cp.setForegroundAlpha(1);
			ValueAxis rangeAxis = cp.getRangeAxis();
			// 设置最高的一个 Item 与图片顶端的距离
			rangeAxis.setUpperMargin(0.1);

			try {
				Date dt = new Date();
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd-HH-mm-ss");
				graphURL = "upload/" + userId + "barchart2" + df.format(dt)
						+ ".png";
				filename = ServletActionContext.getServletContext()
						.getRealPath(graphURL);
				ChartUtilities.saveChartAsPNG(new File(filename), chart, 810,
						600);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return graphURL;
		} else {
			return "";
		}
	}

	public static String drawRtPieChart(int i, int j, List<RiAnalysis> riskList) {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Users us = (Users) session.getAttribute("loginUser");
		String userId = us.getUserId();
		String riskName;
		String graphURL = null;
		DefaultPieDataset dataset = new DefaultPieDataset();
		for (RiAnalysis risk : riskList) {
			riskName = risk.getRiskName();
			if (GeneralAnalysisAction.isRiskType(riskName)) {
				dataset.setValue(riskName,
						Integer.valueOf(risk.getReEventname()));
			}
		}
		if (!riskList.contains("战略风险")) {
			dataset.setValue("战略风险", 0);
		} else if (!riskList.contains("运营风险")) {
			dataset.setValue("运营风险", 0);
		} else if (!riskList.contains("市场风险")) {
			dataset.setValue("市场风险", 0);
		} else if (!riskList.contains("法律风险")) {
			dataset.setValue("法律风险", 0);
		} else if (!riskList.contains("财务风险")) {
			dataset.setValue("财务风险", 0);
		} else {

		}
		JFreeChart chart = ChartFactory.createPieChart("", dataset, false,
				false, false);
		// 设置pieChart的标题和字体
		chart.setTextAntiAlias(false);
		//  设置背景色  
		// chart.setBackgroundPaint(new Color(255,255,255));
		chart.setBackgroundPaint(ChartColor.WHITE);
		// // 设置图例字体
		// LegendTitle legend = chart.getLegend(0);
		// legend.setItemFont(new Font("黑体", Font.BOLD, 17));
		//  设置标签字体  
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("宋体", Font.BOLD, 15));
		plot.setMaximumLabelWidth(0.15);
		// 设置饼图是圆的（true），还是椭圆的（false）；默认为true
		plot.setCircular(true);
		//  指定图片的透明度(0.0-1.0)          
		plot.setForegroundAlpha(1);
		//  图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位 
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}={1}({2})", NumberFormat.getNumberInstance(),
				new DecimalFormat("0.00%")));
		//  图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例  
		// plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(
		// "{0} ({2})", NumberFormat.getNumberInstance(),
		// new DecimalFormat("0.00%")));
		//  设置第一个饼块截面开始的位置，默认是12点钟方向          
		plot.setStartAngle(90);
		plot.setBackgroundPaint(new Color(221, 253, 177));
		plot.setSectionPaint("运营风险", Color.decode("#8164A3"));
		plot.setSectionPaint("战略风险", Color.decode("#4AADC7"));
		plot.setSectionPaint("市场风险", Color.decode("#C1504C"));
		plot.setSectionPaint("法律风险", Color.decode("#9DBD58"));
		plot.setSectionPaint("财务风险", Color.decode("#4C7FB9"));
		// 第一个参数是key,第二个参数是突出显示的大小（可以自己调整一下看看效果就明白了）
		// plot.setExplodePercent("A区域",0.23);
		plot.setShadowGenerator(new ShadowGenerator() {

			@Override
			public BufferedImage createDropShadow(BufferedImage arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int calculateOffsetY() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int calculateOffsetX() {
				// TODO Auto-generated method stub
				return 0;
			}
		});
		try {
			// filename = ServletUtilities.saveChartAsJPEG(chart, 800, 500,
			// null,session);

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			graphURL = "upload/rtPiechart" + userId + ".png";
			String filename = ServletActionContext.getServletContext()
					.getRealPath(graphURL);
			ChartUtilities.saveChartAsPNG(new File(filename), chart, i, j);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return graphURL;
	}

	public static String drawRtBarChart(int i, int j, List<RiAnalysis> riskList) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Users us = (Users) session.getAttribute("loginUser");
		String userId = us.getUserId();
		String filename = "";
		String graphURL = "";
		String riskName;
		// 柱状图中文乱码问题
		StandardChartTheme theme = new StandardChartTheme("CN") {
			// 重写apply(...)方法借机消除文字锯齿.VALUE_TEXT_ANTIALIAS_OFF
			public void apply(JFreeChart chart) {
				chart.getRenderingHints().put(
						RenderingHints.KEY_TEXT_ANTIALIASING,
						RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
				super.apply(chart);
			}
		};
		// 标题乱码解决
		theme.setExtraLargeFont(new Font("宋体", Font.BOLD, 25));
		// 图例乱码解决
		theme.setRegularFont(new Font("宋体", Font.PLAIN, 14));
		theme.setLargeFont(new Font("宋体", Font.BOLD, 20));
		theme.setSmallFont(new Font("宋体", Font.PLAIN, 14));
		theme.setBaselinePaint(java.awt.Color.white);
		// 应用主题样式
		ChartFactory.setChartTheme(theme);

		// 柱状图
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		// 用于临时存放一级风险综合评定值，后面做排序用
		List<RiAnalysis> tempList = new LinkedList<RiAnalysis>();
		for (RiAnalysis risk : riskList) {
			// System.out.println("综合评定值————————————————————"+reportViewNew.getRiAllvalue());
			riskName = risk.getRiskName();
			if (GeneralAnalysisAction.isRiskType(riskName)) {
				tempList.add(risk);
			}
		}

		Collections.sort(tempList, new Comparator<RiAnalysis>() {
			public int compare(RiAnalysis o1, RiAnalysis o2) {
				double cha = Double.valueOf(o2.getRiAllvalue())
						- Double.valueOf(o1.getRiAllvalue());
				if (cha > 0)
					return 1;
				else if (cha < 0)
					return -1;
				else
					return 0;
			}
		});
		for (RiAnalysis risk : tempList) {
			// System.out.println("综合评定值————————————————————"+reportViewNew.getRiAllvalue());
			riskName = risk.getRiskName();
			if (GeneralAnalysisAction.isRiskType(riskName)) {
				dataset.addValue(Double.valueOf(risk.getRiAllvalue()),
						"风险综合评定值", riskName);
			}
		}
		if (!tempList.contains("战略风险")) {
			dataset.addValue(0.00, "风险综合评定值", "战略风险");
		} else if (!tempList.contains("运营风险")) {
			dataset.addValue(0.00, "风险综合评定值", "运营风险");
		} else if (!tempList.contains("市场风险")) {
			dataset.addValue(0.00, "风险综合评定值", "市场风险");
		} else if (!tempList.contains("法律风险")) {
			dataset.addValue(0.00, "风险综合评定值", "法律风险");
		} else if (!tempList.contains("财务风险")) {
			dataset.addValue(0.00, "风险综合评定值", "财务风险");
		} else {

		}
		JFreeChart chart = ChartFactory.createBarChart3D("", "", "综合评定值",
				dataset, PlotOrientation.VERTICAL, false, true, true);

		// HttpSession session=request.getSession();

		CategoryPlot cp = chart.getCategoryPlot();
		cp.setBackgroundPaint(new Color(221, 253, 177)); // 背景色设置
		cp.setRangeGridlinePaint(ChartColor.GRAY); // 网格线色设置
		cp.setNoDataMessage("No data available");
		cp.setForegroundAlpha(1);

		NumberAxis na = (NumberAxis) cp.getRangeAxis();
		na.setAutoRangeIncludesZero(true);
		DecimalFormat df = new DecimalFormat("#0.00");
		na.setNumberFormatOverride(df);

		ValueAxis rangeAxis = cp.getRangeAxis();
		// 设置最高的一个 Item 与图片顶端的距离
		// rangeAxis.setUpperMargin(0.1);
		rangeAxis.setUpperMargin(0.2);

		BarRenderer barrenderer = new BarColorRenderer();
		barrenderer
				.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		barrenderer.setBaseItemLabelsVisible(true);
		barrenderer.setShadowVisible(false);
		barrenderer.setMaximumBarWidth(0.05);
		barrenderer
				.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());// 显示每个柱的数值
		barrenderer.setBaseItemLabelsVisible(true);
		// 注意：此句很关键，若无此句，那数字的显示会被覆盖，给人数字没有显示出来的问题
		barrenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
		barrenderer.setItemLabelAnchorOffset(10D);// 设置柱形图上的文字偏离值
		barrenderer.setItemLabelsVisible(true);
		barrenderer.setDrawBarOutline(false);
		barrenderer.setItemLabelFont(new Font("宋体", Font.BOLD, 12));// 12号黑体加粗
		barrenderer.setItemLabelPaint(Color.black);// 字体为黑色
		// GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F,
		// Color.blue,
		// 0.0F, 0.0F, new Color(135, 206, 235)); //设定特定颜色
		// barrenderer.setSeriesPaint(0, gradientpaint);
		cp.setRenderer(barrenderer);
		// chart.getLegend().setVisible(false);
		// CategoryAxis domainAxis = cp.getDomainAxis();
		// domainAxis.setLabel("");
		try {
			graphURL = "upload/rtBarchart" + userId + ".png";
			filename = ServletActionContext.getServletContext().getRealPath(
					graphURL);
			ChartUtilities.saveChartAsPNG(new File(filename), chart, i, j);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return graphURL;
	}

	public static String drawRuleBarChart(List<RuleFile> ruleFileList) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// int width = 0;
		// int height = 0;
		// if (ruleFileList.size() <= 5) {
		// width = 500;
		// height = 550;
		// } else {
		// width = 1000;
		// height = 550;
		// }
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Users us = (Users) session.getAttribute("loginUser");
		String userId = us.getUserId();
		String filename = "";
		String filenamex="";
		String graphURL = "";
		// 柱状图中文乱码问题
		StandardChartTheme theme = new StandardChartTheme("CN") {
			// 重写apply(...)方法借机消除文字锯齿.VALUE_TEXT_ANTIALIAS_OFF
			public void apply(JFreeChart chart) {
				chart.getRenderingHints().put(
						RenderingHints.KEY_TEXT_ANTIALIASING,
						RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
				super.apply(chart);
			}
		};
		// 标题乱码解决
		theme.setExtraLargeFont(new Font("宋体", Font.BOLD, 25));
		// 图例乱码解决
		theme.setRegularFont(new Font("宋体", Font.PLAIN, 14));
		theme.setLargeFont(new Font("宋体", Font.BOLD, 20));
		theme.setSmallFont(new Font("宋体", Font.PLAIN, 14));
		theme.setBaselinePaint(java.awt.Color.white);
		// 应用主题样式
		ChartFactory.setChartTheme(theme);

		// 柱状图
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (RuleFile file : ruleFileList) {
			dataset.addValue(Integer.valueOf(file.getEventNum()), "风险事件数",
					file.getRuleName());

		}

		JFreeChart chart = ChartFactory.createBarChart3D("", "", "风险事件数",
				dataset, PlotOrientation.VERTICAL, false, true, true);
		// HttpSession session=request.getSession();

		CategoryPlot plot=chart.getCategoryPlot();
		CategoryItemRenderer renderer=plot.getRenderer();
		
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions
				.createDownRotationLabelPositions(Math.PI / 2));
		domainAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 12));
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		domainAxis.setLabel("");
		domainAxis.setCategoryLabelPositionOffset(10);
		domainAxis.setCategoryMargin(0.5);
		domainAxis.setLowerMargin(0.03);

		plot.setBackgroundPaint(new Color(221, 253, 177)); // 背景色设置
		plot.setRangeGridlinePaint(ChartColor.GRAY); // 网格线色设置
		plot.setNoDataMessage("No data available");
		// cp.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
		// cp.setDomainAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
		// 使纵坐标的最小单位格为整数
		NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();
		numberaxis
				.setStandardTickUnits(NumberAxis.createIntegerTickUnits());		
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator()); 
		renderer.setBaseItemLabelsVisible(true); 
		renderer.setBaseItemLabelPaint(Color.BLACK);//设置数值颜色，默认黑色 
		//搭配ItemLabelAnchor TextAnchor 这两项能达到不同的效果，但是ItemLabelAnchor最好选OUTSIDE，因为INSIDE显示不出来 
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.CENTER_LEFT));
		//使不产生url链接
		renderer.setBaseItemURLGenerator(null);		
		//定义url连接位置和参数
		renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator("{1}",NumberFormat.getIntegerInstance()));				
		//创建chartRenderingInfo对象
		ChartRenderingInfo info=new ChartRenderingInfo(new StandardEntityCollection());

		try {
			
			  
			  filenamex = ServletUtilities.saveChartAsPNG(chart, 810, 600,info, request.getSession());		
		//创建热点的区域位置信息
		String mapx=ChartUtilities.getImageMap(filenamex, info);

		String imageUrlx="upload/ruleBarchart" + userId + ".png";
		String filename1 = ServletActionContext.getServletContext()
				.getRealPath(imageUrlx);
		ChartUtilities.saveChartAsPNG(new File(filename1), chart, 810,
				600);
		request.setAttribute("imageurlx", imageUrlx);
		request.setAttribute("filenamex", filenamex);
		request.setAttribute("mapx",mapx);
		return imageUrlx;
			 
			/*graphURL = "upload/ruleBarchart" + userId + ".png";
			filename = ServletActionContext.getServletContext().getRealPath(
					graphURL);
			ChartUtilities.saveChartAsPNG(new File(filename), chart, 810, 600);*/
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String drawKeyDepEventNumBarChart(int i, int j,
			List<RiAnalysis> depEventNumList) {
		// TODO Auto-generated method stub
		if (!depEventNumList.isEmpty()) {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Users us = (Users) session.getAttribute("loginUser");
			String userId = us.getUserId();
			String filename = "";
			String graphURL = "";
			String depname = "";
			// 柱状图中文乱码问题
			StandardChartTheme theme = new StandardChartTheme("CN") {
				// 重写apply(...)方法借机消除文字锯齿.VALUE_TEXT_ANTIALIAS_OFF
				public void apply(JFreeChart chart) {
					chart.getRenderingHints().put(
							RenderingHints.KEY_TEXT_ANTIALIASING,
							RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
					super.apply(chart);
				}
			};
			// 标题乱码解决
			// theme.setExtraLargeFont(new Font("宋体",Font.BOLD,25));
			// 图例乱码解决
			theme.setRegularFont(new Font("宋体", Font.PLAIN, 14));
			theme.setLargeFont(new Font("宋体", Font.BOLD, 20));
			theme.setSmallFont(new Font("宋体", Font.PLAIN, 14));
			theme.setBaselinePaint(java.awt.Color.white);
			// 应用主题样式
			ChartFactory.setChartTheme(theme);

			// 柱状图
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			// 讲二级风险按照风险事件数大小从高到低排序
			Collections.sort(depEventNumList, new Comparator<RiAnalysis>() {
				public int compare(RiAnalysis r1, RiAnalysis r2) {
					return Integer.valueOf(r2.getReEventname())
							- Integer.valueOf(r1.getReEventname());
				}
			});
			for (RiAnalysis riAnalysis : depEventNumList) {
				depname = riAnalysis.getDepName().trim();
				if (depname.equals("湖北中南电力工程建设监理有限责任公司")) {
					depname = "监理有限责任公司";
				}
				if (depname.equals("武汉南方岩土工程技术有限责任公司")) {
					depname = "南方岩土工程技术有限责任公司";
				}
				dataset.addValue(Integer.valueOf(riAnalysis.getReEventname()),
						"风险事件数", depname);
			}

			JFreeChart chart = ChartFactory.createBarChart3D("", "部门名称",
					"风险事件数", dataset, PlotOrientation.VERTICAL, false, true,
					true);

			// HttpSession session=request.getSession();

			CategoryPlot cp = chart.getCategoryPlot();
			// cp.setBackgroundPaint(ChartColor. WHITE ); // 背景色设置
			cp.setRangeGridlinePaint(ChartColor.GRAY); // 网格线色设置
			cp.setNoDataMessage("No data available");
			// 使纵坐标的最小单位格为整数
			NumberAxis numberaxis = (NumberAxis) cp.getRangeAxis();
			numberaxis
					.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			// 使用自定义的渲染器
			BarColorRenderer barrenderer = new BarColorRenderer();
			barrenderer
					.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
			barrenderer.setBaseItemLabelsVisible(true);
			barrenderer.setMaximumBarWidth(0.03);
			// 设置柱子的倒影不可见
			barrenderer.setShadowVisible(false);
			// GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F,
			// Color.blue,
			// 0.0F, 0.0F, new Color(135, 206, 235)); //设定特定颜色
			barrenderer
					.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());// 显示每个柱的数值
			barrenderer.setBaseItemLabelsVisible(true);
			// 注意：此句很关键，若无此句，那数字的显示会被覆盖，给人数字没有显示出来的问题
			barrenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
					ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
			barrenderer.setItemLabelAnchorOffset(10D);// 设置柱形图上的文字偏离值
			barrenderer.setItemLabelsVisible(true);
			barrenderer.setDrawBarOutline(false);
			barrenderer.setItemLabelFont(new Font("宋体", Font.BOLD, 12));// 12号黑体加粗
			barrenderer.setItemLabelPaint(Color.black);// 字体为黑色
			barrenderer.setItemMargin(0.0);
			cp.setRenderer(barrenderer);
			cp.setBackgroundPaint(new Color(221, 253, 177));
			CategoryAxis domainAxis = cp.getDomainAxis();
			domainAxis.setCategoryLabelPositions(CategoryLabelPositions
					.createDownRotationLabelPositions(Math.PI / 2));
			domainAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 8));
			domainAxis.setLabel("");
			domainAxis.setCategoryLabelPositionOffset(10);
			domainAxis.setCategoryMargin(0.5);
			domainAxis.setLowerMargin(0.03);
			cp.setForegroundAlpha(1);
			ValueAxis rangeAxis = cp.getRangeAxis();
			// 设置最高的一个 Item 与图片顶端的距离
			rangeAxis.setUpperMargin(0.1);
			// if(Integer.valueOf(depEventNumList.get(0).getReEventname())<=3){
			// rangeAxis.setRange(0, 4);
			// }
			rangeAxis.setRange(0, Integer.valueOf(Integer
					.valueOf(depEventNumList.get(0).getReEventname())) + 1);

			try {
				Date dt = new Date();
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd-HH-mm-ss");
				graphURL = "upload/" + userId + "KeyDepEventNumBarchart"
						+ df.format(dt) + ".png";
				filename = ServletActionContext.getServletContext()
						.getRealPath(graphURL);
				ChartUtilities.saveChartAsPNG(new File(filename), chart, i, j);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return graphURL;
		} else {
			return "";
		}
	}

	public static String drawRiskDepPieChart(int i, int j, List riskDepList) {
		// TODO Auto-generated method stub

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Users us = (Users) session.getAttribute("loginUser");
		String userId = us.getUserId();
		String graphURL = null;
		DefaultPieDataset dataset = new DefaultPieDataset();
		for (Object riskDep : riskDepList) {
			Object[] dep = (Object[]) riskDep;
			dataset.setValue((String) dep[0], (Long) dep[1]);
		}
		JFreeChart chart = ChartFactory.createPieChart("", dataset, false,
				false, false);
		// 设置pieChart的标题和字体
		chart.setTextAntiAlias(false);
		//  设置背景色  
		// chart.setBackgroundPaint(new Color(255,255,255));
		chart.setBackgroundPaint(ChartColor.WHITE);
		// 设置图例字体
		// LegendTitle legend = chart.getLegend(0);
		// legend.setItemFont(new Font("黑体", Font.BOLD, 17));
		//  设置标签字体  
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("宋体", Font.BOLD, 17));
		// 设置饼图是圆的（true），还是椭圆的（false）；默认为true
		plot.setCircular(true);
		//  指定图片的透明度(0.0-1.0)          
		plot.setForegroundAlpha(1);
		//  图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位 
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}={1}({2})", NumberFormat.getNumberInstance(),
				new DecimalFormat("0.00%")));
		//  图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例  
		// plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(
		// "{0} ({2})", NumberFormat.getNumberInstance(),
		// new DecimalFormat("0.00%")));
		//  设置第一个饼块截面开始的位置，默认是12点钟方向          
		plot.setStartAngle(90);
		// 第一个参数是key,第二个参数是突出显示的大小（可以自己调整一下看看效果就明白了）
		// plot.setExplodePercent("A区域",0.23);
		plot.setBackgroundPaint(new Color(231, 253, 199));
		plot.setLabelFont(new Font("宋体", Font.BOLD, 15));
		plot.setMaximumLabelWidth(0.15);
		plot.setShadowGenerator(new ShadowGenerator() {

			@Override
			public BufferedImage createDropShadow(BufferedImage arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int calculateOffsetY() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int calculateOffsetX() {
				// TODO Auto-generated method stub
				return 0;
			}
		});
		try {
			// filename = ServletUtilities.saveChartAsJPEG(chart, 800, 500,
			// null,session);

			Date dt = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			graphURL = "upload/" + userId + "riskDepPiechart" + df.format(dt)
					+ ".png";
			String filename = ServletActionContext.getServletContext()
					.getRealPath(graphURL);
			ChartUtilities.saveChartAsPNG(new File(filename), chart, i, j);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return graphURL;
	}
}
