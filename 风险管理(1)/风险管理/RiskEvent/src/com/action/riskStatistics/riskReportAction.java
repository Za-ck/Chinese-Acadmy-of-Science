package com.action.riskStatistics;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;


import org.apache.struts2.ServletActionContext;

import com.dao.OperationDAO;
import com.dao.ProbabilityDAO;
import com.dao.ReportViewDAO;
import com.dao.RiskDAO;
import com.dao.RiskTypeDAO;
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
import com.model.Operation;
import com.model.Probability;
import com.model.ReportView;
import com.model.Risk;

public class riskReportAction {
	private String year;
	private String areadetail;
	private String path;

	private List<Risk> rList;
	private List<Probability> pList;
	private List<Operation> iList; // 风险影响的列表，分为极低、低、中等、高、极高。借用运营的实体
	private List<ReportView> reportList;

	private RiskDAO riskDAO;
	private RiskTypeDAO riskTypeDAO;
	private ProbabilityDAO probabilityDao;
	private OperationDAO operationDao;
	private ReportViewDAO reportViewDao;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public RiskDAO getRiskDAO() {
		return riskDAO;
	}

	public void setRiskDAO(RiskDAO riskDAO) {
		this.riskDAO = riskDAO;
	}

	public List<Risk> getrList() {
		return rList;
	}

	public void setrList(List<Risk> rList) {
		this.rList = rList;
	}

	public ProbabilityDAO getProbabilityDao() {
		return probabilityDao;
	}

	public void setProbabilityDao(ProbabilityDAO probabilityDao) {
		this.probabilityDao = probabilityDao;
	}

	public List<Probability> getpList() {
		return pList;
	}

	public void setpList(List<Probability> pList) {
		this.pList = pList;
	}

	public OperationDAO getOperationDao() {
		return operationDao;
	}

	public void setOperationDao(OperationDAO operationDao) {
		this.operationDao = operationDao;
	}

	public List<Operation> getiList() {
		return iList;
	}

	public void setiList(List<Operation> iList) {
		this.iList = iList;
	}

	public ReportViewDAO getReportViewDao() {
		return reportViewDao;
	}

	public void setReportViewDao(ReportViewDAO reportViewDao) {
		this.reportViewDao = reportViewDao;
	}

	public List<ReportView> getReportList() {
		return reportList;
	}

	public void setReportList(List<ReportView> reportList) {
		this.reportList = reportList;
	}

	public String getAreadetail() {
		return areadetail;
	}

