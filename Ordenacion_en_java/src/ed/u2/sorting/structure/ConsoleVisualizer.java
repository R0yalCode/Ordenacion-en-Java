package ed.u2.sorting.structure;

import java.util.Locale;

/**
 * Visualizador por consola (barras horizontales), con colores ANSI y delay.
 *
 * Visual por barras horizontales:
 * 8: ********
 * 3: ***
 *
 * @author Royel Jima, Darwin Jimbo
 * @version 1.0
 */
public final class ConsoleVisualizer {

    // ANSI colors
    public static final String RESET = "\033[0m";
    public static final String RED = "\033[31m";
    public static final String GREEN = "\033[32m";
    public static final String YELLOW = "\033[33m";
    public static final String BLUE = "\033[34m";
    public static final String CYAN = "\033[36m";

    private ConsoleVisualizer() {}

    /**
     * Render horizontal bars of arreglo.
     *
     * @param a arreglo actual
     * @param highlight1 índice 1 a destacar (-1 si ninguno)
     * @param highlight2 índice 2 a destacar (-1 si ninguno)
     * @param action texto breve (p.ej. "swap", "compare")
     * @param colored si true usar colores ANSI
     * @param delayMs ms a dormir después de dibujar (>=0)
     */
    public static void render(int[] a, int highlight1, int highlight2, String action, boolean colored, int delayMs) {
        if (a == null) {
            System.out.println("(null)");
            return;
        }
        System.out.println();
        if (action != null && !action.isEmpty()) {
            if (colored) System.out.println(CYAN + "[ " + action.toUpperCase(Locale.ROOT) + " ]" + RESET);
            else System.out.println("[ " + action.toUpperCase(Locale.ROOT) + " ]");
        }

        // find max to scale bars (avoid huge outputs)
        int max = 1;
        for (int v : a) if (v > max) max = v;
        int scale = Math.max(1, max / 40); // cap width ~40 chars
        for (int i = 0; i < a.length; i++) {
            int v = a[i];
            int len = v / scale;
            StringBuilder bar = new StringBuilder();
            for (int k = 0; k < len; k++) bar.append('*');
            String line = String.format("%3d: %s", v, bar.toString());
            if (colored && (i == highlight1 || i == highlight2)) {
                // choose color
                String color = (i == highlight1 && i == highlight2) ? YELLOW : (i == highlight1 ? RED : GREEN);
                System.out.println(color + line + RESET);
            } else {
                System.out.println(line);
            }
        }
        System.out.println();

        if (delayMs > 0) {
            try { Thread.sleep(delayMs); } catch (InterruptedException ignored) { Thread.currentThread().interrupt(); }
        }
    }
}
