package info.lazycompiler.uuid7_gen.service;

import info.lazycompiler.uuid7_gen.dto.UUID7;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TimedUUIDGeneratorTest {

    private final TimedUUIDGenerator timedUUIDGenerator = new UUID7Generator();

    @Test
    public void testIfOrdered() throws Exception {
        // given
        // at any given millisecond 2 ^ 12 -1 UUIDS will be unique.
        int n = 4095;

        // when
        var uuidObjs = getN_UUIDs(n);
        var item = new ArrayList<>(uuidObjs.stream().map(UUID7::uuid).toList());
        var itemCopy = new ArrayList<>(item);
        // sort
        item.sort(Comparator.naturalOrder());

        // then
        Assertions.assertEquals(itemCopy, item, "UUIDs should be timely ordered.");

    }

    private List<UUID7> getN_UUIDs(int n) {

        return IntStream.rangeClosed(1, n)
                .mapToObj(i -> timedUUIDGenerator.get())
                .collect(Collectors.toList());
    }

}
