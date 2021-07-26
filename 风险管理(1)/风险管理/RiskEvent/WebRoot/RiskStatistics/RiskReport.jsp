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
    
    <title>全面风险坐标图</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script language="javascript" src="IconPage/page.js"></script>	
	<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
    <link rel="stylesheet" type="text/css" href="/RiskEvent/css/webstyle.css">
<script src="/RiskEvent/js/jquery-1.7.1.js"></script>
<script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript" defer="defer"></script> 
</head>

<body>
<form id="riskReport" name="riskReport" action="" method="post">
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>风险坐标图</span></div></div>
  	<table class="tablesquery"  width="100%" cellpadding="0"; cellspacing="0";>
     			<tr> 
     				<td align="right" width="150px" height="32px">录入年份：</td>
     					<td width="60"> 
     					 <input name="year" id="year" style="width:90px;" class="Wdate" value="<s:property value="year"/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy'})"/>
     						</td>     					
     					     	<td width="40">	</td>	
                             <td width="120" align="center">
                             <a onClick="reportQuery()" style="width:120px;"><img src="images/mag.png"/><span>查看坐标图</span></a>
                             </td>
                             <td width="120" align="center"> <a onClick="reportExport()" style="width:120px;"><img  width="32" height="32" src="images/Word.png"/><span>导出坐标图</span></a>
     					     </td>     				
     					     <td width="800"></td>
     			</tr>
     			<tr>
     			<td width="30px"></td>
     			<td colspan="5">
     			将我院风险评估的定性、定量风险得出的风险坐标图分A、B、C三个区域，如图2所示。     			
     			</td>
     			</tr>     			
     			<tr>
     		    <td colspan="6" align="center">
                <table align="center" width="600" height="600" border="0">                
                <tr>
                <td>            
     			<img alt="" src="<s:property value="path"/>" style="width:600px;height:600px"/>
     			</td>
                </tr>
                </table>
     			</tr>
     			 <tr >
     		      <td  colspan="6" align="center">图2：风险坐标图分区图</td>
     			</tr>
     			<tr> 
     		     <td  colspan="6" id="show"></td>
     			</tr>
     		 <tr height="20px">
     		  <td  colspan="6">
     		  <input id="areadetail" type=hidden value="<s:property value="areadetail"/>"/></td>
     		  </tr>
     			
     		</table>
	</form>

<script type="text/javascript">
var detail=document.getElementById('areadetail').value;
var arrayd=detail.split("；");
var showid=document.getElementById('show');

arrayd[0]="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp"+arrayd[0];
for(var i=0;i<arrayd.length-1;i++)
{

arrayd[i]=arrayd[i]+"；"+"<br/>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
showid.innerHTML=showid.innerHTML+arrayd[i];//添加描述
}
showid.innerHTML=showid.innerHTML+arrayd[arrayd.length-1];//加入最后一段描述

function reportExport(){
	riskReport.action="riskStatistic/riskReportAction";
	riskReport.submit();
}
function reportQuery(){
	riskReport.action="riskStatistic/reportQueryAction?tmpnum="+ Math.random();
	riskReport.submit();
}
    </script>
    </body>
    
</html>
