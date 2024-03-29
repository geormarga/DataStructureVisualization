package Helpers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Locale;
import java.util.ResourceBundle;

public class Utils {
    private static final ObjectProperty<Locale> locale = new SimpleObjectProperty<>(Locale.ENGLISH);

    public static ObjectProperty<Locale> localeProperty() {
        return locale;
    }

    public static Locale getLocale() {
        return locale.get();
    }

    public static void setLocale(Locale locale) {
        localeProperty().set(locale);
    }

    public static String i18n(String key) {
        return ResourceBundle.getBundle("application", getLocale()).getString(key);
    }
}