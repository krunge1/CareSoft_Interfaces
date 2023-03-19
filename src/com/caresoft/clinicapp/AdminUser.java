package com.caresoft.clinicapp;

import java.util.ArrayList;
import java.util.Date;

//... imports class definition...
public class AdminUser extends User implements HIPAACompliantUser, HIPAACompliantAdmin {

	// Inside class:
	private String role;
	private ArrayList<String> securityIncidents;
	
	// Implement a constructor that takes an ID and a role
	public AdminUser(Integer id, String role) {
		super(id);
		this.role = role;
		this.securityIncidents = new ArrayList<String>();
		// TODO Auto-generated constructor stub
	}  
	// TO DO: Implement HIPAACompliantUser!
		@Override
		public boolean assignPin(int pin) {
	    	int numDigits = String.valueOf(pin).length();
	    	if (numDigits != 6) {
	    		return false;
	    	}
	    	this.pin = pin;
	    	return true;
		}
		
		@Override
		public boolean accessAuthorized(Integer confirmedAuthID) {
			if (confirmedAuthID != this.id) {
				this.authIncident();
				return false;
			}
			return true;
		}
		
		

	// TO DO: Implement HIPAACompliantAdmin!
	    
	    public void newIncident(String notes) {
	        String report = String.format(
	            "Datetime Submitted: %s \n,  Reported By ID: %s\n Notes: %s \n", 
	            new Date(), this.id, notes
	        );
	        securityIncidents.add(report);
	    }
	    public void authIncident() {
	        String report = String.format(
	            "Datetime Submitted: %s \n,  ID: %s\n Notes: %s \n", 
	            new Date(), this.id, "AUTHORIZATION ATTEMPT FAILED FOR THIS USER"
	        );
	        securityIncidents.add(report);
	    }
		@Override
		public ArrayList<String> reportSecurityIncidents() {
			return this.securityIncidents;
		}
		
	    // TO DO: Setters & Getters
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		
}
