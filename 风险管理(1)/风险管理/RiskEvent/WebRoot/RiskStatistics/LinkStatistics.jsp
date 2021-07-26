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
    
    <title>按环节统计</title>
    
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
<form id="StaQuery" name="StaQuery" action="riskStatistic/linkQueryAction" method="post" onSubmit="checkinfo();">
	<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>环节统计</span></div></div>
  <table class="tablesquery"  width="100%" cellpadding="0"; cellspacing="0";>
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
     					     <a onClick="linkQuery()"><img src="images/mag.png"/><span>统计查询</span></a>
     					     </td>
     					     <td><a  href="riskStatistic/linkExcelAction"  ><img src="images/Excel.png"/><span>导出excel</span></a>
     				</td>
     			</tr>
     		</table>
     		<span style="overflow:auto;">
	<table id="linkTab" width="100%" class="tablestyle">
		<thead>
			<tr style="color:#818181;">
			  <th width="120" height="50">管理规定编码</th>
			  <th width="300">管理规定名称</th>
			  <th width="100">涉及风险编号</th>
			  <th width="120">涉及风险名称</th>
			  <th width="50">涉及部门</th>			   
			  <th width="100">时间跨度选择</th>
			  <th width="60">事件个数	</th>
			  <th width="60">事件个数占比</th>
			  <th width="100">财务损失金额(万元)</th>
			  <th width="100">损失金额占比</th>			  
			</tr>
		</thead>
		<tbody id="tablebody">
		<s:iterator value="linkList2">
		
			<tr>
			  <td><s:property value="fileId"/></td>
			  <td><s:property value="fileName"/></td>
			  <td><s:property value="riskId"/></td>
			  <td><s:property value="riskName"/></td>
			 <td><a class="linkstyle" style="cursor:pointer" onClick="openBox('录入部门如下',350,150,1,'','riskStatistic/depnumLQAction?fileid=<s:property value="fileId"/>&rid=<s:property value="riskId"/>&year=<s:property value="year"/>&quarter=<s:property value="quarter"/>');"><s:property value="indepnum"/></a></td>			  
			  <%--<td><s:property value="year"/>年第<s:property value="quarter"/>季度</td>--%>
              <td>
			  <s:if test='quarter=="5"'>全年</s:if>
              <s:else><s:property value="year"/>年第<s:property value="quarter"/>季度</s:else>
			  </td>
			  <td><s:property value="eventnum"/></td>
			  <td><s:property value="eventHun"/>%</td>
			  <td><s:property value="money"/></td>
			  <td><s:property value="moneyHun"/>%</td>
		  </tr>
		  </s:iterator>

		</tbody>
	</table></span>
	<table align="center">
    <tr>
     		<td align="center">            
     			<img id="barReportImg" alt="" src="<s:property value="fileBarPath"/>" />
     		</td>
		</tr >
		   <tr>
     		<td align="center">管理规定事件柱状图</td>
    </tr>
    </table>
    <input type="text" style="visibility:hidden"  name="totalFlag" id="totalFlag" value="<s:property value="totalFlag"/>"/>
	</form>
</body>
<script type="text/javascript">
senfe('linkTab','#f7f7f7','#fff','#d9ebf5','#e9f2f7');

openBox('统计提示',350,150,0,'正在统计中，请等待....','');
var mytable =  new SortTable('linkTab');
//得到table对象
    var mytable=document.getElementById("linkTab");
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
    
    
    var maxCol = 2, val, count, start;  //maxCol：合并单元格作用到多少列    
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


 
//校验，“小计” 的和 =“总计”
if("N"==document.getElementById("totalFlag").value)
{
	alert("总计栏统计的个数有误");

}
     
function linkQuery(){
openBox('统计提示',350,150,0,'正在统计中，请等待....','');
	StaQuery.action="riskStatistic/linkQueryAction";
	StaQuery.submit();
}
    </script>
    
</html>
