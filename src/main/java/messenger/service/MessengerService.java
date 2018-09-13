package messenger.service;

import messenger.model.Message;
import messenger.model.Messenger;
import reactor.core.publisher.UnicastProcessor;

public interface MessengerService {
  void subscribeMessenger(UnicastProcessor<Message> messagePublisher, Messenger messenger);
}
