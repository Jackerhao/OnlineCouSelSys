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


@WebServlet("/stuAddServlet")
public class StuAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String course_id = request.getParameter("course_id");
		String username = request.getParameter("username");
		int limitied=Integer.parseInt(request.getParameter("limitied"));
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");
		
		//判断课程是否满课
		if(CourseDAO.countSelecteds(course_id, role)==limitied) {
			System.out.println("该课程已经满人");
			String message="该课程已经满人";
			request.getSession().setAttribute("message", message);
			response.sendRedirect("/OnlineCouSelSys/stuSelCourseServlet");
			return;
		}
		 
		
		
			
			//判断是否选过课
		 if((CourseDAO.isStuSelCourse(username, course_id)==0)&&(CourseDAO.AddSelCourse(username, course_id))) {
//			HttpSession session = request.getSession();
//	        session.setAttribute("username", username);
//			ArrayList CourseSelList=CourseDAO.queryAllStuSelCourse();
	        //request.getRequestDispatcher("/stuSelCourseServlet").forward(request, response);
			//session.setAttribute(, );
			System.out.println("++++StuAddServlet插入成功!!!");
//			session.setAttribute("CourseSelList", CourseSelList);
			
			//request.getRequestDispatcher("/StuSelCourRes.jsp").forward(request, response);
			response.sendRedirect("/OnlineCouSelSys/stuSelCourseResServlet");
			//request.getRequestDispatcher("/stuSelCourseResServlet").forward(request, response);
		}else {
			System.out.println("++++插入失败!!!");
			System.out.println("进入stuaddServlet:  该课程选过了");
			String message="你已选过该课程";
			request.getSession().setAttribute("message", message);
			response.sendRedirect("/OnlineCouSelSys/stuSelCourseServlet");
		}
		
		//System.out.println(username+"==="+course_id);
	}

}
