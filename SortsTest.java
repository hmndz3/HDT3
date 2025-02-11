import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class SortsTest {

    // Prueba para Insertion Sort
    @Test
    public void testOrdInsercion() {
        List<Integer> lista = new ArrayList<>(Arrays.asList(5, 3, 4, 1, 2));
        Sorts.ordInsercion(lista);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), lista, "La lista no se ordenó correctamente.");
    }

    // Prueba para QuickSort
    @Test
    public void testQS() {
        List<Integer> lista = new ArrayList<>(Arrays.asList(5, 3, 4, 1, 2));
        Sorts.QS(lista, 0, lista.size() - 1);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), lista, "La lista no se ordenó correctamente.");
    }

    // Prueba para MergeSort
    @Test
    public void testMergeSort() {
        List<Integer> lista = new ArrayList<>(Arrays.asList(5, 3, 4, 1, 2));
        Sorts.mergeSort(lista);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), lista, "La lista no se ordenó correctamente.");
    }

    // Prueba para RadixSort
    @Test
    public void testRadixSort() {
        List<Integer> lista = new ArrayList<>(Arrays.asList(5, 3, 4, 1, 2));
        Sorts.radixSort(lista);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), lista, "La lista no se ordenó correctamente.");
    }

    // Prueba con una lista vacía
    @Test
    public void testEmptyList() {
        List<Integer> lista = new ArrayList<>();
        Sorts.ordInsercion(lista);
        assertTrue(lista.isEmpty(), "La lista debería estar vacía.");
    }

    // Prueba con una lista de un solo elemento
    @Test
    public void testSingleElementList() {
        List<Integer> lista = new ArrayList<>(Collections.singletonList(10));
        Sorts.ordInsercion(lista);
        assertEquals(Collections.singletonList(10), lista, "La lista con un solo elemento no se ordenó correctamente.");
    }

    // Prueba para ordenar una lista ya ordenada (debe seguir igual)
    @Test
    public void testSortedList() {
        List<Integer> lista = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Sorts.ordInsercion(lista);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), lista, "La lista ya ordenada no debería cambiar.");
    }

    // Prueba para una lista con elementos repetidos
    @Test
    public void testListWithDuplicates() {
        List<Integer> lista = new ArrayList<>(Arrays.asList(5, 3, 5, 1, 5, 2, 3));
        Sorts.ordInsercion(lista);
        assertEquals(Arrays.asList(1, 2, 3, 3, 5, 5, 5), lista, "La lista con elementos repetidos no se ordenó correctamente.");
    }
}
