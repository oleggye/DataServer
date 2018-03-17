package by.bsac.tcs.domain.controller.coommand.impl;

import static org.mockito.Mockito.spy;

import by.bsac.tcs.domain.controller.command.impl.LogCommand;
import by.bsac.tcs.domain.model.EventLog;
import by.bsac.tcs.domain.service.EventService;
import by.bsac.tcs.domain.service.EventServiceFactory;
import by.bsac.tcs.domain.util.converter.RequestConverter;
import by.bsac.tcs.domain.util.converter.RequestConverterFactory;
import by.bsac.tcs.server.model.Request;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LogCommandTest {

  private static final String POST_BOX_ID_CONST = "postBoxId";
  private static final String EVENT_CODE_CONST = "code";
  private static final String STATE_VALUE_CONST = "quantity";

  private LogCommand logCommand;

  private EventService spyLogService;
  private RequestConverter<EventLog> spyRequestConverter;

  @Mock
  private Request request;

  @Before
  public void setUpLogCommand() {
    final EventService logService = EventServiceFactory.getInstance().getEventService();
    spyLogService = spy(logService);

    final RequestConverter<EventLog> converter = RequestConverterFactory.getInstance()
        .getConverter(EventLog.class);
    spyRequestConverter = spy(converter);

    logCommand = new LogCommand(spyLogService, spyRequestConverter);
  }

  @Test
  public void dumpTest() {

  }

  /*@Before
  public void setUpRequest() {
    request = new Request();
    request.setRequestParam(POST_BOX_ID_CONST, "123");
    request.setRequestParam(EVENT_CODE_CONST, "6");
    request.setRequestParam(STATE_VALUE_CONST, "6 envelopes");
  }

  @Test
  public void testExecute() throws Exception {

    final EventLog eventLog = mock(EventLog.class);
    when(spyRequestConverter.convert(request)).thenReturn(eventLog);

    logCommand.execute(request);

    verify(spyRequestConverter, times(1))
        .convert(request);
    verifyNoMoreInteractions(spyRequestConverter);

    verify(this.spyLogService, times(1)).log(eventLog);
    verifyNoMoreInteractions(this.spyLogService);
  }*/

}