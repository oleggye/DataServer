package by.bsac.tcs.service;

import by.bsac.tcs.model.PostBox;
import by.bsac.tcs.service.exception.PostBoxServiceException;
import by.bsac.tcs.service.exception.ServiceValidationException;

public interface PostBoxService {

  void registration(PostBox postBox)
      throws PostBoxServiceException, ServiceValidationException;

  PostBox fetchPostBox(long id) throws PostBoxServiceException, ServiceValidationException;

  void removePostBox(PostBox postBox) throws PostBoxServiceException, ServiceValidationException;
}
