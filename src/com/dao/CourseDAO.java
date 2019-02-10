package com.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Pattern;

import com.db.JDBCTools;
import com.domain.Course;

public class CourseDAO {
	private static PreparedStatement ps=null;
	private static ResultSet rs=null;
	private static Connection ct=null;
//	private static String driver ="com.mysql.cj.jdbc.Driver";
//	private static String url = "jdbc:mysql://localhost/users?useSSL=FALSE&serverTimezone=UTC";
//	private static String dbuser = "root";
//	private static String dbpassword = "switch";

	/*
	 * �汾һ
	private static String driver= null;
	private static String jdbcUrl = null;
	private static String dbuser = null;
	private static String dbpassword = null;
	
	//���ݿ�����
     public static void initConnection(){
    	 
    	 InputStream in = 
 				Course.class.getClassLoader().getResourceAsStream("jdbc.properties");
 		Properties properties = new Properties();
 		try {
			properties.load(in);
		} catch (IOException e1) {
		System.out.println("��ȡ�ļ�ʧ��");
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
        	   ct= DriverManager.getConnection(jdbcUrl ,dbuser,dbpassword);
                System.out.println("Success connect Mysql server!");

           }
           catch (Exception e) {
                System.out.print("get data error!");
                e.printStackTrace();
           }
     }
     //���ݿ�ر�
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
     }*/
 
     //��ѯ��ʦ����Ŀγ�
     public static ArrayList queryAllTeaSelCourse(String username) {
    	 ArrayList CourseSelList=new ArrayList();
    	 //String sql="select * from course where teacher='"+teacher+"'";
    	 String sql = "select * from Course where course_id=some(select course_id from elective where username ='"+username+"')";
    	// initConnection();
    	 ct = JDBCTools.getConnection();
    	 try {
    		  rs = ct.createStatement().executeQuery(sql);
    		  
    		  while(rs.next()) {
    			  System.out.println("����queryAllTeaSelCourse");
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
			//closeConnection();
			JDBCTools.releaseSource(rs, ps, ct);
		}
    	 
    	 return CourseSelList;
     }

     //��ʦ��ӿ���Ŀγ�
     public static boolean AddCourse(Course course) {
    	 boolean flag=true;
    	 String sql="insert into course values(?,?,?,?,?,?,?,?)";
    	// initConnection();
    	 ct = JDBCTools.getConnection();
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
			//closeConnection();
			JDBCTools.releaseSource(rs, ps, ct);
		}
    	 return flag;
     }


     //��ѯѧ������ѡ��Ŀγ�
     public static ArrayList queryAllStuSelCourse() {
    	 ArrayList CourseSelList=new ArrayList();
    	 String sql="select * from course";
    	// initConnection();
    	 ct = JDBCTools.getConnection();
    	 try {
    		  rs = ct.createStatement().executeQuery(sql);
    		  
    		  while(rs.next()) {
    			  System.out.println("����ѧ��queryAllStuSelCourse");
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
			//closeConnection();
			JDBCTools.releaseSource(rs, ps, ct);
		}
    	 
    	 return CourseSelList;
     }
   
     
     //ѧ��ѡ�κ����elective��  ��ʦ��ӿα��ͬʱҲ����elective����
     public static boolean AddSelCourse(String username,String course_id) {
    	 boolean flag=true;
    	 String sql="insert into elective values(?,?)";
    	// initConnection();
    	 ct = JDBCTools.getConnection();
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
			//closeConnection();
			JDBCTools.releaseSource(rs, ps, ct);
		}
    	 return flag;
     }

     //�ж��Ƿ�ѡ���Ŀγ�
     public static int isStuSelCourse(String username,String course_id) {
    	 int isSel=0;
    	 String sql =" select count(*) from elective where username ='"+username + "' and course_id ='"+course_id+"'";
    	 //initConnection();
    	 ct = JDBCTools.getConnection();
    	 
    	 try {
			rs = ct.createStatement().executeQuery(sql);
			//System.out.println("isStuSelCourse"+rs.next());
 		   if(rs.next()) {
 		   isSel=rs.getInt(1);
 		   System.out.println(isSel+"===");
 		   System.out.println("�Ѿ�ѡ���ÿγ�"+isSel+rs.next());
 		   }
 		  
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//closeConnection();
			JDBCTools.releaseSource(rs, ps, ct);
		}
    	 return isSel;
     }
     
