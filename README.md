# Translator API

This library allows you to integrate translation into your Spring Boot project using two annotations and a configuration with a translation API.  
You just need to follow the contract for the translation or language detection endpoints.

You can check the `TranslatorConfig` class in the **SAMPLE** example project, which shows a mapping example with the LibreTranslate API.

## How to test the application

- First, you need a translation model. You can use the one from LibreTranslate.  
  Here is the link: [https://hub.docker.com/r/libretranslate/libretranslate](https://hub.docker.com/r/libretranslate/libretranslate)  

  To quickly test it, run the following command:

  ```bash
  docker run -ti --rm -p 5000:5000 \
    -e LT_LOAD_ONLY="en,fr,ar" \
    -e LT_HOST="0.0.0.0" \
    -e LT_PORT="5000" \
    libretranslate/libretranslate
  ```
  Running it locally avoids API key issues and Docker helps prevent machine-specific configuration problems.

- Open the project in your preferred IDE and start the Spring Boot application.

- Test the endpoint with the following command:

```bash
curl --location 'http://localhost:8080/product' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Java",
    "category": "Programming language"
}'
```

## Features:
- The library provides two annotations: :

    - `@Translatable` for annotating complex objects that should be translated.

    - `@Translate` which automatically triggers translation of the method’s return value if the method is annotated, even if the returned object is not annotated with `@Translatable`.

- The translation feature is optimized by using Spring Boot’s caching system to improve response time and reduce the processing load delegated to the translation model.