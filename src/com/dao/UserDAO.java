package com.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.domain.Course;
import com.domain.User;

public class UserDAO {
	private static PreparedStatement ps=null;
	private static ResultSet rs=null;
	private static Connection ct=null;
//	private static String driver ="com.mysql.cj.jdbc.Driver";
//	private static String url = "jdbc:mysql://localhost/users?useSSL=FALSE&serverTimezone=UTC";
//	private static String dbuser = "root";
//	private static String dbpassword = "switch";
	
	private static String driver= null;
	private static String jdbcUrl = null;
	private static String dbuser = null;
	private static String dbpassword = null;
	
	
     public static void initConnection(){
    	 InputStream in = 
  				Course.class.getClassLoader().getResourceAsStream("jdbc.properties");
  		Properties properties = new Properties();
  		try {
 			properties.load(in);
 		} catch (IOException e1) {
 		System.out.println("读取文件失败");
 			e1.printStackTrace();
 		}
  		
  		driver =properties.getProperty("driver");
  		jdbcUrl = properties.getProperty("jdbcUrl");
  		dbuser= properties.getProperty("user");
  		dbpassword = properties.getProperty("password");
         try {
        	 Class.forName(driver);
               System.out.println("Success loading Mysql Driver!");
         }
         catch (Exception e) {
                System.out.print("Error loading Mysql Driver!");
                e.printStackTrace();
          }

          try {
        	   ct= DriverManager.getConnection(jdbcUrl,dbuser,dbpassword);
                System.out.println("Success connect Mysql server!");

           }
           catch (Exception e) {
                System.out.print("get data error!");
                e.printStackTrace();
           }
     }

     public static void closeConnection() {
         try {
        		if(rs!=null) {
					rs.close();
				}
				if(ps!=null) {
					ps.close();
				}
				if(ct!=null) {
					ct.close();
				}
				System.out.println("close successfully");
         }

         catch(Exception e) {e.printStackTrace();}
     }
     
     //登录
     public static User login(String username,String password,String role) {
    	 User user=null;
    	 String sql =" select * from user where username = ? and password = ? and role = ?";
    	 initConnection();
    	 try {
			ps = ct.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ps.setString(3,role);
            rs = ps.executeQuery();
            while(rs.next()){
            	System.out.println("进入了rs.next()");
            	user=new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            }

            closeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
         
    	 return user;
     }
     
     //注册
     public static boolean register(User user) {
         String sql = "insert into user values(?,?,?,?)";
         boolean flag = true;
         initConnection();
    	 try {
			ps = ct.prepareStatement(sql);
			ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRealname());
            ps.setString(4, user.getRole());
            if(ps.executeUpdate()!=1) {
            	System.out.println("插入失败");
				flag=false;
			}
            

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
       
         return flag;
     }
 
     //是否用户名重复
     public static int isUser(String username) {
    	 int isSel=0;
    	 String sql =" select count(*) from user where username ='"+username+"'";
    	 initConnection();
    	 
    	 try {
			rs = ct.createStatement().executeQuery(sql);
			//System.out.println("isStuSelCourse"+rs.next());
 		   if(rs.next()) {
 		   isSel=rs.getInt(1);
 		   System.out.println(isSel+"===");
 		   System.out.println("该用户已经重复了"+isSel+rs.next());
 		   }
 		  
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection();
		}
    	 return isSel;
     }

}
     

