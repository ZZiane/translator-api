package org.zzach.sample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.zzach.translator.services.Translator;
import org.zzach.translator.services.TranslatorRegistrar;
import org.zzach.translator.services.builders.ConfigBuilder;

@Configuration
public class TranslatorConfig implements TranslatorRegistrar  {

	@Bean
	public Translator config(ConfigBuilder config) {
		return config.baseUrl("http://127.0.0.1:5000/").enableTraslation((s) -> {
			return s.path("/translate")
					.method(HttpMethod.POST)
					.textRequestName("q")
					.sourceLangugeRequestName("source")
					.targetLangugeRequestName("target")
					.translatedText("translatedText")
					.build();
		}).build();
	}

}
