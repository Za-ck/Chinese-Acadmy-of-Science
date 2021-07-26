<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>上传文件</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
	<script src="/RiskEvent/js/jquery-1.7.1.js" type="text/javascript"></script>
	<script src="/RiskEvent/js/windows.js" type="text/javascript"></script>
	
  </head>
  <base target="_self"></base>
  <body onload="load()">
  	<div align="center" style="margin-top: 10px">
    <div id="readme">
    	<span id="uploadtip" class="red">提示: 所有文件大小不要超过100M</span><br/>
    </div>
		<input type="hidden" id="files" value="${files}" />
		<form id="fileUploadForm" name="fileUploadForm" action="" enctype="multipart/form-data" method="post">
			
			<div id="file">
				<div>
					<input type="file" name="filename" size="40"/>&nbsp;&nbsp;
					<a href="javascript:void(0)" onClick="this.parentNode.parentNode.removeChild(this.parentNode);">删除</a>
				</div>
				<div>
					<input type="file" name="filename" size="40"/>&nbsp;&nbsp;
					<a href="javascript:void(0)" onClick="this.parentNode.parentNode.removeChild(this.parentNode);">删除</a>
				</div>
				<div>
					<input type="file" name="filename" size="40"/>&nbsp;&nbsp;
					<a href="javascript:void(0)" onClick="this.parentNode.parentNode.removeChild(this.parentNode);">删除</a>
				</div>
			</div>
			<div style="margin-top: 5px">
				<input type="submit" name="uploadButton" id="uploadButton" value="开始上传" style="height: 24px" onclick="uploadfile()"/>
				<input type="button" name="addfileButton" id="addfileButton" value="添加文件" onclick="addfile()" style="height: 24px"/>
			</div>
		</form>
	</div>
  </body>
  
  <script type="text/javascript">
  	
  	function load()
  	{
  		var files = decodeURIComponent(document.getElementById("files").value);
  		
  		if (files !== null && files !== undefined && files.toString().replace(/(^\s*)|(\s*$)/g, "") !== '') {
			//alert("上传成功");
			var data = eval(files);
			//alert(data[0].fileId);
			var pWindow=window.dialogArguments[2];    //父窗体
 			if(pWindow != null)
 			{
  				pWindow.addfile(files);
 			}
 			else
 			{
  				window.opener.addfile(files);
 			}
 			window.opener=null;
 			window.open("","_self");
 			window.close();
		}
  	}
  
  	function addfile()
	{
		//alert("添加文件");
		var div = document.getElementById("file");
	
		var input = document.createElement("input");
		input.type="file";
		input.name="filename";
		$(input).attr("size",40);
		
		
	
		var del = document.createElement("a");
		del.href = "javascript:void(0)";
		del.innerHTML ="删除";
		
		$(del).click(function()
		{
			this.parentNode.parentNode.removeChild(this.parentNode);
		});
		
		var innerdiv = document.createElement("div");
		
		innerdiv.appendChild(input);
		$(innerdiv).append("&nbsp;&nbsp;&nbsp;");
		innerdiv.appendChild(del);
		
		div.appendChild(innerdiv);
		
	}
  	
  	function uploadfile()
  	{
  		//openBox('提示',350,150,0,'正在上传，请等待....','');
        $("#uploadtip").html("正在上传，请稍后...");
  		var reportId = window.dialogArguments[0];
  		var actionname = window.dialogArguments[1] + "_uploadFile?reportId=" + reportId;
  		//alert(reportId + "  " + actionname);
  		fileUploadForm.action = actionname;
  		fileUploadForm.submit();
  		
  	}
  	
  	
  	
  </script>
  
</html>
