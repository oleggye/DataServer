package by.bsac.tcs.server.process.handler;

import static by.bsac.tcs.server.util.LogMessageSharper.formIncomingUserLogMessage;

import by.bsac.tcs.server.process.handler.impl.RequestHandlerImpl;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestHandlerFactory {

  private RequestHandlerFactory() {
  }

  public static RequestHandlerFactory getInstance() {
    return SingletonHolder.getInstance();
  }

  public RequestHandler getRequestHandler(Socket requestSocket) {
    return makeProxy(new RequestHandlerImpl(requestSocket));
  }

  private static class SingletonHolder {

    private static final RequestHandlerFactory INSTANCE = new RequestHandlerFactory();

    public static RequestHandlerFactory getInstance() {
      return INSTANCE;
    }
  }

  private RequestHandler makeProxy(RequestHandler handler) {
    return (RequestHandler)
        Proxy.newProxyInstance(
            handler.getClass().getClassLoader(),
            handler.getClass().getInterfaces(),
            new LoggingSocketProxyHandler(handler));
  }

  private static final class LoggingSocketProxyHandler implements InvocationHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingSocketProxyHandler.class);
    private final RequestHandler handler;

    public LoggingSocketProxyHandler(final RequestHandler handler) {
      this.handler = handler;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      if (method.getName().equals("run")) {
        Socket clientSocket = fetchSocket(handler);
        LOGGER.info(formIncomingUserLogMessage(clientSocket));
      }
      return method.invoke(handler, args);
    }

    private Socket fetchSocket(final Object object)
        throws NoSuchFieldException, IllegalAccessException {
      Class<?> aClass = object.getClass();
      Field clientSocket = aClass.getDeclaredField("clientSocket");
      clientSocket.setAccessible(true);

      return (Socket) clientSocket.get(object);
    }
  }
}
