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
public class QuantityChangedCommandTest extends GenericCommandTest {

  private static final String EXCEPTED_MESSAGE = "CHANGED!";

  @InjectMocks
  private QuantityChangedCommand command;

  @Override
  protected void beforeTestExecuteSuccess() throws ServiceException {
    super.beforeTestExecuteSuccess();
    when(eventService.changed(any(EventLog.class))).thenReturn(EXCEPTED_MESSAGE);
  }

  @Override
  protected void afterTestExecuteSuccess() throws ServiceException {
    super.afterTestExecuteSuccess();
    verify(eventService).changed(eventLog);
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
    when(eventService.changed(any(EventLog.class))).thenThrow(ServiceException.class);
  }

  @Test(expected = CommandException.class)
  public void testExecuteFailsWhenServiceException() throws CommandException, ServiceException {
    beforeTestExecuteFailsWhenServiceException();
    command.execute(request);
  }
}