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
    
    <title>待审核风险事件</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<style type="text/css"></style>
	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
	<script language="javascript" src="IconPage/page.js"></script>
	<script language="javascript" src="IconPage/jquery-1.3.2.min.js"></script>
	<script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
	<script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript" defer="defer"></script> 
	
</head>

<body style="font-size:12px; margin-top:5px;">

<form id="riskStatusQuery" name="riskStatusQuery" method="post" action="riskFlow/RiskStatusAction" onSubmit="return doValidate('riskStatusQuery')">

<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>待审核风险事件</span></div></div>
<div id='pagetable'> 
    <table id='mytable' class='tablestyle' width='100%' height='100%' ><thead>
    	<tr style="background-image:url(IconPage/images/thbg.png)">				
						<td width="10%" align="center" height="30">序号</td>
                		<td width="10%" align="center">风险事件编号</td>
                		<td width="10%" align="center">录入部门</td>
                		<td width="10%" align="center">状态</td>
                		<td width="10%" align="center">填写人</td>
                		<td width="10%" align="center">填写日期</td>
		        		<td width="10%" align="center">操作</td>
						<%int num=0;%>
            		</tr>
             		<tbody id="body_RiskEventInputQuery">
            		<%--<s:iterator value="reList">--%>
					<s:iterator value="#session.Out_reList">
            		<%--<tr align="center" >--%>
					<tr title="双击查看风险事件信息" align="center" onDblClick="window.location.href='riskInput/REIQReadAction?reId=<s:property value='reId'/>'">
						
						<td height="25" align="center"><%=++num%></td>
            			<td align="center"><s:property value="reId"/></td>
            			<td align="center"><s:property value="department.depName"/></td>
            			<td align="center"><a href="riskFlow/RiskFlowChart?riskId=<s:property value='reRiskId'/>&resId=<s:property value="reId"/>&reState=<s:property value='reState'/>">
                      <s:if test='reState=="*"'>
                                                              已发布</s:if>
            			<s:elseif test="reAct=='0'">
            			<s:if test='reState==0'>
                                                              未修改</s:if>
                        <s:else>
            			未通过
            			</s:else>
            			</s:elseif>          
            			<s:else>
            			<s:if test='reState==1'>
                                                              已提交</s:if>
                        <s:else>
            			等待审核中...
            			</s:else>
            			</s:else>
            			</a>
            			</td>
            			<td align="center"><s:property value="reCreator"/></td>
            			<td align="center"><s:property value="reDate"/></td>
						<td align="center">
                    		<a href="riskFlow/DepVerifyAction?riskeventid=<s:property value='reId'/>" onClick="">审批</a> 
                    		</td>
            		</tr>
            		</s:iterator>
            		</tbody>
        		</table>
        		</div>
     			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        			<tr>
          				<td height="6"><img src="/RiskEvent/images/main/spacer.gif" width="1" height="1" /></td>
        			</tr>
				</table>
				  <div id='pageDIV' style="background-image:url(IconPage/images/bg.png)">
                    <a onClick="document.forms[0].action='riskFlow/RiskStatusAction?current_pagenum=1&pageNum=10';document.forms[0].submit();"><img border='0' title='首页' src='IconPage/images/first.gif'/></a>
                    <a onClick="prepage()"><img border='0'title='上一页' src='IconPage/images/prev.gif'/></a>
                    <img border='0' src='IconPage/images/line.gif'/><span>&nbsp;&nbsp;&nbsp;&nbsp;第</span>
                    <input id='current_pagenum' style='width:45px' type='text' value="<%=request.getSession().getAttribute("current_pagenum")%>"/> <span>页&nbsp;&nbsp;</span>
                    <img border='0' src='IconPage/images/hl.png'/><span>共
                  <%
                     int pc=Integer.valueOf(request.getSession().getAttribute("pagecount").toString());
                     int pg=0;
                     if(pc%10==0){ pg=pc/10;out.print(pg);}
                     else {pg=(pc/10)+1;out.print(pg);}
                  %>页&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;<img border='0' src='IconPage/images/line.gif'/>
                  <a onClick="nextpage()"><img border='0' title='下一页' src='IconPage/images/next.gif'/></a>&nbsp;&nbsp;
                  <a onClick="document.forms[0].action='riskFlow/RiskStatusAction?current_pagenum=<%=pg%>&pageNum=10';document.forms[0].submit();"><img border='0' title='尾页' src='IconPage/images/last.gif'/></a>&nbsp;&nbsp;
                 <img border='0' src='IconPage/images/line.gif'/>&nbsp;&nbsp;&nbsp;&nbsp;
                 <a onClick="document.forms[0].action='riskFlow/RiskStatusAction?current_pagenum='+current_pagenum.value+'&pageNum=10';document.forms[0].submit();"><img border='0' style='cursor:pointer;' title='跳转' src='IconPage/images/load.png'/></a>&nbsp;&nbsp;&nbsp;&nbsp;<img border='0' src='IconPage/images/line.gif'/>
&nbsp;&nbsp;&nbsp;&nbsp;<span>共有&nbsp;&nbsp;<%=request.getSession().getAttribute("pagecount")%>&nbsp;&nbsp;条记录,当前第&nbsp;&nbsp;<%=request.getSession().getAttribute("current_pagenum")%>/<%=pg%>页</span>
</div>
        
     </form>
     <script>
<%
  String params=request.getParameter("operator");
  String[] temps=null;
  String operation="",result="";
  if(params!=null){
	 temps=params.split("\\*");	 
      operation=temps[0];
      result=temps[1].split("=")[1];
 }
%>
var operation="<%=operation%>";
var result="<%=result%>";
if(operation!=""){
	if((operation=="flowto")&&(result=="success")) alert("提交成功!");
	else if((operation=="flowto")&&(result=="failget")) alert("策略已经被更改，建议重新录入!");
}
     function prepage(){
    	var current_page = document.getElementById("current_pagenum");
    	current_page=(current_page.value)*1-1;
    	if(current_page==0)	return;
    	document.forms[0].action='riskFlow/RiskStatusAction?current_pagenum='+current_page+'&pageNum=10';
    	document.forms[0].submit();
     }
     function nextpage(){
    	var current_page = document.getElementById("current_pagenum");
    	current_page=(current_page.value)*1+1;   	
    	document.forms[0].action='riskFlow/RiskStatusAction?current_pagenum='+current_page+'&pageNum=10';
    	document.forms[0].submit();
     };
     </script>

</body>
</html>
