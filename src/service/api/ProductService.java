package service.api;

import java.util.List;

import dao.api.ProductDAO;
import dto.ProductDTO;
import dto.ProductGroupDTO;
import entity.ProductGroup;

public interface ProductService {






    ProductDTO findByID(Integer Id);

    ProductDTO findByName(String name);

    ProductDTO create(ProductDTO product);

    boolean delete(Integer id);

    ProductDTO update(ProductDTO product);

	List<ProductDTO> getAllProductDTO();
	
	Integer getNumberProduct();
	
	List<Integer> getAllIdGroupinProductDTO(int id);
	List<Integer> getAllidProductDTOinGroup(int id);
	
	List<ProductDTO> getAllProductDTObyGroup(ProductGroupDTO productGroupDTO);

	boolean addGroupToProduct(int id, ProductDTO pr);

	List<ProductGroupDTO> getAllProductGroupById(int id);

	boolean delGroupFromProduct(Integer id, int id2);

}
