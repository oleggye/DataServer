package by.bsac.tcs.domain.controller.command.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import by.bsac.tcs.domain.controller.command.CommandException;
import by.bsac.tcs.domain.model.EventLog;
import by.bsac.tcs.domain.service.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HasClosedCommandTest extends GenericCommandTest {

  private static final String EXCEPTED_MESSAGE = "OPENED!";

  @InjectMocks
  private HasClosedCommand command;

  @Override
  protected void beforeTestExecuteSuccess() throws ServiceException {
    super.beforeTestExecuteSuccess();
    when(eventService.closed(any(EventLog.class))).thenReturn(EXCEPTED_MESSAGE);
  }

  @Override
  protected void afterTestExecuteSuccess() throws ServiceException {
    super.afterTestExecuteSuccess();
    verify(eventService).closed(eventLog);
    verifyNoMoreInteractions(eventService);
    verify(request).setResponse(EXCEPTED_MESSAGE);
    verifyNoMoreInteractions(request);
  }

  @Test
  public void testExecuteSuccess() throws CommandException, ServiceException {
    beforeTestExecuteSuccess();
    command.execute(request);
    afterTestExecuteSuccess();
  }

  @Override
  protected void beforeTestExecuteFailsWhenServiceException() throws ServiceException {
    super.beforeTestExecuteFailsWhenServiceException();
    when(eventService.closed(any(EventLog.class))).thenThrow(ServiceException.class);
  }

  @Test(expected = CommandException.class)
  public void testExecuteFailsWhenServiceException() throws CommandException, ServiceException {
    beforeTestExecuteFailsWhenServiceException();
    command.execute(request);
  }
}