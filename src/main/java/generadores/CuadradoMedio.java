package generadores;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que encapsula el algoritmo de Cuadrado Medio.
 */
public class CuadradoMedio {

    Scanner scanner = new Scanner(System.in);
    long semilla;

    public void cuadradoMedio() {
        System.out.println("Método del Cuadrado Medio\n");
        System.out.println("Ingrese la semilla: ");
        semilla = scanner.nextLong();
        System.out.println();
        System.out.printf("%-6s %-12s %-18s %-18s %-12s %-12s%n", "n", "R(n)", "R(n)^2", "M.R(n)^2", "Val1", "Val2");
        System.out.println();
        int tamañoSemillaInicial = String.valueOf(semilla).length();
        cuadradoMedio(semilla, new ArrayList<>(), 0, tamañoSemillaInicial);
    }

    public void cuadradoMedio(long semilla, List<Long> valores, int n, int tamañoSemillaInicial) {

        if (semilla == 0 || valores.contains(semilla) || String.valueOf(semilla).length() < tamañoSemillaInicial) {
            return;
        }

        long cuadrado = (long) semilla * semilla;

        String formato = "%0" + (2 * tamañoSemillaInicial) + "d";
        String cadena = String.format(formato, cuadrado);
        int tamaño = cadena.length();
        int principio = (tamaño - tamañoSemillaInicial) / 2;

        long val1 = Long.parseLong(cadena.substring(principio, principio + tamañoSemillaInicial));
        long val2 = 0;

        if (principio + 1 + tamañoSemillaInicial <= tamaño) {
            val2 = Long.parseLong(cadena.substring(principio + 1, principio + 1 + tamañoSemillaInicial));
        }
        String medio = cadena.substring(principio, principio + 1 + tamañoSemillaInicial);
        System.out.printf("%-6d %-12d %-18s %-18s %-12d %-12d%n", n, semilla, cadena, medio, val1, val2);

        valores.add(semilla);

        long siguienteSemilla = val1;
        if (valores.contains(val1) || String.valueOf(val1).length() < tamañoSemillaInicial) {
            siguienteSemilla = val2;
        }

        cuadradoMedio(siguienteSemilla, valores, n + 1, tamañoSemillaInicial);
    }
}