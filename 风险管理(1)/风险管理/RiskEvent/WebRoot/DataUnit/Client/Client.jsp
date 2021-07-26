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
    
    <title>风险事件对客户关系的影响评定等级</title>
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
  
<body style="font-size:12px; margin-top:5px;"  bgcolor="#F8FCFC">
<form name="client" id="client" method="post" action="dataUnit/ClientAction">
            <div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>客户关系</span></div></div>
             <p:page pageTitle="等级编号;影响程度;影响描述"  pageVaule="cliId;cliLevel;cliDetail" id="mytable" dataProvider="cliList"
             actionUrl="dataUnit/ClientAction"
             pageNum="10"
             menu="true" 
             menuAlign="left" 
             dcLineUsed="true"
             dcLink="dataUnit/CliReadAction"         
             addUsed="true" addLink="/RiskEvent/DataUnit/Client/ClientAddUpdate.jsp"
             deleteUsed="true" deleteLink="dataUnit/CliMultiDelAction"
             checkUsed="true" checkValue="cliId"
             updateUsed="true" updateLink="dataUnit/CliUpdatePreAction"
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
	if((operation=="addupdate")&&(result=="success")) alert("提交成功!");
	else if(operation=="delete"&&result=="success") alert("删除成功!");
	else if(operation=="addupdate"&&result=="fail") alert("提交失败!");
	else if(operation=="delete"&&result=="fail") alert("删除失败!");
}
</SCRIPT>
</html>
