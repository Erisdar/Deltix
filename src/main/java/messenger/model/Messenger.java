package messenger.model;

import io.vavr.control.Try;
import lombok.Data;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Data
public class Messenger {
  private String name;
  private BlockingQueue<Message> messages;

  public Messenger(String name, int capacity) {
    this.name = name;
    this.messages = new ArrayBlockingQueue<>(capacity, true);
  }

  public boolean addMessage(Message message) {
    return Try.of(
            () -> {
              messages.put(message);
              return true;
            })
        .getOrElse(false);
  }

  public void clearMessages() {
    messages.clear();
  }
}
