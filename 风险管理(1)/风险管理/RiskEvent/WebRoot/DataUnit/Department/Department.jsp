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
    
    <title>部门管理</title>
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
</head>
<body style="font-size:12px; margin-top:5px;" >
<form name="department" id="department" method="post" action="dataUnit/DepartmentAction">
            <div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>部门信息</span></div></div>
         <div class="queryDiv">
         <span>部门名称：</span>
		<input height="30" name="depNameString" id="depNameString" type="text" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" value="" />
		<img height="25" align="middle" src="images/mag.png" onClick="search()"/>
		</div>
  <div id='pageMenu' style="background-image:url(IconPage/images/bg.png)">
<a href='/RiskEvent/DataUnit/Department/DepartmentAddUpdate.jsp'><img  src='IconPage/images/add.png'/><span>新增</span></a>&nbsp;&nbsp;
<a onclick="checkUpdate()" ><img src='IconPage/images/update.png'/><span>修改</span></a>&nbsp;&nbsp;
<a onclick="checkDelete()"><img src='IconPage/images/close.png'/><span>删除</span></a>&nbsp;&nbsp;
<img  src='IconPage/images/line.gif'/></div>


<div id='pagetable'> 
    <table id='mytable' class='tablestyle' width='100%' height='100%' >
    	<tr style="background-image:url(IconPage/images/thbg.png)">				
						<td width="16.7%" align="center" height="30">
						<img src='IconPage/images/check.png' onClick="allselect()" width="13" height="13" id="allcheck"/><span id="isallselected">全选</span>
						</td>
                		<td width="16.7%" align="center">序号</td>
                		<td width="16.7%" align="center">部门编号</td>
                		<td width="16.7%" align="center">部门名称</td>
                		<td width="16.7%" align="center">部门属性</td>
                		<td width="16.5%" align="center">备注</td>
						<%int num=0;%>
		</tr>
					<tbody id="body_depManage">
            		<s:iterator value="depList">
            		<tr title="双击查看部门信息" align="center" onDblClick="handle('<s:property value='depId'/>','<s:property value='depName'/>')">
              			<td align="center" height="25"><input type="checkbox" name="idCheck"  value="<s:property value='depId'/>"/></td>
						<td align="center"><%=++num%></td>
              			<td align="center"><s:property value="depId"/></td>             			
            			<td align="center"><s:property value="depName"/></td>
            			<!-- <s:if test='reState=="*"'>
                                                              已发布</s:if> -->
                         <td align="center">
                         <s:if test='depSort=="0"'>
                         生产部门</s:if><s:else>管理部门
                         </s:else>
                         </td> 
            			<td align="center"><s:property value="depRemark"/></td>
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
}

function allSearch(){
	department.action="dataUnit/DepartmentAction";
	department.submit();
}
function search(){
	department.action="dataUnit/DepSearchAction";
	department.submit();
}
function EnterPress(e){
	var e=e || window.event;
	if(e.keyCode == 13){
		department.action="dataUnit/DepSearchAction";
		department.submit();
	};
}

var flag=0;

//上一页
function prepage(){
		var s=document.getElementById("actionName").value+"?current_pagenum=";
    	var current_page = document.getElementById("current_pagenum");
    	current_page=(current_page.value)*1-1;
    	if(0==current_page)	return;
    	department.action=s+current_page+"&pageNum=10";
    	department.submit();
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
    	department.action=s+current_page+"&pageNum=10";
    	department.submit();
}
//首页
function firstpage(){
    	var s=document.getElementById("actionName").value+"?current_pagenum=";
    	department.action=s+1+"&pageNum=10";
    	department.submit();
}
//尾页
function lastpage(pageno){
		var s=document.getElementById("actionName").value+"?current_pagenum=";
    	department.action=s+pageno+"&pageNum=10";
    	department.submit();
}
//跳转
function jumppage(maxpage){
		var s=document.getElementById("actionName").value+"?current_pagenum=";	
		var pageno=document.getElementById("current_pagenum").value;
		//alert("pageno="+pageno);
		//alert("maxpage"+maxpage);
		if(maxpage < pageno || pageno < 1)	return;	
    	department.action=s+pageno+"&pageNum=10";
    	department.submit();
}

//修改
function checkUpdate(){ 
	flag=false;
	var count =0;
	var countf=0;
	var sum=document.getElementsByName("idCheck"); 
	for(i = 0; i < sum.length; i++){ 
		if(sum[i].checked == true){ 
			flag = true;count++;countf=i;
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
	window.location.href='dataUnit/DepUpdatePreAction?depId='+sum[countf].value+'&updateFlag=update&current_pagenum='+pageno;
} 
//删除
function checkDelete(){ 
	flag=false;
	var sum=document.getElementsByName("idCheck");
	var depIds = "";
	for(i = 0; i < sum.length; i++)
	{ 
		if(sum[i].checked == true)
		{ 
			flag = true;
			depIds += sum[i].value + ",";
			//break;
		};
	} 
	if(flag == false)
	{ 
		alert("请至少选择一条删除项!"); 
		return false;
	} 
	if(confirm("确定删除?"))
	{
		//document.forms[0].action='dataUnit/DepMultiDelAction';
		//document.forms[0].submit(); 
		//return true;
		
		var params="depIds="+ depIds +"&tmpnum="+new Date().getTime();
		$.ajax({
              url: "dataUnit/ajaxDepMultiDelAction",
              type: "post",
              dateType: "json",
              data: params,
              contentType: "application/x-www-form-urlencoded",			//很重要
              success: function (data) 
              {
              		
              		if(data == "success")
              		{
              			alert("删除成功");
              			//riskEventQuery.submit();
              		}
              		else
              		{
              			alert(decodeURIComponent(data) + "正在使用中，不可删除");
              		};
              		var pageno=document.getElementById("current_pagenum").value;
              		department.action="dataUnit/DepSearchAction?current_pagenum=" +pageno+"&pageNum=10";
					department.submit();
              },
              error: function () 
              {
              		alert("操作失败");
          	  }
      	});
		
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
};

function handle(depId,depName) {

	var url = "dataUnit/DepReadAction?depId=" + depId;
	window.location.href=url;
	
};

</script>

</body>
</html>
