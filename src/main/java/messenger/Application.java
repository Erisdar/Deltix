package messenger;

import messenger.config.AppConfig;
import messenger.model.Message;
import messenger.model.Messenger;
import messenger.service.MessageGeneratorService;
import messenger.service.MessengerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.UnicastProcessor;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Application {

  private static final int MESSENGER_CAPACITY_PER_TIMEOUT = 60;
  private static final int MESSENGER_TIMEOUT = 1;

  public static void main(String[] args) {
    ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
    MessengerService messengerService = appContext.getBean(MessengerService.class);
    MessageGeneratorService messageGeneratorService = appContext.getBean(MessageGeneratorService.class);

    UnicastProcessor<Message> messageProcessor = UnicastProcessor.create();
    FluxSink<Message> sinkMessageProcessor = messageProcessor.sink();

    Messenger messenger = new Messenger("Telegram", MESSENGER_CAPACITY_PER_TIMEOUT);

    Executors.newScheduledThreadPool(1)
        .scheduleAtFixedRate(messenger::clearMessages, MESSENGER_TIMEOUT, MESSENGER_TIMEOUT, TimeUnit.MINUTES);

    messengerService.subscribeMessenger(messageProcessor, messenger);
    messageGeneratorService.subscribeGenerator(sinkMessageProcessor);
  }
}
