package by.bsac.tcs.controller;

import by.bsac.tcs.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriptionController {

  @Autowired
  private SubscriptionService subscriptionService;

  @RequestMapping(value = "/subsciption", method = RequestMethod.GET)
  public List<Subscription> getSub
}
