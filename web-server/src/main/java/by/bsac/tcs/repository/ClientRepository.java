package by.bsac.tcs.repository;

import by.bsac.tcs.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, String> {

  Client findByLogin(String login);
}
