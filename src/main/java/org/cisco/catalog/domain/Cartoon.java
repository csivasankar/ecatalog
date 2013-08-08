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
@Table(name = "cccapp_cartoons")
@Configurable
public class Cartoon {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@JsonProperty("Cartoon_id")
	private Integer id;

	@NotEmpty
	@Size(max = 45)
	@Column(name = "name", length = 45)
	@JsonProperty("Cartoon_name")
	private String name;

	@ManyToOne(targetEntity = Image.class)
	@JoinColumn(name = "icon_id")
	@JsonIgnore
	private Image icon;

	@ManyToOne(targetEntity = Image.class)
	@JoinColumn(name = "image_id")
	@JsonIgnore
	private Image image;

	@Column(name = "state")
	@NotNull
	@JsonProperty("Cartoon_state")
	private boolean state = true;

	@Column(name = "sort_order")
	@JsonProperty("Cartoon_sort_order")
	private Integer sortOrder = 1;

	@Column(name = "modified")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "MM")
	@JsonProperty("Cartoon_last_modified")
	private Calendar modified;

	public Image getIcon() {
		return icon;
	}

	public void setIcon(Image icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("Cartoon_ico")
	public String getIconPath() {
		if (getIcon() != null) {
			String path = RequestContext.get().getContextPath();
			return path + "/" + getIcon().getImagePath();
		}
		return null;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@JsonProperty("Cartoon_image")
	public String getImagePath() {
		if (getImage() != null) {
			String path = RequestContext.get().getContextPath();
			return path + "/" + getImage().getImagePath();
		}
		return null;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public Calendar getModified() {
		return modified;
	}

	public void setModified(Calendar modified) {
		this.modified = modified;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
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
}
