package org.zzach.translator.services.builders;

import org.zzach.translator.models.HttpContract;

@FunctionalInterface
public interface ServiceBuilder<K> {
	
	HttpContract build(K service);
}
