package tictactoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        try (var context = SpringApplication.run(Main.class)) {
            final var launcher = context.getBean(Launcher.class);
            launcher.start();
        }
    }
}
