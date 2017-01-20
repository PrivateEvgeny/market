package controller;

import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedList;

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


@WebServlet("/category")
public class category extends HttpServlet{
	private static final long serialVersionUID = 1L;
	 private static UserService userService = new UserServiceImpl();
	    private static ProductGroupService pgService = new ProductGroupServiceImpl();
	    private static ProductService pService = new ProductServiceImpl();
	    static String name = null;
	    static HttpSession session = null;
	    static UserDTO userDTO = null;
	    static UserRole role = null;
	    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter writer = response.getWriter();
		 writer.println("<html>");
		 String answer ="";
		 session = request.getSession(true);
		 shop.getHeader(writer, request);
		 String result = null;
		 userDTO = (UserDTO) session.getAttribute("userDTO");
		 String update = (String) request.getParameter("update");
		 role = (UserRole) userDTO.getRole();
			
			if (role.equals(entity.UserRole.ADMIN)){
				String delete = (String)request.getParameter("delete");
				int deleteId=0;
				if(delete!=null){
					deleteId = Integer.parseInt(delete);
					
				}
				
				if (deleteId>0){
					result = "trying to delete category" ;
					
					ProductGroupDTO prodDTO = new ProductGroupDTO();
					prodDTO = pgService.findByID(deleteId);
					int k=0;
			    	k = pService.getAllProductDTObyGroup(prodDTO).size();
						if(k>0){
						result+="<br>There are some products in this tag";	
						}	else{
							pgService.delete(deleteId);
						}
					
				}
				
				if (update!=null && update.equals("true")){
						System.out.println("adding group");
						ProductGroupDTO prodDTO = new ProductGroupDTO();
					
					 String description = request.getParameter("description");
					 String title = request.getParameter("title");
									 
					 prodDTO.setDescription(description);
					 prodDTO.setTitle(title);
					 
					 try{
					 result = "trying to update user, result:" + pgService.create(prodDTO);
					 } catch (Exception e){
						 result += " smth gone wrong!"; 
					 }
				}	
				answer+="<br><br>ALL PRODUCT GROUP:";
				int count =  pgService.getAllProductDTO().size();
				answer += "<br><table><tr><td>title</td>"+
				"<td>description</td> "+
				"<td>id</td>"+
				"<td>Update</td>"+
				"<td>Delete</td>"+
				"</tr>"+
						"";
				for (int i=0; i<count; i++){
					ProductGroupDTO proDDTO= pgService.getAllProductDTO().get(i);
					
					 String description = proDDTO.getDescription();
					 String title = proDDTO.getTitle();
					 Integer id = proDDTO.getId();
					 
					 answer+="<tr>"+
							 "<td>"+ title+"</td> "+
							 "<td>"+ description+"</td> "+
							 "<td>"+ id +"</td> "+
								"<td><a href='updatecategory?id="+id+"'>Update</a></td>"+
								"<td><a href='category?delete="+id+"'>Delete</a></td>"+
								"</tr>"+
										"";
					}
					answer+="</table>";
					 
					 answer+="<br>";
						answer+="<br>ADD NEW PRODUCT GROUP";
						answer+="<br>";
						
						answer+="<form action='category' method='get' >";
						answer+= "<br>title:<input type='text' size='10' name='title'" + ">";
						answer+= "<br>description:<input type='text' size='10' name='description' "+ ">";
						answer += "<input type='text' size='10' name='update' value='true' style='display:none;'>";
						answer += "<br><input type='submit' value='Send'> <br></form>";
						answer += "<br>";
				
			}else{
				answer+= "You havent permission to edit this sector!";
			} 
				
			
			
			writer.println(answer);
			if (result!=null){
				writer.println("<br><div style='color:red'>");
				writer.println(result);
				writer.println("</div>");
			}
	}
}
