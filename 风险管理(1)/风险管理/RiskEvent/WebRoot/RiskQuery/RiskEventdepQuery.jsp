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
    
    <title>风险事件查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
	<script language="javascript" src="IconPage/page.js"></script>
	<script language="javascript" src="IconPage/jquery-1.3.2.min.js"></script>
	<script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
	<script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript"></script> 
	<script language=javascript src="/RiskEvent/js/windows.js"></script>
</head>

<body style="font-size:12px; margin-top:5px;">

<form id="riskEventdepQuery" name="riskEventdepQuery" method="post" action="riskQuery/RiskEventdepQueryAction" onSubmit="return doValidate('riskEventQuery')">
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>风险事件查询</span></div></div>

<div class="queryDiv">
        <div style="display:;text-align: center">
        <ul style="list-style-type:none;width:inherit;margin-top:5px;">
        
        <li style="float:left;margin-left:5px;font-size:14px;"> 录入时间：<input name="dateFrom" id="dateFrom" class="Wdate" style="width:90px;" value="<s:property value='dateFrom'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/>
</li>
        <li style="float:left;margin-left:5px;font-size:14px;"> 至：<input name="dateTo" id="dateTo" class="Wdate" style="width:90px;"  value="<s:property value='dateTo'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/>
</li>   
		<li style="float:left;margin-left: 20px;font-size: 14px;">风险事件编号(名称)：
        
        	<input name="reventStr" id="reventStr" style="width:180px;" value="<s:property value='reventStr'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" />
        
        </li>
        <li style="float:left;margin-left:10px;font-size:14px;"> 审核状态：
        	<s:select name="stateId" theme="simple" list="#{'0':'--请选择--','1':'已发布','2':'未修改','3':'未提交','4':'未通过','5':'已提交','6':'等待审核中...'}"  style="width:154px" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>
		</li>
        </ul>
        
        <div style="width:100%;float: left;height:30px;text-align: center;margin-top:10px;">
        <input type="button" class="right-buttonShort" name="buttonQuery" id="buttonQuery" value="查询" onClick="query();" />					     
		<input type="button" class="right-buttonShort" name="buttonReset" id="buttonReset" value="清空" onClick="reSetmess();"/>
		<input type="button" class="querybutton" name="buttonExport" id="buttonExport" value="导出事件" onClick="exportInfo();" /></div>
		
		</div>
         </div>
