package com.unihannover.gamedev.models;

/**
 * this class represents the data transfer object for collectors.
 * @author amrit
 *
 */
public class CollectorDto {
	private String collectorName;
	private long collectorId;
	private String token;
	
	
	public String getCollectorName() {
		return collectorName;
	}
	public void setCollectorName(String collectorName) {
		this.collectorName = collectorName;
	}
	public long getCollectorId() {
		return collectorId;
	}
	public void setCollectorId(long collectorId) {
		this.collectorId = collectorId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
