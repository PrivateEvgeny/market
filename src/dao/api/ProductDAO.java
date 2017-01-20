package dao.api;

import entity.Product;
import entity.ProductGroup;

import java.util.List;

/**
 * Created by anton.kovalenko on 10/10/16.
 */
public interface ProductDAO {

    Product create(Product product);

    Product update(Product product);

    boolean delete(Integer id);

    List<ProductGroup> findProductGroups(Integer id);
    
    
    
    Product findByName(String name);
    
    List<Product>getProductList();
    
    Integer numberProducts ();

	Product findById(int i);
}
