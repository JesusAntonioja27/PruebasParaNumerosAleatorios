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
        boolean dentro = false;
        int hueco = 0;

        for (int j = 0; j < n; j++) {
            if (u[j] >= alfaInt && u[j] < betaInt) {
                if (dentro) {
                    int idx = Math.min(hueco, nMax);
                    o[idx]++;
                    totalHuecos++;
                }
                dentro = true;
                hueco = 0;
            } else {
                if (dentro) {
                    hueco++;
                }
            }
        }

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
        System.out.printf("%-12s %-12s %-12s %-12s %-14s%n", "Distancia", "P[i]", "FO", "FE", "(FO-FE)^2/FE");
        System.out.println("--------------------------------------------------------------");

        for (int i = 0; i <= nMax; i++) {
            String etiqueta = (i < nMax) ? String.valueOf(i) : i + "+";
            double parcial = (fe[i] == 0) ? 0 : Math.pow(o[i] - fe[i], 2) / fe[i];
            System.out.printf("%-12s %-12.4f %-12d %-12.4f %-14.4f%n", etiqueta, p[i], o[i], fe[i], parcial);
        }

        System.out.println("--------------------------------------------------------------");
        System.out.printf("X2 calculado: %.4f%n", x2);
        System.out.printf("X2 critico:   %.4f%n", x2Critico);
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