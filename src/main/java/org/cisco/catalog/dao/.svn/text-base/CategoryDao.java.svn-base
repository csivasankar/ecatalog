package org.cisco.catalog.dao;

import java.util.Calendar;
import java.util.List;

import org.cisco.catalog.domain.Category;
import org.cisco.catalog.util.Sort;

public interface CategoryDao {

	List<Category> findAllByState(boolean state, Sort sort);

	Integer findMaxSortOrder();

	long countAllByState(boolean state);

	List<Category> findByModifiedAfter(Calendar dateTime, Sort sort);

	Category findByNameAndState(String name, boolean state);

	long count();

	Category findOne(Integer id);

	Category save(Category category);

	List<Category> findAll(Sort sort);

	Category update(Category category);
}
