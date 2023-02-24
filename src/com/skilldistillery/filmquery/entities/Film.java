package com.skilldistillery.filmquery.entities;

import java.util.ArrayList;
import java.util.List;

public class Film {
	
	private int id;
	private String title;
	private String description;
	private Integer releaseYear;
	private int languageId;
	private Integer length; //might be null
	private double replacementCost;
	private int rentalDuration;
	private double rentalRate;
	private String rating;
	private String features;
	private List<Actor> actors;
	
	//constructors
	public Film() {}
	
	public Film(int id, String title, String description, int releaseYear, int languageId, Integer length,
			double replacementCost, int rentalDuration, String rating, String features, double rentalRate) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rentalDuration = rentalDuration;
		this.rating = rating;
		this.features = features;
		this.rentalRate = rentalRate;
	}

	//getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}
	
	public double getRentalRate() {
		return rentalRate;
	}
	
	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}
	
	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}
	
	public List<Actor> getActors(){
		//return a copy
		return new ArrayList<Actor>(actors);
	}

	//overrides
	@Override
	public String toString() {
		return getId() + " | " + getTitle() + " | " + getDescription() + " | " + getReleaseYear()
		+ " | " + getLanguageId() + " | " + getRentalDuration() + " | " + getRentalRate() + " | " +
				getLength() + " | " + getReplacementCost() + " | " + getRating() + " | " +
		getFeatures();
	}
	
	
	
}
