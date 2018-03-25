package by.bsac.tcs.domain.controller.command.impl;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import by.bsac.tcs.domain.controller.command.CommandException;
import by.bsac.tcs.domain.model.EventLog;
import by.bsac.tcs.domain.service.EventService;
import by.bsac.tcs.domain.service.exception.ServiceException;
import by.bsac.tcs.domain.util.converter.RequestConverter;
import by.bsac.tcs.server.model.Request;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LogCommandTest {

  @InjectMocks
  private LogCommand logCommand;

  @Mock
  private EventService eventService;
  @Mock
  private RequestConverter<EventLog> requestConverter;
  @Mock
  private Request request;

  @Test
  public void testExecute() throws ServiceException, CommandException {
    final EventLog eventLog = mock(EventLog.class);
    when(requestConverter.convert(any(Request.class))).thenReturn(eventLog);

    logCommand.execute(request);

    verify(requestConverter, times(1))
        .convert(request);
    verifyNoMoreInteractions(requestConverter);

    verify(eventService, times(1)).log(eventLog);
    verifyNoMoreInteractions(eventService);
  }

}