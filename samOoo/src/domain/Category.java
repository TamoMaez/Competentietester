package domain;

import java.util.ArrayList;
import java.util.List;


public class Category implements Comparable {
	private String title;
	private String description;
	private Category mainCategory;
	private List<Feedback> feedback;
	
	public Category() {}
	
	public Category(String title) {
		setTitle(title);
		
		this.feedback = new ArrayList<>();
	}
	
	public Category(String title, String Description) {
		this(title);
		setDescription(Description);
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Category getMainCategorie() {
		return mainCategory;
	}
	
	public void setMainCategorie(Category mainCategory) {
		this.mainCategory = mainCategory;
	}
	
	public String toString(){
		return getTitle();
	}
	
	public void addFeedback(Feedback feedback) {
		if (feedback == null) {
			throw new DomainException("Feedback can't be NULL");
		}
		this.feedback.add(feedback);
	}
	
	public List<Feedback> getFeedback() {
		return this.feedback;
	}
	
	public boolean equals(Object o) {
		if (o instanceof Category) {
			Category c = (Category) o;
			
			return c.getTitle().equals(this.getTitle());
		}
		return false;
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Category) {
			Category c = (Category) o;
			return this.title.compareTo(c.title);
		}
		return -1;
	}
	
	
}