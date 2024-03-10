package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.user.UserDetails;

public class UserDao {
	private Connection con;

	public UserDao(Connection con) {
		super();
		this.con = con;
	}
	
	public boolean addUser(UserDetails us)
	{
		boolean f = false;
		try {
			
			String query = "insert into user(name, email, password) values(?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, us.getName());
			ps.setString(2, us.getEmail());
			ps.setString(3, us.getPassword());
			int res = ps.executeUpdate();
			if(res == 1)
			{
				f = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return f;
	}
	
	public UserDetails loginUser(UserDetails us)
	{
		UserDetails user = null;
		try {
		String query = "select * from user where email =? and password = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, us.getEmail());
		ps.setString(2, us.getPassword());
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			user = new UserDetails();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password")); // user.setPassword("password");
			
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
}
