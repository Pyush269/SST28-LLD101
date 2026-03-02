package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Thread-safe, lazy-initialized Singleton using the static-holder (initialization-on-demand) idiom.
 *
 * Guarantees:
 *  1. Lazy initialization — no instance created until first call to getInstance().
 *  2. Thread safety — guaranteed by the class loader; no explicit locking needed.
 *  3. Reflection guard — throws if constructor is called when an instance already exists.
 *  4. Serialization safety — readResolve() returns the same singleton instance.
 */
public final class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    // Static holder — class loaded lazily by JVM; thread-safe without locking
    // -------------------------------------------------------------------------
    private static final class Holder {
        private static final MetricsRegistry INSTANCE = new MetricsRegistry();
    }

    // -------------------------------------------------------------------------
    // Constructor — private + reflection guard
    // -------------------------------------------------------------------------
    private MetricsRegistry() {
        // Reflection guard: if an instance already exists, reject the call
        if (Holder.INSTANCE != null) {
            throw new IllegalStateException(
                "Singleton already instantiated — reflection attack blocked.");
        }
    }

    // -------------------------------------------------------------------------
    // Public accessor
    // -------------------------------------------------------------------------
    public static MetricsRegistry getInstance() {
        return Holder.INSTANCE;
    }

    // -------------------------------------------------------------------------
    // Metric operations
    // -------------------------------------------------------------------------
    private final Map<String, Long> counters = new HashMap<>();

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

    // -------------------------------------------------------------------------
    // Serialization safety — return the existing singleton on deserialization
    // -------------------------------------------------------------------------
    @Serial
    private Object readResolve() {
        return Holder.INSTANCE;
    }
}
