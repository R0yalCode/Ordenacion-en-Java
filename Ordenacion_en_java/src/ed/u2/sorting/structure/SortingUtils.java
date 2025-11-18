package ed.u2.sorting.structure;

import ed.u2.sorting.exception.SortingException;

/**
 * Utilidades genéricas para ordenación: swap, clone, isSorted.
 * @author Royel Jima, Darwin Jimbo
 * @version 1.0
 */
public final class SortingUtils {
    private SortingUtils() {}

    public static void swap(int[] a, int i, int j) {
        if (a == null) throw new SortingException("Arreglo nulo");
        if (i < 0 || j < 0 || i >= a.length || j >= a.length)
            throw new SortingException("Índices fuera de rango: " + i + "," + j);
        int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
    }

    public static int[] cloneArray(int[] a) {
        if (a == null) return null;
        int[] c = new int[a.length];
        System.arraycopy(a, 0, c, 0, a.length);
        return c;
    }

    public static boolean isSorted(int[] a) {
        if (a == null || a.length <= 1) return true;
        for (int i = 1; i < a.length; i++) if (a[i-1] > a[i]) return false;
        return true;
    }

    public static String toString(int[] a) {
        if (a == null) return "null";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i]);
            if (i < a.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
