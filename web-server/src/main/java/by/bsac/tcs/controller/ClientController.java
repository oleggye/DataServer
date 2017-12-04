package by.bsac.tcs.controller;

import by.bsac.tcs.model.Client;
import by.bsac.tcs.repository.ClientRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
  @Autowired
  private Logger logger;

  @Autowired
  private ClientRepository repository;

  @RequestMapping("/")
  public String getDeviceStatus() {
    return "OK!";
  }

  @RequestMapping(value = "/clients", method = RequestMethod.POST)
  public String addNewClient(@RequestParam String id, @RequestParam String login) {
    Client client = new Client(id, login);
    repository.save(client);
    return "saved!";
  }

  @RequestMapping(value = "/clients/{login}")
  public Client getClientByLogin(@PathVariable String login) {
    Client client = repository.findByLogin(login);
    return client;
  }
}