	public void setAreadetail(String areadetail) {
		this.areadetail = areadetail;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public RiskTypeDAO getRiskTypeDAO() {
		return riskTypeDAO;
	}

	public void setRiskTypeDAO(RiskTypeDAO riskTypeDAO) {
		this.riskTypeDAO = riskTypeDAO;
	}

	// 全面风险事件坐标图生成
	@SuppressWarnings("unchecked")
	public String exportWord() {

		rList = new LinkedList<Risk>();// 初始化链表
		pList = new LinkedList<Probability>();
		iList = new LinkedList<Operation>();
		reportList = new LinkedList<ReportView>();

		rList = riskDAO.findAll();
		pList = probabilityDao.findAll();
		iList = operationDao.findAll();// 影响程度列表
		reportList = reportViewDao.findByYear(this.year);// 风险的可能性和综合评定列表

		String FileDefaultName = year + "年度风险坐标图";
		Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		FileDefaultName += df.format(dt);

		try {
			createWordFile(FileDefaultName, rList, pList, iList, reportList);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "success";
	}

	// 页面中显示的风险分区图和描述
	@SuppressWarnings("unchecked")
	public String reportQuery() throws IOException {
		rList = new LinkedList<Risk>();// 初始化链表
		pList = new LinkedList<Probability>();
		iList = new LinkedList<Operation>();
		reportList = new LinkedList<ReportView>();

		rList = riskDAO.findAll();
		pList = probabilityDao.findAll();
		iList = operationDao.findAll();// 影响程度列表
		reportList = reportViewDao.findByYear(this.year);// 风险的可能性和综合评定列表

		// 获取分区图
		this.path = graphic.drawTableShadow(800, 1000, reportList);

		if (!reportList.isEmpty()) {
			this.areadetail = riskDetail(reportList);// 获取具体描述
			this.areadetail.replace("。", "<br>");
		} else
			this.areadetail = "";
		return "success";
	}

	/*
	 * 生成word文档
	 */
	public boolean createWordFile(String FileDefaultName, List<Risk> rList,
			List<Probability> list_p, List<Operation> list_i,
			List<ReportView> reportList) throws DocumentException, IOException {

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
		Font titleFont = new Font(bfChinese, 14, Font.BOLD);
		// 正文字体风格
		Font contextFont = new Font(bfChinese, 12, Font.NORMAL);
		Paragraph title = new Paragraph("中南电力设计院风险事件评估表");
		// 设置标题格式对齐方式
		title.setAlignment(Element.ALIGN_CENTER);
		title.setFont(titleFont);
		document.add(title);

		String contextString3 = year + "年我院重大风险量化分析";
		Paragraph context3 = new Paragraph(contextString3);
		document.add(context3);

		String contextString4 = "    我院重大风险成因分析见下表1";
		Paragraph context4 = new Paragraph(contextString4);
		document.add(context4);
		Table rTable = new Table(3);

		int rwidth[] = { 30, 50, 30 };
		rTable.setWidths(rwidth);// 设置每列所占比例
		rTable.setWidth(100); // 占页面宽度 90%
		rTable.setAlignment(Element.ALIGN_CENTER);// 居中显示
		rTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
		rTable.setAutoFillEmptyCells(true); // 自动填满
		rTable.setBorderWidth(1); // 边框宽度
		rTable.setBorderColor(new Color(0, 0, 0)); // 边框颜色
		rTable.setPadding(2);// 衬距，看效果就知道什么意思了
		rTable.setSpacing(0);// 即单元格之间的间距
		rTable.setBorder(2);// 边框
		// 设置风险种类表格的表头
		Cell rhaderCell1 = new Cell("重大风险");
		rhaderCell1.setHeader(true);
		rTable.addCell(rhaderCell1);
		Cell rhaderCell2 = new Cell("风险分类");
		rhaderCell2.setHeader(true);
		rTable.addCell(rhaderCell2);
		Cell rhaderCell3 = new Cell("风险编号");
		rhaderCell3.setHeader(true);
		rTable.addCell(rhaderCell3);
		rTable.endHeaders();// 结束表头

		for (int i = 0; i < rList.size(); i++) {

			Cell rcell1 = new Cell();
			if (null != rList.get(i).getRiskType()) {
				rcell1 = new Cell(this.getRiskTypeDAO().findById(
						rList.get(i).getRiskType()).getRtName());
			}

			Cell rcell2 = new Cell(rList.get(i).getRiskName());
			Cell rcell3 = new Cell(String.valueOf(rList.get(i).getRiskQueue()));
			rTable.setAlignment(Element.ALIGN_CENTER);
			rTable.addCell(rcell1);
			rTable.addCell(rcell2);
			rTable.addCell(rcell3);
		}
		document.add(rTable);
		document.add(new Paragraph("\n"));

		String contextString = "表2   风险发生可能性的定性、定量的描述";
		Paragraph context = new Paragraph(contextString);
		// 正文格式左对齐
		context.setAlignment(Element.ALIGN_CENTER);
		context.setFont(contextFont);
		// 离上一段落（标题）空的行数
		// context.setSpacingBefore(5);
		// 设置第一行空的列数
		context.setFirstLineIndent(20);
		document.add(context);
		Table aTable = new Table(list_p.size() + 2);
		int width[] = { 20, 30, 50, 60, 60, 60, 60, 60,60 };
		aTable.setWidths(width);// 设置每列所占比例
		aTable.setWidth(100); // 占页面宽度 90%
		aTable.setAlignment(Element.ALIGN_CENTER);// 居中显示
		aTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
		aTable.setAutoFillEmptyCells(true); // 自动填满
		aTable.setBorderWidth(1); // 边框宽度
		aTable.setBorderColor(new Color(0, 0, 0)); // 边框颜色
		aTable.setPadding(2);// 衬距，看效果就知道什么意思了
		aTable.setSpacing(0);// 即单元格之间的间距
		aTable.setBorder(2);// 边框
		// 设置风险
		Cell haderCell = new Cell("定量方法");
		haderCell.setHeader(true);
		aTable.addCell(haderCell);
		Cell haderCell2 = new Cell("一定时期发生的概率");
		haderCell2.setHeader(true);
		aTable.addCell(haderCell2);
		for (int i = 0; i < list_p.size(); i++) {
			Cell cell = new Cell(list_p.get(i).getProProbability());
			cell.setHeader(true);
			aTable.addCell(cell);
		}
		aTable.endHeaders();
		Cell cell3 = new Cell("定性方法");
		aTable.addCell(cell3);
		Cell cell4 = new Cell("文字描述");
		aTable.addCell(cell4);
		for (int i = 0; i < list_p.size(); i++) {
			Cell cell = new Cell(list_p.get(i).getProDisasterEvent() + " \n"
					+ list_p.get(i).getProLevel());
			aTable.setAlignment(Element.ALIGN_CENTER);
			aTable.addCell(cell);
		}
		Cell cell5 = new Cell("风险归类");
		aTable.addCell(cell5);
		Cell cell6 = new Cell("序号描述");
		aTable.addCell(cell6);
		String[] infos = { "", "", "", "", "", "","" };
		for (int i = 0; i < list_p.size(); i++) {
			for (int j = 0; j < reportList.size(); j++) {
				if (list_p.get(i).getProId().equals(
						reportList.get(j).getRiskPro())) {
					infos[i] += "(" + reportList.get(j).getRiskQueue() + ")";
				}
			}
			Cell cell = new Cell(infos[i]);
			aTable.addCell(cell);
		}
		document.add(aTable);
		document.add(new Paragraph("\n"));

		String contextString2 = "表3   风险对目标影响程度定性、定量的描述";
		Paragraph context2 = new Paragraph(contextString2);
		// 正文格式左对齐
		context2.setAlignment(Element.ALIGN_CENTER);
		context2.setFont(contextFont);
		// 离上一段落（标题）空的行数
		// context2.setSpacingBefore(5);
		// 设置第一行空的列数
		// context.setFirstLineIndent(20);
		document.add(context2);
		Table aTable2 = new Table(list_p.size() + 2);
		int width2[] = { 30, 30, 50, 60, 60, 60, 60 , 60,60};
		aTable2.setWidths(width2);// 设置每列所占比例
		aTable2.setWidth(100); // 占页面宽度 90%
		aTable2.setAlignment(Element.ALIGN_CENTER);// 居中显示
		aTable2.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
		aTable2.setAutoFillEmptyCells(true); // 自动填满
		aTable2.setBorderWidth(1); // 边框宽度
		aTable2.setBorderColor(new Color(0, 0, 0)); // 边框颜色
		aTable2.setPadding(2);// 衬距，看效果就知道什么意思了
		aTable2.setSpacing(0);// 即单元格之间的间距
		aTable2.setBorder(2);// 边框
		Cell haderCell_im1 = new Cell("定量方法");
		haderCell_im1.setHeader(true);
		aTable2.addCell(haderCell_im1);
		Cell haderCell_im2 = new Cell("评分等级");
		haderCell_im1.setHeader(true);
		aTable2.addCell(haderCell_im2);
		for (int i = 0; i < list_i.size(); i++) {
			Cell cell = new Cell(String.valueOf(list_i.get(i).getOpeId()));
			cell.setHeader(true);
			aTable2.addCell(cell);
		}
		aTable2.endHeaders();
		Cell cell_m3 = new Cell("定性方法");
		aTable2.addCell(cell_m3);
		Cell cell_m4 = new Cell("文字描述");
		aTable2.addCell(cell_m4);
		for (int i = 0; i < list_p.size() - 1; i++) {
			Cell cell = new Cell(list_i.get(i).getOpeLevel());
			aTable2.setAlignment(Element.ALIGN_CENTER);
			aTable2.addCell(cell);
		}
		Cell cell_m5 = new Cell("风险归类");
		aTable2.addCell(cell_m5);
		Cell cell_m6 = new Cell("序号描述");
		aTable2.addCell(cell_m6);
		String[] infos_m = { "", "", "", "", "", "","" };
		for (int i = 0; i < list_i.size(); i++) {
			for (int j = 0; j < reportList.size(); j++) {
				if (list_i.get(i).getOpeId().equals(
						reportList.get(j).getRiskValuex())) {
					infos_m[i] += "(" + reportList.get(j).getRiskQueue() + ")";
				}
			}
			Cell cell = new Cell(infos_m[i]);
			aTable2.addCell(cell);
		}
		document.add(aTable2);
		document.add(new Paragraph("\n"));

		String contextString5 = year + "年我院重大风险坐标图";
		Paragraph context5 = new Paragraph(contextString5);
		context5.setFont(titleFont);
		document.add(context5);

		String contextString6 = "    依据上节对风险发生可能性高低和风险对目标影响程度进行定性和定量的评估，绘制风险坐标如下图。";
		Paragraph context6 = new Paragraph(contextString6);
		context6.setFirstLineIndent(4);
		document.add(context6);

		// 生成图片
		graphic.drawTable(800, 1000, reportList);
		String savePath = ServletActionContext.getServletContext().getRealPath(
				"/upload/reportImg.jpg");
		Image img = Image.getInstance(savePath);
		img.setAbsolutePosition(0, 0);
		img.setAlignment(Image.MIDDLE);// 设置图片显示位置
		img.scaleAbsolute(600, 600);// 直接设定显示尺寸
		img.scalePercent(90);// 表示显示的大小为原尺寸的50%
		img.scalePercent(60, 50);// 图像高宽的显示比例
		document.add(img);

		String contextStringp1 = "图1：我院风险坐标图" + "\n";
		Paragraph contextp1 = new Paragraph(contextStringp1);
		contextp1.setAlignment(Element.ALIGN_CENTER);
		contextp1.setFont(contextFont);
		document.add(contextp1);

		String contextString71 = "重大风险管理策略和解决方案" + "\n";
		Paragraph context71 = new Paragraph(contextString71);
		context71.setAlignment(Element.ALIGN_LEFT);
		context71.setFont(titleFont);
		document.add(context71);

		String contextString7 = "（1）风险管理策略"
				+ "\n"
				+ "    我院风险管理主要包括战略、运营、市场、法律、财务风险管理，根据我院的自身条件和外部环境，围绕企业发展战略，分别采用风险承担、风险规避、风险转移、风险控制等方法进行管理。"
				+ "\n" + "    将我院风险评估的定性、定量风险得出的风险坐标图分A、B、C三个区域，如下图所示。" + "\n";
		Paragraph context7 = new Paragraph(contextString7);
		document.add(context7);

		// 插入分区图
		String path2 = graphic.drawTableShadow(800, 1000, reportList);
		String savePatharea = ServletActionContext.getServletContext()
				.getRealPath(path2);
		Image imgarea = Image.getInstance(savePatharea);
		imgarea.setAbsolutePosition(0, 0);
		imgarea.setAlignment(Image.MIDDLE);// 设置图片显示位置
		imgarea.scaleAbsolute(600, 600);// 直接设定显示尺寸
		imgarea.scalePercent(90);// 表示显示的大小为原尺寸的50%
		imgarea.scalePercent(60, 50);// 图像高宽的显示比例
		document.add(imgarea);

		String contextStringp2 = "图2：风险坐标图分区图" + "\n";
		Paragraph contextp2 = new Paragraph(contextStringp2);
		contextp2.setAlignment(Element.ALIGN_CENTER);
		contextp2.setFont(contextFont);
		document.add(contextp2);

		String contextString8 = riskDetail(reportList);// 获取具体描述
		Paragraph context8 = new Paragraph(contextString8);
		document.add(context8);
		document.close();
		return true;
	}

	private String riskDetail(List<ReportView> reportList) {
		String areaA = "";
		String areaB = "";
		String areaC = "";
		for (int i = 0; i < reportList.size(); i++) {
			if (reportList.get(i).getRiskPro() < 3
					&& reportList.get(i).getRiskValuex() < 4) {
				areaA = reportList.get(i).getId().getRiskName() + "、" + areaA;// 获取A区域的风险名称
			} else if (reportList.get(i).getRiskPro() > 3
					&& reportList.get(i).getRiskValuex() > 3) {
				areaC = reportList.get(i).getId().getRiskName() + "、" + areaC;// 获取B区域的风险名称
			} else {
				areaB = reportList.get(i).getId().getRiskName() + "、" + areaB;// 获取C区域的风险名称
			}
		}
		if (areaA.equals(""))
			areaA = "目前我院尚无";
		else
			areaA = "包括" + areaA;
		if (areaB.equals(""))
			areaB = "目前我院尚无";
		else
			areaB = "包括" + areaB;
		if (areaC.equals(""))
			areaC = "目前我院尚无";
		else
			areaC = "包括" + areaC;

		String contextString8 = "    其中A区域中的风险"
				+ areaA
				+ "，此类风险发生可能性和事件发生后的影响程度均为中等及以下，对于A区域中的各项风险拟不再增加控制措施，但需认真执行原有风险控制措施；"
				+ "\n"
				+ "    B区域中的风险"
				+ areaB
				+ "，此类风险发生可能性和事件发生后的影响程度均为中等以上及高等以下，对于B区域的各项风险需要严格控制，并需要补充制定各项措施；"
				+ "\n" + "    C区域中的风险" + areaC
				+ "，风险发生可能性和事件发生后的影响程度均为高等及以上，此类风险应严格防范，确保规避。";
		return contextString8;
	}
}
