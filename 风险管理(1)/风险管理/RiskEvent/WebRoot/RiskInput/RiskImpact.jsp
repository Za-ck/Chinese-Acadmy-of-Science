<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>  
    <base href="<%=basePath%>"/>
    
    <title>风险影响描述</title>
	<meta http-equiv="Expires" content="0"> 
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">   
	<meta http-equiv="X-UA-Compatible"content="IE=7" /><!--以IE7模式渲染-->
	
<link href="/RiskEvent/js/Scroll/scroll.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="/RiskEvent/js/Scroll/scroll.js"></script>
	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
     <script src="/RiskEvent/js/jquery-1.7.1.js" type="text/javascript" charset="utf-8"></script>
     <script language=javascript src="/RiskEvent/js/windows.js"></script>
     <script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
<style>
 textarea{
 width:99%;
  overflow:auto;
  border:1px solid #71B5F9;
 }
 .contentInfo tr td{
    border-bottom:1px solid #d2d2d2;
    border-left:1px solid #d2d2d2;
    border-right:1px solid #d2d2d2;
 }
 
</style>
</head>
  
 <body>
<form id="riskImpactInput" name="riskImpactInput" action="riskInput/ImpactAddUpdateAction" method="post" enctype="multipart/form-data" onsubmit="subPre();return doValidate('riskImpactInput')" >
  <div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle" align="left"><span>风险影响描述</span></div></div>
<div class="MainDiv">
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="CContent" >
<tr>
    <td class="CPanel">
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%" >
		<tr>
			<td width="100%">			
				<fieldset style="height:100%;">
				<legend>风险影响信息</legend>
				 <div id="errorDiv" style="color:red;font-weight:bold;font-size:12px"></div>
					 <table border="0" class="contentInfo" cellpadding="2" cellspacing="0" style="width:100%" >
					 	    
					  <tr>
					    <td width="14%" style="border-top:1px solid #d2d2d2;"  align="right" >风险事件编号：</td>
                        <td width="15%" style="border-top:1px solid #d2d2d2;"><span class="red">
				        <input name="idimpact" id="idimpact" type="text" class="text" readonly="readonly" style="width:154px" value="<s:property value='idimpact'/>"/>*</span></td>
					    <td width="14%" style="border-top:1px solid #d2d2d2;" align="right">归口部门：</td>
					    <td width="14%" style="border-top:1px solid #d2d2d2;" ><span class="red">
		        	   <input name="depname" id="depname" type="text" class="text" style="width:154px" readonly="readonly" value="<s:property value='depname'/>"/>*</span></td>
					    <td width="19%" style="border-top:1px solid #d2d2d2;" align="right">风险来源：</td>
					    <td width="24%" style="border-top:1px solid #d2d2d2;" >
						<s:select name="source" id="source" list="{'院内','院外'}" theme="simple" width="150px"></s:select>
						<span class="red">*</span></td>
					  </tr>
					
					  <tr>
					    <td   align="right">影响的关键绩效指标：</td>
				        <td colspan="5">
                        <textarea title="风险事件的发生可能直接影响到我院关键绩效指标，该项内容可作为确定重大风险偏好与承受度指标的依据" name="kpi" id="kpi" cols="80" rows="5"><s:property value="kpi"/></textarea><span class="red">*</span></td>
					  </tr>
					  <tr>
					    	<td rowspan="3" align="right"  >发生可能性：</td>
				        	<td  rowspan="3">程度：<br /><input title="请从右边选择" type="text" name="prodegree" id="prodegree" style="width: 40px;height: 25px" value="<s:property value='prodegree'/>" readonly="readonly"/><span class="red">*</span></td>
							<td align="right">发生概率：</td>
							<td colspan="3">
					       		<s:select name="proProbability"  id="proProbability" theme="simple" list="proList" listValue="proProbability" listKey="proId" class="text" style="width:650px" onchange="proChange('proProbability')"></s:select>							</td>
						
					  	</tr>
						<tr>
					  		<td align="right">针对大型灾害/事件类：</td>
							<td colspan="3">
					       		<s:select name="proDisasterEvent" id="proDisasterEvent" theme="simple" list="proList" listValue="proDisasterEvent" listKey="proId" class="text" style="width:650px" onchange="proChange('proDisasterEvent')"></s:select>							</td>
						</tr>
