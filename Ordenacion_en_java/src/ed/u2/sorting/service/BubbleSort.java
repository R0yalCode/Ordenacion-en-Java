package ed.u2.sorting.service;

import ed.u2.sorting.model.SortMetrics;
import ed.u2.sorting.structure.ConsoleVisualizer;
import ed.u2.sorting.structure.SortingUtils;

/**
 * Bubble Sort con corte temprano, trazas visuales y conteo de swaps.
 *
 * @author Royel Jima, Darwin Jimbo
 * @version 1.0
 */
public final class BubbleSort implements SortingService {

    private final SortMetrics metrics = new SortMetrics();

    @Override
    public String getName() { return "BubbleSort"; }

    @Override
    public SortMetrics getMetrics() { return metrics; }

    @Override
    public void sort(int[] a) { sort(a, false, false, 0); }

    @Override
    public void sort(int[] a, boolean trace, boolean colored, int delayMs) {
        metrics.reset();
        if (a == null) return;
        int n = a.length;
        if (trace) ConsoleVisualizer.render(a, -1, -1, "Start Bubble", colored, delayMs);
        for (int pass = 0; pass < n - 1; pass++) {
            boolean swapped = false;
            for (int i = 0; i < n - 1 - pass; i++) {
                if (trace) ConsoleVisualizer.render(a, i, i+1, "compare", colored, delayMs);
                if (a[i] > a[i + 1]) {
                    SortingUtils.swap(a, i, i + 1);
                    metrics.incSwap();
                    swapped = true;
                    if (trace) ConsoleVisualizer.render(a, i, i+1, "swap", colored, delayMs);
                }
            }
            if (!swapped) {
                if (trace) ConsoleVisualizer.render(a, -1, -1, "early-stop", colored, delayMs);
                break;
            } else {
                if (trace) ConsoleVisualizer.render(a, -1, -1, "pass-end", colored, delayMs);
            }
        }
        if (trace) ConsoleVisualizer.render(a, -1, -1, "End Bubble", colored, delayMs);
    }
}
