package by.bsac.tcs.domain.controller.command.impl;

import by.bsac.tcs.domain.controller.command.Command;
import by.bsac.tcs.domain.controller.command.CommandException;
import by.bsac.tcs.domain.model.EventLog;
import by.bsac.tcs.domain.service.EventService;
import by.bsac.tcs.domain.service.EventServiceFactory;
import by.bsac.tcs.domain.service.exception.ServiceException;
import by.bsac.tcs.domain.util.converter.RequestConverter;
import by.bsac.tcs.domain.util.converter.RequestConverterFactory;
import by.bsac.tcs.server.model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogCommand implements Command {

  private static final Logger LOGGER = LoggerFactory.getLogger(LogCommand.class);

  private EventService logService;
  private RequestConverter<EventLog> requestConverter;

  public LogCommand() {
    this.logService = EventServiceFactory.getInstance().getEventService();
    this.requestConverter = RequestConverterFactory.getInstance().getConverter(EventLog.class);
  }

  public LogCommand(EventService logService,
      RequestConverter<EventLog> requestConverter) {
    this.logService = logService;
    this.requestConverter = requestConverter;
  }

  @Override
  public void execute(Request request) throws CommandException {
    LOGGER.info("{} is executing...", this.getClass().getSimpleName());

    final EventLog log = requestConverter.convert(request);

    try {
      logService.log(log);
    } catch (ServiceException e) {
      final String message = String.format("An error occurred while log event %s", log);
      LOGGER.error(message);
      throw new CommandException(message, e);
    }
  }
}
