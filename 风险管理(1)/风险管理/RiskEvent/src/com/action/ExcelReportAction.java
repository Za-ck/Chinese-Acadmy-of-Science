package com.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.struts2.ServletActionContext;

public class ExcelReportAction {
	/*
	 * 
	 * report excel 导出excel表格
	 */
	public String ReportExcel(String FileDefaultName,String FileTemplate,String[][] ReportList,int firstline,int maxCol,String condition){              
       	Date dt=new Date();
       	SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       	FileDefaultName+=df.format(dt);                	
       	try{	    
//       		FileDefaultName=new String(FileDefaultName.getBytes(),"iso8859-1");//解决中文 文件名问题    
       		String realpath = ServletActionContext.getServletContext().getRealPath("/");
       		HttpServletResponse response = ServletActionContext.getResponse();
//       		response.reset();		
//       	    response.addHeader("Content-Disposition","attachment;filename="+FileDefaultName+".xls");   
//       	    response.setContentType("application/vnd.ms-excel");
       		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(FileDefaultName + ".xls", "UTF-8"));
     	    
       	    InputStream is = new FileInputStream(realpath+"ReportTemplete\\"+FileTemplate+".xls");
       	    
   			Workbook rwb = Workbook.getWorkbook(is);
   			
   			File tempFile = File.createTempFile("temp", ".xls");
   			
   		    WritableWorkbook wwb = Workbook.createWorkbook(tempFile,rwb);           		   
   			
   			WritableFont wfont = new WritableFont(WritableFont.ARIAL, 10,
   					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
   					Colour.BLACK);
   			WritableFont wfont2 = new WritableFont(WritableFont.ARIAL, 8,
   					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
   					Colour.DARK_RED2);
   			WritableFont wfontTitle = new WritableFont(WritableFont.ARIAL, 20,
   					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
   					Colour.BLACK);
   			WritableCellFormat center = new WritableCellFormat(wfont);
   			center.setAlignment(jxl.format.Alignment.CENTRE);
   			WritableCellFormat centerLeft = new WritableCellFormat(wfont);
   			centerLeft.setAlignment(jxl.format.Alignment.CENTRE);
   			centerLeft.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//设置垂直居中
   			centerLeft.setWrap(true);//设置自动换行
   			WritableCellFormat centerTitle = new WritableCellFormat(wfontTitle);
   			centerTitle.setAlignment(jxl.format.Alignment.CENTRE);
   			
   			WritableCellFormat left= new WritableCellFormat(wfont2);
   			left.setAlignment(jxl.format.Alignment.LEFT);
   			WritableCellFormat last = new WritableCellFormat(wfont2);
   			last.setAlignment(jxl.format.Alignment.LEFT);
   			last.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//设置垂直居中
   			last.setWrap(true);//设置自动换行

   			
   			WritableSheet sheet = wwb.getSheet(0);
   			Label label;
   			
   			int[] begin=new int[maxCol];
   			for(int i=0;i<maxCol;i++)
   				{begin[i]=firstline;}//初始化
			int[] end=new int[maxCol];
			for(int i=0;i<maxCol;i++)
				{end[i]=0;}//初始化
   			for(int i=0;i<ReportList.length;i++){
				for (int j = maxCol; j < ReportList[i].length; j++)// 从第maxCol列开始生成，因为前些列需要合并，为避免mergecells出现警告，先不生成该cell
				{
					label = new Label(j, i + firstline, ReportList[i][j],
							center);
					sheet.addCell(label);
				}

   				for(int m=maxCol-1;m>=0;m--)
   				{
   					if(i+1<ReportList.length)
   				{
   					if(!(ReportList[i][m].equals(ReportList[i+1][m])))
   					{		
   						end[m]=i+firstline;
   				label = new Label(m, begin[m], ReportList[i][m],center); //合并行中只有开始行生成一个cell  					
   	   			sheet.addCell(label);  
   				sheet.mergeCells(m, begin[m], m, end[m]);//合并第一列中相同的
   				//mergeCells(a,b,c,d) 单元格合并函数; a:最左上的列，b最左上的行，c最右下的列，d最右下的行
   				begin[m]=i+1+firstline;
//   				end=begin;
   					}
   				}
   				
   				if(i+1==ReportList.length)
   				{
   					label = new Label(m, begin[m], ReportList[i][m],center);   					
   	   	   			sheet.addCell(label); 
   					end[m]=i+firstline;
   					sheet.mergeCells(m, begin[m], m, end[m]);
   				}
   				}
   			
   			}
				label = new Label(0,1,condition,left);
				sheet.addCell(label);     				
   			wwb.write();
   			wwb.close();
   			
   			BufferedInputStream fis = new BufferedInputStream(new FileInputStream(tempFile));
			int len = 0;  
            byte[] buffer = new byte[1024];
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            while((len = fis.read(buffer)) > 0) {  
                out.write(buffer,0,len);  
            }  
            out.flush();
   			out.close();
   			tempFile.delete();
       	}
       	catch(Exception e){
       		e.printStackTrace();
       		return "error"; 
       	}

