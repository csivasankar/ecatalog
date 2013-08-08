package org.cisco.catalog.web;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.cisco.catalog.domain.Category;
import org.cisco.catalog.domain.Product;
import org.cisco.catalog.domain.ProductPromotion;
import org.cisco.catalog.domain.Promotion;
import org.cisco.catalog.service.CategoryService;
import org.cisco.catalog.service.ProductPromotionService;
import org.cisco.catalog.service.ProductService;
import org.cisco.catalog.service.PromotionService;
import org.cisco.catalog.util.DateUtil;
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

@RequestMapping("/promotions")
@Controller
public class PromotionController {

	@Autowired
    PromotionService promotionService;

	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductPromotionService productPromotionService; 
	
	@RequestMapping(method = RequestMethod.POST)
    public String create(Promotion promotion, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (StringUtil.isEmptyTrim(promotion.getOffer())) {
			bindingResult.rejectValue("offer", "promotion.offer", "Promotion Offer may not be empty");
		}
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, promotion);
            promotion.setEndDate(DateUtil.getPSTDate(promotion.getEndDate()));
            return "promotion/create";
        }
        populateDate(promotion.getEndDate(), httpServletRequest);
        promotion = promotionService.savePromotion(promotion);
        populateProducts(promotion, httpServletRequest);
        uiModel.asMap().clear();        
        return "redirect:/promotions";
    }

	@RequestMapping(value = "/add")
    public String createForm(Model uiModel) {
		Promotion promotion = new Promotion();
		promotion.setEndDate(DateUtil.getPSTDate());
        populateEditForm(uiModel, promotion);
        return "promotion/create";
    }
	
	private Object getOptions(int start, int end) {
		Map<String, String> options = new LinkedHashMap<String, String>();
		String opt = "";
		for (int i = start; i <= end; i++) {
			opt = String.valueOf(i);
			options.put(opt, opt);
		}
		return options;
	}

	private Map<String, String> getYearOptions() {
		Map<String, String> years = new LinkedHashMap<String, String>();
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = currentYear - 20; i <= currentYear + 20; i++) {
			years.put(String.valueOf(i-1900), String.valueOf(i));
		}
		return years;
	}

	private Map<String, String> getMonthOptions() {
		Map<String, String> months = new LinkedHashMap<String, String>();
		months.put("0", "January");
		months.put("1", "February");
		months.put("2", "March");
		months.put("3", "April");
		months.put("4", "May");
		months.put("5", "June");
		months.put("6", "July");
		months.put("7", "August");
		months.put("8", "September");
		months.put("9", "October");
		months.put("10", "November");
		months.put("11", "December");
		return months;
	}

	private List<String> getAvailabilityOptions() {
		return Arrays.asList("Global","Other", "US/CAN", "Latam", "Emea", "Emerging", "Apac", "Russia", "Australia", "China", "Japan");
	}

	private Map<String, String> getCategoryMap() {
		Map<String, String> optionMap = new LinkedHashMap<String, String>();
		optionMap.put("0", "Select Category");
		for (Category category : categoryService.findFilteredCategories()) {
			optionMap.put(category.getId().toString(), category.getName());
		}
		return optionMap;
	}

	@RequestMapping(value = "/show/{id}")
    public String show(@PathVariable("id") Integer id, Model uiModel) {
        uiModel.addAttribute("promotion", promotionService.findPromotion(id));
        uiModel.addAttribute("itemId", id);
        return "promotion/show";
    }

	@RequestMapping(value = "/products/{id}/{category_id}")
	@ResponseBody
    public String products(@PathVariable("id") Integer promotionId,
    		@PathVariable("category_id") Integer categoryId, Model uiModel) {
		String options = "";
		List<ProductPromotion> products = null;
		if (promotionId > 0) {
			products = promotionService.findPromotion(promotionId).getProducts();
		}
		String selected = "";
		if (categoryId > 0) {
			Category category = categoryService.findCategory(categoryId);
			for (Product p : productService.findProductsByCategory(category)) {
				if (products != null) {
					for (ProductPromotion pp: products) {
						if (pp.getProductId().equals(p.getId())) {
							selected = "selected";
						}
					}
				}
				options += "<option value=\"" + p.getId() + "\" "+ selected +">" + p.getName()	+ "</option>";
				selected = "";
			}
		}
		return options;
    }
	
	@RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            //uiModel.addAttribute("promotionList", promotionService.findPromotionEntries(firstResult, sizeNo));
            float nrOfPages = (float) promotionService.countAllPromotionses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("promotionList", promotionService.findAllPromotions());
        }
        uiModel.addAttribute("categoryList", categoryService.findFilteredCategories());
        return "promotion/list";
    }

	@RequestMapping(value = "/byCategory/{id}")
    public String listByCategory(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, 
    		@PathVariable("id") Integer id, Model uiModel) {
		Category category = categoryService.findCategory(id);
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            //uiModel.addAttribute("promotionList", promotionService.findPromotionEntries(firstResult, sizeNo));
            float nrOfPages = (float) promotionService.countAllPromotionses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("promotionList", promotionService.findPromotionsByCategory(category));
        }
        uiModel.addAttribute("selectedCategory", category);
        uiModel.addAttribute("categoryList", categoryService.findFilteredCategories());
        return "promotion/list";
    }
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String update(Promotion promotion, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (StringUtil.isEmptyTrim(promotion.getOffer())) {
			bindingResult.rejectValue("offer", "promotion.offer", "Promotion Offer may not be empty");
		}
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, promotion);
            promotion.setEndDate(DateUtil.getPSTDate(promotion.getEndDate()));
            return "promotion/update";
        }
        populateDate(promotion.getEndDate(), httpServletRequest);
        deletePromotionProducts(promotion);
        populateProducts(promotion, httpServletRequest);
        uiModel.asMap().clear();
        promotionService.updatePromotion(promotion);
        return "redirect:/promotions";
    }

	private void deletePromotionProducts(Promotion promotion) {
		for (ProductPromotion pp : productPromotionService.findByPromotionId(promotion.getId())) {
			productPromotionService.deleteProductPromotion(pp);
		}
	}

	private void populateDate(Calendar cal, HttpServletRequest httpServletRequest) {
		try {
			cal.set(Calendar.MONTH, Integer.parseInt(httpServletRequest.getParameter("endDate.time.month")));
			int year = Integer.parseInt(httpServletRequest.getParameter("endDate.time.year"));
			year += 1900;
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(httpServletRequest.getParameter("endDate.time.date")));
			cal.set(Calendar.HOUR, Integer.parseInt(httpServletRequest.getParameter("endDate.time.hours")));
			cal.set(Calendar.MINUTE, Integer.parseInt(httpServletRequest.getParameter("endDate.time.minutes")));
		} catch (NumberFormatException e) {
		}
	}
	
	private void populateProducts(Promotion promotion, HttpServletRequest httpServletRequest){
		String[] promotionProducts = httpServletRequest.getParameterValues("PromotionProducts");
		if(promotionProducts != null){
			ProductPromotion product = null;
			for(String promotionProduct : promotionProducts){
				product = new ProductPromotion();
				product.setProductId(Integer.parseInt(promotionProduct));
				product.setPromotionId(promotion.getId());
				productPromotionService.saveProductPromotion(product);
			}
		}
	}

	@RequestMapping(value = "/edit/{id}")
    public String updateForm(@PathVariable("id") Integer id, Model uiModel) {
		Promotion promotion = promotionService.findPromotion(id);
		populateEditForm(uiModel, promotion);
        return "promotion/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Promotion promotion = promotionService.findPromotion(id);
        promotionService.deletePromotion(promotion);
        return "redirect:/promotions";
    }

	void populateEditForm(Model uiModel, Promotion promotion) {
        uiModel.addAttribute("promotion", promotion);
        uiModel.addAttribute("categoryOptions", getCategoryMap());
        uiModel.addAttribute("availabilityOptions", getAvailabilityOptions());
        uiModel.addAttribute("monthOptions", getMonthOptions());
        uiModel.addAttribute("dayOptions", getOptions(1, 31));
        uiModel.addAttribute("yearOptions", getYearOptions());
        uiModel.addAttribute("hourOptions", getOptions(0, 23));
        uiModel.addAttribute("minuteOptions", getOptions(0, 59));
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
