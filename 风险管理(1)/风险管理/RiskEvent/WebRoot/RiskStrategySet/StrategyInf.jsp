<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib prefix="p"  uri="pageV"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>预警策略信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
	<script language="javascript" src="IconPage/page.js"></script>
	<script language="javascript" src="IconPage/jquery-1.3.2.min.js"></script>
    <script language="javascript" src="/RiskEvent/js/funcJS.js"></script>
  

  </head>

 
   <body style="font-size:12px; margin-top:5px;" >
<form name="law" id="law" method="post" action="RiskStrategySet/riskStrategyInfAction">
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>预警策略信息</span></div></div>
<p:page pageTitle="策略最小值;策略状态;策略颜色;策略最大值"  pageVaule="stragValue;stragStatue;stragColor;remark" id="mytable" dataProvider="riskStrategyList"
  			actionUrl="RiskStrategySet/riskStrategyInfAction"
  			pageNum="10"
  			menu="true"
  			menuAlign="left"
  			dcLineUsed="true"

  			dcLink="RiskStrategySet/riskStrategySeekAction"
  			addUsed="true" addLink="/RiskEvent/RiskStrategySet/StrategyAddUpdate.jsp"
  			deleteUsed="true" deleteLink="RiskStrategySet/riskStrategyDeleteAction"
  			
  			checkUsed="true" checkValue="riskStrId"
  			updateUsed="true" updateLink="RiskStrategySet/riskStrategypupdateAction"
  			

  			dcLink="dataUnit/LawReadAction"
  			addUsed="true" addLink="/RiskEvent/DataUnit/Law/LawAddUpdate.jsp"
  			deleteUsed="true" deleteLink="dataUnit/LawMultiDelAction"
  			checkUsed="true" checkValue="stragValue"
  			updateUsed="true" updateLink="dataUnit/LawUpdatePreAction"

  			></p:page>
  </form>
  </body>

  <body style="font-size:12px; margin-top:5px;" onmousemove="javascript:MouseMoveToResize(event);" onmouseup="javascript:MouseUpToResize();">
  <form id="riskStrategy" name="riskStrategy" method="post" action="RiskStrategySet/riskStrategyAction" >
<div class="MainDiv">
<table width="98%" border="0" cellpadding="0" cellspacing="0" class="CContent" align="center" bgcolor="#F8FCFC">
	<tr>    
  		<td height="62" align="center" style="font-size:25px" background="/RiskEvent/images/main/nav04.gif">
  			<span class="blue">预警策略信息</span>
  		</td>        
  	</tr>
	<tr><td>
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
                		<td width="10%" align="center" >策略最小值</td>
                		<td width="10%" align="center" >策略状态</td>
                		<td width="10%" align="center" >策略颜色</td>
                		<td width="10%" align="center" >策略最大值</td>
                		<td width="10%" align="center" >操作</td>
                		
                		
						<%int num=0;%>
            	</tr>
             		<tbody id="body_RiskWarn1">
					<s:iterator value="riskStrategyList">
            		<tr align="center" >
              			<td width="10%" height="30" align="center"><%=++num%></td>
              			<td width="10%" align="center"><s:property value="stragValue"/></td>
              			<td width="10%" align="center"><s:property value="stragStatue"/></td>
              			<td width="10%" align="center"><s:property value="stragColor"/></td>
              			<td width="10%" align="center"><s:property value="remark"/></td>
              			
            	
						<td width="10%" align="center">
                    		<a href="RiskStrategySet/riskStrategySeekAction? riskStrId=<s:property value=' riskStrId'/>" onClick="">查看</a> | <a href="RiskStrategySet/riskStrategypupdateAction?riskStrId=<s:property value='riskStrId'/>&updateFlag=update" onClick="">编辑</a> | <a href="RiskStrategySet/riskStrategyDeleteAction?riskStrId=<s:property value='riskStrId'/>" onClick="return confirm('确认要删除吗？')">删除</a> 
                    		</td>
						
						
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
<input name="btn_add" type="button" class="right-button" value="新增" onClick="window.location.href='/RiskEvent/RiskStrategySet/StrategyAddUpdate.jsp'"/>

<input type="submit" value="点击" >

  </body>

</html>
