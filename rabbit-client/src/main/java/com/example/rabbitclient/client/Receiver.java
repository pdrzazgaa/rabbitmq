package com.example.rabbitclient.client;

import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

  private CountDownLatch latch = new CountDownLatch(1);

  public void receiveMessage(String message) {
    System.out.println(ConsoleColors.GREEN_BOLD + "Otrzymano jedzonko: " + message + ConsoleColors.RESET);
    latch.countDown();
  }

  public CountDownLatch getLatch() {
    return latch;
  }

}