package com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "HeadingWidgets")
@DiscriminatorValue("HEADING")
public class HeadingWidget extends Widget {
	
	private int headingSize;
	private String headingText;
	
	public HeadingWidget() {
	}

	public HeadingWidget(int id, String type) {
		super(id, type);
	}

	public int getHeadingSize() {
		return headingSize;
	}

	public void setHeadingSize(int headingSize) {
		this.headingSize = headingSize;
	}

	public String getHeadingText() {
		return headingText;
	}

	public void setHeadingText(String headingText) {
		this.headingText = headingText;
	}
	
}
