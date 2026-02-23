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
        System.out.println("i\tUi\t\ti/N\t\t|Ui - i/N|");
        System.out.println("------------------------------------------------------");

        double dMax = 0;
        int posMax = 0;
        for (int i = 0; i < n; i++) {
            double fnI = (i + 1.0) / n;
            double diferencia = Math.abs(sortedU[i] - fnI);

            String uiStr = String.format("%.4f", sortedU[i]);
            String fniStr = String.format("%.4f", fnI);
            String difStr = String.format("%.4f", diferencia);

            System.out.println((i + 1) + "\t" + uiStr + "\t\t" + fniStr + "\t\t" + difStr);

            if (diferencia > dMax) {
                dMax = diferencia;
                posMax = i + 1;
            }
        }

        System.out.println("------------------------------------------------------");
        System.out.println("D calculado (max): " + String.format("%.4f", dMax) + " (en posicion " + posMax + ")");
        System.out.println("D critico:         " + String.format("%.4f", dCritico));
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