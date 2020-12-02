package com.example.projectda.utils;

import android.app.Activity;
import android.content.res.Configuration;

import com.example.projectda.activity.LoginActivity;

import java.util.Locale;

public class LocaleManager {

    public static final String TOKEN="CosOLiilTNPoJZHFefqyB44loBWGsOvoqq3ffn5xFLGuxP3gNcyNbCzJrx4E73TL";
    public static final String URL="https://viettelgroup.ai/voice/api/tts/v1/rest/syn";

    public static void setLocale(String language, Activity context) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        context.getBaseContext().getResources().updateConfiguration(configuration, context.getBaseContext().getResources().getDisplayMetrics());
        LoginActivity.prefConfig.writeLocate(language);
    }

    public static void loadLocale(Activity context)
    {
        String language= LoginActivity.prefConfig.readLocate();
        setLocale(language,context);
    }
}
