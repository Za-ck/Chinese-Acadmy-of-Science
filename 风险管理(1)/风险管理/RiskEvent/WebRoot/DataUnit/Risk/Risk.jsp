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
    
    <title>风险信息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<style type="text/css">
 		a img {
			border:0; 
		}
	</style>
	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
	<script language="javascript" src="IconPage/page.js"></script>
	<script language="javascript" src="IconPage/jquery-1.3.2.min.js"></script>
    <script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript" defer="defer"></script> 
</head>
<body style="font-size:12px; margin-top:5px;" >
<form name="risk" id="risk" method="post" action="dataUnit/RiskAction">
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>风险信息</span></div></div>
<div class="queryDiv">
        <span>年份：</span>
        <input name="dateFrom" id="dateFrom" class="Wdate" style="width:90px;" value="<s:property value='dateFrom'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy'})"/>
        &nbsp;&nbsp;
         <%--<span>一级风险：</span>
		<input height="30" name="riskTypeString" id="riskTypeString" type="text" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" value="<s:property value='riskTypeString'/>" />--%>
		<span>二级风险：</span>
		<input height="30" name="riskTypeString" id="riskTypeString" type="text" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" value="<s:property value='riskTypeString'/>" />
		<img height="25" align="middle" src="images/mag.png" onClick="search()"/>
		 <a  href="dataUnit/RiskExcelAction"><img height="25" align="middle" src="images/Excel.png" /><span>导出</span></a>	
		</div>
<div id='pageMenu' style="background-image:url(IconPage/images/bg.png)">
<a href='dataUnit/RisAddPreAction?updateFlag=add'><img  src='IconPage/images/add.png'/><span>新增</span></a>&nbsp;&nbsp;
<a onClick="checkUpdate()" ><img src='IconPage/images/update.png'/><span>修改</span></a>&nbsp;&nbsp;
<a onClick="checkDelete()"><img src='IconPage/images/close.png'/><span>删除</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href='dataUnit/RisAddBatchPreAction'><img  src='IconPage/images/add.png'/><span>批量新增</span></a>&nbsp;&nbsp;
<img  src='IconPage/images/line.gif'/></div>

<div id='pagetable'> 
    <table id='mytable' class='tablestyle' width='100%' height='100%' >
    	<tr style="background-image:url(IconPage/images/thbg.png)">				
						<td width="14%" align="center" height="30">
						<img src='IconPage/images/check.png' onClick="allselect()" width="13" height="13" id="allcheck"/><span id="isallselected">全选</span>
						</td>
                		<td width="14%" align="center">序号</td>
                		<td width="14%" align="center">风险编号</td>
                		<td width="14%" align="center">一级风险名称</td>
                		<td width="14%" align="center">二级风险名称</td>
                		<td width="15%" align="center">归口部门</td>
						<td width="15%" align="center">建立时间</td>
						<%int num=0;%>
            		</tr>
					<tbody id="body_riskManage">
            		<s:iterator value="risList">
					<tr title="双击查看风险信息" align="center" onDblClick="window.location.href='dataUnit/RisReadAction?riskId=<s:property value='riskId'/>'">
              			<td align="center" height="25"><input type="checkbox" name="idCheck"  value="<s:property value='riskId'/>"/></td>
						<td align="center"><%=++num%></td>
              			<td align="center"><s:property value="riskId"/></td>
            			<td align="center"><s:property value="rtName"/></td>
            			<td align="center"><s:property value="riskName"/></td>
            			<td align="center"><s:property value="riskDep"/></td>
            			<td align="center"><s:property value="riskRemark"/></td>
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
	if((operation=="addupdate")&&(result=="success")) alert("提交成功!");
	else if(operation=="delete"&&result=="success") alert("删除成功!");
	else if(operation=="addupdate"&&result=="fail") alert("提交失败!");
	else if(operation=="delete"&&result=="fail") alert("删除失败!");
	else if(operation=="addBatch"&&result=="success") alert("批量新增成功!");
	else if(operation=="addBatch"&&result=="fail") alert("批量新增失败!");
}
var flag=0;

//上一页
function prepage(){
		var s=document.getElementById("actionName").value+"?current_pagenum=";
    	var current_page = document.getElementById("current_pagenum");
    	current_page=(current_page.value)*1-1;
    	if(0==current_page)	return;
    	risk.action=s+current_page+"&pageNum=10";
    	risk.submit();
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
    	risk.action=s+current_page+"&pageNum=10";
    	risk.submit();
}
//首页
function firstpage(){
    	var s=document.getElementById("actionName").value+"?current_pagenum=";
    	risk.action=s+1+"&pageNum=10";
    	risk.submit();
}
//尾页
function lastpage(pageno){
		var s=document.getElementById("actionName").value+"?current_pagenum=";
    	risk.action=s+pageno+"&pageNum=10";
    	risk.submit();
}
//跳转
function jumppage(maxpage){
		var s=document.getElementById("actionName").value+"?current_pagenum=";	
		var pageno=document.getElementById("current_pagenum").value;
		//alert("pageno="+pageno);
		//alert("maxpage"+maxpage);
		if(maxpage < pageno || pageno < 1)	return;	
    	risk.action=s+pageno+"&pageNum=10";
    	risk.submit();
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
	window.location.href='dataUnit/RisUpdatePreAction?riskId='+sum[countf].value+'&updateFlag=update&current_pagenum='+pageno;
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
			flag = true;break;
		};
	};
	if(flag == false)
	{ 
		alert("请至少选择一条删除项!"); 
		return false;
	} 
	if(confirm("确定删除?"))
	{
		document.forms[0].action='dataUnit/RisMultiDelAction?current_pagenum=1&pageNum=10';document.forms[0].submit(); 
		return true;
	};
};

function search(){
	risk.action="dataUnit/RisSearchAction?current_pagenum=1&pageNum=10";
	risk.submit();
}
function EnterPress(e){
	var e=e || window.event;
	if(e.keyCode == 13){
		risk.action="dataUnit/RisSearchAction?current_pagenum=1&pageNum=10";
		risk.submit();
	}
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
	} 
}
</script>

</body>
</html>
