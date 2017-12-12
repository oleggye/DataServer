package by.bsac.tcs.domain.dao;

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
