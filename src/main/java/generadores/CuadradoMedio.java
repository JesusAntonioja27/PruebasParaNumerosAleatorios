package generadores;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CuadradoMedio {

    private int periodo = 0;

    public void ejecutar(Scanner scanner) {
        System.out.println("Metodo del Cuadrado Medio\n");
        System.out.print("Ingrese la semilla: ");
        long semilla = scanner.nextLong();
        System.out.println();
        System.out.println("n\tR(n)\tR(n)^2\t\tM.R(n)^2\tVal1\tVal2");
        System.out.println();
        periodo = 0;
        cuadradoMedio(semilla, new ArrayList<>(), 0);
        System.out.println("\nEl periodo es: " + periodo);
    }

    private void cuadradoMedio(long semilla, List<Long> valores, int n) {
        if (semilla == 0) {
            return;
        }
        if (valores.contains(semilla)) {
            return;
        }
        valores.add(semilla);
        periodo++;

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
        System.out.println(n + "\t" + semilla + "\t" + cadena + "\t\t" + medio + "\t\t" + val1 + "\t" + val2);
        cuadradoMedio(val1, valores, n + 1);
    }
}