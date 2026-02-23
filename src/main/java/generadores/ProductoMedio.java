package generadores;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que encapsula el algoritmo de Producto Medio.
 */
public class ProductoMedio {

    Scanner scanner = new Scanner(System.in);
    long semilla1, semilla2;

    public void productoMedio() {
        System.out.println("Método del Producto Medio\n");
        System.out.println("Ingrese la primera semilla: ");
        semilla1 = scanner.nextLong();
        System.out.println("Ingrese la segunda semilla: ");
        semilla2 = scanner.nextLong();
        System.out.println();
        System.out.printf("%-6s %-12s %-12s %-18s %-14s %-12s %-12s%n",
                "n", "R(n)", "R(n+1)", "R(n)*R(n+1)", "M.R(n)", "Val1", "Val2");
        System.out.println();

        productoMedio(semilla1, semilla2, new ArrayList<>(), 0);
    }

    private void productoMedio(long semilla1, long semilla2, List<String> valores, int n) {
        if (semilla1 == 0 || semilla2 == 0) {
            return;
        }

        String multiplicacion = "" + semilla1 + semilla2;
        if (valores.contains(multiplicacion)) {
            return;
        }
        valores.add(multiplicacion);

        int tamañoSemilla = ("" + semilla1).length();
        long producto = semilla1 * semilla2;

        String formato = "%0" + (2 * tamañoSemilla) + "d";
        String cadena = String.format(formato, producto);
        int tamaño = cadena.length();
        int principio = (tamaño - tamañoSemilla) / 2;
        long val1 = Long.parseLong(cadena.substring(principio, principio + tamañoSemilla));
        long val2 = 0;

        if (principio + 1 + tamañoSemilla <= tamaño) {
            val2 = Long.parseLong(cadena.substring(principio + 1, principio + 1 + tamañoSemilla));
        }

        String medio = cadena.substring(principio, principio + 1 + tamañoSemilla);
        System.out.printf("%-6d %-12d %-12d %-18s %-14s %-12d %-12d%n", n, semilla1, semilla2, cadena, medio,
                val1, val2);

        productoMedio(semilla2, val1, valores, n + 1);
    }
}
