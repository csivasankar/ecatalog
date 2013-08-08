package org.cisco.catalog.service;

import java.util.Calendar;
import java.util.List;
import org.cisco.catalog.domain.Category;

public interface CategoryService {

	public abstract long countAllCategories();

	public abstract void deleteCategory(Category categories);

	public abstract Category findCategory(Integer id);

	public abstract List<Category> findAllCategories();
	
	public abstract List<Category> findFilteredCategories();

	public abstract List<Category> findAllByState(boolean state);

	public abstract void saveCategory(Category categories);

	public abstract Category updateCategory(Category categories);

	public abstract long countAllByState(boolean state);

	public abstract List<Category> findAllCategoriesByModifiedDate(
			Calendar dateTime);

	public abstract Category findByName(String name);
}
