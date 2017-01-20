package dto;

import java.util.Date;

import entity.Product;
import entity.User;

public class TransactionDTO {
	 private Integer id;
	    private ProductDTO product;
	    private Integer productCount;
	    private Double productPrice;
	    private Date date;
	    private UserDTO user;

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public ProductDTO getProduct() {
	        return product;
	    }

	    public void setProduct(ProductDTO product) {
	        this.product = product;
	    }

	    public Date getDate() {
	        date = (new java.util.Date());
	    	return date;
	    }

	    public void setDate(Date date) {
	        this.date = date;
	    }

	    public Integer getProductCount() {
	        return productCount;
	    }

	    public void setProductCount(Integer productCount) {
	        this.productCount = productCount;
	    }

	    public Double getProductPrice() {
	        return productPrice;
	    }

	    public void setProductPrice(Double productPrice) {
	        this.productPrice = productPrice;
	    }

		public UserDTO getUser() {
			return user;
		}

		public void setUser(UserDTO userDTO) {
			this.user = userDTO;
		}
	}