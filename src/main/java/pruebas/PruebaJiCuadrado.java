package pruebas;

import utilidades.CalculosEstadisticos;

/**
 * Clase que ejecuta la prueba de uniformidad Ji-Cuadrada.
 */
public class PruebaJiCuadrado {
    // arreglo bidimencional predefinido
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

    public boolean ejecutarPrueba(double[] numerosAleatorios) {
        int n = numerosAleatorios.length;
        // determinar numero de intervalos m, la reglaa comun es la raiz de N
        int m = (int) Math.sqrt(n);
        // calcular frecuencias esperadas
        double frecuenciaEsperada = (double) n / m;
        // calcular frecuencias observadas
        int[] frecuenciasObservadas = CalculosEstadisticos.calcularJiCuadrada(numerosAleatorios, m);

        double estadisticoCalculado = CalculosEstadisticos.calcularEstadisticoJiCuadrada(frecuenciasObservadas,
                frecuenciaEsperada);

        int gradosLibertad = m - 1;

        double valorCriticoTabla = buscarEnTabla(gradosLibertad);
        if (valorCriticoTabla == -1) {
            System.out.println("No se encontro el valor critico para los grados de libertad");
            return false;
        }

        // Imprimir resultados para que el usuario los vea
        System.out.println("--- Resultados para prueba de Ji cuadrada ---");
        System.out.println("Tamaño de muestra: " + n);
        System.out.println("Intervalos: " + m);
        System.out.println("Grados de libertad: " + gradosLibertad);
        System.out.println("Frecuencia esperada: " + frecuenciaEsperada);
        System.out.println("Estadistico calculado: " + estadisticoCalculado);
        System.out.println("Valor critico de tabla: " + valorCriticoTabla);
        if (estadisticoCalculado <= valorCriticoTabla) {
            System.out.println("Aceptamos la hipotesis nula");
            return true;
        } else {
            System.out.println("Rechazamos la hipotesis nula");
            return false;
        }
    }

    private double buscarEnTabla(int valorBuscado) {
        for (int i = 0; i < tablaChi.length; i++) {
            if (tablaChi[i][0] == valorBuscado) {
                double valorTabla = tablaChi[i][1];
                return valorTabla;
            }
        }
        return -1;
    }
}
