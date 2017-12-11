package by.bsac.tcs.logic.service;

import by.bsac.tcs.logic.service.impl.PostBoxServiceImpl;

public class PostBoxServiceFactory {

  private PostBoxServiceFactory() {
  }

  private static final PostBoxServiceFactory INSTANCE = new PostBoxServiceFactory();
  private static final PostBoxService service = new PostBoxServiceImpl();

  public static PostBoxServiceFactory getInstance() {
    return INSTANCE;
  }

  public PostBoxService getPostBoxService() {
    return service;
  }
}
