package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.api.ProductGroupService;
import service.api.ProductService;
import service.api.UserService;
import service.impl.ProductGroupServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.UserServiceImpl;
@WebServlet("/exitsession")
public class exitSession extends HttpServlet{
	
	 			/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
	static index index = new index();
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			HttpSession session = request.getSession();
			session.invalidate();
			
			  RequestDispatcher rd = null;
			  rd=getServletContext().getRequestDispatcher("/index");
			  rd.forward(request, response);
			 
		}
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				HttpSession session = request.getSession();
				session.invalidate();
				 RequestDispatcher rd = null;
				  rd=getServletContext().getRequestDispatcher("/index");
				  rd.forward(request, response);
				 
			 		 
		}
		
}
