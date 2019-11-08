package uk.co.datadisk.rabbitmqconsumer.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

//@Service
public class PictureVectorConsumer {

  private ObjectMapper objectMapper = new ObjectMapper();

  private static final Logger log = LoggerFactory.getLogger(PictureVectorConsumer.class);

  @RabbitListener(queues = "q.picture.vector")
  public void listen(String message) {
    try {
      var p = objectMapper.readValue(message, Picture.class);
      log.info("On vector : {}", p.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}