<tr>					  		<td align="right">针对日常运营：</td>
						  <td colspan="3">
					       		<s:select name="proDailyOperation" id="proDailyOperation" theme="simple" list="proList" listValue="proDailyOperation" listKey="proId" class="text" style="width:650px" onchange="proChange('proDailyOperation')"></s:select></td>					  	</tr>
					
					  				    
                      <tr>
					    <td   align="right">影响的业务领域：</td>
				        <td colspan="5">
                        <textarea title="请输入影响的业务领域" name="busarea" id="busarea" cols="80" rows="5"><s:property value="busarea"/></textarea><span class="red">*</span></td>
					  </tr>
                   <tr>
					    <td   align="right">涉及体系文件：</td>
				        <td colspan="3">
							 <textarea name="allFileName" id="allFileName" cols="50" rows="7"  readonly="readonly"><s:property value="allFileName"/></textarea><span class="red"></span></td>
						<td>
                        	<input type="button"  name="add1" id="add1" value="添加三标体系文件" onclick="selectFile1('1')" /><br/>
							<input type="button"  name="add3" id="add3" value="添加管理标准文件" onclick="selectFile1('2')" /><br/>	
							<input type="button"  name="add5" id="add5" value="添加工作标准文件" onclick="selectFile1('3')" /><br/>
							<input type="button"  name="add7" id="add7" value="添加应急预案文件" onclick="selectFile1('4')" /><br/>											     
							<input type="button"  name="add2" id="add2" value="删减文件" onclick="selectFile2()"/>
						</td>
						<td >
                        	<input name="allFileId" id="allFileId" style="visibility:hidden;" type="text" class="text"  value="<s:property value='allFileId'/>"/>	
						</td>
					  </tr>
					  <tr>
					    <td   align="right">涉及内控文件：</td>
				        <td colspan="3">
							 <textarea name="allflowFileName" id="allflowFileName" cols="50" rows="3"  readonly="readonly"><s:property value="allflowFileName"/></textarea><span class="red"></span></td>
						<td>
                        	<input type="button"  name="addflow" id="addflow" value="添加文件" onclick="selectFile3()" /><br/>
                        	<!-- input type="button"  name="addflow" id="addflow" value="添加文件" onclick="selectFile1('5')" /><br/>-->
							<input type="button"  name="deleteflow" id="deleteflow" value="删减文件" onclick="deleteFile()"/>
						</td>
						<td >
                        	<input name="allflowFileId" id="allflowFileId" style="visibility:hidden;" type="text" class="text"  value="<s:property value='allflowFileId'/>"/>	
						</td>
					  </tr>
                   <tr>
					    	<td rowspan="5" align="right"  >对财务影响：</td>
			        	<td  rowspan="4">影响程度：<br /><input title="请从右边选择" type="text" name="financedegree" id="financedegree" style="width: 40px;height: 25px" value="<s:property value='financedegree'/>" readonly="readonly"/><span class="red">*</span></td>
							<td align="right">净资产：</td>
							<td colspan="3" >
					       		<s:select name="finAsset" id="finAsset" theme="simple" list="finList" listValue="finAsset" listKey="finId" class="text" style="width:650px" onchange="finChange('finAsset')"></s:select>							</td>
				  	   </tr>
						<tr>
					  		<td align="right">营业收入：</td>
							<td colspan="3" >
					       		<s:select name="finIncome" id="finIncome" theme="simple" list="finList" listValue="finIncome" listKey="finId" class="text" style="width:650px" onchange="finChange('finIncome')"></s:select>							</td>
						</tr>
<tr>					  		<td  align="right"> 利润总额：</td>
						  <td colspan="3">
					       		<s:select name="finProfit" id="finProfit" theme="simple" list="finList" listValue="finProfit" listKey="finId" class="text" style="width:650px" onchange="finChange('finProfit')"></s:select></td>					  	</tr>
					    <tr>
					  		<td  align="right">资产总额：</td>
							<td colspan="3">
					       		<s:select name="finProperty" id="finProperty" theme="simple" list="finList" listValue="finProperty" listKey="finId" class="text" style="width:650px" onchange="finChange('finProperty')"></s:select>							</td>					  	</tr>
                      	<tr>
                        	<td>补充描述：</td>
                        	<td colspan="4"><textarea title="请输入对财务影响的补充描述" name="finance" id="finance" cols="60" rows="4"><s:property value="finance"/></textarea></td>
                       	</tr> 
                       	
                     	<tr>
					    	<td rowspan="5" align="right"  >对我院声誉影响：</td>
				        	<td  rowspan="4">影响程度：<br /><input title="请从右边选择"  type="text" name="famedegree" id="famedegree" style="width: 40px;height: 25px" value="<s:property value='famedegree'/>" readonly="readonly"/><span class="red">*</span></td>
							<td align="right">对企业声誉：</td>
							<td colspan="3">
					       		<s:select name="repCom" id="repCom" theme="simple" list="repList" listValue="repDetail" listKey="repId" class="text" style="width:650px" onchange="repChange('repCom')"></s:select>							</td>
						
					  	</tr>
						<tr>
					  		<td align="right">对监管机构/上级单位：</td>
							<td  colspan="3">
					       		<s:select name="repSup" id="repSup" theme="simple" list="repList" listValue="repSuperior" listKey="repId" class="text" style="width:650px" onchange="repChange('repSup')"></s:select>							</td>
						</tr>
