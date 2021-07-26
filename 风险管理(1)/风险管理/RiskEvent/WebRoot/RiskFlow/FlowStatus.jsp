<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>${title }</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<style>
    .flowChart{
		width:100%;
		minWidth:600px;
		/*height:400px;*/
		overflow:hidden;
		border:1px solid #e4e4e4;
		padding:10x;
		
	}
	.direction{
	/*margin:10px 0px 10px 0px;*/
	width:120px;
	height:180px;
	float:left;
	text-align:center;
	display:block;
	line-height:120px;
	/*overflow*/
	overflow:hidden;  
	}
	.flowNode{
		/*修改hidden*/
		overflow:hidden;
	/*margin:10px 0px 10px 0px;padding:5px;*/
	border:2px solid #818181;
	width:120px;
	height:170px;
	float:left;
	text-align:center;
	
	display:block;}
	.divover{
	border:2px solid #1281d3;
	/*margin:10px 0px 10px 0px;padding:5px;*/
	width:120px;
	height:170px;
	float:left;
	text-align:center;
	
	display:block;}
	.divout{
	border:2px solid #818181;
	/*margin:10px 0px 10px 0px;padding:5px;*/
	width:120px;
	overflow:hidden;
	height:170px;
	float:left;
	text-align:center;
	
	display:block;
	}
	.depDesp{
	border-bottom:1px dotted #818181;
	width:120px;
	height:22px;
	line-height:20px;
	font-size:13px;
	text-align:left;}
	.textshow{
	color:#1281d3;
	margin-left:5px;
	font-family：Arial, Helvetica, sans-serif;
	font-size:12px;
	font-weight:bolder;}
	.textinfo{
	color:#818181;
	margin-left:5px;
	text-align:left;
	font-family:Arial, Helvetica, sans-serif;
	font-size:13px;
	}
	.nodecontent{
		/*修改*/
		height:180px;
		padding:5px; 
		/*margin:10px 0;*/
		overflow:hidden;
	width:130px;
	float:left;}
    </style>

  </head>
  
  <body>
<div class="flowChart">
<%int num=0;%>

<s:if test="reState==0"> 
<div class="flowNode" style="border: 3px solid #FFCC33" title="当前流程所处位置">
<div class="depDesp"><span class="textinfo">状态：<s:property value="frStatus"/>未修改</span><img src="/RiskEvent/frame/icon/Blue Round.png"  width="20" height="20"/></div>
<div class="depDesp"><span class="textinfo">提交部门：</span></div>
<div class="depDesp"><span class="textshow"><s:property value="deparment"/></span></div>
<div class="depDesp"><span class="textinfo">提交人：</span><span class="textshow"><s:property value="creator"/></span></div>
<div class="depDesp"><span class="textinfo">时间：</span><span class="textshow"><s:property value="creatdate"/></span></div>
</div>
</s:if>
<s:else>
<div class="flowNode" style="border:2px solid #3aa4ff;">
<div class="depDesp"><span class="textinfo">状态：<s:property value="frStatus"/>已提交</span><img src="/RiskEvent/frame/icon/Blue Round.png"  width="20" height="20"/></div>
<div class="depDesp"><span class="textinfo">提交部门：</span></div>
<div class="depDesp"><span class="textshow"><s:property value="deparment"/></span></div>
<div class="depDesp"><span class="textinfo">提交人：</span><span class="textshow"><s:property value="creator"/></span></div>
<div class="depDesp"><span class="textinfo">时间：</span><span class="textshow"><s:property value="creatdate"/></span></div>
</div>
</s:else>


<s:iterator value="flowRuleList" >

<div class="direction"><img src="/RiskEvent/frame/icon/yesArrow.png"/></div>
<s:if test="frStatus!=reState">
<div class="nodecontent">
<s:if test="yesOrno==1">
<div class="flowNode" onMouseOver="this.className='divover'" onMouseOut="this.className='divout'">
<div class="depDesp"><span class="textinfo">状态：通过</span>
<img style="margin-left:15px;" width="20" height="20" src="/RiskEvent/frame/icon/Ok.png"/>
</div>
<div class="depDesp">
<%
  if (0 == num++) {
%>
<span class="textinfo">提交部门：</span>
<%
}
else{
%>
<span class="textinfo">审核部门：</span>
<%
}
%>
</div>
<div class="depDesp"><span class="textshow"><s:property value="deparment"/></span></div>
<div class="depDesp"><span class="textinfo">审核人：</span><span class="textshow"><s:property value="passuser"/></span></div>
<div class="depDesp"><span class="textinfo">时间：</span><span class="textshow"><s:property value="passdate"/></span></div>
</div>
</s:if>
<s:elseif test="yesOrno==0">
<div class="flowNode" style="border: 3px solid #CC0000">
<div class="depDesp"><span class="textinfo">状态：未通过</span>
<img style="margin-left:10px;" width="20" height="20" src="/RiskEvent/frame/icon/Cancel.png"/>
</div>
<div class="depDesp">
<%
  if (0 == num++) {
%>
<span class="textinfo">提交部门：</span>
<%
}
else{
%>
<span class="textinfo">审核部门：</span>
<%
}
%>
</div>
<div class="depDesp"><span class="textshow" style="color:#818181"><s:property value="deparment"/></span></div>
<div class="depDesp"><span class="textinfo">审核人：</span><span class="textshow" style="color:#818181"><s:property value="passuser"/></span></div>
<div class="depDesp"><span class="textinfo">时间：</span><span class="textshow" style="color:#818181"><s:property value="passdate"/></span></div>
</div>
</s:elseif>
<s:else>
<div class="flowNode" style="border: 3px solid #3aa4ff;" onMouseOver="this.className='divover'" onMouseOut="this.className='divout'">
<div class="depDesp"><span class="textinfo">状态：待审中...</span>
<img style="margin-left:0px;" width="20" height="20" src="/RiskEvent/frame/icon/Cancel.png"/>
</div>
<div class="depDesp">
<%
  if (0 == num++) {
%>
<span class="textinfo">提交部门：</span>
<%
}
else{
%>
<span class="textinfo">审核部门：</span>
<%
}
%>
</div>
<div class="depDesp"><span class="textshow" style="color:#818181"><s:property value="deparment"/></span></div>
<div class="depDesp"><span class="textinfo">审核人：</span><span class="textshow" style="color:#818181"><s:property value="passuser"/></span></div>
<div class="depDesp"><span class="textinfo">时间：</span><span class="textshow" style="color:#818181"><s:property value="passdate"/></span></div>
</div>
</s:else>
</div>
</s:if>
<s:elseif test="frStatus==reState">
<div class="nodecontent">
<div class="flowNode" style="border: 3px solid #FFCC33" title="当前流程所处位置">
<div class="depDesp"><span class="textinfo">状态：审核中...</span><img style="margin-left:5px;" width="20" height="20" src="/RiskEvent/frame/icon/others.png"/></div>
<div class="depDesp">
<%
  if (0 == num++) {
%>
<span class="textinfo">提交部门：</span>
<%
}
else{
%>
<span class="textinfo">审核部门：</span>
<%
}
%>
</div>
<div class="depDesp"><span class="textshow" style="color:#818181"><s:property value="waitfordepart"/></span></div>
<div class="depDesp"><span class="textinfo" >审核人：</span><span class="textshow" style="color:#818181"><s:property value="waitforperson"/></span></div>
<div class="depDesp"><span class="textinfo">时间：</span><span class="textshow" style="color:#818181">等待...</span></div>
</div>
</div>
</s:elseif>

</s:iterator>
</div>
</body>
</html>
