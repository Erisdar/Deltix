package messenger.service.impl;

import io.vavr.control.Try;
import messenger.model.Message;
import messenger.randomizer.MessageInstanceRandomizer;
import messenger.service.MessageGeneratorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

@Service
public class MessageGeneratorServiceImpl implements MessageGeneratorService {

  private static final Logger LOG = LogManager.getLogger(MessageGeneratorServiceImpl.class);
  private static final int GENERATE_INTERVAL = 50000;

  @Override
  public void subscribeGenerator(FluxSink<Message> sinkSubscriber) {
    Flux.fromIterable(MessageInstanceRandomizer.createMessages())
        .parallel()
        .runOn(Schedulers.newParallel("generator"))
        .subscribe(
            message -> {
              sinkSubscriber.next(message);
              LOG.info(String.format("The messenger generate a message: %s", message));
            });

    Try.run(() -> Thread.sleep((long) (Math.random() * GENERATE_INTERVAL)));
    subscribeGenerator(sinkSubscriber);
  }
}
