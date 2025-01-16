package info.lazycompiler.uuid7_gen.service;

import info.lazycompiler.uuid7_gen.dto.UUID7;
import info.lazycompiler.uuid7_gen.util.OverflowAwareSequenceCounter;

import java.math.BigInteger;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UUID7Generator implements TimedUUIDGenerator {

    private static final Long _48_BITS_MASK_HEXADECIMAL = 0xFFFFFFFFFFFFL;
    private static final Integer _12_BITS_MASK_HEXADECIMAL = 0xFFF;
    private static final Long _62_BITS_MASK_HEXADECIMAL = 0x3FFFFFFFFFFFFFFFL;

    private Long lastMs = -1L;

    /**
     * unix_ts_ms:
     * 48-bit big-endian unsigned number of the Unix Epoch timestamp in milliseconds as per Section 6.1. Occupies bits 0 through 47 (octets 0-5).
     * ver:
     * The 4-bit version field as defined by Section 4.2, set to 0b0111 (7). Occupies bits 48 through 51 of octet 6.
     * rand_a:
     * 12 bits of pseudorandom data to provide uniqueness as per Section 6.9 and/or optional constructs to guarantee additional monotonicity as per Section 6.2. Occupies bits 52 through 63 (octets 6-7).
     * var:
     * The 2-bit variant field as defined by Section 4.1, set to 0b10. Occupies bits 64 and 65 of octet 8.
     * rand_b:
     * The final 62 bits of pseudorandom data to provide uniqueness as per Section 6.9 and/or an optional counter to guarantee additional monotonicity as per Section 6.2. Occupies bits 66 through 127 (octets 8-15).
     * @return 128 bit UUID
     */

    // write to file

    //@Override
    public synchronized UUID7 get() {

        // time the overall operation\

        var t0 = System.currentTimeMillis();

        // 1: 48 bits
        // extract 48 LSB bits of unix timestamp
        var _48_bits = System.currentTimeMillis() & _48_BITS_MASK_HEXADECIMAL;

        // 2: 4 bits
        // version number => 7
        var _4_bits = 0b111;

        // 3: 12 bits
        // monotonic number
        // this should be increasing when same timestamp
        var seq = lastMs == _48_bits ? OverflowAwareSequenceCounter.next() : OverflowAwareSequenceCounter.reset();
        var _12_bits = seq & _12_BITS_MASK_HEXADECIMAL;

        // quickly set the lastMs to current value
        lastMs = _48_bits;

        // 4: 64 bits
        // form the 64 MSB bits of time UUID
        var msb = ( _48_bits << 16 ) | (_4_bits << 12) | _12_bits;

        // 5: 2 bits
        // contant var => 2
        var var = 0b10L;

        // 6: 62 bits
        // 62 bits of random number
        var _62_bits = (ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE) & _62_BITS_MASK_HEXADECIMAL);

        // 7: 64 bits
        // form the 64 LSB bits of time UUID
        var lsb = (var << 62) | _62_bits;

        LOGGER.debug("UUID7 generated in {} ms.", System.currentTimeMillis() - t0);
        // 8: msb + lsb
        var uuid7 = new UUID(msb, lsb).toString();
        return new UUID7(uuid7, msb, get128Bits(msb, lsb));
    }

}
