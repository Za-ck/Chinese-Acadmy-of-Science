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
    <title>添加涉及内控文件</title>
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
<body onload="init();">
<form name="selectflowFile" id="selectflowFile" method="post" >
<div id="table1" style="width:95%"> 
	<table  width="100%" border="1" cellpadding="0" cellspacing="0" class="warp_table" id="changecolor" >
		<tr>
		<td colspan="3" height="25">
        	<span class="newfont07file">文件名称：</span>
        	<input style="height: 24px;width: 400px" name="flowfileNameString" id="flowfileNameString" type="text"  value="<s:property value="flowfileNameString"/>" onkeypress="EnterPress(event)" onkeydown="EnterPress()"/>
			<img height="24" align="middle" src="images/mag.png" onClick="search()"/>		</td>
		</tr>
		<tr>
        <td colspan="3" height="25"><span class="newfont07file">选择：
			<a href="#" class="right-font08" onClick="funcAllSel();return false;">全选</a>-
			<a href="#" class="right-font08" onClick="funcInvertSel();return false;">反选</a></span>
			<input name="buttonadd" type="button" onClick="addFlowFile1();" value="确定"/>
			<span class="right-font08" style="margin-left:10px;">当前条数：<s:property value='recordSearchedNum' />/<s:property value='recordNum' /></span>
			<textarea style="visibility:hidden" name="allflowFileId" cols="5" rows="1"  ><s:property value='allflowFileId'/></textarea>
			<textarea style="visibility:hidden" name="allflowFileName" cols="1" rows="1"  ><s:property value='allflowFileName'/></textarea>		</td>
        </tr>
        <tr align="center">
            <th scope="col" align="center"  width="50"  height="20"><div align="center" style="font-size:13px">选择</div></th>
            <th scope="col" align="center"  width="150"><div align="center" style="font-size:13px">文件编号</div></th>
            <th scope="col" align="center"  width="360"><div align="center" style="font-size:13px">文件名称</div></th>
		</tr>
		<tbody id="body_File">
		<s:iterator value="defaultfilList2">
			<tr>  
			<td align="center"><input type="checkbox" name="idCheckFile"  value="<s:property value='fileId'/>@<s:property value='fileName'/>@"/></td>
            <td align="center"><span style="color: #f00;"><s:property value="fileId"/></span></td>
            <td align="center"><span style="color: #f00;"><s:property value="fileName"/></span></td>
            </tr>		   					
     	 </s:iterator>
		 <s:iterator value="flowfilList1">
			<tr>  
			<td align="center"><input type="checkbox" name="idCheckFile"  value="<s:property value='flowfileId'/>@<s:property value='flowfileName'/>@"/></td>
            <td align="center"><s:property value="flowfileId"/></td>
            <td align="center"><s:property value="flowfileName"/></td>
            </tr>		   					
     	 </s:iterator>
	  </tbody>
	</table>
  </div>
	<%-- <div id='pageDIV' style="background-image:url(IconPage/images/bg.png)" style="width:92%">
					<a onClick="firstpage()"><img border='0' title='首页' src='IconPage/images/first.gif'/></a>
                    <a onClick="prepage()"><img border='0'title='上一页' src='IconPage/images/prev.gif'/></a>
                    <span>&nbsp;&nbsp;第</span>
                    <input id='current_pagenum' style='width:45px' type='text' value="<s:property value="current_pagenum"/>"/> <span>页&nbsp;&nbsp;</span>
      <span>共
                  <%
                     int pc=Integer.valueOf(request.getSession().getAttribute("pagecount").toString());
                     int pg=0;
                     if(pc%10==0){ pg=pc/10;out.print(pg);}
                     else {pg=(pc/10)+1;out.print(pg);}
                  %>页&nbsp;&nbsp;</span>
                  <a onClick="nextpage(<%=pg%>)"><img border='0' title='下一页' src='IconPage/images/next.gif'/></a>
				  <a onClick="lastpage(<%=pg%>)"><img border='0' title='尾页' src='IconPage/images/last.gif'/></a>
				 <a onClick="jumppage(<%=pg%>)"><img border='0' style='cursor:pointer;' title='跳转' src='IconPage/images/load.png'/></a>
				 <span>共&nbsp;&nbsp;<%=request.getSession().getAttribute("pagecount")%>&nbsp;&nbsp;条记录</span>
	</div> --%>
