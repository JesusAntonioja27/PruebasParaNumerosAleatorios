package generadores;

import utilidades.GestorCSV;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que encapsula el algoritmo Congruencial Multiplicativo.
 */
public class CongruencialMultiplicativo {
    // Implementación del algoritmo

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Implementación del algoritmo

        /*
         * System.out.print("Ingresa la semilla: ");
         * int x0 = scanner.nextInt();
         * System.out.print("Ingresa el multiplicador: ");
         * int a = scanner.nextInt();
         * System.out.print("Ingresa el módulo: ");
         * int m = scanner.nextInt();
         */

        GestorCSV gestor = new GestorCSV();

        String ruta = "datosParaPruebas/datos.csv";

        List<Double> numeros = gestor.leerNumeros(ruta);

        double x0 = numeros.get(0);
        double a = numeros.get(1);
        double m = numeros.get(2);

        System.out.println("\n n \t Xn \t a * Xn\t \t (a * Xn) mod m");
        System.out.println("[---------------------------------------------------------]");
        double n = 0;
        double axn = a * x0;
        double mod = axn % m;

        System.out.println(n + "\t" + x0 + "\t" + axn + "\t\t\t " + mod);

        double xn = mod;
        axn = a * xn;
        mod = axn % m;

        int periodo = 1;

        while (mod != x0) {
            n++;
            xn = mod;
            axn = a * xn;
            mod = axn % m;
            periodo++;
            System.out.println(n + "\t" + xn + "\t" + axn + "\t\t\t " + mod);

        }
        n++;
        xn = mod;
        axn = a * xn;
        mod = axn % m;
        periodo++;

        System.out.println(n + "\t" + xn + "\t" + axn + "\t\t\t " + mod);

        System.out.println("\nEl periodo es: " + periodo);

    }
}
