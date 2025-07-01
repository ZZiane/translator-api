package org.zzach.translator.services.builders.impls;

import org.zzach.translator.models.HttpContract;
import org.zzach.translator.services.builders.HttpContractBuilder;

public class LanguageDetectionBuilder extends HttpContractBuilder<LanguageDetectionBuilder> {
	

	public LanguageDetectionBuilder(String baseUrl) {
		super(baseUrl);
		this.httpContract.addRequestAttribut("textName", "text");
		this.httpContract.addResponseAttribut("languageCode", "language_code");
		this.httpContract.addResponseAttribut("languageName", "language_name");
	}
	
	public LanguageDetectionBuilder textRequestName(String textName){
		this.httpContract.addRequestAttribut("textName", textName);
		return this;
	}

	public LanguageDetectionBuilder languageCodeRequestName(String languageCode){
		this.httpContract.addResponseAttribut("languageCode", languageCode);
		return this;
	}
	
	public LanguageDetectionBuilder languageRequestName(String languageName){
		this.httpContract.addResponseAttribut("languageName", languageName);
		return this;
	}
	
	public HttpContract build() {
		return this.httpContract;
	}

}
