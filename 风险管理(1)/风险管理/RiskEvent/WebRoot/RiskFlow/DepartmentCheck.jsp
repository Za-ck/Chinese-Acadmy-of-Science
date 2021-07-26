<%@ page language="java" import="java.util.*,com.model.Users" pageEncoding="utf-8"%>
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
    
    <title>部门领导审批</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	
	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
    <link href="/RiskEvent/js/Scroll/scroll.css" rel="stylesheet" type="text/css" />
    <script language="javascript" src="/RiskEvent/js/Scroll/scroll.js"></script>
    <script src="/RiskEvent/js/jquery-1.7.1.js" type="text/javascript"></script>
    <script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
    <script language=javascript src="/RiskEvent/js/windows.js"></script>
    <script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript"></script>
   <style>
textarea{
  width:99%;
  overflow:auto;
  border:1px solid #71B5F9;
}
.contentInfo input{
    BACKGROUND-COLOR: transparent;
    }
     .contentInfo textarea{
    BACKGROUND-COLOR: transparent;}
 .contentInfo tr td{
    border-bottom:1px solid #d2d2d2;
    border-left:1px solid #d2d2d2;
    border-right:1px solid #d2d2d2;
 }
   </style> 
</head>
  
 <body class="ContentBody" bgcolor="#F8FCFC" onload="init()">
