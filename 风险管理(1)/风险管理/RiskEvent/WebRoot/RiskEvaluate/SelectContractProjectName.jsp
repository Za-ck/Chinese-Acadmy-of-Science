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
    <base href="<%=basePath%>" target="_self">
    <title>选择项目</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="X-UA-Compatible"content="IE=7" /><!--以IE7模式渲染--> 
	<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
	<link href="/RiskEvent/css/frameStyle.css" rel="stylesheet" type="text/css" />
	<script src="/RiskEvent/js/jquery-1.7.1.js" type="text/javascript" charset="utf-8"></script>  
</head>
<!--下面：阻止执行action后，弹出一个新窗口，执行action后仍回到本窗口-->
 <base target="_self"></base>
<!--上面：-->
<body>
<form name="selectflowFile" id="selectflowFile" method="post" >
<div id="table1" style="width:95%"> 
	<table  width="100%" border="1" cellpadding="0" cellspacing="0" class="warp_table" id="changecolor" >
		<tr>
		<td colspan="3" height="25">
        	<span class="newfont07file">项目名称：</span>
        	<input style="height: 24px;width: 400px" name="projectNameString" id="projectNameString" type="text"  value="<s:property value="projectNameString"/>" onkeypress="EnterPress(event)" onkeydown="EnterPress()"/>
			<img height="24" align="middle" src="images/mag.png" onClick="search()"/>		</td>
		</tr>
		<tr>
        <td colspan="3" height="25"><span class="newfont07file">选择：
<%--			<a href="#" class="right-font08" onClick="funcAllSel();return false;">全选</a>---%>
<%--			<a href="#" class="right-font08" onClick="funcInvertSel();return false;">反选</a></span>--%>
			<input name="buttonadd" type="button" onClick="addProjectName();" value="确定"/>
			<textarea style="visibility:hidden" name="allflowFileId" cols="5" rows="1"  ><s:property value='allflowFileId'/></textarea>
			<textarea style="visibility:hidden" name="allflowFileName" cols="1" rows="1"  ><s:property value='allflowFileName'/></textarea>		</td>
        </tr>
        <tr align="center">
            <th scope="col" align="center"  width="50"  height="20"><div align="center" style="font-size:13px">选择</div></th>
            <th scope="col" align="center"  width="150"><div align="center" style="font-size:13px">项目名称</div></th>
		</tr>
		<tbody id="body_File">
		<s:iterator value="projectNameList">
			<tr>  
			<td align="center"><input type="checkbox" name="idCheckFile"  value="<s:property value='name'/>"/></td>
            <td align="center"><span style="color: #f00;"><s:property value="name"/></span></td>
            </tr>		   					
     	 </s:iterator>
	  </tbody>
	</table>
  </div>
	<div id='pageDIV' style="background-image:url(IconPage/images/bg.png)" style="width:92%">
					<a onClick="firstpage()"><img border='0' title='首页' src='IconPage/images/first.gif'/></a>
                    <a onClick="prepage()"><img border='0'title='上一页' src='IconPage/images/prev.gif'/></a>
                    <span>&nbsp;&nbsp;第</span>
                    <input id='current_pagenum' style='width:45px' type='text' value="<s:property value="current_pagenum"/>"/> <span>页&nbsp;&nbsp;</span>
      <span>共
                  <%
                     int pc=Integer.valueOf(request.getSession().getAttribute("pagecount").toString());
                     int pg=0;
                     if(pc%10==0){ pg=pc/1;out.print(pg);}
                     else {pg=(pc/10)+1;out.print(pg);}
                  %>页&nbsp;&nbsp;</span>
                  <a onClick="nextpage(<%=pg%>)"><img border='0' title='下一页' src='IconPage/images/next.gif'/></a>
				  <a onClick="lastpage(<%=pg%>)"><img border='0' title='尾页' src='IconPage/images/last.gif'/></a>
				 <a onClick="jumppage(<%=pg%>)"><img border='0' style='cursor:pointer;' title='跳转' src='IconPage/images/load.png'/></a>
				 <span>共&nbsp;&nbsp;<%=request.getSession().getAttribute("pagecount")%>&nbsp;&nbsp;条记录</span>
	</div>
</form>
<script type="text/javascript">

	function EnterPress() {
	
		var e = e || window.event;
		if(e.keyCode == 13)
		{
			search();
		};
	}

//上一页
function prepage(){
		var s="riskEvaluate/selectContractProjectName?current_pagenum=";
    	var current_page = document.getElementById("current_pagenum");
    	current_page=(current_page.value)*1-1;
    	if(0==current_page)	return;
    	selectflowFile.action=s+current_page+"&pageNum=10";
    	selectflowFile.submit();
}
//下一页
function nextpage(maxpage){
		var s="riskEvaluate/selectContractProjectName?current_pagenum=";
    	var current_page = document.getElementById("current_pagenum");
		if(maxpage == current_page)	return;	
		current_page=(current_page.value)*1+1;  
		if(maxpage < current_page)	return;	
    	selectflowFile.action=s+current_page+"&pageNum=10";
    	selectflowFile.submit();
}
//首页
function firstpage(){
    	var s="riskEvaluate/selectContractProjectName?current_pagenum=";
    	selectflowFile.action=s+1+"&pageNum=10";
    	selectflowFile.submit();
}
//尾页
function lastpage(pageno){
		var s="riskEvaluate/selectContractProjectName?current_pagenum=";
    	selectflowFile.action=s+pageno+"&pageNum=10";
    	selectflowFile.submit();
}
//跳转
function jumppage(maxpage){
		var s="riskEvaluate/selectContractProjectName?current_pagenum=";	
		var pageno=document.getElementById("current_pagenum").value;
		if(maxpage < pageno || pageno < 1)	return;	
    	selectflowFile.action=s+pageno+"&pageNum=10";
    	selectflowFile.submit();
}
//全选
function funcAllSel(){
	var sum1 = document.getElementsByName("idCheckFile");
	for(var i=0;i<sum1.length;i++)
	{    
		sum1[i].checked = true;
	};
}

//反选
function funcInvertSel(){
   var sum1 = document.getElementsByName("idCheckFile");
	for(var i=0;i<sum1.length;i++)
	{    
		if(sum1[i].checked ==true) sum1[i].checked=false;
		else
			sum1[i].checked =true;
	};
}
//添加
function addProjectName(){
	var sum = document.getElementsByName("idCheckFile");
<%--	alert("addProjectName");--%>
	var name="";
	if(null !=sum){
		for(var i=0;i<sum.length;i++){    
			if(sum[i].checked =="checked" || sum[i].checked ==true){
			    name=sum[i].value;
<%--			    alert(name);--%>
			};
		};
	}
	backData(name);
	window.close();
}
//返回
function back1(){
	backData(name);
	window.close();
}

//返回之前先将projectName返回
function backData(name){
	   var parentWindow = dialogArguments;
	   parentWindow.projectName1=name;
       parentWindow.updateProject(); 
}

function search(){
	selectflowFile.action="riskEvaluate/selectContractProjectName?current_pagenum=1";
	selectflowFile.submit();
};
</script>
</body>
</html>
