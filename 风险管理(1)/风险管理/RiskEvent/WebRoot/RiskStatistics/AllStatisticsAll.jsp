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
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
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
})

</script>
</head>

<body>
<form id="allStaQuery" name="allStaQuery" action="riskStatistic/allQueryAction" method="post"> 
	<table class="tablesquery"  width="100%" cellpadding="0"; cellspacing="0";>
     			<tr> 
     			<td><a  onclick="window.history.go(-1)" ><img src="images/backdetail.png" style="width:32px;height:32px"/><span>返回</span></a>
     			<a  href="riskStatistic/allExcelAction" ><img src="images/Excel.png"/><span>导出excel</span></a>
     				</td>
     			 <td align="left">
     				</td>
     			</tr>
     		</table>
     		<div style="height:400px;overflow:auto;">
	<table id="depTab" width="3500" class="tablestyle">
		<thead>
			<tr style="color:#818181;position:relative;top:expression(this.offsetParent.scrollTop);padding:0px;" style="color:#818181;">
			  <th width="120" height="50">部门名称</th>
			  <th width="80">风险分类</th>
			  <th width="80">风险编号</th>
			  <th width="120">风险名称</th>
			  <th width="80">事件编号</th>			   
			  <th width="80">影响的关键绩效指标</th>
			  <th width="200">发生可能性</th>
			  <th width="150">影响的业务领域</th>
			  <th width="80">风险来源</th>
			  <th width="80">涉及流程</th>
			  <th width="100">管理责任部门</th>
			  <th width="150">财务方面的影响描述</th>
			  <th width="150">声誉方面的影响描述</th>
			  <th width="150">对法律法规的影响描述</th>
			  <th width="150">对客户关系的影响描述</th>
			  <th width="150">员工满意度的描述</th>
			  <th width="150">对运营的影响描述</th>
			  <th width="150">对人员健康、安全、环境方面的影响描述</th>
			  <th width="150">财务方面的影响</th>
			  <th width="150">声誉方面的影响</th>
			  <th width="150">对法律法规的影响</th>
			  <th width="150">对客户关系的影响</th>
			  <th width="150">员工满意度的影响</th>
			  <th width="150">对运营的影响</th>
			  <th width="150">对人员健康、安全、环境方面的影响</th>
			  <th width="150">机会风险</th>			  
			  <th width="150">风险可控性</th>
			  <th width="150">应对策略及措施</th>
			  <th width="150">针对该风险的控制指标警戒值</th>
			  
			  	  
			</tr>
		</thead>
		<tbody id="tablebody">
		<s:iterator value="allList">
		
			<tr>
			  <td><s:property value="depName"/>(<s:property value="reIndep"/>)</td>
			  <td><s:property value="rtName"/>(<s:property value="reType"/>)</td>			  
			  <td><s:property value="reRiskId"/></td>
			  <td><s:property value="riskName"/></td>
			  
			  <td><s:property value="id.reId"/></td>
			  <td><s:property value="riKpi"/></td>
			  <td><s:property value="riProbability"/></td>
			  <td><s:property value="riBusarea"/></td>
			  <td><s:property value="riSource"/></td>
			  <td><a class="linkstyle" style="cursor:pointer" onclick="openBox('涉及流程如下',450,200,1,'','riskStatistic/flowAQAction?reid=<s:property value="id.reId"/>');">点击</a></td>			  
			  <td><s:property value="riInchargedep"/></td>
			  
			  <td><s:property value="riFinance"/>;金额：<s:property value="riMoney"/></td>
			  <td><s:property value="riFame"/></td>
			  <td><s:property value="riLaw"/></td>
			  <td><s:property value="riClient"/></td>
			  <td><s:property value="riEmployee"/></td>			             
			  <td><s:property value="riOperation"/></td>
			  <td><s:property value="riSafe"/></td>
			  
			  <td><s:property value="riFincriteria"/></td>
			  <td><s:property value="riFamecriteria"/></td>
			  <td><s:property value="riLawcriteria"/></td> 
			  <td><s:property value="riClicriteria"/></td>
			  <td><s:property value="riEmpcriteria"/></td>
			  <td><s:property value="riOpecriteria"/></td> 
			  <td><s:property value="riSafecriteria"/></td>
			   
			  <td><s:property value="rmChance"/></td>
			  <td><s:property value="rmControl"/></td>
			  <td>基本态度：<s:property value="rmStrategy"/>;应对措施:<s:property value="rmReply"/>;计划投入资源:<s:property value="rmPlanres"/>;时间计划:<s:property value="rmPlandate"/></td> 
			  <td><s:property value="rmWarnvalue"/></td>
		  </tr>
		  </s:iterator>

		</tbody>
	</table></div>
	</form>
</body>
<script type="text/javascript">
senfe('depTab','#f7f7f7','#fff','#d9ebf5','#e9f2f7');

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
     
     
function allQuery(){
	allStaQuery.action="riskStatistic/allQueryAction";
	allStaQuery.submit();
}
    </script>
    
</html>
