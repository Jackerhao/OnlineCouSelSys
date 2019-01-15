package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import com.domain.Course;

public class CourseDAO {
	private static PreparedStatement ps=null;
	private static ResultSet rs=null;
	private static Connection ct=null;
	private static String driver ="com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost/users?useSSL=FALSE&serverTimezone=UTC";
	private static String dbuser = "root";
	private static String dbpassword = "switch";
	
	//数据库连接
     public static void initConnection(){
    	 
         try {
        	 Class.forName(driver);
               System.out.println("Success loading Mysql Driver!");
         }
         catch (Exception e) {
                System.out.print("Error loading Mysql Driver!");
                e.printStackTrace();
          }

          try {
        	   ct= DriverManager.getConnection(url,dbuser,dbpassword);
                System.out.println("Success connect Mysql server!");

           }
           catch (Exception e) {
                System.out.print("get data error!");
                e.printStackTrace();
           }
     }
     //数据库关闭
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
 
     //查询老师开设的课程
     public static ArrayList queryAllTeaSelCourse(String username) {
    	 ArrayList CourseSelList=new ArrayList();
    	 //String sql="select * from course where teacher='"+teacher+"'";
    	 String sql = "select * from Course where course_id=some(select course_id from elective where username ='"+username+"')";
    	 initConnection();
    	 try {
    		  rs = ct.createStatement().executeQuery(sql);
    		  
    		  while(rs.next()) {
    			  System.out.println("进入queryAllTeaSelCourse");
                  Course course = new Course();
                  course.setCourse_id(rs.getString("course_id"));
                  course.setCourse_name(rs.getString("course_name"));
                  course.setTeacher(rs.getString("teacher"));
                  course.setPoint(rs.getInt("point"));
                  course.setTime_1(rs.getString("time_1"));
                  course.setTime_2(rs.getString("time_2"));
                  course.setLocation(rs.getString("location"));
                  course.setLimitied(rs.getInt("limitied"));
                  
                  CourseSelList.add(course);
              }

    	 }catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
    	 
    	 return CourseSelList;
     }

