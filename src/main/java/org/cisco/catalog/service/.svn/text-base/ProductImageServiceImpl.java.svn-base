package org.cisco.catalog.service;

import java.util.List;
import org.cisco.catalog.dao.ProductImageDao;
import org.cisco.catalog.domain.Image;
import org.cisco.catalog.domain.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductImageServiceImpl implements ProductImageService {

	@Autowired
	ProductImageDao productImageDao;

	@Autowired
	ImageService imageService;

	public long countAllIProductImages() {
		return productImageDao.count();
	}

	public void deleteProductImage(ProductImage productImage) {
		// delete image
		if (productImage.getImage() != null) {
			imageService.deleteImage(productImage.getImage());
		}
		productImageDao.delete(productImage);
	}

	public ProductImage findProductImage(Integer id) {
		return productImageDao.findOne(id);
	}

	public List<ProductImage> findAllProductImages() {
		return productImageDao.findAll();
	}

	public void saveProductImage(ProductImage productImage) {
		Integer displayOrder = productImageDao.findMaxDisplayOrder(productImage
				.getProductId());
		if (displayOrder != null) {
			productImage.setDisplayOrder(displayOrder + 1);
		}
		productImageDao.save(productImage);
	}

	public ProductImage updateProductImage(ProductImage productImage) {
		return productImageDao.update(productImage);
	}

	public ProductImage findByImage(Image image) {
		return productImageDao.findByImage(image);
	}
}
