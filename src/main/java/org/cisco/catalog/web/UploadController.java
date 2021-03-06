package org.cisco.catalog.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

import javax.servlet.http.HttpServletRequest;

import org.cisco.catalog.domain.BlobData;
import org.cisco.catalog.domain.Cartoon;
import org.cisco.catalog.domain.Category;
import org.cisco.catalog.domain.Image;
import org.cisco.catalog.domain.Product;
import org.cisco.catalog.domain.ProductImage;
import org.cisco.catalog.domain.Tag;
import org.cisco.catalog.domain.Upload;
import org.cisco.catalog.service.BlobDataService;
import org.cisco.catalog.service.CartoonService;
import org.cisco.catalog.service.CategoryService;
import org.cisco.catalog.service.ImageService;
import org.cisco.catalog.service.ProductImageService;
import org.cisco.catalog.service.ProductService;
import org.cisco.catalog.service.TagService;
import org.cisco.catalog.service.UploadService;
import org.cisco.catalog.util.FileUtil;
import org.cisco.catalog.util.ImageUtil;
import org.cisco.catalog.util.UploadUtil;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/upload")
@Controller
public class UploadController {

	@Autowired
    UploadService uploadService;

	@Autowired
	ImageService imageService;

	@Autowired
	ProductService productService;

	@Autowired
	ProductImageService imgProductService;

	@Autowired
	TagService tagService;

	@Autowired
	CartoonService cartoonService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	BlobDataService blobDataService;

	@Autowired
	UploadUtil uploadUtil;

	protected static final Logger log = LoggerFactory.getLogger(UploadController.class);

	@RequestMapping(value="image/{entity}/{id}", method = RequestMethod.POST, consumes="application/octet-stream", produces="application/json")
	@ResponseBody
    public String uploadImage(@PathVariable("entity") String entity, @PathVariable("id") Integer id, HttpServletRequest request) throws FileNotFoundException, IOException, InterruptedException {       
		if (request.getInputStream() != null) {
			return createImage(id, request.getParameter("qqfile"), entity, request.getInputStream());
	    }
	    else {
	        return  "{\"success\": true}";
	    }
    }

	private Image createImageRecord(String file, String entity, BlobData blobData) {
		Image img = new Image();
		img.setBlobData(blobData);
		img.setName(file);
		if (file.indexOf(".") > 0) {
			img.setExt(file.substring(file.lastIndexOf(".") + 1));
		}
		img = imageService.saveImage(img);
		img.setPath("img" + "/" + entity + "/" + img.getId() + "/");
		return imageService.saveImage(img);
	}

	private BlobData getBlobData(InputStream is) throws IOException {
		Blob blob = Hibernate.createBlob(is);
		BlobData blobData = new BlobData();
		blobData.setContent(blob);
		return blobDataService.saveBlobData(blobData);
	}
	
	@RequestMapping(value="file/{entity}/{id}", method = RequestMethod.POST, consumes="application/octet-stream", produces="application/json")
	@ResponseBody
    public String uploadFile(@PathVariable("entity") String entity, @PathVariable("id") Integer id, HttpServletRequest request) throws FileNotFoundException, IOException, InterruptedException {       
		if (request.getInputStream() != null) {
			return createFile(id, request.getParameter("qqfile"), entity, request.getInputStream());
	    }
	    else {
	        return  "{\"success\": true}";
	    }
    }

	private Upload createUploadRecord(String file, String entity, BlobData blobData) {
		Upload upload = new Upload();
		upload.setBlobData(blobData);
		upload.setName(file);
		if (file.indexOf(".") > 0) {
			upload.setExt(file.substring(file.lastIndexOf(".") + 1));
		}
		upload = uploadService.saveUpload(upload);
		upload.setPath("files" + "/" + entity + "/" + upload.getId() + "/");
		return uploadService.saveUpload(upload);
	}

	@RequestMapping(value = "delete/{entity}/{entityId}/{id}", method = RequestMethod.POST)
	public @ResponseBody
	boolean delete(@PathVariable("entity") String entity, @PathVariable("entityId") Integer entityId,
			@PathVariable("id") Integer id) {
		Upload upload = uploadService.findUpload(id);
		if(upload != null) {
			if(entity.equalsIgnoreCase("product")) {
				Product product = productService.findProduct(entityId);
				product.setFile(null);
				productService.updateProduct(product);
			} else if(entity.equalsIgnoreCase("category")) {
				Category category = categoryService.findCategory(entityId);
				category.setFile(null);
				categoryService.updateCategory(category);
			}
			uploadService.deleteUpload(upload);
		}		
		return true;
	}

