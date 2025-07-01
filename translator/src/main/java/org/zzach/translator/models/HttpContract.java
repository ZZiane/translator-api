package org.zzach.translator.models;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpMethod;

public class HttpContract {

	private String httpPath;
	private HttpMethod httpMethod;
	private Map<String, String> request;
	private Map<String, String> response;
	private Map<String, String> formData;

	public HttpContract() {
		this.httpPath = "/";
		this.httpMethod = HttpMethod.GET;
		this.request = new HashMap<String, String>();
		this.response = new HashMap<String, String>();
	}
	
	public void addRequestAttribut(String key, String value) {
		this.request.put(key, value);
	}
	
	public void addResponseAttribut(String key, String value) {
		this.response.put(key, value);
	}
	
	public String getHttpPath() {
		return httpPath;
	}

	public void setHttpPath(String httpPath) {
		this.httpPath = httpPath;
	}

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}
	
	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}
	
	public Map<String, String> getRequest() {
		return request;
	}
	
	public void setRequest(Map<String, String> request) {
		this.request = request;
	}
	
	public Map<String, String> getResponse() {
		return response;
	}
	
	public void setFormData(Map<String, String> formData) {
		this.formData = formData;
	}
	
	public Map<String, String> getFormData() {
		return formData;
	}
	
	public void MapResponse(Map<String, String> response) {
		this.response = response;
	}
	
}
