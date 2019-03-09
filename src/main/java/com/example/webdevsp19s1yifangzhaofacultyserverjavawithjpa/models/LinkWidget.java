package com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "LinkWidgets")
@DiscriminatorValue("LINK")
public class LinkWidget extends Widget {
	
	private String linkTitle;
	private String linkHref;
	
	public LinkWidget() {
	}

	public LinkWidget(int id, String type) {
		super(id, type);
	}

	public String getLinkTitle() {
		return linkTitle;
	}

	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}

	public String getLinkHref() {
		return linkHref;
	}

	public void setLinkHref(String linkHref) {
		this.linkHref = linkHref;
	}
	
}
