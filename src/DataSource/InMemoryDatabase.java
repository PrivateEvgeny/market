package DataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import dto.ProductDTO;
import entity.Product;
import entity.ProductGroup;
import entity.Transaction;
import entity.User;

public class InMemoryDatabase implements InterfaceInMemory {
 private List<User> users = new LinkedList<>();
// private Set<Table> PrPrG = new TreeSet<>();
 private Table table = new Table();
 private HashMap<Integer, ProductGroup> productGroups = new HashMap<>();
 private HashMap<Integer, Product> products = new HashMap<>();
 //
 private  HashMap<Integer, Integer>  tableProductProductGroup = new HashMap<>();
 private static int counter= 0; 
 private static int counterProductGroups = 0;
 /* (non-Javadoc)
 * @see DataSource.InterfaceInMemory#FindUserByLoginAndPassword(java.lang.String, java.lang.String)
 */
@Override
public User FindUserByLoginAndPassword(String login, String password){
     User userResult = null;	
     
     for (User user :users){
    	 if (user.getLogin().equals(login) && user.getPassword().equals(password)){
    		userResult = user; 
    	 }
     }
	return userResult;
  }
 
 /* (non-Javadoc)
 * @see DataSource.InterfaceInMemory#addUser(entity.User)
 */
@Override
public synchronized User addUser(User user){
	 User userResult = null;
	 User unicUser = FindUserByLoginAndPassword(user.getLogin(), user.getPassword());
	 if (unicUser==null){
		 user.setId(counter+1);
		 users.add(user);
		
		 userResult = user;
		 System.out.println("adding inmemory: "+ userResult.getLogin()+ ";"+ userResult.getPassword());
		 counter++;
	 }
	 userResult = user;
	
	 return userResult;
 }

/* (non-Javadoc)
 * @see DataSource.InterfaceInMemory#deleteUser(java.lang.Integer)
 */
@Override
public synchronized boolean deleteUser(Integer id) {
	User userResult = null;
	Boolean result =false;
	User unicUser = FindUserById(id);
	if (unicUser!=null){
		users.remove(unicUser);
		System.out.println("deleted:" + unicUser.toString());
	}
	
	return result;
}

private User FindUserById(Integer id) {
	User userResult = null;
	
			for (User user :users){
		    	 if (user.getId().equals(id)){
		    		userResult = user; 
		    	 }
		     }
	
	return userResult;
}

/* (non-Javadoc)
 * @see DataSource.InterfaceInMemory#findById(int)
 */
@Override
public User findById(int id) {
 User userResult = null;	
     
     for (User user :users){
    	 if (user.getId().equals(id)) {
    		userResult = user; 
    	 }
     }
	return userResult;
}

/* (non-Javadoc)
 * @see DataSource.InterfaceInMemory#getAllUsers()
 */
@Override
public List<User> getAllUsers() {
	// TODO Auto-generated method stub
	return users;
}

/* (non-Javadoc)
 * @see DataSource.InterfaceInMemory#createNewProductGroup(entity.ProductGroup)
 */

public ProductGroup addProductGroup(ProductGroup productGroup) {
	boolean result = false;
	if (ProductGroupExists(productGroup) == false){
		result = true;
		counterProductGroups++;
		productGroup.setId(counterProductGroups);
		productGroups.put(counterProductGroups, productGroup);
		
		putProductToProductGroup(productGroup, counterProductGroups);
	
	} 
	return productGroup;
}

/* (non-Javadoc)
 * @see DataSource.InterfaceInMemory#putProductToProductGroup(entity.ProductGroup, int)
 */
@Override
public void putProductToProductGroup(ProductGroup productGroup, int counterProductGroups) {
	// продукт группа-продукт
	List<ProductGroup> newlist = new LinkedList<>();;
	List<Product> list = productGroup.getProducts();
	for (Product pr: list){
		String str = Integer.toString(counterProductGroups)+"-" + pr.getId();
	//	PrPrG.add(str);
	}
}

private boolean ProductGroupExists(ProductGroup pg) {
	boolean result = false;
	
	//result= productGroups.containsKey(pg.getId())+"-";
	return result;
}

@Override
public boolean updateUser(User user) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean updateGroup(ProductGroup productGroup) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean createProduct(Product product) {
	boolean result= true;

	 
	

	

	return result;
}

@Override
public boolean buyProduct(Product product) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean deleteProductGroup(int id) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public ProductGroup findProductGroupbyId(int id) {
	// TODO Auto-generated method stub
	return null;
}



@Override
public List<ProductGroup> getAllProductGroups() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public ProductGroup findProductGroupbyName(String str) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public boolean updateGroup(int i, ProductGroup productGroup) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public Product findProductById(int i) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Product findProductByName(String name) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public boolean addGroupToProduct(ProductGroup newProGroup, Product newPro) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public List<Integer> getAllIdGroupinProduct(int id) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public boolean deleteProduct(Integer id) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public List<Product> findAllProducts() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Product updateProduct() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Product updateProduct(Product product) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public boolean deleteGroupDTOfromProduct(Integer id, int id2) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public List<Product> GetAllProductInGroup(Integer id) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<Transaction> getAllTransaction() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<Transaction> getTransactionByUser(int id) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public boolean addTransaction(Transaction transaction) {
	// TODO Auto-generated method stub
	return false;
}


 
 
 
 
}
