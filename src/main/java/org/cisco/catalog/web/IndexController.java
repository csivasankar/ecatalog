package org.cisco.catalog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String welcome() {
		return "redirect:/products";
	}
}
