package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.dao.PostDao;
import com.db.DBConnection;

@WebServlet("/NoteEditServlet")
public class NoteEditServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			
			Integer noteid = Integer.parseInt(request.getParameter("noteid"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			PostDao dao = new PostDao(DBConnection.getCon());
			boolean f = dao.PostUpdate(noteid, title, content);
			
			if(f)
			{
				System.out.println("Data updated successfully");
				HttpSession session = request.getSession();
				session.setAttribute("updateMsg", "Notes updated successfully..");
				response.sendRedirect("showNotes.jsp");
			}
			else
			{
				System.out.println("Data not updated");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
