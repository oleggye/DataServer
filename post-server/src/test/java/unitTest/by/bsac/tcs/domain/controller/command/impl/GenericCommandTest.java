package by.bsac.tcs.domain.controller.command.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import by.bsac.tcs.domain.model.EventLog;
import by.bsac.tcs.domain.service.EventService;
import by.bsac.tcs.domain.service.exception.ServiceException;
import by.bsac.tcs.domain.util.converter.RequestConverter;
import by.bsac.tcs.server.model.Request;
import org.mockito.Mock;

public abstract class GenericCommandTest {

  @Mock
  protected EventService eventService;
  @Mock
  protected RequestConverter<EventLog> requestConverter;

  @Mock
  protected Request request;

  @Mock
  protected EventLog eventLog;

  protected void beforeTestExecuteSuccess() throws ServiceException {
    when(requestConverter.convert(request)).thenReturn(eventLog);
  }

  protected void afterTestExecuteSuccess() throws ServiceException {
    verify(requestConverter).convert(request);
    verifyNoMoreInteractions(requestConverter);
  }

  protected void beforeTestExecuteFailsWhenServiceException() throws ServiceException {
    when(requestConverter.convert(request)).thenReturn(eventLog);
  }
}
