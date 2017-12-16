package by.bsac.tcs.domain.controller.command.impl;

import by.bsac.tcs.domain.controller.command.Command;
import by.bsac.tcs.domain.controller.command.CommandException;
import by.bsac.tcs.domain.model.EventLog;
import by.bsac.tcs.domain.service.EventLogService;
import by.bsac.tcs.domain.service.EventLogServiceFactory;
import by.bsac.tcs.domain.service.exception.ServiceException;
import by.bsac.tcs.domain.util.converter.RequestConverter;
import by.bsac.tcs.domain.util.converter.RequestConverterFactory;
import by.bsac.tcs.server.model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogCommand implements Command {

  private static final Logger LOGGER = LoggerFactory.getLogger(LogCommand.class);

  private static final EventLogServiceFactory eventLogServiceFactory = EventLogServiceFactory
      .getInstance();
  private static final RequestConverterFactory requestConverterFactory = RequestConverterFactory
      .getInstance();

  @Override
  public void execute(Request request) throws CommandException {
    LOGGER.info("{} is executing...", this.getClass().getSimpleName());

    final EventLogService logService = eventLogServiceFactory.getEventLogService();
    final RequestConverter<EventLog> converter = requestConverterFactory
        .getConverter(EventLog.class);
    final EventLog log = converter.convert(request);

    try {
      logService.log(log);
    } catch (ServiceException e) {
      final String message = String.format("An error occurred while log event %s", log);
      LOGGER.error(message);
      throw new CommandException(message, e);
    }
  }
}
