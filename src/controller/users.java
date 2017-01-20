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


@WebServlet("/users")
public class users extends HttpServlet{
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
					result = "trying to delete user, result:" + userService.delete(deleteId);
					
				}
				
				if (update!=null && update.equals("true")){
						System.out.println("adding user");
						UserDTO userDTO = new UserDTO();
					 Date birthday = null;
					 String email = request.getParameter("email");
					 String firstname = request.getParameter("firstname");
					
					 String login = request.getParameter("login");
					 String password = request.getParameter("password");
					 try{
					 entity.UserRole role2 = UserRole.valueOf(request.getParameter("role"));
					 userDTO.setRole(role2);
					 }catch (Exception e){
						 result="Mistaken: Illegal role,";
					 }
					 String secondname =request.getParameter("secondname");
					 try{
					 entity.UserSex sex =UserSex.valueOf(request.getParameter("sex"));
					 userDTO.setSex(sex);
					 }catch (Exception e){
						 result+="Illegal SEX,";
					 }
					 
					 userDTO.setBirthday(birthday);
					 userDTO.setFirstName(firstname);
					 userDTO.setLogin(login);
					 userDTO.setPassword(password);
					 
					 userDTO.setSecondName(secondname);
					 
					 userDTO.setEmail(email);
					 try{
					 result = "trying to update user, result:" + userService.create(userDTO).getFirstName();
					 } catch (Exception e){
						 result += " smth gone wrong!"; 
					 }
				}
				
				
				answer+="<br><br>ALL USERS PAGE:";
				int count =  userService.getAllUsersDTO().size();
				answer += "<br><table><tr><td>email</td>"+
				"<td>firstname</td> "+
				"<td>id</td>"+
				"<td>login</td>"+
				"<td>password</td>"+
				"<td>Role</td>"+
				"<td>Secondname</td>"+
				"<td>Sex</td>"+
				"<td>Update</td>"+
				"<td>Delete</td>"+
				"</tr>"+
						"";
				for (int i=0; i<count; i++){
					userDTO = userService.getAllUsersDTO().get(i);
					Date birthday = null;
					 String email = userDTO.getEmail();
					 String firstname = userDTO.getFirstName();
					 Integer id = userDTO.getId();
					 String login = userDTO.getLogin();
					 String password = userDTO.getPassword();
					 entity.UserRole role = (userDTO.getRole());
					 String secondname =userDTO.getSecondName();
					 entity.UserSex sex =(userDTO.getSex());
					 
					 answer+="<tr>"+
							 "<td>"+ login+"</td> "+
							 "<td>"+ firstname+"</td> "+
								"<td>"+id+"</td>"+
								"<td>"+login+"</td>"+
								"<td>"+password+"</td>"+
								"<td>"+role+"</td>"+
								"<td>"+secondname+"</td>"+
								"<td>"+sex+"</td>"+
								"<td><a href='updateuser?id="+id+"'>Update</a></td>"+
								"<td><a href='users?delete="+id+"'>Delete</a></td>"+
								"</tr>"+
										"";
					}
					answer+="</table>";
					 
					 answer+="<br>";
						answer+="<br>ADD NEW USER";
						answer+="<br>";
						
						answer+="<form action='users' method='get' >";
						answer+= "<br>birthday:<input type='text' size='10' name='birthday'" + ">";
						answer+= "<br>email:<input type='text' size='10' name='email' "+ ">";
						answer+= "<br>firstname:<input type='text' size='10' name='firstname' >";
						answer += "<br>login:<input type='text' size='10' name='login'>";
						answer += "<br>password:<input type='text' size='10' name='password'>";
						answer += "<input type='text' size='10' name='update' value='true' style='display:none;'>";
						   answer += "<br>role:<input type='text' size='10' name='role' >";
						   answer += "<br>secondname:<input type='text' size='10' name='secondname' >";
						   answer += "<br>sex:<input type='text' size='10' name='sex'>";
						   answer += "<input type='text' size='10' name='update' value='"+ "true"+ "' style='display:none;'>";
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
