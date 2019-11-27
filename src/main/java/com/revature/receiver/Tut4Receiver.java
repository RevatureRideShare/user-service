package com.revature.receiver;

import static com.revature.util.LoggerUtil.info;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

/**
 * Receives and sends messages to allow for communication between any micro-services. Listens for
 * messages from the queues specified. Where messages are sent from within this AMQ is determined by
 * the destination defined in the body of the message. Messages should begin with their destination
 * followed by a ! Example: UserService!... Each micro-service should listen for messages for that
 * destination (HTTP Response for that micro-service). Example: "/UserService".
 * 
 * @author ErikHaklar
 */
public class Tut4Receiver {

  /**
   * Listens for messages inside autoDeleteQueue1, then calls receive() passing in the message from
   * that queue.
   */
  @RabbitListener(queues = "#{autoDeleteQueue1.name}")
  public void receive1(String in) throws InterruptedException {
    receive(in, 1);
  }

  /**
   * Listens for messages inside autoDeleteQueue2, then calls receive() passing in the message from
   * that queue.
   */
  @RabbitListener(queues = "#{autoDeleteQueue2.name}")
  public void receive2(String in) throws InterruptedException {
    receive(in, 2);
  }

  /**
   * Receives messages from the RabbitMQ queue automatically. There should not be a call to this
   * method except for by a RabbitListener method.
   */
  @RabbitHandler
  public void receive(String in, int receiver) throws InterruptedException {
    StopWatch watch = new StopWatch();
    watch.start();
    info("instance " + receiver + " [x] Received '" + in + "'");
    doWork(in);
    watch.stop();
    info("instance " + receiver + " [x] Done in " + watch.getTotalTimeSeconds() + "s");
  }

  private void doWork(String in) throws InterruptedException {
    for (char ch : in.toCharArray()) {
      if (ch == '.') {
        Thread.sleep(1000);
      }
    }
  }
}
