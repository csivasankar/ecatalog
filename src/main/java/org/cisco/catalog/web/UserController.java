package org.cisco.catalog.web;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.cisco.catalog.domain.Group;
import org.cisco.catalog.domain.User;
import org.cisco.catalog.service.GroupService;
import org.cisco.catalog.service.UserService;
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

@RequestMapping("/users")
@Controller
public class UserController {

	@Autowired
    UserService userService;
	
	@Autowired
	GroupService groupService;

	@RequestMapping(method = RequestMethod.POST)
    public String create(User user, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		String url = httpServletRequest.getHeader("referer");
        String contextPath = httpServletRequest.getContextPath();
        url = url.substring(url.indexOf(contextPath)+contextPath.length());
		if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, user);
            return "user/create";
        }
        uiModel.asMap().clear();
		if (!StringUtil.isEmptyTrim(user.getUsername()) && !isDuplicateUser(user)) {
			userService.saveUser(user);
		}
        return "redirect:" + url;
    }

	@RequestMapping(value = "/add")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new User());
		return "user/create";
	}
	
	@RequestMapping(value = "/validateUser", method = RequestMethod.POST)
	@ResponseBody
	public String validateUser(User user) {
		if (isDuplicateUser(user)) {
			return "{\"errMsg\": \"User already exists\"}";
		}
		return "{}";
	}

	private boolean isDuplicateUser(User user) {
		return userService.findByUsername(user.getUsername()) != null;
	}
	
	private Map<String, String> getGroupOptions() {
		Map<String, String> options = new TreeMap<String, String>();
		for (Group g : groupService.findAllGroups()) {
			options.put(g.getId().toString(), g.getName());
		}
		return options;
	}
	
	@RequestMapping(value = "/show/{id}")
    public String show(@PathVariable("id") Integer id, Model uiModel) {
        uiModel.addAttribute("user", userService.findUser(id));
        uiModel.addAttribute("itemId", id);
        return "user/show";
    }

	@RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            //uiModel.addAttribute("user", userService.findUsersEntries(firstResult, sizeNo));
            float nrOfPages = (float) userService.countAllUsers() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("userList", userService.findAllUsers());
        }
        return "user/list";
    }

	@RequestMapping(method = RequestMethod.PUT)
    public String update(User user, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, user);
            return "user/update";
        }
        uiModel.asMap().clear();
        userService.updateUser(user);
        return "redirect:/users";
    }

	@RequestMapping(value = "/edit/{id}")
    public String updateForm(@PathVariable("id") Integer id, Model uiModel) {
        populateEditForm(uiModel, userService.findUser(id));
        return "user/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id, Model uiModel) {
        User user = userService.findUser(id);
        userService.deleteUser(user);
        uiModel.asMap().clear();
        return "redirect:/users";
    }

	void populateEditForm(Model uiModel, User users) {
        uiModel.addAttribute("user", users);
        uiModel.addAttribute("groupOptions", getGroupOptions());
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
