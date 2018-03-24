package by.bsac.tcs.server.util.loader

import static org.apache.commons.lang3.StringUtils.isBlank

abstract class PropertiesLoader {

    protected final Properties properties

    PropertiesLoader(final String fileName) {
        properties = new Properties()
        def inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName)
        properties.load(inputStream)
    }

    String getProperty(String key) {
        checkKey(key)
        return properties.getProperty(key)
    }

    int getIntProperty(String key) {
        return getProperty(key) as int
    }

    boolean getBooleanProperty(String key) {
        return getProperty(key) as boolean
    }

    protected static void checkKey(String key) {
        if (isBlank(key)) {
            throw new IllegalArgumentException("Illegal key argument: " + key)
        }
    }
}
