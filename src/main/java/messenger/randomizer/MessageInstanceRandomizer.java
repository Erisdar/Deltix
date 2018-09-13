package messenger.randomizer;

import messenger.model.Message;

import java.util.List;
import java.util.stream.Collectors;

import static io.github.benas.randombeans.api.EnhancedRandom.randomStreamOf;

public class MessageInstanceRandomizer {

  private static final int RANDOM_DEEP = 100;

  public static List<Message> createMessages() {
    return randomStreamOf((int) (Math.random() * RANDOM_DEEP), Message.class)
        .collect(Collectors.toList());
  }
}
