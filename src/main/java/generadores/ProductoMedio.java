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

        if (String.valueOf(semilla1).length() != String.valueOf(semilla2).length()) {
            System.out.println("Error: Las semillas deben tener el mismo número de dígitos.");
            return;
        }

        System.out.println();
        System.out.printf("%-6s %-12s %-12s %-18s %-14s %-12s %-12s%n",
                "n", "R(n)", "R(n+1)", "R(n)*R(n+1)", "M.R(n)", "Val1", "Val2");
        System.out.println();

        int tamañoSemillaInicial = String.valueOf(semilla1).length();
        productoMedio(semilla1, semilla2, new ArrayList<>(), 0, tamañoSemillaInicial);
    }

    private void productoMedio(long semilla1, long semilla2, List<String> multiplicaciones, int n,
            int tamañoSemillaInicial) {
        long producto = semilla1 * semilla2;

        String formato = "%0" + (2 * tamañoSemillaInicial) + "d";
        String cadena = String.format(formato, producto);
        int tamaño = cadena.length();
        int principio = (tamaño - tamañoSemillaInicial) / 2;

        long val1 = Long.parseLong(cadena.substring(principio, principio + tamañoSemillaInicial));
        long val2 = 0;
        if (principio + 1 + tamañoSemillaInicial <= tamaño) {
            val2 = Long.parseLong(cadena.substring(principio + 1, principio + 1 + tamañoSemillaInicial));
        }

        String medio = cadena.substring(principio, principio + 1 + tamañoSemillaInicial);
        System.out.printf("%-6d %-12d %-12d %-18s %-14s %-12d %-12d%n", n, semilla1, semilla2, cadena, medio, val1,
                val2);

        long menor = Math.min(semilla1, semilla2);
        long mayor = Math.max(semilla1, semilla2);
        String claveMultiplicacion = menor + "x" + mayor;
        if (multiplicaciones.contains(claveMultiplicacion)) {
            return;
        }
        multiplicaciones.add(claveMultiplicacion);

        long siguienteSemilla = val1;
        if (String.valueOf(val1).length() < tamañoSemillaInicial) {
            siguienteSemilla = val2;
            if (String.valueOf(val2).length() < tamañoSemillaInicial) {
                return;
            }
        }

        productoMedio(semilla2, siguienteSemilla, multiplicaciones, n + 1, tamañoSemillaInicial);
    }
}