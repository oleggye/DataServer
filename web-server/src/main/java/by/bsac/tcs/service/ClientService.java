package by.bsac.tcs.service;

import by.bsac.tcs.model.User;
import by.bsac.tcs.service.exception.ClientServiceException;
import by.bsac.tcs.service.exception.ServiceValidationException;

public interface ClientService {

  void singUp(User user) throws ClientServiceException, ServiceValidationException;

  void signIn(User user) throws ClientServiceException, ServiceValidationException;

  void signOut() throws ClientServiceException;
}
