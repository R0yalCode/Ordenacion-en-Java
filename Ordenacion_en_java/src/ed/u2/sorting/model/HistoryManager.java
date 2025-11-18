package ed.u2.sorting.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Gestiona el historial de ordenamientos en memoria (puede exportarse).
 * Simple y thread-unsafe (suficiente para práctica/CLI).
 *
 * @author Royel Jima, Darwin Jimbo
 * @version 1.0
 */
public final class HistoryManager {
    private final List<SortResult> history = new ArrayList<>();

    public void add(SortResult r) { if (r != null) history.add(r); }

    public List<SortResult> all() { return Collections.unmodifiableList(history); }

    public void clear() { history.clear(); }
}
