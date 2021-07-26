<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.model.Users" %>
<%@ page language="java" import="com.dao.DepartmentDAO" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>监审部门监控页面</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script language="javascript" src="IconPage/page.js"></script>
    <link rel="stylesheet" type="text/css" href="/RiskEvent/css/webstyle.css">
<script src="/RiskEvent/js/jquery-1.7.1.js"></script>
<script src="/RiskEvent/js/frame/middle.js"></script>
<script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript" defer="defer"></script> 
<script language=javascript src="/RiskEvent/js/windows.js"></script>

<style>
.parent	{height:50px; background:#FFF38F;cursor:pointer;color:red}
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

<script type="text/javascript">
$(function(){
	$('tr.parent').click(function(){   // 获取所谓的父行
			$(this)
				.toggleClass("selected")   // 添加/删除高亮
				.siblings('.child_'+this.id).toggle();  // 隐藏/显示所谓的子行
	}).click();
});

function showDiv(o){    
     var hideDiv = o.getElementsByTagName("div")[0]; ;
     hideDiv.style.display="block";//将文本框显示//下边获取坐标并设置坐标    
     hideDiv.style.posLeft= document.body.scrollLeft+event.x-100;  //滚动条坐标+鼠标坐标    
     hideDiv.style.posTop= document.body.scrollTop+event.y;
 }
 
function hideDiv(o){    
    var hideDiv = o.getElementsByTagName("div")[0];;
    hideDiv.style.display="none";//将文本框显示//下边获取坐标并设置坐标    
}
</script>
</head>

<body>
<form id="measureQuery" name="measureQuery" action="riskReply/manageDepSupervise" method="post">
	<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>监审部门监控的预警事件列表</span></div></div>
     	<table class="tablesquery"  width="100%" cellpadding="0"; cellspacing="0";>
     			<%
     				Users user = (Users) session.getAttribute("loginUser");
     				int pt1 = user.getUserPosition();
     				//String dep = departmentDao.getdepname(user.getUserDep());				
     				if(pt1<=5)
     			{%>
     			<tr>
     				<td id="WWW" style="visibility:hidden" align="right" width="1%"> 录入部门：</td>
     				<td width="1%" style="visibility:hidden" ><input  name="reIndep" id="dateFrom" class="Wdate" style="width:1%;" value="<s:property value='reIndep'/>"/></td>

					<td align="right" width="10%">查询时间：<s:select name="choosedId" theme="simple" list="#{'0':'--请选择--','1':'录入时间','2':'发布时间'}"  style="width:154px;" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>
        		</td>
					<td align="left" width="7%"><input name="dateFrom" id="dateFrom" class="Wdate" style="width:90px;" value="<s:property value='dateFrom'/>" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/></td>
                	<td align="right" width="3%"> 至：</td>
               		<td align="left" width="7%"><input name="dateTo" id="dateTo" class="Wdate" style="width:90px;" value="<s:property value='dateTo'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/></td>
               	 	<td align="right" width="8%"> 应对状态：</td>
					<td align="left" width="8%">
					<s:select class="replyState" id="replyState" name="replyState" theme="simple" list="allReplyList" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onchange="changeState()"></s:select>
					</td>
					<td align="right" width="8%"> 监控状态：</td>
					<td align="left" width="8%">
					<s:select class="superviseState" id="superviseState" name="superviseState" theme="simple" list="allSuperviseList" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>
					</td>
                	<td align="right" width="8%">应对时间：</td>
					<td align="left" width="7%"><input name="replyDateFrom" id="replyDateFrom" class="Wdate" style="width:90px;" value="<s:property value='replyDateFrom'/>" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/></td>
                	<td align="right" width="3%"> 至：</td>
               		<td align="left" width="7%"><input name="replyDateTo" id="replyDateTo" class="Wdate" style="width:90px;" value="<s:property value='replyDateTo'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/></td>
               	 	<td width="7%" align="center"><a onclick="keyQuery()"><img src="images/mag.png"/><span>统计查询</span></a></td>
     				<td width="6%" align="center"><a  href="riskSupervise/superviseExcelAction"  ><img src="images/Excel.png"/><span>导出Excel</span></a></td>
     			</tr>
     			<%}else{%>
     			<tr>
     				<td align="right" width="5%"> 录入部门：</td>
     				<td colspan="3">
     				<s:select  style="width:90%;" name="reIndep" theme="simple" list="alldepList" listValue="depName" listKey="depName"></s:select></td>
     				<td align="right" width="10%">查询时间：<s:select name="choosedId" theme="simple" list="#{'0':'--请选择--','1':'录入时间','2':'发布时间'}"  style="width:154px;" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>
        		</td>
					<td align="right" width="6%"><input name="dateFrom" id="dateFrom" class="Wdate" style="width:90px;" value="<s:property value='dateFrom'/>" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/></td>
                	<td align="center" width="3%">至</td>
               		<td align="left" width="6%"><input name="dateTo" id="dateTo" class="Wdate" style="width:90px;" value="<s:property value='dateTo'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/></td>
               	 	<td rowspan="2" width="7%" align="center"><a onclick="keyQuery()"><img src="images/mag.png"/><span>统计查询</span></a></td>
     				<td  rowspan="2" width="6%" align="center"><a  href="riskSupervise/superviseExcelAction"  ><img src="images/Excel.png"/><span>导出Excel</span></a></td>
               	 </tr>
               	 <tr>
               	 <td align="right" width="5%"> 应对状态：</td>
					<td align="left" width="6%">
					<s:select class="replyState" id="replyState" name="replyState" theme="simple" list="allReplyList" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onchange="changeState()"></s:select>
					</td>
					<td align="right" width="5%"> 监控状态：</td>
					<td id="State1" align="left" width="6%">
					<s:select class="superviseState" id="superviseState" name="superviseState" theme="simple" list="allSuperviseList" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>
					</td>
               	 	<td align="right" width="5%">应对时间：</td>
					<td align="right" width="6%"><input id="replyDateFrom" name="replyDateFrom" id="replyDateFrom" class="Wdate" style="width:90px;" value="<s:property value='replyDateFrom'/>" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/></td>
                	<td align="center" width="3%">至</td>
               		<td align="left" width="6%"><input id="replyDateTo" name="replyDateTo" id="replyDateTo" class="Wdate" style="width:90px;" value="<s:property value='replyDateTo'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/></td>
				</tr>
				<%} %>
     	</table>
	<div style="height:400px;overflow:auto;">
	<table id="depTab" width="100%" class="tablestyle">
		<thead class="scrollColThead" >	
			<% 
			String ReplyState = (String)request.getAttribute("replyState");
			String SuperviseState = (String)request.getAttribute("superviseState");
			if("已应对".equals(ReplyState)&&("已监控".equals(SuperviseState)||"--请选择--".equals(SuperviseState))){			
			%>			
			<tr style="position:relative;top:expression(this.offsetParent.scrollTop);padding:0px;">
			  <th colspan="7" width="33%">风险属性</th>
			  <th colspan="3" width="17%">控制措施</th>
			  <th colspan="4" width="20%">制度索引</th>
			  <th width="5%">内控索引</th>
			  <th colspan="1" width="5%">管理责任部门</th>
			  <th width="5%">应对信息</th>
			  <th colspan="3" width="15%">监控信息</th>			  
			</tr>
			<tr style="position:relative;top:expression(this.offsetParent.scrollTop);padding:0px;">
			  <th width="3%">风险序号</th>
			  <th width="5%">二级风险</th>			  
			  <th width="4%">一级风险</th>
			  <th width="5%">部门名称</th>
			  <th width="6%">风险事件名称</th>
			  <th width="6%">风险描述</th>
			  <th width="4%">管理策略</th>
			  <th width="6%">控制措施</th>
			  <th width="5%">计划落实时间</th>
			  <th width="6%">投入资源</th>
			  <th width="5%">三标体系</th>
			  <th width="5%">管理标准</th>
			  <th width="5%">工作标准</th>
			  <th width="5%">应急预案</th>
			  <th width="5%">索引名称</th>

			  <th width="5%">部门名称</th>
			  <th width="5%">应对时间</th>
			  
			  <th width="5%">监控结果</th>
			  <th width="5%">审计监控人</th>
			  <th width="5%">监控时间</th>
			</tr>
			<%}else{ %>
			<tr style="position:relative;top:expression(this.offsetParent.scrollTop);padding:0px;">
			  <th colspan="7" width="40%">风险属性</th>
			  <th colspan="3" width="19%">控制措施</th>
			  <th colspan="4" width="24%">制度索引</th>
			  <th width="6%">内控索引</th>
			  <th colspan="1" width="6%">管理责任部门</th>
			  <th colspan="1" width="5%">监控信息</th>			  
			</tr>
			<tr style="position:relative;top:expression(this.offsetParent.scrollTop);padding:0px;">
			  <th width="3%">风险序号</th>
			  <th width="6%">二级风险</th>			  
			  <th width="5%">一级风险</th>
			  <th width="7%">部门名称</th>
			  <th width="7%">风险事件名称</th>
			  <th width="7%">风险描述</th>
			  <th width="5%">管理策略</th>
			  <th width="7%">控制措施</th>
			  <th width="5%">计划落实时间</th>
			  <th width="7%">投入资源</th>
			  <th width="6%">三标体系</th>
			  <th width="6%">管理标准</th>
			  <th width="6%">工作标准</th>
			  <th width="6%">应急预案</th>
			  <th width="6%">索引名称</th>

			  <th width="6%">部门名称</th>
			  <th width="5%">监控结果</th>
			</tr>
			<%} %>
		</thead>
		<tbody id="tablebody">
		<s:iterator value="ksList2">
			<% 
			if("已应对".equals(ReplyState)&&("已监控".equals(SuperviseState)||"--请选择--".equals(SuperviseState))){						
			%>
		  <tr align="center" onDblClick="openTab('<s:property value='r_reId'/>','<s:property value='r_reEventname'/>')">
			  <td><s:property value="r_riskNum"/></td>
			   <td><s:property value="r_riskName"/></td>		
			  <td><s:property value="r_rtName"/></td>			  
			  <td><s:property value="r_depName"/></td>			  
			  <td><s:property value="r_reEventname"/></td>
			  <%
			  String detail = (String)request.getAttribute("r_reDetail");
			  if("".equals(detail)||detail==null){
			  %>
			  <td><s:property value="r_reDetail"/></td>
			  <%}else if(detail.length()>10){ 
			  detail = detail.substring(0, 10)+"...";
			  %>
			  <td title="<s:property value="r_reDetail"/>" >
			  	<%=detail%>
			  </td>
			  <%} else{%>
			  <td><s:property value="r_reDetail"/></td>	
			  <%}%>		  
			  <td><s:property value="r_rmStrategy"/></td>
			  <%
			  String rmReply = (String)request.getAttribute("r_rmReply");
			  if("".equals(rmReply)||rmReply==null){
			  %>
			  <td style=" color = blue;"><s:property value="r_rmReply"/></td>
			  <%}else if(rmReply.length()>10){ 
			  rmReply = rmReply.substring(0, 10)+"...";
			  %>
			  <td style=" color = blue;" title="<s:property value="r_rmReply"/>" >
			  	<%=rmReply%>
			  </td>
			  <%} else{%>
			  <td style=" color = blue;"><s:property value="r_rmReply"/></td>	
			  <%}%>
			  <td style=" color = blue;"><s:property value="r_rmPlandate"/></td>
			  <%
			  String rmPlanres = (String)request.getAttribute("r_rmPlanres");
			  if("".equals(rmPlanres)||rmPlanres==null||rmPlanres.length()<=10){
			  %>
			  <td><s:property value="r_rmPlanres"/></td>
			  <%}else{ 
			  rmPlanres = rmPlanres.substring(0, 10)+"...";
			  %>
			  <td title="<s:property value="r_rmPlanres"/>" ><%=rmPlanres%></td>
			  <%}%>			  
			  <!--<td><s:property value="r_tsSystem"/></td>-->
			  <%
			  String tsSystem = (String)request.getAttribute("r_tsSystem");
			  if("".equals(tsSystem)||tsSystem==null||tsSystem.length()<=10){
			  %>
			  <td><s:property value="r_tsSystem"/></td>
			  <%}else{ 
			  tsSystem = tsSystem.substring(0, 10)+"...";
			  %>
			  <td title="<s:property value="r_tsSystem"/>" >
			  	<%=tsSystem%>
			  </td>
			  <%}%>
			  <!-- <td><s:property value="r_manageSta"/></td> -->
			   <%
			  String manageSta = (String)request.getAttribute("r_manageSta");
			  if("".equals(manageSta)||manageSta==null||manageSta.length()<=10){
			  %>
			  <td><s:property value="r_manageSta"/></td>
			  <%}else{ 
			  manageSta = manageSta.substring(0, 10)+"...";
			  %>
			  <td title="<s:property value="r_manageSta"/>" >
			  	<%=manageSta%>
			  </td>
			  <%}%>
			  <!-- <td><s:property value="r_workSta"/></td> -->
			  <%
			  String workSta = (String)request.getAttribute("r_workSta");
			  if("".equals(workSta)||workSta==null||workSta.length()<=10){
			  %>
			  <td><s:property value="r_workSta"/></td>
			  <%}else{ 
			  workSta = workSta.substring(0, 10)+"...";
			  %>
			  <td title="<s:property value="r_workSta"/>" >
			  	<%=workSta%>
			  </td>
			  <%}%>
			  <!-- <td><s:property value="r_emergencyPlan"/></td> -->
			  <%
			  String emergencyPlan = (String)request.getAttribute("r_emergencyPlan");
			  if("".equals(emergencyPlan)||emergencyPlan==null||emergencyPlan.length()<=10){
			  %>
			  <td><s:property value="r_emergencyPlan"/></td>
			  <%}else{ 
			  emergencyPlan = emergencyPlan.substring(0, 10)+"...";
			  %>
			  <td title="<s:property value="r_emergencyPlan"/>" >
			  	<%=emergencyPlan%>
			  </td>
			  <%}%>
			  <!-- <td><s:property value="r_icpIndex"/></td> -->
			  <%
			  String icpIndex = (String)request.getAttribute("r_icpIndex");
			  if("".equals(icpIndex)||icpIndex==null||icpIndex.length()<=10){
			  %>
			  <td><s:property value="r_icpIndex"/></td>
			  <%}else{ 
			  icpIndex = icpIndex.substring(0, 10)+"...";
			  %>
			  <td title="<s:property value="r_icpIndex"/>" >
			  	<%=icpIndex%>
			  </td>
			  <%}%>		  
			  <td><s:property value="r_riskDep"/></td>
			  <td><s:property value="r_takeTime"/></td>
			  		  			  
			  <td><s:property value="r_result"/>
			  <%
			  String result = (String)request.getAttribute("r_result");
			  String Rpower = (String)request.getAttribute("r_power");
			  if("未监控".equals(result) && "1".equals(Rpower)){
			  %>
			   <a href="riskSupervise/SuperviseAction?reId=<s:property value='r_reId'/>" onClick="return window.confirm('确实已监控吗?')">监控</a>
			   <%} %>	
			  </td>	
			  <td><s:property value="r_supervisor"/></td>	
			  <td><s:property value="r_time"/></td>
		  </tr>
		  <%}else{ %>
		  <tr align="center" onDblClick="openTab('<s:property value='r_reId'/>','<s:property value='r_reEventname'/>')">
			  <td><s:property value="r_riskNum"/></td>
			  <td><s:property value="r_riskName"/></td>		
			  <td><s:property value="r_rtName"/></td>			  
			  <td><s:property value="r_depName"/></td>			  
			  <td><s:property value="r_reEventname"/></td>
			  <%
			  String detail = (String)request.getAttribute("r_reDetail");
			  if("".equals(detail)||detail==null){
			  %>
			  <td><s:property value="r_reDetail"/></td>
			  <%}else if(detail.length()>10){ 
			  detail = detail.substring(0, 10)+"...";
			  %>
			  <td title="<s:property value="r_reDetail"/>" >
			  	<%=detail%>
			  </td>
			  <%} else{%>
			  <td><s:property value="r_reDetail"/></td>	
			  <%}%>		  
			  <td><s:property value="r_rmStrategy"/></td>
			  <%
			  String rmReply = (String)request.getAttribute("r_rmReply");
			  if("".equals(rmReply)||rmReply==null){
			  %>
			  <td style=" color = blue;"><s:property value="r_rmReply"/></td>
			  <%}else if(rmReply.length()>10){ 
			  rmReply = rmReply.substring(0, 10)+"...";
			  %>
			  <td style=" color = blue;" title="<s:property value="r_rmReply"/>" >
			  	<%=rmReply%>
			  </td>
			  <%} else{%>
			  <td style=" color = blue;"><s:property value="r_rmReply"/></td>	
			  <%}%>
			  <td style=" color = blue;"><s:property value="r_rmPlandate"/></td>
			  <%
			  String rmPlanres = (String)request.getAttribute("r_rmPlanres");
			  if("".equals(rmPlanres)||rmPlanres==null||rmPlanres.length()<=10){
			  %>
			  <td><s:property value="r_rmPlanres"/></td>
			  <%}else{ 
			  rmPlanres = rmPlanres.substring(0, 10)+"...";
			  %>
			  <td title="<s:property value="r_rmPlanres"/>" ><%=rmPlanres%></td>
			  <%}%>			  
			  <!--<td><s:property value="r_tsSystem"/></td>-->
			  <%
			  String tsSystem = (String)request.getAttribute("r_tsSystem");
			  if("".equals(tsSystem)||tsSystem==null||tsSystem.length()<=10){
			  %>
			  <td><s:property value="r_tsSystem"/></td>
			  <%}else{ 
			  tsSystem = tsSystem.substring(0, 10)+"...";
			  %>
			  <td title="<s:property value="r_tsSystem"/>" >
			  	<%=tsSystem%>
			  </td>
			  <%}%>
			   <%
			  String manageSta = (String)request.getAttribute("r_manageSta");
			  if("".equals(manageSta)||manageSta==null||manageSta.length()<=10){
			  %>
			  <td><s:property value="r_manageSta"/></td>
			  <%}else{ 
			  manageSta = manageSta.substring(0, 10)+"...";
			  %>
			  <td title="<s:property value="r_manageSta"/>" >
			  	<%=manageSta%>
			  </td>
			  <%}%>
			  <%
			  String workSta = (String)request.getAttribute("r_workSta");
			  if("".equals(workSta)||workSta==null||workSta.length()<=10){
			  %>
			  <td><s:property value="r_workSta"/></td>
			  <%}else{ 
			  workSta = workSta.substring(0, 10)+"...";
			  %>
			  <td title="<s:property value="r_workSta"/>" >
			  	<%=workSta%>
			  </td>
			  <%}%>
			  <!-- <td><s:property value="r_emergencyPlan"/></td> -->
			  <%
			  String emergencyPlan = (String)request.getAttribute("r_emergencyPlan");
			  if("".equals(emergencyPlan)||emergencyPlan==null||emergencyPlan.length()<=10){
			  %>
			  <td><s:property value="r_emergencyPlan"/></td>
			  <%}else{ 
			  emergencyPlan = emergencyPlan.substring(0, 10)+"...";
			  %>
			  <td title="<s:property value="r_emergencyPlan"/>" >
			  	<%=emergencyPlan%>
			  </td>
			  <%}%>
			  <!-- <td><s:property value="r_icpIndex"/></td> -->
			  <%
			  String icpIndex = (String)request.getAttribute("r_icpIndex");
			  if("".equals(icpIndex)||icpIndex==null||icpIndex.length()<=10){
			  %>
			  <td><s:property value="r_icpIndex"/></td>
			  <%}else{ 
			  icpIndex = icpIndex.substring(0, 10)+"...";
			  %>
			  <td title="<s:property value="r_icpIndex"/>" >
			  	<%=icpIndex%>
			  </td>
			  <%}%>			  
			  <td><s:property value="r_riskDep"/></td>		  			  
			  <td><s:property value="r_result"/>
			  <%
			  String result = (String)request.getAttribute("r_result");
			  String Rpower = (String)request.getAttribute("r_power");
			  if("未监控".equals(result) && "1".equals(Rpower)){
			  %>
			   <a href="riskSupervise/SuperviseAction?reId=<s:property value='r_reId'/>" onClick="return window.confirm('确实已监控吗?')">监控</a>
			   <%} %>
			   </td>
		  </tr>
		  <% }%>
		  </s:iterator>
		</tbody>
	</table></div>
	</form>
</body>
<script type="text/javascript">
senfe('depTab','#f7f7f7','#fff','#d9ebf5','#e9f2f7');

openBox('统计提示',350,150,0,'正在统计中，请等待....','');
//得到table对象
    var mytable=document.getElementById("depTab");
    //向table中插入一行
   var index = mytable.rows.length; 
   var num=1;
   for(var i=1;i<index;i++)
   {
    var text1 = mytable.rows[i].cells[0].innerText.replace(" ","");//获取第i行的第一个单元格firstChild中的内容
     mytable.rows[i].className="child_row_"+num;
      var j=i+1;
     if(j<index)//如果下一行不是最后一行
    { 
    var text2 = mytable.rows[j].cells[0].innerText.replace(" ","");
     if(!(text1==text2))
   {  
    mytable.rows[j-1].className="parent";
    mytable.rows[j-1].id="row_"+num;
    num++;
    }
    }
    }
    if(j==index)
    {
    mytable.rows[j-1].className="parent";
    mytable.rows[j-1].id="row_"+num;
    }
    
    var maxCol = 4, val, count, start;  //maxCol：合并单元格作用到多少列    
    for(var col = maxCol-1; col >= 0 ; col--){
        count = 1;
        val = "";
        for(var i=1; i<mytable.rows.length; i++){
            if(val == mytable.rows[i].cells[col].innerHTML){
                count++;
            }else{
                if(count > 1){ //合并
                    start = i - count;
                    mytable.rows[start].cells[col].rowSpan = count;
                    for(var j=start+1; j<i; j++){
                      //  mytable.rows[j].cells[col].style.display = "none";
                        mytable.rows[j].cells[col].parentNode.removeChild( mytable.rows[j].cells[col]);
                    }
                    count = 1;
                }
                val = mytable.rows[i].cells[col].innerHTML;
            }
        }
        if(count > 1 ){ //合并，最后几行相同的情况下
            start = i - count;
            mytable.rows[start].cells[col].rowSpan = count;
            for(var j=start+1; j<i; j++){
                mytable.rows[j].cells[col].parentNode.removeChild( mytable.rows[j].cells[col]);
            }
        }
    }
closeLoginBox();
     
function keyQuery(){
openBox('统计提示',350,150,0,'正在统计中，请等待....','');
	measureQuery.action="riskSupervise/SuperviseQueryAction";
	measureQuery.submit();
}

function openTab(reId,eventname)
{	
	if (reId !== null && reId !== undefined && reId !== '') 
	{ 
		var url = "riskInput/REIQReadAction?reId="+reId + "&backFlag=reply";
		window.parent.openTab(url,"风险应对-" + eventname);
	} 	
};

function changeState()
{	  
	var replystate2 = $('#replyState option:selected').val();//这是获得选中的值
	if("已应对" != replystate2){
		$('#superviseState').val("--请选择--");
		$("#superviseState").attr("disabled",true);
		$('#replyDateFrom').val("");
		$("#replyDateFrom").attr("disabled",true);
		$('#replyDateTo').val("");
		$("#replyDateTo").attr("disabled",true);
	}else{
		$("#superviseState").attr("disabled",false); 
		$('#replyDateFrom').val("");
		$("#replyDateFrom").attr("disabled",false);
		$('#replyDateTo').val("");
		$("#replyDateTo").attr("disabled",false);
	}	
};

	var replystate2 = $('#replyState option:selected').val();//这是获得选中的值
	if("已应对" != replystate2){
		$('#superviseState').val("--请选择--");
		$("#superviseState").attr("disabled",true);
		$('#replyDateFrom').val("");
		$("#replyDateFrom").attr("disabled",true);
		$('#replyDateTo').val("");
		$("#replyDateTo").attr("disabled",true);
	}else{
		$("#superviseState").attr("disabled",false); 
		$("#replyDateFrom").attr("disabled",false);
		$("#replyDateTo").attr("disabled",false);
	}

</script>

</html>
