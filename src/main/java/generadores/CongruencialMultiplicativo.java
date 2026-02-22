package generadores;

import java.util.Scanner;

/**
 * Clase que encapsula el algoritmo Congruencial Multiplicativo.
 */
public class CongruencialMultiplicativo {
    // Implementación del algoritmo

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Implementación del algoritmo

        System.out.print("Ingresa la semilla: ");
        int x0 = scanner.nextInt();
        System.out.print("Ingresa el multiplicador: ");
        int a = scanner.nextInt();
        System.out.print("Ingresa el módulo: ");
        int m = scanner.nextInt();

        System.out.println("\n n \t Xn \t a * Xn\t \t (a * Xn) mod m");
        System.out.println("[---------------------------------------------------------]");
        int n = 0;
        int axn = a * x0;
        int mod = axn % m;

        System.out.println(n + "\t" + x0 + "\t" + axn + "\t\t\t " + mod);

        int xn = mod;
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
