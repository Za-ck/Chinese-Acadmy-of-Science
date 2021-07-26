<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<base href="<%=basePath%>">

<title>按风险类型分析</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">


<script type="text/javascript" src="IconPage/page.js"></script>
<link rel="stylesheet" type="text/css"
	href="/RiskEvent/css/webstyle.css">
<script src="/RiskEvent/js/jquery-1.7.1.js"></script>
<script src="/RiskEvent/js/DatePicker/WdatePicker.js"
	type="text/javascript"></script>
<script type="text/javascript" src="/RiskEvent/js/windows.js"></script>

<style>
.Freezing_tdd {
	top: expression(document.getElementById (     'div-1').scrollTop-1 );
	position: relative;
	z-index: 100
}

.parent {
	height: 50px;
	background: #FFF38F;
	cursor: pointer;
	color: red
} /* 偶数行样式*/
.odd {
	background: #FFFFEE;
} /* 奇数行样式*/
.selected {
	background: #FFF38F;
	height: 50px;
	border-bottom: 1px solid #000000;
	color: #000000;
}

.tablestyle {
	overflow: auto;
	font-size: 12px;
	border-collapse: collapse;
	border: 2px solid #dddddd;
}

.tablestyle td {
	border: 1px solid #e2e1e1;
	text-align: center;
	height: 25px;
}

.tablestyle thead th {
	color: #818181;
	height: 30px;
	border: 1px solid #dadbdb;
	font-size: 15px;
	font-weight: lighter;
	text-align: center;
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
});
</script>
</head>

