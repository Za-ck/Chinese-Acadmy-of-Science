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
    
    <title>风险事件管理</title>
		<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<meta http-equiv="X-UA-Compatible"content="IE=7" /><!--以IE7模式渲染-->
	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
	<link href="/RiskEvent/js/Scroll/scroll.css" rel="stylesheet" type="text/css" />
	<script language="javascript" src="/RiskEvent/js/Scroll/scroll.js"></script>
    <script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript" defer="defer"></script> 
    <script src="/RiskEvent/js/jquery-1.7.1.js" type="text/javascript" charset="utf-8"></script>
	<script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
	<script language=javascript src="/RiskEvent/js/windows.js"></script>
   <style>
    textarea{
  width:99%;
  overflow:auto;
  border:1px solid #71B5F9;
 }
.contentInfo input{
    BACKGROUND-COLOR: transparent;}
     .contentInfo textarea{
    BACKGROUND-COLOR: transparent;}
 .contentInfo tr td{
    border-bottom:1px solid #d2d2d2;
    border-left:1px solid #d2d2d2;
    border-right:1px solid #d2d2d2;
 }
   </style> 
</head>
 
 <body class="ContentBody" >
<form id="riskManageInput" name="riskManageInput" action="riskInput/ManageUpdateAction" method="post" enctype="multipart/form-data"  onSubmit="return doValidate('riskManageInput')">
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div>
<div class="toptitlemiddle" align="left"><span>风险事件管理</span></div></div>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="CContent"  >
  <tr>
    <td class="CPanel" colspan="2">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"   style="width:100%">
		<tr>
			<td width="100%">			
				<fieldset style="height:100%;">
				<legend>风险事件管理</legend>
				 <div id="errorDiv" style="color:red;font-weight:bold;font-size:12px"></div>
					 <table border="0" class="contentInfo" cellpadding="2" cellspacing="0" style="width:100%"  >
					 <tr>
					    <td nowrap style="border-top:1px solid #d2d2d2;" align="right" width="13%">风险事件编号：</td>
			          <td style="border-top:1px solid #d2d2d2;" width="33%"><span class="red">
		              <input id="idmanage" name="idmanage" type="text" class="text" onFocus="this.blur()" style="width:154px" value="<s:property value='idmanage'/>"/>
		              *</span></td>
					    <td style="border-top:1px solid #d2d2d2;" align="right" width="19%">机会风险：</td>
					    <td style="border-top:1px solid #d2d2d2;" width="35%"><span class="red">
           <%-- <s:select name="chance" style="width:250px">
              <option>是，可以给我院带来正面/负面的影响</option>
              <option>否，仅仅给我院带来负面的影响</option>
            </s:select>--%>
			<s:select id="chance" name="chance"  theme="simple" list="#{'是，可以给我院带来正面/负面的影响':'是，可以给我院带来正面/负面的影响','否，仅仅给我院带来负面的影响':'否，仅仅给我院带来负面的影响'}" style="width:250px"  ></s:select>
            *</span></td>
					  </tr>	
                      <tr>
					    <td nowrap align="right" width="13%">基本态度：</td>
					    <td width="33%"><span class="red">
				        <%--<select name="strategy" style="width:100px">
                            <option value="">--请选择--</option>
							<option value="1">风险接受</option>
                            <option value="2">风险规避</option>
                            <option value="3">风险转移</option>
                            <option value="4">风险控制</option>
                         </select>--%>
						 <s:select id="strategy" name="strategy" theme="simple" list="#{'0':'--请选择--','风险接受':'风险接受','风险规避':'风险规避','风险转移':'风险转移','风险控制':'风险控制'}" style="width:100px"  ></s:select>
				        *</span>						</td>
					    <td align="right" width="19%">采取措施的时间计划：</td>
					    <td width="35%" align="left" nowrap="nowrap"><span class="red">
							<label>
              					<input title="单击选择" name="plandate" id="plandate" class="Wdate" style="width:150px;" readonly="readonly" <s:property value="plandate"/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({minDate: '%y-%M-%d' ,isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/>
              				</label>
              				*</span>
						</td>
				    </tr>		                  				    
					  <tr>
					    <td width="13%" nowrap align="right">可控性：</td>
					    <td colspan="3"><span class="red">
							<textarea title="请输入风险事件的可控性描述" id="control" name="control" cols="100" rows="10"><s:property value="control"/></textarea>						*</span></td>
					  </tr>
                      <tr>
					    <td width="13%" nowrap align="right">计划投入资源：</td>
					    <td colspan="3"><span class="red">
							<textarea title="请输入针对风险事件计划投入的资源" id="planresource" name="planresource" cols="100" rows="10"><s:property value="planresource"/></textarea>						*</span></td>
					  </tr>
                      <tr>
					    <td width="13%" nowrap align="right">具体的应对措施：</td>
					    <td colspan="3"><span class="red">
							<textarea title="请输入针对风险事件计划采取的具体的应对措施" id="reply" name="reply" cols="100" rows="10"><s:property value="reply"/></textarea>						*</span></td> 
					  </tr>
			    </table>
				</fieldset>				
			</td>
		</tr>
		</table>	 
	</td>
  </tr>
		
		<tr>
			<td colspan="2" align="center" height="50px">
			   <input type="button" name="preimpact" value="上一页" class="button" onClick="window.location.href='/RiskEvent/riskInput/getRiskImpactAction?idimpact=<s:property value="idmanage"/>'"/>
				<input type="button" id="save" name="save" class="button"  value="保存" />
				<input type="submit" name="submit" value="下一页" class="button"/>　  
<!--		   		<input type="button" name="putin" value="提交" class="button" onClick="window.location.href='/RiskEvent/riskInput/getIdtoImpact'"/>-->
			</td>
		</tr>
		 <tr><td colspan="3">
		 <input type="text" style="visibility:hidden" name="updateFlag" id="updateFlag" value="<s:property value='updateFlag'/>"/>
		 <input type="text" style="visibility:hidden;"  name="allFileId" id="allFileId" value="<s:property value='allFileId'/>"/>
		 <input type="text" style="visibility:hidden;"  name="allFileName" id="allFileName" value="<s:property value='allFileName'/>"/>
		 </td></tr>

</table>
<div style="DISPLAY:none" id=goTopBtn><img border=0 title="返回顶部" src="/RiskEvent/js/Scroll/top.jpg"/></div>
<div style="DISPLAY:none" id=goBottomBtn><img border=0 title="置底" src="/RiskEvent/js/Scroll/down.jpg"/></div>
<script type=text/javascript>

	alert((new Date().Format("yyyy-MM-dd hh:mm:ss")));

	$("#save").click(function(){
	var idmanage=$('#idmanage').val();
	var chance = $('#chance option:selected').val();
	var strategy=$('#strategy option:selected').val();
	var plandate=$('#plandate').val();
	var control=$('#control').val();
	var planresource=$('#planresource').val();
	var updateFlag= $('#updateFlag').val();
	var reply= $('#reply').val();
	var allFileId= $('#allFileId').val();
	var allFileName= $('#allFileName').val();
	if(doValidate('riskManageInput')){
		 $.ajax({
               url: 'riskInput/ManageUpdateAction',
               type: 'post',
               dateType: 'json',
               data:{'idmanage':idmanage,'chance':chance,'strategy':strategy,'plandate':plandate,'control':control,'planresource':planresource,'updateFlag':updateFlag,'reply':reply,'allFileId':allFileId,'allFileName':allFileName}, 
               contentType: 'application/x-www-form-urlencoded',
               success: function (data) {  
               	alert('保存成功');   
		 		}
		 });
	} 
});

	goTopEx();
	/*function changetype()
       {
        	if("--请选择--"==document.getElementById("strategy").text){
				document.getElementById("strategy").text="";
			}
       }*/
       
</script>
</form>
</body>
</html>
