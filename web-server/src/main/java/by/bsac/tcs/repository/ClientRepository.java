package by.bsac.tcs.repository;

import by.bsac.tcs.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

  public Client findByLogin(String login);
}
