package com.qeatsbackendfile.qeats;

import com.qeatsbackendfile.qeats.globals.GlobalConstants;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class QEatsApplication {

  private static final Logger log = LoggerFactory.getLogger(QEatsApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(QEatsApplication.class, args);

    // If your server starts successfully,
    // you can find the following message in the logs.
    log.info("Congrats! Your QEatsApplication server has started");
  }

  /**
   * Fetches a ModelMapper instance.
   *
   * @return ModelMapper
   */
  @Bean // Want a new obj every time
  @Scope("prototype")
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

}
