package org.cisco.catalog.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "cccapp_promotions")
@Configurable
public class Promotion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@JsonProperty("Promotion_id")
	private Integer id;

	@NotEmpty
	@Size(max = 45)
	@Column(name = "offer", length = 45)
	@JsonProperty("Promotion_offer")
	private String offer;

	@Column(name = "overview")
	@JsonProperty("Promotion_overview")
	@Basic(fetch = FetchType.LAZY)
	@Lob
	private String overview;

	@Column(name = "availability", length = 45)
	@JsonProperty("Promotion_availability")
	private String availability;

	@Column(name = "epromo", length = 45)
	@JsonProperty("Promotion_ePromo")
	private String epromo;

	@Column(name = "end_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "MM")
	@JsonProperty("Promotion_end_date")
	private Calendar endDate = Calendar.getInstance();

	@ManyToOne(targetEntity = Category.class)
	@JoinColumn(name = "category_id")
	@JsonIgnore
	private Category category;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "promotionId")
	@JsonIgnore
	private List<ProductPromotion> products = new ArrayList<ProductPromotion>();

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOffer() {
		return offer;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getEpromo() {
		return epromo;
	}

	public void setEpromo(String epromo) {
		this.epromo = epromo;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@JsonProperty("Promotion_category")
	public String getCategoryName() {
		return category != null ? category.getName() : null;
	}

	public List<ProductPromotion> getProducts() {
		return products;
	}

	public void setProducts(List<ProductPromotion> products) {
		this.products = products;
	}

	@JsonProperty("Products")
	public List<Integer> getPromotionProducts() {
		List<Integer> promotionProducts = new ArrayList<Integer>();
		for (ProductPromotion pp : getProducts()) {
			promotionProducts.add(pp.getProductId());
		}
		return promotionProducts;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
