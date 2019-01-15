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


@WebServlet("/teaSelCourseServlet")
public class TeaSelCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String username = (String) session.getAttribute("username");
		ArrayList CourseSelList=CourseDAO.queryAllTeaSelCourse(username);
		
		
		request.setAttribute("CourseSelList", CourseSelList);
//		request.setAttribute("username", username);
		request.getRequestDispatcher("/TeaSelCourse.jsp").forward(request, response);
	}

}
