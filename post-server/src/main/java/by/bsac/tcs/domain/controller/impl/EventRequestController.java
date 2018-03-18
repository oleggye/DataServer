package by.bsac.tcs.domain.controller.impl;

import by.bsac.tcs.domain.controller.RequestController;
import by.bsac.tcs.domain.controller.command.Command;
import by.bsac.tcs.domain.controller.command.CommandException;
import by.bsac.tcs.domain.controller.command.CommandProvider;
import by.bsac.tcs.domain.controller.exception.ControllerException;
import by.bsac.tcs.server.model.Request;
import by.bsac.tcs.server.process.parser.impl.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventRequestController implements RequestController {

  private static final Logger LOGGER = LoggerFactory.getLogger(EventRequestController.class);

  private final CommandProvider provider;

  public EventRequestController() {
    provider = CommandProvider.getInstance();
  }

  public EventRequestController(CommandProvider provider) {
    this.provider = provider;
  }

  @Override
  public void process(Request request) throws ControllerException {
    Method method = request.getMethod();
    final Command command = provider.getCommand(method);
    try {
      command.execute(request);
    } catch (CommandException e) {
      final String message = "An error occurred while processing request";
      LOGGER.error(message);
      throw new ControllerException(message, e);
    }
  }
}
