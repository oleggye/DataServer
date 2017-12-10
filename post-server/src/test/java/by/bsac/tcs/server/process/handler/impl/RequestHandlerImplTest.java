package by.bsac.tcs.server.process.handler.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import by.bsac.tcs.server.model.Request;
import by.bsac.tcs.server.process.handler.exception.RequestHandlerException;
import by.bsac.tcs.server.process.parser.exception.ProtocolParseException;
import by.bsac.tcs.server.process.parser.ProtocolParser;
import java.net.Socket;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RequestHandlerImplTest {

  @InjectMocks
  private RequestHandlerImpl requestHandler;

  @Mock
  private Socket socket;

  @Mock
  private ProtocolParser parser;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void runSuccessfully() throws Exception {
    when(parser.parse(socket)).thenReturn(any(Request.class));

    requestHandler.run();
    verify(parser, times(1)).parse(socket);
    verifyNoMoreInteractions(parser);
  }

  @Test(expected = RequestHandlerException.class)
  public void runWithException() throws Exception {
    when(parser.parse(socket)).thenThrow(ProtocolParseException.class);

    requestHandler.run();
    verify(parser, times(1)).parse(socket);
    verifyNoMoreInteractions(parser);
  }

}