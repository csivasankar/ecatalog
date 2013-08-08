package org.cisco.catalog.web;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import org.cisco.catalog.domain.Category;
import org.cisco.catalog.service.CategoryService;
import org.cisco.catalog.util.ContextUtil;
import org.cisco.catalog.util.RequestContext;
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
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

@RequestMapping("/categories")
@Controller
public class CategoryController {

	@Autowired
    CategoryService categoryService;

	@RequestMapping(method = RequestMethod.POST)
    public String create(Category category, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		String url = httpServletRequest.getHeader("referer");
        String contextPath = httpServletRequest.getContextPath();
        url = url.substring(url.indexOf(contextPath)+contextPath.length());
		if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, category);
            return "redirect:/categories";
        }
        uiModel.asMap().clear();
		if (!StringUtil.isEmptyTrim(category.getName()) && !isDuplicateCategory(category)) {
			categoryService.saveCategory(category);
		}
        return "redirect:" + url;
    }

	@RequestMapping(value = "/validateCategory", method = RequestMethod.POST)
	@ResponseBody
	public String validateCategory(Category category) {
		if (isDuplicateCategory(category)) {
			return "{\"errMsg\": \"Category already exists\"}";
		}
		return "{}";
	}
	
	private boolean isDuplicateCategory(Category category) {
		Category ctg = categoryService.findByName(category.getName());
		if (ctg != null && (category.getId() == null || !category.getId().equals(ctg.getId()))) {
			return true;
		}
		return false;
	}
	
	@RequestMapping(value="workplace/{id}", method = RequestMethod.POST)
	public String workplace(@PathVariable("id") Integer id, @RequestParam(value="enable") Boolean enable, Model uiModel) {
		Category ctg = categoryService.findCategory(id);
		ctg.setEnable(enable);
        categoryService.updateCategory(ctg);
        return "redirect:/categories";
    }
	
	@RequestMapping(value = "/add")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Category());
        return "category/create";
    }

	@RequestMapping(value = "/show/{id}")
    public String show(@PathVariable("id") Integer id, Model uiModel, HttpServletRequest req) {
		RequestContext.set(ContextUtil.populateContext(req));
        uiModel.addAttribute("category", categoryService.findCategory(id));
        uiModel.addAttribute("itemId", id);
        return "category/show";
    }

	@RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            //uiModel.addAttribute("categoryList", categoryService.findCategoriesEntries(firstResult, sizeNo));
            float nrOfPages = (float) categoryService.countAllByState(true) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("categoryList", categoryService.findAllByState(true));
        }
        return "category/list";
    }
    
    @RequestMapping(value = "/reorder")
    public String reorder(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            //uiModel.addAttribute("categoryList", categoryService.findCategoriesEntries(firstResult, sizeNo));
            float nrOfPages = (float) categoryService.countAllByState(true) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("categoryList", categoryService.findAllByState(true));
        }
        return "category/reorder";
    }
    
	@RequestMapping(method = RequestMethod.PUT)
    public String update(Category category, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, category);
            return "redirect:/categories";
        }
        uiModel.asMap().clear();
		if (!StringUtil.isEmptyTrim(category.getName()) && !isDuplicateCategory(category)) {
			Category dbCategory = categoryService.findCategory(category.getId());
			dbCategory.setName(category.getName());
			categoryService.updateCategory(dbCategory);
		}
        return "redirect:/categories";
    }

	@RequestMapping(value = "/edit/{id}")
    public String updateForm(@PathVariable("id") Integer id, Model uiModel) {
        populateEditForm(uiModel, categoryService.findCategory(id));
        return "category/update";
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id, Model uiModel) {
        Category category = categoryService.findCategory(id);
        categoryService.deleteCategory(category);
        uiModel.asMap().clear();
        return "redirect:/categories";
    }

	void populateEditForm(Model uiModel, Category category) {
        uiModel.addAttribute("category", category);
    }

	String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }

	@RequestMapping(value = "/sort", method = RequestMethod.POST)
	@ResponseBody
	public String sort(@RequestParam("sortedData") String data, Model uiModel) {
		String[] listStr = data.split(",");
		int sortOrder = 1;
		for (String idStr : listStr) {
			Category c = categoryService.findCategory(new Integer(idStr));
			if (!c.getSortOrder().equals(sortOrder)) {
				c.setSortOrder(sortOrder);
				categoryService.updateCategory(c);
			}
			sortOrder++;
		}
		return "true";
	}
	
	@RequestMapping(value = "/image/{id}")
	public String getImage(@PathVariable("id") Integer id, ModelMap map) {
		if (id > 0 && id != null) {
			map.addAttribute("imageObj", categoryService.findCategory(id).getImage());
			map.addAttribute("key", id);
			map.addAttribute("page", "category");
		}
		return "uploadimage";
	}
	
	@RequestMapping(value = "/file/{id}")
	public String getFile(@PathVariable("id") Integer id, Model map){
		if (id > 0 && id != null) {
			map.addAttribute("fileObj", categoryService.findCategory(id).getFile());
			map.addAttribute("key", id);
			map.addAttribute("page", "category");
		}
		return "uploadfile";
	}
}
