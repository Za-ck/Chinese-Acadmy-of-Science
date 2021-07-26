<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tree.tld" prefix="tree" %>
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
    
    <title>权限查看修改</title>
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
     
   	<script src="treeIcon/dtree.js"></script>
    	<link rel="stylesheet" type="text/css" href="treeIcon/dtree.css">
    	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
<script language=javascript src="/RiskEvent/js/windows.js"></script>
  </head>
  
<body>
<form name="limitAddManage" id="limitAddManage" method="post" action="systemManage/roleSubmitAction" onSubmit="return doValidate('limitAddManage')">
            <div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>权限查看/修改</span></div></div>
           <table style="width:100%;height:90%;margin: 0px;" class="contentInfo" border="0" cellpadding="0" cellspacing="0">
           <tr>
           <td height="35px">
           <span style="margin-left:8px;">角色名称：</span><input name="roleName" type="text" value="<s:property value='roleName'/>">
            <span class="red">*</span>
           </td>
           </tr>
           <tr>
           <td height="35px">
           <span style="margin-left:8px;">角色分类：</span><s:select name="roleType"  theme="simple" list="#{'0':'员工','1':'领导'}" style="width:154px"></s:select>
		   <span class="red">*</span>
		  </td>
           </tr>
           <tr>
           <td valign="top">
           <div style="width:100%;height:100%;overflow:auto;margin:0px;border:2px solid #eff0f1;">
            <tree:tree id="treeid" nodeId="fmId" pid="fmCategory"  useCheckbox="true" nodeName="fmName" checkedState="state" dataProvider="moduleList"></tree:tree>
            </div>
           </td>
           </tr>
           <tr>
           <td height="50px">
            <div style="width:100%;text-align: center;">
            <input type="submit" name="submit" value="" class="save" />　
				<input type="button" name="Submit2" value="" class="back" onClick="javascript:history.back(-1)">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </div>
           </td>
           </tr>
           </table>
 </form>

</body>
</html>
