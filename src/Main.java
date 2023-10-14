import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final int LIMITE_SUPERIOR_1 = 80;
    private static final int LIMITE_SUPERIOR_2 = 50;
    private static final int LIMITE_SUPERIOR_3 = 30;
    private static final int LIMITE_INFERIOR = 0;

    public static void main(String[] args) {
        evaluarSeveridad();
        System.out.println("Vuelva pronto");
    }

    private static void evaluarSeveridad() {
        Scanner scanner = new Scanner(System.in);
        int respuesta;

        do {
            try {
                System.out.println("Ingresa el nivel de severidad de 1-100 (valor FEV1):");
                System.out.println("Si desea salir ingrese 0.");
                respuesta = scanner.nextInt();

                if (respuesta >= LIMITE_SUPERIOR_1 && respuesta <= 100) {
                    mostrarResultado("GOLD 1", "Leve");
                } else if (respuesta >= LIMITE_SUPERIOR_2 && respuesta < LIMITE_SUPERIOR_1) {
                    mostrarResultado("GOLD 2", "Moderada");
                } else if (respuesta >= LIMITE_SUPERIOR_3 && respuesta < LIMITE_SUPERIOR_2) {
                    mostrarResultado("GOLD 3", "Severa");
                } else if (respuesta < LIMITE_SUPERIOR_3 && respuesta > LIMITE_INFERIOR) {
                    mostrarResultado("GOLD 4", "Muy severa");
                } else if (respuesta != LIMITE_INFERIOR) {
                    System.out.println("Te saliste de los límites (1-100)");
                }

            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingresa un número válido.");
                scanner.next();
                respuesta = -1;

            }
        } while (respuesta != LIMITE_INFERIOR);
    }

    private static void mostrarResultado(String estadio, String severidad) {
        System.out.println("Estadio: " + estadio + "\nSeveridad: " + severidad);
    }
}
