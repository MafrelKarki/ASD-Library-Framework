package edu.mum.asd.library.dao;

import java.sql.*;

public class DB {
public static Connection getCon(){
	Connection con=null;
	try{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3307/elibrary?useSSL=false","root","yvan");	
	}catch(Exception e){System.out.println(e);}
	return con;
}
}
