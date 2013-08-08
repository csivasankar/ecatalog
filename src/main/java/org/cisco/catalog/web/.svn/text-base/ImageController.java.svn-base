package org.cisco.catalog.web;

import org.cisco.catalog.domain.Cartoon;
import org.cisco.catalog.domain.Category;
import org.cisco.catalog.domain.Image;
import org.cisco.catalog.domain.ProductImage;
import org.cisco.catalog.domain.Tag;
import org.cisco.catalog.service.CartoonService;
import org.cisco.catalog.service.CategoryService;
import org.cisco.catalog.service.ProductImageService;
import org.cisco.catalog.service.ImageService;
import org.cisco.catalog.service.ProductService;
import org.cisco.catalog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping("/appimages")
@Controller
public class ImageController {

	@Autowired
    ImageService imageService;
	
	@Autowired
	ProductImageService productImageService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CartoonService cartoonService;
	
	@Autowired
	TagService tagService;
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value = "delete/{entity}/{entityId}/{id}", method = RequestMethod.POST)
    public @ResponseBody boolean delete(@PathVariable("entity") String entity, @PathVariable("entityId") Integer entityId,
    		@PathVariable("id") Integer id, @RequestParam(value="type", required = false) String type, Model uiModel) {
		if (entity.equalsIgnoreCase("product")) {
			ProductImage productImg = productImageService.findProductImage(id);
			productImageService.deleteProductImage(productImg);
			productService.updateProduct(productService.findProduct(entityId));
		} else {
			Image image = imageService.findImage(id);
			if (entity.equalsIgnoreCase("cartoon")) {
				Cartoon cartoon = cartoonService.findCartoon(entityId);
				cartoon.setImage(null);
				cartoonService.updateCartoon(cartoon);
			} else if (entity.equals("icon")) {
				Cartoon cartoon = cartoonService.findCartoon(entityId);
				cartoon.setIcon(null);
				cartoonService.updateCartoon(cartoon);
			} else if (entity.equals("tag")) {
				Tag tag = tagService.findTag(entityId);
				tag.setImage(null);
				tagService.updateTag(tag);
			} else if (entity.equalsIgnoreCase("category")) {
				Category category = categoryService.findCategory(entityId);
				category.setImage(null);
				categoryService.updateCategory(category);
			}
			if(image != null) {
				imageService.deleteImage(image);
			}
		}
		return true;
    }

	@RequestMapping(value = "sort/{entity}/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String sort(@PathVariable("entity") String entity, @PathVariable("id") Integer id, @RequestParam("sortedData") String data, Model uiModel) {
		if(entity.equalsIgnoreCase("product")) {
			String[] listStr = data.split(",");
			int sortOrder = 1;
			for (String idStr : listStr) {
				ProductImage productImage = productImageService.findProductImage(new Integer(idStr));
				if (!productImage.getDisplayOrder().equals(sortOrder)) {
					productImage.setDisplayOrder(sortOrder);
					productImageService.updateProductImage(productImage);
				}
				sortOrder++;
			}
			productService.updateProduct(productService.findProduct(id));
		}		
		return "true";
	}
}
