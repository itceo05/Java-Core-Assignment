package com.bkap.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class Language {

    static Locale locale = new Locale("vi", "VN");
    static ResourceBundle bundle = ResourceBundle.getBundle("com.bkap.lang.language", locale);

    public static ResourceBundle getBundle(){
        return bundle;
    }
}
