package by.bsac.tcs.server.util;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.net.InetAddress;
import java.net.Socket;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LogMessageSharperTest {

  @Mock
  private Socket socket;

  @Mock
  private InetAddress inetAddress;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void formIncomingUserLogMessage() throws Exception {
    final String hostAddress = "127.9.12.2";
    final int port = 777;

    final String expectedMessage = String
        .format("new incoming request from %s:%d", hostAddress, port);

    when(socket.getLocalAddress()).thenReturn(inetAddress);
    when(socket.getLocalPort()).thenReturn(port);
    when(inetAddress.getHostAddress()).thenReturn(hostAddress);

    String actualMessage = LogMessageSharper.formIncomingUserLogMessage(socket);

    assertEquals(expectedMessage, actualMessage);

    verify(socket, times(1)).getLocalAddress();
    verify(socket, times(1)).getLocalPort();
    verifyNoMoreInteractions(socket);
    verify(inetAddress, times(1)).getHostAddress();
    verifyNoMoreInteractions(inetAddress);
  }
}