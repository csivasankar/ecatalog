package org.cisco.catalog.service;

import java.util.Calendar;
import java.util.List;

import org.cisco.catalog.domain.Cartoon;
import org.cisco.catalog.domain.Image;

public interface CartoonService {

	public abstract long countAllCartoons();

	public abstract void deleteCartoon(Cartoon cartoon);

	public abstract Cartoon findCartoon(Integer id);

	public abstract List<Cartoon> findAllCartoons();

	public abstract void saveCartoon(Cartoon cartoon);

	public abstract Cartoon updateCartoon(Cartoon cartoon);

	public abstract List<Cartoon> findAllByState(boolean state);

	public abstract List<Cartoon> findAllCartoonsByModifiedDate(
			Calendar dateTime);

	public abstract Cartoon findByImageId(Image image, String type);
	
	public abstract Cartoon findByName(String name);
}
