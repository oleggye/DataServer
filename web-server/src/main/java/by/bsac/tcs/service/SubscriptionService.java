package by.bsac.tcs.service;

import by.bsac.tcs.model.Client;
import by.bsac.tcs.model.PostBox;
import by.bsac.tcs.service.exception.ServiceValidationException;
import by.bsac.tcs.service.exception.SubscriptionServiceException;

public interface SubscriptionService {

  void linkClientToPostBox(Client client, PostBox postBox)
      throws SubscriptionServiceException, ServiceValidationException;

  void unlinkClientToPostBox(Client client, PostBox postBox)
      throws SubscriptionServiceException, ServiceValidationException;
}
