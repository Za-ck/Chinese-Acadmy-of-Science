package com.action.riskEvaluate;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.dao.InvestmentProjectDAO;
import com.dao.ProjectElementDAO;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import com.model.InvestmentProject;
import com.model.ProjectElement;
import com.model.RiskEvaluateInternation;
import com.model.Users;




public class evaluateReportAction {

	private InvestmentProject investmentProject;
	private InvestmentProjectDAO investmentProjectDao;
	private List<ProjectElement> projectElement;
	private ProjectElementDAO projectElementDao;
	

	public InvestmentProject getInvestmentProject() {
		return investmentProject;
	}

	public void setInvestmentProject(InvestmentProject investmentProject) {
		this.investmentProject = investmentProject;
	}

	public InvestmentProjectDAO getInvestmentProjectDao() {
		return investmentProjectDao;
	}

	public void setInvestmentProjectDao(InvestmentProjectDAO investmentProjectDao) {
		this.investmentProjectDao = investmentProjectDao;
	}
	
	public List<ProjectElement> getProjectElement() {
		return projectElement;
	}

	public void setProjectElement(List<ProjectElement> projectElement) {
		this.projectElement = projectElement;
	}

	public ProjectElementDAO getProjectElementDao() {
		return projectElementDao;
	}

	public void setProjectElementDao(ProjectElementDAO projectElementDao) {
		this.projectElementDao = projectElementDao;
	}

