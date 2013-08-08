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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.cisco.catalog.util.RequestContext;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "cccapp_products")
@Configurable
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@JsonProperty("Product_id")
	private Integer id;

	@Column(name = "name", length = 45)
	@NotEmpty
	@Size(max = 45)
	@JsonProperty("Product_name")
	private String name;

	@Column(name = "description")
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@JsonProperty("Product_description")
	private String description;

	@Column(name = "at_a_glance")
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@JsonProperty("Product_at_a_glance")
	private String atAGlance;

	@Column(name = "other_info")
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@JsonProperty("Product_other_info")
	private String otherInfo;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "productId")
	@OrderBy("displayOrder ASC")
	@JsonIgnore
	private List<ProductImage> images = new ArrayList<ProductImage>();

	@JsonProperty("Product_images")
	@Transient
	private List<String> productImages = new ArrayList<String>();

	@Column(name = "price", length = 45)
	@JsonProperty("Product_price")
	private String price;

	@ManyToOne(targetEntity = Tag.class)
	@JoinColumn(name = "tag_id")
	@JsonIgnore
	private Tag tag;

	@ManyToOne(targetEntity = Category.class)
	@JoinColumn(name = "category_id")
	@JsonIgnore
	private Category category;

	@Column(name = "options_available")
	@JsonProperty("Product_optionsavailable")
	@Lob
	private String optionsAvailable;

	@Column(name = "created", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "MM")
	@JsonIgnore
	private Calendar created = java.util.Calendar.getInstance();

	@Column(name = "url", length = 255)
	@JsonProperty("Product_url")
	private String url;

	@ManyToOne(targetEntity = Upload.class)
	@JoinColumn(name = "upload_id")
	@JsonIgnore
	private Upload file;

	@Column(name = "state")
	@NotNull
	@JsonProperty("Product_state")
	private boolean state = true;

	@Column(name = "sort_order")
	@JsonProperty("Product_sort_order")
	private Integer sortOrder = 1;
	
	@Column(name = "modified")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "MM")
	@JsonProperty("Product_last_modified")
	private Calendar modified;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAtAGlance() {
		return atAGlance;
	}

	public void setAtAGlance(String atAGlance) {
		this.atAGlance = atAGlance;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getOptionsAvailable() {
		return optionsAvailable;
	}

	public void setOptionsAvailable(String optionsAvailable) {
		this.optionsAvailable = optionsAvailable;
	}

	public Calendar getCreated() {
		return created;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}

	public Calendar getModified() {
		return modified;
	}

	public void setModified(Calendar modified) {
		this.modified = modified;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
	
	public Integer getSortOrder() {
		return sortOrder == null ? 1 : sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public List<ProductImage> getImages() {
		return images;
	}

	public void setImages(List<ProductImage> images) {
		this.images = images;
	}

	public List<String> getProductImages() {
		String path = RequestContext.get().getContextPath();
		for (ProductImage img : getImages()) {
			productImages.add(path + "/" + img.getImage().getImagePath());
		}
		return productImages;
	}

	public Upload getFile() {
		return file;
	}

	public void setFile(Upload file) {
		this.file = file;
	}

	
	@JsonProperty("Product_pdf")
	public String getProductFile() {
		if (file != null) {
			String path = RequestContext.get().getContextPath();
			return path + "/" + file.getFilePath();
		}
		return null;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	@JsonProperty("Product_tagged_as")
	public String getProductTaggedAs() {
		return tag != null ? tag.getName() : null;
	}

	@JsonProperty("Product_tag_image")
	public String getProductTagImage() {
		if (tag != null) {
			if (tag.getImage() != null) {
				return RequestContext.get().getContextPath() + "/"
						+ tag.getImage().getImagePath();
			}
		}
		return null;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@JsonProperty("Category_id")
	public Integer getCategoryId() {
		return category != null ? category.getId() : null;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
