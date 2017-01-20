package service.impl;

import java.util.LinkedList;
import java.util.List;

import dao.impl.ProductDAOImpl;
import dao.impl.ProductGroupDAOImpl;
import dto.ProductDTO;
import dto.ProductGroupDTO;
import dto.UserDTO;
import entity.Product;
import entity.ProductGroup;
import entity.User;
import helper.Transformer;
import service.api.ProductGroupService;
import service.api.ProductService;

public class ProductGroupServiceImpl implements ProductGroupService {

	private ProductGroupDAOImpl productDao = new ProductGroupDAOImpl();;
	
	
	@Override
	public ProductGroupDTO findByID(Integer Id) {
		ProductGroupDTO result = new ProductGroupDTO();
		ProductGroup pro = productDao.findById(Id);
		result = helper.Transformer.transformGroupToGroupDT0(pro);
		return result;
	}

	@Override
	public ProductGroupDTO findByName(String name) {
		ProductGroupDTO result = new ProductGroupDTO();
		ProductGroup pro = productDao.findByName(name);
		result = helper.Transformer.transformGroupToGroupDT0(pro);
		return result;
	}

	@Override
	public ProductGroupDTO create(ProductGroupDTO product) {
		
		ProductGroup pro = null;
		pro = helper.Transformer.transformGroupDToToGroup(product);
		
		productDao.create(pro);
		System.out.println("creating pg :" + pro.toString());
		return product;
		
	}

	@Override
	public boolean delete(Integer id) {
		boolean res = true;
		ProductGroupDTO result = new ProductGroupDTO();
		result = findByID(id);
		if (result ==null){
			res = false;
		}else{
			productDao.delete(id);
		}
		
		return res;
	}

	@Override
	public List<ProductGroupDTO> getAllProductDTO() {
		List<ProductGroupDTO> l = new LinkedList<>();
		List<ProductGroup> lPG = new LinkedList<>();
		lPG= productDao.getAllPGDTO();
		l = Transformer.TransformListPgtoPGDTO(lPG);
		return l;
		
		
		
	}

	@Override
	public List<ProductGroupDTO> getAllProductGroipDTObyProduct(ProductDTO productDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getNumberProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(int i, ProductGroupDTO pg) {
		boolean result = true;
		 ProductGroupDTO oldProduct = findByID(i);
		 if (oldProduct!=null && pg!=null){
			 oldProduct.setTitle(pg.getTitle());
			 oldProduct.setDescription(pg.getDescription());
			 ProductGroup pgNew = Transformer.transformGroupDToToGroup(oldProduct);
			 productDao.update(i, pgNew);
			 }else{
			 result = false;
		 }
		
		
		
		return result;
	}

	public boolean addGroupToProduct(int id){
		boolean result = true;
		List<ProductGroupDTO> l = new LinkedList<>();
		if (findByID(id)!=null){
			
			
		}else{
			result = false;
		}
		
		
		
		return result;
	}
	

	
}
