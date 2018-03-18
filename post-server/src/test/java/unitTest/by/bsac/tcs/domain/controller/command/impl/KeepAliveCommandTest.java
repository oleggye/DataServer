package by.bsac.tcs.domain.controller.command.impl;

import by.bsac.tcs.domain.controller.command.CommandException;
import by.bsac.tcs.domain.service.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class KeepAliveCommandTest extends GenericCommandTest {

  @InjectMocks
  private KeepAliveCommand command;

  @Test
  public void testExecuteSuccess() throws CommandException, ServiceException {
    beforeTestExecuteSuccess();
    command.execute(request);
    afterTestExecuteSuccess();
  }

  @Test(expected = CommandException.class)
  public void testExecuteFailsWhenServiceException() throws CommandException, ServiceException {
    beforeTestExecuteFailsWhenServiceException();
    command.execute(request);
  }
}