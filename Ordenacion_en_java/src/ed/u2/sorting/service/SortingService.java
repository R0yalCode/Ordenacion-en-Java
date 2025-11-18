package ed.u2.sorting.service;

import ed.u2.sorting.model.SortMetrics;

/**
 * Contrato para algoritmos de ordenación.
 *
 * @author Royel Jima, Darwin Jimbo
 * @version 1.0
 */
public interface SortingService {
    void sort(int[] a);
    void sort(int[] a, boolean trace, boolean colored, int delayMs);
    SortMetrics getMetrics();
    String getName();
}
