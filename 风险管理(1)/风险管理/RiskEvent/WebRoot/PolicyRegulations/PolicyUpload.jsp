<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'PolicyUpload.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	 <link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
     <script src="/RiskEvent/js/calendar.js" type="text/javascript"></script>
     <script language="javascript" src="/RiskEvent/js/funcJS.js"></script>
     <script src="/RiskEvent/js/jquery-1.7.1.js" type="text/javascript" charset="utf-8"></script>
     <script language=javascript src="/RiskEvent/js/windows.js"></script>
     <script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
  </head>
 
   <body>  
      <form action="PolicyRegulations/upload" method="post" enctype="multipart/form-data" name="uploadForm" id="uploadForm" onSubmit="return checkinfo();">  
       <div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>政策法规上传</span></div></div>
          <div class="MainDiv">
<fieldset>
				<legend>上传文件信息登记</legend>
       <table border="0" width="100%" class="contentInfo" cellpadding="0" cellspacing="0" align="left">           
          
				 	<tr>
					      <td nowrap align="right" height="30" width="14%">文件名称：</td>
                          <td width="86%"><span class="red">
                          <input name="filetitle" type="text" class="text" style="width:580px" value="<s:property value='filetitle'/>"/>*(文件主题不要超过50字)</span>
						</td>
	     </tr>
           
           		<tr>
					    <td width="14%" nowrap align="right">文件描述：</td>
<td ><span class="red">
							<textarea name="fileremark" cols="70" rows="12"><s:property value='fileremark'/></textarea>*(文件描述不要超过250字)</span>
						</td>
					  </tr>
					  <tr>  
                <td nowrap align="right" width="14%">上传文件路径：</td>  
                <td><input type="file" name="myFile"><span class="red">*(文件大小不要超过100M)</span></td>  
           </tr> 
            <tr>  
                <td colspan="2" align="center" height="50px">
                <input type="submit" value="" class="upload">
                </td>              
           </tr>  
        </table>
        </fieldset>
        </div>
        
    
        
      </form>  
  </body>  
   <SCRIPT type="text/javascript">
<%
  String params=request.getParameter("operation");
  String[] temps=null;
  String operation="",result="";
  if(params!=null){
	 temps=params.split("\\*");	 
      operation=temps[0];
      result=temps[1].split("=")[1];
 }
%>
var operation="<%=operation%>";
var result="<%=result%>";
if(operation!=""){
	if(operation=="upload"&&result=="success") alert("上传成功!");	
	else if(operation=="upload"&&result=="fail") alert("上传失败!");
}

   function checkinfo(){
   if(doValidate('uploadForm')){
   openBox('上传提示',350,150,0,'正在上传中，请等待....','');
   return true;
   }
   else{
    return false;
   }
   }
   </SCRIPT>
  
</html>
