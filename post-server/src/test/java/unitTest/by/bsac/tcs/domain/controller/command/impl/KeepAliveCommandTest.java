package by.bsac.tcs.domain.controller.command.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import by.bsac.tcs.domain.controller.command.CommandException;
import by.bsac.tcs.domain.model.EventLog;
import by.bsac.tcs.domain.service.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class KeepAliveCommandTest extends GenericCommandTest {

  @InjectMocks
  private KeepAliveCommand command;

  @Override
  protected void beforeTestExecuteSuccess() throws ServiceException {
    super.beforeTestExecuteSuccess();
    doNothing().when(eventService).keepAlive(any(EventLog.class));
  }

  @Override
  protected void afterTestExecuteSuccess() throws ServiceException {
    super.afterTestExecuteSuccess();
    verify(eventService).keepAlive(eventLog);
    verifyNoMoreInteractions(eventService);
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
    doThrow(ServiceException.class).when(eventService).keepAlive(any(EventLog.class));
  }

  @Test(expected = CommandException.class)
  public void testExecuteFailsWhenServiceException() throws CommandException, ServiceException {
    beforeTestExecuteFailsWhenServiceException();
    command.execute(request);
  }
}