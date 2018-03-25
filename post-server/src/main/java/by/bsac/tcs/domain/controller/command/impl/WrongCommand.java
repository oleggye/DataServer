package by.bsac.tcs.domain.controller.command.impl;

import by.bsac.tcs.domain.controller.command.Command;
import by.bsac.tcs.server.model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WrongCommand implements Command {

  private static final Logger LOGGER = LoggerFactory.getLogger(WrongCommand.class);

  @Override
  public void execute(Request request) {
    LOGGER.info("{} is executing...", this.getClass().getSimpleName());
    LOGGER.warn("Wrong command is executed by the request {}", request);
  }
}
