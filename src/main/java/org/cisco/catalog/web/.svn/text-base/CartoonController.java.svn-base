package org.cisco.catalog.web;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.cisco.catalog.domain.Cartoon;
import org.cisco.catalog.service.CartoonService;
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

@RequestMapping("/cartoons")
@Controller
public class CartoonController {

	@Autowired
    CartoonService cartoonService;

	@RequestMapping(method = RequestMethod.POST)
    public String create(Cartoon cartoon, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		String url = httpServletRequest.getHeader("referer");
        String contextPath = httpServletRequest.getContextPath();
        url = url.substring(url.indexOf(contextPath)+contextPath.length());
		if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, cartoon);
            return "redirect:/cartoons";
        }
        uiModel.asMap().clear();
		if (!StringUtil.isEmptyTrim(cartoon.getName()) && !isDuplicateCartoon(cartoon)) {
			cartoonService.saveCartoon(cartoon);
		}
        return "redirect:" + url;
    }

	
	@RequestMapping(value = "/validateCartoon", method = RequestMethod.POST)
	@ResponseBody
	public String validateCartoon(Cartoon cartoon) {
		if (isDuplicateCartoon(cartoon)) {
			return "{\"errMsg\": \"Cartoon already exists\"}";
		}
		return "{}";
	}
	
	private boolean isDuplicateCartoon(Cartoon cartoon) {
		Cartoon crtn = cartoonService.findByName(cartoon.getName());
		if (crtn != null && (cartoon.getId() == null || !cartoon.getId().equals(crtn.getId()))) {
			return true;
		}		
		return false;
	}
	
	@RequestMapping(value = "/add")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Cartoon());
        return "cartoon/create";
    }

	@RequestMapping(value = "/show/{id}")
    public String show(@PathVariable("id") Integer id, Model uiModel) {
        uiModel.addAttribute("cartoon", cartoonService.findCartoon(id));
        return "cartoon/show";
    }

	@RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        uiModel.addAttribute("cartoonList", cartoonService.findAllByState(true));
        return "cartoon/list";
    }
	
	@RequestMapping(value = "/reorder")
    public String reorder(Model uiModel) {
		uiModel.addAttribute("cartoonList", cartoonService.findAllByState(true));
        return "cartoon/reorder";
    }

	@RequestMapping(method = RequestMethod.PUT)
    public String update(Cartoon cartoon, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, cartoon);
            return "redirect:/cartoons";
        }
        uiModel.asMap().clear();
		if (!StringUtil.isEmptyTrim(cartoon.getName()) && !isDuplicateCartoon(cartoon)) {
			Cartoon dbCartoon = cartoonService.findCartoon(cartoon.getId());
			dbCartoon.setName(cartoon.getName());
			cartoonService.updateCartoon(dbCartoon);
		}
        return "redirect:/cartoons";
    }

	@RequestMapping(value = "/edit/{id}")
    public String updateForm(@PathVariable("id") Integer id, Model uiModel) {
        populateEditForm(uiModel, cartoonService.findCartoon(id));
        return "cartoon/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id, Model uiModel) {
        Cartoon cartoon = cartoonService.findCartoon(id);
        cartoonService.deleteCartoon(cartoon);
        uiModel.asMap().clear();
        return "redirect:/cartoons";
    }

	void populateEditForm(Model uiModel, Cartoon cartoon) {
        uiModel.addAttribute("cartoon", cartoon);
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
	public void sort(@RequestParam("sortedData") String data, Model uiModel) {
		String[] listStr = data.split(",");
		int sortOrder = 1;
		for (String idStr : listStr) {
			Cartoon c = cartoonService.findCartoon(new Integer(idStr));
			if (!c.getSortOrder().equals(sortOrder)) {
				c.setSortOrder(sortOrder);
				cartoonService.updateCartoon(c);
			}
			sortOrder++;
		}
	}
	
	@RequestMapping(value = "/image/{id}")
	public String getImage(@PathVariable("id") Integer id, ModelMap map) {
		if (id > 0 && id != null) {
			map.addAttribute("imageObj", cartoonService.findCartoon(id).getImage());
			map.addAttribute("key", id);
			map.addAttribute("page", "cartoon");
		}
		return "uploadimage";
	}
	
	@RequestMapping(value = "/icon/{id}")
	public String getIcon(@PathVariable("id") Integer id, ModelMap map) {
		if (id > 0 && id != null) {
			map.addAttribute("imageObj", cartoonService.findCartoon(id).getIcon());
			map.addAttribute("key", id);
			map.addAttribute("page", "icon");
		}
		return "uploadimage";
	}
}
