package org.zzach.translator.services.builders;

import org.springframework.http.HttpMethod;
import org.zzach.translator.models.HttpContract;

public class HttpContractBuilder<K extends HttpContractBuilder<?>> {
	
	protected HttpContract httpContract;
	protected String baseUrl;

	public HttpContractBuilder(String baseUrl) {
		this.httpContract = new HttpContract();
		this.baseUrl = baseUrl;
	}
		
	public HttpContract build() {
		return this.httpContract;
	}
	
	public K method(HttpMethod httpMethod) {
		this.httpContract.setHttpMethod(httpMethod);
		return self();
	}
	
	public K path(String path) {
	    if (path.startsWith("/")) {
	        path = path.substring(1);
	    }
	    String newPath = baseUrl + "/" + path;
	    this.httpContract.setHttpPath(newPath);
	    return self();
	}
	
	@SuppressWarnings("unchecked")
	protected K self() {
	     return (K) this;
	}

}
