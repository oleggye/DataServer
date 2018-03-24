package by.bsac.tcs.server.util

import static java.util.Objects.isNull

class ApplicationPropertiesLoader {

    private static final String FILE_NAME = 'application.properties'

    private final Properties properties

    private ApplicationPropertiesLoader() {
        properties = new Properties()
        def inputStream = this.getClass().getClassLoader().getResourceAsStream(FILE_NAME)
        properties.load(inputStream)
    }

    static ApplicationPropertiesLoader getInstance() {
        return SingletonHolder.getInstance()
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


    int getPort() {
        return getIntProperty("server.port")
    }

    int getSocketTimeout() {
        return getIntProperty("socket.timeout")
    }

    int getPoolShutDownTimeout() {
        return getIntProperty("pool.shutdown.timeout")
    }

    int getPoolThreadCount() {
        return getIntProperty("pool.thread.count")
    }

    int getRequestMaxLength() {
        return getIntProperty("request.max_length")
    }

    boolean isKeepAlive() {
        return getBooleanProperty("socket.keep_alive")
    }

    boolean isReuseAddress() {
        return getBooleanProperty("socket.reuse_address")
    }

    private void checkKey(String key) {
        if (isNull(key) && key.empty) {
            throw new IllegalArgumentException("Illegal key argument: " + key)
        }
    }

    private static class SingletonHolder {

        private static final ApplicationPropertiesLoader INSTANCE =
                new ApplicationPropertiesLoader()

        static ApplicationPropertiesLoader getInstance() {
            return INSTANCE
        }
    }

}
