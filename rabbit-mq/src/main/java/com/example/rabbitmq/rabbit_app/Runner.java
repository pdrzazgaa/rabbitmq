package com.example.rabbitmq.rabbit_app;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.example.rabbitmq.RabbitMqApplication;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

  private final RabbitTemplate rabbitTemplate;
  private final Receiver receiver;

  public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
    this.receiver = receiver;
    this.rabbitTemplate = rabbitTemplate;
  }

  @Override
  public void run(String... args) throws Exception {
    Random random = new Random();
    int maxMilliseconds = 3000;
    int minMilliseconds = 500;
    String[] food = {ConsoleColors.RED_BOLD + "Kebab 🥙", ConsoleColors.CYAN_BOLD + "Pizza 🍕", ConsoleColors.YELLOW_BOLD + "Pierogi 🥟", ConsoleColors.BLUE_BOLD + "Sushi 🍱"};
    int i = 0;
    while (i < 20) {
      System.out.println("🛵 Dostawca w drodze...");
      int randomMilliseconds = random.nextInt(maxMilliseconds - minMilliseconds + 1) + minMilliseconds;
        rabbitTemplate.convertAndSend(RabbitMqApplication.topicExchangeName,
                "food", food[i % 4] + ConsoleColors.RESET);
      receiver.getLatch().await(1000, TimeUnit.MILLISECONDS);
      Thread.sleep(randomMilliseconds);
      i++;
    }
  }

}