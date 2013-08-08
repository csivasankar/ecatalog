package org.cisco.catalog.service;

import java.util.Calendar;
import java.util.List;

import org.cisco.catalog.dao.ProductDao;
import org.cisco.catalog.domain.Category;
import org.cisco.catalog.domain.Product;
import org.cisco.catalog.domain.ProductImage;
import org.cisco.catalog.domain.ProductPromotion;
import org.cisco.catalog.domain.Tag;
import org.cisco.catalog.util.DateUtil;
import org.cisco.catalog.util.Sort;
import org.cisco.catalog.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Autowired
	ProductPromotionService productPromotionService;

	@Autowired
	ProductImageService productImageService;

	@Autowired
	UploadService uploadService;

	public long countAllProducts() {
		return productDao.count();
	}
	
	public long countProductsByCategory(Category category) {
		return productDao.countByCategory(category);
	}

	public void deleteProduct(Product product) {
		// delete associated product promotions
		List<ProductPromotion> ppList = productPromotionService
				.findByProductId(product.getId());
		for (ProductPromotion pp : ppList) {
			productPromotionService.deleteProductPromotion(pp);
		}
		// delete associated product images
		for (ProductImage pi : product.getImages()) {
			productImageService.deleteProductImage(pi);
		}
		// delete associated product file
		if (product.getFile() != null) {
			uploadService.deleteUpload(product.getFile());
			product.setFile(null);
		}
		product.setState(false);
		updateProduct(product);
	}

	public Product findProduct(Integer id) {
		return productDao.findOne(id);
	}

	public List<Product> findAllProducts() {
		return productDao.findAll();
	}

	public void saveProduct(Product product) {
		Integer displayOrder = productDao.findMaxSortOrder(true, product.getCategory());
		if (displayOrder != null) {
			product.setSortOrder(displayOrder + 1);
		}
		product.setState(true);
		populateModifiedDate(product);
		product.setCreated(product.getModified());
		cleanEmptyParagraphs(product);
		productDao.save(product);
	}

	public Product updateProduct(Product product) {
		Product dbProduct = findProduct(product.getId());
		if(dbProduct.getCategoryId() != null) {
			if (!dbProduct.getCategoryId().equals(product.getCategoryId())) {
				Integer displayOrder = productDao.findMaxSortOrder(true, product.getCategory());
				if (displayOrder != null) {
					product.setSortOrder(displayOrder + 1);
				}
			}
		}
		product.setImages(dbProduct.getImages());
		populateModifiedDate(product);
		cleanEmptyParagraphs(product);
		return productDao.update(product);
	}

	public List<Product> findProductsByCategory(Category category) {
		return productDao.findByCategoryAndState(category, true);
	}

	public List<Product> findProductsByCategory(Category category, Sort sort, int firstRes, int maxRes) {
		return productDao.findByCategoryAndState(category, true, sort, firstRes, maxRes);
	}

	public List<Product> findAllByState(boolean state, Sort sort, int firstRes, int maxRes) {
		return productDao.findAllByState(state, sort, firstRes, maxRes);
	}

	private void populateModifiedDate(Product product) {
		product.setModified(DateUtil.getPSTDate());
	}

	public void cleanEmptyParagraphs(Product product) {
		product.setDescription(StringUtil.cleanEmptyParagraphs(product
				.getDescription()));
		product.setAtAGlance(StringUtil.cleanEmptyParagraphs(product
				.getAtAGlance()));
		product.setOptionsAvailable(StringUtil.cleanEmptyParagraphs(product
				.getOptionsAvailable()));
		product.setOtherInfo(StringUtil.cleanEmptyParagraphs(product
				.getOtherInfo()));
	}

	public List<Product> findAllProductsByModifiedDate(Calendar dateTime) {
		return productDao.findByModifiedAfter(dateTime);
	}

	public List<Product> findAllProductsByTag(Tag tag) {
		return productDao.findByTag(tag);
	}
	
	public List<Product> findAllByCategoryAndSortOrder(boolean state, Category category) {
		return productDao.findAllByCategoryAndSortOrder(state, sortBySortOrderAsc(), category);
	}
	
	private Sort sortBySortOrderAsc() {
		return new Sort(Sort.Direction.ASC, "sortOrder");
	}
	
	public Integer findMaxSortOrder(boolean state, Category category) {
		return productDao.findMaxSortOrder(state, category);
	}
}
