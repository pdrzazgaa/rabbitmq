package com.example.rabbitclient.client;

import com.example.rabbitclient.RabbitClientApplication;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Runner implements CommandLineRunner {

  private final RabbitTemplate rabbitTemplate;

  public Runner( RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @Override
  public void run(String... args) throws Exception {
    Random random = new Random();
    int maxMilliseconds = 4000;
    int minMilliseconds = 1000;
    String[] food = {ConsoleColors.RED_BOLD + "Kebab 🥙", ConsoleColors.CYAN_BOLD + "Pizza 🍕", ConsoleColors.YELLOW_BOLD + "Pierogi 🥟", ConsoleColors.BLUE_BOLD + "Sushi 🍱"};
    int i = 0;
    while (i < 5) {
      System.out.println("🛵 Dostawca w drodze...");
      int randomMilliseconds = random.nextInt(maxMilliseconds - minMilliseconds + 1) + minMilliseconds;
        rabbitTemplate.convertAndSend(RabbitClientApplication.topicExchangeName,
                "food", food[i%4] + ConsoleColors.RESET);
      Thread.sleep(randomMilliseconds);
      i++;
    }
  }

}