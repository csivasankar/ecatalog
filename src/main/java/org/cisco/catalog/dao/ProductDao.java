package org.cisco.catalog.dao;

import java.util.Calendar;
import java.util.List;

import org.cisco.catalog.domain.Category;
import org.cisco.catalog.domain.Product;
import org.cisco.catalog.domain.Tag;
import org.cisco.catalog.util.Sort;

public interface ProductDao {

	List<Product> findAllByState(boolean state);

	List<Product> findByCategoryAndState(Category category, boolean state);

	List<Product> findByModifiedAfter(Calendar dateTime);

	List<Product> findByTag(Tag tag);

	List<Product> findByCategoryAndState(Category category, boolean state,
			Sort sort, int firstRes, int maxRes);

	long count();
	
	long countByCategory(Category category);

	Product findOne(Integer id);

	List<Product> findAll();

	Product save(Product product);

	List<Product> findAllByState(boolean state, Sort sort, int firstRes, int maxRes);

	Product update(Product product);
	
	List<Product> findAllByCategoryAndSortOrder(boolean state, Sort sort, Category category);
	
	
	Integer findMaxSortOrder(boolean state, Category category);

}
