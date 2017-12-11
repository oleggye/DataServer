package by.bsac.tcs.logic.controller;

import by.bsac.tcs.logic.controller.exception.ControllerException;
import by.bsac.tcs.server.model.Request;

public interface Controller {

  void process(Request request) throws ControllerException;
}
