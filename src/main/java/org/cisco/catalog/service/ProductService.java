package org.cisco.catalog.service;

import java.util.Calendar;
import java.util.List;

import org.cisco.catalog.domain.Category;
import org.cisco.catalog.domain.Product;
import org.cisco.catalog.domain.Tag;
import org.cisco.catalog.util.Sort;

public interface ProductService {

	public abstract long countAllProducts();
	
	public abstract long countProductsByCategory(Category category);

	public abstract void deleteProduct(Product product);

	public abstract Product findProduct(Integer id);

	public abstract List<Product> findAllProducts();

	public abstract void saveProduct(Product product);

	public abstract Product updateProduct(Product product);

	public abstract List<Product> findProductsByCategory(Category category);
	
	public abstract List<Product> findAllByState(boolean state, Sort sort, int firstRes, int maxRes);

	public abstract List<Product> findAllProductsByModifiedDate(Calendar dateTime);
	
	public abstract List<Product> findAllProductsByTag(Tag tag);

	public abstract List<Product> findProductsByCategory(Category category, Sort sort, int firstRes, int maxRes);
	
	public abstract List<Product> findAllByCategoryAndSortOrder(boolean state, Category category);
	
	public abstract Integer findMaxSortOrder(boolean state, Category category);

}
