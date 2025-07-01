package org.zzach.translator.services;

import org.zzach.translator.services.builders.ConfigBuilder;

public interface TranslatorRegistrar {
    public Translator config(ConfigBuilder config);
}
