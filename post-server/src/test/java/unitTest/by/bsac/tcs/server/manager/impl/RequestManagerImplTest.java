package by.bsac.tcs.server.manager.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import by.bsac.tcs.server.process.handler.RequestHandler;
import by.bsac.tcs.server.process.handler.RequestHandlerFactory;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RequestManagerImplTest {

  @Mock
  private ExecutorService pool;

  @InjectMocks
  private RequestManagerImpl requestManager;

  @Mock
  private RequestHandlerFactory requestHandlerFactory;

  @Mock
  private RequestHandler requestHandler;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void shouldInitAndShutdownPool() {
    /*init pool*/
    requestManager.init();
    assertNotNull(requestManager.pool);
    /*shutdown poll*/
    requestManager.shutdown();
    assertNotNull(requestManager.pool);
    assertTrue(requestManager.pool.isTerminated());
    assertTrue(requestManager.pool.isShutdown());
  }

  @Test
  public void invokeManageMethod() throws Exception {
    requestManager.pool = pool;
    Socket socket = mock(Socket.class);

    when(requestHandlerFactory.getRequestHandler(any(Socket.class))).thenReturn(requestHandler);
    /*as mocked socket object returns null on any get method
    and NPE is thrown from formatting method*/
    when(socket.getLocalAddress()).thenReturn(mock(InetAddress.class));

    requestManager.manage(socket);

    verify(pool, times(1)).submit(requestHandler);
    verifyNoMoreInteractions(pool);
    verify(requestHandlerFactory, times(1))
        .getRequestHandler(any(Socket.class));
    verifyNoMoreInteractions(requestHandlerFactory);
  }
}