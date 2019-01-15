package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDAO;
import com.domain.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private static final String username = null;
//    private static final String password = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
        String password = request.getParameter("password"); 
        String role = request.getParameter("role");
       
            User user =UserDAO.login(username,password,role);
            HttpSession session = request.getSession();
            
            if ((user!= null) && Integer.parseInt(user.getRole())==0){
                session.setAttribute("username", username);
                session.setAttribute("role", role);
                session.setAttribute("password", password);
                	System.out.println("loginservlet: "+session.getAttribute("username")+"学生");
                	System.out.println("loginservlet"+session.getAttribute("role")+"学生");
                request.getRequestDispatcher("/stuSelCourseServlet").forward(request, response);
              
            } else if ((user!= null) && Integer.parseInt(user.getRole())==1){
                
                session.setAttribute("username", username);
                session.setAttribute("role", role);
                session.setAttribute("password", password);
            	System.out.println(session.getAttribute("username")+"老师");
            	System.out.println(session.getAttribute("role")+"老师");
                request.getRequestDispatcher("/teaSelCourseServlet").forward(request, response);
              
            }else {
/*            	String message="你已选过该课程";
    			request.getSession().setAttribute("message", message);*/
               // request.setAttribute("error", "您的账号和密码不匹配，请重新输入");
            	String error="您的输入有误，请重新输入";
            	request.getSession().setAttribute("error", error);
            //    request.getRequestDispatcher("/login.jsp").forward(request, response);
            	response.sendRedirect("/OnlineCouSelSys/login.jsp");
            	//System.out.println(username+"  :" +password+":"+role);
                System.out.println("登录失败");
            }


	}

}
