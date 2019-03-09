package com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "ListWidgets")
@DiscriminatorValue("LIST")
public class ListWidget extends Widget {
	
	private String listStyle;
	private String listItems;
	
	public ListWidget() {
	}

	public ListWidget(int id, String type) {
		super(id, type);
	}

	public String getListStyle() {
		return listStyle;
	}

	public void setListStyle(String listStyle) {
		this.listStyle = listStyle;
	}

	public String getListItems() {
		return listItems;
	}

	public void setListItems(String listItems) {
		this.listItems = listItems;
	}
	
}
