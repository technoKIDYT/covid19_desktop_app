package com.covid19.model;

public class World {
	private long confirmed;
	private long recovered;
	private long critical;
	private long deaths;
	private long active;

	public long getActive() {
		return active;
	}

	public void setActive(long active) {
		this.active = active;
	}

	private String lastChange;
	private String lastUpdate;
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
		return "World [confirmed=" + confirmed + ", recovered=" + recovered + ", critical=" + critical + ", deaths="
				+ deaths + ", active=" + active + ", lastChange=" + lastChange + ", lastUpdate=" + lastUpdate
				+ ", date=" + date + "]";
	}

}
