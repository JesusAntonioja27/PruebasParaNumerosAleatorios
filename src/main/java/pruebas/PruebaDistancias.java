package pruebas;

import utilidades.CalculosEstadisticos;

public class PruebaDistancias {

    public static String verificar(double[] u, double alfaInt, double betaInt, int nMax, double x2Critico) {
        int n = u.length;
        double theta = betaInt - alfaInt;

        double[] p = new double[nMax + 1];
        for (int i = 0; i < nMax; i++) {
            p[i] = Math.pow(1 - theta, i) * theta;
        }
        p[nMax] = Math.pow(1 - theta, nMax);

        int[] o = new int[nMax + 1];
        int totalHuecos = 0;
        int hueco = 0;

        for (int j = 0; j < n; j++) {
            if (u[j] >= alfaInt && u[j] < betaInt) {
                int idx = Math.min(hueco, nMax);
                o[idx]++;
                totalHuecos++;
                hueco = 0;

            } else {
                hueco++;
            }
        }
        int idxFin = Math.min(hueco, nMax);
        o[idxFin]++;
        totalHuecos++;

        double[] fe = new double[nMax + 1];
        for (int i = 0; i <= nMax; i++) {
            fe[i] = totalHuecos * p[i];
        }

        double x2 = CalculosEstadisticos.calcularEstadisticoJiCuadrada(o, fe);

        System.out.println("--- Resultados para prueba de Distancias ---");
        System.out.println("Intervalo: [" + alfaInt + ", " + betaInt + ")");
        System.out.println("Theta: " + theta);
        System.out.println("Total de huecos: " + totalHuecos);
        System.out.println();

        System.out.println("Distancia\tP[i]\t\tFO\tFE\t\t(FO-FE)^2/FE");
        System.out.println("----------------------------------------------------------------------");

        for (int i = 0; i <= nMax; i++) {
            String etiqueta = (i < nMax) ? String.valueOf(i) : i + "+";
            double parcial = (fe[i] == 0) ? 0 : Math.pow(o[i] - fe[i], 2) / fe[i];

            // formateo simple
            String pStr = String.format("%.4f", p[i]);
            String feStr = String.format("%.4f", fe[i]);
            String parStr = String.format("%.4f", parcial);

            System.out.println(etiqueta + "\t\t" + pStr + "\t\t" + o[i] + "\t" + feStr + "\t\t" + parStr);
        }

        System.out.println("----------------------------------------------------------------------");
        System.out.println("X2 calculado: " + String.format("%.4f", x2));
        System.out.println("X2 critico:   " + String.format("%.4f", x2Critico));
        System.out.println();

        if (x2 < x2Critico) {
            System.out.println("Aceptamos la hipotesis nula");
            return "H0 ACEPTADA: numeros son independientes (aleatoriedad OK)";
        } else {
            System.out.println("Rechazamos la hipotesis nula");
            return "H0 RECHAZADA: numeros NO son independientes";
        }
    }
}