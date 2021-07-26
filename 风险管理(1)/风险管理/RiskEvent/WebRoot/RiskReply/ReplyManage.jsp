<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.model.Users" %>
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
    <title>风险应对管理</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">  
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">  

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
<form id="ReplyManageQuery" name="ReplyManageQuery" method="post" action="riskReply/replyManage">
	<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>风险应对管理列表</span></div></div>
		<table class="tablesquery"  width="100%" cellpadding="0" cellspacing="0"> 
     		<tr>
     			<%
     				Users us = (Users) session.getAttribute("loginUser");
     				int pt = us.getUserPosition();				
     				if(pt<=5)
     			{%>
     				<td id="WWW" style="visibility:hidden" align="right" width="6%"> 录入部门：</td>
     				<td align="left" style="visibility:hidden" width="10%"><input  name="reIndep" id="dateFrom" class="Wdate" style="width:90px;" value="<s:property value='reIndep'/>"/></td>	
     				<td align="right" width="8%"> 查询时间：</td>
     				<td width="8%" align="right"><s:select name="choosedId" theme="simple" list="#{'0':'--请选择--','1':'录入时间','2':'发布时间','3':'应对时间'}" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>
</td>
					<td align="left" width="7%"><input name="dateFrom" id="dateFrom" class="Wdate" style="width:90px;" value="<s:property value='dateFrom'/>" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/></td>
                	<td align="right" width="3%"> 至：</td>
               		<td align="left" width="7%"><input name="dateTo" id="dateTo" class="Wdate" style="width:90px;" value="<s:property value='dateTo'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/></td>
					<td width="3%"></td>
					<td align="right" width="8%"> 应对状态：</td>
					<td align="left" width="8%">
						<s:select name="state" theme="simple" style="width:90px" list="allstateList" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>
					</td>
               	 	<td width="3%"></td>
                	<td width="7%" align="center"><a onclick="Query()"><img src="images/mag.png"/><span>统计查询</span></a></td>
     				<td width="2%"></td>
     				<td width="6%" align="center"><a  href="riskReply/manageExcelAction"  ><img src="images/Excel.png"/><span>导出Excel</span></a></td>
     				<td width="35%"></td>
     			<%}else{%>
     				<td align="right" width="10%"> 录入部门：</td>
     				<td align="left" width="18%">
     				<s:select name="reIndep" theme="simple" list="alldepList" listValue="depName" listKey="depName"></s:select></td>	
					<td align="right" width="8%"> 查询时间：</td>
     				<td width="8%" align="right"><s:select name="choosedId" theme="simple" list="#{'0':'--请选择--','1':'录入时间','2':'发布时间','3':'应对时间'}" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>
</td>
					<td align="left" width="7%"><input name="dateFrom" id="dateFrom" class="Wdate" style="width:90px;" value="<s:property value='dateFrom'/>" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/></td>
               	 	<td align="right" width="3%"> 至：</td>
               		<td align="left" width="7%"><input name="dateTo" id="dateTo" class="Wdate" style="width:90px;" value="<s:property value='dateTo'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/></td>
					<td align="right" width="8%"> 应对状态：</td>
					<td align="left" width="8%">
						<s:select name="state" theme="simple" style="width:90px" list="allstateList" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>
					</td>
                	<td width="3%"></td>
                	<td width="6%" align="center"><a onclick="Query()"><img src="images/mag.png"/><span>统计查询</span></a></td>
     				<td width="2%"></td>
     				<td width="6%" align="center"><a  href="riskReply/manageExcelAction"  ><img src="images/Excel.png"/><span>导出Excel</span></a></td>
					<td width="4%"></td>
				<%} %>
				</tr>
     	</table>
