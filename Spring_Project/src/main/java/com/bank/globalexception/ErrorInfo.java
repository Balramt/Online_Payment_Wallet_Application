package com.bank.globalexception;

public class ErrorInfo {
	
	private String url; 
	private String emessage;
	public ErrorInfo(String url, String message) {
		this.url = url;
		this.emessage = message;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMessage() {
		return emessage;
	}
	public void setMessage(String message) {
		this.emessage = message;
	}

}