<form id="departmentCheck1" name="departmentCheck1" action="riskFlow/DepPassAction" method="post" enctype="multipart/form-data" onsubmit="return doValidate('departmentCheck1')">
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div>
<div class="toptitlemiddle" align="center"><span>风险事件信息</span></div></div>
<%
int checkflag = 0;
String assessYear = "2015";
String assessQuarter = "4";
if((Integer)request.getAttribute("reCheckFlag")!=null){
	checkflag = (Integer)request.getAttribute("reCheckFlag");
}
if((String)request.getAttribute("reAssessYear")!=null){
	assessYear = (String)request.getAttribute("reAssessYear");
}
if((String)request.getAttribute("reAssessQuarter")!=null){
	assessQuarter = (String)request.getAttribute("reAssessQuarter");
}
 %>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="CContent"  >
  <tr bgcolor="#F8FCFC">
    <td class="CPanel" colspan="2">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"   style="width:100%">
		<tr>
			<td width="100%">			
				<fieldset style="height:100%;">
				<legend>风险事件信息</legend>
				<div id="errorDiv" style="color:red;font-weight:bold;font-size:12px"></div>
					 <table border="0" class="contentInfo" cellpadding="2" cellspacing="0" style="width:100%"  >
					 <tr>
					  <td style="border-top:1px solid #d2d2d2;" nowrap="nowrap" align="right" width="15%">风险类别：</td>
			          <td style="border-top:1px solid #d2d2d2;" width="30%">
					  
					  <input type="text" name="risktype" style="border:0px;width:80px" readonly="readonly" id="risktype" value="<s:property value='risktype'/>"/> 				
						<input type="text" name="riskname" style="border:0px;width:360px" readonly="readonly" id="riskname" value="<s:property value='riskname'/>"/> 
					  </td>
					  <td style="border-top:1px solid #d2d2d2;" align="right" width="15%">事件名称：</td>
						<td style="border-top:1px solid #d2d2d2;" width="40%">
						<input type="text" name="eventname" style="border:0px;width:99%" readonly="readonly" id="riskname" value="<s:property value='eventname'/>"/>						
						</td>
					  </tr>	
					  
                      <tr>
					    <td nowrap="nowrap" align="right" >风险事件编号：</td>
					    <td ><input type="text" name="riskId" style="border:0px" readonly="readonly" id="riskId" value="<s:property value='riskId'/>"/></td>
					    <td align="right" >录入部门：</td>
				    <td >
					       <input name="indep" type="text" class="text" style="border:0px" readonly="readonly" value="<s:property value='indep'/>"/></td>
					  </tr>		      
                       <tr>
					    <td nowrap="nowrap" align="right" >风险来源：</td>
					    <td ><input type="text" name="source" style="border:0px" readonly="readonly" id="source" value="<s:property value='source'/>"/></td>
					    <td align="right" >综合评定值：</td>
				        <td >
					       <input name="money" type="text" class="text" style="border:0px" readonly="readonly" value="<s:property value='money'/>"/></td>
					  </tr>		                    				    
					  <tr>
					    <td  nowrap="nowrap" align="right">风险事件描述：</td>
					    <td colspan="3">
							<textarea name="riskdetail" cols="100" style=" border:0px" readonly="readonly" rows="5"><s:property value="riskdetail"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td  nowrap="nowrap" align="right">影响的关键绩效指标：</td>
					    <td colspan="3">
							<textarea name="kpi" cols="100" style=" border:0px" readonly="readonly" rows="5"><s:property value="kpi"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td  nowrap="nowrap" align="right">发生可能性：</td>
					    <td colspan="3">
							<textarea name="probability" cols="100" style=" border:0px" readonly="readonly" rows="5"><s:property value="probability"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td  nowrap="nowrap" align="right">影响的业务领域：</td>
					    <td colspan="3">
							<textarea name="busarea" cols="100" style=" border:0px" readonly="readonly" rows="5"><s:property value="busarea"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td  nowrap="nowrap" align="right">涉及流程：</td>
					    <td colspan="3">
							<textarea name="standard" cols="100" style=" border:0px" readonly="readonly" rows="5"><s:property value="standard"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td nowrap="nowrap" align="right">财务方面影响：</td>
					    <td colspan="3">
							<textarea name="finance" cols="100" style=" border:0px" readonly="readonly" rows="5"><s:property value="finance"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td  nowrap="nowrap" align="right">声誉方面影响：</td>
					    <td colspan="3">
							<textarea name="fame" cols="100" style=" border:0px" readonly="readonly" rows="5"><s:property value="fame"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td  nowrap="nowrap" align="right">法律法规影响：</td>
					    <td colspan="3">
							<textarea name="law" cols="100" style=" border:0px" readonly="readonly" rows="5"><s:property value="law"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td  nowrap="nowrap" align="right">客户关系：</td>
					    <td colspan="3">
							<textarea name="client" cols="100" style=" border:0px" readonly="readonly" rows="5"><s:property value="client"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td  nowrap="nowrap" align="right">员工满意度：</td>
					    <td colspan="3">
							<textarea name="employee" cols="100" style=" border:0px" readonly="readonly" rows="5"><s:property value="employee"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td  nowrap="nowrap" align="right">运营影响：</td>
					    <td colspan="3">
							<textarea name="operation" cols="100" style=" border:0px" readonly="readonly" rows="5"><s:property value="operation"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td  nowrap="nowrap" align="right">人员健康环保影响：</td>
					    <td colspan="3">
							<textarea name="safe" cols="100" style=" border:0px" readonly="readonly" rows="5"><s:property value="safe"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td  nowrap="nowrap" align="right">机会风险：</td>
					    <td colspan="3">
							<textarea name="chance" cols="100" style=" border:0px" readonly="readonly" rows="1"><s:property value="chance"/></textarea>						</td>
					  </tr>
                      <tr>
                         <td nowrap="nowrap" align="right" >基本态度：</td>
			          <td ><input type="text" name="strategy" style="border:0px" readonly="readonly" id="strategy" value="<s:property value='strategy'/>"/> </td>
					    <td align="right" >计划时间：</td>
					    <td ><input type="text" name="plandate" style="border:0px" readonly="readonly" id="plandate" value="<s:property value='plandate'/>"/></td>
                     </tr>
                     <tr>
					    <td  nowrap="nowrap" align="right">风险可控性：</td>
					    <td colspan="3">
							<textarea name="control" cols="100" style=" border:0px" readonly="readonly" rows="5"><s:property value="control"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td  nowrap="nowrap" align="right">计划投入资源：</td>
					    <td colspan="3">
							<textarea name="planresource" cols="100" style=" border:0px" readonly="readonly" rows="5"><s:property value="planresource"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td  nowrap="nowrap" align="right">应对措施：</td>
					    <td colspan="3">
							<textarea name="reply" cols="100" style=" border:0px" readonly="readonly" rows="5"><s:property value="reply"/></textarea>						</td>
					  </tr>
                      <tr>
                        <td nowrap="nowrap" align="right">审核人：</td>
                        <td><input type="text" name="verifier" style="border:0px" readonly="readonly" id="verifier" value="<s:property value='verifier'/>"/>                        </td>
                        <td nowrap="nowrap" align="right">录入人：</td>
                        <td><input type="text" name="creator" style="border:0px" readonly="readonly" id="creator" value="<s:property value='creator'/>"/></td>
                      </tr>
                      <%--<tr>
                        <td height="150" align="right" nowrap="nowrap">审核意见：</td>
                        <td colspan="3" >
							<textarea name="verifyview" cols="100" style=" border:0px" onfocus="this.blur()" rows="5"><s:property value="verifyview"/></textarea></td>
                      </tr>--%>  
					  <tr>
                        <td height="150" align="right" nowrap="nowrap">审核记录：</td>
                        <td colspan="3" >
                         	<textarea name="verifyview" style="overflow-x: hidden;  font-family: Verdana,Arial; font-style: normal;  font-size: 13px; line-height: normal;border:0px;width:99%; " rows="10" cols="120"><s:property value="verifyview"/></textarea>
						</td>
                      </tr>  
					  
					                   
                     <tr>
			             <td align="right" height="50px"><span style="font-size:16px;color:#1B88F5;font-weight:bolder">审核意见：</span></td>
			             <td colspan="3"><textarea id="feedback" name="feedback" cols="120" rows="8"><s:property value="feedback"/></textarea><span class="red">*</span></td>
	                 </tr>
	                 <tr id="assessment" style="display:none">
                        <td nowrap="nowrap" align="right"><span style="font-size:16px;color:#1B88F5;font-weight:bolder">考核年份：</span></td>
                       <%if(checkflag==1){ %>
                       <td id="assessYear" name="year" ><%= assessYear%></td>
                       <td nowrap="nowrap" align="right"><span style="font-size:16px;color:#1B88F5;font-weight:bolder">考核季度：</span></td>
                       <td name="quarter" >第<%= assessQuarter%>季度</td>
                        <%}else{ %>
                        <td id="assessYear">
                        	<input id="assessYear" name="year" class="Wdate" style="width: 105px;border:none;text-align: center;"
								value="<%=request.getAttribute("year") %>" readonly="readonly"
								onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy'})" />
						</td>
                        <td nowrap="nowrap" align="right"><span style="font-size:16px;color:#1B88F5;font-weight:bolder">考核季度：</span></td>
                        <td id="assessMonth">
	                        <select name="quarter" style="width: 105px;border:none;text-align: center;" id="assessMonth" readOnly="readOnly">
								<option value="1"
									<%if("1".equals(request.getAttribute("quarter")+""))out.print("selected"); %>>
									第1季度
								</option>
								<option value="2"
									<%if("2".equals(request.getAttribute("quarter")+""))out.print("selected"); %>>
									第2季度
								</option>
								<option value="3"
									<%if("3".equals(request.getAttribute("quarter")+""))out.print("selected"); %>>
									第3季度
								</option>
								<option value="4"
									<%if("4".equals(request.getAttribute("quarter")+""))out.print("selected"); %>>
									第4季度
								</option>
							</select>
						</td>
						<%} %>
                      </tr>
			    </table>
			  </fieldset>			</td>
		</tr>
		</table>	</td>
		
  </tr>
	  <tr>
			<td colspan="4" align="center" height="50px">
				<input type="button" name="btn_pass2" value="保存" class="button" onclick="save()"/>  
			    <input type="button" name="btn_pass" id="btn_pass" value="发送" class="button" onclick="pass()"/>　  		   		
		    <input type="button" name="btn_notpass"  value="回退" class="button" onclick="nopass()"/>
		    <input type="button" name="readRiskEvent" value="查看流程" class="button" onclick="read()" />
		    </td>
			
	  </tr>
		 
