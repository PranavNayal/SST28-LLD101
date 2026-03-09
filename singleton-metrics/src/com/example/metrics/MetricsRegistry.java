package com.example.metrics;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Thread-safe singleton registry for global metrics.
 * Protected against reflection attacks and serialization exploits.
 */
public class MetricsRegistry implements Serializable {

    private static final long serialVersionUID = 1L;

    private static volatile MetricsRegistry INSTANCE;
    private static boolean instantiated = false;
    private final Map<String, Long> counters = new HashMap<>();

    // Private constructor with reflection protection
    private MetricsRegistry() {
        if (instantiated) {
            throw new IllegalStateException("Singleton already instantiated");
        }
        instantiated = true;
    }

    // Thread-safe lazy initialization with double-checked locking
    public static MetricsRegistry getInstance() {
        if (INSTANCE == null) {
            synchronized (MetricsRegistry.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MetricsRegistry();
                }
            }
        }
        return INSTANCE;
    }

    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }

    // Preserve singleton on deserialization
    private Object readResolve() {
        return getInstance();
    }
}
