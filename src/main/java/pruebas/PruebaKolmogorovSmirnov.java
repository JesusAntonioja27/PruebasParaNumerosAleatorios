package pruebas;

import java.util.Arrays;

public class PruebaKolmogorovSmirnov {

    public static String verificar(double[] u, double dCritico) {
        int n = u.length;
        double[] sortedU = Arrays.copyOf(u, n);
        Arrays.sort(sortedU);

        System.out.println("--- Resultados para prueba de Kolmogorov-Smirnov ---");
        System.out.println("Tamaño de muestra: " + n);
        System.out.println();
        System.out.printf("%-6s %-12s %-12s %-12s%n", "i", "Ui", "i/N", "|Ui - i/N|");
        System.out.println("---------------------------------------------");

        double dMax = 0;
        int posMax = 0;
        for (int i = 0; i < n; i++) {
            double fnI = (i + 1.0) / n;
            double diferencia = Math.abs(sortedU[i] - fnI);
            System.out.printf("%-6d %-12.4f %-12.4f %-12.4f%n", (i + 1), sortedU[i], fnI, diferencia);
            if (diferencia > dMax) {
                dMax = diferencia;
                posMax = i + 1;
            }
        }

        System.out.println("---------------------------------------------");
        System.out.printf("D calculado (max): %.4f (en posicion %d)%n", dMax, posMax);
        System.out.printf("D critico:         %.4f%n", dCritico);
        System.out.println();

        if (dMax < dCritico) {
            System.out.println("Aceptamos la hipotesis nula");
            return "H0 ACEPTADA: distribucion uniforme OK";
        } else {
            System.out.println("Rechazamos la hipotesis nula");
            return "H0 RECHAZADA: no sigue distribucion uniforme";
        }
    }
}