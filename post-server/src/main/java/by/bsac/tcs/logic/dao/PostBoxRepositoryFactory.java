package by.bsac.tcs.logic.dao;

import by.bsac.tcs.logic.service.PostBoxService;
import by.bsac.tcs.logic.service.impl.PostBoxServiceImpl;

public class PostBoxRepositoryFactory {

  private PostBoxRepositoryFactory() {
  }

  private static final PostBoxRepositoryFactory INSTANCE = new PostBoxRepositoryFactory();

  private static final PostBoxService service = new PostBoxServiceImpl();

  public PostBoxRepositoryFactory getInstance() {
    return INSTANCE;
  }

  public PostBoxService getService() {
    return service;
  }
}
