package generadores;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
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
        cuadradoMedio(semilla, new ArrayList<>(), 0);
    }

    public void cuadradoMedio(long semilla, List<Long> valores, int n) {
        if (semilla == 0) {
            return;
        }
        if (valores.contains(semilla)) {
            return;
        }
        valores.add(semilla);
        int tamañoSemilla = ("" + semilla).length();
        long cuadrado = (long) semilla * semilla;

        String formato = "%0" + (2 * tamañoSemilla) + "d";
        String cadena = String.format(formato, cuadrado);
        int tamaño = cadena.length();
        int principio = (tamaño - tamañoSemilla) / 2;

        long val1 = Long.parseLong(cadena.substring(principio, principio + tamañoSemilla));
        long val2 = 0;

        if (principio + 1 + tamañoSemilla <= tamaño) {
            val2 = Long.parseLong(cadena.substring(principio + 1, principio + 1 + tamañoSemilla));
        }
        String medio = cadena.substring(principio, principio + 1 + tamañoSemilla);
        System.out.printf("%-6d %-12d %-18s %-18s %-12d %-12d%n", n, semilla, cadena, medio, val1, val2);
        cuadradoMedio(val1, valores, n + 1);
    }
}