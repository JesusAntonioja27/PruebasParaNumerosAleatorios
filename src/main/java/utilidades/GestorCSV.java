package utilidades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestorCSV {
    public List<Double> leerNumeros(String rutaDeArchivo) {
        List<Double> datos = new ArrayList<>();
        boolean primeraLinea = true;

        try (BufferedReader br = new BufferedReader(new FileReader(rutaDeArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) {
                    continue;
                }

                try {
                    linea = linea.replace(",", ".");
                    double numero = Double.parseDouble(linea);
                    datos.add(numero);
                } catch (NumberFormatException e) {
                    if (primeraLinea) {
                    } else {
                        System.err.println("No se pudo convertir a numero la linea: '" + linea + "'");
                    }
                }
                primeraLinea = false;
            }
        } catch (IOException e) {
            System.err.println("No se encontro el archivo en la rutaa: " + rutaDeArchivo);
            e.printStackTrace();
        }

        return datos;
    }
}
