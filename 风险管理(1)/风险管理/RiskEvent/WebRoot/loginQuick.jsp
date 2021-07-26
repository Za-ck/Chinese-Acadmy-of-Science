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
    
    <title>中南电力设计</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">   
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  <style type="text/css"></style>
</head>
  
 <body >
 <form name="login" action="default/loginAction" method="post">
<table width="100%" border="0" height="100%" cellpadding="0" cellspacing="0">
  <tr>
    
    <td width="1024">
<table id="__01" width="1024" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="4" width="151" height="29">
			<input name="userid" type="text"  style="visibility:hidden"/></td>
	</tr>
</table>
    </td>
  </tr>
  
</table>


</form>

</body>
<SCRIPT type="text/javascript">
	//alert("获取用户名");
	//用户是链接进入的
	//alert("55");
	
	//if(window.ActiveXObject)
	//{
		//先判断浏览器是否支持ActiveXObject控件
		//var WshNetwork = new ActiveXObject("WScript.Network");
		//document.getElementById("userid").value=WshNetwork.UserName;
	//}
	
	try {

        //先判断浏览器是否支持ActiveXObject控件
		var WshNetwork = new ActiveXObject("WScript.Network");
		document.getElementById("userid").value=WshNetwork.UserName;

    } catch (e) {}
	
	//自动填取用户名
	
	//loginForm.action="default/loginSingleSystemAction?userid="+userid;
	//注意，上面注释掉的，这样传递会出现错误，因为页面上已经有一个userid的input，这样会传递一个数组到action，
	//若action中对userid的定义没有使用list而是将其定义为String，则会默认为将这些数组的值连接成一个string，中间用 ”,” 相连，所以要注意
	document.forms[0].action="default/loginSingleSystemAction";
  	document.forms[0].submit();
	
</SCRIPT>
</html>
