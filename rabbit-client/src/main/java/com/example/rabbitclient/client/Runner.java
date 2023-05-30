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
    String[] food = {ConsoleColors.RED_BOLD + "Kebab - Paula 🥙", ConsoleColors.CYAN_BOLD + "Pizza - Paula 🍕", ConsoleColors.YELLOW_BOLD + "Pierogi - Paula 🥟", ConsoleColors.BLUE_BOLD + "Sushi - Paula🍱"};
    int i = 1;
    while (i < 6) {
      System.out.println("🛵 Dostawca w drodze...");
      int randomMilliseconds = random.nextInt(maxMilliseconds - minMilliseconds + 1) + minMilliseconds;
        rabbitTemplate.convertAndSend(RabbitClientApplication.topicExchangeName,
                "food", i +". "+ food[i%4] + ConsoleColors.RESET);
      Thread.sleep(randomMilliseconds);
      i++;
    }
  }

}