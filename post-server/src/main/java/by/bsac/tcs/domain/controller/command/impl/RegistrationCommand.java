package by.bsac.tcs.domain.controller.command.impl;

import by.bsac.tcs.domain.controller.command.Command;
import by.bsac.tcs.domain.controller.command.CommandException;
import by.bsac.tcs.domain.service.EventLogServiceFactory;
import by.bsac.tcs.server.model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationCommand implements Command {

  private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationCommand.class);

  private static final EventLogServiceFactory factory = EventLogServiceFactory.getInstance();

  @Override
  public void execute(Request request) throws CommandException {
    LOGGER.info("{} is executing...", this.getClass().getSimpleName());
    throw new UnsupportedOperationException("Method temporary is not implemented!");
  }
}
