package ed.u2.sorting.model;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Resultado de una ejecución de ordenación (para historial y export).
 * @author (por completar)
 * @version 1.0
 */
public final class SortResult {
    private final String algorithm;
    private final int[] original;
    private final int[] sorted;
    private final SortMetrics metrics;
    private final long durationMs;
    private final LocalDateTime timestamp;

    public SortResult(String algorithm, int[] original, int[] sorted, SortMetrics metrics, long durationMs) {
        this.algorithm = algorithm;
        this.original = original == null ? null : original.clone();
        this.sorted = sorted == null ? null : sorted.clone();
        this.metrics = metrics;
        this.durationMs = durationMs;
        this.timestamp = LocalDateTime.now();
    }

    public String getAlgorithm() { return algorithm; }
    public int[] getOriginal() { return original.clone(); }
    public int[] getSorted() { return sorted.clone(); }
    public SortMetrics getMetrics() { return metrics; }
    public long getDurationMs() { return durationMs; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return timestamp + " | " + algorithm
            + " | original=" + Arrays.toString(original)
            + " | sorted=" + Arrays.toString(sorted)
            + " | " + metrics.toString()
            + " | " + durationMs + " ms";
    }
}
