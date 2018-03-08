package by.bsac.tcs.server.process.handler.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import by.bsac.tcs.domain.controller.Controller;
import by.bsac.tcs.domain.controller.exception.ControllerException;
import by.bsac.tcs.server.model.Request;
import by.bsac.tcs.server.process.handler.exception.RequestHandlerException;
import by.bsac.tcs.server.process.parser.ProtocolParser;
import by.bsac.tcs.server.process.parser.exception.ProtocolParseException;
import java.net.Socket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RequestHandlerImplTest {

  @InjectMocks
  private RequestHandlerImpl requestHandler;

  @Mock
  private Socket socket;

  @Mock
  private ProtocolParser parser;

  @Mock
  private Controller controller;

 /* @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }*/

  @Test
  public void runSuccessfully() throws Exception {
    final Request request = mock(Request.class);
    when(parser.parse(socket)).thenReturn(request);

    requestHandler.run();

    verify(parser, times(1)).parse(socket);
    verifyNoMoreInteractions(parser);
    verify(controller, times(1)).process(request);
    verifyNoMoreInteractions(controller);
  }

  @Test(expected = RequestHandlerException.class)
  public void runWithExceptionWhenParse() throws Exception {
    when(parser.parse(socket)).thenThrow(ProtocolParseException.class);

    requestHandler.run();
  }

  @Test(expected = RequestHandlerException.class)
  public void runWithExceptionWhenControllerProcess() throws Exception {
    final Request request = mock(Request.class);
    when(parser.parse(socket)).thenReturn(request);
    doThrow(ControllerException.class).when(controller).process(any(Request.class));

    requestHandler.run();
  }
}