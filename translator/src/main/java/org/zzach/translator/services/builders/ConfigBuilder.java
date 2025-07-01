package org.zzach.translator.services.builders;


import org.zzach.translator.services.Translator;
import org.zzach.translator.services.builders.impls.LanguageDetectionBuilder;
import org.zzach.translator.services.builders.impls.TranslationBuilder;

public interface ConfigBuilder {

	public ConfigBuilder baseUrl(String baseUrl);
	public ConfigBuilder enableLanguageDetection(ServiceBuilder<LanguageDetectionBuilder> languageDetection);
	public ConfigBuilder enableTraslation(ServiceBuilder<TranslationBuilder> translation);
	public Translator build();
}
