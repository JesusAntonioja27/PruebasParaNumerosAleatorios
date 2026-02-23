package utilidades;

public class CalculosEstadisticos {
    public static int[] calcularJiCuadrada(double[] numerosAleatorios, int m) {
        int[] frecuenciasObservadas = new int[m];
        double tamañoIntervalo = 1.0 / m;
        for (int i = 0; i < numerosAleatorios.length; i++) {
            int indice = (int) (numerosAleatorios[i] / tamañoIntervalo);

            if (indice >= m) {
                indice = m - 1;
            }

            frecuenciasObservadas[indice]++;
        }
        return frecuenciasObservadas;
    }

    // metodo para aplicar la formula: sumatoria de ((FO-FE)al cuadrado / FE)
    public static double calcularEstadisticoJiCuadrada(int[] frecuenciasObservadas, double frecuenciaEsperada) {
        double estadisticoC = 0;
        for (int i = 0; i < frecuenciasObservadas.length; i++) {
            double diferencia = frecuenciasObservadas[i] - frecuenciaEsperada;
            double diferenciaAlCuadrado = Math.pow(diferencia, 2);

            estadisticoC = estadisticoC + (diferenciaAlCuadrado / frecuenciaEsperada);

        }
        return estadisticoC;
    }

    // metodo sobrecargado para arreglo de FE (FE variables)
    public static double calcularEstadisticoJiCuadrada(int[] frecuenciasObservadas, double[] frecuenciasEsperadas) {
        double estadisticoC = 0;
        for (int i = 0; i < frecuenciasObservadas.length; i++) {
            if (frecuenciasEsperadas[i] == 0)
                continue; // Evitar division por cero
            double diferencia = frecuenciasObservadas[i] - frecuenciasEsperadas[i];
            double diferenciaAlCuadrado = Math.pow(diferencia, 2);
            estadisticoC = estadisticoC + (diferenciaAlCuadrado / frecuenciasEsperadas[i]);
        }
        return estadisticoC;
    }
}