<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="level" uri="/WEB-INF/level.tld" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link href="/RiskEvent/js/windows.css" rel="stylesheet" type="text/css" />

<title>中南电力设计</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image:url(images/bg.png);
}
.menulist ul{
width:100%;
margin-top:0px;
list-style:none;
text-align:left;
list-style-position:outside;
}
.menulist ul li{
width:100%;
height:30px;
margin-left:5px;
}
.menulista{
text-decoration:none;
cursor:pointer;
color:#818181;
}
.menulist ul li a{
text-decoration:none;
cursor:pointer;
color:#818181;
}
.menulist ul li a:hover{
color:#2e9cee;
font-weight:bold;
}
.menulist ul li a span{
background:url(images/dote.png) left no-repeat;
padding-left:10px;
font-size:14px;
font-family:Arial, Helvetica, sans-serif;
}
-->
</style>
<style> 
.navPoint { 
COLOR: white; CURSOR: hand; FONT-FAMILY: Webdings; FONT-SIZE: 9pt 
} 
</style> 
<script>
var flag=0;
function switchSysBar(){ 
if (flag==0)
{ 
document.all("frmTitle").style.width="20"; 
document.all("leftDiv").style.display="none";
document.all("leftDivHidden").style.display="";
flag=1;
} 
else
{ 
document.all("frmTitle").style.display="" 
document.all("frmTitle").style.width="183"; 
document.all("leftDiv").style.display="";
document.all("leftDivHidden").style.display="none";
flag=0;
} 
} 

function initTreeMenu(num,text){
 var uls=document.getElementById("menulist").getElementsByTagName("ul");
 document.getElementById("menuText").innerHTML=text;
 for(var i=0;i<uls.length;i++)uls[i].style.display="none";
 
var lis=document.getElementById("menu"+num).getElementsByTagName("li");
 for(var i=0;i<lis.length;i++) {
 var a=lis[i].getElementsByTagName("a");
 a[0].style.color="#818181";
 }
 document.all("menu"+num).style.display="";
window.frames["mainFrame"].document.location.href="Rightmain.jsp?num="+num+"&random="+Math.floor(Math.random()*1000+1);
}
function changeColor(upnum,num){
 var lis=document.getElementById("menu"+upnum).getElementsByTagName("li");
 for(var i=0;i<lis.length;i++) {
 var a=lis[i].getElementsByTagName("a");
 a[0].style.color="#818181";
 }
 document.getElementById("menu"+num).style.color="#2e9cee";
}

function callHome(){
 document.getElementById("mainFrame").src="fxlz.html";
  var uls=document.getElementById("menulist").getElementsByTagName("ul");
 document.getElementById("menuText").innerHTML="功能菜单";
 for(var i=0;i<uls.length;i++)uls[i].style.display="none";
}

function showWarn(action){
window.frames["mainFrame"].document.location.href=action+"?random="+Math.floor(Math.random()*1000+1);
 var uls=document.getElementById("menulist").getElementsByTagName("ul");
 document.getElementById("menuText").innerHTML="风险流转";
 for(var i=0;i<uls.length;i++)uls[i].style.display="none";
 var lis=document.getElementById("menu"+13).getElementsByTagName("li");
 for(var i=0;i<lis.length;i++) {
 var a=lis[i].getElementsByTagName("a");
 a[0].style.color="#818181";
 }
 document.all("menu"+13).style.display=""; 
}
</script>

</head>

<body style="overflow:hidden;">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="183" id=frmTitle nowrap="nowrap" name="fmTitle" align="center" valign="top">
    <!--
	<iframe name="I1" height="100%" width="100%" src="treeLeft.html"  base target="rightMain" border="0" frameborder="0" scrolling="no">
	浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架</iframe>
	-->
	<div id="leftDiv" style="width: 100%; height: 100%">
	<table id="__01" width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td background="images/treeLeft_01.gif" width="183" height="71">	
        <div style="width: 90%; height:40px; text-align:right; ">
        <div id="menuText" style="float:left;width:75%;height:40px; font-size:17px;color:#1281d3; font-weight:bolder; text-align:right; margin-top:5px;">功能菜单</div>
         <img id="leftImag" title="隐藏功能" src="images/close.png" style="cursor: pointer;" onClick="switchSysBar()" width="30" height="30"/></div>
			</td>
	</tr>
	<tr>
		<td background="images/treeLeft_02.gif" width="183" height="16">&nbsp; <br></td>
	</tr>
	<tr>
		<td background="images/treeLeft_02.gif" width="183" valign="top" style="margin-top:0px; padding:0px;">
		<div id="menulist" class="menulist">
		<level:level level="2"></level:level>
       </div>
			</td>
	</tr>
	<tr>
		<td background="images/treeLeft_04.gif" width="183" height="31">&nbsp;
			</td>
	</tr>
</table>
	</div>
	<div id="leftDivHidden" onClick="switchSysBar()"  title="展开功能" style="width:20px;height:500px;cursor:pointer;text-align:left; background:url(images/open2.png) center no-repeat; display:none;">   
    </div></td>
    <td align="center" valign="top"><iframe name="mainFrame" height="100%" width="100%" src="<%=request.getSession().getAttribute("Out_Jsp")%>" frameborder="0"  id="mainFrame"> 浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架</iframe></td>
  </tr>
  
</table>
  <%
  if (session.getAttribute("inremind") == "1") {
  %>
  	<script language=javascript src="/RiskEvent/js/Notice/notice.js"></script>
  <div id="winpop" >
  <div class="title">您有新的消息<span class="close" onclick="tips_pop()">×</span></div>
  <div class="con">您本月部门还未录入任何风险事件</div>
  </div>
  <%
  }
  %>
</body>
</html>

