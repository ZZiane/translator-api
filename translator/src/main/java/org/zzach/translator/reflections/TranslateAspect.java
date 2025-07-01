package org.zzach.translator.reflections;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.zzach.translator.annotations.Translatable;
import org.zzach.translator.annotations.Translate;
import org.zzach.translator.services.TranslateService;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Aspect
@Component
public class TranslateAspect {

	private TranslateService translateService;

	public TranslateAspect(TranslateService translateService) {
		this.translateService = translateService;
	}

	@Around("@annotation(org.zzach.translator.annotations.Translate)")
	public Object aroundTranslateMethod(ProceedingJoinPoint pjp) throws Throwable {
		Object result = pjp.proceed();

		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();
		Translate translateAnnotation = method.getAnnotation(Translate.class);

		String source = translateAnnotation.source();
		String target = translateAnnotation.target();
		
		if (result instanceof String) {
			String original = (String) result;
			return translateService.translate(original, source, target);
		} else if (result != null) {
			Class<?> clazz = result.getClass();
			for (Field field : clazz.getDeclaredFields()) {
				if (field.isAnnotationPresent(Translatable.class)) {
					field.setAccessible(true);
					Object fieldValue = field.get(result);
					if (fieldValue instanceof String originalText) {
						String translated = translateService.translate(originalText, source, target);
						field.set(result, translated);
					}
				}
			}
			return result;
		}

		return result;
	}

}
