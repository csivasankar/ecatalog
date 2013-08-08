package org.cisco.catalog.service;

import java.util.List;
import org.cisco.catalog.dao.ProductPromotionDao;
import org.cisco.catalog.domain.ProductPromotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductPromotionServiceImpl implements ProductPromotionService {

	@Autowired
	ProductPromotionDao productPromotionDao;

	public long countAllProductPromotions() {
		return productPromotionDao.count();
	}

	public void deleteProductPromotion(ProductPromotion productPromotion) {
		productPromotionDao.delete(productPromotion);
	}

	public ProductPromotion findProductPromotion(Integer id) {
		return productPromotionDao.findOne(id);
	}

	public List<ProductPromotion> findAllProductPromotions() {
		return productPromotionDao.findAll();
	}

	public void saveProductPromotion(ProductPromotion productPromotion) {
		productPromotionDao.save(productPromotion);
	}

	public ProductPromotion updateProductPromotion(
			ProductPromotion productPromotion) {
		return productPromotionDao.update(productPromotion);
	}

	public ProductPromotion findByProductIdAndPromotionId(Integer productId,
			Integer promotionId) {
		return productPromotionDao.findByProductIdAndPromotionId(productId,
				promotionId);
	}

	public List<ProductPromotion> findByPromotionId(Integer promotionId) {
		return productPromotionDao.findByPromotionId(promotionId);
	}

	public List<ProductPromotion> findByProductId(Integer productId) {
		return productPromotionDao.findByProductId(productId);
	}
}