<div id='pageMenu' style="background-image:url(IconPage/images/bg.png);">
<a onClick="checkRecall()"><img src='IconPage/images/close.png'/><span>撤回</span></a>&nbsp;&nbsp;
<img  src='IconPage/images/line.gif'/>
</div>
<div id='pagetable'> 
    <table id='mytable' class='tablestyle' width='100%' height='100%' >
    	<tr style="background-image:url(IconPage/images/thbg.png)">
						<td width="5%" align="center" height="30">
						<img src='IconPage/images/check.png' onClick="allselect()" width="13" height="13" id="allcheck"/><span id="isallselected">全选</span>
						</td>				
						<td width="4%" align="center" height="30">序号</td>
                		<td width="10%" align="center">风险事件编号<img src='IconPage/images/up.png' style='cursor:pointer;' id="ordersid.reId" onClick="changeUpDown('id.reId')" /></td>
                		<td align="center">风险事件名称<img src='IconPage/images/up.png' style='cursor:pointer;' id="ordersreEventname" onClick="changeUpDown('reEventname')" /></td>
                		<td width="10%" align="center">风险名称<img src='IconPage/images/up.png' style='cursor:pointer;' id="ordersriskName" onClick="changeUpDown('riskName')" /></td>
                		<td width="10%" align="center">录入部门<img src='IconPage/images/up.png' style='cursor:pointer;' id="ordersdepName" onClick="changeUpDown('depName')" /></td>
                		<td width="10%" align="center">管理责任部门</td>
                		<td width="10%" align="center">审核状态<img src='IconPage/images/up.png' style='cursor:pointer;' id="ordersreState" onClick="changeUpDown('reState')" /></td>
                		<td width="10%" align="center">录入时间<img src='IconPage/images/up.png' style='cursor:pointer;' id="ordersreDate" onClick="changeUpDown('reDate')" /></td>
                		<td width="9%" align="center">采取措施的时间计划<img src='IconPage/images/up.png' style='cursor:pointer;' id="ordersrmPlandate" onClick="changeUpDown('rmPlandate')" /></td>
		        		<td width="11%" align="center">综合评定<img src='IconPage/images/up.png' style='cursor:pointer;' id="ordersriAllvalue" onClick="changeUpDown('riAllvalue')" /></td>
						<!-- <td width="12%" align="center">业务流程环节</td> -->
						<%int num=0;%>
            		</tr>
					
					<tbody id="body_RiskEventInputQuery">
            		<s:iterator value="redepList">
            		<tr title="双击查看风险事件信息" align="center" onDblClick="handle('<s:property value='id.reId'/>','<s:property value='reEventname'/>')">
						<td align="center" height="25"><input type="checkbox" name="idCheck"  value="<s:property value='id.reId'/>"/></td>
						<td height="25" align="center"><%=++num%></td>
            			<td align="center"><s:property value="id.reId"/></td>
            			<td align="center"><s:property value="reEventname"/></td>
            			<td align="center"><s:property value="riskName"/></td>
            			<td align="center"><s:property value="depName"/></td>
            			<td align="center">
            				<s:if test='reinchargedep=="本部门"'>
            					<s:property value="depName"/>
            				</s:if>
            				<s:else>
            					<s:property value="reinchargedep"/>
            				</s:else>
            			</td>
            			
            			<td align="center"><a href="riskFlow/RiskFlowChart?riskId=<s:property value='riskId'/>&resId=<s:property value="id.reId"/>&reState=<s:property value='reState'/>" target="_blank">
                      <s:if test='reState=="*"'>
                                                              已发布</s:if>
                         <s:elseif test='reState=="0"'>
                         	<s:if test='reAct=="0"'>未修改</s:if>
                         	<s:else>未提交</s:else>
                         </s:elseif>
            			<s:elseif test='reAct=="0"'>   	
            			未通过
            			</s:elseif>      
            			<s:else>
            			<s:if test='reState=="1"'>
                                                              已提交</s:if>
                        <s:else>
            			等待审核中...
            			</s:else>
            			</s:else>
            			</a>
            			</td>
            			
            			<td align="center"><s:property value="reDate"/></td>
						<td align="center"><s:property value="rmPlandate"/></td>
            			<td align="center"><s:property value="riAllvalue"/></td>
            			<!-- <td align="center">
                    		<a style="cursor:pointer" onClick="openBox('涉及的流程文件如下',550,150,1,'','riskQuery/REQSelectedFileAction?reId=<s:property value='id.reId'/>');"><span title="单击查看" style="text-decoration:underline">涉及的流程文件</span></a> 
                    	</td>
                    	 -->
					</tr>
            		</s:iterator>
            		</tbody>
				
        		</table>
        		</div>
     			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        			<tr>
          				<td height="6"><img src="/RiskEvent/images/main/spacer.gif" width="1" height="1" /></td>
        			</tr>
				</table>
				<div id='pageDIV' style="background-image:url(IconPage/images/bg.png)">
					<a onClick="firstpage()"><img border='0' title='首页' src='IconPage/images/first.gif'/></a>
                    <a onClick="prepage()"><img border='0'title='上一页' src='IconPage/images/prev.gif'/></a>
                    <img border='0' src='IconPage/images/line.gif'/><span>&nbsp;&nbsp;&nbsp;&nbsp;第</span>
                    <input id='current_pagenum' style='width:45px' type='text' value="<s:property value="current_pagenum"/>"/> <span>页&nbsp;&nbsp;</span>
                    <img border='0' src='IconPage/images/hl.png'/><span>共
                  <%
                     int pc=Integer.valueOf(request.getSession().getAttribute("pagecount").toString());
                     int pg=0;
                     if(pc%10==0){ pg=pc/10;out.print(pg);}
                     else {pg=(pc/10)+1;out.print(pg);}
                  %>页&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;<img border='0' src='IconPage/images/line.gif'/>
                  <a onClick="nextpage(<%=pg%>)"><img border='0' title='下一页' src='IconPage/images/next.gif'/></a>&nbsp;&nbsp;
				  <a onClick="lastpage(<%=pg%>)"><img border='0' title='尾页' src='IconPage/images/last.gif'/></a>
				  &nbsp;&nbsp;
                 <img border='0' src='IconPage/images/line.gif'/>&nbsp;&nbsp;&nbsp;&nbsp;
				 <a onClick="jumppage(<%=pg%>)"><img border='0' style='cursor:pointer;' title='跳转' src='IconPage/images/load.png'/></a>
				 &nbsp;&nbsp;&nbsp;&nbsp;<img border='0' src='IconPage/images/line.gif'/>
