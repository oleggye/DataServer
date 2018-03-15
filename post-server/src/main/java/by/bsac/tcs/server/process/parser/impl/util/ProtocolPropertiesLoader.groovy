package by.bsac.tcs.server.process.parser.impl.util

import static java.util.Objects.*

class ProtocolPropertiesLoader {

    private static final String FILE_NAME = 'protocol.properties'
    private static final String METHOD_REGEXP_PROPERTY_FORMAT = 'method.%s.regexp'
    private static final ProtocolPropertiesLoader INSTANCE = new ProtocolPropertiesLoader()

    private final Properties properties

    private ProtocolPropertiesLoader() {
        properties = new Properties()
        def inputStream = this.getClass().getClassLoader().getResourceAsStream(FILE_NAME)
        properties.load(inputStream)
    }

    static ProtocolPropertiesLoader getInstance() {
        return INSTANCE
    }

    String getProperty(String key) {
        checkKey(key)
        return properties.getProperty(key)
    }

    String getMethodsRegexpProperty(String methodName) {
        checkKey(methodName)
        methodName = methodName.toLowerCase()
        String methodKey = sprintf(METHOD_REGEXP_PROPERTY_FORMAT, methodName)
        return getProperty(methodKey)
    }

    private void checkKey(String key) {
        if (isNull(key) && key.empty) {
            throw new IllegalArgumentException("Illegal key argument: " + key)
        }
    }

}