<body onload="init();">
	<form id="GenRiskAnalysis" name="GenRiskAnalysis"
		action="riskAnalysis/GenAnalysisRiskAction" method="post">
		<div class="toptitle">
			<div class="toptitleleft"></div>
			<div class="topttileright"></div>
			<div class="toptitlemiddle">
				<span>按风险类型分析</span>
			</div>
		</div>
		<div>
			<table class="tablesquery" cellpadding="0" cellspacing="0">
				<tr align="left">
					<td width="7%">综合评定：</td>
					<td width="5%"><input name="riAllValueFrom"
						id="riAllValueFrom" style="width:50px"
						value="<s:property value="riAllValueFrom"/>"
						onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" /></td>
					<td width="3%">至：</td>
					<td width="6%" align="left"><input name="riAllValueTo"
						id="riAllValueTo" style="width:50px"
						value="<s:property value="riAllValueTo"/>"
						onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" /></td>
					<td width="7%">影响程度：</td>
					<td width="5%"><input name="riDegreeFrom" style="width:50px"
						id="riDegreeFrom" value="<s:property value="riDegreeFrom"/>"
						onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" /></td>
					<td width="3%">至：</td>
					<td width="7%" align="left"><input name="riDegreeTo"
						style="width:50px" id="riDegreeTo"
						value="<s:property value="riDegreeTo"/>"
						onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" /></td>
					<td width="6%" colspan="2" align="right">发生概率：</td>
					<td width="9%" colspan="2"><s:select name="riProDegree"
							theme="simple" list="allProDegreeList"
							onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>
					</td>
					<td width="10%" align="right">风险类别：</td>
					<td><span class="red"> <s:select name="risktype"
								theme="simple" list="allrtList" listValue="rtName"
								listKey="rtId" style="width:80px" onchange="getRiskData()">
							</s:select> *</span> <span class="red"> <select id="riskname"
							name="riskname" style="width:200px">

						</select> *</span> <input type="hidden" name="riskname2"
						value="<s:property value="riskname"/>"></td>
				</tr>
				<tr style="position:relative;">
					<%
						HttpSession se = request.getSession();
						String pw = se.getAttribute("power").toString();
						if ("0".equals(pw)) {
					%>
					<td width="7%">录入部门：</td>
					<td width="60" colspan="5"><input name="riDepName"
						id="riDepName" value="<s:property value='riDepName'/>"
						disabled="disabled" /></td>
					<%
						} else {
					%>
					<td width="7%">录入部门：</td>
					<td width="60" colspan="5"><s:select name="riDepName"
							theme="simple" list="alldepList" listValue="depName"
							listKey="depName"></s:select></td>
					<%
						}
					%>
					<td colspan="2" width="8%" align="right">查询时间：</td>
					<td width="7%"><s:select name="choosedId" theme="simple"
							list="#{'0':'--请选择--','1':'录入时间','2':'发布时间'}"
							onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>
					</td>
					<td width="7%"><input name="dateFrom" style="width:90px"
						id="dateFrom" class="Wdate" value="<s:property value='dateFrom'/>"
						onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"
						onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})" />
					</td>
					<td width="3%">至：</td>
					<td width="7%" align="left"><input name="dateTo"
						style="width:90px" id="dateTo" class="Wdate"
						value="<s:property value='dateTo'/>"
						onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"
						onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})" />
					</td>
					<td width="6%" align="right"><a onclick="queryByValue();"><img
							src="images/mag.png" /><span>统计查询</span> </a>
					</td>
					<td width="60"><a href="riskAnalysis/GenAnaRiskExcelAction"><img
							src="images/Excel.png" /><span>导出Excel</span> </a></td>

				</tr>
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
						<th><a onclick="openTab();"
							style="color: red; text-decoration:none;">综合评定</a>
						</th>
						<th>风险事件时间</th>
					</tr>
				</thead>
				<tbody id="tablebody">
					<s:iterator value="ralist">

						<tr title="双击查看风险事件信息"
							onDblClick="showModelessDialog('riskAnalysis/GenRiskDetailAction?reId=<s:property value='reId'/>&riNum=<s:property value='riNum'/>',window,'status:false;dialogWidth:800px;dialogHeight:250px;resizable:yes;scroll:yes');">
							<td><s:property value="riNum" />
							</td>
							<td><s:property value="rtName" />
							</td>
							<td><s:property value="riskName" />
							</td>
							<td><s:property value="depName" />
							</td>
							<td><s:property value="reId" />
							</td>
							<td><s:property value="reEventname" />
							</td>
							<td><s:property value="riAlldegree" />
							</td>
							<td><s:property value="riProdegree" />
							</td>
							<td><s:property value="riKpi" />
							</td>
							<td><s:property value="riAllvalue" />
							</td>
							<td><s:property value="riEventDate" />
							</td>

						</tr>
					</s:iterator>
				</tbody>
			</table>

			<table id="coordinateGraphs" width="100%" cellpadding="0"
				cellspacing="0">
				<tr>
					<td colspan="8">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="5">将我院风险评估的定性、定量风险得出的风险坐标图分A、B、C三个区域，如图1所示。</td>
					<td colspan="3" align="right">
						<table class="tablesquery" align="right">
							<td><a href="riskAnalysis/genRiskReportAction"
								style="width:120px;"><img width="32" height="32"
									src="images/Word.png" /><span>导出统计图</span> </a></td>
						</table></td>
				</tr>
				<tr>
					<td colspan="5" align="center"><img id="reportImg" alt=""
						src="<s:property value="path"/>" style="width:570px;height:570px;" />
					</td>
					<td colspan="4" align="center">
						<div
							style="height:450px;overflow-x:hidden;overflow-y:auto;width: 100%">
							<table align="center" border="0" class="tablestyle"
								style="width: 100%">
								<thead>
									<tr
										style="position:relative;top:expression(this.offsetParent.scrollTop);padding:0px;">
										<th>风险序号</th>
										<th>风险名称</th>
										<th>风险事件数</th>
										<th width="80px">实时概率（次/年）</th>
										<th width="80px">填报概率（次/年）</th>
									</tr>
								</thead>
								<tbody id="detailTabBody">
									<s:iterator value="reportList">
										<%
											int riskPro = (Integer) request.getAttribute("riskPro");
												int riskValuex = (Integer) request.getAttribute("riskValuex");
												if (riskPro <= 2 && riskValuex <= 2) {
										%>
										<tr bgcolor="rgb(198,249,184)">
											<%
												} else if (riskPro >= 4 && riskValuex >= 4) {
											%>
										
										<tr bgcolor="rgb(248,195,195)">
											<%
												} else {
											%>
										
										<tr bgcolor="rgb(253,236,182)">
											<%
												}
											%>

											<%
												if ((Integer) request.getAttribute("riskPro") <= 2
															&& (Integer) request.getAttribute("riskValuex") <= 2) {
											%>
										
										<tr bgcolor="rgb(198,249,184)">
											<%
												} else if ((Integer) request.getAttribute("riskPro") >= 4
															&& (Integer) request.getAttribute("riskValuex") >= 4) {
											%>
										
										<tr bgcolor="rgb(248,195,195)">
											<%
												} else {
											%>
										
										<tr bgcolor="rgb(253,236,182)">
											<%
												}
											%>
											<td><s:property value="riskQueue" />
											</td>
											<td><s:property value="riskName" />
											</td>
											<td><s:property value="riskNum" />
											</td>
											<td><s:property value="freq" />
											</td>
											<td><s:property value="reportFreq" />
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div></td>
				</tr>
				<tr>
					<td colspan="5" align="center"><font
						style="font-family: 宋体;font-weight: bold;font-size: 20px">图1：风险坐标图分区图</font>
					</td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td colspan="8">&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td colspan="8">&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td colspan="8" id="show"></td>
				</tr>
				<tr>
					<td colspan="8">&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td colspan="8" width="100%"><input id="areadetail"
						type=hidden value="<s:property value="areadetail"/>" /></td>
				</tr>
				<tr>
					<td colspan="8">&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr align="center">
					<td colspan="8"><img id="pieReportImg" alt=""
						src="<s:property value="piePath"/>" /></td>
				</tr>
				<tr>
					<td colspan="8" align="center"><font
						style="font-family: 宋体;font-weight: bold;font-size: 20px;">图2：分区二级风险比例饼状图</font>
					</td>
				</tr>
				<tr>
					<td colspan="8">&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td colspan="8" align="center">${map}<img id="barReportImg" alt=""
						src="<s:property value="barPath"/>"  usemap='#${filename}' border='0'/></td>
				</tr>
				<tr>
					<td colspan="8" align="center"><font
						style="font-family: 宋体;font-weight: bold;font-size: 20px;">图3：风险事件柱状图</font>
					</td>
				</tr>
				<tr>
					<td colspan="8">&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td colspan="8">&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td colspan="8" align="center"><font
						style="font-family: 宋体;font-weight: bold;font-size: 20px;">表一：风险分布情况及评定情况</font>
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<table id="riskDiskTab" width="800px" class="tablestyle"
							border="0" align="center">
							<thead>
								<tr>
									<th>风险类型</th>
									<th>风险事件数</th>
									<th>综合评定平均值</th>
								</tr>
							</thead>
						</table></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td colspan="8" align="center"><img id="pieRtReportImg" alt="" />
					</td>
				</tr>

				<tr>
					<td colspan="8" align="center"><font
						style="font-family: 宋体;font-weight: bold;font-size: 20px;">图4：一级风险事件数比例饼状图</font>
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td colspan="8" align="center"><img id="barRtReportImg" alt="" />
					</td>
				</tr>
				<tr>
					<td colspan="8" align="center"><font
						style="font-family: 宋体;font-weight: bold;font-size: 20px;">图5：一级风险综合评定值柱状图</font>
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td colspan="8" align="center"><font
						style="font-family: 宋体;font-weight: bold;font-size: 20px;">表二：企业标准映射统计表</font>
					</td>
				</tr>

				<tr>
					<td colspan="8">
						<table id="ruleTab" width="800px" class="tablestyle" border="0"
							align="center">
							<thead>
								<tr>
									<th>序号</th>
									<th>管理规定名称</th>
									<th>风险事件个数</th>
								</tr>
							</thead>
						</table></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;</td>
				</tr>
				<%-- <tr>
					<td colspan="8" align="center">
						<table>
							<tr>
								<td>${mapx}
								<img id="barRuleReportwImg" alt="" usemap='#${filenamex}' border="0"/></td>
								<!-- <img id="barRuleReportImg" alt=""  border="0"/> -->
							</tr>
						</table></td>
				</tr> --%>
				<tr>
				<td colspan="8" align="center">${mapx}<img id="barReportImgx" alt="" src="<s:property value="barPath_2" />" usemap='#${filenamex}' border='0' /></td></tr>
				<tr>
					<td colspan="8" align="center"><font
						style="font-family: 宋体;font-weight: bold;font-size: 20px;">图6：管理规定事件数柱状图</font>
					</td>
				</tr>
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
      


