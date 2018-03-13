package by.bsac.tcs.domain.controller.impl;

import by.bsac.tcs.domain.controller.RequestController;
import by.bsac.tcs.domain.controller.command.Command;
import by.bsac.tcs.domain.controller.command.CommandException;
import by.bsac.tcs.domain.controller.command.CommandProvider;
import by.bsac.tcs.domain.controller.exception.ControllerException;
import by.bsac.tcs.server.model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventRequestController implements RequestController {

  private static final Logger LOGGER = LoggerFactory.getLogger(EventRequestController.class);

  //private static final String EVENT_KEY_CONST = "event";
  private static final CommandProvider provider = CommandProvider.getInstance();

  //FIXME: this is used for simple implementation
  private static final String EVENT_HARDCODED_NAME = "LOG";

  @Override
  public void process(Request request) throws ControllerException {

    final Command command = provider.getCommand(EVENT_HARDCODED_NAME);
    try {
      command.execute(request);
    } catch (CommandException e) {
      final String message = "An error occurred while processing request";
      LOGGER.error(message);
      throw new ControllerException(message, e);
    }
  }
}