<tr>					  		<td align="right"> 对合作伙伴：</td>
						  <td  colspan="3">
					       		<s:select name="repPar" id="repPar" theme="simple" list="repList" listValue="repPartner" listKey="repId" class="text" style="width:650px" onchange="repChange('repPar')"></s:select></td>					  	</tr>
					    <tr>
					  		<td align="right">对员工/公众：</td>
							<td  colspan="3">
					       		<s:select name="repPub" id="repPub" theme="simple" list="repList" listValue="repPublic" listKey="repId" class="text" style="width:650px" onchange="repChange('repPub')"></s:select>							</td>					  	</tr>
                      	<tr>
                        	<td>补充描述：</td>
                        	<td colspan="4"><textarea title="请输入对我院声誉影响的补充描述" name="fame" id="fame" cols="60" rows="4"><s:property value="fame"/></textarea></td>
                       	</tr> 
						
					   <tr>
					    <td rowspan="2" align="right"  >对法律法规影响：</td>
				        <td >影响程度：</td>
					    <td  colspan="2"><span class="red">
                        	<s:select name="lawdegree" id="lawdegree" theme="simple" list="lawList" listValue="lawLevel" listKey="lawId" class="text" style="width:100px" onchange="getLawData()"></s:select>             
                        *</span></td>
					    <td  colspan="2"><textarea name="lawshow" id="lawshow" cols="30" rows="5" style="width:97%"></textarea></td>
                      </tr>
                      <tr>
                        <td>补充描述：</td>
                        <td colspan="4"><textarea title="请输入对法律法规影响的补充描述" name="law" id="law" cols="60" rows="5"><s:property value="law"/></textarea></td>
                       </tr>
					   <tr>
					    <td rowspan="2" align="right"  >对客户关系影响：</td>
				        <td >影响程度：</td>
					    <td colspan="2"><span class="red">
                        	<s:select name="clientdegree" id="clientdegree" theme="simple" list="cliList" listValue="cliLevel" listKey="cliId" class="text" style="width:100px" onchange="getCliData()"></s:select>             
                        *</span></td>
					    <td colspan="2"><textarea id="clientshow" name="clientshow" cols="30" rows="5" style="width:97%"></textarea></td>
                      </tr>
                      <tr>
                        <td>补充描述：</td>
                        <td colspan="4"><textarea title="请输入对客户关系影响的补充描述" id="client" name="client" cols="60" rows="5"><s:property value="client"/></textarea></td>
                       </tr>
					   <tr>
					    <td rowspan="2" align="right"  >对员工满意度影响：</td>
				        <td >影响程度：</td>
					    <td colspan="2"><span class="red">
                        	<s:select id="employeedegree" name="employeedegree" theme="simple" list="empList" listValue="empLevel" listKey="empId" class="text" style="width:100px" onchange="getEmpData()"></s:select>             
                        *</span></td>
					    <td colspan="2"><textarea id="employeeshow" name="employeeshow" cols="30" rows="5" style="width:97%"></textarea></td>
                      </tr>
                      <tr>
                        <td>补充描述：</td>
                        <td colspan="4"><textarea title="请输入对员工满意度影响的补充描述" id="employee" name="employee" cols="60" rows="5"><s:property value="employee"/></textarea></td>
                       </tr>
					   
					   <tr>
					    <td rowspan="2" align="right"  >对运营影响：</td>
				        <td >影响程度：</td>
					    <td colspan="2"><span class="red">
                        	<s:select id="operationdegree" name="operationdegree" theme="simple" list="opeList" listValue="opeLevel" listKey="opeId" class="text" style="width:100px" onchange="getOpeData()"></s:select>             
                        *</span></td>
					    <td colspan="2"> <textarea id="operationshow" name="operationshow" cols="30" rows="5" style="width:97%"></textarea></td>
                      </tr>
                      <tr>
                        <td>补充描述：</td>
                        <td colspan="4"><textarea title="请输入对运营影响的补充描述" id="operation" name="operation" cols="60" rows="5"><s:property value="operation"/></textarea></td>
                       </tr>
					   
					   <tr>
					    	<td rowspan="3" align="right"  >对人员健康环保影响：</td>
				        	<td  rowspan="2">影响程度：<br /><input title="请从右边选择" type="text" id="safedegree" name="safedegree" style="width: 40px;height: 25px" value="<s:property value='safedegree'/>" readonly="readonly"/><span class="red">*</span></td>
							<td align="right">安全事故：</td>
							<td colspan="3">
					       		<s:select id="safSafety" name="safSafety" theme="simple" list="safList" listValue="safSafety" listKey="safId" class="text" style="width:650px" onchange="safChange('safSafety')"></s:select>							</td>
						
					  	</tr>
						<tr>
					  		<td align="right">环境：</td>
							<td colspan="3">
					       		<s:select id="safEnvironment" name="safEnvironment" theme="simple" list="safList" listValue="safEnvironment" listKey="safId" class="text" style="width:650px" onchange="safChange('safEnvironment')"></s:select>							</td>
						</tr>
                      	<tr>
                        	<td>补充描述：</td>
                        	<td colspan="4"><textarea title="请输入对人员健康环保影响的补充描述" id="safe" name="safe" cols="60" rows="4"><s:property value="safe"/></textarea></td>
                       	</tr> 
					   
					  </table>
					  <input type="text" style="visibility:hidden" name="updateFlag" id="updateFlag" value="<s:property value='riskeventid'/>"/>
				</fieldset>				
			</td>
		</tr>
		</table>	 
	</td>
  </tr>
		
		<tr >
			<td colspan="6" align="center" height="50px">
                <input type="button" name="returnrisk" value="上一页"  class="button" onclick="window.location.href='/RiskEvent/riskInput/getRiskEventAction?id=<s:property value="idimpact"/>&updateFlag=update'"/>
                <input type="button" id="save" name="save"       value="保存"   class="button" onclick="subPre();return doValidate('riskImpactInput');this.form.action='riskInput/ImpactAddUpdateSaveAction';alert('保存成功')this.form.submit()"/>
				<input type="submit" name="nextpage"   value="下一页"  class="button"  />　  		   		
			</td>
		</tr>
		 <tr ><td colspan="6"><input type="text" style="visibility:hidden" name="updateFlag" id="updateFlag" value="<s:property value='proProbability'/>"/></td></tr>

