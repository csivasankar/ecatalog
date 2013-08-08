package org.cisco.catalog.service;

import java.util.List;
import org.cisco.catalog.domain.Image;

public interface ImageService {

	public abstract long countAllImages();

	public abstract void deleteImage(Image image);

	public abstract Image findImage(Integer id);

	public abstract List<Image> findAllImages();

	public abstract Image saveImage(Image image);

	public abstract Image updateImage(Image image);

	public abstract List<Image> findImageEntries(int startIndex,
			int maxRecords);

}
