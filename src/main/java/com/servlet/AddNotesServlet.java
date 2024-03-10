package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.dao.PostDao;
import com.db.DBConnection;
import com.user.Post;

@WebServlet("/AddNotesServlet")
public class AddNotesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddNotesServlet() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int uid = Integer.parseInt(request.getParameter("uid"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		PostDao dao = new PostDao(DBConnection.getCon());
		boolean f = dao.AddNotes(title, content, uid);
		
		if(f)
		{
			System.out.println("data inserted successfully..");
			response.sendRedirect("showNotes.jsp");		}
		else
		{
			System.out.println("data not inserted");
		}
	}

}
