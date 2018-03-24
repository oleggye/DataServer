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
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LogMessageSharperTest {

  @Mock
  private Socket socket;

  @Mock
  private InetAddress inetAddress;

  @Test
  public void formIncomingUserLogMessage() {
    final String hostAddress = "127.9.12.2";
    final int port = 777;

    final String expectedMessage = String
        .format("new incoming request from %s:%d", hostAddress, port);

    when(socket.getLocalAddress()).thenReturn(inetAddress);
    when(socket.getPort()).thenReturn(port);
    when(inetAddress.getHostAddress()).thenReturn(hostAddress);

    String actualMessage = LogMessageSharper.formIncomingUserLogMessage(socket);

    assertEquals(expectedMessage, actualMessage);

    verify(socket).getLocalAddress();
    verify(socket).getPort();
    verifyNoMoreInteractions(socket);
    verify(inetAddress).getHostAddress();
    verifyNoMoreInteractions(inetAddress);
  }
}