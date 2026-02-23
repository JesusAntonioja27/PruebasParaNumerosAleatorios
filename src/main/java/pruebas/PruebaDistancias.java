package pruebas;

import utilidades.CalculosEstadisticos;

/**
 * 
 * Clase que ejecuta la prueba de independencia de Distancias.
 */
public class PruebaDistancias {

    public static String verificar(double[] u, double alfaInt, double betaInt, int nMax, double x2Critico) {
        int n = u.length;
        double theta = betaInt - alfaInt;

        // Paso 1: Calcular probabilidades teóricas P[i]
        double[] p = new double[nMax + 1];
        for (int i = 0; i < nMax; i++) {
            p[i] = Math.pow(1 - theta, i) * theta;
        }
        p[nMax] = Math.pow(1 - theta, nMax);

        // Paso 2: Contar huecos observados O[i]
        int[] o = new int[nMax + 1];
        int totalHuecos = 0;
        boolean dentro = false;
        int hueco = 0;

        for (int j = 0; j < n; j++) {
            if (u[j] >= alfaInt && u[j] < betaInt) { // Número DENTRO del intervalo
                if (dentro) { // Cierra hueco anterior
                    int idx = Math.min(hueco, nMax);
                    o[idx]++;
                    totalHuecos++;
                }
                dentro = true;
                hueco = 0;
            } else { // Número FUERA del intervalo
                if (dentro) {
                    hueco++;
                }
            }
        }

        // Paso 3: Calcular FE[i] = total_huecos * P[i]
        double[] fe = new double[nMax + 1];
        for (int i = 0; i <= nMax; i++) {
            fe[i] = totalHuecos * p[i];
        }

        // Paso 4: Calcular X² usando CalculosEstadisticos (Sobrecarga con arreglo de
        // FE)
        double x2 = CalculosEstadisticos.calcularEstadisticoJiCuadrada(o, fe);

        // Paso 6: Decisión
        if (x2 < x2Critico) {
            return "H0 ACEPTADA: números son independientes (aleatoriedad OK)";
        } else {
            return "H0 RECHAZADA: números NO son independientes";
        }
    }
}