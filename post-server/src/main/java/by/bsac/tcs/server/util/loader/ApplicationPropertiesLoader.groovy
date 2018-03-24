package by.bsac.tcs.server.util.loader

class ApplicationPropertiesLoader extends PropertiesLoader {

    private static final String FILE_NAME = 'application.properties'

    private ApplicationPropertiesLoader() {
        super(FILE_NAME)
    }

    static ApplicationPropertiesLoader getInstance() {
        return SingletonHolder.getInstance()
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

    private static class SingletonHolder {

        private static final ApplicationPropertiesLoader INSTANCE =
                new ApplicationPropertiesLoader()

        static ApplicationPropertiesLoader getInstance() {
            return INSTANCE
        }
    }
}
