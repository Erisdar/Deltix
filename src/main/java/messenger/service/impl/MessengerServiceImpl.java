package messenger.service.impl;

import messenger.model.Message;
import messenger.model.Messenger;
import messenger.service.MessengerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import reactor.core.publisher.UnicastProcessor;
import reactor.core.scheduler.Schedulers;

@Service
public class MessengerServiceImpl implements MessengerService {

  private static final Logger LOG = LogManager.getLogger(MessengerServiceImpl.class);

  @Override
  public void subscribeMessenger(UnicastProcessor<Message> messagePublisher, Messenger messenger) {
    messagePublisher
        .parallel()
        .runOn(Schedulers.newElastic("messenger"))
        .subscribe(
            message -> {
              if (messenger.addMessage(message)) {
                LOG.info(
                    String.format(
                        "The messenger took the message: %s and now have remaining Capacity %s\n",
                        message, messenger.getMessages().remainingCapacity()));
              } else {
                LOG.error(String.format("The messenger doesn't took the message: %s \n", message));
              }
            });
  }
}
