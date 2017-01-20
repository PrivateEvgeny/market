package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.api.UserDAO;
import dao.impl.UserDAOImpl;
import dto.ProductDTO;
import dto.ProductGroupDTO;
import dto.UserDTO;
import entity.User;
import entity.UserRole;
import entity.UserSex;
import helper.Transformer;
import service.api.ProductGroupService;
import service.api.ProductService;
import service.api.UserService;
import service.impl.ProductGroupServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.UserServiceImpl;
@WebServlet("/addProduct")
public class addProduct extends HttpServlet {
	 private static UserService userService = new UserServiceImpl();
	    private static ProductGroupService pgService = new ProductGroupServiceImpl();
	    private static ProductService pService = new ProductServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		 writer.println("<html>");
		 HttpSession session = request.getSession(true);
		 String resultString ="<br> updaite fail:";
		 String reasonFail = "bad title";
		 boolean result = true;
		 try{
		  String title = (String) request.getParameter("title");
		  reasonFail = "bad decr";
		  String description = (String) request.getParameter("description");
		  reasonFail = "bad price";
		  Double price = Double.parseDouble(request.getParameter("price"));
		  reasonFail = "bad count";
		  int count = Integer.parseInt(request.getParameter("count"));
		  reasonFail = "bad database";
		  ProductDTO newPro = new ProductDTO();
		  newPro.setCount(count);
		  newPro.setDescription(description);
		  newPro.setPrice(price);
		  newPro.setTitle(title);
		  pService.create(newPro);
		 }catch (Exception e){
			 result = false;
		 }finally{
			 writer.println("Result:" + result);
			 if (!result){
				 writer.println("<br>Reason:" + reasonFail);
			 }
			 writer.println("<br>Go back:<a href='/market-on-web/item?all=true'>shop</a>");
		 }
	}
}
