package by.bsac.tcs.domain.controller.impl;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import by.bsac.tcs.domain.controller.command.Command;
import by.bsac.tcs.domain.controller.command.CommandException;
import by.bsac.tcs.domain.controller.command.CommandProvider;
import by.bsac.tcs.domain.controller.exception.ControllerException;
import by.bsac.tcs.server.model.Request;
import by.bsac.tcs.server.process.parser.impl.Method;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EventRequestControllerTest {

  @InjectMocks
  private EventRequestController controller;

  @Mock
  private CommandProvider provider;

  @Mock
  private Command command;

  @Mock
  private Request request;

  @SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_NO_SIDE_EFFECT")
  @Test
  public void testProcessSuccess() throws ControllerException, CommandException {
    final Method method = Method.EMPTY;

    when(request.getMethod()).thenReturn(method);
    when(provider.getCommand(any(Method.class))).thenReturn(command);

    controller.process(request);

    verify(request).getMethod();
    verifyNoMoreInteractions(request);
    verify(provider).getCommand(method);
    verifyNoMoreInteractions(provider);
    verify(command).execute(request);
    verifyNoMoreInteractions(command);
  }

  @Test(expected = ControllerException.class)
  public void testProcessFailsWhenCommandException() throws ControllerException, CommandException {
    final Method method = Method.EMPTY;

    when(request.getMethod()).thenReturn(method);
    when(provider.getCommand(any(Method.class))).thenReturn(command);
    doThrow(CommandException.class).when(command).execute(any(Request.class));

    controller.process(request);
  }
}