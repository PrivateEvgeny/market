package dao.api;

import entity.Product;
import entity.ProductGroup;

import java.util.List;

/**
 * Created by anton.kovalenko on 10/10/16.
 */
public interface ProductGroupDAO {

    ProductGroup create(ProductGroup productGroup);

    ProductGroup update(ProductGroup productGroup);

    boolean delete(Integer id);

    List<Product> findProducts(Integer id);

	ProductGroup update(int i, ProductGroup productGroup);

}
