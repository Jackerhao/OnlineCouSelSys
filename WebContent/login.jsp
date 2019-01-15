<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style/register-login.css">
</head>
<body>

<%
	String error = (String) session.getAttribute("error");
	if(("".equals(error))|| (error==null)){
		
	}else{%>
	<script type="text/javascript">alert("<%=error%>")</script>
	
	<%
	session.setAttribute("error","");
	}%>


<div id="box"></div>
<div class="cent-box">
	<div class="cent-box-header">
		<h1 >在线选课系统</h1>
	</div>

	<div class="cont-main clearfix">
		<div class="index-tab">
			<div class="index-slide-nav">
				<a href="login.jsp" class="active">登录</a>
				<a href="register.jsp">注册</a>
				<div class="slide-bar"></div>				
			</div>
		</div>
         
         
         
		<form action="/OnlineCouSelSys/loginservlet" method="post">
		<div class="login form">
			<div class="group">
				<div class="group-ipt user">
					<input type="text" name="username" class="ipt" placeholder="用户名" required>
				</div>
				<div class="group-ipt password">
					<input type="password" name="password" class="ipt" placeholder="密码" required>
				</div>
				
				<div align="center" style="font-size:20px" >
					<input type="radio" name="role" value="0" class="ipt" >student 
					&nbsp &nbsp &nbsp &nbsp
					<input type="radio" name="role" value="1" class="ipt" >teacher
				</div>
			
			</div>
		</div>

		<div class="button">
			<button type="submit" class="login-btn register-btn" id="button">登录</button>
		</div>

		</form>
	</div>
</div>


<script src='js/particles.js' type="text/javascript"></script>
<script src='js/background.js' type="text/javascript"></script>
<script src='js/jquery.min.js' type="text/javascript"></script>
<script src='js/layer/layer.js' type="text/javascript"></script>
<script src='js/index.js' type="text/javascript"></script>

</body>
</html>