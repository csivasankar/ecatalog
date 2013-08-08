package org.cisco.catalog.service;

import java.util.List;
import org.cisco.catalog.dao.ImageDao;
import org.cisco.catalog.domain.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ImageServiceImpl implements ImageService {

	@Autowired
    ImageDao imagesDao;

	@Autowired
	BlobDataService blobDataService;
	
	public long countAllImages() {
        return imagesDao.count();
    }

	public void deleteImage(Image image) {
		if(image.getBlobData() != null) {
			blobDataService.deleteBlobData(image.getBlobData());
		}
        imagesDao.delete(image);
    }

	public Image findImage(Integer id) {
        return imagesDao.findOne(id);
    }

	public List<Image> findAllImages() {
        return imagesDao.findAll();
    }

	public Image saveImage(Image images) {
        return imagesDao.save(images);
    }

	public Image updateImage(Image images) {
       return imagesDao.update(images);
    }

	public List<Image> findImageEntries(int startIndex, int maxRecords) {
		return imagesDao.findImageEntries(startIndex, maxRecords);
	}
}