</table>
</div>
  <div style="DISPLAY:none" id=goTopBtn><img border=0 title="返回顶部" src="/RiskEvent/js/Scroll/top.jpg"/></div>
  <div style="DISPLAY:none" id=goBottomBtn><img border=0 title="置底" src="/RiskEvent/js/Scroll/down.jpg"/></div>
<script type=text/javascript>goTopEx();
</script>
</form>
<script type="text/javascript">
$("#save").click(function(){
	subPre();
	
	var idimpact = $('#idimpact').val();
	var depname = $('#depname').val();
	var source = $('#source option:selected').val();
	var kpi = $('#kpi').val();
	var prodegree = $('#prodegree').val();
	var proProbability = $('#proProbability option:selected').val();
	var proDisasterEvent = $('#proDisasterEvent option:selected').val();
	var proDailyOperation = $('#proDailyOperation option:selected').val();
	var busarea= $('#busarea').val();
	var allFileName = $('#allFileName').val();
	var allFileId = $('#allFileId').val();
	var allflowFileName = $('#allflowFileName').val();
	var allflowFileId = $('allflowFileId').val();
	var financedegree = $('financedegree').val();
	var finAsset = $('#finAsset option:selected').val();
	var finIncome = $('#finIncome option:selected').val();
	var finProfit = $('#finProfit option:selected').val();
	var finProperty = $('#finProperty option:selected').val();
	var finance = $('finance').val();
	var famedegree = $('famedegree').val();
	var repCom = $('#repCom option:selected').val();
	var repSup = $('#repSup option:selected').val();
	var repPar = $('#repPar option:selected').val();
	var repPub = $('#repPub option:selected').val();
	var fame = $('fame').val();
	var lawdegree = $('#lawdegree option:selected').val();
	var lawshow = $('#lawshow').val();
	var law = $('#law').val();
	var clientdegree=$('#clientdegree option:selected').val();
	var clientshow=$('#clientshow').val();
	var client=$('#client').val();
	var employeedegree=$('#employeedegree option:selected').val();
	var employeeshow= $('#employeeshow').val();
	var employee= $('#employee').val();
	var operationdegree=$('#operationdegree option:selected').val();
	var operationshow=$('#operationshow').val();
	var operation= $('#operation').val();
	var safedegree=$('#safedegree').val();
	var safSafety=$('#safSafety option:selected').val();
	var safEnvironment=$('#safEnvironment option:selected').val();
	var safe=$('#safe').val();
	var updateFlag=$('#updateFlag').val();
	if(doValidate('riskImpactInput')){
		 $.ajax({
               url: 'riskInput/ImpactAddUpdateAction'
               		/* ?idimpact='+idimpact+'&depname='+depname+'&source='+source+'&kpi='+kpi+'&prodegree='+prodegree+
               		'&proProbability='+proProbability+'&proDisasterEvent='+proDisasterEvent+'&proDailyOperation='+proDailyOperation+'&busarea='+busarea+'&allFileName='+allFileName+
               		'&allFileId='+allFileId+'&allflowFileName='+allflowFileName+'&allflowFileId='+allflowFileId+'&financedegree='+financedegree+'&finAsset='+finAsset+
               		'&finIncome='+finIncome+'&finProfit='+finProfit+'&finProperty='+finProperty+'&finance='+finance+'&famedegree='+famedegree+
               		'&repCom='+repCom+'&repSup='+repSup+'&repPar='+repPar+'&repPub='+repPub+'&fame='+fame+
               		'&lawdegree='+lawdegree+'&lawshow='+lawshow+'&law='+law+'&clientdegree='+clientdegree+'&clientshow='+clientshow+
               		'&client='+client+'&employeedegree='+employeedegree+'&employeeshow='+employeeshow+'&employee='+employee+'&operationdegree='+operationdegree+
               		'&operationshow='+operationshow+'&operation='+operation+'&safedegree='+safedegree+'&safSafety='+safSafety+'&safEnvironment='+safEnvironment+
               		'&safe='+safe+'&updateFlag='+updateFlag */,
               type: 'post',
               dateType: 'json',
               data:{'idimpact':idimpact,'depname':depname,'source':source,'kpi':kpi,'prodegree':prodegree,'proProbability':proProbability,'proDisasterEvent':proDisasterEvent,'proDailyOperation':proDailyOperation,'busarea':busarea,'allFileName':allFileName,'allFileId':allFileId,'allflowFileName':allflowFileName,'allflowFileId':allflowFileId,'financedegree':financedegree,'finAsset':finAsset,'finIncome':finIncome,'finProfit':finProfit,'finProperty':finProperty,'finance':finance,'famedegree':famedegree,'repCom':repCom,'repSup':repSup,'repPar':repPar,'repPub':repPub,'fame':fame,'lawdegree':lawdegree,'lawshow':lawshow,'law':law,'clientdegree':clientdegree,'clientshow':clientshow,'client':client,'employeedegree':employeedegree,'employeeshow':employeeshow,'employee':employee,'operationdegree':operationdegree,'operationshow':operationshow,'operation':operation,'safedegree':safedegree,'safSafety':safSafety,'safEnvironment':safEnvironment,'safe':safe,'updateFlag':updateFlag,'allFileId':allFileId},
               contentType: 'application/x-www-form-urlencoded',
               success: function (data) {  
               	alert('保存成功');   
		 		}
		 });
	} 
});


