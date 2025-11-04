package com.automationexercise.utils.DataReader;

import com.automationexercise.utils.Logs.LogsManager;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.Properties;

public class PropertyReader {
    private static Properties properties;

    // Load Properties function
    public static Properties loadProperties() {
        if (properties != null) return properties; // already loaded
        properties = new Properties();
        try {
            Collection<File> propertiesFiles = FileUtils.listFiles(
                    new File("src/main/resources"), new String[]{"properties"}, true);
            for (File file : propertiesFiles) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    properties.load(fis);
                } catch (Exception e) {
                    LogsManager.error("Error loading properties file:", file.getName(), e.getMessage());
                }
            }
            System.getProperties().putAll(properties); // put into system properties
            LogsManager.info("Properties loaded successfully");
        } catch (Exception e) {
            LogsManager.error("Error loading properties files", e.getMessage());
        }
        return properties;
    }

    public static String getProperty(String key) {
        if (properties == null) {
            loadProperties(); // ensure properties are loaded
        }
        String value = System.getProperty(key, properties.getProperty(key));
        if (value == null) {
            LogsManager.error("Property '" + key + "' is not set!");
            throw new RuntimeException("Property '" + key + "' is not defined in .properties files!");
        }
        return value;
    }
}
