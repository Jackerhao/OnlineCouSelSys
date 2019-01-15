package com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CourseDAO;


@WebServlet("/stuSelCourseResServlet")
public class StuSelCourseResServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String username = (String) session.getAttribute("username");
		//System.out.println(username);
		ArrayList StuCourseSelList=CourseDAO.queryStuSelCourseRes(username);
		
		
		request.setAttribute("StuCourseSelList", StuCourseSelList);
		
		
		
		request.getRequestDispatcher("/StuSelCourRes.jsp").forward(request, response);
	}

}
