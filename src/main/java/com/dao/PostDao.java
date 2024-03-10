package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.user.Post;
import com.user.UserDetails;

public class PostDao {
	private Connection con;

	public PostDao(Connection con) {
		super();
		this.con = con;
	}

	public boolean AddNotes(String ti, String cont, int ui)
	{
		boolean f = false;
		
		try {
			String qu = "insert into post(title, content, uid) values(?,?,?)";
			
			PreparedStatement ps = con.prepareStatement(qu);
			
			ps.setString(1, ti);
			ps.setString(2, cont);
			ps.setInt(3, ui);
			
			int i = ps.executeUpdate();
			if(i == 1)
			{
				f = true;
			}
		} catch (Exception e) {
			
		}
	
	return f;
	}
	
	
	public List<Post> getData(int id)
	{
	  List<Post> list = new ArrayList<Post>();
	  Post po = null;
	  try {
		  
		  String query1 = "select * from post where uid=? order by id DESC";
		  PreparedStatement ps = con.prepareStatement(query1);
		  ps.setInt(1, id);
		  
		  ResultSet result = ps.executeQuery();
		  while(result.next())
		  {
			  po = new Post();
			po.setId(result.getInt(1));
			po.setTitle(result.getString(2));
			po.setContent(result.getString(3));
			po.setPdate(result.getTimestamp(4));
			list.add(po);
			
		  }
		  
	} catch (Exception e) {
		
	}
	   
	  return list;
	}
	
	
//	edit code for form 
	public Post getDataById(int noteId)
	{
		
		Post p = null;
		try {
			
			String qu = "select * from post where id=?";
			PreparedStatement ps = con.prepareStatement(qu);
			ps.setInt(1, noteId);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				p = new Post();
				p.setId(rs.getInt(1));
				p.setTitle(rs.getString(2));
				p.setContent(rs.getString(3));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return p;
	}
	
//	update data 
	
	public boolean PostUpdate(int nid, String ti, String cont)
	{
		boolean f = false;
		try {
			
			String qu = "update post set title=? , content=? where id=?";
			PreparedStatement ps = con.prepareStatement(qu);
			ps.setString(1,ti);
			ps.setString(2, cont);
			ps.setInt(3, nid);
			int i = ps.executeUpdate();
			if(i == 1)
			{
				f = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
	
//	Delete data
	
	public boolean DeleteNotes(int nid)
	{
		boolean f = false;
		
		try {
			
			String qu =" delete from post where id=?";
			PreparedStatement ps = con.prepareStatement(qu);
			ps.setInt(1, nid);
			int count = ps.executeUpdate();
			if(count == 1)
			{
				f = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}

}
