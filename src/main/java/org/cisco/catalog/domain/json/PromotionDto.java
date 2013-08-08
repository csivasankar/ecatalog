package org.cisco.catalog.domain.json;

import java.util.ArrayList;
import java.util.List;

import org.cisco.catalog.domain.Promotion;
import org.codehaus.jackson.annotate.JsonProperty;

public class PromotionDto extends BaseDto {

	@JsonProperty("Promotions")
	private List<Promotion> promotions = new ArrayList<Promotion>();

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}
}
