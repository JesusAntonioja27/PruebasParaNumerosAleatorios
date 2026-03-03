package generadores;

import java.util.Scanner;

public class CongruencialLineal {

    public void congruenciaLineal() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese un valor para m que sea potencia de 2: ");
        long m = scanner.nextLong();
        while (m <= 0 || (m & (m - 1)) != 0) {
            System.out.println("Ingrese un valor para m que sea potencia de 2: ");
            m = scanner.nextLong();
        }

        System.out.println("Ingrese un valor para a: ");
        long a = scanner.nextLong();

        while ((a - 1) % 4 != 0) {
            System.out.println("Ingrese un valor para a que sea multiplo de 4 mas 1: ");
            a = scanner.nextLong();

        }

        System.out.println("Ingrese un valor para c: ");
        long c = scanner.nextLong();
        while (c % 2 == 0) {
            System.out.println("Ingrese un valor para c que sea un número entero impar: ");
            c = scanner.nextLong();
        }

        System.out.println("Ingrese un valor para x0: ");
        long x0 = scanner.nextLong();
        while (x0 >= m) {
            System.out.println("Ingrese un valor para x0 que sea menor a m: ");
            x0 = scanner.nextLong();

        }

        System.out.println("\n n\tXn\ta * Xn + c\t(a * Xn + c) mod m");
        System.out.println("---------------------------------------------------------");

        int n = 1;
        Long axnc = a * x0 + c;
        Long mod = axnc % m;

        System.out.println(n + "\t" + x0 + "\t" + axnc + "\t\t" + mod);

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
            System.out.println(n + "\t" + xn + "\t" + axnc + "\t\t" + mod);
        }

        n++;
        xn = mod;
        axnc = a * xn + c;
        mod = axnc % m;
        periodo++;

        System.out.println(n + "\t" + xn + "\t" + axnc + "\t\t" + mod);

        System.out.println("\nEl periodo es: " + periodo);
    }
}