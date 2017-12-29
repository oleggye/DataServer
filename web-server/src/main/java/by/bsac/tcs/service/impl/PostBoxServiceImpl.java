package by.bsac.tcs.service.impl;

import by.bsac.tcs.model.PostBox;
import by.bsac.tcs.service.PostBoxService;
import by.bsac.tcs.service.exception.PostBoxServiceException;
import by.bsac.tcs.service.exception.ServiceValidationException;
import org.springframework.stereotype.Service;

@Service
public class PostBoxServiceImpl implements PostBoxService {

  @Override
  public void registration(PostBox postBox)
      throws PostBoxServiceException, ServiceValidationException {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  public PostBox getPostBox(long id) throws PostBoxServiceException, ServiceValidationException {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  public void removePostBox(PostBox postBox)
      throws PostBoxServiceException, ServiceValidationException {
    throw new UnsupportedOperationException("Not implemented!");
  }
}
