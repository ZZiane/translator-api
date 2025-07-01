package org.zzach.translator.services.builders.impls;

import org.zzach.translator.services.builders.HttpContractBuilder;

public class TranslationBuilder extends HttpContractBuilder<TranslationBuilder>{
	
	public TranslationBuilder(String baseUrl) {
		super(baseUrl);
		this.httpContract.addRequestAttribut("textName", "text");
		this.httpContract.addRequestAttribut("sourceLanguageName", "source_lang");
		this.httpContract.addRequestAttribut("targetLanguageName", "target_lang");
		this.httpContract.addResponseAttribut("translatedText", "translated_text");
	}
	
	public TranslationBuilder textRequestName(String textName){
		this.httpContract.addRequestAttribut("textName", textName);
		return this;
	}
	
	public TranslationBuilder sourceLangugeRequestName(String sourceLanguageName){
		this.httpContract.addRequestAttribut("sourceLanguageName", sourceLanguageName);
		return this;	
	}
	
	public TranslationBuilder targetLangugeRequestName(String targetLanguageName){
		this.httpContract.addRequestAttribut("targetLanguageName", targetLanguageName);
		return this;
	}
	
	public TranslationBuilder translatedText(String translatedText){
		this.httpContract.addResponseAttribut("translatedText", translatedText);
		return this;
	}
	
}
