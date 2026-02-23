package generadores;

import utilidades.GestorCSV;
import java.util.List;
import java.util.Random;
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

        String ruta = "PruebasParaNumerosAleatorios/datosParaPruebas/datos.csv";

        List<Double> numeros = gestor.leerNumeros(ruta);

        Random random = new Random();

        int pot = random.nextInt(3) + 6; // entre 2^5 y 2^8
        long m = (long) Math.pow(2, pot);

        long a;
        do {
            double valor = numeros.get(random.nextInt(numeros.size()));
            a = (long) (valor * 1000);

        } while (!(a % 8 == 3 || a % 8 == 5) || a <= 1 || a >= m);

        long x0;
        do {
            double valor = numeros.get(random.nextInt(numeros.size()));
            x0 = (long) (valor * 1000);

        } while (x0 % 2 == 0 || x0 < 0 || x0 >= m);

        System.out.println("\n n \t Xn \t a * Xn\t \t (a * Xn) mod m");
        System.out.println("[---------------------------------------------------------]");
        int n = 0;
        Long axn = a * x0;
        Long mod = axn % m;

        System.out.println(n + "\t" + x0 + "\t" + axn + "\t\t\t " + mod);

        Long xn = mod;
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
