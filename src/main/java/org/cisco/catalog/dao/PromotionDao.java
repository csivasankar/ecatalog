package org.cisco.catalog.dao;

import java.util.List;

import org.cisco.catalog.domain.Category;
import org.cisco.catalog.domain.Promotion;

public interface PromotionDao {

	List<Promotion> findByCategory(Category category);

	long count();

	void delete(Promotion promotion);

	Promotion findOne(Integer id);

	List<Promotion> findAll();

	Promotion save(Promotion promotion);

	Promotion update(Promotion promotion);

}
