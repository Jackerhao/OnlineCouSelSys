<%@page import="com.domain.TimeChange"%>
<%@page import="com.domain.Course"%>
<%@page import="java.util.ArrayList"%>
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
        ArrayList StuCourseSelList =(ArrayList)request.getAttribute("StuCourseSelList");
         String username = (String)session.getAttribute("username");
         /*String password = (String)session.getAttribute("password"); 
        String role = (String)session.getAttribute("role"); */
 %>
 <%-- <h1><%=username%></h1><h1><%=password%></h1><h1><%=role%></h1> --%>
<%--  <td colspan="1"><a href="/OnlineCouSelSys/loginservlet
?username=<%=username%>&password=<%=password%>&role=<%=role%>">继续选课</a></td> --%>
<!--  <a href="/OnlineCouSelSys/login.jsp">退出登录</a>-->
<!-- <input type="hidden" name="username" value=>
<input type="hidden" name="password" value=>
<input type="hidden" name="role" value=> -->
<h1 align="center">在线选课系统</h1>
<table class="f1-table" align="center" width="1000px">
<thead>
<tr align="center">
<th colspan="5"><h2><%=username %>所选的课如下:</h2></th>
<th colspan="1"><form action="/OnlineCouSelSys/stuSelCourseServlet" method="post">
<input type="submit" value="继续选课"></form></th> 
<th colspan="1"><form action="/OnlineCouSelSys/login.jsp" method="post">
<input type="submit" value="退出登录"></form></th>
</tr>
</thead>
</table>

<div class="table-wrapper">
<table class="f1-table" align="center" width="1000px" >

<thead>
<tr align="center">
<th>课程编号</th>
<th>课程名称</th>
<th>授课老师</th>
<th>学分</th>
<th colspan="2">上课时间</th>
<th>上课地点</th>
<th>操作</th>
</tr>
</thead>


<%

        for(int i=0;i<StuCourseSelList.size();i++){
            Course course = (Course)StuCourseSelList.get(i);
%>
	<tbody>
    <tr align="center" height="60px">
    <td><%=course.getCourse_id() %></td>
    <td><%=course.getCourse_name() %></td>
    <td><%=course.getTeacher() %></td>
    <td><%=course.getPoint() %></td>
    <td><%=TimeChange.reverse1(course.getTime_1().substring(0, 1))%>
    	<%=TimeChange.reverse2(course.getTime_1().substring(0, 1))%>
    </td>
    <td><%=TimeChange.reverse1(course.getTime_2().substring(0, 1))%>
    <%=TimeChange.reverse2(course.getTime_2().substring(0, 1))%>
    </td>
    <td><%=course.getLocation() %></td>
    <%-- <td><a href="/OnlineCouSelSys/stuDelServlet?course_id=<%=course.getCourse_id()%>">取消选课</a></td> --%>
    <td>
    <form action="/OnlineCouSelSys/stuDelServlet" method="post">
        <input type="hidden" name="course_id" value=<%= course.getCourse_id()%>>
		<input type="submit" value="取消选课"></form>
    </td>
    </tr>

    <%
        }   
    %>
    </tbody>
</table>
</div>
<div id="box"></div>

<script src='js/particles.js' type="text/javascript"></script>
<script src='js/background.js' type="text/javascript"></script>
<script src='js/jquery.min.js' type="text/javascript"></script>
<script src='js/layer/layer.js' type="text/javascript"></script>
<script src='js/index.js' type="text/javascript"></script>
</body>
</html>