var allFileId1="";
var allFileName1="";
var allflowFileId1="";
var allflowFileName1="";
/*//影响程度最高的数值，如1、2、3、4、5
var num1=0;
//影响程度第二高的数值，如1、2、3、4、5
var num2=0;
//影响程度最高的数值，如“对企业声誉”、“对监管机构/上级单位”、“对合作伙伴”、“对员工/公众”
var tar1="";
var tar2="";*/
//我院声誉影响
var num=0;
var tar="";
//发生可能性
var numPro=0;
var tarPro="";
//财务影响
var numFin=0;
var tarFin="";
//人员健康环保影响
var numSaf=0;
var tarSaf="";

function returnback(){
	riskImpactInput.action="riskInput/getRiskEventAction";
	riskImpactInput.submit();
}

function getFinData(){
	var tempname=document.getElementById("finId").value;
	$.ajax({
		url: 'default/ajaxFinAction?finId=' + tempname+'&tmpnum='+ Math.random().toString(),
        type: 'post',
        dateType: 'json',
        data: '',
        contentType: 'text/html;charset=utf-8',
        success: function (data) {  
			var data1 = eval("("+ data+")");
            var ObjTarget=document.getElementById("financeshow");
            ObjTarget.innerText=data1.finDetail+"\n"+data1.finAsset+"\n"+data1.finIncome+"\n"+data1.finProfit+"\n"+data1.finProperty;
		},
		error: function () { 
               alert("error");
        }   
	});
}

