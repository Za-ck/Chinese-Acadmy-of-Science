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
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
<link href="/RiskEvent/js/Scroll/scroll.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="/RiskEvent/js/Scroll/scroll.js"></script>
	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
    <script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
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
<!--
<<<<<<< RiskEventInputRead.jsp
  <table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent" bgcolor="#F8FCFC">
    <tr bgcolor="#F8FCFC">
		 <td  align="center" height="30px">
			<input type="button" name="back" value="" class="back" onClick="backForm()"/>
		</td>
=======
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="CContent">
    <tr>
		  <td  align="center" height="50px">
				<input type="button" name="back" value="" class="back" onclick="backForm()"/>
				</td>	
		</tr>
>>>>>>> 1.5
-->
	<tr>	
    <td class="CPanel">
		<table border="0" cellpadding="0" cellspacing="0"   style="width:100%">
		<tr>
			<td width="100%">			
				<fieldset style="height:100%;">
				<legend>风险事件信息
				<input type="button" name="back" value="" class="back" onclick="backForm()"/>
				</legend>
				 <div id="errorDiv" style="color:red;font-weight:bold;font-size:12px"></div>
					 <table border="0" class="contentInfo" cellpadding="1" cellspacing="0" style="width:100%;"  >
					 <tr >
					    <td style="border-top:1px solid #d2d2d2;" align="right" width="13%">风险类别：</td>
			          <td style="border-top:1px solid #d2d2d2;" width="33%"><input type="text" name="risktype" style="border:0px; width:99%;" onfocus="this.blur()" id="risktype" value="<s:property value='risktype'/>"/> </td>
					    <td style="border-top:1px solid #d2d2d2;" align="right" width="19%">风险名称：</td>
					    <td style="border-top:1px solid #d2d2d2;" width="35%"><input type="text" name="riskname" style="border:0px;width:99%" onfocus="this.blur()" id="riskname" value="<s:property value='riskname'/>"/></td>
					  </tr>	
                      <tr>
					    <td   align="right" width="13%">风险事件编号：</td>
					    <td width="33%"><input type="text" name="riskId" style="border:0px;width:99%" onfocus="this.blur()" id="riskId" value="<s:property value='riskId'/>"/></td>
					    <td align="right" width="19%">录入部门：</td>
				    <td width="35%">
					       <input name="indep" type="text" class="text" style="border:0px;width:99%" onfocus="this.blur()" value="<s:property value='indep'/>"/></td>
					  </tr>		      
                       <tr>
					    <td   align="right" width="13%">风险来源：</td>
					    <td width="33%"><input type="text" name="source" style="border:0px;width:99%" onfocus="this.blur()" id="source" value="<s:property value='source'/>"/></td>
					    <td align="right" width="19%">损失金额：</td>
				        <td width="35%">
					       <input name="money" type="text" class="text" style="border:0px;width:99%" onfocus="this.blur()" value="<s:property value='money'/>"/></td>
					  </tr>		                    				    
					  <tr>
					    <td width="13%"   align="right">风险事件描述:</td>
					    <td colspan="3">
							<textarea name="riskremark" wrap="hard" cols="100" style=" border:0px;width:99%" onfocus="this.blur()" rows="5"><s:property value="riskremark"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td width="13%"   align="right">影响的关键绩效指标:</td>
					    <td colspan="3">
							<textarea name="kpi" wrap="hard" cols="100" style=" border:0px;width:99%" onfocus="this.blur()" rows="5"><s:property value="kpi"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td width="13%"   align="right">发生可能性:</td>
					    <td colspan="3">
							<textarea name="probability" cols="100" style=" border:0px;width:99%" onfocus="this.blur()" rows="5"><s:property value="probability"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td width="13%"   align="right">影响的业务领域:</td>
					    <td colspan="3">
							<textarea name="busarea" cols="100" style=" border:0px;width:99%" onfocus="this.blur()" rows="5"><s:property value="busarea"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td width="13%"   align="right">涉及流程:</td>
					    <td colspan="3">
							<textarea name="standard" cols="100" style=" border:0px;width:99%" onfocus="this.blur()" rows="5"><s:property value="standard"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td width="13%"   align="right">对财务的影响:</td>
					    <td colspan="3">
							<textarea name="finance" cols="100" style=" border:0px;width:99%" onfocus="this.blur()" rows="5"><s:property value="finance"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td width="13%"   align="right">对声誉的影响:</td>
					    <td colspan="3">
							<textarea name="fame" cols="100" style=" border:0px;width:99%" onfocus="this.blur()" rows="5"><s:property value="fame"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td width="13%"   align="right">在法律法规上的影响:</td>
					    <td colspan="3">
							<textarea name="law" cols="100" style=" border:0px;width:99%" onfocus="this.blur()" rows="5"><s:property value="law"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td width="13%"   align="right">对客户关系的影响:</td>
					    <td colspan="3">
							<textarea name="client" cols="100" style=" border:0px;width:99%" onfocus="this.blur()" rows="5"><s:property value="client"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td width="13%"   align="right">员工满意度遭受的影响:</td>
					    <td colspan="3">
							<textarea name="employee" cols="100" style=" border:0px;width:99%" onfocus="this.blur()" rows="5"><s:property value="employee"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td width="13%"   align="right">对运营的影响:</td>
					    <td colspan="3">
							<textarea name="operation" cols="100" style=" border:0px;width:99%" onfocus="this.blur()" rows="5"><s:property value="operation"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td width="13%"   align="right">对人员健康环保的影响:</td>
					    <td colspan="3">
							<textarea name="safe" cols="100" style=" border:0px;width:99%" onfocus="this.blur()" rows="5"><s:property value="safe"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td width="13%"   align="right">机会风险:</td>
					    <td colspan="3">
							<textarea name="chance" cols="100" style=" border:0px;width:99%" onfocus="this.blur()" rows="1"><s:property value="chance"/></textarea>						</td>
					  </tr>
                      <tr>
                         <td   align="right" width="13%">基本态度：</td>
			          <td width="33%"><input type="text" name="strategy" style="border:0px;width:99%" onfocus="this.blur()" id="strategy" value="<s:property value='strategy'/>"/> </td>
					    <td align="right" width="19%">计划时间：</td>
					    <td width="35%"><input type="text" name="plandate" style="border:0px;width:99%" onfocus="this.blur()" id="plandate" value="<s:property value='plandate'/>"/></td>
                      </tr>
                      <tr>
					    <td width="13%"   align="right">风险可控性:</td>
					    <td colspan="3">
							<textarea name="control" cols="100" style=" border:0px;width:99%" onfocus="this.blur()" rows="5"><s:property value="control"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td width="13%"   align="right">计划投入资源:</td>
					    <td colspan="3">
							<textarea name="planresource" cols="100" style=" border:0px;width:99%" onfocus="this.blur()" rows="5"><s:property value="planresource"/></textarea>						</td>
					  </tr>
                      <tr>
					    <td width="13%"   align="right">应对措施:</td>
					    <td colspan="3">
							<textarea name="reply" cols="100" style=" border:0px;width:99%" onfocus="this.blur()" rows="5"><s:property value="reply"/></textarea>						</td>
					  </tr>
					  <tr>
                        <td align="right"  >审核记录：</td>
                        <td colspan="3" >
							<textarea rows=1 cols=40 style='border:0px;overflow:scroll;overflow-y:hidden;;overflow-x:hidden'
                            onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},500);" 
                            onblur="clearInterval(this.clock);"><s:property value="verifyview"/></textarea>
							<!-- style="border:0px;width:99%;overflow-y:visible;" -->
                             						</td>
                      </tr>
