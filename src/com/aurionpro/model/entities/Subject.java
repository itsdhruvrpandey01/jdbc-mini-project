package com.aurionpro.model.entities;

public class Subject {
	  	private int subjectId;
	    private String name;
	    private int courseId;   
	    private boolean isActive;
	    private String courseName;	

	    public Subject() {
	    	
	    }

	    public Subject(int subjectId, String name, int courseId) {
	        this.subjectId = subjectId;
	        this.name = name;
	        this.courseId = courseId;
	    }
	    
	    public boolean isActive() {
			return isActive;
		}

		public void setActive(boolean isActive) {
			this.isActive = isActive;
		}


	    public int getSubjectId() {
	        return subjectId;
	    }

	    public void setSubjectId(int subjectId) {
	        this.subjectId = subjectId;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public int getCourseId() {
	        return courseId;
	    }

	    public void setCourseId(int courseId) {
	        this.courseId = courseId;
	    }

	    public String getCourseName() {
	        return courseName;
	    }

	    public void setCourseName(String courseName) {
	        this.courseName = courseName;
	    }
	    @Override
	    public String toString() {
	        return "Subject [subjectId=" + subjectId + ", name=" + name +
	               ", courseId=" + courseId + "]";
	    }
}
