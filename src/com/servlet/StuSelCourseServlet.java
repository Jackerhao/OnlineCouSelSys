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


@WebServlet("/stuSelCourseServlet")
public class StuSelCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList CourseSelList=CourseDAO.queryAllStuSelCourse();
//		HttpSession session = request.getSession();
		
//		String username = (String) session.getAttribute("username");
//		String role = (String) session.getAttribute("role");
		System.out.println("11");
		
//		System.out.println("stuSelCourseServlet :"+username+"  :"+role);

//		session.setAttribute("CourseSelList", CourseSelList);
//		System.out.println("stuselcourRes"+request.getParameter("gosel"));
    	request.setAttribute("CourseSelList", CourseSelList);
//		request.setAttribute("username", username);
//		request.setAttribute("role", role);
		request.getRequestDispatcher("/StuSelCourse.jsp").forward(request, response);
	}

}
