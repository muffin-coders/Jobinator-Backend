package ch.wintihack.jobinator.persistence.service;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class TranslationService {

    public String translateTo(String text, String lang) {
        Translate translate = TranslateOptions.getDefaultInstance().getService();

        Translation translation = translate.translate(
                text,
                com.google.cloud.translate.Translate.TranslateOption.sourceLanguage("de"),
                com.google.cloud.translate.Translate.TranslateOption.targetLanguage(lang));

        return translation.getTranslatedText();
    }
}
