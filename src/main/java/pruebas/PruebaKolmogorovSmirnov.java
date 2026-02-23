package pruebas;

import java.util.Arrays;

/**
 * 
 * Clase que ejecuta la prueba de uniformidad Kolmogorov-Smirnov.
 */
public class PruebaKolmogorovSmirnov {

    public static String verificar(double[] u, double dCritico) {
        int n = u.length;

        // Copiamos el arreglo para no modificar el original
        double[] sortedU = Arrays.copyOf(u, n);

        // Paso 1: Ordenar la serie de menor a mayor
        Arrays.sort(sortedU);

        // Paso 2: Calcular D = max|Ui - i/N|
        double dMax = 0;
        for (int i = 0; i < n; i++) {
            double fnI = (i + 1.0) / n;
            double diferencia = Math.abs(sortedU[i] - fnI);
            if (diferencia > dMax) {
                dMax = diferencia;
            }
        }

        // Paso 4: Decisión
        if (dMax < dCritico) {
            return "H0 ACEPTADA: distribución uniforme OK";
        } else {
            return "H0 RECHAZADA: no sigue distribución uniforme";
        }
    }
}