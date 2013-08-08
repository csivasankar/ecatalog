package org.cisco.catalog.service;

import java.util.ArrayList;
import java.util.List;
import org.cisco.catalog.dao.PromotionDao;
import org.cisco.catalog.domain.Category;
import org.cisco.catalog.domain.ProductPromotion;
import org.cisco.catalog.domain.Promotion;
import org.cisco.catalog.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PromotionServiceImpl implements PromotionService {

	@Autowired
	PromotionDao promotionDao;

	@Autowired
	ProductPromotionService productPromotionService;

	public long countAllPromotionses() {
		return promotionDao.count();
	}

	public void deletePromotion(Promotion promotions) {
		promotionDao.delete(promotions);
	}

	public Promotion findPromotion(Integer id) {
		return promotionDao.findOne(id);
	}

	public List<Promotion> findAllPromotions() {
		return promotionDao.findAll();
	}

	public Promotion savePromotion(Promotion promotion) {
		cleanEmptyParagraphs(promotion);
		return promotionDao.save(promotion);
	}

	public Promotion updatePromotion(Promotion promotion) {
		cleanEmptyParagraphs(promotion);
		return promotionDao.update(promotion);
	}

	public void cleanEmptyParagraphs(Promotion promotion) {
		promotion.setOverview(StringUtil.cleanEmptyParagraphs(promotion
				.getOverview()));
	}

	public List<Promotion> findAllPromotionsByProductId(Integer productId) {
		List<Promotion> promotions = new ArrayList<Promotion>();
		for (ProductPromotion pp : productPromotionService
				.findByProductId(productId)) {
			promotions.add(promotionDao.findOne(pp.getPromotionId()));
		}
		return promotions;
	}

	public List<Promotion> findPromotionsByCategory(Category category) {
		return promotionDao.findByCategory(category);
	}
}
