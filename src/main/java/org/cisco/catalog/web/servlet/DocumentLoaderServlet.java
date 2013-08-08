package org.cisco.catalog.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServlet;

import org.cisco.catalog.domain.Category;
import org.cisco.catalog.domain.Image;
import org.cisco.catalog.domain.Upload;
import org.cisco.catalog.service.CategoryService;
import org.cisco.catalog.service.ImageService;
import org.cisco.catalog.service.UploadService;
import org.cisco.catalog.util.FileUtil;
import org.cisco.catalog.util.ImageUtil;
import org.cisco.catalog.util.UploadUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * @author Sudhakar Nallam
 * 
 *         Document Loader Servlet responsible for loading the uploaded
 *         images/files from Blob table into the temp folder.
 */
@Configurable
@SuppressWarnings("serial")
public class DocumentLoaderServlet extends HttpServlet {
	
	@Autowired
	ImageService imageService;
	
	@Autowired
	UploadService uploadService;
	
	@Autowired
	UploadUtil uploadUtil;
	
	@Autowired
	CategoryService categoryService;
	
	protected static final Logger log = LoggerFactory.getLogger(DocumentLoaderServlet.class);
	
	public void init() {
		loadImages();
		loadFiles();
		loadCategoryFiles();
		try {
			if(log.isDebugEnabled()) {
				log.debug("com.ibm.websphere.servlet.temp.dir = " + System.getProperty("com.ibm.websphere.servlet.temp.dir"));
				log.debug("upload path " + uploadUtil.getUploadPath());
			}
		} catch (Exception e){
			
		}
	}
	
	private void loadCategoryFiles() {
		for(Category ctg : categoryService.findAllCategories()) {
			if(ctg.getFile() != null) {
				FileUtil.loadFile(ctg.getFile(), uploadUtil.getUploadPath(), true);
			}
		}
	}

	/**
	 * Loads images in batches 
	 */
	private void loadImages() {
		int startIndex = 0;
        int maxRecords = 250;
        
        long totalFiles = imageService.countAllImages();;
        while(startIndex < totalFiles){        	
        	List<Image> images = imageService.findImageEntries(startIndex, maxRecords);
        	for (Image image: images) {
        		ImageUtil.loadImage(image, uploadUtil.getUploadPath());
        	}
        	startIndex += maxRecords;
        }
	}
	
	/**
	 * Load files in batches
	 */
	private void loadFiles() {
		int startIndex = 0;
        int maxRecords = 250;
        
        long totalFiles = uploadService.countAllUploads();
        while(startIndex < totalFiles){        	
        	List<Upload> uploads = uploadService.findUploadEntries(startIndex, maxRecords);
        	for (Upload upload: uploads) {
        		FileUtil.loadFile(upload, uploadUtil.getUploadPath(), false);
        	}
        	startIndex += maxRecords;
        }		
	}
}
