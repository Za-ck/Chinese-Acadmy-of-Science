<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="level" uri="/WEB-INF/level.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<title>中南电力设计</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="/RiskEvent/js/jquery-1.7.1.js" type="text/javascript"></script>
<style>
#menuLevel {
	width: 100%;
	height: 35px;
	padding: 0px;
	line-height: 35px;
	text-align: left;
}

#menuLevel a {
	text-decoration: none;
	cursor: pointer;
	color: white;
	font-size: 14;
}

#menuLevel a:hover {
	text-decoration: none;
	cursor: pointer;
	color: #cad9e3;
	font-size: 15;
}

#menuLevel span {
	width: 70px;
	font-family: sans-serif;
	font-weight: bold;
	background: url(images/toptitlebg.png) right no-repeat;
}

#menuTop span {
	font-size: 12px;
}

#menuTop a {
	text-decoration: none;
	cursor: pointer;
	color: #818181
}

#menuTop a:hover {
	text-decoration: none;
	cursor: pointer;
	color: #cad9e3;
	font-size: 13;
}

.userinfo {
	line-height: 25px;
	margin-top: 5px;
	height: 30px;
	font-size: 14px;
	font-weight: 600;
	color: #117ed0
}

.userinfo:HOVER {
	color: #818181;
}

.linkstyle {
	cursor: pointer;
	color: #1281d3;
}

.linkstyle:visited {
	color: #FF0000
}

.linkstyle:HOVER {
	color: #818181;
}

.linkstyle:ACTIVE {
	color: #000000;
}
</style>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0">
	<table style="width:100%;border: 0px;border-collapse:collapse;">
		<tr>
			<td background="images/top_01.gif" width="26" height="33">&nbsp;</td>
			<td background="images/top_02.gif" width="285" height="33">&nbsp;</td>
			<td background="images/top_03.gif" width="490" height="33">&nbsp;</td>
			<td align="right" height="33" colspan="4"
				background="images/top_04.gif">
				<div id="menuTop">
					<img src="images/dote.png" /><a href="#" onClick="backHome()"><span>&nbsp;返回首页</span>
					</a>&nbsp; <img src="images/dote.png" /><a href="help.doc" onclick=""><span>&nbsp;使用说明</span>
					</a>&nbsp; <img src="images/dote.png" /><a
						onClick="return closeSystem()" href="default/ExitSystem"
						target="_blank"><span>&nbsp;安全退出</span>
					</a>&nbsp;
				</div></td>
		</tr>
		<tr>
			<td background="images/top_07.gif" width="26" height="61">&nbsp;</td>
			<td background="images/top_08.gif" width="285" height="61">&nbsp;</td>
			<td height="61" colspan="2" background="images/1_09.gif">&nbsp;</td>
			<td background="images/top_11.gif" width="52" height="61">&nbsp;
			</td>
			<td height="61" colspan="2" background="images/top_12.gif">&nbsp;</td>
		</tr>
		<tr>
			<td background="images/top_13.gif" width="26" height="37">&nbsp;</td>
			<td height="37" colspan="2" background="images/top_14.gif">
				<div id="menuLevel">
					<level:level level="1"></level:level>
				</div></td>
			<td background="images/Top_16.gif" width="141" height="37">
			<img
				src="icon/Person.png" style=" margin-left:35px; margin-top:3px"
				title="用户信息" width="23" height="23" /> <span class="userinfo"
				title="<%=request.getSession().getAttribute("userdep") %>/<%=request.getSession().getAttribute("userrole") %>"><%=request.getSession().getAttribute("username")%></span>
			</td>
			<td height="37" colspan="2" background="images/Top_17.gif">&nbsp;</td>
			<td width="200" height="37" background="images/Top_17.gif">
			<MARQUEE style="width:80%;float: left;font-size:14px;color:#818181;" scrollamount='4' onmouseover=this.stop() onmouseout=this.start()>
				<!-- 添加滚动文字 -->
				<a class="linkstyle" onclick="callWarn('default/reportMessageAction_getReportMessage?state=0')"><%=request.getSession().getAttribute("remind")%></a>
				
			</MARQUEE> 
			<img src="icon/laba.png" style="margin-right:10px;float: left;" title="近期警告提示信息" width="20" height="20" />
			</td>
		</tr>
	</table>
	<script type="text/javascript">
		
		function autoCallTreeMenu(num) {
		
			var as = document.getElementById("menuLevel" + num);
			var text = $(as).find("span").eq(0).text();
			callTreeMenu(num,text);
		}
	
		function callTreeMenu(num, text) {
			var as = document.getElementById("menuLevel").getElementsByTagName("a");
			for ( var i = 0; i < as.length; i++)
				as[i].style.color = "white";
			document.getElementById("menuLevel" + num).style.color = "#b9b8b8";
			//parent.document.getElementById("centerFrame").initTreeMenu(num);
			window.parent.window.frames["centerFrame"].initTreeMenu(num, text);
		}

		function callWarn(action) {
			window.parent.window.frames["centerFrame"].showWarn(action);
		}

		function backHome() {
			var as = document.getElementById("menuLevel").getElementsByTagName(
					"a");
			for ( var i = 0; i < as.length; i++)
				as[i].style.color = "white";
			window.parent.window.frames["centerFrame"].callHome();
		}

		function closeSystem() {
			if (confirm('确认要退出吗?')) {
				window.top.opener = null;
				window.parent.close();
				return true;
			} else
				return false;
		}
		function AddFavorite(sURL, sTitle) {
			//var end=sURL.indexOf("frame");
			alert(sURL);
			try {
				window.external.addFavorite(sURL.substring(0, end), sTitle);
			} catch (e) {
				try {
					window.sidebar.addPanel(sTitle, sURL.substring(0, end), "");
				} catch (e) {
					alert("加入收藏失败，请使用Ctrl+D进行添加");
				}
			}
		}
	</script>
</body>
</html>