package pruebas;

import utilidades.GestorCSV;
import java.util.List;

/**
 * 
 * Clase que ejecuta la prueba de independencia de Series.
 */
public class PruebaSeries {
    // Implementación de la prueba
    public static void main(String[] args) {
        GestorCSV gestor = new GestorCSV();

        // Coloca la ruta a tu archivo CSV. Puedes usar la ruta relativa desde la raíz
        // del proyecto.
        String ruta = "PruebasParaNumerosAleatorios/datosParaPruebas/datos.csv";

        // Obtenemos la lista de números
        List<Double> numeros = gestor.leerNumeros(ruta);

        int N = numeros.size() - 1; // pares n-1
        int n = 5;
        // u1 u2 u3 u4 u5
        // u1 u2
        // u2 u3
        // u3 u4
        // u4 u5
        int[][] O = new int[n][n];// matriz de frecuencia observada de pares en la celda OIJ

        // Pares
        System.out.println("Pares \t Ui \t\tUi + 1 \n------------------------------");
        for (int k = 0; k < N; k++) {
            System.out.printf("%d \t %.4f \t%.4f%n", k + 1, numeros.get(k), numeros.get(k + 1));// k+1 indica el inicio
                                                                                                // de la
            // tabla en 1 y no en 0

            double u1 = numeros.get(k);
            double u2 = numeros.get(k + 1);
            // double u1 y u2 son numeros entre 0 y 1
            // int i y j son numeros entre 0 y n-1

            int i = (int) (u1 * n);// convierte el decimal en un entero
            int j = (int) (u2 * n);

            if (i == n)
                i = n - 1;// si el numero es 1 se convierte en n-1
            if (j == n)
                j = n - 1;

            O[i][j]++;
        }
        System.out.println("------------------------------ \n\n");

        double E = (double) N / (n * n); // formula de la frecuencia esperada

        // OIJ
        System.out.println("\nTabla Oij\n--------------------------------------------");

        // Encabezado columnas
        System.out.print("        ");
        for (int j = 0; j < n; j++) {
            double b = (double) (j + 1) / n;
            System.out.printf("%7.2f", b);
        }
        System.out.println();

        // Filas con rangos
        for (int i = n - 1; i >= 0; i--) {
            double b = (double) (i + 1) / n;

            // etiqueta fila
            System.out.printf("%.2f", b);

            // datos
            for (int j = 0; j < n; j++) {
                System.out.printf("%8d", O[i][j]);
            }
            System.out.println("\n");
        }

        // EIJ
        System.out.println("\nTabla Eij\n--------------------------------------------");

        // Encabezado columnas
        System.out.print("        ");
        for (int j = 0; j < n; j++) {
            double b = (double) (j + 1) / n;
            System.out.printf("%7.2f", b);
        }
        System.out.println();

        // Filas con rangos
        for (int i = n - 1; i >= 0; i--) {
            double b = (double) (i + 1) / n;

            // etiqueta fila
            System.out.printf("%.2f", b);

            // datos
            for (int j = 0; j < n; j++) {
                System.out.printf("%8.2f", E);
            }
            System.out.println("\n");
        }

        // Tabla de diferencia
        System.out.println("\nTabla de diferencia\n--------------------------------------------");

        // Encabezado columnas
        System.out.print("        ");
        for (int j = 0; j < n; j++) {
            double b = (double) (j + 1) / n;
            System.out.printf("%7.2f", b);
        }
        System.out.println();

        // Filas con rangos
        for (int i = n - 1; i >= 0; i--) {
            double b = (double) (i + 1) / n;

            // etiqueta fila
            System.out.printf("%.2f", b);

            // datos
            for (int j = 0; j < n; j++) {
                System.out.printf("%8.2f", O[i][j] - E);
            }
            System.out.println("\n");
        }

        // Tabla de diferencia al cuadrado entre EIJ
        System.out.println("\nTabla de diferencia al cuadrado entre EIJ\n--------------------------------------------");
        double ji = 0;

        // Encabezado columnas
        System.out.print("        ");
        for (int j = 0; j < n; j++) {
            double b = (double) (j + 1) / n;
            System.out.printf("%7.2f", b);
        }
        System.out.println();

        // Filas con rangos
        for (int i = n - 1; i >= 0; i--) {
            double b = (double) (i + 1) / n;

            // etiqueta fila
            System.out.printf("%.2f", b);

            // datos

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

        // Imprimimos la cantidad y algunos para verificar

    }
}