&nbsp;&nbsp;&nbsp;&nbsp;<span>共有&nbsp;&nbsp;<%=request.getSession().getAttribute("pagecount")%>&nbsp;&nbsp;条记录,当前第&nbsp;&nbsp;<%=request.getSession().getAttribute("current_pagenum")%>/<%=pg%>页</span>
</div>          
        	<input type="text" style="visibility:hidden"  name="actionName" id="actionName" value="<s:property value="actionName"/>"/>
			<input type="text" style="visibility:hidden"  name="updownflag" id="updownflag" value="<s:property value="updownflag"/>"/>
			<input type="text" style="visibility:hidden"  name="updownid" id="updownid" value="<s:property value="updownid"/>"/>
     </form>
<script>
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
	if((operation=="recall")&&(result=="success")) alert("撤回成功!");
	else if(operation=="recall"&&result=="fail") alert("撤回失败!");
}
var flag=0;
var orderbycmd=0;
var ordersorts=""; 
//撤回
function checkRecall()
{ 
	flag=false;
	var count =0;var countf=0;var state=0;
	var sum=document.getElementsByName("idCheck"); 

	for(i = 0; i < sum.length; i++)
	{ 
		if(sum[i].checked == true)
  		{ 
  			flag = true;
  			count++;
  			countf=i;
  		}
	}  
  	if(flag == false)
  	{ 
  		alert("请选择一条记录撤回!"); 
  		return false;
  	} 
  	if(count > 1)
  	{ 
  		alert("此功能只限于每次撤回一条记录!"); 
  		return false;
  	} 
	
	if(confirm("确定撤回?"))
	{
		//document.forms[0].action='riskFlow/RERecallAction?current_pagenum=1&pageNum=10&recallPage=DQ&id='+sum[countf].value;
		//document.forms[0].submit(); 
		//return true;
		var params="reId="+ sum[countf].value +"&tmpnum="+new Date().getTime();
		$.ajax({
              url: "riskFlow/RERevocation",
              type: "post",
              dateType: "json",
              data: params,
              contentType: "application/x-www-form-urlencoded",			//很重要
              success: function (data) 
              {
              		if(data == "fail")
              		{
              			alert("不可撤回");
              		}
              		else
              		{
              			alert("撤回成功");
              			riskEventdepQuery.submit();
              		}
              },
              error: function () 
              {
              		alert("操作失败");
          	  }
      	});
	};
}


function showCurrentYearInfo(){
	riskEventdepQuery.action="riskQuery/REQCurrentYearAction";
	riskEventdepQuery.submit();
}

function showAllInfo(){
	riskEventdepQuery.action="riskQuery/RiskEventQueryAction";
	riskEventdepQuery.submit();
}
function query()
{
	riskEventdepQuery.action="riskQuery/REadQueryAction?current_pagenum=1"; 
	riskEventdepQuery.submit();
	//document.getElementById('dateFlag').value="";
}
//alert("与上query()同，只是把enter键作为触发事件");
function EnterPress(e){
	var e=e || window.event;
	if(e.keyCode == 13){
		
		query();
		//document.getElementById('dateFlag').value="";
	};
}
function list(){
	var objId="subtree";
	var object=document.getElementById(objId);
	
	if(object.style.display=="none"){
		document.getElementById(objId).style.display="";
		document.getElementById('buttonMore').value="更多 ▼";
		document.getElementById('dateFlag').value="detail";
		document.getElementById('calendar1').style.visibility="visible";
	}
	else{
		document.getElementById(objId).style.display="none";
		document.getElementById('buttonMore').value="更多 ►";
		document.getElementById('dateFlag').value="";
		document.getElementById('calendar1').style.visibility="hidden";
	}
}
function order(){
	if(orderClick==0){
		document.getElementById('buttonOrder').value="倒序显示";
		orderClick=1;
	}
	else{
		document.getElementById('buttonOrder').value="顺序显示";
		orderClick=0;
	}
}


