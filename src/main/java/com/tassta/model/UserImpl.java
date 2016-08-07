package com.tassta.model;

import javafx.beans.property.BooleanProperty;
import javafx.scene.image.Image;

public class UserImpl implements User {

	public UserImpl() {
	}

	public UserImpl(Integer id, String name, Boolean isOnline, Image icon) {
		this.isOnline=isOnline;
		this.icon=icon;
		this.name = name;
		this.id = id;
	}

	private String name;
	private Integer id;
	private boolean isOnline;
	private Image icon;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIsOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public void setIcon(String url) {
		this.icon = new Image(url);
	}

	@Override
	public boolean isOnline() {
		return this.isOnline;
	}

	@Override
	public Image getIcon() {
	return this.icon;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserImpl user = (UserImpl) o;

		if (!name.equals(user.name)) return false;
		return id.equals(user.id);
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + id.hashCode();
		return result;
	}
}
