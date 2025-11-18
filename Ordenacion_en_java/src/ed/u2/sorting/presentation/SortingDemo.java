package ed.u2.sorting.presentation;

import ed.u2.sorting.domain.Dataset;
import ed.u2.sorting.service.BubbleSort;
import ed.u2.sorting.service.InsertionSort;
import ed.u2.sorting.service.SelectionSort;
import ed.u2.sorting.structure.SortingUtils;

/**
 * Demo rápido para ejecutar los datasets A..E con trazas apagadas.
 * Útil para pruebas unitarias / comprobación automática.
 *
 * @author Royel Jima, Darwin Jimbo
 * @version 1.0
 */
public final class SortingDemo {
    public static void main(String[] args) {
        int[][] ds = { Dataset.A(), Dataset.B(), Dataset.C(), Dataset.D(), Dataset.E() };
        var ins = new InsertionSort();
        var sel = new SelectionSort();
        var bub = new BubbleSort();

        for (int i = 0; i < ds.length; i++) {
            int[] a = SortingUtils.cloneArray(ds[i]);
            ins.sort(a, false, false, 0);
            System.out.println("Insertion res: " + SortingUtils.toString(a));
        }
        // similar para selection y bubble...
        System.out.println("Demo completado.");
    }
}