</form>
<script type="text/javascript">

function init()
{
	var allflowFileId = document.getElementById("allflowFileId").value;
	var sArray=allflowFileId.split("@");
	var sum = document.getElementsByName("idCheckFile");
	if(null !=sum){
		for(var i=0;i<sum.length;i++){    
				//通过if过滤，这里的sum是所有选择了的复选框，sum[i]是某复选框
				var s=sum[i].value.split("@");//将复选框的值分解成Fileid和Filename，其中s[0]是Fileid，s[1]是Filename
				for(var j=0;j<sArray.length;j++){    
					//sArray[j]是某FileId
					if(s[0]==sArray[j]){
						sum[i].checked = "checked";
						break;
					};
				};
			};
		
	};
}

	function EnterPress() {
	
		var e = e || window.event;
		if(e.keyCode == 13)
		{
			search();
		};
	}

//上一页
function prepage(){
		var s="riskInput/addFlowFile?current_pagenum=";
    	var current_page = document.getElementById("current_pagenum");
    	current_page=(current_page.value)*1-1;
    	if(0==current_page)	return;
    	selectflowFile.action=s+current_page+"&pageNum=10";
    	selectflowFile.submit();
}
//下一页
function nextpage(maxpage){
		var s="riskInput/addFlowFile?current_pagenum=";
    	var current_page = document.getElementById("current_pagenum");
		if(maxpage == current_page)	return;	
		current_page=(current_page.value)*1+1;  
		if(maxpage < current_page)	return;	
    	selectflowFile.action=s+current_page+"&pageNum=10";
    	selectflowFile.submit();
}
//首页
function firstpage(){
    	var s="riskInput/addFlowFile?current_pagenum=";
    	selectflowFile.action=s+1+"&pageNum=10";
    	selectflowFile.submit();
}
//尾页
function lastpage(pageno){
		var s="riskInput/addFlowFile?current_pagenum=";
    	selectflowFile.action=s+pageno+"&pageNum=10";
    	selectflowFile.submit();
}
//跳转
function jumppage(maxpage){
		var s="riskInput/addFlowFile?current_pagenum=";	
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
function addFlowFile1(){
	var allflowFileId = document.getElementById("allflowFileId").value;
	var allflowFileName = document.getElementById("allflowFileName").value;
	var sArray=allflowFileId.split("@");
	var no1=sArray.length-2;//已选中的file的个数	
	var sum = document.getElementsByName("idCheckFile");
	var flaghave=0;
	if(null !=sum){
		for(var i=0;i<sum.length;i++){    
			flaghave=0;//用来判断复选框选中的file是否早已选中，为0表示未先选中
			if(sum[i].checked =="checked" || sum[i].checked ==true){
				//通过if过滤，这里的sum是所有选择了的复选框，sum[i]是某复选框
				var s=sum[i].value.split("@");//将复选框的值分解成Fileid和Filename，其中s[0]是Fileid，s[1]是Filename
				for(var j=0;j<sArray.length;j++){    
					//sArray[j]是某FileId
					if(s[0]==sArray[j]){
						flaghave=1;
						break;
					};
				}
				//将新选中的File添加到已选中序列
				if(0==flaghave){
					allflowFileId=allflowFileId+s[0]+"@";
					no1=no1+1;
					allflowFileName=allflowFileName+no1+"、"+s[0]+"，"+s[1]+"\n";
				};
			};
		};
	}
	document.getElementById("allflowFileId").value = allflowFileId;
	document.getElementById("allflowFileName").value = allflowFileName;	
	backData();
	window.close();
}
//返回
function back1(){
	backData();
	window.close();
}

//返回之前先将flowfileId和flowfileName返回
function backData(){
	   var parentWindow = dialogArguments;
       parentWindow.allflowFileId1=document.getElementById("allflowFileId").value;
	   parentWindow.allflowFileName1=document.getElementById("allflowFileName").value;
       parentWindow.updateflow(); 
}

function search(){
	selectflowFile.action="riskInput/addFlowFile?current_pagenum=1&pageNum=10";
	selectflowFile.submit();
};
</script>
</body>
</html>
