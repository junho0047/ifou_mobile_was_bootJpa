package com.nifou.m.ifou_mobile_was.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApplicationPropertiesUtil {
    private static final String CONFIG_FILE_PATH = "./config.ini";
    private static final Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(CONFIG_FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
            // 프로퍼티 파일 로드 실패 처리
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}