package com.action.riskStatistics;

import java.io.*;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.action.analysis.BarColorRenderer;
import com.action.riskStatistics.info;
import com.entity.ReportViewNew;
import com.entity.depStatistic;
import com.entity.eventStatistic;
import com.entity.linkStatistic;

import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import com.model.ReportView;
import com.model.Users;
import com.sun.image.codec.jpeg.*;

public class graphic {

	private static int _heightA;

	public static void drawTable(int width, int height, List<ReportView> lists)
			throws IOException {
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
		g.setFont(new Font("??????", Font.BOLD, 17));
		g.drawString("????????????", width - 145, height - 50);
		g.drawLine(60, height - 50, 60, 50);
		g.drawLine(60, 50, 55, 60);
		g.drawLine(60, 50, 65, 60);
		g.setColor(Color.black);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setFont(new Font("??????", Font.BOLD, 17));
		g.drawString("?????????", 70, 50);
		g.drawString("??????", 100, height - 30);
		g.drawString("???", 220, height - 30);
		g.drawString("??????", 320, height - 30);
		g.drawString("???", 440, height - 30);
		g.drawString("??????", 540, height - 30);
		String[][] infos = { { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" } };
		for (int n = 0; n < lists.size(); n++) {
			if (lists.get(n).getRiskPro() > 5 || lists.get(n).getRiskPro() < 1)
				continue;// ????????????????????????1???5??????
			if (lists.get(n).getRiskValuex() > 5
					|| lists.get(n).getRiskValuex() < 1)
				continue;// ?????????????????????1???5??????
			infos[(lists.get(n).getRiskPro() - 1)][(lists.get(n)
					.getRiskValuex() - 1)] += "("
					+ String.valueOf(lists.get(n).getRiskQueue()) + ")*";
		}
		// end draw:
		int rowheight_max = height - 50;
		for (int i = 0; i < 5; i++) {
			int temp_height = cal_Max_height(infos[i]) * 20;
			rowheight_max -= temp_height + 100;
			for (int j = 0; j < 5; j++) {
				/*
				 * ?????????????????????????????????
				 */
				g.drawRect(60 + j * 110, rowheight_max, 110, temp_height + 100);
				/*
				 * ????????????lists ??????a[5][5]????????????25???????????????
				 */
				String[] sequence = infos[i][j].split("\\*");
				int count = sequence.length;
				int start_x = 60 + j * 110 + 1;
				int start_y = rowheight_max + 20;
				int x = start_x;
				int y = start_y;
				for (int n = 0; n < count; n++) {
					if ((n) % 3 == 0) {
						g.drawString(sequence[n], start_x, y + 20);
						y = y + 20;
						x = start_x + 35;
					} else {
						g.drawString(sequence[n], x, y);
						x = x + 35;
					}
				}
			}
			String Y_str = "";
			if (0 == i)
				Y_str = "??????";
			else if (1 == i)
				Y_str = "???";
			else if (2 == i)
				Y_str = "??????";
			else if (3 == i)
				Y_str = "???";
			else if (4 == i)
				Y_str = "??????";
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

	/*
	 * ????????????
	 */
	public static String drawTableShadow(int width, int height,
			List<ReportView> lists) throws IOException {
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
		g.setFont(new Font("??????", Font.BOLD, 17));
		g.drawString("????????????", width - 145, height - 50);
		g.drawLine(60, height - 50, 60, 50);
		g.drawLine(60, 50, 55, 60);
		g.drawLine(60, 50, 65, 60);
		g.setColor(Color.black);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setFont(new Font("??????", Font.BOLD, 17));
		g.drawString("?????????", 70, 50);
		g.drawString("??????", 100, height - 30);
		g.drawString("???", 220, height - 30);
		g.drawString("??????", 320, height - 30);
		g.drawString("???", 440, height - 30);
		g.drawString("??????", 540, height - 30);
		String[][] infos = { { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" } };
		for (int n = 0; n < lists.size(); n++) {
			if (lists.get(n).getRiskPro() > 5 || lists.get(n).getRiskPro() < 1)
				continue;// ????????????????????????1???5??????
			if (lists.get(n).getRiskValuex() > 5
					|| lists.get(n).getRiskValuex() < 1)
				continue;// ?????????????????????1???5??????

			infos[(lists.get(n).getRiskPro() - 1)][(lists.get(n)
					.getRiskValuex() - 1)] += "("
					+ String.valueOf(lists.get(n).getRiskQueue()) + ")*";
		}
		// end draw:
		int rowheight_max = height - 50;
		for (int i = 0; i < 5; i++) {
			int temp_height = cal_Max_height(infos[i]) * 20;
			rowheight_max -= temp_height + 100;
			for (int j = 0; j < 5; j++) {
				/*
				 * ?????????????????????????????????
				 */

				g.drawRect(60 + j * 110, rowheight_max, 110, temp_height + 100);
				/*
				 * ???????????????
				 */
				if (1 >= i && 2 >= j) {
					g.setBackground(new Color(198, 249, 184));// ??????
					g.clearRect(60 + j * 110, rowheight_max, 110,
							temp_height + 100);
					Rectangle2D rect = new Rectangle2D.Float(60 + j * 110,
							rowheight_max, 110, temp_height + 100);
					g.draw(rect);
				} else if (3 <= i && 3 <= j) {
					g.setBackground(new Color(248, 195, 195));// ??????
					g.clearRect(60 + j * 110, rowheight_max, 110,
							temp_height + 100);
					Rectangle2D rect = new Rectangle2D.Float(60 + j * 110,
							rowheight_max, 110, temp_height + 100);
					g.draw(rect);
				} else {
					g.setBackground(new Color(253, 236, 182));// ??????
					g.clearRect(60 + j * 110, rowheight_max, 110,
							temp_height + 100);
					Rectangle2D rect = new Rectangle2D.Float(60 + j * 110,
							rowheight_max, 110, temp_height + 100);
					g.draw(rect);
				}
				/*
				 * ?????????????????????
				 */
				if (1 == i && 2 == j) {
					_heightA = rowheight_max;
					// A?????????????????????????????????
				} else if (0 == i && 4 == j) {

					g.setBackground(new Color(253, 227, 142));
					g.drawLine(60 + j * 110 + 80, rowheight_max + 50,
							width - 150, rowheight_max + 50);
					g.drawLine(width - 150, rowheight_max + 50, width - 160,
							rowheight_max + 45);
					g.drawLine(width - 150, rowheight_max + 50, width - 160,
							rowheight_max + 55);
					g.setBackground(new Color(253, 236, 182));// ??????
					g.clearRect(width - 150, rowheight_max + 25, 80, 50);
					Rectangle2D rect = new Rectangle2D.Float(width - 150,
							rowheight_max + 25, 80, 50);
					g.draw(rect);
					g.drawString("B??????", width - 140, rowheight_max + 50);
				} else if (4 == i && 4 == j) {
					// g.setBackground(new Color(246,144,158));
					g.drawLine(60 + j * 110 + 80, rowheight_max + 50,
							width - 150, rowheight_max + 50);
					g.drawLine(width - 150, rowheight_max + 50, width - 160,
							rowheight_max + 45);
					g.drawLine(width - 150, rowheight_max + 50, width - 160,
							rowheight_max + 55);
					g.setBackground(new Color(248, 195, 195));// ??????
					g.clearRect(width - 150, rowheight_max + 25, 80, 50);
					Rectangle2D rect = new Rectangle2D.Float(width - 150,
							rowheight_max + 25, 80, 50);
					g.draw(rect);
					g.drawString("C??????", width - 140, rowheight_max + 50);
				}
				/*
				 * ????????????lists ??????a[5][5]????????????25???????????????
				 */
				String[] sequence = infos[i][j].split("\\*");
				int count = sequence.length;
				int start_x = 60 + j * 110 + 1;
				int start_y = rowheight_max + 20;
				int x = start_x;
				int y = start_y;
				for (int n = 0; n < count; n++) {
					if ((n) % 3 == 0) {
						g.drawString(sequence[n], start_x, y + 20);
						y = y + 20;
						x = start_x + 35;
					} else {
						g.drawString(sequence[n], x, y);
						x = x + 35;
					}
				}
			}
			String Y_str = "";
			if (0 == i)
				Y_str = "??????";
			else if (1 == i)
				Y_str = "???";
			else if (2 == i)
				Y_str = "??????";
			else if (3 == i)
				Y_str = "???";
			else if (4 == i)
				Y_str = "??????";
			g.drawString(Y_str, 8, rowheight_max + 50 + temp_height / 2);
		}

		g
				.drawLine(60 + 2 * 110 + 80, _heightA + 50, width - 150,
						_heightA - 50);
		g.drawLine(width - 150, _heightA - 50, width - 162, _heightA - 53);
		g.drawLine(width - 150, _heightA - 50, width - 160, _heightA - 40);
		g.setBackground(new Color(198, 249, 184));// ??????
		g.clearRect(width - 150, _heightA - 70, 80, 50);
		Rectangle2D rect = new Rectangle2D.Float(width - 150, _heightA - 70,
				80, 50);
		g.draw(rect);
		g.drawString("A??????", width - 140, _heightA - 40);

		g.dispose();
		bi.flush();
		Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

		String filename = "upload/reportImgShadow" + df.format(dt) + ".jpg";
		String savePath = ServletActionContext.getServletContext().getRealPath(
				filename);
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
	}

	public static int cal_Max_height(String[] infos) {
		int height_max[] = new int[5];
		int max = 0;
		for (int n = 0; n < infos.length; n++) {
			String temp_str = infos[n];
			if (temp_str.length() > 0) {
				int temp = (temp_str.split("\\*")).length;
				if (temp > max)
					max = temp;
			}
		}
		if (max % 3 == 0)
			return max / 3;
		else {
			return (max / 3) + 1;
		}
	}

	public static String drawBarChart(List<eventStatistic> esList) {
		// TODO Auto-generated method stub
		if (!esList.isEmpty()) {
			// int width=0;
			// int height=0;
			// if(esList.size()<=5){
			// width=500;
			// height=450;
			// }else{
			// width=1000;
			// height=550;
			// }
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Users us = (Users) session.getAttribute("loginUser");
			String userId = us.getUserId();
			String filename = "";
			String graphURL = "";
			// ???????????????????????????
			StandardChartTheme theme = new StandardChartTheme("CN") {
				// ??????apply(...)??????????????????????????????.VALUE_TEXT_ANTIALIAS_OFF
				public void apply(JFreeChart chart) {
					chart.getRenderingHints().put(
							RenderingHints.KEY_TEXT_ANTIALIASING,
							RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
					super.apply(chart);
				}
			};
			// ??????????????????
			// theme.setExtraLargeFont(new Font("??????",Font.BOLD,25));
			// ??????????????????
			theme.setRegularFont(new Font("??????", Font.PLAIN, 14));
			theme.setLargeFont(new Font("??????", Font.BOLD, 20));
			theme.setSmallFont(new Font("??????", Font.PLAIN, 14));
			theme.setBaselinePaint(java.awt.Color.white);
			// ??????????????????
			ChartFactory.setChartTheme(theme);

			// ?????????
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();

			List<eventStatistic> useList = new ArrayList<eventStatistic>();
			useList.addAll(esList);
			// ????????????????????????????????????????????????????????????
			Collections.sort(useList, new Comparator<eventStatistic>() {
				public int compare(eventStatistic e1, eventStatistic e2) {
					return e2.getEventnum() - e1.getEventnum();
				}
			});
			for (eventStatistic es : useList) {
				dataset.addValue(es.getEventnum(), "???????????????", es.getRiskType());
			}

			JFreeChart chart = ChartFactory.createBarChart3D("", "", "???????????????",
					dataset, PlotOrientation.VERTICAL, false, true, false);

			// HttpSession session=request.getSession();

			CategoryPlot cp = chart.getCategoryPlot();
			cp.setBackgroundPaint(new Color(221, 253, 177)); // ???????????????
			cp.setRangeGridlinePaint(ChartColor.GRAY); // ??????????????????
			cp.setNoDataMessage("No data available");
			// ???????????????????????????????????????
			NumberAxis numberaxis = (NumberAxis) cp.getRangeAxis();
			numberaxis
					.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			BarColorRenderer barrenderer = new BarColorRenderer();
			barrenderer
					.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
			barrenderer.setBaseItemLabelsVisible(true);
			barrenderer.setMaximumBarWidth(0.05);
			// ??????????????????????????????
			barrenderer.setShadowVisible(false);
			barrenderer
					.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());// ????????????????????????
			barrenderer.setBaseItemLabelsVisible(true);
			// ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????
			barrenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
					ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
			barrenderer.setItemLabelAnchorOffset(10D);// ????????????????????????????????????
			barrenderer.setItemLabelsVisible(true);
			barrenderer.setDrawBarOutline(false);
			barrenderer.setItemLabelFont(new Font("??????", Font.BOLD, 12));// 12???????????????
			barrenderer.setItemLabelPaint(Color.black);// ???????????????
			cp.setRenderer(barrenderer);
			cp.setForegroundAlpha(1);

			ValueAxis rangeAxis = cp.getRangeAxis();
			// ????????????????????? Item ????????????????????????
			rangeAxis.setUpperMargin(0.1);
			rangeAxis.setRange(0, useList.get(0).getEventnum() + 2);
			// GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F,
			// Color.blue,
			// 0.0F, 0.0F, new Color(135, 206, 235)); //??????????????????
			// barrenderer.setSeriesPaint(0, gradientpaint);
			// CategoryAxis domainAxis=cp.getDomainAxis();
			// domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createDownRotationLabelPositions(Math.PI
			// / 4));
			try {
				Date dt = new Date();
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd-HH-mm-ss");
				graphURL = "upload/" + userId + "rtStatisticsBarchart"
						+ df.format(dt) + ".png";
				filename = ServletActionContext.getServletContext()
						.getRealPath(graphURL);
				ChartUtilities.saveChartAsPNG(new File(filename), chart, 600,
						300);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return graphURL;
		} else {
			return "";
		}
	}

	public static String drawDepBarChart(int i, int j, List<depStatistic> dsList) {
		// TODO Auto-generated method stub
		if (!dsList.isEmpty()) {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Users us = (Users) session.getAttribute("loginUser");
			String userId = us.getUserId();
			String filename = "";
			String graphURL = "";
			String depname="";
			// ???????????????????????????
			StandardChartTheme theme = new StandardChartTheme("CN") {
				// ??????apply(...)??????????????????????????????.VALUE_TEXT_ANTIALIAS_OFF
				public void apply(JFreeChart chart) {
					chart.getRenderingHints().put(
							RenderingHints.KEY_TEXT_ANTIALIASING,
							RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
					super.apply(chart);
				}
			};
			// ??????????????????
			// theme.setExtraLargeFont(new Font("??????",Font.BOLD,25));
			// ??????????????????
			theme.setRegularFont(new Font("??????", Font.PLAIN, 14));
			theme.setLargeFont(new Font("??????", Font.BOLD, 20));
			theme.setSmallFont(new Font("??????", Font.PLAIN, 14));
			theme.setBaselinePaint(java.awt.Color.white);
			// ??????????????????
			ChartFactory.setChartTheme(theme);

			// ?????????
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();

			List<depStatistic> useList = new ArrayList<depStatistic>();
			useList.addAll(dsList);
			// ????????????????????????????????????????????????????????????
			Collections.sort(useList, new Comparator<depStatistic>() {
				public int compare(depStatistic d1, depStatistic d2) {
					return d2.getNum() - d1.getNum();
				}
			});
			for (depStatistic ds : useList) {
				depname=ds.getReIndep().trim();
				if(depname.equals("??????????????????????????????????????????????????????")){
					depname="????????????????????????";
				}
				if(depname.equals("????????????????????????????????????????????????")){
					depname="??????????????????????????????????????????";
				}
				dataset.addValue(ds.getNum(), "???????????????", depname);
			}

			JFreeChart chart = ChartFactory.createBarChart3D("", "", "???????????????",
					dataset, PlotOrientation.VERTICAL, false, true, false);

			// HttpSession session=request.getSession();

			CategoryPlot cp = chart.getCategoryPlot();
			cp.setBackgroundPaint(new Color(221, 253, 177)); // ???????????????
			cp.setRangeGridlinePaint(ChartColor.GRAY); // ??????????????????
			cp.setNoDataMessage("No data available");
			// ???????????????????????????????????????
			NumberAxis numberaxis = (NumberAxis) cp.getRangeAxis();
			numberaxis
					.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			BarColorRenderer barrenderer = new BarColorRenderer();
			barrenderer
					.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
			barrenderer.setBaseItemLabelsVisible(true);
			barrenderer.setMaximumBarWidth(0.03);
			// ??????????????????????????????
			barrenderer.setShadowVisible(false);
			barrenderer
					.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());// ????????????????????????
			barrenderer.setBaseItemLabelsVisible(true);
			// ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????
			barrenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
					ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
			barrenderer.setItemLabelAnchorOffset(10D);// ????????????????????????????????????
			barrenderer.setItemLabelsVisible(true);
			barrenderer.setDrawBarOutline(false);
			barrenderer.setItemLabelFont(new Font("??????", Font.BOLD, 12));// 12???????????????
			barrenderer.setItemLabelPaint(Color.black);// ???????????????
			// GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F,
			// Color.blue,
			// 0.0F, 0.0F, new Color(135, 206, 235)); //??????????????????
			// barrenderer.setSeriesPaint(0, gradientpaint);
			cp.setRenderer(barrenderer);
			cp.setForegroundAlpha(1);
			CategoryAxis domainAxis = cp.getDomainAxis();
			domainAxis.setCategoryLabelPositions(CategoryLabelPositions
					.createDownRotationLabelPositions(Math.PI / 2));
			ValueAxis rangeAxis = cp.getRangeAxis();
			// ????????????????????? Item ????????????????????????
			rangeAxis.setUpperMargin(0.1);
			rangeAxis.setRange(0, useList.get(0).getNum() + 2);
			try {
				Date dt = new Date();
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd-HH-mm-ss");
				graphURL = "upload/" + userId + "depStatisticsBarchart"
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

	public static String drawFileBarChart(List<linkStatistic> lsList) {
		// TODO Auto-generated method stub
		if (!lsList.isEmpty()) {
			// int width=0;
			// int height=0;
			// if(lsList.size()<=5){
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
			String graphURL = "";
			// ???????????????????????????
			StandardChartTheme theme = new StandardChartTheme("CN") {
				// ??????apply(...)??????????????????????????????.VALUE_TEXT_ANTIALIAS_OFF
				public void apply(JFreeChart chart) {
					chart.getRenderingHints().put(
							RenderingHints.KEY_TEXT_ANTIALIASING,
							RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
					super.apply(chart);
				}
			};
			// ??????????????????
			// theme.setExtraLargeFont(new Font("??????",Font.BOLD,25));
			// ??????????????????
			theme.setRegularFont(new Font("??????", Font.PLAIN, 14));
			theme.setLargeFont(new Font("??????", Font.BOLD, 20));
			theme.setSmallFont(new Font("??????", Font.PLAIN, 14));
			theme.setBaselinePaint(java.awt.Color.white);
			// ??????????????????
			ChartFactory.setChartTheme(theme);

			// ?????????
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();

			List<linkStatistic> useList = new ArrayList<linkStatistic>();
			useList.addAll(lsList);
			// ????????????????????????????????????????????????????????????
			Collections.sort(useList, new Comparator<linkStatistic>() {
				public int compare(linkStatistic l1, linkStatistic l2) {
					return l2.getEventnum() - l1.getEventnum();
				}
			});
			for (linkStatistic ls : useList) {
				dataset.addValue(ls.getEventnum(), "???????????????", ls.getFileName());
			}

			JFreeChart chart = ChartFactory.createBarChart3D("", "", "???????????????",
					dataset, PlotOrientation.VERTICAL, false, true, false);

			// HttpSession session=request.getSession();

			CategoryPlot cp = chart.getCategoryPlot();
			cp.setBackgroundPaint(new Color(221, 253, 177)); // ???????????????
			cp.setRangeGridlinePaint(ChartColor.GRAY); // ??????????????????
			cp.setNoDataMessage("No data available");
			// ???????????????????????????????????????
			NumberAxis numberaxis = (NumberAxis) cp.getRangeAxis();
			numberaxis
					.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			BarColorRenderer barrenderer = new BarColorRenderer();
			barrenderer
					.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
			barrenderer.setBaseItemLabelsVisible(true);
			barrenderer.setMaximumBarWidth(0.03);
			// ??????????????????????????????
			barrenderer.setShadowVisible(false);
			barrenderer
					.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());// ????????????????????????
			barrenderer.setBaseItemLabelsVisible(true);
			// ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????
			barrenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
					ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
			barrenderer.setItemLabelAnchorOffset(10D);// ????????????????????????????????????
			barrenderer.setItemLabelsVisible(true);
			barrenderer.setDrawBarOutline(false);
			barrenderer.setItemLabelFont(new Font("??????", Font.BOLD, 12));// 12???????????????
			barrenderer.setItemLabelPaint(Color.black);// ???????????????
			cp.setRenderer(barrenderer);
			cp.setForegroundAlpha(1);
			ValueAxis rangeAxis = cp.getRangeAxis();
			// ????????????????????? Item ????????????????????????
			rangeAxis.setUpperMargin(0.1);
			rangeAxis.setRange(0, useList.get(0).getEventnum() + 2);
			// GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F,
			// Color.blue,
			// 0.0F, 0.0F, new Color(135, 206, 235)); //??????????????????
			// barrenderer.setSeriesPaint(0, gradientpaint);
			CategoryAxis domainAxis = cp.getDomainAxis();
			domainAxis.setCategoryLabelPositions(CategoryLabelPositions
					.createDownRotationLabelPositions(Math.PI / 2));
			try {
				Date dt = new Date();
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd-HH-mm-ss");
				graphURL = "upload/" + userId + "fileStatisticsBarchart"
						+ df.format(dt) + ".png";
				filename = ServletActionContext.getServletContext()
						.getRealPath(graphURL);
				ChartUtilities.saveChartAsPNG(new File(filename), chart, 700,
						550);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return graphURL;
		} else {
			return "";
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

}
