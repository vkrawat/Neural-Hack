package com.virtusa.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class database 
{
	public static Statement st;
	public static Statement getStatement() throws ClassNotFoundException,SQLException {
		if(st==null) {
	final String url = "jdbc:sqlserver://localhost;databaseName=bankm";
    final String user = "sa";
    final String password = "admin1234";
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    Connection cn=DriverManager.getConnection(url,user,password);
    Statement ps=cn.createStatement();
    st=ps;
    return st;
		}
		else return st;
	}
}
