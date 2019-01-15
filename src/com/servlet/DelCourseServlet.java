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
import com.domain.Course;


@WebServlet("/delCourseServlet")
public class DelCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String course_id = request.getParameter("course_id");
		String username = (String)request.getSession().getAttribute("username");
		
		if(CourseDAO.delSelCourse(username, course_id)&&CourseDAO.delCourse(course_id)) {
			System.out.println("É¾³ýcourse±í³É¹¦!!!+/delCourseServlet");
			response.sendRedirect("/OnlineCouSelSys/teaSelCourseServlet");
		}

	}

}
