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


@WebServlet("/item")
public class item extends HttpServlet{
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
		 session = request.getSession(true);
		 if (session.getAttribute("userDTO")==null){
				
				 
			        writer.println("<div id= 'header'>");
					writer.println("You havent login!");
					writer.println("<br>Please <a href='index'>visit main page to login</a>");
					writer.println("</div>");
					
				// index.getHeader(writer, request);
		 } else{
			shop.getHeader(writer, request);
			getShop(writer, request);
			 
		 }		 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doGet(request,response);
		 
	}

	public void getHeader(PrintWriter writer, HttpServletRequest request){
		
		session = request.getSession(true);
		name = (String) session.getAttribute("name");
		userDTO = (UserDTO) session.getAttribute("userDTO");
		role = (UserRole) userDTO.getRole();
		writer.println("<div id= 'header'>");
		writer.println("hello, " + name);
		writer.println("<br> exit: " + "<a href='/market-on-web/exitsession'>UnLoggin</a>");
		//if (role.equals(entity.UserRole.ADMIN)){
			writer.println("<br> admin page: " + "<a href='/market-on-web/updateuser'>goto admin page</a>");	
		//}
		writer.println("</div'>");
	}

	public void getShop(PrintWriter writer, HttpServletRequest request){
		 
		 HttpSession session = request.getSession(true);
		 name = (String) session.getAttribute("name");
			userDTO = (UserDTO) session.getAttribute("userDTO");
			role = (UserRole) userDTO.getRole();
		 
		 String all = (String) request.getParameter("all");
		 String idString = (String) request.getParameter("id");
		 String idCatString = (String) request.getParameter("catid");
		 String edit = (String) request.getParameter("edit");
		 int id=0;
		 int catId=0;
		if (idString!=null && idString !=""){ id = Integer.parseInt(idString);}
		if (idCatString!=null && idCatString !=""){ catId = Integer.parseInt(idCatString);}
		 
		writer.println("<br><p>Main content of the page</p>");
		writer.println("<br>-----------------------------");
		
		 if (all!=null && all.equals("true")){
			// «десь если алл=тру
			 String button = "Buy";
			 String linkName="item";
			 String update ="";
			 if (role.equals(entity.UserRole.ADMIN)){
				 linkName = "updateitem";
				 button = "update";
				 update ="&update=true";
			 }
			System.out.println(all); 
			
			writer.println("<div id='Products'>");
			writer.println("ALL PRODUCTS PAGE");
			writer.println("<table><tr>");
			writer.println(" <td>id</td><td>title</td><td>description</td><td>price</td><td>count</td><td>Button</td> </tr> ");
			int countIdProduct = pService.getAllProductDTO().size();
			System.out.println(countIdProduct);
			ProductDTO newProduct = new ProductDTO();;
			for (int i=0; i<countIdProduct; i++){
				newProduct = pService.getAllProductDTO().get(i);
				 if (catId>0){
					 
					 ProductGroupDTO proGDTO = pgService.findByID(catId);
					 System.out.println(catId);
					 System.out.println(proGDTO);
					 newProduct= pService.getAllProductDTObyGroup(proGDTO).get(i);
					 countIdProduct =pService.getAllProductDTObyGroup(proGDTO).size();
				 }
				String title = newProduct.getTitle();
				String desc =  newProduct.getDescription();
				Double price = newProduct.getPrice();
				Integer count = newProduct.getCount();
				Integer id2 = newProduct.getId();
				writer.println(" <tr><td>"+id2+"</td><td>" +title+"</td><td>" + desc+ "</td><td>" +price+ "</td><td>" +count+ "</td><td>"+"<a href='/market-on-web/"+linkName + "?id="+id2+update+"'>" +button+ "</a>"+ "</td> </tr> ");	
			}
			
			
		    writer.println("</table>");
		    writer.println("<br> TAGS:");
		    writer.println("<a href='item?all=true'>Alltag</a>, ");
		    int groupCounter = pgService.getAllProductDTO().size();
		   
		    // spisok tegov
		    
		    for (int i=0; i<groupCounter; i++){
		    	ProductGroupDTO prodDTO = new ProductGroupDTO();
		    	prodDTO =pgService.findByID(i);
		    	int k =0;
		    	k = pService.getAllProductDTObyGroup(prodDTO).size();
		    	if (k>0){
		    		writer.print("<a href='item?all=true&catid=" +prodDTO.getId()+ "'>" +prodDTO.getTitle()+ "</a>");
		    		writer.print(", " );
		    	}
		    	
		    }
		    
		    
		    if (role.equals(entity.UserRole.ADMIN)){
		    	
		    	writer.println("<br>Adding new product:");
		    	writer.println("<form action='addProduct' method='get' >");
		    	writer.println("<table><tr>");
		    	writer.println(" <td>" +
		    			"Auto"+
		    			"</td><td>"+
		    			"<input type='text' size='5' name='title'>" +
		    			"</td><td>"+
		    			"<input type='text' size='5' name='description'>" +
		    			"</td><td>"+
		    			"<input type='text' size='5' name='price'>"+ 
		    			"</td><td>"+
		    			"<input type='text' size='5' name='count'>" );
		    	writer.println("</tr></table>");	
		    	writer.println("<input type='submit' value='Send'> <br></form>");
			}
		 }else if(id>0){
			 
			 		 
			 if (role.equals(entity.UserRole.ADMIN)){
				 writer.println("<br>Admin cant buy items");
			 }else{
				    try{ProductDTO newProduct = new ProductDTO();
				
				    newProduct = pService.findByID(id);
				    String title = newProduct.getTitle();
					String desc =  newProduct.getDescription();
					Double price = newProduct.getPrice();
					Integer count = newProduct.getCount();
					Integer id2 = newProduct.getId();
					writer.println("<br>");
					writer.println("<br> Lets buy item!<br>");
		
					writer.println("<form action='backet' method='get' >");
					writer.println("<input type='text' size='5' name='add' value='true' style='display:none;'>");
					writer.println("<br> title:" + title);
					writer.println("<br> desc:" + desc);
					writer.println("<br> price:" + price);
					writer.println("<br> count:" + count);
					writer.println("<input type='text' size='5' name='id' value='" + id+ "' style='display:none;'>");
					writer.println("<input type='text' size='5' name='count' value='" + count + "' >");
					
					
					
					writer.println("<br><br><input type='submit' value='Send'> <br></form>");
				    } catch (Exception e){
				    	 writer.println("<br>Smth gone bad: bad id!");
				    	 e.printStackTrace();
				    }
				    
			 }
		
				  
				
		 }else{
			 writer.println("Havent find anything to show");
			 writer.println("<br>");
			 writer.println("<a href='/market-on-web/item?all=true'>Come back to main page</a>");
		 }
		
	}
	
}
