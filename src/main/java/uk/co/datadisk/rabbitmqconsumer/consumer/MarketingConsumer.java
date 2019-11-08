package uk.co.datadisk.rabbitmqconsumer.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

//@Service
public class MarketingConsumer {

  private ObjectMapper objectMapper = new ObjectMapper();

  private static final Logger log = LoggerFactory.getLogger(MarketingConsumer.class);

  @RabbitListener(queues = "q-hr.marketing")
  public void listen(String message){
    try {
      var emp = objectMapper.readValue(message, Employee.class);
      log.info("Marketing - Employee is {}" , emp);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
