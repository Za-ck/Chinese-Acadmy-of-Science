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
    
    <title>政策法规删除</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css"></style>
	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
	<script language=javascript src="/RiskEvent/js/windows.js"></script>
	<script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
	<script language="javascript" src="IconPage/page.js"></script>
	<script language="javascript" src="IconPage/jquery-1.3.2.min.js"></script>
  </head>
   
  <body style="font-size:12px; margin-top:5px;"  bgcolor="#F8FCFC">
<form name="policyQuery" id=policyQuery method="post" action="PolicyRegulations/lawDeleteshow">
            <div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>政策法规删除</span></div></div>
             <p:page pageTitle="文件名称;上传日期;上传用户"  pageVaule="filetitle;fileDate;users.userName" id="mytable" dataProvider="queryList"
             actionUrl="PolicyRegulations/lawDeleteshow"
             pageNum="10"
             menu="true" 
             menuAlign="left"         
             addUsed="true" addLink="/RiskEvent/PolicyRegulations/PolicyUpload.jsp"
             deleteUsed="true" deleteLink="PolicyRegulations/lawDeleteAction"
             checkUsed="true" checkValue="fileId"
             ></p:page>
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
	if(operation=="delete"&&result=="success") alert("删除成功!");	
	else if(operation=="delete"&&result=="fail") alert("删除失败!");
}
</SCRIPT>
</html>
