package ed.u2.sorting.service;

import ed.u2.sorting.model.SortMetrics;
import ed.u2.sorting.structure.ConsoleVisualizer;
import ed.u2.sorting.structure.SortingUtils;

/**
 * Insertion Sort con trazas visuales.
 *
 * @author (por completar)
 * @version 1.0
 */
public final class InsertionSort implements SortingService {

    private final SortMetrics metrics = new SortMetrics();

    @Override
    public String getName() { return "InsertionSort"; }

    @Override
    public SortMetrics getMetrics() { return metrics; }

    @Override
    public void sort(int[] a) { sort(a, false, false, 0); }

    @Override
    public void sort(int[] a, boolean trace, boolean colored, int delayMs) {
        metrics.reset();
        if (a == null) return;
        int n = a.length;
        if (trace) ConsoleVisualizer.render(a, -1, -1, "Start Insertion", colored, delayMs);
        for (int i = 1; i < n; i++) {
            int key = a[i];
            int j = i - 1;
            int moved = 0;
            // mostrar comparación inicial
            if (trace) ConsoleVisualizer.render(a, i, j, "compare key=" + key, colored, delayMs);

            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j];
                j--;
                moved++;
                metrics.incMoves();
                if (trace) ConsoleVisualizer.render(a, j+1, j, "shift", colored, delayMs);
            }
            a[j + 1] = key;
            metrics.incMoves(); // colocar key
            if (trace) ConsoleVisualizer.render(a, j+1, i, "insert key", colored, delayMs);
        }
        if (trace) ConsoleVisualizer.render(a, -1, -1, "End Insertion", colored, delayMs);
    }
}
