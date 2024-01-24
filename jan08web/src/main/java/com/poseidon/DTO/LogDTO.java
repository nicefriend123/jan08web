package com.poseidon.DTO;

public class LogDTO {
	private String ip;
	private int date;
	public String getIp() {
		return ip;
	}
	public void setIp(String clientIP) {
		this.ip = clientIP;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
}
