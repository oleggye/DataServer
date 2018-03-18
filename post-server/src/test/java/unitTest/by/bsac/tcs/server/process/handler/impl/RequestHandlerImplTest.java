package by.bsac.tcs.server.process.handler.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import by.bsac.tcs.domain.controller.RequestController;
import by.bsac.tcs.domain.controller.exception.ControllerException;
import by.bsac.tcs.server.model.Request;
import by.bsac.tcs.server.process.handler.exception.RequestHandlerException;
import by.bsac.tcs.server.process.parser.ProtocolParser;
import by.bsac.tcs.server.process.parser.exception.ProtocolParseException;
import by.bsac.tcs.server.process.response.ResponseWriter;
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
  private RequestController requestController;

  @Mock
  private ResponseWriter responseWriter;

  @Test
  public void runSuccessfully() throws Exception {
    final Request request = mock(Request.class);
    when(parser.parse(socket)).thenReturn(request);
    doNothing().when(responseWriter).write(socket, request);

    requestHandler.run();

    verify(parser).parse(socket);
    verifyNoMoreInteractions(parser);
    verify(requestController).process(request);
    verifyNoMoreInteractions(requestController);
    verify(responseWriter).write(socket, request);
    verifyNoMoreInteractions(responseWriter);
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
    doThrow(ControllerException.class).when(requestController).process(any(Request.class));

    requestHandler.run();
  }
}