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

import java.util.LinkedList;
import java.util.List;
/**
 * Servlet implementation class index
 */
@WebServlet("/index")
public class index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private static UserService userService = new UserServiceImpl();
	    private static ProductGroupService pgService = new ProductGroupServiceImpl();
	    private static ProductService pService = new ProductServiceImpl();
    /**
     * Default constructor. 
     */
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 PrintWriter writer = response.getWriter();
		 String answer = response.getHeader("answer");
		 writer.println("<html>");
		 
		 HttpSession session = request.getSession(true);
		 System.out.println(session.getAttribute("userDTO"));
		 if (session.getAttribute("userDTO") !=null){
			 System.out.println("session already exists");
			 RequestDispatcher rd = null;
			  rd=getServletContext().getRequestDispatcher("/shop");
			  rd.forward(request, response);
			 writer.println("Session exists");
		 }else{
		 writer.println("hello! Pls login!");
		 writer.println("<form name='test' method='post' >" +
		  "<br>Login <input type='text' size='40' name = 'login'>" +
		  "<br>Pass <input type='text' size='40' name = 'password'>" +
		  "<input type='submit' value='Send'> <br>"
		  );
		 writer.println("</html>");
		 }
		if (answer!=null) writer.println(answer);
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		  String login = (String) request.getParameter("login");
		  String password = (String) request.getParameter("password");
		  
		  UserDTO userDTO = userService.findByLoginAndPassword(login, password);
		  if (userDTO.getFirstName() == null){
			  response.addHeader("answer", "no such user!");
			  
			  doGet(request, response);
			  
		  }else{
			  PrintWriter writer = response.getWriter();
			  String name = userDTO.getFirstName();
			  writer.println("<p>hello, ");
			  writer.println(name +"</p>");
			  try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			  HttpSession session = request.getSession(true);
			  session.setAttribute("userDTO", userDTO);	
			  session.setAttribute("name", name);	
			  session.setAttribute("role", userDTO.getRole());	
			  RequestDispatcher rd = null;
			  rd=getServletContext().getRequestDispatcher("/shop");
			  rd.forward(request, response);
		  }
	}
	
	public void getHeader(PrintWriter writer, HttpServletRequest request){
		
		HttpSession session = request.getSession(true);
		String name = (String) session.getAttribute("name");
		writer.println("<div id= 'header'>");
		writer.println("hello, " + name);
		writer.println("<br> exit: " + "<a href='/market-on-web/exitsession'>UnLoggin</a>");
		writer.println("</div'>");
	}
	
	
public void getShop(PrintWriter writer, HttpServletRequest request){
	getHeader(writer, request);
		
	}

}
