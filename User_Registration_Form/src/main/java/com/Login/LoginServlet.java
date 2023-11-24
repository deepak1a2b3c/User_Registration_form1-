package com.Login;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		String s=request.getParameter("name");
		String p=request.getParameter("password");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/shree","root","deep123");
			String query="insert into gungun values(?,?)";
			PreparedStatement ps=(PreparedStatement) con.prepareStatement(query);
			
			ps.setString(1,s);
			ps.setString(2,p);
			int i=ps.executeUpdate();
			if(i>0) {
				System.out.println("data Entered Successfully!!");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(s.equals("deepak")&&p.equals("kumar")) {
		RequestDispatcher rd=request.getRequestDispatcher("success.jsp");
		rd.forward(request, response);
		}
		else {
			System.out.println("Sorry!! Invalid user or password");
			/*
			 * RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
			 * rd.forward(request, response);
			 */
			response.sendRedirect("index.html");
		}		
	}
}