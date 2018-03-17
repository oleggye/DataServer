package by.bsac.tcs.domain.controller.command.impl;

import by.bsac.tcs.domain.controller.command.CommandException;
import by.bsac.tcs.domain.model.EventLog;
import by.bsac.tcs.domain.service.EventService;
import by.bsac.tcs.domain.service.EventServiceFactory;
import by.bsac.tcs.domain.util.converter.RequestConverter;
import by.bsac.tcs.domain.util.converter.RequestConverterFactory;
import by.bsac.tcs.server.model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HasOpenedCommand implements by.bsac.tcs.domain.controller.command.Command {

  private static final Logger LOGGER = LoggerFactory.getLogger(HasOpenedCommand.class);

  private final EventService logService;
  private final RequestConverter<EventLog> requestConverter;

  public HasOpenedCommand() {
    this.logService = EventServiceFactory.getInstance().getEventService();
    this.requestConverter = RequestConverterFactory.getInstance().getConverter(EventLog.class);
  }

  public HasOpenedCommand(EventService logService,
      RequestConverter<EventLog> requestConverter) {
    this.logService = logService;
    this.requestConverter = requestConverter;
  }

  @Override
  public void execute(Request request) throws CommandException {
    LOGGER.info("{} is executing...", this.getClass().getSimpleName());
    throw new UnsupportedOperationException("Method temporary is not implemented!");
  }
}
