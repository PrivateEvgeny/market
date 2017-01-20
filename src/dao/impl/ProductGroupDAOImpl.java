package dao.impl;

import dao.api.ProductGroupDAO;
import entity.Product;
import entity.ProductGroup;

import java.util.LinkedList;
import java.util.List;

import DataSource.InMemoryDatabase;
import DataSource.InterfaceInMemory;
import DataSource.MySQL;

/**
 * Created by anton.kovalenko on 10/10/16.
 */
public class ProductGroupDAOImpl implements ProductGroupDAO {
static InterfaceInMemory inmem = new MySQL();
	public ProductGroup findById(Integer id) {
		ProductGroup pg = null;
		pg = inmem.findProductGroupbyId(id);
		return pg;
	}

	public ProductGroup findByName(String name) {
		ProductGroup pg = null;
		pg = inmem.findProductGroupbyName(name);
		return pg;
	}

	@Override
	public  ProductGroup create(ProductGroup productGroup) {
		inmem.addProductGroup(productGroup);
		return productGroup;
	}

	@Override
	public ProductGroup update(int i, ProductGroup productGroup) {
		inmem.updateGroup(i, productGroup);
		
		return productGroup;
	}

	@Override
	public boolean delete(Integer id) {
		inmem.deleteProductGroup(id);
		return true;
	}

	@Override
	public List<Product> findProducts(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ProductGroup> getAllPGDTO() {
		
		return inmem.getAllProductGroups();
	}

	@Override
	public ProductGroup update(ProductGroup productGroup) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
