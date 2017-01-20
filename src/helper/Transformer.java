package helper;

import dto.ProductDTO;
import dto.ProductGroupDTO;
import dto.TransactionDTO;
import dto.UserDTO;
import entity.Product;
import entity.ProductGroup;
import entity.User;
import entity.Transaction;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by anton.kovalenko on 10/10/16.
 */
public class Transformer {
    /*Dozer*/

    public static UserDTO transformUserToUserDTO(User user) {
        UserDTO u = new UserDTO();
        u.setId(user.getId());
        u.setLogin(user.getLogin());
        u.setPassword(user.getPassword());
        u.setBirthday(user.getBirthday());
        u.setEmail(user.getEmail());
        u.setFirstName(user.getFirstName());
        u.setRole(user.getRole());
        u.setSecondName(user.getSecondName());
        u.setSex(user.getSex());
        System.out.println("transforming:" + u.toString());
        return u;
    }

    public static User transformUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setBirthday(userDTO.getBirthday());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setRole(userDTO.getRole());
        user.setSecondName(userDTO.getSecondName());
        user.setSex(userDTO.getSex());
        return user;
    }

    public static List<User> transformListUserDTOToListUser(List<UserDTO> userDTOs) {
        List<User> users = new LinkedList<>();

        for (UserDTO userDTO : userDTOs) {
            User user = transformUserDTOToUser(userDTO);
            users.add(user);
        }
        return users;
    }
    
    public  static ProductDTO transformProductToProductDTP (Product pro){
		ProductDTO productDTO = new ProductDTO();
    	productDTO.setCount(pro.getCount());
    	productDTO.setDescription(pro.getDescription());
       	productDTO.setPrice(pro.getPrice());
    	productDTO.setProductGroups(pro.getProductGroups());
    	productDTO.setTitle(pro.getTitle());
    	productDTO.setId(pro.getId());
    	return productDTO;
    	
    }
    
    public static Product transformProductDTOToProduct(ProductDTO productDTO){
		Product pro = new Product();
		pro.setCount(productDTO.getCount());
		pro.setId(productDTO.getId());
		pro.setDescription(productDTO.getDescription());
		pro.setPrice(productDTO.getPrice());
		pro.setProductGroups(productDTO.getProductGroups());
		pro.setTitle(productDTO.getTitle());
    	return pro;
    }

	public static ProductGroupDTO transformGroupToGroupDT0(ProductGroup pro) {
		// 
		
		ProductGroupDTO prod = new ProductGroupDTO();
		prod.setDescription(pro.getDescription());
		prod.setId(pro.getId());
		prod.setTitle(pro.getTitle());
		
		
		List<Product> p = pro.getProducts();
		List <ProductDTO> pNew = new LinkedList<>();
		if (p!= null){
		 for (Product pro1 : p) {
	           ProductDTO proD = Transformer.transformProductToProductDTP(pro1);
	            pNew.add(proD);
	        }
		
		 prod.setProducts(pNew);
		}
		 return prod;
	}

	public static List<UserDTO> ListUserToUSERDTO(List<User> userOld) {
		List<UserDTO> users = new LinkedList<>();

        for (User user : userOld) {
            UserDTO u = Transformer.transformUserToUserDTO(user);
            users.add(u);
        }
        return users;
	}

	public static ProductGroup transformGroupDToToGroup(ProductGroupDTO pro) {
		ProductGroup prod = new ProductGroup();
		
		
		prod.setDescription(pro.getDescription());
		prod.setId(pro.getId());
		prod.setTitle(pro.getTitle());
		
		List <Product> p = new LinkedList<>();
		List <ProductDTO> pNew = pro.getProducts();
		if (pNew!= null){
		 for (ProductDTO pro1 : pNew) {
	           Product proD = Transformer.transformProductDTOToProduct(pro1);
	            p.add(proD);
	        }
		}
		 prod.setProducts(p);
		return prod;
	}

	public static List<ProductGroupDTO> TransformListPgtoPGDTO(List<ProductGroup> lPG) {
		List<ProductGroupDTO> listPGDTO = new LinkedList<>();
		ProductGroupDTO pDTO = null;
		if (lPG!=null){
			for(ProductGroup pg: lPG){
			 pDTO = Transformer.transformGroupToGroupDT0(pg);
			 listPGDTO.add(pDTO);
			
			}
		}
		
		return listPGDTO;
	}

	public static Transaction transformTRDTOtoTransaction(TransactionDTO transactionOLD) {
		Transaction transactionNew = new Transaction();
		transactionNew.setDate(transactionOLD.getDate());
		transactionNew.setProduct(Transformer.transformProductDTOToProduct(transactionOLD.getProduct()));
		transactionNew.setProductCount(transactionOLD.getProductCount());
		transactionNew.setProductPrice(transactionOLD.getProductPrice());
		transactionNew.setUser(Transformer.transformUserDTOToUser(transactionOLD.getUser()));
		return transactionNew;
	}
	public static TransactionDTO transformTRtoTransactionDTO(Transaction transactionOLD) {
		TransactionDTO transactionNew = new TransactionDTO();
		transactionNew.setDate(transactionOLD.getDate());
		transactionNew.setProduct(Transformer.transformProductToProductDTP(transactionOLD.getProduct()));
		transactionNew.setProductCount(transactionOLD.getProductCount());
		transactionNew.setProductPrice(transactionOLD.getProductPrice());
		transactionNew.setUser(Transformer.transformUserToUserDTO(transactionOLD.getUser()));
		return transactionNew;
	}
	
	
    
   
    
    
    
    
}
