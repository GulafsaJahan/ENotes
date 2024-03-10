package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.dao.UserDao;
import com.db.DBConnection;
import com.user.UserDetails;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/registerServlet")
public class UserServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		UserDetails us = new UserDetails();
		us.setName(name);
		us.setEmail(email);
		us.setPassword(password);

		UserDao dao = new UserDao(DBConnection.getCon());
		boolean f = dao.addUser(us);
		HttpSession session;
		
		if (f) {
			session = request.getSession();
			session.setAttribute("user-msg", "User Register successfully ..");
			response.sendRedirect("register.jsp");
		}
		else
		{
			session = request.getSession();
			session.setAttribute("error-msg", "Something went wrong on server ..");
			response.sendRedirect("register.jsp");
		}
	}
}
