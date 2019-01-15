package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CourseDAO;
import com.domain.Course;


@WebServlet("/teaAddServlet")
public class TeaAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		String username =(String) request.getSession().getAttribute("username");
		String course_id = request.getParameter("course_id");
		String course_name = request.getParameter("course_name");
		String teacher = request.getParameter("teacher");
		String time_1T = request.getParameter("time_1T");
		String time_1B = request.getParameter("time_1B");
		String time_1=time_1T+time_1B;
		String time_2T = request.getParameter("time_2T");
		String time_2B = request.getParameter("time_2B");
		String time_2=time_2T+time_2B;
		String location = request.getParameter("location");
		if(course_id.equals("")||course_name.equals("")||
				teacher.equals("")||location.equals("")||
				request.getParameter("limitied").equals("")||
				request.getParameter("point").equals("")) {
			System.out.println("输入存在有空值 请重新输入");
			
			String message="输入存在有空值 请重新输入";
			request.getSession().setAttribute("message", message);
			response.sendRedirect("/OnlineCouSelSys/TeaAdd.jsp");
			return;
		}
		int point = 0;
		if(!CourseDAO.isNumeric(request.getParameter("point"))) {
			System.out.println("CourseDAO.isNumeric");
			System.out.println("学分输入格式不正确");
			String message="学分输入格式不正确 请输入1~6的整数";
			request.getSession().setAttribute("message", message);
			response.sendRedirect("/OnlineCouSelSys/TeaAdd.jsp");
			return;
		}else {
			 point = Integer.parseInt(request.getParameter("point"));
		}
		int  limitied = Integer.parseInt(request.getParameter("limitied"));
		
		
		Course course =new Course();
		course.setCourse_id(course_id);
		course.setCourse_name(course_name);
		course.setTeacher(teacher);
		course.setPoint(point);
		course.setTime_1(time_1);
		course.setTime_2(time_2);
		course.setLocation(location);
		course.setLimitied(limitied);
		
		
		
		/*if((CourseDAO.isStuSelCourse(username, course_id)==0)&&(CourseDAO.AddSelCourse(username, course_id))) {
			System.out.println("++++StuAddServlet插入成功!!!");
			response.sendRedirect("/OnlineCouSelSys/stuSelCourseResServlet");
			
		}else {
			System.out.println("++++插入失败!!!");
			System.out.println("进入stuaddServlet:  该课程选过了");
			String message="你已选过该课程";
			request.getSession().setAttribute("message", message);
			response.sendRedirect("/OnlineCouSelSys/stuSelCourseServlet");
		}*/
		
		
		
		System.out.println("开始判断id是否重复 electric course是否插入");
		if((CourseDAO.isTeaAddCourse(course_id)==0)&&
		  (CourseDAO.AddCourse(course))&&
		  (CourseDAO.AddSelCourse(username, course_id))) {
			
			System.out.println("course_id");
			System.out.println("插入course表成功");
			System.out.println("插入elective表成功");
			response.sendRedirect("/OnlineCouSelSys/teaSelCourseServlet");
		}else {
			System.out.println("teaaddServlet插入失败!!!");
			System.out.println("进入TeaAddServlet:  该课程的id重复了");
			String message="该课程的id重复了";
			request.getSession().setAttribute("message", message);
			response.sendRedirect("/OnlineCouSelSys/TeaAdd.jsp");
		}
	
		
		/*System.out.println(course_id+course_name+teacher);
		System.out.println(time_1+"时间"+time_2);
		System.out.println(location+"时间"+limitied);*/
		
		
	}

}
