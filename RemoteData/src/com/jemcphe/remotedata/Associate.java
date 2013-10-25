package com.jemcphe.remotedata;

public class Associate {

	// Create some useful string constants
	public static final String COLUMN_FIRSTNAME = "firstName";
	public static final String COLUMN_LASTNAME = "lastName";
	public static final String COLUMN_PHONE = "phoneNumber";
	public static final String COLUMN_EMAIL = "email";
	public static final String COLUMN_COMPID = "compId";
	public static final String COLUMN_STATUS = "status";
	
	// Create Strings
	private String id;
	private String firstName;
	private String compId;
	private String lastName;
	private String phone;
	private String email;
	private String status;
	
	/*
	 * Getters & Setters for the Associate Objects.  This Class
	 * will make it easier to assign/retrieve values within
	 * application.
	 */
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getCompId() {
		return compId;
	}
	public void setCompId(String compId) {
		this.compId = compId;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}
	
	@Override
	public String toString() {
		
		return firstName;
//		NumberFormat nf = NumberFormat.getCurrencyInstance();
//		return nf.format(total);
	}
	
}
