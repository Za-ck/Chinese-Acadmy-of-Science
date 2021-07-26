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
    
    <title>风险事件-详细列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="X-UA-Compatible"content="IE=7" /><!--以IE7模式渲染-->

	<script language="javascript" src="IconPage/page.js"></script>
    <link rel="stylesheet" type="text/css" href="/RiskEvent/css/webstyle.css">
<script src="/RiskEvent/js/jquery-1.7.1.js"></script>
<script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript" defer="defer"></script> 
<script language=javascript src="/RiskEvent/js/windows.js"></script>

<style>
.Freezing_tdd 
{ 
top:expression(document.getElementById('div-1').scrollTop-1); 
    position:relative ; 
   z-index:100 
} 
.parent	{height:50px; background:#FFF38F;cursor:pointer;color:red}  /* 偶数行样式*/
.odd{ background:#FFFFEE;}  /* 奇数行样式*/
.selected{
background:#FFF38F;
height:50px;
border-bottom:1px solid #000000;
color:#000000;
}

.tablestyle{
    overflow:auto;
	font-size:12px;
    border-collapse:collapse;
	border:2px solid #dddddd;
}
.tablestyle td {border:1px solid #e2e1e1;text-align:center; height:25px;}
.tablestyle thead th {
	color:#818181;
	height:30px;
	border:1px solid #dadbdb;
	font-size:15px;
    font-weight:lighter;
	text-align:center;
	background-image: url("/RiskEvent/IconPage/images/thbg2.png");
    }

</style>


</head>

<body>
<form id="GenAnalysisAllRisk" name="GenAnalysisAllRisk" action="riskAnalysis/GenAnaAllRiskAction" method="post">
  <div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>风险事件-详细列表</span></div></div>
        
    
    <div style="height:400px;overflow:auto;">
	<table id="depTab" width="100%" class="tablestyle">
		<thead>
			<tr  style="position:relative;top:expression(this.offsetParent.scrollTop);padding:0px;">
			
			 <th>部门序号</th>
              <th>录入部门</th>
              <th>二级风险序号</th> 
              <th>二级风险编号</th> 
              <th>二级风险</th>	
              <th>一级风险</th>    
			  <th>风险事件名称</th>
			  <th>影响的关键绩效指标</th>
			  <th>可能性/概率</th>  	    
			  <th>对财务的影响</th>
			  <th>对声誉的影响</th>
			  <th>对法律法规的影响</th>
			  <th>对客户关系的影响</th>
			  <th>对员工满意度的影响</th>
			  <th>对运营的影响</th>
			  <th>对安全、健康和环境的影响</th>
			  <th>影响程度</th>
			  <th>财务评定</th>
			  <th>声誉评定</th>
			  <th>合规评定</th>
			  <th>客户关系评定</th>
			  <th>员工满意度评定</th>
			  <th>运营评定</th>
			  <th>安全评定</th>
			  <th>综合评定</th>
			  <th>风险事件录入时间</th> 
		    </tr> 
		</thead>
		<tbody id="tablebody">
		<s:iterator value="eventDetailList">
		
			<tr>
			  <td><s:property value="depNum"/></td>
			  <td><s:property value="depName"/></td>
			  <td><s:property value="riNum"/></td>
			  <td><s:property value="riskId"/></td>
			  <td><s:property value="riskName"/></td>
			  <td><s:property value="rtName"/></td>
			  <td><s:property value="reEventname"/></td>
			  <td><s:property value="riKpi"/></td>
			  <td><s:property value="riProdegree"/></td>
			  <td><s:property value="riFindegree"/></td>
			  <td><s:property value="riFamedegree"/></td>
			  <td><s:property value="riLawdegree"/></td>
			  <td><s:property value="riClidegree"/></td>
			  <td><s:property value="riEmpdegree"/></td>
			  <td><s:property value="riOpedegree"/></td>
			  <td><s:property value="riSafedegree"/></td>
			  <td><s:property value="riAlldegree"/></td>
			  <td><s:property value="riFinvalue"/></td>
			  <td><s:property value="riFramevalue"/></td>
			  <td><s:property value="riLawvalue"/></td>
			  <td><s:property value="riClivalue"/></td>
			  <td><s:property value="riEmpvalue"/></td>
			  <td><s:property value="riOpevalue"/></td>
			  <td><s:property value="riSafevalue"/></td>
			  <td><s:property value="riAllvalue"/></td>
			  <td><s:property value="riEventDate"/></td>
		  </tr>
		  </s:iterator>

		</tbody>
	</table></div>
	</form>
</body>
</html>
