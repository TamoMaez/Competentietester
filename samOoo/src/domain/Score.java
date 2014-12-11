package domain;

public class Score {
	private int points;
	private int maxPoints = 1;
	private Category category;
	
	public Score(int maximumPoints, int points, Category category) {
		this(maximumPoints, category);
		this.setPoints(points);
	}
	
	public Score(int maximumPoints, Category category) {
		this(category);
		this.setMaxPoints(maximumPoints);
	}
	
	public Score(Category category) {
		super();
		this.setCategory(category);
	}
	
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Category getCategory() {
		return category;
	}

	private void setCategory(Category category) {
		this.category = category;
	}

	public int getMaxPoints() {
		return maxPoints;
	}

	public void setMaxPoints(int maxPoints) {
		this.maxPoints = maxPoints;
	}

	public boolean isMaximumScore() {
		return getPoints() == getMaxPoints();
	}
	
	
}
