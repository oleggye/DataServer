package by.bsac.tcs.controller;

import by.bsac.tcs.model.User;
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
    /*User user = new User(id, login);
    repository.save(user);*/
    return "saved!";
  }

  @RequestMapping(value = "/clients/{login}")
  public User getClientByLogin(@PathVariable String login) {
    User user = repository.findByLogin(login);
    return user;
  }
}
