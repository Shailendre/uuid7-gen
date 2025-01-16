package info.lazycompiler.uuid7_gen.service;

import info.lazycompiler.uuid7_gen.dto.UUID7;

import java.math.BigInteger;
import java.util.function.Supplier;

public interface TimedUUIDGenerator extends Supplier<UUID7> {

    default BigInteger get128Bits(long msb, long lsb) {
        var msbBI = BigInteger.valueOf(msb).shiftLeft(64);

        var lsbBI = BigInteger.valueOf(lsb);

        // Handle negative values of least significant bits (unsigned representation)
        if (lsb < 0) {
            lsbBI = lsbBI.add(BigInteger.ONE.shiftLeft(64));
        }

        return msbBI.add(lsbBI);
    }

}
