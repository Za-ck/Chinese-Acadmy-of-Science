<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="p"  uri="pageV"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <base href="<%=basePath%>">
    
    <title>权限管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
	<script language="javascript" src="IconPage/page.js"></script>
	<script language="javascript" src="IconPage/jquery-1.3.2.min.js"></script>
  <!--   <link href="/RiskEvent/css/frameStyle.css" rel="stylesheet" type="text/css" />--> 
     
    <script language="javascript" src="/RiskEvent/js/funcJS.js"></script>
  </head>
  
<body>
<form name="limitManage" id="limitManage" method="post" action="">
            <div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>权限管理</span></div></div>        
 <div class="queryDiv">
<div style="display:;text-align: center">
<ul style="list-style-type:none;width:inherit;margin-top:5px;">
<li style="float:left;margin-left:5px;font-size:14px;">角色名称：<s:select name="systemRole" theme="simple" list="queryList" listValue="srName" listKey="srName" class="text" style="width:154px" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>
</li>
<li style="float:left;margin-left:5px;">
<input type="button" class="right-buttonShort" name="buttonQuery" id="buttonQuery" value="查询" onClick="query();" />
</li>
</ul>
</div>
</div>
             <p:page pageTitle="角色编号;角色名称"  
             pageVaule="srId;srName" 
             sort="srId;srName"
             id="mytable" dataProvider="roleList"
             actionUrl="systemManage/roleQueryAction"
             pageNum="10"
             menu="true" 
             menuAlign="left"
             addUsed="true" addLink="systemManage/limitQueryAction?updateFlag=addnew"
             deleteUsed="true" deleteLink="systemManage/roleMultiDelAction"
             checkUsed="true" checkValue="srId"
             updateUsed="true" updateLink="systemManage/limitQueryAction"
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
function query(){
	limitManage.action="systemManage/jueseQueryAction?current_pagenum=1";
	limitManage.submit();
	
}
</SCRIPT>
</html>
