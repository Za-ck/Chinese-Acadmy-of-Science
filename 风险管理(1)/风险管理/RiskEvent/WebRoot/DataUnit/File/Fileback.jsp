<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib prefix="p"  uri="pageV"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <base href="<%=basePath%>">
    
    <title>涉及流程的管理规定文件</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
	<script language="javascript" src="IconPage/page.js"></script>
	<script language="javascript" src="IconPage/jquery-1.3.2.min.js"></script>
  </head>
  
<body style="font-size:12px; margin-top:5px;" >
<form name="file" id="file" method="post" action="dataUnit/FileAction">

<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>涉及流程的管理规定文件</span></div></div>
           <div class="queryDiv">
         <span>文件名称：</span>
		<input height="30" name="riskTypeString" id="riskTypeString" type="text" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" value="<s:property value="riskTypeString"/>" />
		
		<img height="25" align="middle" src="images/mag.png" onclick="search()"/>		 
		</div>
             <p:page pageTitle="文件编号;文件名称"  pageVaule="fileId;fileName" id="mytable" dataProvider="filList"
             actionUrl="dataUnit/FileAction"
             pageNum="10"
             menu="true" 
			 dcLineUsed="true"
             dcLink="dataUnit/FilReadAction"
             addUsed="true" addLink="dataUnit/FilAddPreAction"
             deleteUsed="true" deleteLink="dataUnit/FilMultiDelAction"
             checkUsed="true" checkValue="fileId"
             updateUsed="true" updateLink="dataUnit/FilUpdatePreAction"
             ></p:page>
</form>

</body>
<script type="text/javascript">
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
	if((operation=="addupdate")&&(result=="success")) alert("提交成功!");
	else if(operation=="delete"&&result=="success") alert("删除成功!");
	else if(operation=="addupdate"&&result=="fail") alert("提交失败!");
	else if(operation=="delete"&&result=="fail") alert("删除失败!");
}
</script>
</html>
