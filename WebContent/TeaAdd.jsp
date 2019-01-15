<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style/table.css">
<link rel="stylesheet" type="text/css" href="style/input.css">
</head>
<body>

	<%
	String mess = (String) session.getAttribute("message");
	if(("".equals(mess))|| (mess==null)){
		
	}else{%>
	<script type="text/javascript">alert("<%=mess%>")</script>
	
	<%
	session.setAttribute("message","");
	}%>
<%-- <% String username =(String)session.getAttribute("username"); %> --%>
<%--  <h1><%=username%></h1>  --%>
 <!-- <a href="/OnlineCouSelSys/teaSelCourseServlet">开设选课目录</a> -->
 <h1 align="center">在线选课系统</h1>
 <table class="f1-table" align="center" width="1000px">
 <thead>
 <tr>
<th ><h2>请输入课程信息:</h2>
</th>
<th ><form action="/OnlineCouSelSys/teaSelCourseServlet" method="post">
<input type="submit" value="开设选课目录"></form>
</th>
<th>
<form action="/OnlineCouSelSys/login.jsp" method="post">
<input type="submit" value="退出登录"></form>
</th>
</tr>
</thead>
 </table>
 
 <div class="table-wrapper">
<form action="/OnlineCouSelSys/teaAddServlet" method="post">
<table class="f1-table" align="center" width="1000px">

<tr align="center">
<td>课程编号</td><td><input type="text" name="course_id" class="ipt"/></td>
</tr>
<tr align="center">
<td>课程名称</td><td><input type="text" name="course_name" class="ipt"/></td>
</tr>
<tr align="center">
<td>教师姓名</td><td><input type="text" name="teacher" class="ipt"/></td>
</tr>
<tr align="center">
<td>学分</td><td><input type="text" name="point" value="(1~6)" class="ipt"/></td>
</tr>
<tr align="center">
<td rowspan="2">上课时间</td>
<td>第一次 
<select name="time_1T">
<option value="1">星期一</option>
<option value="2">星期二</option>
<option value="3">星期三</option>
<option value="4">星期四</option>
<option value="5">星期五</option>
</select>
<select name="time_1B">
<option value="1">8:05~9:50</option>
<option value="2">10:15~12:00</option>
<option value="3">13:35~15:20</option>
<option value="4">15:45~17:30</option>
<option value="5">18:30~20:45</option>
</select>

</td>
</tr>
<tr align="center">
<td>第二次
<select name="time_2T">
<option value="1">星期一</option>
<option value="2">星期二</option>
<option value="3">星期三</option>
<option value="4">星期四</option>
<option value="5">星期五</option>
</select>
<select  name="time_2B">
<option value="1">8:05~9:50</option>
<option value="2">10:15~12:00</option>
<option value="3">13:35~15:20</option>
<option value="4">15:45~17:30</option>
<option value="5">18:30~20:45</option>
</select>
</td>
</tr>
<tr align="center">
<td>上课地点</td><td><input type="text" name="location" class="ipt"/></td>
</tr>
<tr align="center">
<td>限制人数</td><td><input type="text" name="limitied" class="ipt"/></td>
</tr>

<tr align="center">
      <td colspan="2" align="center"><input type="submit" value="添加"/></td>
</tr>

</table>

</form>
</div>

 <div id="box"></div>

<script src='js/particles.js' type="text/javascript"></script>
<script src='js/background.js' type="text/javascript"></script>
<script src='js/jquery.min.js' type="text/javascript"></script>
<script src='js/layer/layer.js' type="text/javascript"></script>
<script src='js/index.js' type="text/javascript"></script>
</body>
</html>