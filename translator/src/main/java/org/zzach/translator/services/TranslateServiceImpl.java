package org.zzach.translator.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.zzach.translator.clients.Fetcher;
import org.zzach.translator.enums.ServiceName;
import org.zzach.translator.models.HttpContract;

import java.util.Map.Entry;

@Service
public class TranslateServiceImpl implements TranslateService {

	private final Translator translator;
	private final Fetcher fetcher;

	public TranslateServiceImpl(Translator translator) {
		this.translator = translator;
		this.fetcher = new Fetcher();
	}

	@Cacheable(value = "translations", key = "(#text.toLowerCase()) + '_' + ((#sourceLang == null ? 'auto' : #sourceLang).toLowerCase()) + '_' + (#targetLang.toLowerCase())")
	public String translate(String text, String sourceLang, String targetLang) {
		HttpContract httpContract = this.translator.getContract(ServiceName.TRANSLATE);
		Map<String, String> requestData = new HashMap<>();
		Map<String, String> requestMap = httpContract.getRequest();
		requestData.put(requestMap.get("textName"), text);
		requestData.put(requestMap.get("sourceLanguageName"), sourceLang == null ? "auto" : sourceLang);
		requestData.put(requestMap.get("targetLanguageName"), targetLang);
		httpContract.setFormData(requestData);
		Map<String, Object> response = this.fetcher.fetch(httpContract);
		Map<String, String> responseMap = httpContract.getResponse();
		if (responseMap.containsKey("translatedText")) {
			String translatedText = responseMap.get("translatedText");
			String value = (String) response.get(translatedText);
			return value;
		}
		return "null";
	}

	public Map<String, String> getLanguages() {
		return null;
	}

	public Optional<Entry<String, String>> detectLanguage(String text) {
		return null;
	}

	public Optional<String> getLanguageCode(String lang) {
		return Optional.empty();
	}

}
