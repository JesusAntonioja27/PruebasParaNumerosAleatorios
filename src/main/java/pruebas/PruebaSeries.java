package pruebas;

import java.util.List;

public class PruebaSeries {

    public void ejecutar(List<Double> numeros) {

        double[][] tablaChi = {
            { 1, 3.841 }, { 2, 5.991 }, { 3, 7.815 }, { 4, 9.488 }, { 5, 11.070 },
            { 6, 12.592 }, { 7, 14.067 }, { 8, 15.507 }, { 9, 16.919 }, { 10, 18.307 },
            { 11, 19.675 }, { 12, 21.026 }, { 13, 22.362 }, { 14, 23.685 }, { 15, 24.996 },
            { 16, 26.296 }, { 17, 27.587 }, { 18, 28.869 }, { 19, 30.144 }, { 20, 31.410 },
            { 21, 32.671 }, { 22, 33.924 }, { 23, 35.172 }, { 24, 36.415 }, { 25, 37.652 },
            { 26, 38.885 }, { 27, 40.113 }, { 28, 41.337 }, { 29, 42.557 }, { 30, 43.773 },
            { 31, 44.985 }, { 32, 46.194 }, { 33, 47.400 }, { 34, 48.602 }, { 35, 49.802 },
            { 40, 55.758 }, { 50, 67.500 }, { 60, 79.1 }, { 100, 124.3 }
    };
        int N = numeros.size();
        int n = 4;
        int[][] O = new int[n][n];

        System.out.println("Pares \t Ui \t\tUi + 1 \n------------------------------");
        for (int k = 0; k < N; k++) {
            double u1 = numeros.get(k);
            double u2 = numeros.get((k + 1) % N); 

            System.out.printf("%d \t %.4f \t%.4f%n", k + 1, u1, u2);

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
        double sech = (n * n) - 1;
        double vc = 1;

        for (int i = 0; i < tablaChi.length; i++) {
         if ((int) tablaChi[i][0] == sech) {
        vc = tablaChi[i][1];
        break;
         }
        }

        System.out.println("\n\nX2 calculado = " + ji + "\n");
        System.out.println("\n\nX2 critico = "+ vc + "," + n +"\n");

        
        
        if (ji < sech) {
            System.out.println("Se acepta la hipotesis \n");
        } else {
            System.out.println("Se rechaza la hipotesis \n");
        }
    }
}