   //�ж��Ƿ�γ̵�id�ظ���
     public static int isTeaAddCourse(String course_id) {
    	 int isAdd=0;
    	 String sql ="select count(*) from course where course_id='"+course_id+"'";
    	 //initConnection();
    	 
    	 ct = JDBCTools.getConnection();
    	 
    	 try {
			rs = ct.createStatement().executeQuery(sql);
			//System.out.println("isStuSelCourse"+rs.next());
 		   if(rs.next()) {
 		   isAdd=rs.getInt(1);
 		   System.out.println(isAdd+"===");
 		   System.out.println("�Ѿ��ÿγ̵�id�ظ���"+isAdd+rs.next());
 		   }
 		   else {
 		   System.out.println("�ÿγ̵�idû���ظ�"+isAdd);
 		   }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//closeConnection();
			JDBCTools.releaseSource(rs, ps, ct);
		}
    	 return isAdd;
     }
     
 
   //�ж������ѧ���Ƿ�Ϊ�������Ͳ���С�ڵ���6�ʹ��ڵ���1
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
     
     
     
     //ѧ����ѯ�Լ���ѡ�ε����
     public static ArrayList queryStuSelCourseRes(String username) {
    	 ArrayList CourseSelList=new ArrayList();
   	     String sql = "select * from Course where course_id="
    	 		+ "some(select course_id from elective where username ='"+username+"')";
    	// String sql = "select * from Course";
    	 //initConnection();
   	     ct = JDBCTools.getConnection();
		
    	 try {
    		  rs = ct.createStatement().executeQuery(sql);
    		  
    		  while(rs.next()) {
    			  System.out.println("ѧ����ѯ�Լ���ѡ�ε����queryStuSelCourseRes");
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
			//closeConnection();
			JDBCTools.releaseSource(rs, ps, ct);
		}
    	 
    	 return CourseSelList;
     }

     //�����Ѿ�ѡ��ѧ��������
     public static int countSelecteds(String course_id,String role) {
    	 int selecteds=0;
    	 String sql="select count(course_id) selecteds from elective e join user u " + 
    	 		"on e.username=u.username  where role='"+role+"'and course_id='"+course_id+"'";
  //select count(course_id) selecteds from elective e join user u 
  //on e.username=u.username  where role='0'and course_id='00002'; 
    	 //initConnection();
    	 ct = JDBCTools.getConnection();
			
    	 try {
    		  rs = ct.createStatement().executeQuery(sql);
    		   if(rs.next()) {
    		   selecteds=rs.getInt("selecteds");
    		   System.out.println("ѡ�ε�����Ϊ"+selecteds+rs.next());
    		   }
    		   
    	 }catch (Exception e) {
			e.printStackTrace();
		}finally {
			//closeConnection();
			
			JDBCTools.releaseSource(rs, ps, ct);
		}
    	 
    	 return selecteds;
     }

     
     
 
     
     //ȡ��ѡ��Ҳ����ɾ��elective��ļ�¼
     public static boolean delSelCourse(String username,String course_id) {
    	 boolean flag= true;
    	 String sql="delete from elective where username = ? and course_id= ?";
    	// initConnection();
    	 ct = JDBCTools.getConnection();
			
    	 
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
			//closeConnection();
			
			JDBCTools.releaseSource(rs, ps, ct);
		}
    	 return flag;
     }
     
     
     public static boolean delCourse(String course_id) {
    	 boolean flag= true;
    	 String sql="delete from course where course_id= ?";
    	//initConnection();
    	 ct = JDBCTools.getConnection();
		
    	 
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
			//closeConnection();
			ct = JDBCTools.getConnection();
			JDBCTools.releaseSource(rs, ps, ct);
		}
    	 return flag;
     }

}



