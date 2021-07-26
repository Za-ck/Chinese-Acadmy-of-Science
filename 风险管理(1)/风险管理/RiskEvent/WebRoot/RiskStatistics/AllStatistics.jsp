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
    <title>风险汇总统计</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<script language="javascript" src="IconPage/page.js"></script>	
	<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
    <link rel="stylesheet" type="text/css" href="/RiskEvent/css/webstyle.css">
<script src="/RiskEvent/js/jquery-1.7.1.js"></script>
<script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript" defer="defer"></script> 
<script language=javascript src="/RiskEvent/js/windows.js"></script>

<style>
.Freezing_tdd 
{ 
top:expression(document.getElementById('div-1').scrollTop-1); 
    position:relative ; 
   z-index:100 
} 
.parent	{height:50px; background:#FFF38F;cursor:pointer;color:red}  /* 偶数行样式*/
.odd{ background:#FFFFEE;}  /* 奇数行样式*/
.selected{
background:#FFF38F;
height:50px;
border-bottom:1px solid #000000;
color:#000000;
}
.linkstyle{
cursor:pointer;
color:#1281d3;
}
.linkstyle:HOVER {
	color:#818181;
} 
.linkstyle:ACTIVE {
	color:#000000;
}

</style>

<script type="text/javascript">

$(function(){
	$('tr.parent').click(function(){   // 获取所谓的父行
			$(this)
				.toggleClass("selected")   // 添加/删除高亮
				.siblings('.child_'+this.id).toggle();  // 隐藏/显示所谓的子行
	}).click();
});

</script>
</head>

<body>
<form id="allStaQuery" name="allStaQuery" action="riskStatistic/allQueryAction" method="post">
  <div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>风险汇总统计</span></div></div>
        
	<table class="tablesquery"  width="100%" cellpadding="0" cellspacing="0">
     			<tr> 
     					<td align="center" width="300px" height="32px">查询时间：<s:select name="choosedId" theme="simple" list="#{'0':'--请选择--（默认为录入时间）','1':'录入时间','2':'发布时间'}"  style="width:100px;" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select></td>
     					<td width="60"> 
     					 <input name="year" id="year" style="width:90px;" class="Wdate" value="<s:property value="year"/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy'})"/>
     						</td>     					
     					     					
     					<td align="right" width="60">季度：</td>
     						<td width="120"> <s:select name="quarter" id="quarter"  theme="simple" list="#{'1':'第一季度','2':'第二季度','3':'第三季度','4':'第四季度','5':'全年'}"></s:select>
                             </td>
                             <td width="60" align="center">
                             <!-- riskStatistic/depQueryAction -->
                             <!--<a href="riskStatistic/depQueryAction"><input type="submit" value="" style="width:32px;height:32px;border:0px;background-image:url(images/mag.png)"/><span>查找</span></a> --> 
     					     <a onClick="allQuery()"><img src="images/mag.png"/><span>统计查询</span></a>
     					     </td>
     					     <td><a  href="riskStatistic/allExcelAction"  ><img src="images/Excel.png"/><span>导出excel</span></a>
     					   <a  onclick="allDetail()"><img src="frame/icon/Chart_1.png" style="width:32px;height:32px"/><span>具体信息</span></a>
     				</td>
     			</tr>
     		</table>
     		<div style="height:400px;overflow:auto;">
	<table id="depTab" width="100%" class="tablestyle">
		<thead>
			<tr style="color:#818181;position:relative;top:expression(this.offsetParent.scrollTop);padding:0px;" style="color:#818181;">
			  <th width="120" height="50">部门名称</th>
			  <th width="80">风险分类</th>
			  <th width="80">风险编号</th>
			  <th width="120">风险名称</th>
			  <th width="80">事件编号</th>			   
			  <th width="80">影响指标</th>
			  <th width="200">发生可能性</th>
			  <th width="80">影响业务领域</th>
			  <th width="60">风险来源</th>
			  <th width="60">涉及流程</th>
			  <th width="120">管理责任部门</th>
			</tr>
		</thead>
		<tbody id="tablebody">
		<s:iterator value="allList">
		
			<tr>
			<% String depName = (String)request.getAttribute("depName");
				if(!("总计".equals(depName))){
			%>
			  <td><s:property value="depName"/>(<s:property value="reIndep"/>)</td>
			  <td><s:property value="rtName"/>(<s:property value="reType"/>)</td>
			  <%}else{ %>	
			  <td><s:property value="depName"/></td>
			  <td><s:property value="rtName"/></td>
			  <%} %>		  
			  <td><s:property value="reRiskId"/></td>
			  <td><s:property value="riskName"/></td>
			  
			  <td><s:property value="id.reId"/></td>
			  <td><s:property value="riKpi"/></td>
			  <td><s:property value="riProbability"/></td>
			  <td><s:property value="riBusarea"/></td>
			  <td><s:property value="riSource"/></td>
			  <% if(!("总计".equals(depName))){%>
			  <td><a style="cursor:pointer" onClick="openBox('涉及流程如下',450,200,1,'','riskStatistic/flowAQAction?reid=<s:property value="id.reId"/>');">点击</a></td>			  
			  <%}else{ %>
			  <td></td>
			  <%} %>
			  <td><s:property value="riInchargedep"/></td>			  
		  </tr>
		  </s:iterator>

		</tbody>
	</table></div>
	</form>
</body>
<script type="text/javascript">
senfe('depTab','#f7f7f7','#fff','#d9ebf5','#e9f2f7');

openBox('统计提示',350,150,0,'正在统计中，请等待....','');
//得到table对象
    var mytable=document.getElementById("depTab");
    
    var maxCol = 1, val, count, start;  //maxCol：合并单元格作用到多少列    
    for(var col = maxCol-1; col >= 0 ; col--){
        count = 1;
        val = "";
        for(var i=0; i<mytable.rows.length; i++){
            if(val == mytable.rows[i].cells[col].innerHTML){
                count++;
            }else{
                if(count > 1){ //合并
                    start = i - count;
                    mytable.rows[start].cells[col].rowSpan = count;
                    for(var j=start+1; j<i; j++){
                      //  mytable.rows[j].cells[col].style.display = "none";
                        mytable.rows[j].cells[col].parentNode.removeChild( mytable.rows[j].cells[col]);
                    }
                    count = 1;
                }
                val = mytable.rows[i].cells[col].innerHTML;
            }
        }
        if(count > 1 ){ //合并，最后几行相同的情况下
            start = i - count;
            mytable.rows[start].cells[col].rowSpan = count;
            for(var j=start+1; j<i; j++){
               // mytable.rows[j].cells[col].style.display = "none";
                mytable.rows[j].cells[col].parentNode.removeChild( mytable.rows[j].cells[col]);
            }
        }       
    }
      closeLoginBox(); 
     
function allQuery(){
openBox('统计提示',350,150,0,'正在统计中，请等待....','');
	allStaQuery.action="riskStatistic/allQueryAction";
	allStaQuery.submit();
}
function allDetail(){
allStaQuery.action="riskStatistic/allDetailAction";
	allStaQuery.submit();
}

    </script>
    
</html>
