package com.aurionpro.model.entities;

public class Course {
	   private int courseId;
	    private String name;

	    public Course() {}

	    public Course(int courseId, String name) {
	        this.courseId = courseId;
	        this.name = name;
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

	    @Override
	    public String toString() {
	        return "Course [courseId=" + courseId + ", name=" + name + "]";
	    }
}
