package by.bsac.tcs.domain.controller.command.impl;

import by.bsac.tcs.domain.controller.command.Command;
import by.bsac.tcs.server.model.Request;
import by.bsac.tcs.server.process.parser.impl.Method;
import by.bsac.tcs.server.util.loader.ProtocolPropertiesLoader;

abstract class ResponseCommand implements Command {

  private static final ProtocolPropertiesLoader PROPERTIES_LOADER =
      ProtocolPropertiesLoader.getInstance();

  void writeResponse(final Request request) {
    final Method method = request.getMethod();
    final String response = PROPERTIES_LOADER.getMethodsResponseProperty(method);

    request.setResponse(response);
  }

}
