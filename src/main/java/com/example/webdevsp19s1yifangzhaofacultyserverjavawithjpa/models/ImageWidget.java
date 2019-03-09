package com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "ImageWidgets")
@DiscriminatorValue("IMAGE")
public class ImageWidget extends Widget {

	private String imageSrc;
	
	public ImageWidget() {
	}

	public ImageWidget(int id, String type) {
		super(id, type);
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}
	
}
