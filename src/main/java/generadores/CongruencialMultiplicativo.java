package generadores;

import java.util.Scanner;

public class CongruencialMultiplicativo {

    public void congruencialMultiplicativo() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese un valor para m que sea potencia de 2: ");
        long m = scanner.nextLong();
        while ((m & (m - 1)) != 0 || m <= 1) {
            System.out.println("Ingrese un valor para m que sea potencia de 2: ");
            m = scanner.nextLong();
        }

        System.out.println("Ingrese un valor para a: ");
        long a = scanner.nextLong();
        while (!(a % 8 == 3 || a % 8 == 5)) {
            System.out.println("Ingrese un valor para a que sea multiplo de 8 mas 3 o 5: ");
            a = scanner.nextLong();
        }

        System.out.println("Ingrese un valor para x0: ");
        long x0 = scanner.nextLong();
        while (x0 % 2 == 0 || x0 >= m) {
            System.out.println("Ingrese un valor para x0 que sea impar y menor a m: ");
            x0 = scanner.nextLong();
        }

        System.out.println("\n n\tXn\ta * Xn\t\t(a * Xn) mod m");
        System.out.println("---------------------------------------------------------");

        int n = 1;
        Long axn = a * x0;
        Long mod = axn % m;

        System.out.println(n + "\t" + x0 + "\t" + axn + "\t\t" + mod);

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
            System.out.println(n + "\t" + xn + "\t" + axn + "\t\t" + mod);
        }

        n++;
        xn = mod;
        axn = a * xn;
        mod = axn % m;
        periodo++;

        System.out.println(n + "\t" + xn + "\t" + axn + "\t\t" + mod);

        System.out.println("\nEl periodo es: " + periodo);
    }
}