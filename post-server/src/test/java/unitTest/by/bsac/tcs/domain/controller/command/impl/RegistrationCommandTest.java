package by.bsac.tcs.domain.controller.command.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import by.bsac.tcs.domain.controller.command.CommandException;
import by.bsac.tcs.domain.model.EventLog;
import by.bsac.tcs.domain.service.exception.ServiceException;
import by.bsac.tcs.server.model.Method;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationCommandTest extends GenericCommandTest {

  private static final String EXCEPTED_MESSAGE = "REGISTERED";

  @InjectMocks
  private RegistrationCommand command;

  @Override
  protected void beforeTestExecuteSuccess() throws ServiceException {
    super.beforeTestExecuteSuccess();
    doNothing().when(eventService).register(any(EventLog.class));
    when(request.getMethod()).thenReturn(Method.REG);
  }

  @SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_NO_SIDE_EFFECT")
  @Override
  protected void afterTestExecuteSuccess() throws ServiceException {
    super.afterTestExecuteSuccess();
    verify(eventService).register(eventLog);
    verifyNoMoreInteractions(eventService);
    verify(request).setResponse(EXCEPTED_MESSAGE);
    verify(request).getMethod();
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
    doThrow(ServiceException.class).when(eventService).register(any(EventLog.class));
  }


  @Test(expected = CommandException.class)
  public void testExecuteFailsWhenServiceException() throws CommandException, ServiceException {
    beforeTestExecuteFailsWhenServiceException();
    command.execute(request);
  }
}