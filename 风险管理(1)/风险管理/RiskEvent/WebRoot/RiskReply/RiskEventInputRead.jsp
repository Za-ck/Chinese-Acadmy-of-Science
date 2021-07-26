<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <title>风险事件信息</title>   
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	
	<link href="/RiskEvent/css/scroll.css" rel="stylesheet" type="text/css" />
	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
    <script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
    <script src="/RiskEvent/js/jquery-1.7.1.js" type="text/javascript"></script>
    
    <style>
    .contentInfo tr td{
    border-bottom:1px solid #d2d2d2;
    border-left:1px solid #d2d2d2;
    border-right:1px solid #d2d2d2;
    }
    .contentInfo input{
    BACKGROUND-COLOR: transparent;}
     .contentInfo textarea{
    BACKGROUND-COLOR: transparent;}
     textarea{
  overflow:auto;
 }
    </style>
  </head>
  
  <body class="ContentBody">
  <form  method="post" enctype="multipart/form-data" name="riskEventInputRead" id="riskEventInputRead">
  <div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle" align="center"><span>风险情况查询</span></div></div>
  <div class="MainDiv">
<table>
	<tr>	
    <td class="CPanel">
		<table border="0" cellpadding="0" cellspacing="0"   style="width:100%">
		<tr>
			<td width="100%">			
				<fieldset style="height:100%;">
				<legend>风险事件信息</legend>
				 <div id="errorDiv" style="color:red;font-weight:bold;font-size:12px"></div>
					 <table border="0" class="contentInfo" cellpadding="1" cellspacing="0" style="width:100%;"  >
					 <tr >
					    <td style="border-top:1px solid #d2d2d2;" align="right" width="13%">风险类别：</td>
			            <td style="border-top:1px solid #d2d2d2;" width="33%">
					  	<input type="text" name="risktype" style="border:0px;width:80px"  id="risktype" value="<s:property value='risktype'/>"/> 				
						<input type="text" name="riskname" style="border:0px;width:360px"  id="riskname" value="<s:property value='riskname'/>"/> 
						</td>
					    <td style="border-top:1px solid #d2d2d2;" align="right" width="19%">事件名称：</td>
					    <td style="border-top:1px solid #d2d2d2;" width="35%"><input type="text" name="eventname" style="border:0px;width:99%"  id="riskname" value="<s:property value='eventname'/>"/></td>
					  </tr>	
                      <tr>
					    <td   align="right" width="13%">风险事件编号：</td>
					    <td width="33%"><input type="text" name="riskId" style="border:0px;width:99%"  id="riskId" value="<s:property value='riskId'/>"/></td>
					    <td align="right" width="19%">录入部门：</td>
				    <td width="35%">
					       <input name="indep" type="text" class="text" style="border:0px;width:99%"  value="<s:property value='indep'/>"/></td>
					  </tr>
					  <tr>
					  	<td width="13%"   align="right">录入部门负责人：</td>
					    <td width="33%">
					       <input name="inputleader" type="text" class="text" style="border:0px;width:99%"  value="<s:property value='inputleader'/>"/></td>
					  	<td width="19%"   align="right">管理责任部门负责人：</td>
					    <td width="35%" >
					       <input name="manageDepLeader" type="text" class="text" style="border:0px;width:99%"  value="<s:property value='manageDepLeader'/>"/></td>
					  </tr>	      
                       <tr>
					    <td   align="right" width="13%">风险来源：</td>
					    <td width="33%"><input type="text" name="source" style="border:0px;width:99%"  id="source" value="<s:property value='source'/>"/></td>
					    <td align="right" width="19%">综合评定值：</td>
				        <td width="35%">
					       <input name="money" type="text" class="text" style="border:0px;width:99%"  value="<s:property value='money'/>"/></td>
					  </tr>		                    				    
					  <tr>
					    <td width="13%"   align="right">风险事件描述：</td>
					    <td colspan="3">
							<textarea name="riskremark" cols="100" style=" border:0px;width:99%"  rows="5"><s:property value="riskremark"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td width="13%"   align="right">影响的关键绩效指标：</td>
					    <td colspan="3">
							<textarea name="kpi" cols="100" style=" border:0px;width:99%"  rows="5"><s:property value="kpi"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td width="13%"   align="right">发生可能性：</td>
					    <td colspan="3">
							<textarea name="probability" cols="100" style=" border:0px;width:99%"  rows="5"><s:property value="probability"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td width="13%"   align="right">影响的业务领域：</td>
					    <td colspan="3">
							<textarea name="busarea" cols="100" style=" border:0px;width:99%"  rows="5"><s:property value="busarea"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td width="13%"   align="right">涉及体系文件：</td>
					    <td colspan="3">
							<textarea name="standard" cols="100" style=" border:0px;width:99%"  rows="5"><s:property value="standard"/></textarea>						</td>
					  </tr>
					  <tr>
					    <td width="13%"   align="right">涉及内控文件：</td>
					    <td colspan="3">
							<textarea name="controlFile" cols="100" style=" border:0px;width:99%"  rows="5"><s:property value="controlFile"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td width="13%"   align="right">对财务的影响：</td>
					    <td colspan="3">
							<textarea name="finance" cols="100" style=" border:0px;width:99%"  rows="5"><s:property value="finance"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td width="13%"   align="right">对声誉的影响：</td>
					    <td colspan="3">
							<textarea name="fame" cols="100" style=" border:0px;width:99%"  rows="5"><s:property value="fame"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td width="13%"   align="right">在法律法规上的影响：</td>
					    <td colspan="3">
							<textarea name="law" cols="100" style=" border:0px;width:99%"  rows="5"><s:property value="law"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td width="13%"   align="right">对客户关系的影响：</td>
					    <td colspan="3">
							<textarea name="client" cols="100" style=" border:0px;width:99%"  rows="5"><s:property value="client"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td width="13%"   align="right">对员工满意度的影响：</td>
					    <td colspan="3">
							<textarea name="employee" cols="100" style=" border:0px;width:99%"  rows="5"><s:property value="employee"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td width="13%"   align="right">对运营的影响：</td>
					    <td colspan="3">
							<textarea name="operation" cols="100" style=" border:0px;width:99%"  rows="5"><s:property value="operation"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td width="13%"   align="right">对人员健康环保的影响：</td>
					    <td colspan="3">
							<textarea name="safe" cols="100" style=" border:0px;width:99%"  rows="5"><s:property value="safe"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td width="13%"   align="right">机会风险：</td>
					    <td colspan="3">
							<textarea name="chance" cols="100" style=" border:0px;width:99%"  rows="1"><s:property value="chance"/></textarea>						</td>
					  </tr>
                      <tr>
                         <td   align="right" width="13%">基本态度：</td>
			          <td width="33%"><input type="text" name="strategy" style="border:0px;width:99%"  id="strategy" value="<s:property value='strategy'/>"/> </td>
					    <td align="right" width="19%">计划时间：</td>
					    <td width="35%"><input type="text" name="plandate" style="border:0px;width:99%"  id="plandate" value="<s:property value='plandate'/>"/></td>
                      </tr>
                      <tr>
					    <td width="13%"   align="right">风险可控性：</td>
					    <td colspan="3">
							<textarea name="control" cols="100" style=" border:0px;width:99%"  rows="5"><s:property value="control"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td width="13%"   align="right">计划投入资源：</td>
					    <td colspan="3">
							<textarea name="planresource" cols="100" style=" border:0px;width:99%"  rows="5"><s:property value="planresource"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td width="13%"   align="right">应对措施：</td>
					    <td colspan="3">
							<textarea name="reply" cols="100" style=" border:0px;width:99%"  rows="5"><s:property value="reply"/></textarea>						</td>
					  </tr>
					  <tr>
						<td  width="13%" align="right"  >审核记录：</td>
						<td colspan="3" >
							<textarea style="overflow-x: hidden;  font-family: Verdana,Arial; font-style: normal;  font-size: 13px; line-height: normal;border:0px;width:99%; " rows="10" cols="120"><s:property value="verifyview"/></textarea>
						</td>
                      </tr>
                     
			    </table>
				</fieldset>			</td>
		</tr>
		<!-- <tr>
		  <td  align="center" height="50px">
				<input type="button" name="readRiskEvent" style="width:70px;height: 24px;" value="查看流程" onclick="read()"/>
				</td>
		</tr> -->
		</table>	</td>
  </tr>
 		
  </table>
  </div>
  <div style="DISPLAY:none" id=goTopBtn><img border=0 title="返回顶部" src="/RiskEvent/js/Scroll/top.jpg"/></div>
  <div style="DISPLAY:none" id=goBottomBtn><img border=0 title="置底" src="/RiskEvent/js/Scroll/down.jpg"/></div>