		return "success";
	}
	
	public String ReportRiskStaExcel(String FileDefaultName,String FileTemplate,String[][] ReportList,int firstline,int maxCol,String depName,
			String riskAdmin,String adminTime,String riskVerifier,String verifyTime){              
       	Date dt=new Date();
       	SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       	FileDefaultName+=df.format(dt);                	
       	try{	    
//       		FileDefaultName=new String(FileDefaultName.getBytes(),"iso8859-1");//解决中文 文件名问题    
       		String realpath = ServletActionContext.getServletContext().getRealPath("/");
       		HttpServletResponse response = ServletActionContext.getResponse();
//       		response.reset();		
//       	    response.addHeader("Content-Disposition","attachment;filename="+FileDefaultName+".xls");   
//       	    response.setContentType("application/vnd.ms-excel");
       		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(FileDefaultName + ".xls", "UTF-8"));
     	    
       	    InputStream is = new FileInputStream(realpath+"ReportTemplete\\"+FileTemplate+".xls");
       	    
   			Workbook rwb = Workbook.getWorkbook(is);
   			
   			File tempFile = File.createTempFile("temp", ".xls");
   			
   		    WritableWorkbook wwb = Workbook.createWorkbook(tempFile,rwb);           		   
   			
   			WritableFont wfont = new WritableFont(WritableFont.ARIAL, 10,
   					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
   					Colour.BLACK);
   			WritableFont wfont2 = new WritableFont(WritableFont.ARIAL, 8,
   					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
   					Colour.DARK_RED2);
   			WritableFont wfontTitle = new WritableFont(WritableFont.ARIAL, 20,
   					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
   					Colour.BLACK);
   			WritableCellFormat center = new WritableCellFormat(wfont);
   			center.setAlignment(jxl.format.Alignment.CENTRE);
   			WritableCellFormat centerLeft = new WritableCellFormat(wfont);
   			centerLeft.setAlignment(jxl.format.Alignment.CENTRE);
   			centerLeft.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//设置垂直居中
   			centerLeft.setWrap(true);//设置自动换行
   			WritableCellFormat centerTitle = new WritableCellFormat(wfontTitle);
   			centerTitle.setAlignment(jxl.format.Alignment.CENTRE);
   			
   			WritableCellFormat left= new WritableCellFormat(wfont2);
   			left.setAlignment(jxl.format.Alignment.LEFT);
   			WritableCellFormat last = new WritableCellFormat(wfont2);
   			last.setAlignment(jxl.format.Alignment.LEFT);
   			last.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//设置垂直居中
   			last.setWrap(true);//设置自动换行

   			
   			WritableSheet sheet = wwb.getSheet(0);
   			Label label;
   			
   			int[] begin=new int[maxCol];
   			for(int i=0;i<maxCol;i++)
   				{begin[i]=firstline;}//初始化
			int[] end=new int[maxCol];
			for(int i=0;i<maxCol;i++)
				{end[i]=0;}//初始化
   			for(int i=0;i<ReportList.length;i++){
				for (int j = maxCol; j < ReportList[i].length; j++)// 从第maxCol列开始生成，因为前些列需要合并，为避免mergecells出现警告，先不生成该cell
				{
					label = new Label(j, i + firstline, ReportList[i][j],
							center);
					sheet.addCell(label);
				}

   				for(int m=maxCol-1;m>=0;m--)
   				{
   					if(i+1<ReportList.length)
   				{
   					if(!(ReportList[i][m].equals(ReportList[i+1][m])))
   					{		
   						end[m]=i+firstline;
   				label = new Label(m, begin[m], ReportList[i][m],center); //合并行中只有开始行生成一个cell  					
   	   			sheet.addCell(label);  
   				sheet.mergeCells(m, begin[m], m, end[m]);//合并第一列中相同的
   				//mergeCells(a,b,c,d) 单元格合并函数; a:最左上的列，b最左上的行，c最右下的列，d最右下的行
   				begin[m]=i+1+firstline;
//   				end=begin;
   					}
   				}
   				
   				if(i+1==ReportList.length)
   				{
   					label = new Label(m, begin[m], ReportList[i][m],center);   					
   	   	   			sheet.addCell(label); 
   					end[m]=i+firstline;
   					sheet.mergeCells(m, begin[m], m, end[m]);
   				}
   				}
   			
   			}
				label = new Label(4,1,depName,center);
				sheet.addCell(label); 
				sheet.mergeCells(4, 1, 20, 1);
			    label=new Label(4,2,riskAdmin,center);
			    sheet.addCell(label);
			    sheet.mergeCells(4, 2, 8, 2);
			    label=new Label(13,2,adminTime,center);
			    sheet.addCell(label);
			    sheet.mergeCells(13, 2, 20, 2);
			    label=new Label(4,3,riskVerifier,center);
			    sheet.addCell(label);
			    sheet.mergeCells(4, 3, 8, 3);
			    label=new Label(13,3,verifyTime,center);
			    sheet.addCell(label);
			    sheet.mergeCells(13, 3, 20, 3);
			    
   			wwb.write();
   			wwb.close();
   			
   			BufferedInputStream fis = new BufferedInputStream(new FileInputStream(tempFile));
			int len = 0;  
            byte[] buffer = new byte[1024];
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            while((len = fis.read(buffer)) > 0) {  
                out.write(buffer,0,len);  
            }  
            out.flush();
   			out.close();
   			tempFile.delete();
       	}
       	catch(Exception e){
       		e.printStackTrace();
       		return "error"; 
       	}

		return "success";
	}
}
