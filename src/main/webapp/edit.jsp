<%@page import="com.user.Post"%>
<%@page import="com.db.DBConnection"%>
<%@page import="com.dao.PostDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
UserDetails user1 = (UserDetails) session.getAttribute("userD");
if (user1 == null) {
	response.sendRedirect("login.jsp");
	session.setAttribute("login-error", "Please Login..");
}
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Notes</title>
<%@include file="all_components/allcss.jsp"%>
</head>
<body>

	<%
 Integer noteid = Integer.parseInt(request.getParameter("note_id"));
// out.println("Edit id = "+noteid);
	PostDao post = new PostDao(DBConnection.getCon());
	Post p = post.getDataById(noteid);

%>

	<div class="container-fluid">
		<%@include file="all_components/navbar.jsp"%>

		<h1 class="text-center">Edit Your Notes</h1>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<form action="NoteEditServlet" method="post">

						<input type="hidden" value="<%= noteid%>" name="noteid" />

						<div class="form-group">
							<label for="exampleInputTitle">Enter Title </label> <input
								type="text" class="form-control" id="exampleInputTitle"
								aria-describedby="emailHelp" name="title" value="<%= p.getTitle() %>" required="required">
						</div>
						<div class="form-group">
							<label for="exampleInputContent">Enter Content </label>
							<textarea rows="8" cols="" class="form-control" name="content" 
								required="required"><%= p.getContent() %></textarea>
						</div>
						<div class="container text-center">
							<button type="submit" class="btn btn-primary">Update Notes</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%@include file="all_components/footer.jsp"%>

</body>
</html>