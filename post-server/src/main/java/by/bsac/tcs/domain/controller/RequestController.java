package by.bsac.tcs.domain.controller;

import by.bsac.tcs.domain.controller.exception.ControllerException;
import by.bsac.tcs.server.model.Request;

public interface RequestController {

  void process(Request request) throws ControllerException;
}
