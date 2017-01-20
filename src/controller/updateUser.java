package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.fabric.xmlrpc.base.Data;

import dto.UserDTO;
import entity.UserRole;
import entity.UserSex;
import service.api.ProductGroupService;
import service.api.ProductService;
import service.api.UserService;
import service.impl.ProductGroupServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.UserServiceImpl;

@WebServlet("/updateuser")
public class updateUser extends HttpServlet{
	private static final long serialVersionUID = 1L;
	 private static UserService userService = new UserServiceImpl();
	    private static ProductGroupService pgService = new ProductGroupServiceImpl();
	    private static ProductService pService = new ProductServiceImpl();
	    static String name = null;
	    static HttpSession session = null;
	    static UserDTO userDTO = null;
	    static UserRole role = null;
	    static int id = 0;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DoGet in updateUser"); 
		PrintWriter writer = response.getWriter();
		id=0;
		boolean mistaken = false;
		writer.println("<html>");
		 session = request.getSession(true);
		 role=  (entity.UserRole)session.getAttribute("role");
		 String result=null;
		 String idString = request.getParameter("id");
		 String update = request.getParameter("update");
		 boolean updateUser =false;
		 if (update!=null&& update.equals("true")){
			 updateUser =true;
			 UserDTO userDTO = userService.FindById(id);
			 Date birthday = null;
			 String email = request.getParameter("email");
			 String firstname = request.getParameter("firstname");
			 Integer id2 = Integer.parseInt(request.getParameter("id"));
			 String login = request.getParameter("login");
			 String password = request.getParameter("password");
			 try{
			 entity.UserRole role2 = UserRole.valueOf(request.getParameter("role"));
			 userDTO.setRole(role2);
			 }catch (Exception e){
				 result="Illegal Role,";
			 }
			 String secondname =request.getParameter("secondname");
			 try{
			 entity.UserSex sex =UserSex.valueOf(request.getParameter("sex"));
			 userDTO.setSex(sex);
			 }catch (Exception e){
				 result+=" Illegal Sex,";
			 }
			 userDTO.setBirthday(birthday);
			 userDTO.setFirstName(firstname);
			 userDTO.setId(id2);
			 userDTO.setEmail(email);
			 userDTO.setLogin(login);
			 userDTO.setPassword(password);
			
			 userDTO.setSecondName(secondname);
			
			 try{
			  result+= "trying to update: " + userService.update(id, userDTO);
			 }catch (Exception e){
				 result+=" smth in datebase, illegal state";
			 }		 			 
		 }
		 
		 
		 if (idString!=null && !idString.equals("")){
			 id = Integer.parseInt(idString);
		 }
		 
		 shop.getHeader(writer, request);
		 
		 userDTO = (UserDTO) session.getAttribute("userDTO");
		 
		 System.out.println("before id=" +userDTO);
		 System.out.println(userDTO.getId());
		 
		 if (id>0){
			userDTO = userService.FindById(id);
			if (userDTO.getFirstName()==null || userDTO.getFirstName().equals("null")){
				mistaken = true;
			}
		 }
		 System.out.println("after id= " + userDTO);

		 if (mistaken) {
			 result = "havent find user by id";
		 System.out.println(result);
		 } 
		 
		 System.out.println(userDTO);
		 System.out.println(userDTO.getId());
		 String mainText ="<div id ='main'>";
		   mainText+= "<br>UPDATE USER PAGE";
		 //  mainText ="<br><br><a href='index?all=true'>Back to the All Products</a>";
		   mainText+= "<br>";
		   mainText+= "<br>";
		   
		   String birthday = "1";
		   String email = userDTO.getEmail();
		   String firstname = userDTO.getFirstName();
		   Integer id = userDTO.getId();
		   String login = userDTO.getLogin();
			String password = userDTO.getPassword();
			entity.UserRole role2 = userDTO.getRole();
			String secondname =userDTO.getSecondName();
			entity.UserSex sex =userDTO.getSex();
		   
			//u.setSex(UserSex.valueOf(res.getString("sex")));
		   if (!mistaken){
			mainText+="<form action='updateuser' method='get' >";
		   mainText += "<br>birthday:<input type='text' size='10' name='birthday' value='"+ birthday+ "'>";
		   mainText += "<br>email:<input type='text' size='10' name='email' value='"+ email+ "'>";
		   mainText += "<br>firstname:<input type='text' size='10' name='firstname' value='"+ firstname+ "'>";
		   mainText += "<br>login:<input type='text' size='10' name='login' value='"+ login+ "'>";
		   mainText += "<br>Id:<input type='text' size='10' name='id' value='"+ id+ "' style='display:none;'>";
		   mainText += "<br>password:<input type='text' size='10' name='password' value='"+ password+ "'>";
		   if (role.equals(entity.UserRole.ADMIN)){
		   mainText += "<br>role:<input type='text' size='10' name='role' value='"+ role2+ "'>";
		   }
		   mainText += "<br>secondname:<input type='text' size='10' name='secondname' value='"+ secondname+ "'>";
		   mainText += "<br>sex:<input type='text' size='10' name='sex' value='"+ sex+ "'>";
		     	
		   mainText += "<input type='text' size='10' name='update' value='"+ "true"+ "' style='display:none;'>";
	        
		   mainText += "<br><input type='submit' value='Send'> <br></form>";
		   mainText += "<br>";
		   }  
		   if (updateUser){
			   mainText += "<br>";
			  if (result==null) {
				  result = "<div style='color:red'>Info updated, pls unloggin to see this!	<>";
			  }
		   }
		   writer.println(mainText);
		   writer.println("<br>");
		   if(result!=null){
			   writer.println("<br><div style='color:red'>");
			   writer.println(result);
			   writer.println("</div>");
		   }
	}

}
