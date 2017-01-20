package dto;

import java.util.List;

import entity.Product;
import entity.ProductGroup;

public class ProductGroupDTO {
	
	private Integer id;
    private String title;
    private String description;
    private List<ProductDTO> products;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<ProductDTO> getProducts() {
		return products;
	}
	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}
	@Override
	public String toString(){
		String s = this.title + " id=" + this.description;
		return s;
	}
    
    
}
