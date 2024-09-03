package com.example.boardtennismatch.util;

import org.hibernate.cfg.Configuration;

public class ConfigurationUtil {
    public static String get(String key){
        return new Configuration().configure("hibernate.cfg.xml").getProperty(key);
    }

}
