package messenger.model;

import io.github.benas.randombeans.annotation.Randomizer;
import io.github.benas.randombeans.annotation.RandomizerArgument;
import io.github.benas.randombeans.randomizers.range.IntegerRangeRandomizer;
import lombok.Data;

@Data
public class Message {

  @Randomizer(
      value = IntegerRangeRandomizer.class,
      args = {
        @RandomizerArgument(value = "10", type = Integer.class),
        @RandomizerArgument(value = "10000", type = Integer.class)
      })
  private int id;

  private String text;
}
