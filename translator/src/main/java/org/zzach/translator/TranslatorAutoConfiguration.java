package org.zzach.translator;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@EnableCaching
@ComponentScan("org.zzach.translator")
public class TranslatorAutoConfiguration  {
}
