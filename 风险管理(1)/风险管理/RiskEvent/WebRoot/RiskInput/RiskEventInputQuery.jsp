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
    
    <title>风险事件库录入情况查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
	<script language="javascript" src="IconPage/page.js"></script>
	<script language="javascript" src="IconPage/jquery-1.3.2.min.js"></script>
	<script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
	<script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript"></script> 
</head>

<form id="riskEventInputQuery" name="riskEventInputQuery" method="post" action="riskInput/RiskEventInputQueryAction" onSubmit="return doValidate('riskEventInputQuery')">
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>录入情况查询</span></div></div>

<div class="queryDiv">
        <div style="display:;text-align: center">
        <ul style="list-style-type:none;width:inherit;margin-top:5px; ">

<!--    	<li style="float:left;margin-left:10px;font-size:14px;"> 风险名称：<s:select name="riskId" theme="simple" list="allriskList" listValue="riskName" listKey="riskId" class="text" style="width:154px" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>-->
<!--</li>-->
        <li style="float:left;margin-left:10px;font-size:14px;"> 录入日期：<input name="dateFrom" id="dateFrom" class="Wdate" style="width:120px;" value="<s:property value='dateFrom'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/>
</li>
        <li style="float:left;margin-left:10px;font-size:14px;"> 至：<input name="dateTo" id="dateTo" class="Wdate" style="width:120px;"  value="<s:property value='dateTo'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/>
</li>   
        <li style="float:left;margin-left: 20px;font-size: 14px;">风险事件编号(名称)：
        
        	<input name="reIdStr" id="reIdStr" style="width:180px;" value="<s:property value='reIdStr'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" />
        
        </li>
        </ul>
        <div style="width:100%;float: left;height:30px;text-align: center;margin-top:10px;">
        <input type="button" class="right-buttonShort" name="buttonQuery" id="buttonQuery" value="查询" onClick="query();" />					     
		<input type="button" class="right-buttonShort" name="buttonReset" id="buttonReset" value="清空" onClick="reSetmess();"/>
		<input type="button" class="querybutton" name="buttonAll" id="buttonAll" value="全部事件" onClick="showAllInfo();"/>
		</div>
		</div>
		
		</div>
<div id='pageMenu' style="background-image:url(IconPage/images/bg.png);">

<a onClick="checkAdd()"><img  src='IconPage/images/add.png'/><span>新增</span></a>&nbsp;&nbsp;
<a onClick="checkUpdate()" ><img src='IconPage/images/update.png'/><span>修改</span></a>&nbsp;&nbsp;
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
                		<td width="5%" align="center">序号</td>
						<td width="13%" align="center">风险事件编号<img src='IconPage/images/up.png' style='cursor:pointer;' id="ordersreId" onClick="changeUpDown('reId')" /></td>
                		<td align="center">风险事件名称<img src='IconPage/images/up.png' style='cursor:pointer;' id="ordersreEventname" onClick="changeUpDown('reEventname')" /></td>
                		<td width="13%" align="center">录入部门名称<img src='IconPage/images/up.png' style='cursor:pointer;' id="ordersdepName" onClick="changeUpDown('depName')" /></td>
                		<td width="12%" align="center">审核状态</td>
                		<td width="8%" align="center">填写人<img src='IconPage/images/up.png' style='cursor:pointer;' id="ordersreCreator" onClick="changeUpDown('reCreator')" /></td>
                		<td width="13%" align="center">录入日期<img src='IconPage/images/up.png' style='cursor:pointer;' id="ordersreDate" onClick="changeUpDown('reDate')" /></td>
                		<td width="8%" align="center">修改人<img src='IconPage/images/up.png' style='cursor:pointer;' id="ordersreModifier" onClick="changeUpDown('reModifier')" /></td>
						<td width="13%" align="center">修改日期<img src='IconPage/images/up.png' style='cursor:pointer;' id="ordersreModifydate" onClick="changeUpDown('reModifydate')" /></td>
						<%int num=0;%>
            		</tr>
					
					<tbody id="body_RiskEventInputQuery">
            		<s:iterator value="reList">
            		<tr title="双击查看风险事件信息" align="center" onDblClick="handle('<s:property value='reId'/>','<s:property value='reEventname'/>')">
              			<td align="center" height="25"><input type="checkbox" name="idCheck"  value="<s:property value="reId"/>*<s:property value="reCreator"/>*<s:property value="reState"/>"/></td>
						<td align="center"><%=++num%></td>		
              			<td align="center"><s:property value="reId"/></td>
              			<td align="center"><s:property value="reEventname"/></td>
            			<td align="center"><s:property value="depName"/></td>
            			<td align="center"><a href="riskFlow/RiskFlowChart?riskId=<s:property value='reRiskId'/>&resId=<s:property value="reId"/>&reState=<s:property value='reState'/>" target="_blank">
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
	if((operation=="addupdate")&&(result=="notallowed")) alert("风险已提交，不允许修改!");
	else if(operation=="delete"&&result=="success") alert("删除成功!");
	else if(operation=="delete"&&result=="fail") alert("删除失败!");
	else if(operation=="submitto"&&result=="success") 
	{
		alert("提交成功!");
		window.parent.unprocessed();
	}
	else if(operation=="submitto"&&result=="fail") 
	{
		alert("提交失败!");
		window.parent.unprocessed();
	}
	else if(operation=="delete"&&result=="notallowed") alert("风险已提交，不允许删除!");
	else if(operation=="delete"&&result=="notallowedother") alert("不是录入人，不允许删除!");
	
}
var flag=0;
function showAllInfo(){
	//Alert("显示全部事件");
	riskEventInputQuery.action="riskInput/RiskEventInputQueryAction?current_pagenum=1&pageNum=10";
	riskEventInputQuery.submit();
}

