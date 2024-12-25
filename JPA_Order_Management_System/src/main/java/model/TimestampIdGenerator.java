package model;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.util.concurrent.atomic.AtomicLong;

public class TimestampIdGenerator implements IdentifierGenerator {

    private final AtomicLong counter = new AtomicLong();
    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {

        long timestamp = System.currentTimeMillis();
        long uniqueId  = counter.incrementAndGet();

        return Long.parseLong(timestamp + String.format("%03d", uniqueId));

    }
}
