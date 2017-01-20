package service.api;

import java.util.List;

import dto.ProductDTO;
import dto.ProductGroupDTO;

public interface ProductGroupService {
	    ProductGroupDTO findByID(Integer Id);
 
	    ProductGroupDTO findByName(String name);

	    ProductGroupDTO create(ProductGroupDTO product);

	    boolean delete(Integer id);

		List<ProductGroupDTO> getAllProductDTO();
		
		List<ProductGroupDTO> getAllProductGroipDTObyProduct(ProductDTO productDTO);
		
		Integer getNumberProduct();

		boolean update(int i, ProductGroupDTO pg);
}