function query()
{
	riskEventInputQuery.action="riskInput/REIQQueryAction?current_pagenum=1&pageNum=10";
	riskEventInputQuery.submit();
}
//alert("与上query()同，只是把enter键作为触发事件");
function EnterPress(e){
	var e=e || window.event;
	if(e.keyCode == 13){
		riskEventInputQuery.action="riskInput/REIQQueryAction?current_pagenum=1";
		riskEventInputQuery.submit();
	};
}

//上一页
function prepage(){
		var s=document.getElementById("actionName").value+"?current_pagenum=";
    	var current_page = document.getElementById("current_pagenum");
    	current_page=(current_page.value)*1-1;
    	if(0==current_page)	return;
    	riskEventInputQuery.action=s+current_page+"&pageNum=10";
    	riskEventInputQuery.submit();
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
    	riskEventInputQuery.action=s+current_page+"&pageNum=10";
    	riskEventInputQuery.submit();
}
//首页
function firstpage(){
    	var s=document.getElementById("actionName").value+"?current_pagenum=";
    	riskEventInputQuery.action=s+1+"&pageNum=10";
    	riskEventInputQuery.submit();
}
//尾页
function lastpage(pageno){
		var s=document.getElementById("actionName").value+"?current_pagenum=";
    	riskEventInputQuery.action=s+pageno+"&pageNum=10";
    	riskEventInputQuery.submit();
}
//跳转
function jumppage(maxpage){
		var s=document.getElementById("actionName").value+"?current_pagenum=";	
		var pageno=document.getElementById("current_pagenum").value;
		//alert("pageno="+pageno);
		//alert("maxpage"+maxpage);
		if(maxpage < pageno || pageno < 1)	return;	
    	riskEventInputQuery.action=s+pageno+"&pageNum=10";
    	riskEventInputQuery.submit();
}
function checkAdd()
{
	riskEventInputQuery.action="riskInput/GetDepMessageAction";
	riskEventInputQuery.submit();
}
//修改
function checkUpdate()
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
  		};
	}  
  	if(flag == false)
  	{ 
  		alert("请选择一条记录修改!"); 
  		return false;
  	} 
  	if(count > 1)
  	{ 
  		alert("此功能只限于每次修改一条记录!"); 
  		return false;
  	} 
  	window.location.href='riskInput/getRiskEventAction?id='+sum[countf].value+'&updateFlag=update';
}
//删除
function checkDelete()
{ 
	flag=false;
	var sum=document.getElementsByName("idCheck"); 
	for(i = 0; i < sum.length; i++)
	{ 
		if(sum[i].checked == true)
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
	if(confirm("确定删除?"))
	{
		var current_page = document.getElementById("current_pagenum");
		document.forms[0].action="riskInput/REIQMultiDelAction?current_pagenum="+current_page+"&pageNum=10";
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
		riskEventInputQuery.action=s+ordersorts;
		riskEventInputQuery.submit();
 	};
}
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
	document.getElementById('reIdStr').value="";
};

function handle(reId,eventname) 
{
	var url = "riskInput/REIQReadAction?reId=" + reId;
	window.parent.openTab(url,"查看详情-" + eventname);
};
		
</script>

</body>
</html>
