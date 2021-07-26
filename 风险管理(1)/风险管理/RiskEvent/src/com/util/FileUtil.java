package com.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

public class FileUtil {

	public static String makeFileName(String filename){
		return UUID.randomUUID().toString() + "_" + filename;
	}
	
	public static String makePath(String filename, String savePath){
		int hashcode = filename.hashCode();
		int dir1 = hashcode&0xf;			//低4位生成一级目录
		int dir2 = (hashcode&0xf0)>>4;		//低5-8位生成二级目录
		
		String dir = savePath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
		File file = new File(dir);
		if(!file.exists()){
			file.mkdirs();
		}
		return dir;
	}
	
	public static void downloadFile(HttpServletResponse response,String filename,String filepath) throws Exception {
		
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		
		try {
			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
			in = new BufferedInputStream(new FileInputStream(filepath));
			int len = 0;  
            byte[] buffer = new byte[1024];
            out = new BufferedOutputStream(response.getOutputStream());
            while((len = in.read(buffer)) > 0) {  
                out.write(buffer,0,len);  
            }  
            out.flush();
			
		} finally {
			if(in!=null)
            	in.close();
            if(out!=null)
            	out.close();
		}
	}
	
	public static void downloadFileList(HttpServletResponse response, Map<String,String> fileMap, String filename) {
		
		String realpath = ServletActionContext.getServletContext().getRealPath("/");
		String tmpFileName = filename + ".zip";
		byte[] buffer = new byte[1024];  
        String strZipPath = realpath + tmpFileName;
        try {  
            ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(strZipPath));  
            // 需要同时下载的两个文件result.txt ，source.txt  
            for(Map.Entry<String, String> file : fileMap.entrySet()) {
            	//System.out.println("---------------------------" + file.getKey() + "-----------");
            	FileInputStream fis = new FileInputStream(file.getValue());
            	zout.putNextEntry(new ZipEntry(file.getKey()));
            	int len;
            	// 读入需要下载的文件的内容，打包到zip文件  
                while ((len = fis.read(buffer)) > 0) {  
                    zout.write(buffer, 0, len);  
                }
                zout.setEncoding("UTF-8");
                zout.closeEntry();
                fis.close();
            }  
            zout.close();
        } catch (Exception e) {  
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        
        try { 
            File file = new File(strZipPath);
            //System.out.println(strZipPath);
            InputStream ins = new FileInputStream(strZipPath);  
            BufferedInputStream bins = new BufferedInputStream(ins);// 放到缓冲流里面  
            OutputStream outs = response.getOutputStream();// 获取文件输出IO流  
            BufferedOutputStream bouts = new BufferedOutputStream(outs);  
            //response.setContentType("application/x-download");// 设置response内容的类型  
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(filename + ".zip", "UTF-8"));// 设置头部信息  
            int bytesRead = 0;  
            byte[] buff = new byte[8192];  
            // 开始向网络传输文件流  
            while ((bytesRead = bins.read(buff)) > 0) {  
                bouts.write(buff, 0, bytesRead);  
            }  
            bouts.flush();// 这里一定要调用flush()方法  
            ins.close();  
            bins.close();
            bouts.close();
            outs.close();
            file.delete();
            
        } catch (Exception e) {  
        	e.printStackTrace();
            throw new RuntimeException(e);
        } 
		
	}
	
	@SuppressWarnings("unchecked")
	public static String uploadFile(HttpServletRequest request,String uploadpath) {
		
		JSONArray jsonArray = new JSONArray();
		
		try {
			// 得到解析器工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//设置上传文件的临时保存目录,有个缓冲区,默认为10KB,如果超过10KB,文件在上传过程中会先保存在该目录下
			factory.setRepository(new File(request.getSession().getServletContext().getRealPath("\\WEB-INF\\temp")));
			// 得到解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");		//解决上传文件名中文乱码问题
			//设置单个文件大小以及文件总量大小
			//upload.setFileSizeMax(1024);
			upload.setSizeMax(100 * 1024 * 1024);		//文件上传的总大小不能超过100M
			// 调用解析器解析上传数据
			List<FileItem> list = upload.parseRequest(request);
			// 用于保存上传文件的目录应该禁止用户访问
			String savePath = request.getSession().getServletContext().getRealPath(uploadpath);
			// 遍历list
			for (FileItem item : list) {

				if (!item.isFormField()) {
					
					String filename = item.getName();			//不同的浏览器提交的文件不一样
					if(filename == null || filename.trim().equals("")){
						continue;
					}
					filename = filename.substring(filename.lastIndexOf("\\") + 1);
					String savaFilename = makeFileName(filename);
					InputStream in = item.getInputStream();
					int len = 0;
					byte[] buffer = new byte[1024];
					
					String realSavePath = makePath(savaFilename, savePath);  //得到文件的保存目录
					FileOutputStream out = new FileOutputStream(realSavePath + "\\"+ savaFilename);

					while ((len = in.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
					
					in.close();
					out.close();
					item.delete();  //删除临时文件
					
					JSONObject object = new JSONObject();
					object.put("filename", filename);
					object.put("filepath", realSavePath + "\\" + savaFilename);
					jsonArray.add(object.toString());
					
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return jsonArray.toString();
	}
	
	
}

