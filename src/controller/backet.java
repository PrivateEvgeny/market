package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.ProductDTO;
import dto.TransactionDTO;
import entity.Product;
import entity.User;
import dto.UserDTO;
import service.api.ProductGroupService;
import service.api.ProductService;
import service.api.UserService;
import service.impl.ProductGroupServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.TransactonImpl;
import service.impl.UserServiceImpl;


@WebServlet("/backet")
public class backet extends HttpServlet{
	
	 static List<ProductDTO> productList = new LinkedList<>();
	 static final long serialVersionUID = 1L;
	  static UserService userService = new UserServiceImpl();
	   static ProductGroupService pgService = new ProductGroupServiceImpl();
	    static ProductService pService = new ProductServiceImpl();	
	    static TransactonImpl tService = new TransactonImpl();
	public backet(){
		
	}
	
	
	
	public void  addProduct(ProductDTO e){
		
		productList.add(e);
	}
	
	public boolean  deleteProduct(int i){
		boolean result = true;
		try{productList.remove(i);}
		catch (Exception e){
			result = false;
			e.printStackTrace();
			
		}
		return result;
	}
	
	public List<ProductDTO> getProducts(){
		
		return productList; 
	}
	
	public boolean clearBacket(){
		boolean result = true;
		productList.clear();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter writer = response.getWriter();
		 writer.println("<html>");
		 HttpSession session = request.getSession(true);
		 
		 if (session.getAttribute("userDTO")==null){
				
				 
			        writer.println("<div id= 'header'>");
					writer.println("You havent login!");
					writer.println("<br>Please <a href='index'>visit main page to login</a>");
					writer.println("</div>");
					
				// index.getHeader(writer, request);
		 } else{
			shop.getHeader(writer, request);
			 String add = (String) request.getParameter("add");
			 String idString = (String) request.getParameter("id");
			 String countString= (String) request.getParameter("count");
			 String transactionString= (String) request.getParameter("transaction");
			 int count = 0;
			 int id=0;
			 int catId=0;
		    	if (idString!=null && idString !=""){ id = Integer.parseInt(idString);}
			  if (countString!=null && idString !=""){ count = Integer.parseInt(countString);}
			
				if (transactionString!=null && transactionString.equals("true")){
					
					if (session.getAttribute("backet")!=null){ 
						this.productList =  (List<ProductDTO>) session.getAttribute("backet");
					}
					int countProducts = this.getProducts().size();
					if (countProducts >0){
						for (int i=0; i<countProducts; i++){
							ProductDTO productDTO = new ProductDTO();
							productDTO = (productList.get(i));
							TransactionDTO tr = new TransactionDTO();
							tr.setProduct(productDTO);
							tr.setProductCount(productList.get(i).getCount());
							tr.setProductPrice(productList.get(i).getPrice());
							tr.setUser((UserDTO)session.getAttribute("userDTO"));
							boolean g= tService.add(tr, productDTO);
							System.out.println("trans:" + g);
							if (g){
								productList.remove(i);
							}
						}
					}
					
				}
				if (add!=null && add.equals("true")){
					ProductDTO productDTO = new ProductDTO();
					productDTO = pService.findByID(id);
					productDTO.setCount(count);
					this.addProduct(productDTO);
					session.setAttribute("backet", this.getProducts());
					System.out.println("putting backet");
					System.out.println(this.getProducts().size());
				}
				
				
				if (session.getAttribute("backet")!=null){ 
					
					this.productList =  (List<ProductDTO>) session.getAttribute("backet");
				}
				int countProducts = this.getProducts().size();
				
				
				writer.println("<br>");
				writer.println("<br>BACKET");
				
				if (countProducts >0){
					
					writer.println("<form action='backet' method='get' >");
					writer.println("<input type='text' size='5' name='transaction' value='true' style='display:none;'>");
					
					for (int i=0; i<countProducts; i++){
						writer.println("<br>");
						ProductDTO productDTO = new ProductDTO();
						productDTO =this.getProducts().get(i);
						writer.println(" item= " + productDTO.getTitle() );
						writer.println(", count= " + productDTO.getCount() );
						writer.println(", price= " + productDTO.getPrice() );
						//writer.println("<input type='text' size='5' name='id' value='" + productDTO.getId()+ "' style='display:none;'>");
						//writer.println("<input type='text' size='5' name='count' value='" + productDTO.getCount() + "style='display:none;' >");
					}
					
					writer.println("<br><br><input type='submit' value='Buy!'> <br></form>");
					
					
					
					
				}else {
					writer.println("<br>backet is empty;");
				} 
					
			 
		 }		 
	}
	
}
