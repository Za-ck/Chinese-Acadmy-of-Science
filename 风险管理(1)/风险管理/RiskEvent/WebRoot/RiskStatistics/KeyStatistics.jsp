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
    
    <title>关键风险统计</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script language="javascript" src="IconPage/page.js"></script>
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

.tablestyle{
    overflow:auto;
	font-size:12px;
    border-collapse:collapse;
	border:2px solid #dddddd;
}
.tablestyle td {border:1px solid #e2e1e1;text-align:center; height:25px;}
.tablestyle thead th {
	color:#818181;
	height:30px;
	border:1px solid #dadbdb;
	font-size:15px;
    font-weight:lighter;
	text-align:center;
	background-image: url("/RiskEvent/IconPage/images/thbg2.png");
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
<form id="keyStaQuery" name="keyStaQuery" action="riskStatistic/keyQueryAction" method="post">
  <div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>关键风险统计</span></div></div>
        
	<table class="tablesquery"  width="100%" cellpadding="0"; cellspacing="0";>
     			<tr> 
     				<td align="right" width="150px" height="32px">录入年份：</td>
     					<td width="60"> 
     					 <input name="year" id="year" style="width:90px;" class="Wdate" value="<s:property value="year"/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy'})"/>
     						</td>     					
     					     	<td width="40">	</td>	
                             <td width="60" align="center">
                             <a onClick="keyQuery()"><img src="images/mag.png"/><span>统计查询</span></a>
     					     </td>
     					     <td><a  href="riskStatistic/keyExcelAction"  ><img src="images/Excel.png"/><span>导出excel</span></a>
     				</td>
     			</tr>
     		</table>
     		<div style="height:400px;overflow:auto;">
	<table id="depTab" width="100%" class="tablestyle">
		<thead>
			<tr  style="position:relative;top:expression(this.offsetParent.scrollTop);padding:0px;">
			  <th width="100" >部门</th>
			  <th width="150" colspan="6">风险描述</th>
			  <th colspan="7">风险影响程度</th>
               <th colspan="8">风险重要性评定</th>
		    </tr> 
        <tr  style="position:relative;top:expression(this.offsetParent.scrollTop);padding:0px;">
				<th width="100">部门名称</th>
				<th width="30">序号</th>
                <th width="120">风险类型</th>
                <th>时间跨度选择</th>
                <th width="80">事件编号</th>
                <th>详细情况</th>
                <th width="30">可能性/概率</th>
                <th width="30">财务影响</th>		
                <th width="30">声誉影响</th>
			    <th width="30">合规影响</th>
			    <th width="30">客户关系影响</th>
			    <th width="30">员工满意度影响</th>
			    <th width="30">运营影响</th>
			    <th width="30">安全影响</th>
                <th width="30">财务评定</th>					
                <th width="30">声誉评定</th>
                <th width="30">合规评定</th>
                <th width="30">客户关系评定</th>
                <th width="30">员工满意度评定</th>
                <th width="30">运营评定</th>
                <th width="30">安全评定	</th>
                <th width="30">综合评定</th>
                
            </tr>
		</thead>
		<tbody id="tablebody">
		<s:iterator value="ksList2"> 		<!-- 迭代ksList2集合中所有数据	-->
		<tr>
			  <td><s:property value="depname"/></td>
			   <td><s:property value="number"/></td>		
			  <td><s:property value="riskName"/></td>			  
			  <td><s:property value="year"/></td>
			  <td><s:property value="reId"/></td>			  
			  <td><s:property value="detail"/></td>
			  <td><s:property value="riProdegree"/></td>
			  
			  <td><s:property value="riFindegree"/></td>
			  <td><s:property value="riFamedegree"/></td>
			  <td><s:property value="riLawdegree"/></td>
			  <td><s:property value="riClidegree"/></td>
			  <td><s:property value="riEmpdegree"/></td>
			  <td><s:property value="riOpedegree"/></td>
			  <td><s:property value="riSafedegree"/></td>
			  
			  <td><s:property value="riFinvalue"/></td>
			  <td><s:property value="riFamevalue"/></td>			             
			  <td><s:property value="riLawvalue"/></td>
			  <td><s:property value="riClivalue"/></td>
			  <td><s:property value="riEmpvalue"/></td>			             
			  <td><s:property value="riOpevalue"/></td>
			  <td><s:property value="riSafevalue"/></td>
			  
			  <td><s:property value="riAllvalue"/></td>
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
    //向table中插入一行
   var index = mytable.rows.length; 
   var num=1;
   for(var i=1;i<index;i++)
   {
    var text1 = mytable.rows[i].cells[0].innerText.replace(" ","");//获取行的第一个单元格firstChild中的内容
     mytable.rows[i].className="child_row_"+num;
      var j=i+1;
     if(j<index)//如果下一行不是最后一行
    { 
    var text2 = mytable.rows[j].cells[0].innerText.replace(" ","");
     if(!(text1==text2))
   {  
    mytable.rows[j-1].className="parent";
    mytable.rows[j-1].id="row_"+num;
    num++;
    }
    }
    }
    if(j==index)
    {
    mytable.rows[j-1].className="parent";
    mytable.rows[j-1].id="row_"+num;
    }
    
    var maxCol = 3, val, count, start;  //maxCol：合并单元格作用到多少列    
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
     
function keyQuery(){
openBox('统计提示',350,150,0,'正在统计中，请等待....','');
	keyStaQuery.action="riskStatistic/keyQueryAction";
	keyStaQuery.submit();
}
    </script>
    
</html>
