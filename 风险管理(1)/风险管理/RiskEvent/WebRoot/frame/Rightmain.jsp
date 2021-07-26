<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="level" uri="/WEB-INF/level.tld" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>功能主界面</title>
<style>
.imglist ul li{
width:100px;
float:left;
text-align:center;
padding:10px;
list-style:none;
}
.imglist ul li a{
text-align:center;
text-decoration:none;
cursor:pointer;
color:#818181;
font-size:13px;
}
.imglist ul li a:hover{
color:#2e9cee;
font-size:13px;
}
.imglist ul li a img{
border:0;
margin-left:8px;

}
.imglist ul li a span{
width:100%;
text-align:center;
margin-left:10px;
float:left;
}
<!--behavior:url("images/iepngfix.htc");-->
</style>
</head>

<body>
<%--<div id="imgList" class="imglist">
<level:level level="3" id="imagesLink" imagelist="0"></level:level>	
</div>--%>
<script>  
 /* var url=location.href;
  var numTemp=url.split("?");
  var num0=numTemp[1].split("&");
  var num=num0[0].split("=");
  var uls=document.getElementById("imgList").getElementsByTagName("ul");
 for(var i=0;i<uls.length;i++)uls[i].style.display="none";
 document.all("imgmenu"+num[1]).style.display="";*/
</script>
</body>
</html>
