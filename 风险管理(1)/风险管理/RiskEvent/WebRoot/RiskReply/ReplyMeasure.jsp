<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.model.Users" %>
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
    	
    <title>风险应对措施列表</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	

	<script language="javascript" src="IconPage/page.js"></script>
    <link rel="stylesheet" type="text/css" href="/RiskEvent/css/webstyle.css">
<script src="/RiskEvent/js/jquery-1.7.1.js"></script>
<script src="/RiskEvent/js/frame/middle.js"></script>
<script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript" ></script> 
<script language=javascript src="/RiskEvent/js/windows.js"></script>

<style>
.Freezing_tdd 
{ 
top:expression(document.getElementById('div-1').scrollTop-1); 
    position:relative ; 
   z-index:100 
} 
.parent	{height:50px; background:#FFF38F;cursor:pointer;color:red;}  /* 偶数行样式*/
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
<script type="text/javascript">

$(function(){
	$('tr.parent').click(function(){   // 获取所谓的父行
			$(this)
				.toggleClass("selected")   // 添加/删除高亮
				.siblings('.child_'+this.id).toggle();  // 隐藏/显示所谓的子行
	}).click();
});

</script>
</head>

<body>
<form id="measureQuery" name="measureQuery" action="riskReply/MeasureQueryAction" method="post">
	<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>风险应对措施列表</span></div></div>
		<table class="tablesquery"  width="100%" cellpadding="0" cellspacing="0"> 
     	<tr> 
     			<%		
     				Users us = (Users) session.getAttribute("loginUser");
     				int pt = us.getUserPosition();		
     				if(pt<=5)
     			{%>
     				<td id="WWW" style="visibility:hidden" align="right" width="4%"> 录入部门：</td>
     				<td align="left" style="visibility:hidden" width="4%"><input  name="reIndep" class="Wdate" style="width:1px;" value="<s:property value='reIndep'/>"/></td>
     				<td align="left" width="8%"> 管理责任部门：</td>
     				<td align="left" width="18%">
     				<s:select name="reMadep" theme="simple" list="alldepList" listValue="depName" listKey="depName"></s:select></td>
     				<td align="right" width="8%"> 查询时间：</td>
     				<td width="8%" align="right"><s:select name="choosedId" theme="simple" list="#{'0':'--请选择--','1':'录入时间','2':'发布时间','3':'应对时间'}" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>
</td>
					<td align="left" width="6%"><input name="dateFrom" id="dateFrom" class="Wdate" style="width:90px;" value="<s:property value='dateFrom'/>" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/></td>
                	<td align="right" width="3%"> 至：</td>
               		<td align="left" width="6%"><input name="dateTo" id="dateTo" class="Wdate" style="width:90px;" value="<s:property value='dateTo'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/></td>
                	<td width="3%"></td>
                	<td width="7%" align="center"><a onclick="keyQuery()"><img src="images/mag.png"/><span>统计查询</span></a></td>
     				<td width="2%"></td>
     				<td width="6%" align="center"><a  href="riskReply/measureExcelAction"  ><img src="images/Excel.png"/><span>导出Excel</span></a></td>
     				<td width="8%" ></td>
     			<%}else{%>
     				<td align="right" width="6%"> 录入部门：</td>
     				<td align="left" width="10%">
     				<s:select name="reIndep" style="width:220px" theme="simple" list="alldepList" listValue="depName" listKey="depName"></s:select></td>
     				<td align="right" width="7%"> 管理责任部门：</td>
     				<td align="left" width="10%">
     				<s:select name="reMadep" style="width:220px" theme="simple" list="alldepList" listValue="depName" listKey="depName"></s:select></td>	
					<td align="right" width="8%"> 查询时间：</td>
     				<td width="8%" align="right"><s:select name="choosedId" theme="simple" list="#{'0':'--请选择--','1':'录入时间','2':'发布时间','3':'应对时间'}" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>
</td>
					<td align="left" width="6%"><input name="dateFrom" id="dateFrom" class="Wdate" style="width:90px;" value="<s:property value='dateFrom'/>" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/></td>
                	<td align="right" width="3%"> 至：</td>
               		<td align="left" width="6%"><input name="dateTo" id="dateTo" class="Wdate" style="width:90px;" value="<s:property value='dateTo'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/></td>
               		<td width="6%" align="center"><a onclick="keyQuery()"><img src="images/mag.png"/><span>统计查询</span></a></td>
     				<td width="6%" align="center"><a  href="riskReply/measureExcelAction"  ><img src="images/Excel.png"/><span>导出Excel</span></a></td>
				<%} %>
     		</tr>
     	</table>
	<div style="height:400px;overflow:auto;">
	<table id="depTab" width="100%" class="tablestyle">
		<thead class="scrollColThead" >	
		<tr style="position:relative;top:expression(this.offsetParent.scrollTop);padding:0px;">
			  <th colspan="8" width="44%">风险属性</th>
			  <th colspan="3" width="19%">控制措施</th>
			  <th colspan="4" width="20%">制度索引</th>
			  <th width="5%">内控索引</th>
			  <th colspan="1" width="5%">录入信息</th>
			  <th colspan="1" width="7%">管理责任部门</th>

			</tr>
			<tr style="position:relative;top:expression(this.offsetParent.scrollTop);padding:0px;">
			  <th width="3%">风险序号</th>
			  <th width="7%">二级风险</th>			  
			  <th width="4%">一级风险</th>
			  <th width="7%">录入部门</th>
			  <th width="5%">风险事件编号</th>
			  <th width="6%">风险事件名称</th>
			  <th width="6%">风险描述</th>
			  <th width="6%">管理策略</th>
			  <th width="7%">控制措施</th>
			  <th width="5%">计划时间</th>
			  <th width="7%">投入资源</th>
			  <th width="5%">三标体系</th>
			  <th width="5%">管理标准</th>
			  <th width="5%">工作标准</th>
			  <th width="5%">应急预案</th>
			  <th width="5%">索引名称</th>
			  <th width="5%">录入人</th>
			  <th width="7%">部门名称</th>
			</tr>			
		</thead>
		<tbody id="tablebody">
		<s:iterator value="ksList2">	
			<tr align="center" onDblClick="openTab('<s:property value='m_reId'/>','<s:property value='m_reEventname'/>')">	
			  <td><s:property value="m_riskNum"/></td>
			  <td><s:property value="m_riskName"/></td>		
			  <td><s:property value="m_rtName"/></td>			  
			  <td><s:property value="m_depName"/></td>
			  <td><s:property value="m_reId"/></td>
			  <td><s:property value="m_reEventname"/></td>
			  <%
			  String detail = (String)request.getAttribute("m_reDetail");
			  if("".equals(detail)||detail==null){
			  %>
			  <td><s:property value="m_reDetail"/></td>
			  <%}else if(detail.length()>10){ 
			  detail = detail.substring(0, 10)+"...";
			  %>
			  <td title="<s:property value="m_reDetail"/>" >
			  	<%=detail%>
			  </td>
			  <%} else{%>
			  <td><s:property value="m_reDetail"/></td>	
			  <%}%>
			  <td><s:property value="m_rmStrategy"/></td>
			  <%
			  String rmReply = (String)request.getAttribute("m_rmReply");
			  if("".equals(rmReply)||rmReply==null){
			  %>
			  <td style=" color = blue;"><s:property value="m_rmReply"/></td>
			  <%}else if(rmReply.length()>10){ 
			  rmReply = rmReply.substring(0, 10)+"...";
			  %>
			  <td style=" color = blue;" title="<s:property value="m_rmReply"/>" >
			  <%=rmReply%>
			  </td>
			  <%} else{%>
			  <td style=" color = blue;"><s:property value="m_rmReply"/></td>	
			  <%}%>
			  <td style=" color = blue;"><s:property value="m_rmPlandate"/></td>
			  <!-- <td><s:property value="m_rmPlanres"/></td> -->
			  <%
			  String rmPlanres = (String)request.getAttribute("m_rmPlanres");
			  if("".equals(rmPlanres)||rmPlanres==null||rmPlanres.length()<=10){
			  %>
			  <td><s:property value="m_rmPlanres"/></td>
			  <%}else{ 
			  rmPlanres = rmPlanres.substring(0, 10)+"...";
			  %>
			  <td title="<s:property value="m_rmPlanres"/>" >
			  	<%=rmPlanres%>
			  </td>
			  <%}%>
			  <!-- <td><s:property value="m_tsSystem"/></td> -->
			  <%
			  String tsSystem = (String)request.getAttribute("m_tsSystem");
			  if("".equals(tsSystem)||tsSystem==null||tsSystem.length()<=10){
			  %>
			  <td><s:property value="m_tsSystem"/></td>
			  <%}else{ 
			  tsSystem = tsSystem.substring(0, 10)+"...";
			  %>
			  <td title="<s:property value="m_tsSystem"/>" >
			  	<%=tsSystem%>
			  </td>
			  <%}%>
			 <!--  <td><s:property value="m_manageSta"/></td>-->
			  <%
			  String manageSta = (String)request.getAttribute("m_manageSta");
			  if("".equals(manageSta)||manageSta==null||manageSta.length()<=10){
			  %>
			  <td><s:property value="m_manageSta"/></td>
			  <%}else{ 
			  manageSta = manageSta.substring(0, 10)+"...";
			  %>
			  <td title="<s:property value="m_manageSta"/>" >
			  	<%=manageSta%>
			  </td>
			  <%}%>
			  <!--  <td><s:property value="m_workSta"/></td>-->
			  <%
			  String workSta = (String)request.getAttribute("m_workSta");
			  if("".equals(workSta)||workSta==null||workSta.length()<=10){
			  %>
			  <td><s:property value="m_workSta"/></td>
			  <%}else{ 
			  workSta = workSta.substring(0, 10)+"...";
			  %>
			  <td title="<s:property value="m_workSta"/>" >
			  	<%=workSta%>
			  </td>
			  <%}%>
			  <!--  <td><s:property value="m_emergencyPlan"/></td>-->
			  <%
			  String emergencyPlan = (String)request.getAttribute("m_emergencyPlan");
			  if("".equals(emergencyPlan)||emergencyPlan==null||emergencyPlan.length()<=10){
			  %>
			  <td><s:property value="m_emergencyPlan"/></td>
			  <%}else{ 
			  emergencyPlan = emergencyPlan.substring(0, 10)+"...";
			  %>
			  <td title="<s:property value="m_emergencyPlan"/>" >
			  	<%=emergencyPlan%>
			  </td>
			  <%}%>
			  <!--  <td><s:property value="m_icpIndex"/></td>-->
			  <%
			  String icpIndex = (String)request.getAttribute("m_icpIndex");
			  if("".equals(icpIndex)||icpIndex==null||icpIndex.length()<=10){
			  %>
			  <td><s:property value="m_icpIndex"/></td>
			  <%}else{ 
			  icpIndex = icpIndex.substring(0, 10)+"...";
			  %>
			  <td title="<s:property value="m_icpIndex"/>" >
			  	<%=icpIndex%>
			  </td>
			  <%}%>	
			  <td><s:property value="m_reCreator"/></td>
			  <td><s:property value="m_riskDep"/></td>
		  </tr>
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
    var text1 = mytable.rows[i].cells[0].innerText.replace(" ","");//获取行的第一个单元格firstChild中的内容
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
    
    var maxCol = 5, val, count, start;  //maxCol：合并单元格作用到多少列    
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
               // mytable.rows[j].cells[col].style.display = "none";
                mytable.rows[j].cells[col].parentNode.removeChild( mytable.rows[j].cells[col]);
            }
        }
    }
closeLoginBox();
     
function keyQuery(position){
openBox('统计提示',350,150,0,'正在统计中，请等待....','');
	var pt = <%=pt%>;
	measureQuery.action="riskReply/MeasureQueryAction?type="+pt;
	measureQuery.submit();
}

function openTab(reId,eventname)
{	
	if (reId !== null && reId !== undefined && reId !== '' && reId !=="-") 
	{ 
		var url = "riskInput/REIQReadAction?reId="+reId+"&backFlag=reply";
		window.parent.openTab(url,"风险应对-" + eventname);
	}
};

</script>

</html>
