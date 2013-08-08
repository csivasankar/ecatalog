package org.cisco.catalog.dao;

import java.util.Calendar;
import java.util.List;

import org.cisco.catalog.domain.Cartoon;
import org.cisco.catalog.domain.Image;
import org.cisco.catalog.util.Sort;

public interface CartoonDao {

	List<Cartoon> findAllByState(boolean state, Sort sort);

	Integer findMaxSortOrder();

	List<Cartoon> findByModifiedAfter(Calendar dateTime, Sort sort);

	Cartoon findByImage(Image image);

	Cartoon findByIcon(Image icon);

	Cartoon findByNameAndState(String name, boolean state);

	long count();

	Cartoon findOne(Integer id);

	List<Cartoon> findAll(Sort sort);

	Cartoon save(Cartoon cartoon);

	Cartoon update(Cartoon cartoon);
}
