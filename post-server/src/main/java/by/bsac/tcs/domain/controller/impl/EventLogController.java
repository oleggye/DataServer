package by.bsac.tcs.domain.controller.impl;

import by.bsac.tcs.domain.controller.Controller;
import by.bsac.tcs.domain.controller.exception.ControllerException;
import by.bsac.tcs.domain.service.EventLogService;
import by.bsac.tcs.domain.service.EventLogServiceFactory;
import by.bsac.tcs.server.model.Request;

public class EventLogController implements Controller {

  public EventLogServiceFactory factory = EventLogServiceFactory.getInstance();

  @Override
  public void process(Request request) throws ControllerException {
    EventLogService service = factory.getEventLogService();

    //TODO: impl builder which construct PostBox entity using request object
  }
}
