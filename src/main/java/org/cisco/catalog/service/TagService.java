package org.cisco.catalog.service;

import java.util.List;
import org.cisco.catalog.domain.Tag;

public interface TagService {

	public abstract long countAllTags();

	public abstract void deleteTag(Tag tag);

	public abstract Tag findTag(Integer id);

	public abstract List<Tag> findAllTags();

	public abstract void saveTag(Tag tag);

	public abstract Tag updateTag(Tag tag);

	public abstract Tag findByName(String name);

}
