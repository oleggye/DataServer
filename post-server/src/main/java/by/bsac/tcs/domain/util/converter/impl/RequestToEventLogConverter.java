package by.bsac.tcs.domain.util.converter.impl;

import by.bsac.tcs.domain.model.Event;
import by.bsac.tcs.domain.model.EventLog;
import by.bsac.tcs.domain.util.converter.RequestConverter;
import by.bsac.tcs.server.model.Request;

public class RequestToEventLogConverter implements RequestConverter {

  private static final String POST_BOX_ID_CONST = "postBoxId";
  private static final String EVENT_CODE_CONST = "code";
  private static final String STATE_VALUE_CONST = "state";

  public EventLog convert(Request request) {
    final String postBoxIdValue = request.getRequestParam(POST_BOX_ID_CONST);
    final long postBoxId = Long.parseLong(postBoxIdValue);

    final String eventCodeValue = request.getRequestParam(EVENT_CODE_CONST);
    final int eventCode = Integer.parseInt(eventCodeValue);
    final Event event = Event.getEvent(eventCode);

    final String state = request.getRequestParam(STATE_VALUE_CONST);

    return new EventLog(postBoxId, event, state);
  }
}
