package generadores;

import utilidades.GestorCSV;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

/**
 * Clase que encapsula el algoritmo Congruencial Lineal (Mixto).
 */

public class CongruencialLineal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Implementación del algoritmo

        /*
         * System.out.print("Ingresa la semilla: ");
         * int x0 = scanner.nextInt();
         * System.out.print("Ingresa el multiplicador: ");
         * int a = scanner.nextInt();
         * System.out.print("Ingresa la constante aditiva: ");
         * int c = scanner.nextInt();
         * System.out.print("Ingresa el módulo: ");
         * int m = scanner.nextInt();
         */

        GestorCSV gestor = new GestorCSV();

        String ruta = "PruebasParaNumerosAleatorios/datosParaPruebas/datos.csv";

        List<Double> numeros = gestor.leerNumeros(ruta);

        Random random = new Random();

        long a;
        do {
            double valor = numeros.get(random.nextInt(numeros.size()));
            a = (long) (valor * 1000);

        } while ((a - 1) % 4 != 0 || a <= 1);

        long c;
        do {
            double cf = numeros.get(random.nextInt(numeros.size()));
            c = (long) (cf * 1000);

        } while (c % 2 == 0 || c == 0);

        int pot = random.nextInt(3) + 6; // entre 2^5 y 2^8
        long m = (long) Math.pow(2, pot);

        long x0;
        do {
            double valor = numeros.get(random.nextInt(numeros.size()));
            x0 = (long) (valor * 1000);

        } while (x0 < 0 || x0 >= m);

        System.out.println("\n n \t Xn \t a * Xn + c\t \t (a * Xn + c) mod m");
        System.out.println("[---------------------------------------------------------]");
        int n = 0;
        Long axnc = a * x0 + c;
        Long mod = axnc % m;

        System.out.println(n + "\t" + x0 + "\t" + axnc + "\t\t\t " + mod);

        Long xn = mod;
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
