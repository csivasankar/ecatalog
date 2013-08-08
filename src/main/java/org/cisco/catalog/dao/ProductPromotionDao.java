package org.cisco.catalog.dao;

import org.cisco.catalog.domain.ProductPromotion;
import java.lang.Integer;
import java.util.List;

public interface ProductPromotionDao {

	ProductPromotion findByProductIdAndPromotionId(Integer productId,
			Integer promotionId);

	List<ProductPromotion> findByPromotionId(Integer promotionId);

	List<ProductPromotion> findByProductId(Integer productId);

	long count();

	void delete(ProductPromotion productPromotion);

	ProductPromotion findOne(Integer id);

	List<ProductPromotion> findAll();

	ProductPromotion save(ProductPromotion productPromotion);

	ProductPromotion update(ProductPromotion productPromotion);
}