     //老师添加开设的课程
     public static boolean AddCourse(Course course) {
    	 boolean flag=true;
    	 String sql="insert into course values(?,?,?,?,?,?,?,?)";
    	 initConnection();
    	 
    	 try {
			ps=ct.prepareStatement(sql);
			
			ps.setString(1,course.getCourse_id());
			ps.setString(2,course.getCourse_name());
			ps.setString(3,course.getTeacher());
			ps.setInt(4,course.getPoint());
			ps.setString(5,course.getTime_1());
			ps.setString(6,course.getTime_2());
			ps.setString(7,course.getLocation());
			ps.setInt(8,course.getLimitied());
			
			if(ps.executeUpdate()!=1) {
				flag=false;
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection();
		}
    	 return flag;
     }


     //查询学生可以选择的课程
     public static ArrayList queryAllStuSelCourse() {
    	 ArrayList CourseSelList=new ArrayList();
    	 String sql="select * from course";
    	 initConnection();
    	 try {
    		  rs = ct.createStatement().executeQuery(sql);
    		  
    		  while(rs.next()) {
    			  System.out.println("进入学生queryAllStuSelCourse");
                  Course course = new Course();
                  course.setCourse_id(rs.getString("course_id"));
                  course.setCourse_name(rs.getString("course_name"));
                  course.setTeacher(rs.getString("teacher"));
                  course.setPoint(rs.getInt("point"));
                  course.setTime_1(rs.getString("time_1"));
                  course.setTime_2(rs.getString("time_2"));
                  course.setLocation(rs.getString("location"));
                  course.setLimitied(rs.getInt("limitied"));
                  
                  CourseSelList.add(course);
              }

    	 }catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
    	 
    	 return CourseSelList;
     }
   
     
     //学生选课后插入elective表  老师添加课表的同时也加入elective表中
     public static boolean AddSelCourse(String username,String course_id) {
    	 boolean flag=true;
    	 String sql="insert into elective values(?,?)";
    	 initConnection();
    	 
    	 try {
			ps=ct.prepareStatement(sql);
			ps.setString(1,username);
			ps.setString(2, course_id);
			
			if(ps.executeUpdate()!=1) {
				flag=false;
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection();
		}
    	 return flag;
     }

     //判断是否选过的课程
     public static int isStuSelCourse(String username,String course_id) {
    	 int isSel=0;
    	 String sql =" select count(*) from elective where username ='"+username + "' and course_id ='"+course_id+"'";
    	 initConnection();
    	 
    	 try {
			rs = ct.createStatement().executeQuery(sql);
			//System.out.println("isStuSelCourse"+rs.next());
 		   if(rs.next()) {
 		   isSel=rs.getInt(1);
 		   System.out.println(isSel+"===");
 		   System.out.println("已经选过该课程"+isSel+rs.next());
 		   }
 		  
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection();
		}
    	 return isSel;
     }
     
   //判断是否课程的id重复了
     public static int isTeaAddCourse(String course_id) {
    	 int isAdd=0;
    	 String sql ="select count(*) from course where course_id='"+course_id+"'";
    	 initConnection();
    	 
    	 try {
			rs = ct.createStatement().executeQuery(sql);
			//System.out.println("isStuSelCourse"+rs.next());
 		   if(rs.next()) {
 		   isAdd=rs.getInt(1);
 		   System.out.println(isAdd+"===");
 		   System.out.println("已经该课程的id重复了"+isAdd+rs.next());
 		   }
 		   else {
 		   System.out.println("该课程的id没有重复"+isAdd);
 		   }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection();
		}
    	 return isAdd;
     }
     
 
   //判断输入的学分是否为数字类型并且小于等于6和大于等于1
     public static boolean isNumeric(String str) {
    	 boolean flag = false;
    	 Pattern  pattern = Pattern.compile("[1-6]*");
    	 
    	 if(pattern.matcher(str).matches()) {
    		 int point = Integer.parseInt(str);
    		 if((point>=1)&&(point<=6)) {
    			 flag=true;
    		 }
    	 }
    	 
    	 return flag;
     }
     
     
     
     //学生查询自己的选课的情况
     public static ArrayList queryStuSelCourseRes(String username) {
    	 ArrayList CourseSelList=new ArrayList();
   	     String sql = "select * from Course where course_id="
    	 		+ "some(select course_id from elective where username ='"+username+"')";
    	// String sql = "select * from Course";
    	 initConnection();
    	 try {
    		  rs = ct.createStatement().executeQuery(sql);
    		  
    		  while(rs.next()) {
    			  System.out.println("学生查询自己的选课的情况queryStuSelCourseRes");
                  Course course = new Course();
                  course.setCourse_id(rs.getString("course_id"));
                  course.setCourse_name(rs.getString("course_name"));
                  course.setTeacher(rs.getString("teacher"));
                  course.setPoint(rs.getInt("point"));
                  course.setTime_1(rs.getString("time_1"));
                  course.setTime_2(rs.getString("time_2"));
                  course.setLocation(rs.getString("location"));
                  course.setLimitied(rs.getInt("limitied"));
                  
                  CourseSelList.add(course);
              }

    	 }catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
    	 
    	 return CourseSelList;
     }

     //计算已经选课学生的数量
     public static int countSelecteds(String course_id,String role) {
    	 int selecteds=0;
    	 String sql="select count(course_id) selecteds from elective e join user u " + 
    	 		"on e.username=u.username  where role='"+role+"'and course_id='"+course_id+"'";
  //select count(course_id) selecteds from elective e join user u 
  //on e.username=u.username  where role='0'and course_id='00002'; 
    	 initConnection();
    	 try {
    		  rs = ct.createStatement().executeQuery(sql);
    		   if(rs.next()) {
    		   selecteds=rs.getInt("selecteds");
    		   System.out.println("选课的人数为"+selecteds+rs.next());
    		   }
    		   
    	 }catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
    	 
    	 return selecteds;
     }

     
     
 
     
     //取消选课也就是删除elective表的记录
     public static boolean delSelCourse(String username,String course_id) {
    	 boolean flag= true;
    	 String sql="delete from elective where username = ? and course_id= ?";
    	 initConnection();
    	 
    	 try {
			ps=ct.prepareStatement(sql);
			ps.setString(1,username);
			ps.setString(2, course_id);
			
			if(ps.executeUpdate()!=1) {
				flag=false;
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection();
		}
    	 return flag;
     }
     
     
     public static boolean delCourse(String course_id) {
    	 boolean flag= true;
    	 String sql="delete from course where course_id= ?";
    	 initConnection();
    	 
    	 try {
			ps=ct.prepareStatement(sql);
			ps.setString(1,course_id);
			
			
			if(ps.executeUpdate()!=1) {
				flag=false;
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection();
		}
    	 return flag;
     }

}



