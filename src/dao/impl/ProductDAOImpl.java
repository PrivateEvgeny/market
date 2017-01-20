package dao.impl;

import entity.Product;
import entity.ProductGroup;

import dao.api.ProductDAO;
import dto.ProductDTO;

import java.util.LinkedList;
import java.util.List;

import DataSource.InMemoryDatabase;
import DataSource.InterfaceInMemory;
import DataSource.MySQL;

/**
 * Created by anton.kovalenko on 10/10/16.
 */
public class ProductDAOImpl implements ProductDAO {
static InterfaceInMemory inmem = new MySQL();
    @Override
    public Product create(Product product) {
    	inmem.createProduct(product);
    	return product;
    }

    @Override
    public Product update(Product product) {
    	return inmem.updateProduct(product);
    }

    @Override
    public boolean delete(Integer id) {
        return inmem.deleteProduct(id);
    }

    @Override
    public List<ProductGroup> findProductGroups(Integer id) {
        List<ProductGroup> groups = new LinkedList<>();
        ProductGroup productGroup = new ProductGroup();
        productGroup.setTitle("Group mock");
        groups.add(productGroup);

        return groups;
    }

	@Override
	public  Product findById(int i) {
		// TODO Auto-generated method stub
		return inmem.findProductById(i);
	}

	
	@Override
	public Product findByName(String name) {
		// TODO Auto-generated method stub
		return inmem.findProductByName(name);
	}

	@Override
	public List<Product> getProductList() {
		List<Product> list = inmem.findAllProducts();
		return list ;
	}

	@Override
	public Integer numberProducts() {
		// TODO Auto-generated method stub
		return 3;
	}

	public boolean addGroupToProduct(ProductGroup newProGroup, Product newPro) {
		
		return inmem.addGroupToProduct(newProGroup, newPro);
	}

	public List<Integer> getAllIdGroupinProduct(int id) {
		List<Integer> listId = new LinkedList<>();
		listId = inmem.getAllIdGroupinProduct(id);
		return listId;
	}

	public void deleteGroupDTOfromProduct(Integer id, int id2) {
		inmem.deleteGroupDTOfromProduct(id, id2);
		
	}

	public List<Product> getAllProductInGroup(Integer id) {
		// TODO Auto-generated method stub
		return inmem.GetAllProductInGroup(id);
	}
}
