package messenger.service;

import messenger.model.Message;
import reactor.core.publisher.FluxSink;

public interface MessageGeneratorService {
  void subscribeGenerator(FluxSink<Message> sinkPublisher);
}
