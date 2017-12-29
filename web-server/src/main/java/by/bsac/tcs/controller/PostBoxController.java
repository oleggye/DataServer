package by.bsac.tcs.controller;

import by.bsac.tcs.model.PostBox;
import by.bsac.tcs.service.PostBoxService;
import by.bsac.tcs.service.exception.PostBoxServiceException;
import by.bsac.tcs.service.exception.ServiceValidationException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostBoxController {

  @Autowired
  private Logger logger;

  @Autowired
  private PostBoxService postBoxService;

  @RequestMapping(value = "/postbox/{id}", method = RequestMethod.GET)
  public PostBox getPostBoxById(@PathVariable long id)
      throws PostBoxServiceException, ServiceValidationException {
    logger.info("getPostBoxById {}", id);
    return postBoxService.getPostBox(id);
  }

  @RequestMapping(value = "/postbox/registration", method = RequestMethod.POST)
  public void registerPostBox(PostBox postBox)
      throws PostBoxServiceException, ServiceValidationException {
    logger.info("getPostBoxById {}", postBox);
    postBoxService.registration(postBox);
  }
}
