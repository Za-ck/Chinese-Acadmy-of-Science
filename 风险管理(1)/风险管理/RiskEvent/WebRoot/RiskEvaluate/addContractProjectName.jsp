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
    <title>添加项目</title>
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
		  <span class="newfont07file">项目名称：</span>
          <input height="25" name="pName" id="pName" type="text"/> 
        </td>
     </tr>
     
     <tr>
		<td colspan="1" height="25"> </td>
     </tr>

     <tr>
        <td colspan="1" height="25" style="border: none;">
          <label style = "padding-left:70;"> <input name="buttonadd" type="button" onClick="addProjectName()" value="确定"/></label>
	      <label style = "padding-left:70;"><input name="buttonback" type="button" onClick="back()" value="取消"/></label>
       </td>
	 </tr> 
	</table>
	 <input type="text" style="visibility:hidden;width:10px;" name="elementHidden" id="elementHidden" value="可能原因"/>
     </form>
<script type="text/javascript">
function addProjectName(){    
	var Name = document.getElementById("pName").value;
<%--	alert(Name);--%>
	var parentWindow = dialogArguments;
	if(null ==Name||""==Name){
		alert("请输入项目名称!");
	}
	else{	
		$.ajax({
               url: 'default/ajaxAddContractProjectAction?pName=' + Name+'&tmpnum='+ Math.random().toString(),
               type: 'post',
               dateType: 'json',
               data: '',
               contentType: 'text/html;charset=utf-8',
               success: function (data) {     
                    var data1 = eval("("+ data+")"); 
                    var isAdd=data1[0].isAdd;
<%--                    alert(isAdd);--%>
                    if(isAdd=="是"){
                    parentWindow.projectName1=Name;
                    parentWindow.updateProject();
                    window.close();
                    }
                    else{
                    	alert("此项目已存在");
                    }
               
               },
               error: function () {
               alert("error");
           }
       });
	}
	 
}
function back(){
	window.close();
}


</script>
</body>
</html>