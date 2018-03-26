package by.bsac.tcs.server.util.loader

import by.bsac.tcs.server.model.Method

class ProtocolPropertiesLoader extends PropertiesLoader {

    private static final String FILE_NAME = 'protocol.properties'
    private static final String METHOD_REGEXP_PROPERTY_FORMAT = 'method.%s.regexp'
    private static final String METHOD_RESPONSE_PROPERTY_FORMAT = 'method.%s.response'

    private ProtocolPropertiesLoader() {
        super(FILE_NAME)
    }

    static ProtocolPropertiesLoader getInstance() {
        return SingletonHolder.getInstance()
    }

    String getMethodsRegexpProperty(final String methodName) {
        checkKey(methodName)
        String methodKey = sprintf(METHOD_REGEXP_PROPERTY_FORMAT, methodName.toLowerCase())
        return getProperty(methodKey)
    }

    String getMethodsResponseProperty(final String methodName) {
        checkKey(methodName)
        String methodKey = sprintf(METHOD_RESPONSE_PROPERTY_FORMAT, methodName.toLowerCase())
        return getProperty(methodKey)
    }

    String getMethodsResponseProperty(final Method method) {
        final String methodName = method != null ? method.name() : null
        return getMethodsResponseProperty(methodName)
    }

    private static class SingletonHolder {

        private static final ProtocolPropertiesLoader INSTANCE =
                new ProtocolPropertiesLoader()

        static ProtocolPropertiesLoader getInstance() {
            return INSTANCE
        }
    }
}
