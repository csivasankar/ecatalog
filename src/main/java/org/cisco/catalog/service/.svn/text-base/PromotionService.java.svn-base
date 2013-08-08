package org.cisco.catalog.service;

import java.util.List;

import org.cisco.catalog.domain.Category;
import org.cisco.catalog.domain.Promotion;

public interface PromotionService {

	public abstract long countAllPromotionses();

	public abstract void deletePromotion(Promotion promotions);

	public abstract Promotion findPromotion(Integer id);

	public abstract List<Promotion> findAllPromotions();

	public abstract Promotion savePromotion(Promotion promotions);

	public abstract Promotion updatePromotion(Promotion promotions);

	public abstract List<Promotion> findAllPromotionsByProductId(
			Integer productId);

	public abstract List<Promotion> findPromotionsByCategory(Category category);

}
