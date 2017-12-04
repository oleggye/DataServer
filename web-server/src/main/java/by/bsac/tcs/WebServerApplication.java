package by.bsac.tcs;

import by.bsac.tcs.model.Client;
import by.bsac.tcs.repository.ClientRepository;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@SpringBootApplication
public class WebServerApplication extends WebMvcConfigurerAdapter {
  @Autowired
  private ClientRepository clientRepository;

  public static void main(String[] args) {
    SpringApplication.run(WebServerApplication.class, args);
  }

  @Bean
  public Logger logger() {
    return LoggerFactory.getLogger("application");
  }

  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext ctx, Logger logger) {
    return args -> {
      logger.info("deleteAll");
      clientRepository.deleteAll();


      Client client = new Client("123","admin");
      logger.info("save client: " + client);
      clientRepository.save(client);

      Client admin = clientRepository.findByLogin("admin");
      logger.info("findByLogin admin: " + admin);


     /* String[] beanNames = ctx.getBeanDefinitionNames();
      Arrays.sort(beanNames);
      for (String beanName : beanNames) {
        logger.info(beanName);
      }*/
    };
  }

 /* @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/signIn").setViewName("signIn");
  }*/
}
