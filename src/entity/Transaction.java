package entity;

import java.util.Date;

/**
 * Created by anton.kovalenko on 10/10/16.
 */
public class Transaction {
    private Integer id;
    private Product product;
    private Integer productCount;
    private Double productPrice;
    private Date date;
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
