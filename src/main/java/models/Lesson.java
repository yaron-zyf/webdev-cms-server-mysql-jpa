package models;

import java.util.List;

public class Lesson {
	
	private int id;
	private String title;
	private List<Topic> topics;
	
	public Lesson() {
	}

	public Lesson(int id, String title, List<Topic> topics) {
		this.id = id;
		this.title = title;
		this.topics = topics;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}
	
}
