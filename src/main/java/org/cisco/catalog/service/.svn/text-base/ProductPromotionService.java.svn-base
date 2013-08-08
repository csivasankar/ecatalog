package org.cisco.catalog.service;

import java.util.List;
import org.cisco.catalog.domain.ProductPromotion;

public interface ProductPromotionService {

	public abstract long countAllProductPromotions();

	public abstract void deleteProductPromotion(
			ProductPromotion productPromotion);

	public abstract ProductPromotion findProductPromotion(Integer id);

	public abstract List<ProductPromotion> findAllProductPromotions();

	public abstract void saveProductPromotion(ProductPromotion productPromotion);

	public abstract ProductPromotion updateProductPromotion(
			ProductPromotion productPromotion);

	public abstract ProductPromotion findByProductIdAndPromotionId(
			Integer productId, Integer promotionId);

	public abstract List<ProductPromotion> findByPromotionId(Integer promotionId);
	
	public abstract List<ProductPromotion> findByProductId(Integer productId);
}
