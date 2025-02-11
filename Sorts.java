import java.io.*;
import java.util.*;

public class Sorts {
    public static void main(String[] args) {
        List<Integer> numeros = leerNumerosDesdeArchivo("HDT3\\Numeros_random.txt");
        
        if (numeros == null) {
            System.out.println("Error leyendo el archivo.");
            return;
        }
        
        List<Integer> copia1 = new ArrayList<>(numeros);
        List<Integer> copia2 = new ArrayList<>(numeros);
        
        long inicio, fin;
        
        // Inserción
        inicio = System.nanoTime();
        ordInsercion(copia1);
        fin = System.nanoTime();
        System.out.println("Tiempo de Inserción: " + (fin - inicio) / 1e9 + " segundos");
        
        // Quicksort
        inicio = System.nanoTime();
        QS(copia2, 0, copia2.size() - 1);
        fin = System.nanoTime();
        System.out.println("Tiempo de QuickSort: " + (fin - inicio) / 1e9 + " segundos");
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
        return i +1;
    }
}

