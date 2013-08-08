package org.cisco.catalog.service;

import java.util.Calendar;
import java.util.List;

import org.cisco.catalog.dao.CartoonDao;
import org.cisco.catalog.domain.Cartoon;
import org.cisco.catalog.domain.Image;
import org.cisco.catalog.util.DateUtil;
import org.cisco.catalog.util.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartoonServiceImpl implements CartoonService {

	@Autowired
	CartoonDao cartoonDao;

	@Autowired
	ImageService imageService;

	public long countAllCartoons() {
		return cartoonDao.count();
	}

	public void deleteCartoon(Cartoon cartoon) {
		// delete cartoon images
		if (cartoon.getImage() != null) {
			imageService.deleteImage(cartoon.getImage());
			cartoon.setImage(null);
		}
		if (cartoon.getIcon() != null) {
			imageService.deleteImage(cartoon.getIcon());
			cartoon.setIcon(null);
		}
		cartoon.setState(false);
		updateCartoon(cartoon);
	}

	public Cartoon findCartoon(Integer id) {
		return cartoonDao.findOne(id);
	}

	public List<Cartoon> findAllCartoons() {
		return cartoonDao.findAll(sortBySortOrderAsc());
	}

	public void saveCartoon(Cartoon cartoon) {
		Integer displayOrder = cartoonDao.findMaxSortOrder();
		if (displayOrder != null) {
			cartoon.setSortOrder(displayOrder + 1);
		}
		cartoon.setState(true);
		populateModifiedDate(cartoon);
		cartoonDao.save(cartoon);
	}

	public Cartoon updateCartoon(Cartoon cartoon) {
		populateModifiedDate(cartoon);
		return cartoonDao.update(cartoon);
	}

	private void populateModifiedDate(Cartoon cartoon) {
		cartoon.setModified(DateUtil.getPSTDate());
	}

	private Sort sortBySortOrderAsc() {
		return new Sort(Sort.Direction.ASC, "sortOrder");
	}

	public List<Cartoon> findAllByState(boolean state) {
		return cartoonDao.findAllByState(state, sortBySortOrderAsc());
	}

	public List<Cartoon> findAllCartoonsByModifiedDate(Calendar dateTime) {
		return cartoonDao.findByModifiedAfter(dateTime, sortBySortOrderAsc());
	}

	public Cartoon findByImageId(Image image, String type) {
		Cartoon c = null;
		if (type.equals("icon")) {
			c = cartoonDao.findByIcon(image);
		} else if (type.equals("image")) {
			c = cartoonDao.findByImage(image);
		}
		return c;
	}

	public Cartoon findByName(String name) {
		return cartoonDao.findByNameAndState(name, true);
	}
}
