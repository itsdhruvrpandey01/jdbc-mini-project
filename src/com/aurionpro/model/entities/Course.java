package com.aurionpro.model.entities;

public class Course {
	   	private int courseId;
	    private String name;
	    private int durationInMonths;

	    private boolean isActive;

	    public Course() {}

	    public Course(int courseId, String name,int durationInMonths) {
	        this.courseId = courseId;
	        this.name = name;
	        this.durationInMonths = durationInMonths;
	        this.isActive = true;
	    }
	    public boolean isActive() {
			return isActive;
		}

		public void setActive(boolean isActive) {
			this.isActive = isActive;
		}


	    public int getCourseId() {
	        return courseId;
	    }

	    public void setCourseId(int courseId) {
	        this.courseId = courseId;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }
	    public int getDurationInMonths() {
	        return durationInMonths;
	    }

	    public void setDurationInMonths(int durationInMonths) {
	        this.durationInMonths = durationInMonths;
	    }
	    @Override
	    public String toString() {
	    	 return "Course [courseId=" + courseId + 
	    	           ", name=" + name + 
	    	           ", durationInMonths=" + durationInMonths + "]";
	    }
}
