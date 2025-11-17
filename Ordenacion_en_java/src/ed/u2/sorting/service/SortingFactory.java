package ed.u2.sorting.service;

/**
 * Fabrica simple para obtener instancias por nombre.
 * @author (por completar)
 * @version 1.0
 */
public final class SortingFactory {
    private SortingFactory() {}

    public static SortingService of(String name) {
        switch (name.toLowerCase()) {
            case "insertion":
            case "insertionsort":
                return new InsertionSort();
            case "selection":
            case "selectionsort":
                return new SelectionSort();
            case "bubble":
            case "bubblesort":
                return new BubbleSort();
            default:
                throw new IllegalArgumentException("Algoritmo desconocido: " + name);
        }
    }
}
