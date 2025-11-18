package ed.u2.sorting.exception;

/**
 * Excepción del módulo de ordenación.
 * @author Royel Jima, Darwin Jimbo
 * @version 1.0
 */
public class SortingException extends RuntimeException {
    public SortingException() { super(); }
    public SortingException(String msg) { super(msg); }
    public SortingException(String msg, Throwable t) { super(msg, t); }
}
