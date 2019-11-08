package uk.co.datadisk.rabbitmqconsumer.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

//@Service
public class MyPictureImageConsumer {

  private ObjectMapper objectMapper = new ObjectMapper();

  private static final Logger log = LoggerFactory.getLogger(MyPictureImageConsumer.class);

  @RabbitListener(queues = "q.mypicture.image")
  public void listen(Message message, Channel channel) {
    try {
      var p = objectMapper.readValue(message.getBody(), Picture.class);

      if(p.getSize() > 4000 ){
        channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
      }
      log.info("On image : {}", p.toString());
      channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}