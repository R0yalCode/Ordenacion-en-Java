package ed.u2.sorting.model;

/**
 * Métricas registradas durante la ejecución de un algoritmo de ordenación.
 * @author (por completar)
 * @version 1.0
 */
public final class SortMetrics {
    private int swaps;
    private int moves;

    public SortMetrics() { reset(); }

    public void incSwap() { swaps++; }
    public void incMoves() { moves++; }
    public void addMoves(int n) { moves += n; }

    public int getSwaps() { return swaps; }
    public int getMoves() { return moves; }

    public void reset() { swaps = 0; moves = 0; }

    @Override
    public String toString() {
        return "swaps=" + swaps + ", moves=" + moves;
    }
}
