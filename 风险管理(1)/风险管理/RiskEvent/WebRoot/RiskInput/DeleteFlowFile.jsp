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
    
    <title>删除涉及流程文件</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="X-UA-Compatible"content="IE=7" /><!--以IE7模式渲染--> 
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="/RiskEvent/css/frameStyle.css" rel="stylesheet" type="text/css" />
<script src="/RiskEvent/js/jquery-1.7.1.js" type="text/javascript" charset="utf-8"></script>  
</head>
<!--下面：阻止执行action后，弹出一个新窗口，执行action后仍回到本窗口-->
 <base target="_self"></base>
<!--上面：-->
<body>
<form name="deleteflowFile" id="deleteflowFile" method="post" >
<div id="table1" style="width: 95%"> 
	<table  width="100%" border="1" cellpadding="0" cellspacing="0" class="warp_table" id="changecolor" >
		<tr>
        <td colspan="3" height="25"><span class="newfont07file">选择：
			<a href="#" class="right-font08" onClick="funcAllSel();return false;">全选</a>-
			<a href="#" class="right-font08" onClick="funcInvertSel();return false;">反选</a></span>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input name="button" type="button" onClick="finish();" value="确定"/>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="取消" onClick="window.close();"/>     
		</td>
        </tr>
        <tr align="center">
            <th scope="col" align="center"  width="50"  height="20"><div align="center" style="font-size:13px">选择</div></th>
            <th scope="col" align="center"  width="150"><div align="center" style="font-size:13px">文件编号</div></th>
            <th scope="col" align="center"  width="360"><div align="center" style="font-size:13px">文件名称</div></th>
		</tr>
		<tbody id="body_File">
  		<s:iterator value="flowfilList2">
			<tr>
			<td align="center"><input type="checkbox" name="idCheckFile1"  value="<s:property value='flowfileId'/>@<s:property value='flowfileName'/>@" %></td>
            <td align="center"><s:property value="flowfileId"/></td>
            <td align="center"><s:property value="flowfileName"/></td>
            </tr>		   					
     	 </s:iterator>
	  </tbody>
	</table>
		
</div>
</form>
<script type="text/javascript">
	var i=1;
	var noCheckFlag=0;
	var allflowFileId="";
	var allflowFileName="";
//单击确定
function finish(){
	//selectFile.action="riskInput/SelectFileAction";
	//selectFile.submit();
	getAllFileData();
	backData();
	//alert("44444444444444 操作完毕 操作完毕 操作完毕 操作完毕");
	window.close();
}
//关闭窗口之前先将allflowFileId和allflowFileName返回
function backData(){
		//alert("33333333333 操作父窗口 操作父窗口 操作父窗口 操作父窗口");
		//alert(allflowFileId);
		//alert(allflowFileName);
	   var parentWindow = dialogArguments;
       parentWindow.allflowFileId1=allflowFileId;
	   parentWindow.allflowFileName1=allflowFileName;
       parentWindow.updateflow(); 
}
//保存复选框信息
function getAllFileData(){
	//alert("00000000000000   getAllFileData  getAllFileData");
	var sum1 = document.getElementsByName("idCheckFile1");
	if(null !=sum1){
		for(var j=0;j<sum1.length;j++)
		{    
			//alert("这里");
			/* if(sum1[j].checked =="checked" || sum1[j].checked ==true){
				//alert("idCheckFile111111111111111选中 开始getData   getData");
				alert(sum1[i].value);
				getData(sum1[j].value);
				noCheckFlag=1;
			} */
			if(sum1[j].checked ==false){
				//alert("idCheckFile111111111111111选中 开始getData   getData");
				alert(sum1[i].value);
				getData(sum1[j].value);
				noCheckFlag=1;
			}
		}
	}
	if(0==noCheckFlag){
		allflowFileId="@";
		allflowFileName="";
	}
}
//得到allflowFileId和allflowFileName
function getData(s){
	var s1;
	var s2;
	var sArray=s.split("@");
	s1=sArray[0];
	s2=sArray[1];
	if(""==allflowFileId){
		//alert("getData   getData    1111111111111111111111111111");
		//alert(s1);
		//alert(s2);
		allflowFileId="@"+s1+"@";
		allflowFileName=i+"、"+s1+"，"+s2+"\n";
		i=i+1;
		//alert(allflowFileId);
		//alert(allflowFileName);
	}else{
		//alert("getData   getData    2个以后 2个以后 2个以后 2个以后");
		allflowFileId=allflowFileId+s1+"@";
		allflowFileName=allflowFileName+i+"、"+s1+"，"+s2+"\n";
		i=i+1;
	}
}
//全选
function funcAllSel(){
	var sum1 = document.getElementsByName("idCheckFile1");
	for(var j=0;j<sum1.length;j++)
	{    
		sum1[j].checked = true;
	}
}

//反选
function funcInvertSel(){
   var sum1 = document.getElementsByName("idCheckFile1");
	for(var j=0;j<sum1.length;j++)
	{    
		if(sum1[j].checked ==true) sum1[j].checked=false;
		else
			sum1[j].checked =true;
	}
}
</script>
</body>
</html>
