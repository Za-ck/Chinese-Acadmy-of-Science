<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String depId = request.getParameter("depId");
String depNameUnfinished = (String)request.getAttribute("depNameUnfinished");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>未应对的风险事件</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<link rel="stylesheet" type="text/css" href="/RiskEvent/css/webstyle.css">
</head>

<body style="margin:0px;text-align: center">
<form id="UnFinished" name="UnFinished">
	<table id="table"  border="1" cellspacing="0" cellpadding="0" class="linktable" width='100%'>
		<thead align="center">
		<%int num = 0;%>
			<tr>
			<td colspan='12' height='50'><%=depNameUnfinished %></td>
			</tr>
			<tr>
			  <td align="center" width="4%">序号</td>
			  <!--  <td align="center" width="6%">二级风险编号</td>
			  <td align="center" width="10%">二级风险</td>
			  <td align="center" width="4%">一级风险</td>-->
			  <td align="center" width="8%">风险事件名称</td>
			  <td align="center" width="4%">影响程度</td>
			  <td align="center" width="4%">发生概率</td>
			  <td align="center" width="10%">影响的关键绩效指标</td>
			  <td align="center" width="4%">综合评定</td>
			  <td align="center" width="30%">控制措施</td>
			  <td align="center" width="6%">计划落实时间</td>
			  <td align="center" width="10%">投入资源</td>
			</tr>
			<s:iterator value='rmlist'>
            <tr>
				<td align='center'><%=++num%></td>
            	<!--<td align='center'><s:property value='ReId'/></td>
            	<td align='center'><s:property value='RiskName'/></td>
            	<td align='center'><s:property value='RtName'/></td>-->
            	<td align='center'><s:property value='ReEventname'/></td>
            	<td align='center'><s:property value='RiAlldegree'/></td>
            	<td align='center'><s:property value='RiProdegree'/></td>
            	<td align='center'><s:property value='RiKpi'/></td>
            	<td align='center'><s:property value='RiAllvalue'/></td>
            	<td align='center'><s:property value='RiReply'/></td>
            	<td align='center'><s:property value='Riplandate'/></td>
            	<td align='center'><s:property value='Riplanres'/></td>
			</tr>
			</s:iterator>
		</thead>
		<tbody align="center">		
		<s:iterator value="#request.resultList" >	
			<tr>
			    <td align="center"><s:property value="raSort"/></td>
			    <td align="center"><s:property value="raAssessResult"/></td>
		  </tr>
		  </s:iterator>
		  <s:if test="#request.resultList.size()==0">
		  <tr>
			  <td>暂无数据</td>
			  <td>暂无数据</td>
		  </tr>
		  </s:if>
		</tbody>	
	</table>
	</form>
</body>
</html>
