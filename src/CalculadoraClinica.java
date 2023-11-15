import java.util.Scanner;

public class CalculadoraClinica {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione el tipo de dosis:");
        System.out.println("1. Dosis por kilogramo de peso");
        System.out.println("2. Dosis por unidad de superficie corporal");
        int tipoDosis = scanner.nextInt();

        if (tipoDosis == 1) {
            calcularDosisPorPeso(scanner);
        } else if (tipoDosis == 2) {
            calcularDosisPorSuperficieCorporal(scanner);
        } else {
            System.out.println("Tipo de dosis no válido. Saliendo del programa.");
        }

        scanner.close();
    }

    private static void calcularDosisPorPeso(Scanner scanner) {
        System.out.println("Ingrese la dosis del fármaco (mg/kg): ");
        double dosisPorKilo = scanner.nextDouble();

        System.out.println("Ingrese el peso corporal del paciente (kg): ");
        double pesoCorporal = scanner.nextDouble();

        if (dosisPorKilo <= 0 || pesoCorporal <= 0) {
            System.out.println("Error: La dosis y el peso deben ser mayores que cero.");
        } else {
            double dosisTotal = dosisPorKilo * pesoCorporal;

            System.out.println("La dosis a aplicar es: " + dosisTotal + " mg");
        }
    }

    private static void calcularDosisPorSuperficieCorporal(Scanner scanner) {
        System.out.println("Ingrese la dosis por unidad de superficie corporal (mg/m2): ");
        double dosisPorM2 = scanner.nextDouble();

        System.out.println("Ingrese la altura del paciente (m): ");
        double altura = scanner.nextDouble();

        System.out.println("Ingrese el peso corporal del paciente (kg): ");
        double pesoCorporal = scanner.nextDouble();

        if (dosisPorM2 <= 0 || altura <= 0 || pesoCorporal <= 0) {
            System.out.println("Error: Todos los valores deben ser mayores que cero.");
        } else {
            double dosisTotal = dosisPorM2 * calcularAreaSuperficieCorporal(altura, pesoCorporal);

            System.out.println("La dosis a aplicar es: " + dosisTotal + " mg");
        }

    }

    private static double calcularAreaSuperficieCorporal(double altura, double pesoCorporal) {
        // Fórmula de Dubois: Área de Superficie Corporal (m2) = 0.007184 x (altura^0.725) x (peso^0.425)
        return 0.007184 * Math.pow(altura, 0.725) * Math.pow(pesoCorporal, 0.425);
    }
}



