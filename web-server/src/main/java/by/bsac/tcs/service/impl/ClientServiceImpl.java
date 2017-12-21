package by.bsac.tcs.service.impl;

import by.bsac.tcs.model.User;
import by.bsac.tcs.service.ClientService;
import by.bsac.tcs.service.exception.ClientServiceException;
import by.bsac.tcs.service.exception.ServiceValidationException;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

  @Override
  public void singUp(User user) throws ClientServiceException, ServiceValidationException {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  public void signIn(User user) throws ClientServiceException, ServiceValidationException {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  public void signOut() throws ClientServiceException {
    throw new UnsupportedOperationException("Not implemented!");
  }
}
