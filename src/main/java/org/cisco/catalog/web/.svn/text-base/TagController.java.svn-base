package org.cisco.catalog.web;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import org.cisco.catalog.domain.Tag;
import org.cisco.catalog.service.TagService;
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

@RequestMapping("/tags")
@Controller
public class TagController {

	@Autowired
    TagService tagService;

	@RequestMapping(method = RequestMethod.POST)
    public String create(Tag tag, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		String url = httpServletRequest.getHeader("referer");
        String contextPath = httpServletRequest.getContextPath();
        url = url.substring(url.indexOf(contextPath)+contextPath.length());
		if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, tag);
            return "redirect:/tags";
        }
        uiModel.asMap().clear();
        if (!StringUtil.isEmptyTrim(tag.getName()) && !isDuplicateTag(tag)) {
        	tagService.saveTag(tag);
        }
        return "redirect:" + url;
    }

	@RequestMapping(value = "/validateTag", method = RequestMethod.POST)
	@ResponseBody
	public String validateUser(Tag tag) {
		if (isDuplicateTag(tag)) {
			return "{\"errMsg\": \"Tag already exists\"}";
		}
		return "{}";
	}
	
	private boolean isDuplicateTag(Tag tag) {
		Tag t = tagService.findByName(tag.getName());
		if (t != null && (tag.getId() == null || !tag.getId().equals(t.getId()))) {
			return true;
		}
		return false;
	}
	
	@RequestMapping(value = "/add")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Tag());
        return "tag/create";
    }

	@RequestMapping(value = "/show/{id}")
    public String show(@PathVariable("id") Integer id, Model uiModel) {
        uiModel.addAttribute("tag", tagService.findTag(id));
        uiModel.addAttribute("itemId", id);
        return "tag/show";
    }

	@RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            //uiModel.addAttribute("tagList", tagService.findTagsEntries(firstResult, sizeNo));
            float nrOfPages = (float) tagService.countAllTags() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("tagList", tagService.findAllTags());
        }
        return "tag/list";
    }

	@RequestMapping(method = RequestMethod.PUT)
    public String update(Tag tag, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, tag);
            return "redirect:/tags";
        }
        uiModel.asMap().clear();
        if (!isDuplicateTag(tag)) {
	        Tag dbTag = tagService.findTag(tag.getId());
	        dbTag.setName(tag.getName());
	        tagService.updateTag(dbTag);
        }
        return "redirect:/tags";
    }

	@RequestMapping(value = "/edit/{id}")
    public String updateForm(@PathVariable("id") Integer id, Model uiModel) {
        populateEditForm(uiModel, tagService.findTag(id));
        return "tag/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id, Model uiModel) {
        Tag tag = tagService.findTag(id);
        tagService.deleteTag(tag);
        uiModel.asMap().clear();
        return "redirect:/tags";
    }

	void populateEditForm(Model uiModel, Tag tag) {
        uiModel.addAttribute("tag", tag);
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

	@RequestMapping(value = "/image/{id}")
	public String getImage(@PathVariable("id") Integer id, ModelMap map) {
		if (id > 0 && id != null) {
			map.addAttribute("imageObj", tagService.findTag(id).getImage());
			map.addAttribute("key", id);
			map.addAttribute("page", "tag");
		}
		return "uploadimage";
	}
}