function init()
{
	getRiskReport();
	getRiskData();
}

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
       
 function getRiskDisk(){
     $.ajax({
         url: 'riskAnalysis/ajaxGenRiskDiskAction',
         type: 'post',
         dateType: 'json',
         data: '',
         contentType: 'text/html;charset=utf-8',
         success: function (data) {     
        	 var data1 = eval("("+ data+")");
        	 var item;  
        	 for(var i=0;i<data1.length;i++)
             { 
        		 if(data1[i].riskName == "法律风险" || data1[i].riskName == "市场风险" || data1[i].riskName == "运营风险"|| data1[i].riskName == "战略风险" ||data1[i].riskName == "财务风险"){
                 item = "<tr bgcolor='#e9f2f7'><td>"+data1[i].riskName+"</td><td>"+data1[i].reEventname+"</td><td>"+data1[i].riAllvalue+"</td></tr>";  
        		 }else{
        			 item = "<tr><td>"+data1[i].riskName+"</td><td>"+data1[i].reEventname+"</td><td>"+data1[i].riAllvalue+"</td></tr>";  
        		 }
                 $("#riskDiskTab") .append(item);
             }
         },
         error: function () {
         alert("error");
     }
 });
 }
 
 function getRiskDiskPieAndBar(){
     $.ajax({
         url: 'riskAnalysis/ajaxGenRiskDiskPieAndBarAction',
         type: 'post',
         dateType: 'json',
         data: '',
         contentType: 'text/html;charset=utf-8',
         success: function (data) {     
        	 var data1 = eval("("+ data+")");
        	 var item;  
        	 $("#pieRtReportImg").attr("src",data1[0].pieChart);
        	 $("#barRtReportImg").attr("src",data1[0].barChart);
         },
         error: function () {
         alert("error");
     }
 });
 }
 
 function getRule(){
     $.ajax({
         url: 'riskAnalysis/ajaxGenRuleAction',
         type: 'post',
         dateType: 'json',
         data: '',
         contentType: 'text/html;charset=utf-8',
         success: function (data) {     
        	 var data1 = eval("("+ data+")");
        	 var item;  
        	 for(var i=0;i<data1.length;i++)
             { 
                 item = "<tr bgcolor='#e9f2f7'><td>"+(i+1)+"</td><td>"+data1[i].ruleName+"</td><td>"+data1[i].eventNum+"</td></tr>";  
                 $("#ruleTab") .append(item);
             }
         },
         error: function () {
         alert("error");
     }
 });
 }
 
 function getRuleBar(){
     $.ajax({
         url: 'riskAnalysis/ajaxGenRuleBarAction',
         type: 'post',
         dateType: 'json',
         data: '',
         contentType: 'text/html;charset=utf-8',
         success: function (data) {     
        	 var data1 = eval("("+ data+")"); 
        	 $("#barRuleReportImg").attr("src",data1[0].barChart);
        	 $("#updown").css("top",window.screen.availHeight/2-100+"px");
         },
         error: function () {
         alert("error");
     }
 });
 }

function queryByValue(){
    openBox('统计提示',350,150,0,'正在统计中，请等待....','');
	GenRiskAnalysis.action="riskAnalysis/GenAnalysisRiskByValueAction";
	GenRiskAnalysis.submit();
}

function reportExport(){
	GenRiskAnalysis.action="riskAnalysis/genRiskReportAction";
	GenRiskAnalysis.submit();
}


function getRiskReport(){
	getRiskDisk();
	getRiskDiskPieAndBar();
	getRule();
	getRuleBar();
	
}

function openTab()
{	
		var url = "riskAnalysis/GenAnaAllRiskAction";
		window.parent.openTab(url,"按风险类型（一般）-详细");
};


</script>

</html>