	// 全面风险事件坐标图生成
	public String exportWord() {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
	    Users us = (Users) session.getAttribute("loginUser");
	    String userId=us.getUserId();
		String projectId = request.getParameter("Project_id");
		String path = "upload/" +projectId+".png"; 
		String FileDefaultName ="风险评估结果图";
				
        investmentProject = investmentProjectDao.findById(projectId);   //评估结果
//        projectElement = new LinkedList<ProjectElement>();
//        projectElement = projectElementDao.findByPeProjectid(projectId);  //风险因素和可能原因
        Map<String,LinkedList<ProjectElement>> resultMap=(Map<String,LinkedList<ProjectElement>>)session.getAttribute(projectId+"resultMap");
        
        
		try {
			createWordFile(FileDefaultName, path,investmentProject,resultMap, projectId);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "success";

	}
	
	// 保存或覆盖前全面风险事件坐标图生成
	public String tempExportWord() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
	    Users us = (Users) session.getAttribute("loginUser");
	    String userId=us.getUserId();
		String projectId = request.getParameter("Project_id");
		String path = "upload/"+userId+"tempInvestmentProject"+projectId+".png"; 
		String FileDefaultName ="风险评估结果图(临时)";
		investmentProject=(InvestmentProject)session.getAttribute("investmentProject");       
//		projectElement=(List<ProjectElement>)session.getAttribute("peList");
		Map<String,LinkedList<ProjectElement>> resultMap=(Map<String,LinkedList<ProjectElement>>)session.getAttribute("resultMap");
		try {
			createWordFile(FileDefaultName, path,investmentProject,resultMap, projectId);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "success";

	}
	
	/*
	 * 生成word文档
	 */
	public boolean createWordFile(String FileDefaultName,
			String path, InvestmentProject ip, Map<String,LinkedList<ProjectElement>> resultMap,String projectId) throws DocumentException, IOException {
		
		FileDefaultName = new String(FileDefaultName.getBytes(), "iso8859-1");// 解决中文
		// 文件名问题
		HttpServletResponse response = ServletActionContext.getResponse();
		OutputStream os = response.getOutputStream();
		response.reset();
		response.addHeader("Content-Disposition", "attachment;filename="
		+ FileDefaultName + ".doc");
		response.setContentType("application/msword");
		
		// 设置纸张大小
		Document document = new Document(PageSize.A4);
		// 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中
		RtfWriter2.getInstance(document, os);
		document.open();
		// 设置中文字体
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light",
		"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		// 标题字体风格
		Font titleFont = new Font(bfChinese, 12, Font.BOLD);
		// 正文字体风格
		Font contextFont = new Font(bfChinese, 10, Font.NORMAL);
		Paragraph title = new Paragraph("项目评估结果报告");
		// 设置标题格式对齐方式
		title.setAlignment(Element.ALIGN_CENTER);
		title.setFont(titleFont);
		document.add(title);
		
		//插入评估结果表1
		String contextString1  = "（一）项目评估结果见下表1";
		Paragraph context1 = new Paragraph(contextString1);
		document.add(context1);
		String contextString2 = "表1   项目评估结果";
		Paragraph context2 = new Paragraph(contextString2);
		context2.setAlignment(Element.ALIGN_CENTER);
		context2.setFont(contextFont);
		// 设置第一行空的列数
		context2.setFirstLineIndent(20);
		document.add(context2);
			
		Table rTable = new Table(7);
		int rwidth[] = {20,20,20,20,20,20,20};
		rTable.setWidths(rwidth);// 设置每列所占比例
		rTable.setWidth(90); // 占页面宽度 90%
		rTable.setAlignment(Element.ALIGN_CENTER);// 居中显示
		rTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
		rTable.setAutoFillEmptyCells(true); // 自动填满
		rTable.setBorderWidth(1); // 边框宽度
		rTable.setBorderColor(new Color(0, 0, 0)); // 边框颜色
		rTable.setPadding(2);// 衬距，看效果就知道什么意思了
		rTable.setSpacing(0);// 即单元格之间的间距
		rTable.setBorder(2);// 边框
		// 设置风险种类表格的表头
		Cell rhaderCell1 = new Cell("项目名称");
		rhaderCell1.setHeader(true);
		rTable.addCell(rhaderCell1);
		Cell rhaderCell2 = new Cell("项目类型");
		rhaderCell2.setHeader(true);
		rTable.addCell(rhaderCell2);
		Cell rhaderCell3 = new Cell("评估部门");
		rhaderCell3.setHeader(true);
		rTable.addCell(rhaderCell3);
		Cell rhaderCell4 = new Cell("评估人");
		rhaderCell4.setHeader(true);
		rTable.addCell(rhaderCell4);
		Cell rhaderCell5 = new Cell("评估时间");
		rhaderCell5.setHeader(true);
		rTable.addCell(rhaderCell5);
		Cell rhaderCell6 = new Cell("评估均值");
		rhaderCell6.setHeader(true);
		rTable.addCell(rhaderCell6);
		Cell rhaderCell7 = new Cell("评估方差");
		rhaderCell7.setHeader(true);
		rTable.addCell(rhaderCell7);
		rTable.endHeaders();// 结束表头
		
		Cell rcell1 =new Cell(ip.getIpName());
		Cell rcell2 =new Cell(ip.getIpMark());
		Cell rcell3 =new Cell(ip.getIpDepname());
		Cell rcell4 =new Cell(ip.getIpUsername());
		Cell rcell5 =new Cell(ip.getIpTime());
		Cell rcell6 =new Cell(ip.getIpExpectedvalue());
		Cell rcell7 =new Cell(ip.getIpVariance());
		rTable.setAlignment(Element.ALIGN_CENTER);
		rTable.addCell(rcell1);
		rTable.addCell(rcell2);
		rTable.addCell(rcell3);
		rTable.addCell(rcell4);
		rTable.addCell(rcell5);
		rTable.addCell(rcell6);
		rTable.addCell(rcell7);
		
		document.add(rTable);
		document.add(new Paragraph("\n"));
		
		//插入项目的风险因素和可能原因详细列表2
		String contextString3  = "（二）项目的详细信息见下表2";
		Paragraph context3 = new Paragraph(contextString3);
		document.add(context3);
		String contextString4 = "表2   项目的风险因素和可能原因列表";
		Paragraph context4 = new Paragraph(contextString4);
		context4.setAlignment(Element.ALIGN_CENTER);
		context4.setFont(contextFont);
		// 设置第一行空的列数
		context4.setFirstLineIndent(20);
		document.add(context4);
		
		Table dTable = new Table(5);
		int dwidth[] = {20,20,20,20,20};
		dTable.setWidths(dwidth);// 设置每列所占比例
		dTable.setWidth(90); // 占页面宽度 90%
		dTable.setAlignment(Element.ALIGN_CENTER);// 居中显示
		dTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
		dTable.setAutoFillEmptyCells(true); // 自动填满
		dTable.setBorderWidth(1); // 边框宽度
		dTable.setBorderColor(new Color(0, 0, 0)); // 边框颜色
		dTable.setPadding(2);// 衬距，看效果就知道什么意思了
		dTable.setSpacing(0);// 即单元格之间的间距
		dTable.setBorder(2);// 边框
		
		// 设置风险种类表格的表头
		Cell dhaderCell1 = new Cell("风险因素");
		dhaderCell1.setHeader(true);
		dTable.addCell(dhaderCell1);
		Cell dhaderCell2 = new Cell("影响程度");
		dhaderCell2.setHeader(true);
		dTable.addCell(dhaderCell2);
		Cell dhaderCell3 = new Cell("可能原因");
		dhaderCell3.setHeader(true);
		dTable.addCell(dhaderCell3);
		Cell dhaderCell4 = new Cell("发生可能性");
		dhaderCell4.setHeader(true);
		dTable.addCell(dhaderCell4);
		Cell dhaderCell5 = new Cell("影响程度");
		dhaderCell5.setHeader(true);
		dTable.addCell(dhaderCell5);
		dTable.endHeaders();// 结束表头

		for (Map.Entry<String, LinkedList<ProjectElement>> entry : resultMap.entrySet()) {
			List<ProjectElement> pelist=entry.getValue();
			if(pelist.size()>1){
		     	for (ProjectElement projectElement : pelist) {
		     		Cell dcell1 =new Cell(projectElement.getPeCategoryname());
					Cell dcell2 =new Cell(projectElement.getPeCateimpact());
					Cell dcell3 =new Cell(projectElement.getPeEvaluatename());
					Cell dcell4 =new Cell(projectElement.getPeProdegree());
					Cell dcell5 =new Cell(projectElement.getPeImpactdegree());
					if(projectElement.getPeEvaluatename().equals("小计")){
						dcell1.setBackgroundColor(Color.decode("#e9f2f7"));
						dcell2.setBackgroundColor(Color.decode("#e9f2f7"));
						dcell3.setBackgroundColor(Color.decode("#e9f2f7"));
						dcell4.setBackgroundColor(Color.decode("#e9f2f7"));
						dcell5.setBackgroundColor(Color.decode("#e9f2f7"));
					}
					dTable.setAlignment(Element.ALIGN_CENTER);
					dTable.addCell(dcell1);
					dTable.addCell(dcell2);
					dTable.addCell(dcell3);
					dTable.addCell(dcell4);
					dTable.addCell(dcell5);
				}
			}
		}

		document.add(dTable);
		document.add(new Paragraph("\n"));
		
		
		// 插入评估结果图
		String contextString5  = "（三）项目评估结果柱状图见下图1";
		Paragraph context5 = new Paragraph(contextString5);
		document.add(context5);
		String graghPath = path;
		String savePatharea = ServletActionContext.getServletContext()
				.getRealPath(graghPath);
		Image imgarea = Image.getInstance(savePatharea);
		imgarea.setAbsolutePosition(0, 0);
		imgarea.setAlignment(Image.MIDDLE);// 设置图片显示位置
		//imgarea.scaleAbsolute(800, 600);// 直接设定显示尺寸
		imgarea.scalePercent(50);// 表示显示的大小为原尺寸的50%
		//imgarea.scalePercent(60, 50);// 图像高宽的显示比例

		document.add(imgarea);

		String contextStringp = "图1：项目评估结果柱状图" + "\n";
		Paragraph contextp = new Paragraph(contextStringp);
		contextp.setAlignment(Element.ALIGN_CENTER);
		contextp.setFont(contextFont);
		document.add(contextp);
		
		String contextString6  = "（四）投资项目风险评估建议";
		Paragraph context6 = new Paragraph(contextString6);
		document.add(context6);
		//插入评估结果表3
		String contextString7 = "表3   项目评估投资建议表";
		Paragraph context7 = new Paragraph(contextString7);
		context7.setAlignment(Element.ALIGN_CENTER);
		context7.setFont(contextFont);
		// 设置第一行空的列数
		context7.setFirstLineIndent(20);
		document.add(context7);
			
		Table aTable = new Table(2);
		int awidth[] = {45,55};
		aTable.setWidths(awidth);// 设置每列所占比例
		aTable.setWidth(90); // 占页面宽度 90%
		aTable.setAlignment(Element.ALIGN_CENTER);// 居中显示
		aTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
		aTable.setAutoFillEmptyCells(true); // 自动填满
		aTable.setBorderWidth(1); // 边框宽度
		aTable.setBorderColor(new Color(0, 0, 0)); // 边框颜色
		aTable.setPadding(2);// 衬距，看效果就知道什么意思了
		aTable.setSpacing(0);// 即单元格之间的间距
		aTable.setBorder(2);// 边框
		// 设置风险种类表格的表头
		Cell ahaderCell1 = new Cell("综合评定值大于0.5发生的次数总和N");
		ahaderCell1.setHeader(true);
		aTable.addCell(ahaderCell1);
		Cell ahaderCell2 = new Cell("项目评估投资建议");
		ahaderCell2.setHeader(true);
		aTable.addCell(ahaderCell2);
		aTable.endHeaders();// 结束表头
		
		List<String> adviseList = new LinkedList<String>();
		adviseList.add("0≤N<1000");
		adviseList.add("1000≤N<3000");
		adviseList.add("3000≤N<5000");
		adviseList.add("5000≤N≤10000");
		adviseList.add("此投资项目的投资风险很低，可以投资。");
		adviseList.add("此投资项目的投资风险较低，可以考虑投资。");
		adviseList.add("此投资项目的投资风险较高，请慎重考虑。");
		adviseList.add("此投资项目的投资风险很高，建议不要投资。");
		for(int i =0;i<4;i++){
			 Cell acell1 =new Cell(adviseList.get(i));
			 Cell acell2 =new Cell(adviseList.get((i+4)));
			 aTable.setAlignment(Element.ALIGN_CENTER);
			 aTable.addCell(acell1);
			 aTable.addCell(acell2);
		}			

		document.add(aTable);
		document.add(new Paragraph("\n"));
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession(); 
		String totalNum =session.getAttribute("totalNum").toString();
		String advise = session.getAttribute("advise").toString();
		String adviseStringp = "    在项目评估结果柱状图中，综合评定值区间越大则表明项目投资风险越大。如图1所示，在一万次模拟中综合评定值大于0.5的发生次数总和为"+totalNum+"次。投资建议："+advise+"\n";
		Paragraph advisetp = new Paragraph(adviseStringp);
		advisetp.setAlignment(Element.ALIGN_LEFT);
		document.add(advisetp);
		
		document.close();
				
		return true;
		
	}
	
	
	
}
