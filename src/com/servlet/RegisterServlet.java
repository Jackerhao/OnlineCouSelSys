package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDAO;
import com.domain.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
        String password = request.getParameter("password"); 
        String realname = request.getParameter("realname"); 
        String role = request.getParameter("role");
        
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRealname(realname);
        user.setRole(role);
        System.out.println(user);
        if((UserDAO.isUser(username)==0)&&UserDAO.register(user)) {
        	System.out.println("注册成功");
        	response.sendRedirect("/OnlineCouSelSys/login.jsp");
        }else {
        	String error="注册失败,用户名已存在,请重新注册";
        	request.getSession().setAttribute("error", error);
        	response.sendRedirect("/OnlineCouSelSys/register.jsp");
        }
        
	}

}