function getProData(){
	var tempname=document.getElementById("proId").value;
	$.ajax({
		url: 'default/ajaxProAction?proId=' + tempname+'&tmpnum='+ Math.random().toString(),
        type: 'post',
        dateType: 'json',
        data: '',
        contentType: 'text/html;charset=utf-8',
        success: function (data) {  
			var data1 = eval("("+ data+")");
            var ObjTarget=document.getElementById("proshow");
            ObjTarget.innerText=data1.proLevel+"\n"+data1.proProbability+"\n"+data1.proDisasterEvent+"\n"+data1.proDailyOperation;
		},
		error: function () { 
               alert("error");
        }   
	});
}

function getRepData(){
	var tempname=document.getElementById("repId").value;
	$.ajax({
		url: 'default/ajaxRepAction?repId=' + tempname+'&tmpnum='+ Math.random().toString(),
        type: 'post',
        dateType: 'json',
        data: '',
        contentType: 'text/html;charset=utf-8',
        success: function (data) {  
			var data1 = eval("("+ data+")");
            var ObjTarget=document.getElementById("fameshow");
            ObjTarget.innerText=data1.repLevel+"\n"+data1.repDetail+"\n"+data1.repSuperior+"\n"+data1.repPartner+"\n"+data1.repPublic;
		},
		error: function () { 
               alert("error");
        }   
	});
}

function getLawData(){
	var tempname=document.getElementById("lawdegree").value;
	if(0==tempname){
		document.getElementById("lawshow").innerText="";
	}
	else{
	$.ajax({
		url: 'default/ajaxLawAction?lawId=' + tempname+'&tmpnum='+ Math.random().toString(),
        type: 'post',
        dateType: 'json',
        data: '',
        contentType: 'text/html;charset=utf-8',
        success: function (data) {  
			var data1 = eval("("+ data+")");
            var ObjTarget=document.getElementById("lawshow");
            ObjTarget.innerText="影响程度："+data1.lawLevel+"\n"+"影响描述："+data1.lawDetail+"\n"+"                                                                                                                                                                                                                                                                   "+"                                                                                                                                                                                                                                                                   ";
		},
		error: function () { 
               alert("error");
        }   
	});
	}
}

function getCliData(){
	var tempname=document.getElementById("clientdegree").value;
	if(0==tempname){
		document.getElementById("clientshow").innerText="";
	}
	else{
	$.ajax({
		url: 'default/ajaxCliAction?cliId=' + tempname+'&tmpnum='+ Math.random().toString(),
        type: 'post',
        dateType: 'json',
        data: '',
        contentType: 'text/html;charset=utf-8',
        success: function (data) {  
			var data1 = eval("("+ data+")");
            var ObjTarget=document.getElementById("clientshow");
            ObjTarget.innerText="影响程度："+data1.cliLevel+"\n"+"影响描述："+data1.cliDetail+"\n"+"                                                                                      "+"                                                                                                                                                                                                                                                                   ";
		},
		error: function () { 
               alert("error");
        }   
	});
	}
}

function getEmpData(){
	var tempname=document.getElementById("employeedegree").value;
	if(0==tempname){
		document.getElementById("employeeshow").innerText="";
	}
	else{
	$.ajax({
		url: 'default/ajaxEmpAction?empId=' + tempname+'&tmpnum='+ Math.random().toString(),
        type: 'post',
        dateType: 'json',
        data: '',
        contentType: 'text/html;charset=utf-8',
        success: function (data) {  
			var data1 = eval("("+ data+")");
            var ObjTarget=document.getElementById("employeeshow");
            ObjTarget.innerText="影响程度："+data1.empLevel+"\n"+"影响描述："+data1.empDetail+"\n"+"                                                                                      "+"                                                                                                                                                                                                                                                                   ";
		},
		error: function () { 
               alert("error");
        }   
	});
	}
}

function getOpeData(){
	var tempname=document.getElementById("operationdegree").value;
	if(0==tempname){
		document.getElementById("operationshow").innerText="";
	}
	else{
	$.ajax({
		url: 'default/ajaxOpeAction?opeId=' + tempname+'&tmpnum='+ Math.random().toString(),
        type: 'post',
        dateType: 'json',
        data: '',
        contentType: 'text/html;charset=utf-8',
        success: function (data) {  
			var data1 = eval("("+ data+")");
            var ObjTarget=document.getElementById("operationshow");
            ObjTarget.innerText="影响程度："+data1.opeLevel+"\n"+"影响描述："+data1.opeDetail+"\n"+"                                                                                      "+"                                                                                                                                                                                                                                                                   ";
		},
		error: function () { 
               alert("error");
        }   
	});
	}
}

