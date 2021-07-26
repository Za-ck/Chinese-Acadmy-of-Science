<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
    <title>风险流转策略信息列表</title> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
	<script language="javascript" src="IconPage/page.js"></script>
	<script language="javascript" src="IconPage/jquery-1.3.2.min.js"></script>
    <script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript"></script> 
  </head>
  
<body style="font-size:12px; margin-top:5px;"  bgcolor="#F8FCFC">
<form name="riskFlow" id="riskFlow" method="post" action="riskFlow/getRiskFlows">
            <div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>风险流转策略信息列表</span></div></div>
  <div class="queryDiv">
<div style="display:;text-align: center">
<ul style="list-style-type:none;width:inherit;margin-top:5px;">

<li style="float:left;margin-left:20px;font-size:14px;">年份：<input name="dateFrom" id="dateFrom" class="Wdate" style="width:90px;" value="<s:property value='dateFrom'/>"  onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy'})"/>
</li>

<li style="float:left;margin-left:20px;font-size:14px;">风险名称：<input height="30" name="riskTypeString" id="riskTypeString" type="text"  onKeyDown="EnterPress()" value="<s:property value='riskTypeString'/>" />
</li>
	
<li style="float:left;margin-left:5px;">
<input type="button" class="right-buttonShort" name="buttonQuery" id="buttonQuery" value="查询" onClick="yearQuery();" />
</li>
</ul>
</div>
</div>

<div id='pageMenu' style="background-image:url(IconPage/images/bg.png)">
<%--<a href='riskFlow/addRisk?updateFlag=add'><img  src='IconPage/images/add.png'/><span>新增</span></a>--%>
<a onClick="checkAdd()"><img  src='IconPage/images/add.png'/><span>新增</span></a>&nbsp;&nbsp;
<a onClick="checkUpdate()" ><img src='IconPage/images/update.png'/><span>修改</span></a>&nbsp;&nbsp;
<a onClick="checkDelete()"><img src='IconPage/images/close.png'/><span>删除</span></a>&nbsp;&nbsp;
<img  src='IconPage/images/line.gif'/></div>

<div id='pagetable'> 
    <table id='mytable' class='tablestyle' width='100%' height='100%' >
    	<tr style="background-image:url(IconPage/images/thbg.png)">				
						<td width="10%" align="center" height="30">
						<img src='IconPage/images/check.png' onClick="allselect()" width="13" height="13" id="allcheck"/><span id="isallselected">全选</span>
						</td>
                		<td width="10%" align="center">序号</td>
                		<td width="30%" align="center">策略编号</td>
                		<td width="50%" align="center">策略名称</td>
						<%int num=0;%>
            		</tr>
					<tbody id="body_riskManage">
            		<s:iterator value="listFlows">
					<tr >
              			<td align="center" height="25"><input type="checkbox" name="idCheck"  value="<s:property value='ids'/>"/></td>
						<td align="center"><%=++num%></td>
              			<td align="center"><s:property value="ids"/></td>
            			<td align="center"><s:property value="name"/></td>
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
        	<input type="text" style="visibility:hidden"  name="actionName" id="actionName" value="<s:property value='actionName'/>"/>
     </form>
<script>

function yearQuery()
{
	//alert("3");
	document.forms[0].action="riskFlow/yearQueryAction?current_pagenum=1";
	document.forms[0].submit();	
}
//alert("与上query()同，只是把enter键作为触发事件");
function EnterPress(e){
	
	var e=e || window.event;
	if(e.keyCode == 13){
		//alert(4);
		document.forms[0].action="riskFlow/yearQueryAction?current_pagenum=1";
		document.forms[0].submit();
	}
}

var flag=0;

//上一页
function prepage(){
		var s=document.getElementById("actionName").value+"?current_pagenum=";
    	var current_page = document.getElementById("current_pagenum");
    	current_page=(current_page.value)*1-1;
    	if(0==current_page)	return;
    	document.forms[0].action=s+current_page+"&pageNum=10";
    	document.forms[0].submit();
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
    	document.forms[0].action=s+current_page+"&pageNum=10";
    	document.forms[0].submit();
}
//首页
function firstpage(){
    	var s=document.getElementById("actionName").value+"?current_pagenum=";
    	document.forms[0].action=s+1+"&pageNum=10";
    	document.forms[0].submit();
}
//尾页
function lastpage(pageno){
		var s=document.getElementById("actionName").value+"?current_pagenum=";
    	document.forms[0].action=s+pageno+"&pageNum=10";
    	document.forms[0].submit();
}
//跳转
function jumppage(maxpage){
		var s=document.getElementById("actionName").value+"?current_pagenum=";	
		var pageno=document.getElementById("current_pagenum").value;
		//alert("pageno="+pageno);
		//alert("maxpage"+maxpage);
		if(maxpage < pageno || pageno < 1)	return;	
    	document.forms[0].action=s+pageno+"&pageNum=10";
    	document.forms[0].submit();
}

//新增
function checkAdd(){
    	document.forms[0].action="riskFlow/addRisk?updateFlag=add";
    	document.forms[0].submit();
}

//修改
function checkUpdate(){ 
	flag=false;
	var count =0;
	var countf=0;
	var sum=document.getElementsByName("idCheck"); 
	for(i = 0; i < sum.length; i++){ 
		if(sum[i].checked == true){ 
			flag = true;
			count++;
			countf=i;
		}
	} 
	if(flag == false){ 
		alert("请选择一条记录修改!");
		return false;
	} 
	if(count > 1){ 
		alert("此功能只限于每次修改一条记录!"); 
		return false;
	} 
	var pageno=document.getElementById("current_pagenum").value;
	window.location.href='riskFlow/updateRisk?ids='+sum[countf].value+'&updateFlag=update&current_pagenum='+pageno;
}
//删除
function checkDelete(){ flag=false;var sum=document.getElementsByName("idCheck"); for(i = 0; i < sum.length; i++){ if(sum[i].checked == true){ flag = true;break;}} if(flag == false){ alert("请至少选择一条删除项!"); return false;} if(confirm("确定删除?")){document.forms[0].action='riskFlow/deleteRiskFlow';document.forms[0].submit(); return true;}}
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
	} 
}
</script>

</body>
</html>
