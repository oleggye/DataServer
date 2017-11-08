package by.bsac.tcs.server.request.parser.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import by.bsac.tcs.server.request.Request;
import by.bsac.tcs.server.request.RequestBuilder;
import by.bsac.tcs.server.request.parser.ProtocolParser;
import java.io.InputStream;
import java.net.Socket;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class CustomProtocolParserTest {

  private ProtocolParser parser;

  @Mock
  private Socket socket;

  @Spy
  private InputStream inputStream;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    parser = new CustomProtocolParser();
    // inputStream = socket.getInputStream();
  }

  @Test
  public void parseWhenPassedOnlyDelimiterCharacter() throws Exception {
    final int paramDelimiter = ';';
    final int exitInputStreamCode = -1;

    final Request expectedRequest = new Request();
    when(socket.getInputStream()).thenReturn(inputStream);
    doReturn(paramDelimiter, exitInputStreamCode).when(inputStream).read();

    Request takenRequest = parser.parse(socket);

    assertNotNull(takenRequest);
    assertEquals(expectedRequest, takenRequest);

    verify(socket, times(1)).getInputStream();
    verifyNoMoreInteractions(socket);
    verify(inputStream, times(4)).read();
    verify(inputStream, times(1)).close();
  }

  @Test(expected = IllegalArgumentException.class)
  public void parseWhenPassedOnlyParamKeyAndDelimiterCharacter() throws Exception {
    final int paramKey = '5';
    final int paramDelimiter = ';';
    final int exitInputStreamCode = -1;

    when(socket.getInputStream()).thenReturn(inputStream);
    doReturn(paramKey, paramDelimiter, exitInputStreamCode).when(inputStream).read();

    parser.parse(socket);
  }

  @Test(expected = IllegalArgumentException.class)
  public void parseWhenPassedOnlyParamKeyAndEqAndDelimiterCharacter() throws Exception {
    final int paramKey = '5';
    final int eqPairDelimiter = '=';

    final int paramDelimiter = ';';
    final int exitInputStreamCode = -1;

    when(socket.getInputStream()).thenReturn(inputStream);
    doReturn(paramKey, eqPairDelimiter, paramDelimiter, exitInputStreamCode).when(inputStream)
        .read();

    parser.parse(socket);
  }

  @Test
  public void parseWhenPassedOneParamAndDelimiterCharacter() throws Exception {
    final int paramKey = 'a';
    final int eqPairDelimiter = '=';
    final int paramValue = '5';

    final int paramDelimiter = ';';
    final int exitInputStreamCode = -1;

    final Request expectedRequest = new RequestBuilder()
        .setParam("a", "5")
        .build();

    when(socket.getInputStream()).thenReturn(inputStream);
    doReturn(paramKey, eqPairDelimiter, paramValue,
        paramDelimiter, exitInputStreamCode)
        .when(inputStream).read();

    Request takenRequest = parser.parse(socket);

    assertNotNull(takenRequest);
    assertEquals(expectedRequest, takenRequest);

    verify(socket, times(1)).getInputStream();
    verifyNoMoreInteractions(socket);
    verify(inputStream, times(7)).read();
    verify(inputStream, times(1)).close();
  }
}