package generadores;

import java.util.List;
import java.util.Random;

public class CongruencialLineal {

    public void ejecutar(List<Double> numeros) {
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

        int pot = random.nextInt(3) + 6;
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
}
