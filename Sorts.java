import java.io.*;
import java.util.*;

public class Sorts {
    public static void main(String[] args) {
        List<Integer> numeros = leerNumerosDesdeArchivo("Numeros_random.txt");

        if (numeros == null) {
            System.out.println("Error leyendo el archivo.");
            return;
        }

        List<Integer> copia1 = new ArrayList<>(numeros);
        List<Integer> copia2 = new ArrayList<>(numeros);
        List<Integer> copia3 = new ArrayList<>(numeros);
        List<Integer> copia4 = new ArrayList<>(numeros);

        long inicio, fin;

        // Medir el tiempo de Inserción
        inicio = System.nanoTime();
        ordInsercion(copia1);
        fin = System.nanoTime();
        System.out.println("Tiempo de Inserción: " + (fin - inicio) / 1e9 + " segundos");

        // Medir el tiempo de QuickSort
        inicio = System.nanoTime();
        QS(copia2, 0, copia2.size() - 1);
        fin = System.nanoTime();
        System.out.println("Tiempo de QuickSort: " + (fin - inicio) / 1e9 + " segundos");

        // Medir el tiempo de MergeSort
        inicio = System.nanoTime();
        mergeSort(copia3);
        fin = System.nanoTime();
        System.out.println("Tiempo de MergeSort: " + (fin - inicio) / 1e9 + " segundos");

        // Medir el tiempo de RadixSort
        inicio = System.nanoTime();
        radixSort(copia4);
        fin = System.nanoTime();
        System.out.println("Tiempo de RadixSort: " + (fin - inicio) / 1e9 + " segundos");

         // Medir el tiempo de HeapSort
         inicio = System.nanoTime();
         heapSort(copia1);
         fin = System.nanoTime();
         System.out.println("Tiempo de HeapSort: " + (fin - inicio) / 1e9 + " segundos");       
    }

    public static List<Integer> leerNumerosDesdeArchivo(String nombreArchivo) {
        List<Integer> numeros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                numeros.add(Integer.parseInt(linea.trim()));
            }
        } catch (IOException | NumberFormatException e) {
            return null;
        }
        return numeros;
    }

    // Insertion Sort
    public static void ordInsercion(List<Integer> lista) {
        for (int i = 1; i < lista.size(); i++) {
            int clave = lista.get(i);
            int j = i - 1;
            while (j >= 0 && lista.get(j) > clave) {
                lista.set(j + 1, lista.get(j));
                j--;
            }
            lista.set(j + 1, clave);
        }
    }

    // QuickSort
    public static void QS(List<Integer> lista, int bajo, int alto) {
        if (bajo < alto) {
            int indicePivote = particion(lista, bajo, alto);
            QS(lista, bajo, indicePivote - 1);
            QS(lista, indicePivote + 1, alto);
        }
    }
    public static int particion(List<Integer> lista, int bajo, int alto) {
        int pivote = lista.get(alto);
        int i = bajo - 1;
        for (int j = bajo; j < alto; j++) {
            if (lista.get(j) < pivote) {
                i++;
                Collections.swap(lista, i, j);
            }
        }
        Collections.swap(lista, i + 1, alto);
        return i + 1;
    }

    // MergeSort
    public static void mergeSort(List<Integer> lista) {
        if (lista.size() <= 1) return;

        int mid = lista.size() / 2;
        List<Integer> left = new ArrayList<>(lista.subList(0, mid));
        List<Integer> right = new ArrayList<>(lista.subList(mid, lista.size()));

        mergeSort(left);
        mergeSort(right);

        merge(lista, left, right);
    }
    private static void merge(List<Integer> lista, List<Integer> left, List<Integer> right) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                lista.set(k++, left.get(i++));
            } else {
                lista.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) lista.set(k++, left.get(i++));
        while (j < right.size()) lista.set(k++, right.get(j++));
    }

    // RadixSort
    public static void radixSort(List<Integer> lista) {
        int max = Collections.max(lista);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(lista, exp);
        }
    }
    private static void countingSortByDigit(List<Integer> lista, int exp) {
        int[] output = new int[lista.size()];
        int[] count = new int[10];

        for (int i = 0; i < lista.size(); i++) {
            count[(lista.get(i) / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = lista.size() - 1; i >= 0; i--) {
            output[count[(lista.get(i) / exp) % 10] - 1] = lista.get(i);
            count[(lista.get(i) / exp) % 10]--;
        }

        for (int i = 0; i < lista.size(); i++) {
            lista.set(i, output[i]);
        }
    }
    // HeapSort
    public static void heapSort(List<Integer> lista) {
        int n = lista.size();

        for (int i = n / 2 - 1; i >= 0; i--) {
            heap(lista, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            Collections.swap(lista, 0, i);
            heap(lista, i, 0);
        }
    }

    private static void heap(List<Integer> lista, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && lista.get(left) > lista.get(largest)) {
            largest = left;
        }

        if (right < n && lista.get(right) > lista.get(largest)) {
            largest = right;
        }

        if (largest != i) {
            Collections.swap(lista, i, largest);
            heap(lista, n, largest);
        }
    }    
}
