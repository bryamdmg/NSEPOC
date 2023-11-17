import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Biblioteca {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double multa;
        int diasRetraso = 0;

        System.out.println("Ingrese el tipo de libro (no limitado o restringido): ");
        String tipoLibro = scanner.nextLine().toLowerCase();

        System.out.println("¿El libro fue extraviado? (s/n)");
        String extraviado = scanner.nextLine().toLowerCase();

        if ("no limitado".equals(tipoLibro) || "restringido".equals(tipoLibro)) {
            if ("s".equals(extraviado)) {
                multa = calcularextravio(tipoLibro);
                mostrarMulta(multa);
            } else if ("n".equals(extraviado)) {

                System.out.print("Ingrese la fecha en que debió ser devuelto (dd/MM/yyyy): ");
                String fechaDevolucionEsperadaStr = scanner.nextLine();

                System.out.print("Ingrese la fecha en que se devolvió (dd/MM/yyyy): ");
                String fechaDevolucionActualStr = scanner.nextLine();

                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date fechaDevolucionEsperada = dateFormat.parse(fechaDevolucionEsperadaStr);
                    Date fechaDevolucionActual = dateFormat.parse(fechaDevolucionActualStr);

                    if(fechaDevolucionEsperada.after(fechaDevolucionActual)) {
                        System.out.println("Error: La fecha de devolución esperada debe ser anterior a la fecha de devolución actual.");
                    } else {
                        diasRetraso = calcularDiasRetraso(fechaDevolucionEsperada, fechaDevolucionActual);
                    }

                } catch (ParseException e) {
                    System.out.println("Error al parsear las fechas. Asegúrese de usar el formato dd/MM/yyyy.");
                }

                if (diasRetraso > 0) {
                    multa = calcularMulta(tipoLibro, diasRetraso);
                    mostrarMulta(multa);
                } else {
                    System.out.println("Error: Los días de restraso deben ser mayores a 0");
                }
            } else {
                System.out.println("Error: No se indentifico si el libro esta extraviado o no");
            }
        } else {
            System.out.println("Error: Tipo de libro no válido. Debe ser 'no limitado' o 'restringido'.");
        }
    }

    private static double calcularMulta(String tipoLibro, int diasRetraso) {
        double multa = 0;

        if ("no limitado".equals(tipoLibro) && diasRetraso > 3) {
            multa = (50 + (diasRetraso - 3) * 25) - 25;
        } else if ("restringido".equals(tipoLibro)) {
            multa = 150 * diasRetraso;
        }

        return multa;
    }

    private static int calcularDiasRetraso(Date fechaDevolucionEsperada, Date fechaDevolucionActual) {
        long diff = fechaDevolucionActual.getTime() - fechaDevolucionEsperada.getTime();
        return (int) (diff / (24 * 60 * 60 * 1000));
    }

    private static double calcularextravio(String tipoLibro) {
        double extravio = 0;

        if ("no limitado".equals(tipoLibro)) {
            extravio = 1500;
        } else if ("restringido".equals(tipoLibro)) {
            extravio = 5000;
        }

        return extravio;
    }

    private static void mostrarMulta(double multa) {
        if (multa > 0) {
            System.out.println("Multa por retraso: $" + multa);
        } else {
            System.out.println("No hay multa por retraso.");
        }
    }
}