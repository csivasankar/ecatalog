package org.cisco.catalog.dao;

import java.util.List;

import org.cisco.catalog.domain.Tag;
import org.cisco.catalog.util.Sort;

public interface TagDao {

	Tag findByName(String name);

	long count();

	void delete(Tag tag);

	Tag findOne(Integer id);

	Tag save(Tag tag);

	List<Tag> findAll(Sort sort);

	Tag update(Tag tag);
}
