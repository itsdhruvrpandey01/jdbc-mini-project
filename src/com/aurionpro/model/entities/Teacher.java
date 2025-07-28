package com.aurionpro.model.entities;

public class Teacher {
	 	private int teacherId;
	    private String name;
	    private int profileId;
	    private boolean isActive;
	    public Teacher() {
	    	
	    }

	    public Teacher(int teacherId, String name, int profileId,boolean isActive) {
	        this.teacherId = teacherId;
	        this.name = name;
	        this.profileId = profileId;
	        this.isActive = isActive;
	    }

	    public boolean isActive() {
			return isActive;
		}

		public void setActive(boolean isActive) {
			this.isActive = isActive;
		}

		public int getTeacherId() {
	        return teacherId;
	    }

	    public void setTeacherId(int teacherId) {
	        this.teacherId = teacherId;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public int getProfileId() {
	        return profileId;
	    }

	    public void setProfileId(int profileId) {
	        this.profileId = profileId;
	    }

	    @Override
	    public String toString() {
	        return "Teacher [teacherId=" + teacherId + ", name=" + name + ", profileId=" + profileId + "]";
	    }
}
