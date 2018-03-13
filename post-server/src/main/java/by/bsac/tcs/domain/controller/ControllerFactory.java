package by.bsac.tcs.domain.controller;

import by.bsac.tcs.domain.controller.impl.EventRequestController;

public class ControllerFactory {

  private ControllerFactory() {
  }

  public static ControllerFactory getInstance() {
    return SingletonHolder.getInstance();
  }

  public RequestController getController() {
    return new EventRequestController();
  }

  private static class SingletonHolder {

    private static final ControllerFactory INSTANCE = new ControllerFactory();

    private SingletonHolder() {
    }

    public static ControllerFactory getInstance() {
      return INSTANCE;
    }
  }
}
