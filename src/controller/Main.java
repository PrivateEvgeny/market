package controller;

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
 * Created by anton.kovalenko on 10/10/16.
 */
public class Main {
    private static UserService userService = new UserServiceImpl();
    private static ProductGroupService pgService = new ProductGroupServiceImpl();
    private static ProductService pService = new ProductServiceImpl();
    public static void main(String[] args) {
    //   for (int i=0; i<10; i++){
    //	   UserDTO user = new UserDTO();
    //	   user.setLogin("Login"+ i);
    //	   user.setPassword("Pass" + i);
    //	   user.setSex(UserSex.FEMALE);
    //	   user.setRole(UserRole.USER);
    //	   userService.create(user);
    //	   System.out.println(user.toString());
    	
    //   }
    	UserDTO user = new UserDTO();
    	
    	    	   user.setLogin("Login15");
    	    	   user.setPassword("Pass15" );
    	    	   user.setSex(UserSex.FEMALE);
    	    	   user.setRole(UserRole.USER);
    	    	   user.setSecondName("secondName14");
    	    	   user.setFirstName("fName14");
    	    	   user.setEmail("fff");
    	   // 	   userService.create(user);
    	 
    	 UserDTO by = userService.findByLoginAndPassword("Login15", "Pass15");
    	 userService.delete(7);	
    	 by = userService.FindById(1);
    	 System.out.println(by.toString());
    	 by.setLogin("newUser");
    	 userService.update(12, by);
    	 System.out.println(by.toString());
    	 List <UserDTO> l =new LinkedList<>();
    	 l =userService.getAllUsersDTO();
    	 int i=0;
    	 for (UserDTO u: l){
    		 i++;
    		 System.out.println(i+ "." + u.toString()); 
    	 }
    	 ProductGroupDTO pg = new	ProductGroupDTO();
    	 pg.setTitle("Group2");
    	 pg.setDescription("descr2");
    	 pgService.create(pg);
    	 //pg.setId(1);
    	// pg = pgService.findByID(2);
    	 pg = pgService.findByName("Group2");
    	 pg.setTitle("Group5");
    	 pg.setDescription("descr5");
    	
    //	 pgService.delete(2);
    	 pgService.update(1, pg);
    	 pg = pgService.findByID(1);
    	 System.out.println(pg.getTitle()+pg.getDescription());
    	 List <ProductGroupDTO> pglist =new LinkedList<>();
    	 pglist = pgService.getAllProductDTO();
    	 for (ProductGroupDTO u: pglist){
    		 i++;
    		 System.out.println(i+ "." + u.getId()); 
    	 }
    	 
    	 ProductDTO proD = new ProductDTO();
    	 proD.setTitle("title3");
    	 proD.setDescription("desc33");
    	 proD.setPrice(21.0);
    	 proD.setCount(7);
    	  pService.create(proD);
    	 proD = pService.findByID(2);
    	 System.out.println("1111"+ proD.getTitle()+proD.getDescription());
    	 proD = pService.findByName("title3");
    	 System.out.println("1111"+ proD.getTitle()+proD.getDescription());
    	 pService.addGroupToProduct(1, proD);
    //	 pService.addGroupToProduct(2, proD);
    	 pglist =pService.getAllProductGroupById(proD.getId());
    	 for (ProductGroupDTO u: pglist){
    		 i++;
    		 System.out.println(i+ "." + u); 
    	 }
    	 
   // 	 pService.delete(2);
//       11
	   
    }

}
