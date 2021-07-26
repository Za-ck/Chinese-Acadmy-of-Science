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
    
    <title>风险信息添加</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<script language=javascript src="/RiskEvent/js/windows.js"></script>
<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
<script src="/RiskEvent/js/validation-framework.js" type="text/javascript"></script>
  <script src="/RiskEvent/js/jquery-1.7.1.js" type="text/javascript"></script>
  <script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript" ></script> 
</head>
  
<body>
<form action="dataUnit/RisAddUpdateAction" method="post" enctype="multipart/form-data" name="risAddUpdateForm" id="risAddUpdateForm" onSubmit="return checksub()">
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle" align="center"><span>风险信息新增修改</span></div></div>
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr >
    <td class="CPanel">	
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">	
		<tr>
			<td width="100%">			
				<fieldset style="height:100%;">
				<legend>风险信息</legend>
				 <div id="errorDiv" style="color:red;font-weight:bold;font-size:12px"></div>
					  <table class="contentInfo" border="0" cellpadding="2" cellspacing="1" style="width:100%" >
					  <tr>
					    <td width="35%" nowrap align="right">一级风险名称：</td>
					    <td width="65%"><span class="red">
						   <s:select id="riskTypeId" name="riskTypeId" style="width:154px" theme="simple" list="allrtList" listKey="rtId" listValue="rtName" onchange="getRiskNum()">		
						   </s:select>*</span>
					</td>
					  </tr>
					  <tr><td height="10"></td></tr>
					  <tr>
					    <td nowrap align="right" width="35%">风险数字编号：</td>
					    <td width="65%">
					       <input name="riskNumId" type="text" class="text" style="width:400px" onFocus="this.blur()" value="<s:property value='riskNumId'/>"/><span class="red">*</span>(自动生成)
						</td>
					  </tr>
					  <tr><td height="10"></td></tr>					 
					  <tr>
					    <td nowrap align="right" width="35%">二级风险名称：</td>
					    <td width="65%"><span class="red">
					       <input name="riskName" type="text" class="text" style="width:400px" value="<s:property value='riskName'/>"/>*</span>
						</td>
					  </tr>
					  <tr><td height="10"></td></tr>
					  <tr>
						 <td width="35%" nowrap align="right">归口部门：</td>
					    <td width="65%">
							<!--
					       <input name="riskDep" type="text" class="text" style="width:154px" value="<s:property value='riskDep'/>"/>
						   -->
						   <s:select id="riskDep" name="riskDep" theme="simple" list="riskdepList" listValue="depName" listKey="depName" class="text" style="width:154px" ></s:select>
						<span class="red">*</span>
						</td>
					  </tr>
					  <tr>
					 <td  width="35%" nowrap align="right">年份：</td>
     					<td width="60"> 
     					 <input name="year" id="year" style="width:90px;" class="Wdate" value="<s:property value="year"/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy'})"/>
     					</td>
     					</tr>
					  <tr>
					  
					   <td width="35%" nowrap align="right"></td>
					    <td width="65%">
					     <div id="addshow">
					  <input type="button" name="addflow" value="保存并新增修改风险流转策略" onClick="addriskFlow()"/>
					    </div>
					  </td>
					  </tr>
					
					   <tr>
					   <td>
					    <input name="riskQueue" type="hidden"  value="<s:property value='riskQueue'/>"/>
					    </td>
					     </tr>
					  </table>
				</fieldset>				</td>
		</tr>
		</table>	 </td>
  </tr>
		<tr  >
			<td colspan="2" align="center" height="50px">
				<!-- <input type="submit" name="saverisk" value="" class="save" /> -->　
				<input type="button" name="backrisk" value="" class="back" onClick="javascript:history.back(-1);">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
			</td>
		</tr>
		<tr bgcolor="#F8FCFC" ><td colspan="2" align="left"><input type="text" style="visibility:hidden" name="updateFlag" id="updateFlag" value="<s:property value='updateFlag'/>"/></td></tr>
		<tr bgcolor="#F8FCFC"><td align="left"><input type="text" style="visibility:hidden" name="current_pagenum" id="current_pagenum" value="<s:property value='current_pagenum'/>"/></td>
		<tr bgcolor="#F8FCFC" ><td colspan="2" align="left"><input type="text" style="visibility:hidden" name="riskId" id="riskId" value="<s:property value='riskId'/>"/></td></tr>
		
</table>
</div>
</form>

<script type="text/javascript">
if("update"==document.getElementById("updateFlag").value)
{
  document.getElementById("riskTypeId").disabled='true';
  document.getElementById("year").disabled='true';
}

function checksub()
{
if(doValidate('risAddUpdateForm'))
{
 if(document.getElementById('riskTypeId').value=="none1")
{alert("请选择一级风险名称");
return false;}
else if(document.getElementById('riskDep').value=="--请选择--")
{alert("请选择归口部门");
return false;}
else
{return true;}
}
else 
{return false;}
}

function getRiskNum()
{
var tempname=document.getElementById("riskTypeId").value;
          $.ajax({
               url: 'default/ajaxRiskNumAction?temprisktypeid='  + tempname+'&tmpnum='+ Math.random().toString(),
               type: 'post',
               dateType: 'json',
               data: '',
               contentType: 'text/html;charset=utf-8',
               success: function (data) {     
              var data1 = eval("("+ data+")");
              var ObjTarget=document.getElementById("riskNumId");
              ObjTarget.innerText=data1;
               },
               error: function () {
               alert("error");
           }
       });
}

function addriskFlow()
{
if(checksub()){
document.risAddUpdateForm.action="dataUnit/addriskFlow2";
document.risAddUpdateForm.submit();
}
else 
{return false;}
}
</script>
</body>
</html>