</table>
	<input type="hidden" id="position" name="position" value="<%=((Users)session.getAttribute("loginUser")).getUserPosition()%>"/>
	<input type="hidden" id="id" name="id" value='<s:property value="riskId"/>'/>
  <div style="DISPLAY:none" id=goTopBtn><img border=0 title="返回顶部" src="/RiskEvent/js/Scroll/top.jpg"/></div>
  <div style="DISPLAY:none" id=goBottomBtn><img border=0 title="置底" src="/RiskEvent/js/Scroll/down.jpg"/></div>
<script type=text/javascript>goTopEx();</script>
</form>
<script type="text/javascript">

function init()
{
	var position = document.getElementById("position").value;
	if(position == "12")
	{
		document.getElementById("btn_pass").value = "发布";
		var ui = document.getElementById("assessment");
		ui.style.display="";
	}
}

function pass()
{
	if(document.getElementById('feedback').value=="")
	{
		alert("审批意见不能为空");
		return false;
	}
	else
	{
		var position = document.getElementById("position").value;
		if(position == "12")
		{
			if(confirm("确定发布吗?"))
			{
				departmentCheck1.action='riskFlow/DepPassAction?act=1';
  				departmentCheck1.submit();
			}
		}
		else
		{
			departmentCheck1.action='riskFlow/DepPassAction?act=1';
  			departmentCheck1.submit();
		}
	}
}

function nopass()
{
if(document.getElementById('feedback').value=="")
{alert("审批意见不能为空");
return false;
}
else
{
  departmentCheck1.action='riskFlow/DepNotPassAction?act=0';
  departmentCheck1.submit();
}
}

function save()
{
if(document.getElementById('feedback').value=="")
{alert("审批意见不能为空");
return false;
}
else
{
  departmentCheck1.action='riskFlow/RiskViewSaveAction';
  departmentCheck1.submit();
  }
 
}

var agt = navigator.userAgent.toLowerCase();
var is_op = (agt.indexOf("opera") != -1);
var is_ie = (agt.indexOf("msie") != -1) && document.all && !is_op;
function ResizeTextarea(a,row){
    if(!a)
    {
    	return;
    }
    if(!row)
        row=5;
    var b=a.value.split("\n");
    var c=is_ie?1:0;
    c+=b.length;
    var d=a.cols;
    if(d<=20){d=40;}
    for(var e=0;e<b.length;e++){
        if(b[e].length>=d){
            c+=Math.ceil(b[e].length/d);
        }
    }
    c=Math.max(c,row);
    if(c!=a.rows){
        a.rows=c;
    }
}

	function read() 
	{
		var reId = document.getElementById("riskId").value;
		var riskId = reId.substring(0,reId.lastIndexOf("-"));
		window.open("/RiskEvent/riskFlow/RiskFlowChart?riskId="+riskId+"&resId="+reId);
	}
</script>
</body>
</html>
