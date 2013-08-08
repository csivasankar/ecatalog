package org.cisco.catalog.web;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import org.cisco.catalog.domain.Usync;
import org.cisco.catalog.service.UsyncService;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

@RequestMapping("/usyncs")
@Controller
public class UsyncController {
	
	@Autowired
	UsyncService usyncService;

	@RequestMapping(method = RequestMethod.POST)
    public String create(Usync usync, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, usync);
            return "usyncs/create";
        }
        uiModel.asMap().clear();
        usyncService.saveUsync(usync);
        return "redirect:/usyncs/" + encodeUrlPathSegment(usync.getId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Usync());
        return "usyncs/create";
    }

	@RequestMapping(value = "/{id}")
    public String show(@PathVariable("id") Integer id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("usync", usyncService.findUsync(id));
        uiModel.addAttribute("itemId", id);
        return "usyncs/show";
    }

	@RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            //uiModel.addAttribute("usyncs", usyncService.findUsyncEntries(firstResult, sizeNo));
            float nrOfPages = (float) usyncService.countAllUsyncs() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("usyncs", usyncService.findAllUsyncs());
        }
        addDateTimeFormatPatterns(uiModel);
        return "usyncs/list";
    }

	@RequestMapping(method = RequestMethod.PUT)
    public String update(Usync usync, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, usync);
            return "usyncs/update";
        }
        uiModel.asMap().clear();
        usyncService.updateUsync(usync);
        return "redirect:/usyncs/" + encodeUrlPathSegment(usync.getId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form")
    public String updateForm(@PathVariable("id") Integer id, Model uiModel) {
        populateEditForm(uiModel, usyncService.findUsync(id));
        return "usyncs/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Usync usync = usyncService.findUsync(id);
        usyncService.deleteUsync(usync);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/usyncs";
    }

	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("usync_datecreated_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
    }

	void populateEditForm(Model uiModel, Usync usync) {
        uiModel.addAttribute("usync", usync);
        addDateTimeFormatPatterns(uiModel);
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
