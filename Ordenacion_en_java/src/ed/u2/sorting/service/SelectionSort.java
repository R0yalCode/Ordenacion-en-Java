package ed.u2.sorting.service;

import ed.u2.sorting.model.SortMetrics;
import ed.u2.sorting.structure.ConsoleVisualizer;
import ed.u2.sorting.structure.SortingUtils;

/**
 * Selection Sort con trazas visuales y conteo de swaps.
 *
 * @author Royel Jima, Darwin Jimbo
 * @version 1.0
 */
public final class SelectionSort implements SortingService {

    private final SortMetrics metrics = new SortMetrics();

    @Override
    public String getName() { return "SelectionSort"; }

    @Override
    public SortMetrics getMetrics() { return metrics; }

    @Override
    public void sort(int[] a) { sort(a, false, false, 0); }

    @Override
    public void sort(int[] a, boolean trace, boolean colored, int delayMs) {
        metrics.reset();
        if (a == null) return;
        int n = a.length;
        if (trace) ConsoleVisualizer.render(a, -1, -1, "Start Selection", colored, delayMs);
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (trace) ConsoleVisualizer.render(a, minIndex, j, "compare", colored, delayMs);
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                    if (trace) ConsoleVisualizer.render(a, minIndex, j, "new min", colored, delayMs);
                }
            }
            if (minIndex != i) {
                SortingUtils.swap(a, i, minIndex);
                metrics.incSwap();
                if (trace) ConsoleVisualizer.render(a, i, minIndex, "swap", colored, delayMs);
            } else {
                if (trace) ConsoleVisualizer.render(a, i, minIndex, "no-swap", colored, delayMs);
            }
        }
        if (trace) ConsoleVisualizer.render(a, -1, -1, "End Selection", colored, delayMs);
    }
}
