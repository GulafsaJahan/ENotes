package com.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private static Connection con;

	public static Connection getCon() {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/enotes", "root", "Gula@7860");
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return con;
	}

}
