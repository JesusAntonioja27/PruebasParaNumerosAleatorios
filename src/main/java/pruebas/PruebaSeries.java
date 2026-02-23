package pruebas;

import java.util.List;

public class PruebaSeries {

    public void ejecutar(List<Double> numeros) {
        int N = numeros.size() - 1;
        int n = 5;
        int[][] O = new int[n][n];

        System.out.println("Pares \t Ui \t\tUi + 1 \n------------------------------");
        for (int k = 0; k < N; k++) {
            System.out.printf("%d \t %.4f \t%.4f%n", k + 1, numeros.get(k), numeros.get(k + 1));

            double u1 = numeros.get(k);
            double u2 = numeros.get(k + 1);

            int i = (int) (u1 * n);
            int j = (int) (u2 * n);

            if (i == n)
                i = n - 1;
            if (j == n)
                j = n - 1;

            O[i][j]++;
        }
        System.out.println("------------------------------ \n\n");

        double E = (double) N / (n * n);

        System.out.println("\nTabla Oij\n--------------------------------------------");

        System.out.print("        ");
        for (int j = 0; j < n; j++) {
            double b = (double) (j + 1) / n;
            System.out.printf("%7.2f", b);
        }
        System.out.println();

        for (int i = n - 1; i >= 0; i--) {
            double b = (double) (i + 1) / n;
            System.out.printf("%.2f", b);
            for (int j = 0; j < n; j++) {
                System.out.printf("%8d", O[i][j]);
            }
            System.out.println("\n");
        }

        System.out.println("\nTabla Eij\n--------------------------------------------");

        System.out.print("        ");
        for (int j = 0; j < n; j++) {
            double b = (double) (j + 1) / n;
            System.out.printf("%7.2f", b);
        }
        System.out.println();

        for (int i = n - 1; i >= 0; i--) {
            double b = (double) (i + 1) / n;
            System.out.printf("%.2f", b);
            for (int j = 0; j < n; j++) {
                System.out.printf("%8.2f", E);
            }
            System.out.println("\n");
        }

        System.out.println("\nTabla de diferencia\n--------------------------------------------");

        System.out.print("        ");
        for (int j = 0; j < n; j++) {
            double b = (double) (j + 1) / n;
            System.out.printf("%7.2f", b);
        }
        System.out.println();

        for (int i = n - 1; i >= 0; i--) {
            double b = (double) (i + 1) / n;
            System.out.printf("%.2f", b);
            for (int j = 0; j < n; j++) {
                System.out.printf("%8.2f", O[i][j] - E);
            }
            System.out.println("\n");
        }

        System.out.println("\nTabla de diferencia al cuadrado entre EIJ\n--------------------------------------------");
        double ji = 0;

        System.out.print("        ");
        for (int j = 0; j < n; j++) {
            double b = (double) (j + 1) / n;
            System.out.printf("%7.2f", b);
        }
        System.out.println();

        for (int i = n - 1; i >= 0; i--) {
            double b = (double) (i + 1) / n;
            System.out.printf("%.2f", b);
            for (int j = 0; j < n; j++) {
                double val = (Math.pow(O[i][j] - E, 2) / E);
                ji += val;
                System.out.printf("%8.2f", val);
            }
            System.out.println("\n");
        }

        System.out.println("\n\n X2 calculado = " + ji + "\n");

        double sech = ji * ((n * n) - 1);

        if (ji < sech) {
            System.out.println("Se acepta la hipotesis \n");
        } else {
            System.out.println("Se rechaza la hipotesis \n");
        }
    }
}