package com.aurionpro.model.entities;

public class Student {
	    private int studentId;
	    private String name;
	    private int profileId;
	    private boolean isActive;
		public Student(int studentId, String name, int profileId) {
			super();
			this.studentId = studentId;
			this.name = name;
			this.profileId = profileId;
		}
		public boolean isActive() {
			return isActive;
		}

		public void setActive(boolean isActive) {
			this.isActive = isActive;
		}
		public int getStudentId() {
			return studentId;
		}
		public String getName() {
			return name;
		}
		public int getProfileId() {
			return profileId;
		}
		public void setStudentId(int studentId) {
			this.studentId = studentId;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setProfileId(int profileId) {
			this.profileId = profileId;
		}
		@Override
		public String toString() {
			return "Student [studentId=" + studentId + ", name=" + name + ", profileId=" + profileId + "]";
		}
		
}
