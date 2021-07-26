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
    
    <title>事件管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	
	<style type="text/css"></style>
	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
	<script language="javascript" src="IconPage/page.js"></script>
	<script language="javascript" src="IconPage/jquery-1.3.2.min.js"></script>
	<script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
	<script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript"></script> 
</head>

<form id="EventManage" name="EventManage" method="post" action="riskInput/AllStateEventDeleteAction" >
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>事件管理</span></div></div>

<div class="queryDiv">
        <div style="margin-left: 10px">
        <table style="margin-top:5px;">
        	<tr>
        		<td style="padding:5px 20px;">录入部门名称：<s:select name="reIndep" theme="simple" list="alldepList" listValue="depName" listKey="depId" class="text" style="width:240px" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select></td>
        		<td style="padding:5px 20px;">责任部门名称：<s:select name="riskDep" theme="simple" list="riskdepList" listValue="depName" listKey="depName" class="text" style="width:154px" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select></td>
        		<td style="padding:5px 20px;">录入日期：<input name="dateFrom" id="dateFrom" class="Wdate" style="width:140px;" value="<s:property value='dateFrom'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/>
        			&nbsp;&nbsp;至：<input name="dateTo" id="dateTo" class="Wdate" style="width:140px;" value="<s:property value='dateTo'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/>
        		</td>
        	</tr>
        	<tr>
        		<td style="padding:5px 20px;">
        			风险事件编号(名称)：<input name="reventStr" id="reventStr" style="width:180px;" value="<s:property value='reventStr'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" />
        		</td>
        		<td style="padding:5px 20px;">
        			审核状态：<s:select name="stateId" theme="simple" list="#{'0':'--请选择--','1':'已发布','2':'未修改','3':'未提交','4':'未通过','5':'已提交','6':'等待审核中...'}"  style="width:154px;" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>
        		</td>
        	</tr>
        </table>
        </div>
        <div style="width:100%;float: left;height:30px;text-align: center;margin-top:10px;">
        <input type="button" class="right-buttonShort" name="buttonQuery" id="buttonQuery" value="查询" onClick="query();" />					     
		<input type="button" class="right-buttonShort" name="buttonReset" id="buttonReset" value="清空" onClick="reSetmess();"/>
		
		</div>
		
		</div>
<div id='pageMenu' style="background-image:url(IconPage/images/bg.png);">
<a onClick="checkDeleteTask()"><img src='IconPage/images/close.png'/><span>删除待办</span></a>&nbsp;&nbsp;
<a onClick="checkDelete()"><img src='IconPage/images/close.png'/><span>删除</span></a>&nbsp;&nbsp;
<img  src='IconPage/images/line.gif'/>
</div>
<div id='pagetable'> 
    <table id='mytable' class='tablestyle' width='100%' height='100%' >
    	<tr style="background-image:url(IconPage/images/thbg.png)">				
						<td width="5%" align="center" height="30">
						<!--<a href="###" onClick="funcAllSel();return false;"><span>全选</span></a>-<a href="###" onClick="funcInvertSel();return false;"><span>反选</span></a>-->
						<img src='IconPage/images/check.png' onClick="allselect()" width="13" height="13" id="allcheck"/><span id="isallselected">全选</span>
						</td>
                		<td width="2%" align="center">序号</td>
						<td width="12%" align="center">风险事件编号<img src='IconPage/images/up.png' style='cursor:pointer;' id="ordersid.reId" onClick="changeUpDown('id.reId')" /></td>
                		<td width="12%" align="center">录入部门名称<img src='IconPage/images/up.png' style='cursor:pointer;' id="ordersdepName" onClick="changeUpDown('depName')" /></td>
                		<td width="12%" align="center">管理责任部门</td>
                		<td width="6%" align="center">审核状态</td>
                		<td width="8%" align="center">填写人<img src='IconPage/images/up.png' style='cursor:pointer;' id="ordersreCreator" onClick="changeUpDown('reCreator')" /></td>
                		<td width="10%" align="center">录入日期<img src='IconPage/images/up.png' style='cursor:pointer;' id="ordersreDate" onClick="changeUpDown('reDate')" /></td>
                		<td width="8%" align="center">修改人<img src='IconPage/images/up.png' style='cursor:pointer;' id="ordersreModifier" onClick="changeUpDown('reModifier')" /></td>
						<td width="10%" align="center">修改日期<img src='IconPage/images/up.png' style='cursor:pointer;' id="ordersreModifydate" onClick="changeUpDown('reModifydate')" /></td>
						<td width="5%" align="center">是否考核</td>
						<%int num=0;%>
            		</tr>
					
					<tbody id="body_EventManage">
            		<s:iterator value="allreList">
            		<tr title="双击查看风险事件信息" align="center" onDblClick="openTab('<s:property value='id.reId'/>','<s:property value='reEventname'/>')">
              			<td align="center" height="25"><input type="checkbox" name="idCheck"  value="<s:property value="id.reId"/>"/></td>
						<td align="center"><%=++num%></td>		
              			<td align="center"><s:property value="id.reId"/></td>
            			<td align="center"><s:property value="depName"/></td>
            			<td align="center">
            				
            				<s:if test='riInchargedep=="本部门"'>
            					<s:property value="depName"/>
            				</s:if>
            				<s:else>
            					<s:property value="riInchargedep"/>
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
            			<td align="center"><s:property value="reCreator"/></td>
            			<td align="center"><s:property value="reDate"/></td>
            			<td align="center"><s:property value="reModifier"/></td>
						<td align="center"><s:property value="reModifydate"/></td>
						<td align="center">
							<s:if test='reCheckflag==0'>
            					未考核
            				</s:if>
            				<s:else>
            					已考核
            				</s:else>
						</td>
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
			<input type="hidden" name="orderbys" id="orderbys" value="<s:property value="orderbys"/>">
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
	if((operation=="delete")&&(result=="success")) alert("删除成功!");
	else if(operation=="delete"&&result=="fail") alert("删除失败!");
	else if((operation=="deleteTask")&&(result=="success")) alert("删除待办成功!");
	else if(operation=="deleteTask"&&result=="fail") alert("删除待办失败!");
}
var flag=0;

