package org.zzach.translator.services;

import java.util.Map;

import org.zzach.translator.enums.ServiceName;
import org.zzach.translator.models.HttpContract;

public class Translator {
	private Map<ServiceName, HttpContract> services;
	
	public Translator(Map<ServiceName, HttpContract> services) {
		this.services = services;
	}
	
	public HttpContract getContract(ServiceName serviceName) {
		if(!services.containsKey(serviceName)) {
			throw new Error("Service ( "+serviceName.name()+" ) not enabled");
		}
		return services.get(serviceName);
	}

}
