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
    <title>添加涉及流程文件</title>
	<meta http-equiv="Pragam" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache" must-revalidate/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="X-UA-Compatible"content="IE=7" /><!--以IE7模式渲染-->
	<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
	<link href="/RiskEvent/css/frameStyle.css" rel="stylesheet" type="text/css" />
	<script src="/RiskEvent/js/jquery-1.7.1.js" type="text/javascript" charset="utf-8"></script>  
</head>
<!--下面：阻止执行action后，弹出一个新窗口，执行action后仍回到本窗口-->
 <base target="_self"></base>
<!--上面：-->
<body onload="init();">
<form name="selectFile" id="selectFile" method="post" >
<div id="table1" style="width: 95%"> 
	<table  width="100%" border="1" cellpadding="0" cellspacing="0" class="warp_table" id="changecolor" >
		<tr>
		<td colspan="3" height="25">
        	<span class="newfont07file">文件名称：</span>
        	<input name="fileNameString" id="fileNameString" type="text"  style="height: 24px;width: 400px" value="<s:property value="fileNameString"/>" onkeypress="EnterPress(event)" onkeydown="EnterPress()"  />
			<img height="24" align="middle" src="images/mag.png" onClick="search()"/>		</td>
		</tr>
		<tr>
        <td colspan="3" height="25"><span class="newfont07file">选择：
			<a href="#" class="right-font08" onClick="funcAllSel();return false;">全选</a>
			<a href="#" class="right-font08" onClick="funcInvertSel();return false;">反选</a></span>
			<input name="buttonadd" type="button" onClick="addFile();" value="确定"/>
			<span class="right-font08" style="margin-left:10px;">当前条数：<s:property value='recordSearchedNum' />/<s:property value='recordNum' /></span>
			<textarea style="visibility:hidden" name="allFileId" cols="1" rows="1"  ><s:property value='allFileId'/></textarea>
			<textarea style="visibility:hidden" name="allFileName" cols="1" rows="1"  ><s:property value='allFileName'/></textarea>		</td>
        </tr>
        <tr align="center">
            <th scope="col" align="center"  width="50"  height="20"><div align="center" style="font-size:13px">选择</div></th>
            <th scope="col" align="center"  width="150"><div align="center" style="font-size:13px">文件编号</div></th>
            <th scope="col" align="center"  width="360"><div align="center" style="font-size:13px">文件名称</div></th>
		</tr>
		<tbody id="body_File">
		 <s:iterator value="filList1">
			<tr>  
			<td align="center"><input type="checkbox" name="idCheckFile"  value="<s:property value='fileId'/>@<s:property value='fileName'/>@"/></td>
            <td align="center"><s:property value="fileId"/></td>
            <td align="center"><s:property value="fileName"/></td>
            </tr>		   					
     	 </s:iterator>
	  </tbody>
	</table>
  </div>
  <div id="pageDiv">
  </div>
	<%-- <div id='pageDIV' style="background-image:url(IconPage/images/bg.png);width: 92%">
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
<%-- var fileList = <%=session.getAttribute("filList1")%>;
var pageNumMax = Math.ceil(fileList/10);//最大可能页数，每页显示10条
function page(pageNum)//前端分页
{
	var htmlContext = "";
	var tr=""
	for(i=0;i<10;i++)
	{
		tr = "<tr><td><input type='checkbox'></input></td></tr>"
	}
} --%>

function init() {

	var allFileId = document.getElementById("allFileId").value;
	var sArray=allFileId.split("@");
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
		var s="riskInput/ImpFileAction1?current_pagenum=";
    	var current_page = document.getElementById("current_pagenum");
    	current_page=(current_page.value)*1-1;
    	if(0==current_page)	return;
    	selectFile.action=s+current_page+"&pageNum=10";
    	selectFile.submit();
}
//下一页
function nextpage(maxpage){
		var s="riskInput/ImpFileAction1?current_pagenum=";
    	var current_page = document.getElementById("current_pagenum");
		if(maxpage == current_page)	return;	
		current_page=(current_page.value)*1+1;  
		if(maxpage < current_page)	return;	
    	selectFile.action=s+current_page+"&pageNum=10";
    	selectFile.submit();
}
//首页
function firstpage(){
    	var s="riskInput/ImpFileAction1?current_pagenum=";
    	selectFile.action=s+1+"&pageNum=10";
    	selectFile.submit();
}
//尾页
function lastpage(pageno){
		var s="riskInput/ImpFileAction1?current_pagenum=";
    	selectFile.action=s+pageno+"&pageNum=10";
    	selectFile.submit();
}
//跳转
function jumppage(maxpage){
		var s="riskInput/ImpFileAction1?current_pagenum=";	
		var pageno=document.getElementById("current_pagenum").value;
		if(maxpage < pageno || pageno < 1)	return;	
    	selectFile.action=s+pageno+"&pageNum=10";
    	selectFile.submit();
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
function addFile(){
	var allFileId = document.getElementById("allFileId").value;
	var allFileName = document.getElementById("allFileName").value;
	var sArray=allFileId.split("@");
	var no = sArray.length-2;//用于计数，目前选中了多少项
	var sum = document.getElementsByName("idCheckFile");
	var flaghave=0;//用来判断复选框选中的file是否早已选中，为0表示未先选中
	if(sum != null)
	{
		for(var i=0;i<sum.length;i++)
		{
			flaghave=0;
			if(sum[i].checked =="checked" || sum[i].checked ==true)
			{
				//通过if过滤，这里的sum是所有选择了的复选框，sum[i]是某复选框
				var s=sum[i].value.split("@");//将复选框的值分解成Fileid和Filename，其中s[0]是Fileid，s[1]是Filename
				for(j=0;j<sArray.length;j++)//判断sArray中是否已包含该元素
				{
					if(sArray[j] == s[0])
					{
						flaghave=1;
						break;
					}
				}
				if(flaghave!=1)
				{
					allFileId=allFileId+s[0]+"@";
					no = no+1;
					allFileName=allFileName+no+"、"+s[0]+"，"+s[1]+"\n";
				}
			}
		}
	}
	document.getElementById("allFileId").value = allFileId;
	document.getElementById("allFileName").value = allFileName;	
	backData();
	window.close();

	/* var flaghave=0;//用来判断复选框选中的file是否早已选中，为0表示未先选中
	var allFileId = document.getElementById("allFileId").value;
	var allFileName = document.getElementById("allFileName").value;
	var sArray=allFileId.split("@");
	var no1=sArray.length-2;//已选中的file的个数	
	var sum = document.getElementsByName("idCheckFile");
	if(null !=sum){
		for(var i=0;i<sum.length;i++){    
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
					allFileId=allFileId+s[0]+"@";
					no1=no1+1;
					allFileName=allFileName+no1+"、"+s[0]+"，"+s[1]+"\n";
				};
			};
		}; 
	}
	document.getElementById("allFileId").value = allFileId;
	document.getElementById("allFileName").value = allFileName;	
	backData();
	window.close();*/
}
//返回
function back1(){
	backData();
	window.close();
}
//返回之前先将allFileId和allFileName返回
function backData(){
	   var parentWindow = dialogArguments;
       parentWindow.allFileId1=document.getElementById("allFileId").value;
	   parentWindow.allFileName1=document.getElementById("allFileName").value;
       parentWindow.update(); 
}

function search(){
	selectFile.action="riskInput/ImpFileAction1?current_pagenum=1&pageNum=10";
	selectFile.submit();
};
</script>
</body>
</html>
