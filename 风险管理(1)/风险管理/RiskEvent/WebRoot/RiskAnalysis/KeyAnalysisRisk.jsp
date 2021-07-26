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
    
    <title>按风险类型分析</title>
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

<body onload="getRiskData();">
<form id="KeyRiskAnalysis" name="KeyRiskAnalysis" action="riskAnalysis/KeyAnalysisRiskAction" method="post">
  <div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>按风险类型分析</span></div></div> 
<table class="tablesquery"  width="100%" cellpadding="0"; cellspacing="0";>
  <tr> 
 
       <td width="7%"> 综合评定：</td>
       <td width="5%"><input name="riAllValueFrom"   style="width:50px" id="riAllValueFrom" value="<s:property value="riAllValueFrom"/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"/></td>
       <td width="3%"> 至：</td> 
       <td width="6%" align="left">
       <input name="riAllValueTo" id="riAllValueTo" style="width:50px" value="<s:property value="riAllValueTo"/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" />
       </td>  
       <td width="7%">影响程度：</td>
       <td width="5%"><input name="riDegreeFrom" id="riDegreeFrom" style="width:50px" value="<s:property value="riDegreeFrom"/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"/></td>
       <td width="3%"> 至： </td>
       <td width="7%" align="left"> 
       <input name="riDegreeTo" id="riDegreeTo" style="width:40" value="<s:property value="riDegreeTo"/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" />
       </td> 
        <td width="6%" align="right"> 发生概率：</td>
        <td width="9%" colspan="2"> 
        <s:select name="riProDegree" theme="simple" style="width:90" list="allProDegreeList" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>
        </td>
        <td width="10%" align="right">风险类别：</td>
			 <td width="30%" colspan="3"><span class="red">
			    <s:select name="risktype" theme="simple" list="allrtList" listValue="rtName" listKey="rtId" class="text" style="width:80px"  onchange="getRiskData()"></s:select>                                           
                *</span>
			    <span class="red">
			     <select name="riskname"  style="width:200px">					    
                     <option value="<s:property value='riskname'/>"><s:property value="riskname" /></option>
            	 </select>
            	*</span>
	    </td>
	    	<td><input type="hidden" name="riskname2" value="<s:property value="riskname"/>"></td>
	     </tr>
	     <tr>
	     <%
		HttpSession se = request.getSession();
		String pw = se.getAttribute("power").toString();				
        if("0".equals(pw))
     	{%>
     	<td width="7%">录入部门：</td>	
     	<td colspan="5">
     	<input  name="riDepName" id="riDepName" style="width:280px" value="<s:property value='riDepName'/>" disabled="disabled"/>
     	</td>
        <%}else{
     	 %>
     	<td width="6%">录入部门：</td>	
     	<td colspan="5">
     	<s:select name="riDepName" theme="simple" list="alldepList" listValue="depName" listKey="depName" ></s:select>
     	</td>	
		<%} %>
		 <td colspan="2" width="8%" align="right">查询时间：</td>
		<td width="7%"><s:select name="choosedId" theme="simple" list="#{'0':'--请选择--','1':'录入时间','2':'发布时间'}" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>
		</td>
        <td width="7%">	
        <input name="dateFrom" style="width:90px" id="dateFrom" class="Wdate" value="<s:property value='dateFrom'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/>
        </td>
        <td width="3%"> 至：
        </td>  
        <td width="7%" align="left">
        <input name="dateTo" style="width:90px" id="dateTo" class="Wdate" value="<s:property value='dateTo'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/>
        </td>   
         
        <td align="center" colspan="2"><a onclick="queryByValue();"><img src="images/mag.png"/><span>统计查询</span></a></td>
        <td align="center" colspan="2">
        <a href="riskAnalysis/KeyAnaRiskExcelAction" ><img src="images/Excel.png"/><span>导出Excel</span></a>	
       </td><!-- 
         <td width="7%" align="right">
        <a href="riskAnalysis/KeyAnaRiskStaExcelAction" ><img src="images/Excel.png"/><span>导出标准Excel</span></a>	
       </td>	 	
        
	--></tr>
     </table>
	<table id="depTab" width="100%" class="tablestyle">
		<thead>
			<tr>			
			  <th>风险序号</th>
			  <th>一级风险</th> 
              <th>二级风险</th>
              <th>录入部门</th>  
              <th>风险事件编号</th>   
			  <th>风险事件名称</th>
			  <th>影响程度</th> 
			  <th>发生概率</th> 		  
			  <th>影响的关键绩效指标</th>
			  <th><a onclick="openTab();" style="color: red; text-decoration:none;">综合评定</a></th>
			  <th>风险事件时间</th>
		    </tr> 
		</thead>
		<tbody id="tablebody">
		<s:iterator value="ralist">
		
			<tr title="双击查看风险事件信息" onDblClick="showModelessDialog('riskAnalysis/GenRiskDetailAction?reId=<s:property value='reId'/>&riNum=<s:property value='riNum'/>',window,'status:false;dialogWidth:800px;dialogHeight:250px;resizable:yes;scroll:yes');">
			  <td><s:property value="riNum"/></td>
			  <td><s:property value="rtName"/></td>
			  <td><s:property value="riskName"/></td>
			  <td><s:property value="depName"/></td>
			  <td><s:property value="reId"/></td>
			  <td><s:property value="reEventname"/></td>
			  <td><s:property value="riAlldegree"/></td>
			  <td><s:property value="riProdegree"/></td>
			  <td><s:property value="riKpi"/></td>
			  <td><s:property value="riAllvalue"/></td>
			  <td><s:property value="riEventDate"/></td>
			  
		  </tr>
		  </s:iterator>
		</tbody>
	</table>
     	<table align="center" width="100%" >
     		<tr>
     		<td colspan="6" align="right">
	           	<table class="tablesquery"  align="right">
	           	<td>
                <a href="riskAnalysis/keyRiskReportAction"  style="width:120px;"><img  width="32" height="32" src="images/Word.png"/><span>导出统计图</span></a>	
                 </td>
                 </table> 
                 </td> 
     		</tr>
     		<tr><td colspan="6">&nbsp;&nbsp;</td></tr>		
		<tr >     		
     		<td align="center"><font style="font-family: 宋体;font-weight: bold;font-size: 20px">表1：二级风险概率统计表</font></td>
     	</tr>
     	<tr>
     		<td colspan="6" align="center">
                <table align="center" width="100%">                
                <tr>
                	<td>
				<table id="detailTab" width="800px" class="tablestyle" align="center">
					<thead>
					<tr>	
			  			<th>风险序号</th>
						<th>风险名称</th> 
              			<th>风险事件数</th>
              			<th>实时概率（次/年)</th>
              			<th>填报概率（次/年）</th>
		    		</tr> 
					</thead>
					<tbody id="detailTabBody">
					<s:iterator value="reportList">
					<tr bgcolor="#e9f2f7">
			 			<td><s:property value="riskQueue"/></td>
			  			<td><s:property value="riskName"/></td>
			  			<td><s:property value="riskNum"/></td>
			 			<td><s:property value="freq"/></td>
			 			<td><s:property value="reportFreq"/></td>
		  			</tr>
		  			</s:iterator>
		  			</tbody>
				</table>
                </td>
	     		</tr>
	     		</table>
     		</td>     		
     		</tr>
     		<tr><td>&nbsp;</td></tr>
     		<tr><td>&nbsp;</td></tr>
     		<tr>
     			<td align="center">
     				<table>
     				<tr>
     		 			<td>            
     					<img id="barReportImg" alt="" src="<s:property value="barPath"/>"/>
     					</td>
     				</tr>
     			</table>
     			</td>
     		</tr>
     		<tr >     		
     		<td align="center"><font style="font-family: 宋体;font-weight: bold;font-size: 20px;">图1：二级风险事件数柱状图</font></td>
     	   </tr>
     		<tr><td>&nbsp;</td></tr>
     		<tr>
     		<td align="center">
     		<table>
     		 <tr>
     		 <td>            
     			<img id="barReportImg2" alt="" src="<s:property value="barPath2"/>"/>
     		</td>
     		</tr>
     		</table>
     		</td>
     		</tr>
     		<tr >     		
     		<td align="center"><font style="font-family: 宋体;font-weight: bold;font-size: 20px;">图2：二级风险综合评定值柱状图</font></td>
     	   </tr>
     		</table> 
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
   var START_ROW=1;//合并开始行号，表示从第一行开始合并
   for(var i=START_ROW;i<index;i++)
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
    
    var maxCol = 4, val, count, start;  //maxCol：合并单元格作用到多少列    
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
     
