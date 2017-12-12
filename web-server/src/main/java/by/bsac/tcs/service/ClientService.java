package by.bsac.tcs.service;

import by.bsac.tcs.model.Client;
import by.bsac.tcs.service.exception.ClientServiceException;
import by.bsac.tcs.service.exception.ServiceValidationException;

public interface ClientService {

  void singUp(Client client) throws ClientServiceException, ServiceValidationException;

  void signIn(Client client) throws ClientServiceException, ServiceValidationException;

  void signOut() throws ClientServiceException;
}
