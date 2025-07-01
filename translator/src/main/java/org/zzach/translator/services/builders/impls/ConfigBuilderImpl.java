package org.zzach.translator.services.builders.impls;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.zzach.translator.enums.ServiceName;
import org.zzach.translator.models.HttpContract;
import org.zzach.translator.services.Translator;
import org.zzach.translator.services.builders.ConfigBuilder;
import org.zzach.translator.services.builders.ServiceBuilder;

@Configuration
public class ConfigBuilderImpl implements ConfigBuilder {

	private String baseUrl;
	private Map<ServiceName, HttpContract> services;
	
	public ConfigBuilderImpl() {
		this.baseUrl = "http://127.0.0.1";
		this.services =  new HashMap<ServiceName, HttpContract>();
	}

	public ConfigBuilder baseUrl(String baseUrl) {
	    if (baseUrl.endsWith("/")) {
	        this.baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
	    }
		return this;
	}

	public ConfigBuilder enableLanguageDetection(ServiceBuilder<LanguageDetectionBuilder> languageDetection) {
		LanguageDetectionBuilder languageDetectionBuilder = new LanguageDetectionBuilder(this.baseUrl);
		this.services.put(ServiceName.DETECT_LANGUAGE, languageDetection.build(languageDetectionBuilder));
		return this;
	}

	public ConfigBuilder enableTraslation(ServiceBuilder<TranslationBuilder> translation) {
		TranslationBuilder translationBuilder = new TranslationBuilder(this.baseUrl);
		this.services.put(ServiceName.TRANSLATE, translation.build(translationBuilder));
		return this;
	}


	public Translator build() {
		return new Translator(this.services);
	}


}
