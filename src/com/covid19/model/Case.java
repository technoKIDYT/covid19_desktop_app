package com.covid19.model;

public class Case {

	private Country country;
	private String code;
	private long confirmed;
	private long recovered;
	private long critical;
	private long deaths;
	private double latitude;
	private double longitude;
	private String lastChange;
	private String lastUpdate;

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public long getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(long confirmed) {
		this.confirmed = confirmed;
	}

	public long getRecovered() {
		return recovered;
	}

	public void setRecovered(long recovered) {
		this.recovered = recovered;
	}

	public long getCritical() {
		return critical;
	}

	public void setCritical(long critical) {
		this.critical = critical;
	}

	public long getDeaths() {
		return deaths;
	}

	public void setDeaths(long deaths) {
		this.deaths = deaths;
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

	public String getLastChange() {
		return lastChange;
	}

	public void setLastChange(String lastChange) {
		this.lastChange = lastChange;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "Case [country=" + country.getName() + ", code=" + code + ", confirmed=" + confirmed + ", recovered="
				+ recovered + ", critical=" + critical + ", deaths=" + deaths + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", lastChange=" + lastChange + ", lastUpdate=" + lastUpdate + "]";
	}

}
