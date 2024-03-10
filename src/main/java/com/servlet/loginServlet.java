package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.dao.UserDao;
import com.db.DBConnection;
import com.user.UserDetails;

@WebServlet("/LoginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public loginServlet() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("uemail");
		String password = request.getParameter("upassword");
		
		UserDetails us = new UserDetails();
		us.setEmail(email);
		us.setPassword(password);
		
		UserDao dao = new UserDao(DBConnection.getCon());
		UserDetails user = dao.loginUser(us);
		
		if(user != null)
		{
			HttpSession session = request.getSession();
			session.setAttribute("userD", user);
			response.sendRedirect("home.jsp");
		}
		else
		{
			HttpSession session = request.getSession();
			session.setAttribute("login-failed", "Invalid username and password!");
			response.sendRedirect("login.jsp");
		}
		
	}

}
