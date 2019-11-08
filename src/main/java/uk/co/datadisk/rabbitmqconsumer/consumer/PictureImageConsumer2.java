package uk.co.datadisk.rabbitmqconsumer.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

//@Service
public class PictureImageConsumer2 {

  private ObjectMapper objectMapper = new ObjectMapper();

  private static final Logger log = LoggerFactory.getLogger(PictureImageConsumer2.class);

  @RabbitListener(queues = "q.picture.image2")
  public void listen(String message) {
    try {
      var p = objectMapper.readValue(message, Picture.class);
      log.info("On image : {}", p.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}