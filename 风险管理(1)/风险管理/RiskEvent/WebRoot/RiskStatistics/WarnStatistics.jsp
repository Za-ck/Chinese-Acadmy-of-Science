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
    
    <title>按预警值统计</title>
    
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
<form id="StaQuery" name="StaQuery" action="riskStatistic/depQueryAction" method="post">
	<table class="tablesquery"  width="100%" cellpadding="0"; cellspacing="0";>
     			<tr> 
     				<td align="right" width="150px" height="32px">录入年份：</td>
     					<td width="60"> 
     					 <input name="year" id="year" style="width:90px;" class="Wdate" value="<s:property value="year"/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy'})"/>
     						</td>     					
     					     					
     					<td align="right" width="60">季度：</td>
     						<td width="120"> <s:select name="quarter" id="quarter"  theme="simple" list="#{'1':'第一季度','2':'第二季度','3':'第三季度','4':'第四季度'}"></s:select>
                             </td>
                             <td width="60" align="center">
                             <!-- riskStatistic/depQueryAction -->
                             <!--<a href="riskStatistic/depQueryAction"><input type="submit" value="" style="width:32px;height:32px;border:0px;background-image:url(images/mag.png)"/><span>查找</span></a> --> 
     					     <a onclick="depQuery()"><img src="images/mag.png"/><span>统计查询</span></a>
     					     </td>
     					     <td><a  href="riskStatistic/depExcelAction"  ><img src="images/Excel.png"/><span>导出excel</span></a>
     				</td>
     			</tr>
     		</table>
     		<div style="height:400px;overflow:auto;">
	<table id="depTab" width="100%" class="tablestyle">
		<thead>
			<tr style="color:#818181;position:relative;top:expression(this.offsetParent.scrollTop);padding:0px;" style="color:#818181;">
			  <th width="150" height="50">风险名称</th>
			  <th width="150">风险事件编号</th>
			  <th width="200">风险时间填写事件</th>
			  <th width="100">影响的关键绩效指标</th>
			  <th width="150">录入事件部门名称</th>			   
			  <th width="150">管理责任部门</th>
			  <th width="150">风险控制指标警戒值</th>  
			</tr>
		</thead>
		<tbody id="tablebody">
		<s:iterator value="">
		
			<tr>
			  <td><s:property value=""/></td>
			  <td><s:property value=""/></td>
			  <td><s:property value=""/></td>
			  <td><s:property value=""/></td>
			  <td><s:property value=""/></td>
			  <td><s:property value=""/></td>
			  <td><s:property value=""/></td>
		  </tr>
		  </s:iterator>

		</tbody>
	</table></div>
	</form>
</body>
<script type="text/javascript">
senfe('depTab','#f7f7f7','#fff','#d9ebf5','#e9f2f7');

var mytable =  new SortTable('depTab');
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
     
     
function depQuery(){
	StaQuery.action="riskStatistic/depQueryAction";
	StaQuery.submit();
}
    </script>
    
</html>
