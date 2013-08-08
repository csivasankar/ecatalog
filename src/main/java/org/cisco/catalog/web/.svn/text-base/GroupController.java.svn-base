package org.cisco.catalog.web;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import org.cisco.catalog.domain.Group;
import org.cisco.catalog.service.GroupService;
import org.cisco.catalog.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

@RequestMapping("/groups")
@Controller
public class GroupController {

	@Autowired
    GroupService groupService;

	@RequestMapping(method = RequestMethod.POST)
    public String create(Group group, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		String url = httpServletRequest.getHeader("referer");
        String contextPath = httpServletRequest.getContextPath();
        url = url.substring(url.indexOf(contextPath)+contextPath.length());
		if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, group);
            return "redirect:/groups";
        }
        uiModel.asMap().clear();
		if (!StringUtil.isEmptyTrim(group.getName()) && !isDuplicateGroup(group)) {
			groupService.saveGroup(group);
		}
        return "redirect:" + url;
    }

	@RequestMapping(value = "/validateGroup", method = RequestMethod.POST)
	@ResponseBody
	public String validateGroup(Group group) {
		if (isDuplicateGroup(group)) {
			return "{\"errMsg\": \"Group already exists\"}";
		}
		return "{}";
	}
	
	private boolean isDuplicateGroup(Group group) {
		Group grp = groupService.findByName(group.getName());
		if (grp != null	&& (group.getId() == null || !group.getId().equals(grp.getId()))) {
			return true;
		}
		return false;
	}
	
	@RequestMapping(value = "/add")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Group());
        return "group/create";
    }

	@RequestMapping(value = "/show/{id}")
    public String show(@PathVariable("id") Integer id, Model uiModel) {
        uiModel.addAttribute("group", groupService.findGroup(id));
        uiModel.addAttribute("itemId", id);
        return "group/show";
    }

	@RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            //uiModel.addAttribute("group", groupService.findGroupsEntries(firstResult, sizeNo));
            float nrOfPages = (float) groupService.countAllGroups() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("groupList", groupService.findAllGroups());
        }
        return "group/list";
    }

	@RequestMapping(method = RequestMethod.PUT)
    public String update(Group group, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, group);
            return "redirect:/groups";
        }
        uiModel.asMap().clear();
		if (!StringUtil.isEmptyTrim(group.getName()) && !isDuplicateGroup(group)) {
			Group dbGroup = groupService.findGroup(group.getId());
			dbGroup.setName(group.getName());
			groupService.updateGroup(dbGroup);
		}
        return "redirect:/groups";
    }

	@RequestMapping(value = "/edit/{id}")
    public String updateForm(@PathVariable("id") Integer id, Model uiModel) {
        populateEditForm(uiModel, groupService.findGroup(id));
        return "group/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id, Model uiModel) {
        Group groups = groupService.findGroup(id);
        groupService.deleteGroup(groups);
        uiModel.asMap().clear();
        return "redirect:/groups";
    }

	void populateEditForm(Model uiModel, Group group) {
        uiModel.addAttribute("group", group);
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
}
