package org.cisco.catalog.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.cisco.catalog.domain.Usync;
import org.cisco.catalog.domain.json.ErrorDto;
import org.cisco.catalog.domain.json.JSONDto;
import org.cisco.catalog.domain.json.ProductDto;
import org.cisco.catalog.domain.json.PromotionDto;
import org.cisco.catalog.service.CartoonService;
import org.cisco.catalog.service.CategoryService;
import org.cisco.catalog.service.ProductService;
import org.cisco.catalog.service.PromotionService;
import org.cisco.catalog.service.UsyncService;
import org.cisco.catalog.util.ContextUtil;
import org.cisco.catalog.util.DateUtil;
import org.cisco.catalog.util.RequestContext;
import org.cisco.catalog.util.StringUtil;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductJsonController {

	@Autowired
	ProductService productsService;

	@Autowired
	CategoryService categoriesService;

	@Autowired
	CartoonService cartoonsService;

	@Autowired
	PromotionService promotionsService;
	
	@Autowired
	UsyncService usyncService;

	private static final String PROMOTIONS = "promotions";
	private static final String PROMOTION = "promotion";	

	@RequestMapping(value = "/products.json", produces = {"application/json; charset=utf-8"})
	@ResponseBody
	public String getProductsJson(
			@RequestParam(value = "UUID", required = false) String uuid,
			@RequestParam(value = "action", required = false, defaultValue = "") String action,
			@RequestParam(value = "product_id", required = false, defaultValue = "0") Integer productId,
			@RequestParam(value = "datetime", required = false) @DateTimeFormat(pattern = DateUtil.DATE_FORMAT) Calendar dateTime,
			HttpServletRequest req)
			throws JsonGenerationException, JsonMappingException, IOException {
		if(isValidRequest(uuid)) {
			RequestContext.set(ContextUtil.populateContext(req));
			if (!StringUtil.isEmptyTrim(action)
					&& action.equalsIgnoreCase(PROMOTIONS)) {
				// generate promotions JSON
				PromotionDto dto = new PromotionDto();
				dto.setPromotions(promotionsService.findAllPromotions());
				return generateJson(dto);
			} else {
				if ((!StringUtil.isEmptyTrim(action)
						&& action.equalsIgnoreCase(PROMOTION) && productId > 0) || (StringUtil.isEmptyTrim(action) && productId > 0)) {
					// generate product wise promotions JSON
					PromotionDto dto = new PromotionDto();
					dto.setPromotions(promotionsService.findAllPromotionsByProductId(productId));
					return generateJson(dto);
				} else {
					// generate products
					ProductDto dto = new ProductDto();
					if(dateTime == null) {
						dto.setProducts(productsService.findAllProducts());
						dto.setCategories(categoriesService.findAllCategories());
						dto.setCartoons(cartoonsService.findAllCartoons());
					} else {
						dto.setProducts(productsService.findAllProductsByModifiedDate(dateTime));
						dto.setCategories(categoriesService.findAllCategoriesByModifiedDate(dateTime));
						dto.setCartoons(cartoonsService.findAllCartoonsByModifiedDate(dateTime));
					}
					return generateJson(dto);
				}
			}
		} else {
			ErrorDto dto = new ErrorDto();
			dto.setErrorMessage("UUID parameter is mandatory");
			return generateJson(dto);
		}
	}

	private boolean isValidRequest(String uuid) {
		if (!StringUtil.isEmptyTrim(uuid)) {
			Usync usync = usyncService.findByUuid(uuid);
			if (usync == null) {
				usync = new Usync();
				usync.setUuid(uuid);
				usyncService.saveUsync(usync);
			}
			return true;
		} else {
			return false;
		}
	}

	private String generateJson(JSONDto dto) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.WRITE_NUMBERS_AS_STRINGS, true);
		mapper.setDateFormat(new SimpleDateFormat(DateUtil.DATE_FORMAT));
		return mapper.writeValueAsString(dto);
	}
}
