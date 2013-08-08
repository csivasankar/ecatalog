package org.cisco.catalog.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "cccapp_categories")
@Configurable
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@JsonProperty("Category_id")
	private Integer id;

	@NotEmpty
	@Size(max = 45)
	@Column(name = "name", length = 45)
	@JsonProperty("Category_name")
	private String name;

	@Column(name = "modified")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "MM")
	@JsonProperty("Category_last_modified")
	private Calendar modified;

	@Column(name = "state")
	@NotNull
	@JsonIgnore
	private boolean state = true;
	
	@Column(name = "enable")
	@NotNull
	@JsonIgnore
	private boolean enable = true;

	@Column(name = "sort_order")
	@JsonProperty("Category_sort_order")
	private Integer sortOrder = 1;

	@ManyToOne(targetEntity = Image.class)
	@JoinColumn(name = "image_id")
	@JsonIgnore
	private Image image;
	
	@ManyToOne(targetEntity = Upload.class)
	@JoinColumn(name = "upload_id")
	@JsonIgnore
	private Upload file;

	@Column(name = "is_promo")
	@NotNull
	@JsonProperty("Category_is_promo")
	private boolean promo = false;
	
	public boolean isPromo() {
		return promo;
	}

	public void setPromo(boolean promo) {
		this.promo = promo;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public Upload getFile() {
		return file;
	}

	public void setFile(Upload file) {
		this.file = file;
	}

	@JsonProperty("Category_zip_file")
	public String getCategoryFile() {
		if (file != null) {
			String path = RequestContext.get().getContextPath();
			return path + "/" + file.getPathByName();
		}
		return null;
	}

	@JsonProperty("Category_state")
	public boolean getCategoryState() {
		return enable ? isState() : false;
	}
	
	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Calendar getModified() {
		return modified;
	}

	public void setModified(Calendar modified) {
		this.modified = modified;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	@JsonProperty("Category_image")
	public String getCategoryImage() {
		if (image != null) {
			String path = RequestContext.get().getContextPath();
			return path + "/" + image.getImagePath();
		}
		return null;
	}

	@JsonProperty("Category_is_cartoon")
	public boolean isCartoon() {
		return getName().equalsIgnoreCase("Cartoons");
	}
	
	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
