package by.bsac.tcs.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

  @RequestMapping("/")
  public String getDeviceStatus(){
    return "OK!";
  }
}
