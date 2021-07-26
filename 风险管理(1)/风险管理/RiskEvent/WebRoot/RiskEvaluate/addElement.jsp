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
    <title>添加风险因素的可能原因</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
	<link href="/RiskEvent/css/frameStyle.css" rel="stylesheet" type="text/css" />
	<script src="/RiskEvent/js/jquery-1.7.1.js" type="text/javascript" charset="utf-8"></script>  
</head>
<!--下面：阻止执行action后，弹出一个新窗口，执行action后仍回到本窗口-->
 <base target="_self"></base>
<!--上面：-->
<body>
     <form name="Element" id="Element" method="post" >

     <table  width="350" border="1" cellpadding="2" cellspacing="1">

     <tr>
		<td colspan="1" height="25"> </td>
     </tr>
     <tr>
		<td colspan="1" height="25">
		  <span class="newfont07file">可能原因的名称：</span>
          <input height="25" name="elementName" id="elementName" type="text"/> 
        </td>
     </tr>
     
     <tr>
		<td colspan="1" height="25"> </td>
     </tr>

     <tr>
        <td colspan="1" height="25" style="border: none;">
          <label style = "padding-left:70;"> <input name="buttonadd" type="button" onClick="addElement()" value="确定"/></label>
	      <label style = "padding-left:70;"><input name="buttonback" type="button" onClick="back()" value="取消"/></label>
       </td>
	 </tr> 
	</table>
	 <input type="text" style="visibility:hidden;width:10px;" name="elementHidden" id="elementHidden" value="可能原因"/>
     </form>
<script type="text/javascript">
function addElement(){    
	var Name = document.getElementById("elementName").value;
	
	var parentWindow = dialogArguments;
	if(null ==Name||""==Name){
		alert("请输入可能原因名称!");
	}
	else{
	    
		$('#elementHidden').val(Name);
		parentWindow.elementName1=document.getElementById("elementHidden").value;
		parentWindow.addElement_Impel();  //调用父窗口addElement_Impel()函数增加可能原因
		window.close();
	}
	 
}
function back(){
	window.close();
}


</script>
</body>
</html>