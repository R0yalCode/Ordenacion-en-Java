package ed.u2.sorting.domain;

/**
 * Datasets del taller y utilidades para casos borde.
 * @author Royel Jima, Darwin Jimbo
 * @version 1.0
 */
public final class Dataset {
    private Dataset() {}

    public static int[] A() { return new int[]{8, 3, 6, 3, 9}; }
    public static int[] B() { return new int[]{5, 4, 3, 2, 1}; }
    public static int[] C() { return new int[]{1, 2, 3, 4, 5}; }
    public static int[] D() { return new int[]{2, 2, 2, 2}; }
    public static int[] E() { return new int[]{9, 1, 8, 2}; }

    public static int[] EMPTY() { return new int[]{}; }
    public static int[] SINGLE() { return new int[]{42}; }
}
