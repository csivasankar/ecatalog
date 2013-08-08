package org.cisco.catalog.dao;

import java.util.List;

import org.cisco.catalog.domain.Image;
import org.cisco.catalog.domain.ProductImage;

public interface ProductImageDao {

	ProductImage findByImage(Image img);

	Integer findMaxDisplayOrder(Integer productId);

	long count();

	void delete(ProductImage productImage);

	ProductImage findOne(Integer id);

	List<ProductImage> findAll();

	ProductImage save(ProductImage productImage);

	ProductImage update(ProductImage productImage);
}
