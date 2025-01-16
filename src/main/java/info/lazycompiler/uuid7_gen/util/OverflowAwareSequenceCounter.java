package info.lazycompiler.uuid7_gen.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class OverflowAwareSequenceCounter {

    private static final AtomicInteger SEQUENCE_COUNTER = new AtomicInteger(0);

    private OverflowAwareSequenceCounter() {
        throw new UnsupportedOperationException(String.format("cannot %s init  class", OverflowAwareSequenceCounter.class.getName()));
    }

    public static Integer next() {
        // if reached max reset the counter to 0
        if (SEQUENCE_COUNTER.get() == Integer.MAX_VALUE) {
            reset();
        }
        return SEQUENCE_COUNTER.incrementAndGet();
    }

    public static Integer reset() {
        return SEQUENCE_COUNTER.updateAndGet(op -> 0);
    }


}
