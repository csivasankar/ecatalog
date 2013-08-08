package org.cisco.catalog.domain.json;

import java.util.ArrayList;
import java.util.List;

import org.cisco.catalog.domain.Cartoon;
import org.cisco.catalog.domain.Category;
import org.cisco.catalog.domain.Product;
import org.codehaus.jackson.annotate.JsonProperty;

public class ProductDto extends BaseDto {
	@JsonProperty("Products")
	private List<Product> products = new ArrayList<Product>();
	@JsonProperty("Categories")
	private List<Category> categories = new ArrayList<Category>();
	@JsonProperty("Cartoons")
	private List<Cartoon> cartoons = new ArrayList<Cartoon>();

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Cartoon> getCartoons() {
		return cartoons;
	}

	public void setCartoons(List<Cartoon> cartoons) {
		this.cartoons = cartoons;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
}
