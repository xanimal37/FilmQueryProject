package com.skilldistillery.filmquery.entities;

import java.util.ArrayList;
import java.util.List;

public class Actor {
	
	private int id;
	private String firstName;
	private String lastName;
	
	//constructors
	public Actor() {}
	
	public Actor(int id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	//getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	//overrides
	@Override
	public String toString() {
		return getId() + " | " + getFirstName() + " | " + getLastName();
	}

}
