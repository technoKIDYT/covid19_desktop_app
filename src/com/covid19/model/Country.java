package com.covid19.model;

public class Country {
	private String name;
	private String alpha2Code;
	private String alpha3Code;
	private double latitude;
	private double longitude;
	private Case case1;

	public Case getCase1() {
		return case1;
	}

	public void setCase1(Case case1) {
		this.case1 = case1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlpha2Code() {
		return alpha2Code;
	}

	public void setAlpha2Code(String alpha2Code) {
		this.alpha2Code = alpha2Code;
	}

	public String getAlpha3Code() {
		return alpha3Code;
	}

	public void setAlpha3Code(String alpha3Code) {
		this.alpha3Code = alpha3Code;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Country [name=" + name + ", alpha2Code=" + alpha2Code + ", alpha3Code=" + alpha3Code + ", latitude="
				+ latitude + ", longitude=" + longitude + ", case1=" + case1 + "]";
	}

}
