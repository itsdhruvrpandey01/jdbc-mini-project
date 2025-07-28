package com.aurionpro.model.entities;

public class Profile {
	    private int profileId;
	    private String email;
	    private String phone;
	    private String city;
	    private boolean isActive;
	    public Profile() {
	    	
	    }

	    public Profile(int profileId, String email, String phone, String city) {
	        this.profileId = profileId;
	        this.email = email;
	        this.phone = phone;
	        this.city = city;
	    }
	    
	    public boolean isActive() {
			return isActive;
		}

		public void setActive(boolean isActive) {
			this.isActive = isActive;
		}


	    public int getProfileId() {
	        return profileId;
	    }

	    public void setProfileId(int profileId) {
	        this.profileId = profileId;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPhone() {
	        return phone;
	    }

	    public void setPhone(String phone) {
	        this.phone = phone;
	    }

	    public String getCity() {
	        return city;
	    }

	    public void setCity(String city) {
	        this.city = city;
	    }

	    @Override
	    public String toString() {
	        return "Profile [profileId=" + profileId + ", email=" + email + ", phone=" + phone + ", city=" + city + "]";
	    }
}
