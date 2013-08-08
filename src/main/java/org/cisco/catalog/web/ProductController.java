package org.cisco.catalog.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.cisco.catalog.domain.Category;
import org.cisco.catalog.domain.Product;
import org.cisco.catalog.domain.Tag;
import org.cisco.catalog.service.CategoryService;
import org.cisco.catalog.service.ProductService;
import org.cisco.catalog.service.TagService;
import org.cisco.catalog.util.ContextUtil;
import org.cisco.catalog.util.RequestContext;
import org.cisco.catalog.util.Sort;
import org.cisco.catalog.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/products")
@Controller
public class ProductController {

	@Autowired
    ProductService productsService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	TagService tagService;

	@RequestMapping(method = RequestMethod.POST)
    public String create(Product product, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (StringUtil.isEmptyTrim(product.getName())) {
			bindingResult.rejectValue("name", "product.name", "Product name may not be empty");
		}
		if (product.getCategory() == null) {
			bindingResult.rejectValue("category", "product.category", "Product Category may not be empty");
		}
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, product);
            return "product/create";
        }
        uiModel.asMap().clear();
        productsService.saveProduct(product);
        return "redirect:/products";
    }

	@RequestMapping(value = "/add")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new Product());
		return "product/create";
	}
	
	private Map<String, String> getCategoryMap() {
		Map<String, String> optionMap = new LinkedHashMap<String, String>();
		for (Category category : categoryService.findFilteredCategories()) {
			optionMap.put(category.getId().toString(), category.getName());
		}
		return optionMap;
	}
	
	private Map<String, String> getTagMap() {
		Map<String, String> optionMap = new LinkedHashMap<String, String>();
		optionMap.put("0", "None");
		for (Tag tag : tagService.findAllTags()) {
			optionMap.put(tag.getId().toString(), tag.getName());
		}
		return optionMap;
	}
	
	@RequestMapping(value = "/show/{id}")
    public String show(@PathVariable("id") Integer id, Model uiModel,HttpServletRequest req) {
		RequestContext.set(ContextUtil.populateContext(req));
        uiModel.addAttribute("product", productsService.findProduct(id));
        uiModel.addAttribute("itemId", id);
        return "product/show";
    }

	@RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, 
    				   @RequestParam(value = "sort", required = false) String sort,
    				   @RequestParam(value = "direction", required = false) String direction, HttpServletRequest request, Model uiModel) {
		String sortStr = (sort == null ? "name" : sort);
		String dir = (direction == null ? "asc" : direction);
        if (page == null) {
			page = 1;
		}
        if (request.getCookies() != null) {
	        for (Cookie ck: request.getCookies()){
				if (ck.getName().equalsIgnoreCase("product_list")) {
					if (ck.getValue() != null && page != Integer.parseInt(ck.getValue())) {
						page = Integer.parseInt(ck.getValue());
					}
				}
			}
        }
		int firstRes = page*5 - 5;
		int maxRes = 5;
		uiModel.addAttribute("totalRecords", productsService.countAllProducts());
		uiModel.addAttribute("productList", productsService.findAllByState(true, getSortObject(dir,sortStr), firstRes, maxRes));
        uiModel.addAttribute("categoryList", categoryService.findFilteredCategories());
        uiModel.addAttribute("sort", sortStr);
        uiModel.addAttribute("currentPage", page);
		uiModel.addAttribute("dir", (dir.equalsIgnoreCase("asc") ? "asc" : "desc"));
        return "product/list";
    }

	private Sort getSortObject(String dir, String feild) {
		if(dir.equalsIgnoreCase("asc")) {
			return new Sort(Sort.Direction.ASC, feild);
		}
		return new Sort(Sort.Direction.DESC, feild);
	}
	
	@RequestMapping(value = "/byCategory/{id}")
    public String listByCategory(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,
    		@PathVariable("id") Integer id, @RequestParam(value = "sort", required = false) String sort,
    		@RequestParam(value = "direction", required = false) String direction, Model uiModel) {
		String sortStr = (sort == null ? "name" : sort);
		String dir = (direction == null ? "asc" : direction);
		Category category = categoryService.findCategory(id);
        if (page == null) {
			page = 1;
		}
		int firstRes = page*5 - 5;
		int maxRes = 5;
		uiModel.addAttribute("totalRecords", productsService.countProductsByCategory(category));
        uiModel.addAttribute("productList", productsService.findProductsByCategory(category, getSortObject(dir,sortStr), firstRes, maxRes));
        uiModel.addAttribute("sort", sortStr);
		uiModel.addAttribute("dir", (dir.equalsIgnoreCase("asc") ? "asc" : "desc"));
        uiModel.addAttribute("selectedCategory", category);
        uiModel.addAttribute("currentPage", page);
        uiModel.addAttribute("categoryList", categoryService.findFilteredCategories());
        return "product/list";
    }
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String update(Product product, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (StringUtil.isEmptyTrim(product.getName())) {
			bindingResult.rejectValue("name", "product.name", "Product name may not be empty");
		}
		if (product.getCategory() == null) {
			bindingResult.rejectValue("category", "product.category", "Product Category may not be empty");
		}
		if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, product);
            return "product/update";
        }
		uiModel.asMap().clear();
        productsService.updateProduct(product);
        return "redirect:/products";
    }
	
	@RequestMapping(value = "/edit/{id}")
    public String updateForm(@PathVariable("id") Integer id, Model uiModel) {
        populateEditForm(uiModel, productsService.findProduct(id));
        return "product/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Product product = productsService.findProduct(id);
        productsService.deleteProduct(product);
        uiModel.asMap().clear();
        return "redirect:/products";
    }

	void populateEditForm(Model uiModel, Product product) {
        uiModel.addAttribute("product", product);
        uiModel.addAttribute("categoryOptions", getCategoryMap());
		uiModel.addAttribute("tagOptions", getTagMap());
    }
	
	@RequestMapping(value = "/images/{id}")
	public String getImage(@PathVariable("id") Integer id, ModelMap map) {
		if (id > 0 && id != null) {
			map.addAttribute("imageObj", productsService.findProduct(id).getImages());
			map.addAttribute("key", id);
			map.addAttribute("page", "product");
		}
		return "uploadimage";
	}
	
	@RequestMapping(value = "/file/{id}")
	public String getFile(@PathVariable("id") Integer id, Model map){
		if (id > 0 && id != null) {
			map.addAttribute("fileObj", productsService.findProduct(id).getFile());
			map.addAttribute("key", id);
			map.addAttribute("page", "product");
		}
		return "uploadfile";
	}
	
	@RequestMapping(value = "/reorder")
    public String reorder(@RequestParam(value = "category_id", required = false) Integer categoryId, Model uiModel) {
		Category category = null;
		List<Category> categories = categoryService.findFilteredCategories();
		if (categoryId == null) {
			category = categories.get(0);
		} else {
			category = categoryService.findCategory(categoryId);
		}
		uiModel.addAttribute("categoryList", categoryService.findFilteredCategories());
		uiModel.addAttribute("productList", productsService.findAllByCategoryAndSortOrder(true, category));
		uiModel.addAttribute("selectedCategory", category);
        return "product/reorder";
    }
	
	@RequestMapping(value = "/sort", method = RequestMethod.POST)
	@ResponseBody
	public String sort(@RequestParam("sortedData") String data, Model uiModel) {
		String[] listStr = data.split(",");
		int sortOrder = 1;
		for (String idStr : listStr) {
			Product product = productsService.findProduct(new Integer(idStr));
			if (!product.getSortOrder().equals(sortOrder)) {
				product.setSortOrder(sortOrder);
				productsService.updateProduct(product);
			}
			sortOrder++;
		}
		return "true";
	}
}