function query()
{
	EventManage.action="riskInput/AllEventIOQueryAction?current_pagenum=1&pageNum=10";
	document.getElementById("orderbys").value = "";
	EventManage.submit();
}
//alert("与上query()同，只是把enter键作为触发事件");
function EnterPress(e){
	var e=e || window.event;
	if(e.keyCode == 13){
		EventManage.action="riskInput/AllEventIOQueryAction?current_pagenum=1&pageNum=10";
		document.getElementById("orderbys").value = "";
		EventManage.submit();
	};
}

//上一页
function prepage(){
		var s=document.getElementById("actionName").value+"?current_pagenum=";
    	var current_page = document.getElementById("current_pagenum");
    	current_page=(current_page.value)*1-1;
    	if(0==current_page)	return;
    	EventManage.action=s+current_page+"&pageNum=10";
    	EventManage.submit();
}
//下一页
function nextpage(maxpage){
		var s=document.getElementById("actionName").value+"?current_pagenum=";
    	var current_page = document.getElementById("current_pagenum");
		if(maxpage == current_page)	return;	
		current_page=(current_page.value)*1+1;
		//alert("maxpage="+maxpage);  
		//alert("current_page="+current_page);   
		if(maxpage < current_page)	return;	
    	EventManage.action=s+current_page+"&pageNum=10";
    	EventManage.submit();
}
//首页
function firstpage(){
    	var s=document.getElementById("actionName").value+"?current_pagenum=";
    	EventManage.action=s+1+"&pageNum=10";
    	EventManage.submit();
}
//尾页
function lastpage(pageno){
		var s=document.getElementById("actionName").value+"?current_pagenum=";
    	EventManage.action=s+pageno+"&pageNum=10";
    	EventManage.submit();
}
//跳转
function jumppage(maxpage){
		var s=document.getElementById("actionName").value+"?current_pagenum=";	
		var pageno=document.getElementById("current_pagenum").value;
		//alert("pageno="+pageno);
		//alert("maxpage"+maxpage);
		if(maxpage < pageno || pageno < 1)	return;	
    	EventManage.action=s+pageno+"&pageNum=10";
    	EventManage.submit();
}
//删除待办
function checkDeleteTask()
{ 
	var pageno=document.getElementById("current_pagenum").value;
	flag=false;
	var sum=document.getElementsByName("idCheck"); 
	for(i = 0; i < sum.length; i++)
	{ 
		if(sum[i].checked == true)
		{ 
			flag = true;break;
		};
	} 
	if(flag == false)
	{ 
		alert("请至少选择一条删除项!"); 
		return false;
	} 
	if(confirm("删除选中风险事件在待办平台相应的待办，确定?"))
	{
		document.forms[0].action="riskInput/AllStateEventDeleteTaskAction?current_pagenum="+pageno+"&pageNum=10";
		document.forms[0].submit(); 
		return true;
	};
}

