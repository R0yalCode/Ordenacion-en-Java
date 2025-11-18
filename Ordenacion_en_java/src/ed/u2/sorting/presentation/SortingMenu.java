package ed.u2.sorting.presentation;

import ed.u2.sorting.domain.Dataset;
import ed.u2.sorting.model.HistoryManager;
import ed.u2.sorting.model.SortMetrics;
import ed.u2.sorting.model.SortResult;
import ed.u2.sorting.service.SortingFactory;
import ed.u2.sorting.service.SortingService;
import ed.u2.sorting.structure.FileUtils;
import ed.u2.sorting.structure.SortingUtils;

import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Menú interactivo por consola para elegir algoritmo, ingresar números o cargar .txt,
 * ver historial y exportar. Estilo CLI profesional con opciones.
 *
 * @author Royel Jima, Darwin Jimbo
 * @version 1.0
 */
public final class SortingMenu {

    private static final Scanner SC = new Scanner(System.in);
    private static final HistoryManager history = new HistoryManager();
    private static final int DEFAULT_DELAY_MS = 150; // elegido por el usuario

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            showMainMenu();
            String opt = SC.nextLine().trim();
            switch (opt) {
                case "1": runAlgorithmInteractive("InsertionSort"); break;
                case "2": runAlgorithmInteractive("SelectionSort"); break;
                case "3": runAlgorithmInteractive("BubbleSort"); break;
                case "4": manualInputAndRun(); break;
                case "5": importFromFileAndRun(); break;
                case "6": runDatasets(); break;
                case "7": showHistory(); break;
                case "8": exportHistoryToTxt(); break;
                case "0": exit = true; break;
                default: System.out.println("Opción inválida. Intenta de nuevo.");
            }
        }
        System.out.println("Saliendo... ¡Hasta luego!");
    }

    private static void showMainMenu() {
        System.out.println("\n===== SISTEMA DE ORDENACIÓN =====");
        System.out.println("1. Insertion Sort");
        System.out.println("2. Selection Sort");
        System.out.println("3. Bubble Sort");
        System.out.println("4. Ingresar números manualmente");
        System.out.println("5. Cargar números desde archivo .txt");
        System.out.println("6. Usar datasets del Taller");
        System.out.println("7. Ver historial de ordenamientos");
        System.out.println("8. Exportar historial a TXT");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void runAlgorithmInteractive(String algorithmName) {
        SortingService svc = SortingFactory.of(algorithmName);
        int[] arr = askForArray();
        if (arr == null) return;
        boolean trace = askYesNo("¿Desea ver trazas y animación visual? (s/n): ");
        boolean colored = askYesNo("¿Usar colores ANSI en consola? (s/n): ");
        int delay = DEFAULT_DELAY_MS;
        if (trace) {
            String d = askString("Delay en ms por paso (enter = " + DEFAULT_DELAY_MS + "): ");
            if (!d.trim().isEmpty()) {
                try { delay = Integer.parseInt(d.trim()); } catch (NumberFormatException ignored) {}
            }
        } else {
            delay = 0;
        }

        int[] original = SortingUtils.cloneArray(arr);
        long t0 = System.currentTimeMillis();
        svc.sort(arr, trace, colored, delay);
        long duration = System.currentTimeMillis() - t0;
        SortMetrics m = svc.getMetrics();
        SortResult r = new SortResult(svc.getName(), original, arr, m, duration);
        history.add(r);
        System.out.println("Resultado final: " + SortingUtils.toString(arr));
        System.out.println("Métricas: " + m + " | Duración: " + duration + " ms");
    }

    private static int[] askForArray() {
        System.out.println("¿Cómo desea proporcionar los números?");
        System.out.println("1. Manual (una línea, separados por espacios)");
        System.out.println("2. Cargar desde archivo .txt");
        System.out.println("3. Usar dataset del Taller (A..E)");
        System.out.print("Seleccione: ");
        String o = SC.nextLine().trim();
        switch (o) {
            case "1": return readManual();
            case "2": return readFromFile();
            case "3": return chooseDataset();
            default: System.out.println("Opción inválida."); return null;
        }
    }

    private static int[] readManual() {
        System.out.print("Ingrese números separados por espacios: ");
        String line = SC.nextLine().trim();
        if (line.isEmpty()) { System.out.println("Entrada vacía."); return null; }
        String[] parts = line.split("\\s+");
        try {
            int[] a = new int[parts.length];
            for (int i = 0; i < parts.length; i++) a[i] = Integer.parseInt(parts[i]);
            return a;
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Deben ser enteros."); return null;
        }
    }

    private static int[] readFromFile() {
        System.out.print("Ruta del archivo .txt: ");
        String path = SC.nextLine().trim();
        try {
            return FileUtils.readIntArray(path);
        } catch (Exception e) {
            System.out.println("Error leyendo archivo: " + e.getMessage()); return null;
        }
    }

    private static int[] chooseDataset() {
        System.out.println("Elegir dataset: A,B,C,D,E");
        System.out.print("Ingrese letra: ");
        String l = SC.nextLine().trim().toUpperCase();
        switch (l) {
            case "A": return Dataset.A();
            case "B": return Dataset.B();
            case "C": return Dataset.C();
            case "D": return Dataset.D();
            case "E": return Dataset.E();
            default: System.out.println("No reconocido."); return null;
        }
    }

    private static void manualInputAndRun() {
        int[] a = readManual();
        if (a == null) return;
        String alg = askAlgorithmChoice();
        if (alg == null) return;
        SortingService svc = SortingFactory.of(alg);
        boolean trace = askYesNo("¿Mostrar trazas? (s/n): ");
        boolean colored = askYesNo("¿Colores ANSI? (s/n): ");
        int delay = trace ? DEFAULT_DELAY_MS : 0;
        svc.sort(a, trace, colored, delay);
        System.out.println("Resultado: " + SortingUtils.toString(a));
    }

    private static void importFromFileAndRun() {
        int[] a = readFromFile();
        if (a == null) return;
        String alg = askAlgorithmChoice();
        if (alg == null) return;
        SortingService svc = SortingFactory.of(alg);
        boolean trace = askYesNo("¿Mostrar trazas? (s/n): ");
        boolean colored = askYesNo("¿Colores ANSI? (s/n): ");
        int delay = trace ? DEFAULT_DELAY_MS : 0;
        svc.sort(a, trace, colored, delay);
        System.out.println("Resultado: " + SortingUtils.toString(a));
    }

    private static void runDatasets() {
        String[] names = {"A","B","C","D","E"};
        int[][] ds = {Dataset.A(),Dataset.B(),Dataset.C(),Dataset.D(),Dataset.E()};
        String alg = askAlgorithmChoice();
        if (alg == null) return;
        SortingService svc = SortingFactory.of(alg);
        boolean trace = askYesNo("¿Mostrar trazas? (s/n): ");
        boolean colored = askYesNo("¿Colores ANSI? (s/n): ");
        int delay = trace ? DEFAULT_DELAY_MS : 0;

        for (int i = 0; i < ds.length; i++) {
            int[] arr = SortingUtils.cloneArray(ds[i]);
            System.out.println("\n--- Dataset " + names[i] + " ---");
            long t0 = System.currentTimeMillis();
            svc.sort(arr, trace, colored, delay);
            long dur = System.currentTimeMillis() - t0;
            history.add(new SortResult(svc.getName(), ds[i], arr, svc.getMetrics(), dur));
            System.out.println("Resultado: " + SortingUtils.toString(arr));
        }
    }

    private static void showHistory() {
        var all = history.all();
        if (all.isEmpty()) { System.out.println("Historial vacío."); return; }
        System.out.println("===== HISTORIAL =====");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        int i = 1;
        for (var r : all) {
            System.out.println("#" + (i++) + " | " + r.getTimestamp().format(fmt)
                    + " | " + r.getAlgorithm()
                    + " | original=" + SortingUtils.toString(r.getOriginal())
                    + " | sorted=" + SortingUtils.toString(r.getSorted())
                    + " | " + r.getMetrics() + " | " + r.getDurationMs() + "ms");
        }
    }

    private static void exportHistoryToTxt() {
        System.out.print("Archivo destino (ej: historial.txt): ");
        String path = SC.nextLine().trim();
        try (FileWriter fw = new FileWriter(path)) {
            for (var r : history.all()) fw.write(r.toString() + System.lineSeparator());
            System.out.println("Historial exportado a " + path);
        } catch (Exception e) {
            System.out.println("Error exportando: " + e.getMessage());
        }
    }

    private static String askAlgorithmChoice() {
        System.out.println("Elegir algoritmo: 1=Insertion, 2=Selection, 3=Bubble");
        System.out.print("Seleccione: ");
        String s = SC.nextLine().trim();
        switch (s) {
            case "1": return "InsertionSort";
            case "2": return "SelectionSort";
            case "3": return "BubbleSort";
            default: System.out.println("Opción inválida."); return null;
        }
    }

    private static boolean askYesNo(String prompt) {
        String r;
        while (true) {
            System.out.print(prompt);
            r = SC.nextLine().trim().toLowerCase();
            if (r.equals("s") || r.equals("si") || r.equals("y") || r.equals("yes")) return true;
            if (r.equals("n") || r.equals("no")) return false;
            System.out.println("Responda s/n.");
        }
    }

    private static String askString(String prompt) {
        System.out.print(prompt);
        return SC.nextLine();
    }
}
