package by.bsac.tcs.service.impl;

import by.bsac.tcs.model.Client;
import by.bsac.tcs.model.PostBox;
import by.bsac.tcs.service.SubscriptionService;
import by.bsac.tcs.service.exception.ServiceValidationException;
import by.bsac.tcs.service.exception.SubscriptionServiceException;

public class SubscriptionServiceImpl implements SubscriptionService {

  @Override
  public void linkClientToPostBox(Client client, PostBox postBox)
      throws SubscriptionServiceException, ServiceValidationException {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  public void unlinkClientToPostBox(Client client, PostBox postBox)
      throws SubscriptionServiceException, ServiceValidationException {
    throw new UnsupportedOperationException("Not implemented!");
  }
}
