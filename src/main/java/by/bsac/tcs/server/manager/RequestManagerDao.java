package by.bsac.tcs.server.manager;

import by.bsac.tcs.server.manager.impl.RequestManagerImpl;

public class RequestManagerDao {

    private static final RequestManagerDao INSTANCE = new RequestManagerDao();
    private static final RequestManager pool = new RequestManagerImpl();

    private RequestManagerDao() {
    }

    public static RequestManagerDao getInstance() {
        return INSTANCE;
    }

    public RequestManager getManager() {
        return pool;
    }
}