<script type=text/javascript>goTopEx();</script>
  </form>
  <div id="updown"><span class="up"></span><span class="down"></span></div>
<script type="text/javascript">

		$(function() {
				
				$("#updown").css("top",window.screen.availHeight/2-100+"px");

				$(window).scroll(function() {		
				
					if($(window).scrollTop() >= 100){
				
						$('#updown').fadeIn(300); 
				
					}else{    
				
						$('#updown').fadeOut(300);    
				
					}  
				
				});
				
				$('#updown .up').click(function(){$('html,body').animate({scrollTop: '0px'}, 800);});
				
				$('#updown .down').click(function(){$('html,body').animate({scrollTop: document.body.clientHeight+'px'}, 800);});
		});

function backForm(){
	var flagData=document.getElementById('backFlag').value;
	if("query"==flagData){
		riskEventInputRead.action="riskQuery/RiskEventQueryAction";
		riskEventInputRead.submit();
	}else{
		riskEventInputRead.action="riskInput/RiskEventInputQueryAction";
		riskEventInputRead.submit();
	}
}

var agt = navigator.userAgent.toLowerCase();
var is_op = (agt.indexOf("opera") != -1);
var is_ie = (agt.indexOf("msie") != -1) && document.all && !is_op;
function ResizeTextarea(a,row){
    if(!a){return;}
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

	//撤回风险事件的操作
	function revocationRiskEvent()
	{
		var reId = document.getElementById("riskId").value;
		var params="reId="+ reId +"&tmpnum="+new Date().getTime();
		$.ajax({
              url: "riskFlow/RERevocation",
              type: "post",
              dateType: "json",
              data: params,
              contentType: "application/x-www-form-urlencoded",			//很重要
              success: function (data) 
              {
              		if(data == "fail")
              		{
              			alert("不可撤回");
              		}
              		else
              		{
              			alert("撤回成功");
              			window.location.href = "/RiskEvent/" + data;
              		}
              },
              error: function () 
              {
              		alert("操作失败");
          	   }
      		});
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
