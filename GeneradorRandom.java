import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GeneradorRandom {

    public static void generarandom(int size, String fileName) {
        Random rand = new Random();
        try (FileWriter writer = new FileWriter(fileName)) {
            for (int i = 0; i < size; i++) {
                writer.write(rand.nextInt(10000) + "\n");  
            }
            System.out.println("Números aleatorios generados y guardados en: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        generarandom(3000, "Numeros_random.txt");
    }
}