var detail=document.getElementById('areadetail').value;
var arrayd=detail.split("；");
var showid=document.getElementById('show');

arrayd[0]="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp"+arrayd[0];
for(var i=0;i<arrayd.length-1;i++)
{

arrayd[i]=arrayd[i]+"；"+"<br/>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
showid.innerHTML=showid.innerHTML+arrayd[i];//添加描述
}
showid.innerHTML=showid.innerHTML+arrayd[arrayd.length-1];//加入最后一段描述
      
  


function getRiskData(){
           var tempname=document.getElementById("risktype").value;
      		var riskname2 = document.getElementById("riskname2").value;
      		//alert(riskname2);
           $.ajax({
               url: 'default/ajaxRiskAction?tmprisk=' + tempname+'&tmpnum='+ Math.random().toString(),
               type: 'post',
               dateType: 'json',
               data: '',
               contentType: 'text/html;charset=utf-8',
               success: function (data) {     
              var data1 = eval("("+ data+")");
              var ObjTarget=document.getElementById("riskname");
              ObjTarget.innerHTML="";
              var opt2=document.createElement("option");   
                   opt2.value="请选择";   
                     opt2.text="请选择";  
                        ObjTarget.add(opt2); 
               for(var i=0;i<data1.length;i++)
               {   
                 var opt=document.createElement("option");   
                   opt.value=data1[i].riskName;   
                     opt.text=data1[i].riskName;  
                        ObjTarget.add(opt); 
                  if(opt.text == riskname2) {
                	  opt.selected = true;
                  }
                }
               },
               error: function () {
               alert("error");
           }
       });
       }

function queryByValue(){
    openBox('统计提示',350,150,0,'正在统计中，请等待....','');
	KeyRiskAnalysis.action="riskAnalysis/KeyAnalysisRiskByValueAction";
	KeyRiskAnalysis.submit();
}

function reportExport(){
	KeyRiskAnalysis.action="riskAnalysis/keyRiskReportAction";
	KeyRiskAnalysis.submit();
}

function openTab()
{	
		var url = "riskAnalysis/KeyAnaAllRiskAction";
		window.parent.openTab(url,"按风险类型（关键）-详细");
}

</script>
    
</html>
