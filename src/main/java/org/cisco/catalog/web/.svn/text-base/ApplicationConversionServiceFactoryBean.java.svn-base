package org.cisco.catalog.web;

import org.cisco.catalog.domain.Cartoon;
import org.cisco.catalog.domain.Category;
import org.cisco.catalog.domain.Group;
import org.cisco.catalog.domain.Image;
import org.cisco.catalog.domain.ProductImage;
import org.cisco.catalog.domain.Product;
import org.cisco.catalog.domain.ProductPromotion;
import org.cisco.catalog.domain.Promotion;
import org.cisco.catalog.domain.Tag;
import org.cisco.catalog.domain.Upload;
import org.cisco.catalog.domain.User;
import org.cisco.catalog.domain.Usync;
import org.cisco.catalog.service.CartoonService;
import org.cisco.catalog.service.CategoryService;
import org.cisco.catalog.service.GroupService;
import org.cisco.catalog.service.ImageService;
import org.cisco.catalog.service.ProductService;
import org.cisco.catalog.service.PromotionService;
import org.cisco.catalog.service.TagService;
import org.cisco.catalog.service.UploadService;
import org.cisco.catalog.service.UserService;
import org.cisco.catalog.service.UsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

@Configurable
/**
 * A central place to register application converters and formatters. 
 */
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		// Register application converters and formatters
	}

	@Autowired
    CartoonService cartoonsService;

	@Autowired
    CategoryService categoriesService;

	@Autowired
    GroupService groupsService;

	@Autowired
    ImageService imagesService;

	@Autowired
    ProductService productsService;

	@Autowired
    PromotionService promotionsService;

	@Autowired
    TagService tagsService;

	@Autowired
    UploadService uploadsService;

	@Autowired
    UserService usersService;
	
	@Autowired
    UsyncService usyncService;

	public Converter<Cartoon, String> getCartoonsToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.cisco.catalog.domain.Cartoon, java.lang.String>() {
            public String convert(Cartoon cartoons) {
                return new StringBuilder().append(cartoons.getIcon()).append(' ').append(cartoons.getImage()).append(' ').append(cartoons.getModified()).toString();
            }
        };
    }

	public Converter<Integer, Cartoon> getIdToCartoonsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Integer, org.cisco.catalog.domain.Cartoon>() {
            public org.cisco.catalog.domain.Cartoon convert(java.lang.Integer id) {
                return cartoonsService.findCartoon(id);
            }
        };
    }

	public Converter<String, Cartoon> getStringToCartoonsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.cisco.catalog.domain.Cartoon>() {
            public org.cisco.catalog.domain.Cartoon convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), Cartoon.class);
            }
        };
    }

	public Converter<Category, String> getCategoriesToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.cisco.catalog.domain.Category, java.lang.String>() {
            public String convert(Category categories) {
                return new StringBuilder().append(categories.getModified()).append(' ').append(categories.getName()).toString();
            }
        };
    }

	public Converter<Integer, Category> getIdToCategoriesConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Integer, org.cisco.catalog.domain.Category>() {
            public org.cisco.catalog.domain.Category convert(java.lang.Integer id) {
                return categoriesService.findCategory(id);
            }
        };
    }

	public Converter<String, Category> getStringToCategoriesConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.cisco.catalog.domain.Category>() {
            public org.cisco.catalog.domain.Category convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), Category.class);
            }
        };
    }

	
	public Converter<Group, String> getGroupsToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.cisco.catalog.domain.Group, java.lang.String>() {
            public String convert(Group groups) {
                return new StringBuilder().append(groups.getName()).append(' ').append(groups.getCreated()).append(' ').append(groups.getModified()).toString();
            }
        };
    }

	public Converter<Integer, Group> getIdToGroupsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Integer, org.cisco.catalog.domain.Group>() {
            public org.cisco.catalog.domain.Group convert(java.lang.Integer id) {
                return groupsService.findGroup(id);
            }
        };
    }

	public Converter<String, Group> getStringToGroupsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.cisco.catalog.domain.Group>() {
            public org.cisco.catalog.domain.Group convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), Group.class);
            }
        };
    }
	
	public Converter<Image, String> getImagesToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.cisco.catalog.domain.Image, java.lang.String>() {
            public String convert(Image images) {
                return new StringBuilder().append(images.getName()).append(' ').append(images.getPath()).append(' ').append(images.getExt()).append(' ').append(images.getHeight()).toString();
            }
        };
    }

	public Converter<Integer, Image> getIdToImagesConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Integer, org.cisco.catalog.domain.Image>() {
            public org.cisco.catalog.domain.Image convert(java.lang.Integer id) {
                return imagesService.findImage(id);
            }
        };
    }

	public Converter<String, Image> getStringToImagesConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.cisco.catalog.domain.Image>() {
            public org.cisco.catalog.domain.Image convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), Image.class);
            }
        };
    }

	public Converter<ProductImage, String> getImagesProductsToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.cisco.catalog.domain.ProductImage, java.lang.String>() {
            public String convert(ProductImage imagesProducts) {
                return new StringBuilder().append(imagesProducts.getDisplayOrder()).toString();
            }
        };
    }

	public Converter<String, ProductImage> getStringToImagesProductsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.cisco.catalog.domain.ProductImage>() {
            public org.cisco.catalog.domain.ProductImage convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), ProductImage.class);
            }
        };
    }

	public Converter<Product, String> getProductsToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.cisco.catalog.domain.Product, java.lang.String>() {
            public String convert(Product products) {
                return new StringBuilder().append(products.getName()).append(' ').append(products.getDescription()).append(' ').append(products.getAtAGlance()).append(' ').append(products.getPrice()).toString();
            }
        };
    }

	public Converter<Integer, Product> getIdToProductsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Integer, org.cisco.catalog.domain.Product>() {
            public org.cisco.catalog.domain.Product convert(java.lang.Integer id) {
                return productsService.findProduct(id);
            }
        };
    }

	public Converter<String, Product> getStringToProductsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.cisco.catalog.domain.Product>() {
            public org.cisco.catalog.domain.Product convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), Product.class);
            }
        };
    }

	public Converter<ProductPromotion, String> getProductsPromotionsToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.cisco.catalog.domain.ProductPromotion, java.lang.String>() {
            public String convert(ProductPromotion productsPromotions) {
                return "(no displayable fields)";
            }
        };
    }

	public Converter<String, ProductPromotion> getStringToProductsPromotionsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.cisco.catalog.domain.ProductPromotion>() {
            public org.cisco.catalog.domain.ProductPromotion convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), ProductPromotion.class);
            }
        };
    }

	public Converter<Promotion, String> getPromotionsToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.cisco.catalog.domain.Promotion, java.lang.String>() {
            public String convert(Promotion promotions) {
                return new StringBuilder().append(promotions.getOffer()).append(' ').append(promotions.getOverview()).append(' ').append(promotions.getAvailability()).append(' ').append(promotions.getEpromo()).toString();
            }
        };
    }

	public Converter<Integer, Promotion> getIdToPromotionsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Integer, org.cisco.catalog.domain.Promotion>() {
            public org.cisco.catalog.domain.Promotion convert(java.lang.Integer id) {
                return promotionsService.findPromotion(id);
            }
        };
    }

	public Converter<String, Promotion> getStringToPromotionsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.cisco.catalog.domain.Promotion>() {
            public org.cisco.catalog.domain.Promotion convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), Promotion.class);
            }
        };
    }

	public Converter<Tag, String> getTagsToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.cisco.catalog.domain.Tag, java.lang.String>() {
            public String convert(Tag tags) {
                return new StringBuilder().append(tags.getName()).append(' ').append(tags.getImage()).toString();
            }
        };
    }

	public Converter<Integer, Tag> getIdToTagsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Integer, org.cisco.catalog.domain.Tag>() {
            public org.cisco.catalog.domain.Tag convert(java.lang.Integer id) {
                return tagsService.findTag(id);
            }
        };
    }

	public Converter<String, Tag> getStringToTagsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.cisco.catalog.domain.Tag>() {
            public org.cisco.catalog.domain.Tag convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), Tag.class);
            }
        };
    }

	public Converter<Upload, String> getUploadsToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.cisco.catalog.domain.Upload, java.lang.String>() {
            public String convert(Upload uploads) {
                return new StringBuilder().append(uploads.getName()).append(' ').append(uploads.getExt()).append(' ').append(uploads.getPath()).toString();
            }
        };
    }

	public Converter<Integer, Upload> getIdToUploadsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Integer, org.cisco.catalog.domain.Upload>() {
            public org.cisco.catalog.domain.Upload convert(java.lang.Integer id) {
                return uploadsService.findUpload(id);
            }
        };
    }

	public Converter<String, Upload> getStringToUploadsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.cisco.catalog.domain.Upload>() {
            public org.cisco.catalog.domain.Upload convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), Upload.class);
            }
        };
    }

	public Converter<User, String> getUsersToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.cisco.catalog.domain.User, java.lang.String>() {
            public String convert(User users) {
                return new StringBuilder().append(users.getUsername()).append(' ').append(users.getPassword()).append(' ').append(users.getGroup()).append(' ').append(users.getCreated()).toString();
            }
        };
    }

	public Converter<Integer, User> getIdToUsersConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Integer, org.cisco.catalog.domain.User>() {
            public org.cisco.catalog.domain.User convert(java.lang.Integer id) {
                return usersService.findUser(id);
            }
        };
    }

	public Converter<String, User> getStringToUsersConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.cisco.catalog.domain.User>() {
            public org.cisco.catalog.domain.User convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), User.class);
            }
        };
    }

	public Converter<Usync, String> getUsyncToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.cisco.catalog.domain.Usync, java.lang.String>() {
            public String convert(Usync usync) {
                return new StringBuilder().append(usync.getUuid()).append(' ').append(usync.getDateCreated()).toString();
            }
        };
    }

	public Converter<Integer, Usync> getIdToUsyncConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Integer, org.cisco.catalog.domain.Usync>() {
            public org.cisco.catalog.domain.Usync convert(java.lang.Integer id) {
                return usyncService.findUsync(id);
            }
        };
    }

	public Converter<String, Usync> getStringToUsyncConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.cisco.catalog.domain.Usync>() {
            public org.cisco.catalog.domain.Usync convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), Usync.class);
            }
        };
    }

	public void installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getCartoonsToStringConverter());
        registry.addConverter(getIdToCartoonsConverter());
        registry.addConverter(getStringToCartoonsConverter());
        registry.addConverter(getCategoriesToStringConverter());
        registry.addConverter(getIdToCategoriesConverter());
        registry.addConverter(getStringToCategoriesConverter());
        registry.addConverter(getGroupsToStringConverter());
        registry.addConverter(getIdToGroupsConverter());
        registry.addConverter(getStringToGroupsConverter());
        registry.addConverter(getImagesToStringConverter());
        registry.addConverter(getIdToImagesConverter());
        registry.addConverter(getStringToImagesConverter());
        registry.addConverter(getImagesProductsToStringConverter());
        registry.addConverter(getStringToImagesProductsConverter());
        registry.addConverter(getProductsToStringConverter());
        registry.addConverter(getIdToProductsConverter());
        registry.addConverter(getStringToProductsConverter());
        registry.addConverter(getProductsPromotionsToStringConverter());
        registry.addConverter(getStringToProductsPromotionsConverter());
        registry.addConverter(getPromotionsToStringConverter());
        registry.addConverter(getIdToPromotionsConverter());
        registry.addConverter(getStringToPromotionsConverter());
        registry.addConverter(getTagsToStringConverter());
        registry.addConverter(getIdToTagsConverter());
        registry.addConverter(getStringToTagsConverter());
        registry.addConverter(getUploadsToStringConverter());
        registry.addConverter(getIdToUploadsConverter());
        registry.addConverter(getStringToUploadsConverter());
        registry.addConverter(getUsersToStringConverter());
        registry.addConverter(getIdToUsersConverter());
        registry.addConverter(getStringToUsersConverter());
        registry.addConverter(getUsyncToStringConverter());
        registry.addConverter(getIdToUsyncConverter());
        registry.addConverter(getStringToUsyncConverter());
    }

	public void afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
}
