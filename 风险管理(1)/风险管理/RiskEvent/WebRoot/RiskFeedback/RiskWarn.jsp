<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">  
    <title>风险事件预警</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="/RiskEvent/css/frameStyle.css" rel="stylesheet" type="text/css" />	
	<script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
  </head>
  
  <body style="font-size:12px; margin-top:5px;">
  <form id="riskWarn" name="riskWarn" method="post" action="riskFeedback/riskWarnAction" >
<div class="MainDiv">
<table width="98%" border="0" cellpadding="0" cellspacing="0" class="CContent" align="center" bgcolor="#F8FCFC">
	<tr>    
  		<td height="62" align="center" style="font-size:25px" background="/RiskEvent/images/main/nav04.gif">
  			<span class="blue">风险事件预警222</span>
  		</td>        
  	</tr>
	<tr><td>
</table>
	<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#F8FCFC">
		<tr>
            <td align="right" width="10%" height="30" nowrap="nowrap">录入部门名称：</td>
              <td align="left" width="13%" nowrap="nowrap">
              		<label> 
                       <input type="text" name="reIndep" id="reIndep" size="15" value=""> 
                    </label> 
              </td>
                <td width="55%" align="left" nowrap="nowrap">
				<input type="button" class="right-buttonShort" name="buttonQuery" id="buttonQuery" value="查询" onClick="query();" />					     	
				</td>
        </tr>
    </table>
          		
          <div id="table1"> 
			<!--  style="table-layout:fixed; font-weight:normal; margin-top:3px;" -->
           	<table width="98%" border="1" cellpadding="0" cellspacing="0" class="warp_table" id="changecolor" align="center">
            	<tr>
                    <td height="22" colspan="7" align="center" style="font-size:16px"> 
                    		
						信息详细列表
					</td>
                </tr>
				 <tr bgcolor="#EEEEEE" >
              			<td width="10%" align="center" height="30">序号</td>
                		<td width="10%" align="center" >风险事件编号</td>
                		<td width="10%" align="center">风险名称</td>
                		<td width="10%" align="center">录入部门</td>
                		<td width="10%" align="center">计划时间</td>
                		<td width="10%" align="center">录入时间</td>
		        		<td width="10%" align="center">操作</td>
		        		<td width="10%" align="center">风险程度</td>
		        		<td width="10%" align="center">风险颜色</td>
		        		
						<%int num=0;%>
            	</tr>
             		<tbody id="body_RiskWarn1">
					<s:iterator value="riskWarnList">
            		<tr align="center" >
              			<td width="10%" height="30" align="center"><%=++num%></td>
              			<td width="10%" align="center"><s:property value="id.reId"/></td>
            			<td width="10%" align="center"><s:property value="id.riskName"/></td>
            			<td width="10%" align="center"><s:property value="id.depName"/></td>
            			<td width="10%" align="center"><s:property value="id.rmPlandate"/></td>
						<td width="10%" align="center"><s:property value="id.reDate"/></td>
						<td width="10%" align="center">
                    		<a href="riskInput/REIQReadAction?reId=<s:property value='id.reId'/>" onClick="">查看</a> 
						</td>
						<td width="10%" align="center">
							
							<%--<s:if test="chaZhi>60">
    							<div>正常</div>
						</s:if>
							<s:elseif test="chaZhi>30">
    							<div>较紧急</div>
								</s:elseif>
							<s:else>
   								 <div>紧急</div>
				</s:else>
						--%>
						<s:property value="value"/>
						</td>
						<td width="10%" align="center"><s:property value="stragColor"/></td>
            		</tr>
            		</s:iterator>
            		</tbody>
        		</table>
        		</div>
     			<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" align="center">
        			<tr>
          				<td height="6"><img src="/RiskEvent/images/main/spacer.gif" width="1" height="1" /></td>
        			</tr>
				</table>
				<table width="98%" height="33" border="0" align="center" cellpadding="0" cellspacing="0" class="right-font08" align="center">
              		<tr>
                		<td width="50%"><span id="divFood"></span></td>
                		<td width="49%" align="right">
							[<a href="riskFeedback/riskWarnAction" class="right-font08" onClick="page.firstPage();return false;">首页</a> 
							| <a  class="right-font08" onClick="page.prePage();return false;">上一页</a> 
                			| <a class="right-font08" onClick="page.nextPage();return false;">下一页</a> 
                			| <a  class="right-font08" onClick="page.lastPage();return false;">末页</a>]&nbsp;&nbsp;
							<input id="pageno" value="1" style="width:20px"/>页<a href="riskFeedback/riskWarnAction" onClick="page.aimPage();return false;"> 跳转</a>
						</td>
					</tr>
					<tr bgcolor="#F8FCFC"><td height="1"><input type="text" style="visibility:hidden" name="dateFlag" id="dateFlag" value=""/></td></tr>
                </table>
			  
    
</div>
</form>


<a href="RiskStrategySet/riskStrategyAction" >策略页面</a> 

  </body>
</html>
