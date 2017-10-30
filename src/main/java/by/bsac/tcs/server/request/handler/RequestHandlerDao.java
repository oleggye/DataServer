package by.bsac.tcs.server.request.handler;

import by.bsac.tcs.server.request.handler.impl.RequestHandlerImpl;

import java.net.Socket;

public class RequestHandlerDao {
    private static final RequestHandlerDao INSTANCE = new RequestHandlerDao();

    private RequestHandlerDao(){}

    public static RequestHandlerDao getInstance(){
        return INSTANCE;
    }

    public RequestHandler getRequestProcessor(Socket requestSocket){
        return new RequestHandlerImpl(requestSocket);
    }

}