<!--                      <tr>-->
<!--                        <td   align="right">审核人：</td>-->
<!--                        <td><input type="text" name="depverifier" style="border:0px;width:99%" onfocus="this.blur()" id="depverifier" value="<s:property value='depverifier'/>"/>                        </td>-->
<!--                        <td align="right">&nbsp;</td>-->
<!--                        <td>&nbsp;</td>-->
<!--                      </tr>-->
<!--                      <tr>-->
<!--                        <td height="150" align="right"  >审核意见：</td>-->
<!--                        <td colspan="3" >-->
<!--							<textarea name="depview" cols="100" style=" border:0px;width:99%" onfocus="this.blur()" rows="5"><s:property value="depview"/></textarea>						</td>-->
<!--                      </tr>-->
<!--                      <tr>-->
<!--                        <td   align="right">审核人：</td>-->
<!--                        <td><input type="text" name="fucstaff" style="border:0px;width:99%" onfocus="this.blur()" id="fucstaff" value="<s:property value='fucstaff'/>"/>                        </td>-->
<!--                        <td align="right">&nbsp;</td>-->
<!--                        <td>&nbsp;</td>-->
<!--                      </tr>-->
<!--                      <tr>-->
<!--                        <td height="150" align="right"  >审核意见：</td>-->
<!--                        <td colspan="3" >-->
<!--							<textarea name="staffview" cols="100" style=" border:0px;width:99%" onfocus="this.blur()" rows="5"><s:property value="staffview"/></textarea>						</td>-->
<!--                      </tr>-->
<!--                      <tr>-->
<!--                        <td   align="right">审核人：</td>-->
<!--                        <td><input type="text" name="fucverifier" style="border:0px;width:99%" onfocus="this.blur()" id="fucverifier" value="<s:property value='fucverifier'/>"/>                        </td>-->
<!--                        <td align="right">&nbsp;</td>-->
<!--                        <td>&nbsp;</td>-->
<!--                      </tr>-->
<!--                      <tr>-->
<!--                        <td height="150" align="right"  >审核意见：</td>-->
<!--                        <td colspan="3" >-->
<!--							<textarea name="fucview" cols="100" style=" border:0px;width:99%" onfocus="this.blur()" rows="5"><s:property value="fucview"/></textarea>						</td>-->
<!--                      </tr>-->
<!--                      <tr>-->
<!--                        <td   align="right">审核人：</td>-->
<!--                        <td><input type="text" name="planstaff" style="border:0px;width:99%" onfocus="this.blur()" id="planstaff" value="<s:property value='planstaff'/>"/>                        </td>-->
<!--                        <td align="right">&nbsp;</td>-->
<!--                        <td>&nbsp;</td>-->
<!--                      </tr>-->
<!--                      <tr>-->
<!--                        <td height="150" align="right"  >审核意见：</td>-->
<!--                        <td colspan="3" >-->
<!--							<textarea name="planstaffview" cols="100" style=" border:0px;width:99%" onfocus="this.blur()" rows="5"><s:property value="planstaffview"/></textarea>						</td>-->
<!--                      </tr>-->
<!--                      <tr>-->
<!--                        <td   align="right">审核人：</td>-->
<!--                        <td><input type="text" name="planverifier" style="border:0px;width:99%" onfocus="this.blur()" id="planverifier" value="<s:property value='planverifier'/>"/></td>-->
<!--                        <td align="right">&nbsp;</td>-->
<!--                        <td>&nbsp;</td>-->
<!--                      </tr>-->
<!--                      <tr>-->
<!--                        <td height="150" align="right"  >审核意见：</td>-->
<!--                        <td colspan="3" >-->
<!--							<textarea name="planview" cols="100" style=" border:0px;width:99%;" onfocus="this.blur()" rows="8"><s:property value="planview"/></textarea>						</td>-->
<!--                      </tr>-->
<!--                      <tr>-->
<!--                        <td   align="right">审核人：</td>-->
<!--                        <td><input type="text" name="president" style="border:0px;width:99%" onfocus="this.blur()" id="president" value="<s:property value='president'/>"/></td>-->
<!--                        <td align="right">&nbsp;</td>-->
<!--                        <td>&nbsp;</td>-->
<!--                      </tr>-->
<!--                      <tr>-->
<!--                        <td height="150" align="right"  >审核意见：</td>-->
<!--                        <td colspan="3" >-->
<!--							<textarea name="presidentview" cols="100" style=" border:0px;width:99%" onfocus="this.blur()" rows="5"><s:property value="presidentview"/></textarea>						</td>-->
<!--                      </tr>-->
                     
			    </table>
				</fieldset>			</td>
		</tr>
		</table>	</td>
  </tr>
 		<tr  >
		  <td  align="center" height="50px">
				<input type="button" name="back" value="" class="back" onclick="backForm()"/>
				<input type="text" style="visibility:hidden" name="backFlag" id="backFlag" value="<s:property value='backFlag'/>"/>
				</td>
			
		</tr>
  </table>
  </div>
  <div style="DISPLAY:none" id=goTopBtn><img border=0 title="返回顶部" src="/RiskEvent/js/Scroll/top.jpg"/></div>
  <div style="DISPLAY:none" id=goBottomBtn><img border=0 title="置底" src="/RiskEvent/js/Scroll/down.jpg"/></div>
<script type=text/javascript>goTopEx();</script>
  </form>
<script type="text/javascript">

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

</script>
  </body>
</html>
