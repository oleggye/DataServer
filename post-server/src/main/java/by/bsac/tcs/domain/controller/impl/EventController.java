package by.bsac.tcs.domain.controller.impl;

import by.bsac.tcs.domain.controller.Controller;
import by.bsac.tcs.domain.controller.command.Command;
import by.bsac.tcs.domain.controller.command.CommandException;
import by.bsac.tcs.domain.controller.command.CommandProvider;
import by.bsac.tcs.domain.controller.exception.ControllerException;
import by.bsac.tcs.server.model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventController implements Controller {

  private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

  private static final String EVENT_KEY_CONST = "event";
  private static final CommandProvider provider = CommandProvider.getInstance();

  @Override
  public void process(Request request) throws ControllerException {
    final String eventValue = request.getRequestParam(EVENT_KEY_CONST);

    final Command command = provider.getCommand(eventValue);
    try {
      command.execute(request);
    } catch (CommandException e) {
      final String message = "An error occurred while processing request";
      LOGGER.error(message);
      throw new ControllerException(message, e);
    }
  }
}
