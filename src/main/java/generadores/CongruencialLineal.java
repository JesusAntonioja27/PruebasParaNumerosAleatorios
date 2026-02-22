package generadores;

import java.util.Scanner;
/*Los generadores congruenciales lineales generan una serie de números pseudo - aleatorios
 de tal forma que se puede generar el siguiente a partir del ultimo número derivado, 
 es decir, que el número Xn+1 es generado a partir de Xn.*/

/**
 * Clase que encapsula el algoritmo Congruencial Lineal (Mixto).
 */

public class CongruencialLineal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Implementación del algoritmo

        System.out.print("Ingresa la semilla: ");
        int x0 = scanner.nextInt();
        System.out.print("Ingresa el multiplicador: ");
        int a = scanner.nextInt();
        System.out.print("Ingresa la constante aditiva: ");
        int c = scanner.nextInt();
        System.out.print("Ingresa el módulo: ");
        int m = scanner.nextInt();

        System.out.println("\n n \t Xn \t a * Xn + c\t \t (a * Xn + c) mod m");
        System.out.println("[---------------------------------------------------------]");
        int n = 0;
        int axnc = a * x0 + c;
        int mod = axnc % m;

        System.out.println(n + "\t" + x0 + "\t" + axnc + "\t\t\t " + mod);

        int xn = mod;
        axnc = a * xn + c;
        mod = axnc % m;

        int periodo = 1;

        while (mod != x0) {
            n++;
            xn = mod;
            axnc = a * xn + c;
            mod = axnc % m;
            periodo++;
            System.out.println(n + "\t" + xn + "\t" + axnc + "\t\t\t " + mod);

        }
        n++;
        xn = mod;
        axnc = a * xn + c;
        mod = axnc % m;
        periodo++;
        System.out.println(n + "\t" + xn + "\t" + axnc + "\t\t\t " + mod);

        System.out.println("\nEl periodo es: " + periodo);
    }

    /*
     * Lee los valores necesarios para generar la serie de números aleatorios.
     * Funciona correctamente, muestra los valores generados, dice la longitud del
     * periodo,
     * y valida correctamente cuándo debe dejar de generar valores
     */

    /*
     * La relación de recurrencia para el método congruencial mixto es:
     * 
     * Xn+1 = (aXn + c) mod m
     */

    /*
     * Donde:
     * X0 = semilla (X0 >0)
     * a = multiplicador (a >0)
     * c = constante aditiva (c >0)
     * m = módulo (m >X0, m >a y m>c)
     */

}
