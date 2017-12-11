package by.bsac.tcs.logic.controller.impl;

import by.bsac.tcs.logic.controller.Controller;
import by.bsac.tcs.logic.controller.exception.ControllerException;
import by.bsac.tcs.logic.service.PostBoxService;
import by.bsac.tcs.logic.service.PostBoxServiceFactory;
import by.bsac.tcs.server.model.Request;

public class PostBoxController implements Controller {

  public PostBoxServiceFactory factory = PostBoxServiceFactory.getInstance();

  @Override
  public void process(Request request) throws ControllerException {
    PostBoxService postBoxService = factory.getPostBoxService();

    //TODO: impl builder which construct PostBox entity using request object
  }
}
