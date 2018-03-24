package by.bsac.tcs.server.util.loader

class ProtocolPropertiesLoader extends PropertiesLoader {

    private static final String FILE_NAME = 'protocol.properties'
    private static final String METHOD_REGEXP_PROPERTY_FORMAT = 'method.%s.regexp'

    private ProtocolPropertiesLoader() {
        super(FILE_NAME)
    }

    static ProtocolPropertiesLoader getInstance() {
        return SingletonHolder.getInstance()
    }

    String getMethodsRegexpProperty(String methodName) {
        checkKey(methodName)
        methodName = methodName.toLowerCase()
        String methodKey = sprintf(METHOD_REGEXP_PROPERTY_FORMAT, methodName)
        return getProperty(methodKey)
    }

    private static class SingletonHolder {

        private static final ProtocolPropertiesLoader INSTANCE =
                new ProtocolPropertiesLoader()

        static ProtocolPropertiesLoader getInstance() {
            return INSTANCE
        }
    }
}
