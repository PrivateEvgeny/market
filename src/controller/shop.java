package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.UserDTO;
import entity.Product;
import entity.UserRole;
import service.api.ProductGroupService;
import service.api.ProductService;
import service.api.UserService;
import service.impl.ProductGroupServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.UserServiceImpl;
@WebServlet("/shop")
public class shop extends HttpServlet{
	
	 			/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
	static String name = null;
    static HttpSession session = null;
    static UserDTO userDTO = null;
    static UserRole role = null;
   
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			
			 PrintWriter writer = response.getWriter();
			 String answer = response.getHeader("Shop");
			 writer.println("<html>");
		//	 writer.println("hello!, this is shop!");
		//	 index.getHeader(writer, request);
			 HttpSession session = request.getSession(true);
			 String name = (String) session.getAttribute("name");
			 writer.println("<div id= 'header'>");
				writer.println("hello, " + name);
				writer.println("<br> <a href='/market-on-web/item?all=true'>Enter to shop</a>");
				writer.println("</div>"); 
			 
			 writer.println("</html>");
			if (answer!=null) writer.println(answer);
			 
		}
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			 PrintWriter writer = response.getWriter();
			 writer.println("<html>");
			 HttpSession session = request.getSession(true);
			 if (session.getAttribute("userDTO")!=null){
					 
					 String name = (String) session.getAttribute("name");
					 writer.println("<div id= 'header'>");
						writer.println("hello, " + name);
						writer.println("<br> <a href='/market-on-web/item?all=true'>Enter to shop</a>");
						writer.println("</div>");
						
					// index.getHeader(writer, request);
			 } else{
				 RequestDispatcher rd = null;
				  rd=getServletContext().getRequestDispatcher("/index");
				  rd.forward(request, response);
				 
			 }		 
		}
 static void getHeader(PrintWriter writer, HttpServletRequest request){
		
		session = request.getSession(true);
		if (session.getAttribute("userDTO")==null){
			
			 
	        writer.println("<div id= 'header'>");
			writer.println("You havent login!");
			writer.println("<br>Please <a href='/market-on-web/index'>visit main page to login</a>");
			writer.println("</div>");
			
			throw new RuntimeException("not loggin");
		}else {
		name = (String) session.getAttribute("name");
		userDTO = (UserDTO) session.getAttribute("userDTO");
		role = (UserRole) userDTO.getRole();
		writer.println("<div id= 'header'>");
		writer.println("hello, " + name);
		writer.println("<br> exit: " + "<a href='/market-on-web/exitsession'>UnLoggin</a>");
		writer.println("<br> User: " + "<a href='/market-on-web/updateuser'>update user info</a>");	
		writer.println("<br> User: " + "<a href='/market-on-web/item?all=true'>To All Products</a>");
		if (role.equals(entity.UserRole.USER)){
			writer.println("<br> User: " + "<a href='backet'>To Backet</a>");
			}
		if (role.equals(entity.UserRole.ADMIN)){
			writer.println("<br> Admin: " + "<a href='/market-on-web/users'>edit users</a>");
			writer.println("<br> Admin: " + "<a href='/market-on-web/category'>edit category</a>");	
			writer.println("<br> Admin: " + "<a href='/market-on-web/item?all=true'>edit products category</a>");
		}
		
		}
		writer.println("</div'>");
	}
}

