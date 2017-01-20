package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.ProductDTO;
import dto.ProductGroupDTO;
import dto.UserDTO;
import entity.UserRole;
import service.api.ProductGroupService;
import service.api.ProductService;
import service.api.UserService;
import service.impl.ProductGroupServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.UserServiceImpl;

@WebServlet("/updateitem")
public class updateItem extends HttpServlet{
	private static final long serialVersionUID = 1L;
	 private static UserService userService = new UserServiceImpl();
	    private static ProductGroupService pgService = new ProductGroupServiceImpl();
	    private static ProductService pService = new ProductServiceImpl();
	    static String name = null;
	    static HttpSession session = null;
	    static UserDTO userDTO = null;
	    static ProductDTO productDTO = new ProductDTO();
	    static UserRole role = null;
	    static int id =0;
	    static String mainText="";
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
	    	 
			 PrintWriter writer = response.getWriter();
			 String idString = (String) request.getParameter("id");
			 String nameGroup = (String) request.getParameter("group");
			 String idGroupString = (String) request.getParameter("delgroup");
			 int delgroup =0;
			if (idGroupString!=null){
				delgroup=Integer.parseInt((String) request.getParameter("delgroup"));
			}
			 shop.getHeader(writer, request);
			 
			 
			 
			 
			 if (idString!=null  ){
			   id = Integer.parseInt(idString);	
			   if (id>0){
				   productDTO=pService.findByID(id);
				   
				   //add product to group
				   
				   if (nameGroup!=null  ){
						 ProductGroupDTO pgDTO = pgService.findByName(nameGroup);
						 if (pgDTO!=null){
							pService.addGroupToProduct(pgDTO.getId(),productDTO );
						 }
						 
					 }
				   
				   
				   if (delgroup>0  ){
						 ProductGroupDTO pgDTO = pgService.findByID(delgroup);
						 if (pgDTO!=null){
							pService.delGroupFromProduct(pgDTO.getId(),id);
						 }
						 
					 }
				   
				   
				   
				   mainText ="<div id ='main'>";
				   mainText+= "<br>UPDATE PRODUCT PAGE";
				   mainText ="<br><br><a href='item?all=true'>Back to the All Products</a>";
				   mainText+= "<br>";
				   mainText+= "<br>";
				   mainText+="<form action='updateitem' method='post' >";
				   String title = productDTO.getTitle();
				   String description = productDTO.getDescription();
				   Double price = productDTO.getPrice();
				   Integer count = productDTO.getCount();
				   
				   mainText += "<br>Title:<input type='text' size='10' name='title' value='"+ title+ "'>";
				   mainText += "<br>Description:<input type='text' size='10' name='description' value='"+ description+ "'>";
				   mainText += "<br>Price:<input type='text' size='10' name='price' value='"+ price+ "'>";
				   mainText += "<br>Count:<input type='text' size='10' name='count' value='"+ count+ "'>";
				   mainText += "<br>Id:<input type='text' size='10' name='id' value='"+ id+ "' style='display:none;'>";
				   mainText += "<br><input type='submit' value='Send'> <br></form>";
				   mainText += "<br>";
				   mainText += "ProductCategories";
				   int countGroups = 0;
				   if (pService.getAllIdGroupinProductDTO(id)!=null){
					   countGroups = pService.getAllIdGroupinProductDTO(id).size();
				   }
				   if (countGroups>0){
					   
					   for(int k=0; k<countGroups; k++){
						   mainText += "<br>";
						   int pgId = pService.getAllIdGroupinProductDTO(id).get(k);
						   ProductGroupDTO pgDTO =pgService.findByID(pgId);
						   mainText += (k+1) + ". " + pgDTO.getTitle();
						   mainText += ">>>>" + "<a href = '/market-on-web/updateitem?id=" + id+"&delgroup=" +pgId +"'";
						   mainText += ">delete this group</a>";
					   }
					    
					   
				   }else{
					   mainText += "<br>Nothing to show";
				   }
				   
				   mainText += "<br> ";
				   mainText += "<br> ";
				   	
				   mainText += "Add to new group this product";
				   mainText+="<form action='updateitem' method='get' name='group'>";
				   mainText += "<select name='group'> ";
				   	countGroups =pgService.getAllProductDTO().size();
				  
				   for(int k=0; k<countGroups; k++){
				//	   <option>Пункт 1</option>
					   			mainText += "<option>";
							   mainText += pgService.getAllProductDTO().get(k).getTitle();
							   mainText += "</option>";
				   }
				  
				   mainText += "</select>";  
				   mainText += "<br><input type='text' size='10' name='id' value='"+ id+ "' style='display:none;'>";
				   mainText += "<br><input type='submit' value='Add to group'> <br></form>";
			   }else mainText = "havent find this item";
			 }else{
				 mainText = "smth gone bad";
				 
				 
			 }
			 
			 writer.println(mainText);
			 
			 session = request.getSession(true);
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
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
			  reasonFail = "bad id";
			  int id2 = Integer.parseInt(request.getParameter("id"));
			  reasonFail = "bad database";
			  System.out.println(count);
			  ProductDTO newPro = new ProductDTO();
			  newPro.setCount(count);
			  newPro.setDescription(description);
			  newPro.setPrice(price);
			  newPro.setTitle(title);
			  newPro.setId(id2);
			  pService.update(newPro);
			 }catch (Exception e){
				 result = false;
				 System.out.println(e);
			 }finally{
				 writer.println("Result:" + result);
				 if (!result){
					 writer.println("<br>Reason:" + reasonFail);
				 }
				 writer.println("<br>Go back:<a href='/market-on-web/item?all=true'>shop</a>");
			 }
	    	
	    
	    	
	    }
}
