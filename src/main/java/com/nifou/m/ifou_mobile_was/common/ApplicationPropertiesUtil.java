package com.nifou.m.ifou_mobile_was.common;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class ApplicationPropertiesUtil {
    private static final String CONFIG_FILE_PATH = "config.ini";
    private static final Properties properties;

    static {
        properties = loadProperties();
    }

    public static Properties loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new InputStreamReader(new FileInputStream(CONFIG_FILE_PATH), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            // 프로퍼티 파일 로드 실패 처리
        }
        return properties;
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
