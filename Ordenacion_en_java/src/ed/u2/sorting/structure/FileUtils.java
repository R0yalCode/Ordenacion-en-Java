package ed.u2.sorting.structure;

import ed.u2.sorting.exception.SortingException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Operaciones de archivo: lectura de .txt con números (espacio o línea por número).
 * Lanza SortingException en caso de entrada inválida.
 *
 * @author Royel Jima, Darwin Jimbo
 * @version 1.0
 */
public final class FileUtils {
    private FileUtils() {}

    /**
     * Lee números enteros desde un archivo. Acepta ambos formatos:
     * "8 3 6 3 9" o líneas con un número por línea.
     *
     * @param path ruta al archivo
     * @return arreglo de ints
     * @throws SortingException si hay error o contenido inválido
     */
    public static int[] readIntArray(String path) {
        if (path == null) throw new SortingException("Ruta nula");
        File f = new File(path);
        if (!f.exists()) throw new SortingException("Archivo no encontrado: " + path);
        List<Integer> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\s+");
                for (String p : parts) {
                    try {
                        list.add(Integer.parseInt(p));
                    } catch (NumberFormatException ex) {
                        throw new SortingException("Valor no entero encontrado: '" + p + "'", ex);
                    }
                }
            }
        } catch (Exception e) {
            throw new SortingException("Error al leer archivo: " + e.getMessage(), e);
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) res[i] = list.get(i);
        return res;
    }
}
