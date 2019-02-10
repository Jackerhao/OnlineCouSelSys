package com.test;

import java.sql.Connection;

import org.junit.Test;

import com.dao.CourseDAO;
import com.db.JDBCTools;

public class Test01 {
	
	@Test
	public void testJDBCTools() {
		Connection ct = JDBCTools.getConnection();
		System.out.println(ct);
		
	}
}