function getSafData(){
	var tempname=document.getElementById("safId").value;
	$.ajax({
		url:'default/ajaxSafAction?safId=' + tempname+'&tmpnum='+ Math.random().toString(),
        type: 'post',
        dateType: 'json',
        data: '',
        contentType: 'text/html;charset=utf-8',
        success: function (data) {  
			var data1 = eval("("+ data+")");
            var ObjTarget=document.getElementById("safeshow");
            ObjTarget.innerText=data1.safLevel+"\n"+data1.safSafety+"\n"+data1.safEnvironment;
		},
		error: function () { 
               alert("error");
        }   
	});
}

/*function selectFile(){
	var tempname=document.getElementById("idimpact").value;
	//var f=document.getElementById("allFile").value;
	/*var allFile1=window.showModalDialog("riskInput/ImpFileAction?reId="+tempname+"&"+Math.random().toString(),null,"status:false;dialogWidth:350px;dialogHeight:400px;resizable:yes;scroll:yes");*/
	/*showModelessDialog("riskInput/ImpFileAction?reId="+tempname,window,"status:false;dialogWidth:351px;dialogHeight:600px;resizable:yes;scroll:yes");
	//window.location.reload();
	//return allFile1;
}*/
//添加体系文件
function selectFile1(param){
	var allFileId=document.getElementById("allFileId").value;
	var allFileName=document.getElementById("allFileName").value;
	var idimpact = document.getElementById("idimpact").value;
	allFileName=encodeURI(encodeURI(allFileName));//为避免乱码，对有中文的字符串进行加码
	var fileNameString = '';
	showModelessDialog("riskInput/ImpFileAction1?allFileId="+allFileId+"&allFileName="+allFileName+"&filetype="+param+"&idimpact="+idimpact+"&fileNameString="+fileNameString,window,"status:false;dialogWidth:600px;dialogHeight:490px;resizable:yes;scroll:yes");
   
}
//删减体系文件
function selectFile2(){
	var allFileId=document.getElementById("allFileId").value;
	showModelessDialog("riskInput/ImpFileAction2?allFileId="+allFileId,window,"status:false;dialogWidth:600px;dialogHeight:470px;resizable:yes;scroll:yes");
}
//添加内控文件
function selectFile3(){
	var allflowFileId=document.getElementById("allflowFileId").value;
	var allflowFileName=document.getElementById("allflowFileName").value;
	var idimpact = document.getElementById("idimpact").value;
	allflowFileName=encodeURI(encodeURI(allflowFileName));//为避免乱码，对有中文的字符串进行加码
	showModelessDialog("riskInput/addFlowFile?allflowFileId="+allflowFileId+"&allflowFileName="+allflowFileName+"&idimpact="+idimpact,window,"status:false;dialogWidth:600px;dialogHeight:490px;resizable:yes;scroll:yes");
}
function deleteFile(){
	var allflowFileId=document.getElementById("allflowFileId").value;
	showModelessDialog("riskInput/deleteFlowFile?allflowFileId="+allflowFileId,window,"status:false;dialogWidth:600px;dialogHeight:470px;resizable:yes;scroll:yes");
}
function update(){
	document.getElementById("allFileId").value=allFileId1;
	//alert(allFileId1);
	//document.getElementById("allFileName").innerText=allFileName1;
	document.getElementById("allFileName").value=allFileName1;
}
function updateflow(){
	document.getElementById("allflowFileId").value=allflowFileId1;
	document.getElementById("allflowFileName").value=allflowFileName1;
}

