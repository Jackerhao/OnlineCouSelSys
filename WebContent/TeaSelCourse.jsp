<%@page import="com.dao.CourseDAO"%>
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

<%                //表示服务端代码
        ArrayList CourseSelList =(ArrayList)request.getAttribute("CourseSelList");
        String username=(String)session.getAttribute("username");
        
%>
<!-- <a href="/OnlineCouSelSys/TeaAdd.jsp">添加选课</a> -->
<h1 align="center">在线选课系统</h1>
<table class="f1-table" align="center" width="1000px">
<thead>
<tr>
<th colspan="7"><h2><%=username %>用户开设的课程如下:</h2></th>
<th colspan="1"><form action="/OnlineCouSelSys/TeaAdd.jsp" method="post">
<input type="submit" value="添加开设的课程"></form></th>
<th colspan="1"><form action="/OnlineCouSelSys/login.jsp" method="post">
<input type="submit" value="退出登录"></form></th>
</tr>
</thead>
</table>


<div class="table-wrapper">
<table class="f1-table" align="center" width="1000px">
<thead>
<tr>
<th>课程编号</th>
<th>课程名称</th>
<th>授课老师</th>
<th>学分</th>
<th colspan="2">上课时间</th>
<th>上课地点</th>
<th>限制人数</th>
<th>已选人数</th>
<th>操作</th>
</tr>
</thead>

<tbody>
<%

        for(int i=0;i<CourseSelList.size();i++){
            Course course = (Course)CourseSelList.get(i);
    %>
    <tr align="center" height="60px">
    <td><%=course.getCourse_id() %></td>
    <td><%=course.getCourse_name() %></td>
    <td><%=course.getTeacher() %></td>
    <td><%=course.getPoint() %></td>
    <td><%=TimeChange.reverse1(course.getTime_1().substring(0, 1))%>
    	<%=TimeChange.reverse2(course.getTime_1().substring(1, 2))%>
    </td>
    <td><%=TimeChange.reverse1(course.getTime_2().substring(0, 1))%>
    <%=TimeChange.reverse2(course.getTime_2().substring(1, 2))%>
    </td>
    <td><%=course.getLocation() %></td>
    <td><%=course.getLimitied()%></td>
    <td><%=CourseDAO.countSelecteds(course.getCourse_id(), "0")%></td>
    <td><a href="/OnlineCouSelSys/delCourseServlet?course_id=<%=course.getCourse_id()%>">delete</a></td>
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