<div style="height:400px;overflow:auto;" id='pagetable' id='pagetable'>
	<table id="depTab" width="100%" class="tablestyle">
		<thead>
		<tr style="position:relative;top:expression(this.offsetParent.scrollTop);padding:0px;">
			<%
			  String state1 = (String)request.getAttribute("state");
			  if("未应对".equals(state1)){
			%>
			<th width="5%">部门序号</th>
 			<th width="9%">部门名称</th>
			<th width="9%">二级风险</th>	
			<th width="7%">一级风险</th>    
			<th width=9%">风险事件名称</th>
			<th width="6%">影响程度</th> 
			<th width="6%">发生概率</th> 
			<th width="8%">影响的关键绩效指标</th>
			<th width="6%">综合评定</th>
			<th width="10%">控制措施</th>
			<th width="6%">计划落实时间</th>
			<th width="10%">投入资源</th>
			<th width="9%">应对状态</th>			
			<%}else{%>			
			<th width="4%">部门序号</th>
 			<th width="8%">部门名称</th>
			<th width="8%">二级风险</th>	
			<th width="6%">一级风险</th>    
			<th width="8%">风险事件名称</th>
			<th width="4%">影响程度</th> 
			<th width="4%">发生概率</th> 
			<th width="8%">影响的关键绩效指标</th>
			<th width="5%">综合评定</th>
			<th width="8%">控制措施</th>
			<th width="7%">计划落实时间</th>
			<th width="9%">投入资源</th>
			<th width="9%">应对状态</th>
			<th width="5%">应对人</th>
			<th width="7%">应对时间</th>
			<%} %>		  
		</tr> 
		</thead>
		<tbody id="tablebody">
		<s:iterator value="rmlist">
			
			<tr align="center" onDblClick="openTab('<s:property value='reId'/>','<s:property value='reEventname'/>')">
			  <td><s:property value="depNum"/></td>
			  <td><s:property value="depName"/></td>
			  <td><s:property value="riskName"/></td>
			  <td><s:property value="rtName"/></td>
			  <td><s:property value="reEventname"/></td>
			  <td><s:property value="riAlldegree"/></td>
			  <td><s:property value="riProdegree"/></td>
			  <!--  xtd><s:property value="riKpi"/></td>-->
			  <%
			  String kp = (String)request.getAttribute("riKpi");
			  if("".equals(kp)||kp==null||kp.length()<=10){
			  %>
			  <td><s:property value="riKpi"/></td>
			  <%}else{ 
			  kp = kp.substring(0, 10)+"...";
			  %>
			  <td title="<s:property value="riKpi"/>" >
			  	<%=kp%>
			  </td>
			  <%}%>
			  <td><s:property value="riAllvalue"/></td>			  
			  <!--<td><s:property value="riReply"/></td>
			  <td><s:property value=""/></td>
			  -->
			  <%
			  String rmReply = (String)request.getAttribute("riReply");
			  if("".equals(rmReply)||rmReply==null){
			  %>
			  <td style=" color = blue;"><s:property value="riReply"/></td>
			  <%}else if(rmReply.length()>10){ 
			  rmReply = rmReply.substring(0, 10)+"...";
			  %>
			  <td style=" color = blue;" title="<s:property value="riReply"/>" >
			  	<%=rmReply%>
			  </td>
			  <%} else{%>
			  <td style=" color = blue;"><s:property value="riReply"/></td>	
			  <%}%>
			  <td style=" color = blue;"><s:property value="riplandate"/></td>
			  <!-- <td><s:property value="riplanres"/></td> -->
			  <%
			  String rmPlanres = (String)request.getAttribute("riplanres");
			  if("".equals(rmPlanres)||rmPlanres==null||rmPlanres.length()<=10){
			  %>
			  <td><s:property value="riplanres"/></td>
			  <%}else{ 
			  rmPlanres = rmPlanres.substring(0, 10)+"...";
			  %>
			  <td title="<s:property value="riplanres"/>" >
			  	<%=rmPlanres%>
			  </td>
			  <%}%>
			  <%
			  String state2 =  (String)request.getAttribute("riState");
			  String taketime = (String)request.getAttribute("ritaketime");
			  String userPower = (String)request.getAttribute("userPower");
			  if("未应对".equals(state1)){			  
			  if((taketime==null||taketime.length()<2)&&"1".equals(userPower)){
			   %>
			   <td><s:property value="riState"/>
			  <a  href="riskReply/RiskHandleAction1?reId=<s:property value='reId'/>&dateFrom=<s:property value='dateFrom'/>&dateTo=<s:property value='dateTo'/>&state=<s:property value='state'/>&reIndep=<s:property value='reIndep'/>" onClick="return window.confirm('确实已应对吗?')">应对</a>
			  </td>
			  <%}else{ %>
			  <td><s:property value="riState"/></td>			  
			  <%}}else{
			  if((taketime==null||taketime.length()<2)&&"1".equals(userPower)){ %>
			  <td><s:property value="riState"/>&nbsp; <a  href="riskReply/RiskHandleAction1?reId=<s:property value='reId'/>&dateFrom=<s:property value='dateFrom'/>&dateTo=<s:property value='dateTo'/>&state=<s:property value='state'/>&reIndep=<s:property value='reIndep'/>" onClick="return window.confirm('确实已应对吗?')">应对</a>
			  </td>
			  <td><s:property value="riPerson"/></td>
			  <td><s:property value="ritaketime"/></td>
			  <%}else{ %>
			  <td><s:property value="riState"/></td>
			  <td><s:property value="riPerson"/></td>
			  <td><s:property value="ritaketime"/></td>
			  <%}} %>			   
		  </tr>
		  </s:iterator>
		</tbody>
	</table>
	</div>
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
   var START_ROW=1;//合并开始行号，表示从第三行开始合并
   for(var i=START_ROW;i<index;i++)
   {
   // var text1_0 = mytable.rows[i].cells[0].innerText.replace(" ","");//获取行的第一个单元格firstChild中的内容
    var text1 = mytable.rows[i].cells[0].innerText.replace(" ","");//获取行的第一个单元格firstChild中的内容
    if(text1 == ""){
    	text1=mytable.rows[i].cells[0].innerText;}
    
   // if(text1_0 != text1) alert("text1_0="+text1_0+"------"+"text1="+text1);
     mytable.rows[i].className="child_row_"+num;
      var j=i+1;
     if(j<index)//如果下一行不是最后一行
    { 
    	 //var text2_0 = mytable.rows[j].cells[0].innerText.replace(" ","");//获取行的第一个单元格firstChild中的内容
    	var text2 = mytable.rows[j].cells[0].innerText.replace(" ","");
    	   if(text2 == ""){
    	text2=mytable.rows[j].cells[0].innerText;}
    	// if(text2_0 != text2) alert("text2_0="+text2_0+"------"+"text2="+text2);
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
        for(var i=START_ROW; i<mytable.rows.length; i++){
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
     
function Query(){
    openBox('统计提示',350,150,0,'正在统计中，请等待....','');
	ReplyManageQuery.action="riskReply/RMQueryAction";
	ReplyManageQuery.submit();
}

function openTab(reId,eventname)
{
	if (reId !== null && reId !== undefined && reId !== '') 
	{ 
		var url = "riskInput/REIQReadAction?reId="+reId + "&backFlag=reply";
		window.parent.openTab(url,"风险应对-" + eventname);
	} 
	
};
</script>
    
</html>
