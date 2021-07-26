<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    
    <title>管理规定文件新增修改</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
<script language=javascript src="/RiskEvent/js/windows.js"></script>
<script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
<script src="/RiskEvent/js/jquery-1.7.1.js" type="text/javascript"></script>
</head>
  
 <body class="ContentBody">
<form action="dataUnit/FilAddUpdateAction" method="post" enctype="multipart/form-data" name="filAddUpdateForm" id="filAddUpdateForm" >
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle" align="center"><span>管理规定文件新增修改</span></div></div>
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr >
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
		
		<tr>
			<td width="100%">			
				<fieldset style="height:100%;">
				<legend>管理规定文件信息</legend>
				 <div id="errorDiv" style="color:red;font-weight:bold;font-size:12px"></div>
				 	<input type="hidden" name="fileType" value="<s:property value='fileType'/>"/>
					  <table class="contentInfo" border="0" cellpadding="2" cellspacing="1" style="width:100%">					 
					  <tr>
					    <td nowrap align="right" width="37%">文件编号：</td>
					    <td width="15%"><span class="red">
					       <input name="fileId" type="text" class="text" style="width:154px" value="<s:property value='fileId'/>"/>*</span>						
						</td>
					    <td align="right" width="10%">文件名称：</td>
					    <td width="38%"><span class="red">
				        	<input name="fileName" type="text" class="text" style="width:200px" value="<s:property value='fileName'/>"/>*</span>
						</td>
					  </tr>
					  <tr>
					  	<td nowrap align="right" width="37%">文件类型：</td>
					  	<td>
					  		<select name="fileTypeString" id="fileTypeString" style="width: 154px" onchange="changeFileType();">
								<option value="1" <s:if test="fileType==1">selected</s:if>>三标体系</option>
								<option value="2" <s:if test="fileType==2">selected</s:if>>管理标准</option>
								<option value="3" <s:if test="fileType==3">selected</s:if>>工作标准</option>
								<option value="4" <s:if test="fileType==4">selected</s:if>>应急预案</option>
								<option value="5" <s:if test="fileType==5">selected</s:if>>内控流程</option>
							</select>
					  	</td>
					    
					  </tr>
					  <tr>
					  	<td align="right">管理文件涉及的风险名称：</td> 
						<td >
							<s:select name="riskname" theme="simple" list="allriskList" listValue="riskName" listKey="riskId" class="text" style="width:154px"></s:select>
						</td>                 
						<td colspan="2" align="left">
							<img title="添加选中的风险" src='IconPage/images/add.png'  style="cursor: pointer;" onClick="addrow()"/>					
						</td>
					  </tr>	
					  </table>
					  <table class="contentInfo" border="0" cellpadding="2" cellspacing="1" style="width:100%" id="doc">
							<s:iterator value="risList">
								<tr>
                                  <td width="37%" align="right"><input type="text" name="riskId" style="width:5px;visibility:hidden;" value="<s:property value='riskId'/>"/>&nbsp;</td>						
						            <td width="15%" align="right" ><input type="text" name="riskName" align="right" style="width:150px;border:0px;text-align:center;" value="<s:property value='riskName'/>"/></td>									
									<td width="48%" align="left" colspan="2"><img title='删除风险' src="IconPage/images/close.png" style="cursor:pointer" onClick="delete2(this.parentNode.parentNode.rowIndex)"/></td>
 									</tr>
							</s:iterator>
					  </table>
				</fieldset>	
				</td>
		</tr>
		</table>
	 </td>
  </tr>
		<tr>
			<td colspan="2" align="center" height="50px">
				<input type="submit" name="submit" value="" class="save" onClick="show()"/>　
				<input type="button" name="Submit2" value="" class="back" onClick="javascript:history.back(-1)">
			</td>
		</tr>
		<tr >
		<td colspan="2" align="left">
			<input type="text" style="visibility:hidden" name="updateFlag" id="updateFlag" value="<s:property value='updateFlag'/>"/>
			<input type="text" style="visibility:hidden" name="noneFlag" id="noneFlag" value=""/>
		</td>
		</tr>
		
</table>
</div>
</form>
</body>
<script type="text/javascript">
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

var noneFlag="0";

