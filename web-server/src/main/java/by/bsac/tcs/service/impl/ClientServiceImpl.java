package by.bsac.tcs.service.impl;

import by.bsac.tcs.model.Client;
import by.bsac.tcs.service.ClientService;
import by.bsac.tcs.service.exception.ClientServiceException;
import by.bsac.tcs.service.exception.ServiceValidationException;

public class ClientServiceImpl implements ClientService {

  @Override
  public void singUp(Client client) throws ClientServiceException, ServiceValidationException {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  public void signIn(Client client) throws ClientServiceException, ServiceValidationException {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  public void signOut() throws ClientServiceException {
    throw new UnsupportedOperationException("Not implemented!");
  }
}