//我院声誉影响
function repChange(tar1){
	/*var num=document.getElementById(ele).value;
	//change的影响程度的数值 > 原影响程度最高的数值
	if(num1 < num){
		num2=num1;
		tar2=tar1;
		num1=num;
		tar1=tar;
		document.getElementById("famedegree").value=num1;	
	}
	//原影响程度第二高的数值 <= change的影响程度的数值 <= 原影响程度最高的数值
	else if(num2 <= num <= num1){
		//change的是 原影响程度最高的方面
		if(num1 != document.getElementById(tar1).value){
			num1=num;
			document.getElementById("famedegree").value=num1;
		}
		//change的不是 原影响程度最高的方面
		else{
			num2=num;
			tar2=tar;
		}
		document.getElementById("famedegree").value=num1;	
	}
	else{
		//change的是 原影响程度最高的方面
		if(num1 != document.getElementById(tar1).value){
			num1=num;
			document.getElementById("famedegree").value=num1;
			
			document.getElementById("famedegree").value=num1;	
		}
	}*/
	var num1=document.getElementById(tar1).value;
	//还没选择
	if(0==num){
		num=num1;
		tar=tar1;
		document.getElementById("famedegree").value=num;	
	}
	//change之后的影响程度的数值 > 原影响程度最高的数值
	else if(num < num1){
		num=num1;
		tar=tar1;
		document.getElementById("famedegree").value=num;	
	}
	//change的是 原影响程度最高的方面的下拉框
	else if(num != document.getElementById(tar).value){
		num=document.getElementById("repCom").value;
		tar="repCom";
		if(num < document.getElementById("repSup").value){
			num = document.getElementById("repSup").value;
			tar="repSup";
		}
		if(num < document.getElementById("repPar").value){
			num = document.getElementById("repPar").value;
			tar="repPar";
		}
		if(num < document.getElementById("repPub").value){
			num = document.getElementById("repPub").value;
			tar="repPub";
		}
		document.getElementById("famedegree").value=num;	
	}

}
//发生可能性
function proChange(tar1){
var num1=document.getElementById(tar1).value;
	//还没选择
	if(0==numPro){
		numPro=num1;
		tar=tar1;
		document.getElementById("prodegree").value=numPro;	
	}
	//change之后的影响程度的数值 > 原影响程度最高的数值
	else if(numPro < num1){
		numPro=num1;
		tar=tar1;
		document.getElementById("prodegree").value=numPro;	
	}
	//change的是 原影响程度最高的方面的下拉框
	else if(numPro != document.getElementById(tar).value){
		numPro=document.getElementById("proProbability").value;
		tar="proProbability";
		if(numPro < document.getElementById("proDisasterEvent").value){
			numPro = document.getElementById("proDisasterEvent").value;
			tar="proDisasterEvent";
		}
		if(numPro < document.getElementById("proDailyOperation").value){
			numPro = document.getElementById("proDailyOperation").value;
			tar="proDailyOperation";
		}
		document.getElementById("prodegree").value=numPro;	
	}

}
//财务影响
function finChange(tar1){
var num1=document.getElementById(tar1).value;
	//还没选择
	if(0==numFin){
		numFin=num1;
		tar=tar1;
		document.getElementById("financedegree").value=numFin;	
	}
	//change之后的影响程度的数值 > 原影响程度最高的数值
	else if(numFin < num1){
		numFin=num1;
		tar=tar1;
		document.getElementById("financedegree").value=numFin;	
	}
	//change的是 原影响程度最高的方面的下拉框
	else if(numFin != document.getElementById(tar).value){
		numFin=document.getElementById("finAsset").value;
		tar="finAsset";
		if(numFin < document.getElementById("finIncome").value){
			numFin = document.getElementById("finIncome").value;
			tar="finIncome";
		}
		if(numFin < document.getElementById("finProfit").value){
			numFin = document.getElementById("finProfit").value;
			tar="finProfit";
		}
		if(numFin < document.getElementById("finProperty").value){
			numFin = document.getElementById("finProperty").value;
			tar="finProperty";
		}
		document.getElementById("financedegree").value=numFin;	
	}

}
//人员健康环保影响
function safChange(tar1){
var num1=document.getElementById(tar1).value;
	//还没选择
	if(0==numSaf){
		numSaf=num1;
		tar=tar1;
		document.getElementById("safedegree").value=numSaf;	
	}
	//change之后的影响程度的数值 > 原影响程度最高的数值
	else if(numSaf < num1){
		numSaf=num1;
		tar=tar1;
		document.getElementById("safedegree").value=numSaf;	
	}
	//change的是 原影响程度最高的方面的下拉框
	else if(numSaf != document.getElementById(tar).value){
		numSaf=document.getElementById("safSafety").value;
		tar="safSafety";
		if(numSaf < document.getElementById("safEnvironment").value){
			numSaf = document.getElementById("safEnvironment").value;
			tar="safEnvironment";
		}
		document.getElementById("safedegree").value=numSaf;	
	}

}
//验证各影响程度是否未选择
function subPre(){
	if("-1"==document.getElementById("financedegree").value){
		document.getElementById("financedegree").value="";
	}
	if("-1"==document.getElementById("prodegree").value){
		document.getElementById("prodegree").value="";
	}
	if("-1"==document.getElementById("famedegree").value){
		document.getElementById("famedegree").value="";
	}
	if("-1"==document.getElementById("lawdegree").value){
		document.getElementById("lawdegree").value="";
	}
	if("-1"==document.getElementById("clientdegree").value){
		document.getElementById("clientdegree").value="";
	}
	if("-1"==document.getElementById("employeedegree").value){
		document.getElementById("employeedegree").value="";
	}
	if("-1"==document.getElementById("operationdegree").value){
		document.getElementById("operationdegree").value="";
	}
	if("-1"==document.getElementById("safedegree").value){
		document.getElementById("safedegree").value="";
	}
}
</script>
</body>
</html>
