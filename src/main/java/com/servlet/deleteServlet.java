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

@WebServlet("/deleteServlet")
public class deleteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Integer noteid = Integer.parseInt(request.getParameter("note_id"));
		PostDao dao = new PostDao(DBConnection.getCon());
		boolean f = dao.DeleteNotes(noteid);
		HttpSession session = null;
		if(f)
		{
			session = request.getSession();
			session.setAttribute("updateMsg", "Notes delete successfully..");
			response.sendRedirect("showNotes.jsp");
		}
		else
		{
			session = request.getSession();
			session.setAttribute("wrongMsg", "Something went wrong on server");
			response.sendRedirect("showNotes.jsp");
		}

	}

}