//删除
function checkDelete()
{ 
	var pageno=document.getElementById("current_pagenum").value;
	flag=false;
	var sum=document.getElementsByName("idCheck"); 
	for(i = 0; i < sum.length; i++){ if(sum[i].checked == true)
	{ 
		flag = true;
		break;
		};
	} 
	if(flag == false)
	{ 
		alert("请至少选择一条删除项!"); 
		return false;
	} 
	if(confirm("该功能可以删除已发布风险事件,确定删除?"))
	{
		document.forms[0].action="riskInput/AllStateEventDeleteAction?current_pagenum="+pageno + "&pageNum=10";
		document.forms[0].submit(); 
		return true;
	};
}
//对字段排序 
var orderbycmd=0;
var ordersorts=""; 
if("IconPage/images/dn.png"==document.getElementById("updownflag").value){
	document.getElementById(document.getElementById("updownid").value).src="IconPage/images/dn.png";
}
function changeUpDown(sorts)
{
    //alert(sorts);
	if(orderbycmd==1) return;
	orderbycmd=1; 
	var path=document.getElementById('orders'+sorts); 
	document.getElementById("updownid").value=path.id;
	if(path.src.indexOf('up.png')!=-1) 
	{ 
		path.src="IconPage/images/dn.png";
		document.getElementById("updownflag").value="IconPage/images/dn.png";
 		ordersorts=sorts+' desc'; 
 		document.getElementById("orderbys").value=ordersorts;
 		var s=document.getElementById("actionName").value+"?current_pagenum=1&pageNum=10";
		document.forms[0].action=s;
		document.forms[0].submit();
    }
	else 
	{
		path.src="IconPage/images/up.png"; 
		document.getElementById("updownflag").value="IconPage/images/up.png";
		ordersorts=sorts+' asc'; 
		document.getElementById("orderbys").value=ordersorts;
		var s=document.getElementById("actionName").value+"?current_pagenum=1&pageNum=10";
		EventManage.action=s;
		EventManage.submit();
 	};
}
/*//全选
function funcAllSel(){
	var sum = document.getElementsByName("idCheck");
	for(var i=0;i<sum.length;i++)
	{    
		sum[i].checked = true;
	}
}

//反选
function funcInvertSel(){
   var sum = document.getElementsByName("idCheck");
	for(var i=0;i<sum.length;i++)
	{    
		if(sum[i].checked ==true) sum[i].checked=false;
		else
			sum[i].checked =true;
	}
}*/
//全选,取消
function allselect(){ 
	if(flag==0){ 
		var sum = document.getElementsByName("idCheck"); 
		for(i = 0; i < sum.length; i++){ sum[i].checked=true; 
	} 
	document.getElementById("allcheck").src="IconPage/images/checked.png";
	document.getElementById("isallselected").innerText="取消";
	flag=1;
	}else{ 
		var sum = document.getElementsByName("idCheck"); 
		for(i = 0; i < sum.length; i++){
			sum[i].checked=false; 
		}
		document.getElementById("allcheck").src="IconPage/images/check.png";
		document.getElementById("isallselected").innerText="全选";
		flag=0;
	};
}
function reSetmess()
{
    document.getElementById('dateFrom').value="";
	document.getElementById('dateTo').value="";
	document.getElementById('reIndep').value="none1";
	document.getElementById('riskDep').value="--请选择--";
	document.getElementById('stateId').value="0";
	document.getElementById('reventStr').value="";
};

function openTab(reId,eventname)
{
	var url = "riskInput/REIQReadAction?reId=" + reId;
	window.parent.openTab(url,"查看详情-" + eventname);
};
		
</script>

</body>
</html>
