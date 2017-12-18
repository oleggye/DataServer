package by.bsac.tcs.service;

import by.bsac.tcs.model.User;
import by.bsac.tcs.model.PostBox;
import by.bsac.tcs.service.exception.ServiceValidationException;
import by.bsac.tcs.service.exception.SubscriptionServiceException;

public interface SubscriptionService {

  void linkClientToPostBox(User user, PostBox postBox)
      throws SubscriptionServiceException, ServiceValidationException;

  void unlinkClientToPostBox(User user, PostBox postBox)
      throws SubscriptionServiceException, ServiceValidationException;
}
