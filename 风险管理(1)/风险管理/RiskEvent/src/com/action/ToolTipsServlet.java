package com.action;

import java.awt.Font;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;

import com.entity.ReportViewNew;

@SuppressWarnings("serial")
public class ToolTipsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ToolTipsServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @return 
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@SuppressWarnings("unused")
	public static String doPost(List<ReportViewNew> reportList,HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		
//		String rowkeys1[]={};
//		String columnKeys1[]={};
		List<ReportViewNew> useList = new ArrayList<ReportViewNew>();
		useList.addAll(reportList);
		Collections.sort(useList, new Comparator<ReportViewNew>() {
			public int compare(ReportViewNew o1, ReportViewNew o2) {
				return Integer.valueOf(o2.getRiskNum())
						- Integer.valueOf(o1.getRiskNum());
			}
		});
//		for(int m=0;m<=useList.size();m++){
//			rowkeys1[m]=useList.get(m).getRiskName();
//		}
//		for(int n=0;n<=useList.size();n++){
//			columnKeys1[n]=useList.get(n).getRiskNum();
//		}
		
		//创建数据源对象		
		DefaultCategoryDataset dataset=new DefaultCategoryDataset();
		for (ReportViewNew reportViewNew : useList) {
			dataset.addValue(Integer.valueOf(reportViewNew.getRiskNum()),
					"风险事件数", reportViewNew.getRiskName());
		}
		//设置主题
		StandardChartTheme theme1=new StandardChartTheme("CN");
		theme1.setExtraLargeFont(new Font("黑体",Font.PLAIN,20));
		theme1.setLargeFont(new Font("宋体",Font.PLAIN,12));
		theme1.setRegularFont(new Font("宋体",Font.PLAIN,12));
		theme1.setSmallFont(new Font("宋体",Font.PLAIN,12));
		
		//让工厂应用主题		
		ChartFactory.setChartTheme(theme1);
		
		//创建jfreechart
		JFreeChart chart=ChartFactory.createBarChart3D("","风险事件数","",dataset, PlotOrientation.VERTICAL, true, true, true);
		
		CategoryPlot plot=chart.getCategoryPlot();
		CategoryItemRenderer renderer=plot.getRenderer();
		
		//使不产生url链接
		renderer.setBaseItemURLGenerator(null);
		
		//定义url连接位置和参数
		renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator("{0}",NumberFormat.getIntegerInstance()));
		
		
		//创建chartRenderingInfo对象
		ChartRenderingInfo info=new ChartRenderingInfo(new StandardEntityCollection());
		String filename=ServletUtilities.saveChartAsPNG(chart, 810, 600, request.getSession());
		
		
		return null;
		

		
		
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
