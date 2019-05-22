package com.ying.config;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.stereotype.Component;

/**
 * 读取配置文件（下次再看看@Config注解）
 * Created by lyz on 2016/11/16.
 */
@Component
public class ConfigUtil {

    private static Configuration config = null;

    public ConfigUtil() {
    }

    static {
        try {
            config = new PropertiesConfiguration("es/elasticsearch.properties");
        } catch (Exception e) {
            System.out.println("properties not exists error");
        }
    }

    // 根据key读取value
    public static String getValue(String key) {
        try {
            String value = config.getString(key);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
