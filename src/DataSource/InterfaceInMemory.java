package DataSource;

import java.util.List;

import dto.ProductDTO;
import entity.Product;
import entity.ProductGroup;
import entity.Transaction;
import entity.User;

public interface InterfaceInMemory {

	User FindUserByLoginAndPassword(String login, String password);
	List<Product> findAllProducts();
	boolean updateUser(User user);

	User addUser(User user);

	boolean deleteUser(Integer id);

	User findById(int id);

	List<User> getAllUsers();

	ProductGroup addProductGroup(ProductGroup productGroup);
	
	boolean deleteProductGroup(int id);
	
	ProductGroup findProductGroupbyId(int id);
	
	ProductGroup findProductGroupbyName(String str);
	
	List<ProductGroup> getAllProductGroups();
	

	void putProductToProductGroup(ProductGroup productGroup, int counterProductGroups);
	
	boolean updateGroup(int i, ProductGroup productGroup);
	
	boolean createProduct(Product product);
	
	boolean buyProduct(Product product);

	boolean updateGroup(ProductGroup productGroup);

	Product findProductById(int i);

	Product findProductByName(String name);

	boolean addGroupToProduct(ProductGroup newProGroup, Product newPro);

	List<Integer> getAllIdGroupinProduct(int id);

	boolean deleteProduct(Integer id);
	Product updateProduct(Product product);
	Product updateProduct();
	boolean deleteGroupDTOfromProduct(Integer id, int id2);
	List<Product> GetAllProductInGroup(Integer id);
	List<Transaction> getAllTransaction();
	List<Transaction> getTransactionByUser(int id);
	boolean addTransaction(Transaction transaction);
	
	

}