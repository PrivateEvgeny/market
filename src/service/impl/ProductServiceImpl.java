package service.impl;

import dto.ProductDTO;
import dto.ProductGroupDTO;
import entity.Product;
import entity.ProductGroup;
import helper.Transformer;
import service.api.ProductService;

import java.util.LinkedList;
import java.util.List;

import dao.api.ProductDAO;
import dao.impl.*;

public class ProductServiceImpl implements ProductService {
    private ProductGroupServiceImpl proGDTO = new ProductGroupServiceImpl ();
	private ProductDAOImpl productDao = new ProductDAOImpl();
	@Override
	public ProductDTO findByID(Integer Id) {
		// TODO Auto-generated method stub
		ProductDTO result = new ProductDTO();
		Product pro = productDao.findById(Id);
		result = helper.Transformer.transformProductToProductDTP(pro);
		return result;
	}

	@Override
	public ProductDTO findByName(String name) {
		ProductDTO result = new ProductDTO();
		Product pro = productDao.findByName(name);
		result = helper.Transformer.transformProductToProductDTP(pro);
		return result;
	}

	@Override
	public ProductDTO create(ProductDTO productDTO) {
		Product product = new Product();
		product = helper.Transformer.transformProductDTOToProduct(productDTO);
	    productDao.create(product);
		return productDTO;
	}

	@Override
	public boolean delete(Integer id) {
		Boolean res = true;
		Product product = new Product();
		product = productDao.findById(id);
		System.out.println("find before deleting=" + product );
		res= productDao.delete(id);
		
		return res;
	}

	@Override
	public ProductDTO update(ProductDTO productDTO) {
		
		Product product = new Product();
		int id = 0;
	    product = helper.Transformer.transformProductDTOToProduct(productDTO);
	    id = product.getId();
		productDao.update(product);
	    return productDTO;
	}

	@Override
	public List<ProductDTO> getAllProductDTO() {
		List<ProductDTO> list = new LinkedList<>();
		Product product = new Product();
		ProductDTO proD = new ProductDTO();
		for (int i=0; i<productDao.getProductList().size(); i++ ){
			product = productDao.getProductList().get(i);
			 proD = helper.Transformer.transformProductToProductDTP(product);
			 list.add(proD);
		}
		
		return list;
		
	}

	@Override
	public Integer getNumberProduct() {
		// TODO Auto-generated method stub
		int res = productDao.numberProducts();
		return res;
	}

	
	

	@Override
	public List<ProductDTO> getAllProductDTObyGroup(ProductGroupDTO productGroupDTO) {
		List<ProductDTO> list = new LinkedList<>();
		List<Product>list2 = productDao.getAllProductInGroup(productGroupDTO.getId());
		Product product = new Product();
		ProductDTO proD = new ProductDTO();
		for (int i=0; i<list2.size(); i++ ){
			 product = list2.get(i);
			 proD = helper.Transformer.transformProductToProductDTP(product);
			 list.add(proD);
		}
		
		
		
		return list;
	}
	@Override
	public boolean addGroupToProduct(int id, ProductDTO pr){
		boolean result = true;
		List<ProductGroupDTO> l = new LinkedList<>();
		List<Integer> listId = new LinkedList<>();
		ProductGroupDTO proGroupDTO = new ProductGroupDTO();
		if (proGDTO.findByID(id)!=null){
			proGroupDTO =proGDTO.findByID(id);
			ProductGroup newProGroup = Transformer.transformGroupDToToGroup(proGroupDTO);
			Product newPro = Transformer.transformProductDTOToProduct(pr);
			System.out.println(newPro.getId() +" "+ newProGroup.getId() );
			if (pr.getId()!=null){
			 listId = productDao.getAllIdGroupinProduct(pr.getId());
			}
			if (!listId.contains(id)& pr.getId()!=null){
			productDao.addGroupToProduct(newProGroup, newPro);
			}
		}else{
			result = false;
		}
		
		
		
		return result;
	}

	@Override
	public List<Integer> getAllIdGroupinProductDTO(int id) {
		List<Integer> listId = new LinkedList<>();
		listId = productDao.getAllIdGroupinProduct(id);
		return listId;
	}
	
	public List<ProductGroupDTO> getAllProductGroupById(int id){
		List<ProductGroupDTO> result = new LinkedList<>();
		List<Integer> listId = new LinkedList<>();
		listId = getAllIdGroupinProductDTO(id);
		if (listId!=null){
		int x=0;
		int z=0;
			for (int k=0; k<listId.size(); k++ ){
				 z =listId.get(k);
				 ProductGroupDTO pgDTO = proGDTO.findByID(z);
				 result.add(pgDTO);
			}
		}
		return result;
	}

	@Override
	public List<Integer> getAllidProductDTOinGroup(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delGroupFromProduct(Integer id, int id2) {
		productDao.deleteGroupDTOfromProduct( id, id2);
		return false;
	}

	
	
	

}
