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
		
		//�жϿγ��Ƿ�����
		if(CourseDAO.countSelecteds(course_id, role)==limitied) {
			System.out.println("�ÿγ��Ѿ�����");
			String message="�ÿγ��Ѿ�����";
			request.getSession().setAttribute("message", message);
			response.sendRedirect("/OnlineCouSelSys/stuSelCourseServlet");
			return;
		}
		 
		
		
			
			//�ж��Ƿ�ѡ����
		 if((CourseDAO.isStuSelCourse(username, course_id)==0)&&(CourseDAO.AddSelCourse(username, course_id))) {
//			HttpSession session = request.getSession();
//	        session.setAttribute("username", username);
//			ArrayList CourseSelList=CourseDAO.queryAllStuSelCourse();
	        //request.getRequestDispatcher("/stuSelCourseServlet").forward(request, response);
			//session.setAttribute(, );
			System.out.println("++++StuAddServlet����ɹ�!!!");
//			session.setAttribute("CourseSelList", CourseSelList);
			
			//request.getRequestDispatcher("/StuSelCourRes.jsp").forward(request, response);
			response.sendRedirect("/OnlineCouSelSys/stuSelCourseResServlet");
			//request.getRequestDispatcher("/stuSelCourseResServlet").forward(request, response);
		}else {
			System.out.println("++++����ʧ��!!!");
			System.out.println("����stuaddServlet:  �ÿγ�ѡ����");
			String message="����ѡ���ÿγ�";
			request.getSession().setAttribute("message", message);
			response.sendRedirect("/OnlineCouSelSys/stuSelCourseServlet");
		}
		
		//System.out.println(username+"==="+course_id);
	}

}
