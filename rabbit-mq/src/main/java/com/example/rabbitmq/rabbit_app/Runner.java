package com.example.rabbitmq.rabbit_app;

import java.util.Random;

import com.example.rabbitmq.RabbitMqApplication;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

  private final RabbitTemplate rabbitTemplate;
  public Runner(RabbitTemplate rabbitTemplate) {;
    this.rabbitTemplate = rabbitTemplate;
  }

  @Override
  public void run(String... args) throws Exception {
    Random random = new Random();
    int maxMilliseconds = 2500;
    int minMilliseconds = 1500;
    String[] food = {ConsoleColors.RED_BOLD + "Kebab - Kuba 🥙", ConsoleColors.CYAN_BOLD + "Pizza - Kuba🍕",
            ConsoleColors.YELLOW_BOLD + "Pierogi - Kuba 🥟", ConsoleColors.BLUE_BOLD + "Sushi - Kuba🍱"};
    int i = 1;
    while (i < 6) {
      System.out.println("🛵 Dostawca w drodze...");
      int randomMilliseconds = random.nextInt(maxMilliseconds - minMilliseconds + 1) + minMilliseconds;
        rabbitTemplate.convertAndSend(RabbitMqApplication.topicExchangeName,
                "food", i +". "+ food[i%4] + ConsoleColors.RESET);
      Thread.sleep(randomMilliseconds);
      i++;
    }
  }
}