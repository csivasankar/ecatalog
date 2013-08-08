package org.cisco.catalog.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.cisco.catalog.dao.CategoryDao;
import org.cisco.catalog.domain.Category;
import org.cisco.catalog.domain.Product;
import org.cisco.catalog.domain.Promotion;
import org.cisco.catalog.util.DateUtil;
import org.cisco.catalog.util.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;

	@Autowired
	ProductService productService;

	@Autowired
	PromotionService promotionService;

	@Autowired
	ImageService imageService;

	@Autowired
	UploadService uploadService;

	public long countAllCategories() {
		return categoryDao.count();
	}

	public void deleteCategory(Category category) {
		// reset associated products
		List<Product> products = productService
				.findProductsByCategory(category);
		for (Product p : products) {
			p.setCategory(null);
			productService.updateProduct(p);
		}
		// reset associated promotions
		List<Promotion> promotions = promotionService
				.findPromotionsByCategory(category);
		for (Promotion p : promotions) {
			p.setCategory(null);
			promotionService.updatePromotion(p);
		}
		// delete category image
		if (category.getImage() != null) {
			imageService.deleteImage(category.getImage());
			category.setImage(null);
		}
		// delete category file
		if (category.getFile() != null) {
			uploadService.deleteUpload(category.getFile());
			category.setFile(null);
		}

		category.setState(false);
		updateCategory(category);
	}

	public Category findCategory(Integer id) {
		return categoryDao.findOne(id);
	}

	public List<Category> findAllCategories() {
		return categoryDao.findAll(sortBySortOrderAsc());
	}

	public void saveCategory(Category category) {
		Integer displayOrder = categoryDao.findMaxSortOrder();
		if (displayOrder != null) {
			category.setSortOrder(displayOrder + 1);
		}
		category.setState(true);
		category.setEnable(true);
		populateModifiedDate(category);
		populateIsPromo(category);
		categoryDao.save(category);
	}

	public Category updateCategory(Category category) {
		populateModifiedDate(category);
		populateIsPromo(category);
		return categoryDao.update(category);
	}

	private void populateModifiedDate(Category category) {
		category.setModified(DateUtil.getPSTDate());
	}

	public List<Category> findAllByState(boolean state) {
		return categoryDao.findAllByState(state, sortBySortOrderAsc());
	}

	public long countAllByState(boolean state) {
		return categoryDao.countAllByState(state);
	}

	private Sort sortBySortOrderAsc() {
		return new Sort(Sort.Direction.ASC, "sortOrder");
	}

	public List<Category> findAllCategoriesByModifiedDate(Calendar dateTime) {
		return categoryDao.findByModifiedAfter(dateTime, sortBySortOrderAsc());
	}

	private void populateIsPromo(Category category) {
		if (category.getName().equalsIgnoreCase("Promotions")) {
			category.setPromo(true);
		} else {
			category.setPromo(false);
		}
	}

	public Category findByName(String name) {
		return categoryDao.findByNameAndState(name, true);
	}

	public List<Category> findFilteredCategories() {
		List<Category> filteredList = new ArrayList<Category>();
		for (Category category : findAllByState(true)) {
			if (category.getName().equalsIgnoreCase("Workplace")
					|| category.getName().equalsIgnoreCase("Promotions")
					|| category.getName().equalsIgnoreCase("Cartoons")) {
				// skip
			} else {
				filteredList.add(category);
			}
		}
		return filteredList;
	}
}
