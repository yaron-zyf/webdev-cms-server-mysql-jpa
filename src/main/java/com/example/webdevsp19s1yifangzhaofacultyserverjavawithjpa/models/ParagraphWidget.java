package com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "ParagraphWidgets")
@DiscriminatorValue("PARAGRAPH")
public class ParagraphWidget extends Widget {
	
	private String paragraphText;
	
	public ParagraphWidget() {
	}

	public ParagraphWidget(int id, String type) {
		super(id, type);
	}

	public String getParagraphText() {
		return paragraphText;
	}

	public void setParagraphText(String paragraphText) {
		this.paragraphText = paragraphText;
	}
	
}