function addrow(){
        //得到table对象
       var mytable=document.getElementById("doc");
       
       //如果风险选择的是"请选择",那么就不能添加
       if(document.getElementById("riskname").value == "none1")
       {
       		alert("不能添加");
       		return;
       }
       
       //如果风险已经添加了就不能重复添加
       var risklist = document.getElementsByName("riskId");
       for(i = 0; i < risklist.length; i++)
	   { 
			if(risklist[i].value == document.getElementById("riskname").value)
			{ 
				alert("该风险已经添加");
				return;
			};
		};
       
       //向table中插入一行
       var index = mytable.rows.length;
       var mytr=mytable.insertRow(index);
       mytr.onclick=function(){getIndex(this.rowIndex);};//得到所在的行号，通过getIndex（），将值赋给trIndex
   
       //循环生成一行中多个td对象
       var input=new Array();
       //下面这行，value的赋值可能有错 visibility:hidden
      
       //input[0]=document.createElement("<INPUT type='text' name='type' align='right' style='width:17px;border:0px;text-align:right;'>" );
       input[0]=document.createElement("<input type='text' name='riskId' style='width:5px;visibility:hidden;'/>");
       input[1]=document.createElement("<input type='text' name='riskName' style='width:150px;border:0px;text-align:center;'  />");    
       input[2]=document.createElement("<img title='删除风险' src='IconPage/images/close.png' align='left' style='cursor:pointer' onClick='delete1()'/>");      
       //input[3].onclick=function(){delete1();}   
       for(i=0;i<3;i++)
       {
    	   mytd=document.createElement("td");
    	       if(0==i)
       		    mytd.style.width="37%";
    	       else if(1==i) mytd.style.width="15%";
    	       else mytd.style.width="48%";
       		/*if(i!=3) {
       			input[i].setAttribute("type","text");    
       		}*/
       		 //alert(input[0]);
       		mytd.appendChild(input[i]);
       		mytr.appendChild(mytd);
       }
	   //alert(document.all.riskname.options[document.all.riskname.selectedIndex].text+index);
       if(0==index){
       		//document.all.type.value=1+index;
       		document.all.riskId.value=document.getElementById("riskname").value;
            document.all.riskName.value=document.all.riskname.options[document.all.riskname.selectedIndex].text;           
       }else{
       		//document.all.type(index).value=1+index;
       		document.all.riskId(index).value=document.getElementById("riskname").value;
       	    document.all.riskName(index).value=document.all.riskname.options[document.all.riskname.selectedIndex].text;    	    
       }
      // riskname.options[riskname.selectedIndex].text;//document.getElementById("riskname").text;
}

var trIndex=null;
//得到trIndex,用于delete操作，addRow（）中使用
function getIndex(x)
{
    trIndex=x;
}
//页面中新增风险的删除
function delete1(){
	    //得到table对象
		var mytable = document.getElementById("doc");
	    //得到table中的行
	  	if(trIndex==null)
	 		return "";
	 	else
	  	{
	    	mytable.deleteRow(trIndex);
	    	trIndex=null;
	    	//num--，此处并未实现，必须对删除后的所有行序号减1才正确
	  	}
}
//点击“修改”进入页面，对原来存在的风险的删除
function delete2(row){
	    //得到table对象
		//alert(row);
	var mytable = document.getElementById("doc");
	mytable.deleteRow(row);
}

function show(){
	//var mytable=document.getElementById("doc");
       //向table中插入一行
    //var index = mytable.rows.length;
	//alert(index);    
	/*if(null==document.getElementsByName("riskId")){
		alert("riskId1全部删除");
		noneFlag="1";
		document.getElementById("noneFlag").value=noneFlag;
	}
	else alert("riskId1未部删除");*/
	
	if(null==document.all.item("riskId")){
		//alert("riskId全部删除");
		noneFlag="1";
		document.getElementById("noneFlag").value=noneFlag;
	}
	else{
		//alert("riskId未全部删除");
		noneFlag="0";
		document.getElementById("noneFlag").value=noneFlag;
	}
}

function changeFileType()
{
	var filetype = $("#fileTypeString option:selected").val();//选中的值
	//alert(filetype);
	document.getElementById("fileType").value = fileType;
}
</SCRIPT>
</html>