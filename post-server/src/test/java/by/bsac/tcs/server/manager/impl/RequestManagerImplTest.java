package by.bsac.tcs.server.manager.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import by.bsac.tcs.server.process.handler.RequestHandler;
import by.bsac.tcs.server.process.handler.RequestHandlerDao;
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
  private RequestHandlerDao dao;

  @Mock
  private Socket socket;

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
    when(dao.getRequestProcessor(any(Socket.class))).thenReturn(requestHandler);

    requestManager.manage(socket);

    verify(pool, times(1)).submit(requestHandler);
    verifyNoMoreInteractions(pool);
    verify(dao, times(1)).getRequestProcessor(any(Socket.class));
    verifyNoMoreInteractions(dao);
  }
}