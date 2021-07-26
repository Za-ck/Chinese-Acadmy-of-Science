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
    
    <title>按部门分析（关键）-详细列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equi	v="expires" content="0">    
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
<form id="GenDepAnalysis" name="GenDepAnalysis" action="riskAnalysis/GenAnalysisDepAction" method="post">
  <div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>按部门分析（关键）-详细列表</span></div></div>
        
  <table class="tablesquery"  width="100%" cellpadding="0"; cellspacing="0";>
  <tr> 
 
       <td style="width:60"> 综合评定：
       </td>
       <td style="width:60">
       <input name="riAllValueFrom" id="riAllValueFrom" style="width:45px" value="<s:property value="riAllValueFrom"/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"/>
       </td>
       <td style="width:10"> 至：</td> 
       <td style="width:60">
       <input name="riAllValueTo" id="riAllValueTo" style="width:45px"value="<s:property value="riAllValueTo"/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" />
       </td>  
       <td style="width:60">影响程度：</td>
       <td style="width:60">
       <input name="riDegreeFrom" id="riDegreeFrom" style="width:45px" value="<s:property value="riDegreeFrom"/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"/>
       </td>
       <td style="width:10">至： </td>
       <td style="width:60"> 
       <input name="riDegreeTo" id="riDegreeTo" style="width:45px" value="<s:property value="riDegreeTo"/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" />
        </td> 
         <td style="width:60"> 发生概率：</td>
        <td style="width:60"> 
        <s:select name="riProDegree" theme="simple" style="width:90px" list="allProDegreeList" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>
        </td>
        <%
		HttpSession se = request.getSession();
		String pw = se.getAttribute("power").toString();				
        if("0".equals(pw))
     	{%>
     	<td style="width:60"> 录入部门：</td>
     	<td> 
     	<input  name="riDepName" id="riDepName" style="width:280px" value="<s:property value='riDepName'/>" disabled="disabled"/>
     	</td>
        <%}else{
     	 %>
     	<td style="width:60">录入部门：</td>	
     	<td style="width:60">
     	<s:select name="riDepName" theme="simple" style="width:280px" list="alldepList" listValue="depName" listKey="depName" ></s:select>
     	</td>	
		<%} %>
        <td style="width:60">录入时间：
        </td>
        <td style="width:60">	
        <input name="dateFrom" id="dateFrom" class="Wdate" style="width:90px" value="<s:property value='dateFrom'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/>
        </td>
        <td style="width:10">至：
        </td>  
        <td>
        <input name="dateTo" id="dateTo" class="Wdate" style="width:90px" value="<s:property value='dateTo'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/>
        </td>  
        <td style="width:30">
        <input type="button" name="buttonQuery" id="buttonQuery" value="查询" onClick="queryByValue();" />
        </td>	
       	<td style="width:60">
        <a href="riskAnalysis/KeyAnaAllDepExcelAction" ><img src="images/Excel.png"/><span>导出Excel</span></a>	
       </td> 
 	
	</tr>
     </table>
 


    <div style="height:400px;overflow:auto;">
   
	<table id="depTab" width="100%" class="tablestyle">
		<thead>
		 
		
			<tr style="position:relative;top:expression(this.offsetParent.scrollTop);padding:0px;">
			
		<th>部门序号</th>
              <th>录入部门</th>
              <th>二级风险序号</th> 
              <th>二级风险</th>	
              <th>一级风险</th> 
              <th>风险事件编号</th>   
			  <th>风险事件名称</th>
			  <th>影响程度</th>
			  <th>发生概率</th>  
			  <th>影响的关键绩效指标</th>
			    
			  <th>对财务的影响</th>
			  <th>对声誉的影响</th>
			  <th>对法律法规的影响</th>
			  <th>对客户关系的影响</th>
			  <th>对员工满意度的影响</th>
			  <th>对运营的影响</th>
			  <th>对安全、健康和环境的影响</th>
			  
			  <th>财务评定</th>
			  <th>声誉评定</th>
			  <th>合规评定</th>
			  <th>客户关系评定</th>
			  <th>员工满意度评定</th>
			  <th>运营评定</th>
			  <th>安全评定</th>
			  <th>综合评定</th>
			  <th>风险事件录入时间</th> 
			 
		    </tr> 
		</thead>
		<tbody id="tablebody">
		<s:iterator value="ralist">
		
			<tr>
			   <td><s:property value="depNum"/></td>
			  <td><s:property value="depName"/></td>
			  <td><s:property value="riNum"/></td>
			  <td><s:property value="riskName"/></td>
			  <td><s:property value="rtName"/></td>
			  <td><s:property value="reId"/></td>
			  <td><s:property value="reEventname"/></td>
			  <td><s:property value="riAlldegree"/></td>
			  <td><s:property value="riProdegree"/></td>
			  <td><s:property value="riKpi"/></td>
			   
			  <td><s:property value="riFindegree"/></td>
			  <td><s:property value="riFamedegree"/></td>
			  <td><s:property value="riLawdegree"/></td>
			  <td><s:property value="riClidegree"/></td>
			  <td><s:property value="riEmpdegree"/></td>
			  <td><s:property value="riOpedegree"/></td>
			  <td><s:property value="riSafedegree"/></td>

			  
			  <td><s:property value="riFinvalue"/></td>
			  <td><s:property value="riFramevalue"/></td>
			  <td><s:property value="riLawvalue"/></td>
			  <td><s:property value="riClivalue"/></td>
			  <td><s:property value="riEmpvalue"/></td>
			  <td><s:property value="riOpevalue"/></td>
			  <td><s:property value="riSafevalue"/></td>
			  <td><s:property value="riAllvalue"/></td>
			  <td><s:property value="riEventDate"/></td>
			  
		  </tr>
		  </s:iterator>

		</tbody>
	</table>

	</div>
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
   var START_ROW=1;//合并开始行号，表示从第三行开始合并
   for(var i=START_ROW;i<index;i++)
   {
   // var text1_0 = mytable.rows[i].cells[0].innerText.replace(" ","");//获取行的第一个单元格firstChild中的内容
    var text1 = mytable.rows[i].cells[0].innerText.replace(" ","");//获取行的第一个单元格firstChild中的内容
    if(text1 == ""){
    	text1=mytable.rows[i].cells[0].innerText;}
    
   // if(text1_0 != text1) alert("text1_0="+text1_0+"------"+"text1="+text1);
     mytable.rows[i].className="child_row_"+num;
      var j=i+1;
     if(j<index)//如果下一行不是最后一行
    { 
    	 //var text2_0 = mytable.rows[j].cells[0].innerText.replace(" ","");//获取行的第一个单元格firstChild中的内容
    	var text2 = mytable.rows[j].cells[0].innerText.replace(" ","");
    	   if(text2 == ""){
    	text2=mytable.rows[j].cells[0].innerText;}
    	// if(text2_0 != text2) alert("text2_0="+text2_0+"------"+"text2="+text2);
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
    
    var maxCol = 5, val, count, start;  //maxCol：合并单元格作用到多少列    
    for(var col = maxCol-1; col >= 0 ; col--){
        count = 1;
        val = "";
        for(var i=START_ROW; i<mytable.rows.length; i++){
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
     


function queryByValue(){
    openBox('统计提示',350,150,0,'正在统计中，请等待....','');
	GenDepAnalysis.action="riskAnalysis/KeyAnaAllDepAction";
	GenDepAnalysis.submit();
}
    </script>
    
</html>
