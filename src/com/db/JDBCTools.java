package com.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;



public class JDBCTools {
	private static Connection ct=null;
	private static String driverClass = null;
	private static String jdbcUrl =null;
	private static String dbUser = null;
	private static String dbPassword = null;
	
	
	public static Connection getConnection()  {
		
		
		InputStream  in =JDBCTools.class.getClassLoader().getResourceAsStream("jdbc.properties") ;
		
		Properties properties = new Properties();
		
		//��ȡ�����ļ�
		try {
			properties.load(in);
			driverClass =properties.getProperty("driver");
			jdbcUrl = properties.getProperty("jdbcUrl");
			dbUser= properties.getProperty("user");
			dbPassword = properties.getProperty("password");
			
		} catch (IOException e) {
			System.out.println("��ȡʧ��...");
			e.printStackTrace();
		}
		//��������
		
			try {
				Class.forName(driverClass);
			} catch (ClassNotFoundException e1) {
				System.out.println("��������ʧ��");
				e1.printStackTrace();
			}
			
			try {
				ct = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
			} catch (SQLException e) {
				System.out.println("�������ݿ�ʧ��");
				e.printStackTrace();
			}
			
	
		return ct;
		
	}
	
	public static void releaseSource(ResultSet rs,PreparedStatement ps,Connection ct) {
		if(rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				System.out.println("ResultSet close fail");
				e.printStackTrace();
			}
		}
		
		if(ps !=null) {
			try {
				ps.close();
			} catch (Exception e) {
				System.out.println("PreparedStatement close fail");
				e.printStackTrace();
			}
		}
		
		if(ct != null) {
			try {
				ct.close();
			} catch (Exception e) {
				System.out.println("Connectionment close fail");
				e.printStackTrace();
			}
		}
	}
	
	
}
