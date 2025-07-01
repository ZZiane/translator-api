package org.zzach.translator.services;

import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

public interface TranslateService {

	 public String translate(String text, String sourceLang, String targetLang);
	 public Optional<Entry<String, String>> detectLanguage(String text);
	 public Map<String, String> getLanguages();
	 public Optional<String> getLanguageCode(String lang);
}
