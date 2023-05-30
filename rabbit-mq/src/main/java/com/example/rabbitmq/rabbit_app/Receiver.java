package com.example.rabbitmq.rabbit_app;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

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