	// for IE browser
	@RequestMapping(value="image/{entity}/{id}", method = RequestMethod.POST, consumes="multipart/form-data")
    public String uploadImages(@PathVariable("entity") String entity, @PathVariable("id") Integer id,
    		@RequestParam("qqfile") MultipartFile multiPartFile, Model uiModel, HttpServletRequest request) throws FileNotFoundException, IOException, InterruptedException {       
		String response = "";
		if (multiPartFile != null) {
			response = createImage(id, multiPartFile.getOriginalFilename(), entity, multiPartFile.getInputStream());
	    }
	    else {
	        response = "{\"success\": true}";
	    }
		uiModel.addAttribute("response", response);
		return "response";
    }

	private String createImage(Integer id, String filename, String entity, InputStream inputStream) throws IOException, InterruptedException{
		BlobData blobData = getBlobData(inputStream);
		Image image = createImageRecord(filename, entity, blobData);
		ImageUtil.loadImage(image, uploadUtil.getUploadPath());
		Integer key = image.getId();
		Image prevImage = null;
		if(entity.equalsIgnoreCase("product")) {
			ProductImage productImage = new ProductImage();
			productImage.setImage(image);
			productImage.setProductId(id);
			imgProductService.saveProductImage(productImage);
			productService.updateProduct(productService.findProduct(id));
			key = productImage.getId();
		} else if(entity.equalsIgnoreCase("tag")) {
			Tag tag = tagService.findTag(id);
			prevImage = tag.getImage();
			tag.setImage(image);
			tagService.updateTag(tag);
			deleteExistingImage(prevImage);
		} else if(entity.equalsIgnoreCase("cartoon")) {
			Cartoon cartoon = cartoonService.findCartoon(id);
			prevImage = cartoon.getImage();
			cartoon.setImage(image);
			cartoonService.updateCartoon(cartoon);
			deleteExistingImage(prevImage);
		} else if(entity.equalsIgnoreCase("category")) {
			Category ctg = categoryService.findCategory(id);
			prevImage = ctg.getImage();
			ctg.setImage(image);
			categoryService.updateCategory(ctg);
			deleteExistingImage(prevImage);
		} else if(entity.equalsIgnoreCase("icon")) {
			Cartoon cartoon = cartoonService.findCartoon(id);
			prevImage = cartoon.getIcon();
			cartoon.setIcon(image);
			cartoonService.updateCartoon(cartoon);
			deleteExistingImage(prevImage);
		}
		return  "{\"success\":true,\"image\":{\"id\":\"" +key+ "\",\"ext\":\""+ image.getExt()+"\",\"path\":\"" + image.getThumbImagePath()+"\",\"name\":\""+image.getName()+"\"}}";
	}

	private void deleteExistingImage(Image image) {
		if(image != null) {
			imageService.deleteImage(image);
		}		
	}

	private void deleteExistingFile(Upload upload) {
		if(upload != null) {
			uploadService.deleteUpload(upload);
		}		
	}
	
	// for IE browser
	@RequestMapping(value="file/{entity}/{id}", method = RequestMethod.POST, consumes="multipart/form-data")
    public String uploadFiles(@PathVariable("entity") String entity, @PathVariable("id") Integer id,
    		@RequestParam("qqfile") MultipartFile multiPartFile, Model uiModel, HttpServletRequest request) throws FileNotFoundException, IOException, InterruptedException {       
		String response = "";
		if (request.getInputStream() != null) {
			response = createFile(id, multiPartFile.getOriginalFilename(), entity, multiPartFile.getInputStream());
	    }
	    else {
	        response = "{\"success\": true}";
	    }
		uiModel.addAttribute("response", response);
		return "response";
    }

	private String createFile(Integer id, String filename, String entity, InputStream inputStream) throws IOException, InterruptedException {
		BlobData blobData = getBlobData(inputStream);
		Upload upload = createUploadRecord(filename, entity, blobData);
		FileUtil.loadFile(upload, uploadUtil.getUploadPath(), entity.equalsIgnoreCase("category"));
		String filePath = upload.getFilePath();
		Upload prevFile = null;
		if(entity.equalsIgnoreCase("product")) {
			Product product = productService.findProduct(id);
			prevFile = product.getFile();
			product.setFile(upload);
			productService.updateProduct(product);
			deleteExistingFile(prevFile);
		} else if(entity.equalsIgnoreCase("category")) {
			Category category = categoryService.findCategory(id);
			prevFile = category.getFile();
			category.setFile(upload);
			categoryService.updateCategory(category);
			deleteExistingFile(prevFile);
			filePath = upload.getPathByName();
		}
        return  "{\"success\":true,\"file\":{\"id\":\"" +upload.getId()+ "\",\"ext\":\""+ upload.getExt()+"\",\"path\":\"" + filePath +"\",\"name\":\""+upload.getName()+"\"}}";
	}
}