function reSetmess(){
	//var calendar=java.util.Calendar.getInstance();
	document.getElementById('dateFrom').value="";
	document.getElementById('dateTo').value="";
	document.getElementById('reventStr').value="";
	document.getElementById('stateId').value="0";
}
//上一页
function prepage(){
		var s=document.getElementById("actionName").value+"?current_pagenum=";
    	var current_page = document.getElementById("current_pagenum");
    	current_page=(current_page.value)*1-1;
    	if(0==current_page)	return;
    	riskEventdepQuery.action=s+current_page+"&pageNum=10";
    	riskEventdepQuery.submit();
}
//下一页
function nextpage(maxpage){
		var s=document.getElementById("actionName").value+"?current_pagenum=";
    	var current_page = document.getElementById("current_pagenum");
		if(maxpage == current_page)	return;	
		current_page=(current_page.value)*1+1;  
		if(maxpage < current_page)	return;	
		
    	riskEventdepQuery.action=s+current_page+"&pageNum=10";
    	riskEventdepQuery.submit();
}
//首页
function firstpage(){
    	var s=document.getElementById("actionName").value+"?current_pagenum=";
    	riskEventdepQuery.action=s+1+"&pageNum=10";
    	riskEventdepQuery.submit();
}
//尾页
function lastpage(pageno){
		var s=document.getElementById("actionName").value+"?current_pagenum=";
    	riskEventdepQuery.action=s+pageno+"&pageNum=10";
    	riskEventdepQuery.submit();
}
//跳转
function jumppage(maxpage){
		var s=document.getElementById("actionName").value+"?current_pagenum=";	
		var pageno=document.getElementById("current_pagenum").value;
		//alert("pageno="+pageno);
		//alert("maxpage"+maxpage);
		if(maxpage < pageno || pageno < 1)	return;	
    	riskEventdepQuery.action=s+pageno+"&pageNum=10";
    	riskEventdepQuery.submit();
}
//对字段排序 
if("IconPage/images/dn.png"==document.getElementById("updownflag").value){
	document.getElementById(document.getElementById("updownid").value).src="IconPage/images/dn.png";
}
function changeUpDown(sorts){
	if(orderbycmd==1) return;
	orderbycmd=1; 
	var path=document.getElementById('orders'+sorts); 
	document.getElementById("updownid").value=path.id;
	if(path.src.indexOf('up.png')!=-1) { 
		path.src="IconPage/images/dn.png";
		document.getElementById("updownflag").value="IconPage/images/dn.png";
 		ordersorts=sorts+' desc'; 
 		var s=document.getElementById("actionName").value+"?current_pagenum=1&pageNum=10&orderbys=";
		document.forms[0].action=s+ordersorts;
		document.forms[0].submit();
    }
	else {
		path.src="IconPage/images/up.png"; 
		document.getElementById("updownflag").value="IconPage/images/up.png";
		ordersorts=sorts+' asc'; 
		var s=document.getElementById("actionName").value+"?current_pagenum=1&pageNum=10&orderbys=";
		riskEventdepQuery.action=s+ordersorts;
		riskEventdepQuery.submit();
 	};
}

function exportInfo()
{
document.getElementById("buttonExport").disabled=true;
riskEventdepQuery.action="riskQuery/eventdepQueryExportAction";
riskEventdepQuery.submit();
};

function handle(reId,eventname) 
{
	var url = "riskInput/REIQReadAction?reId=" + reId;
	window.parent.openTab(url,"查看详情-" + eventname);
};

</script>

</body>
</html>
