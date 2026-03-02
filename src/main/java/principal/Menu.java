package principal;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import utilidades.GestorCSV;
import generadores.CongruencialLineal;
import generadores.CongruencialMultiplicativo;
import generadores.CuadradoMedio;
import generadores.ProductoMedio;
import pruebas.PruebaJiCuadrado;
import pruebas.PruebaKolmogorovSmirnov;
import pruebas.PruebaDistancias;
import pruebas.PruebaSeries;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private GestorCSV gestorCSV = new GestorCSV();
    private static final String CARPETA_DATOS = "datosParaPruebas";

    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n==========================================");
            System.out.println("             PROYECTO SIMULACION            ");
            System.out.println("==========================================");
            System.out.println("  1. Generadores");
            System.out.println("  2. Pruebas");
            System.out.println("  3. Salir");
            System.out.println("==========================================");
            System.out.print("  Seleccione una opcion: ");

            int opcion = leerEntero();

            switch (opcion) {
                case 1:
                    menuGeneradores();
                    break;
                case 2:
                    menuPruebas();
                    break;
                case 3:
                    salir = true;
                    System.out.println("\n  Hasta luego.\n");
                    break;
                default:
                    System.out.println("\n  Opcion no valida, intente de nuevo.");
            }
        }
    }

    private void menuGeneradores() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n------------------------------------------");
            System.out.println("          GENERADORES                     ");
            System.out.println("------------------------------------------");
            System.out.println("  1. Cuadrado Medio");
            System.out.println("  2. Producto Medio");
            System.out.println("  3. Congruencial Lineal");
            System.out.println("  4. Congruencial Multiplicativo");
            System.out.println("  5. Volver");
            System.out.println("------------------------------------------");
            System.out.print("  Seleccione una opcion: ");

            int opcion = leerEntero();

            switch (opcion) {
                case 1:
                    System.out.println();
                    CuadradoMedio cm = new CuadradoMedio();
                    cm.cuadradoMedio();
                    pausar();
                    break;
                case 2:
                    System.out.println();
                    ProductoMedio pm = new ProductoMedio();
                    pm.productoMedio();
                    pausar();
                    break;
                case 3: {
                    String ruta = seleccionarCSV();
                    if (ruta != null) {
                        List<Double> numeros = gestorCSV.leerNumeros(ruta);
                        if (!numeros.isEmpty()) {
                            CongruencialLineal cl = new CongruencialLineal();
                            cl.ejecutar(numeros);
                        } else {
                            System.out.println("  El archivo no tiene datos validos.");
                        }
                    }
                    pausar();
                    break;
                }
                case 4: {
                    String ruta = seleccionarCSV();
                    if (ruta != null) {
                        List<Double> numeros = gestorCSV.leerNumeros(ruta);
                        if (!numeros.isEmpty()) {
                            CongruencialMultiplicativo cmu = new CongruencialMultiplicativo();
                            cmu.ejecutar(numeros);
                        } else {
                            System.out.println("  El archivo no tiene datos validos.");
                        }
                    }
                    pausar();
                    break;
                }
                case 5:
                    volver = true;
                    break;
                default:
                    System.out.println("\n  Opcion no valida, intente de nuevo.");
            }
        }
    }

    private void menuPruebas() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n------------------------------------------");
            System.out.println("          PRUEBAS                         ");
            System.out.println("------------------------------------------");
            System.out.println("  1. Ji-Cuadrada");
            System.out.println("  2. Kolmogorov-Smirnov");
            System.out.println("  3. Series");
            System.out.println("  4. Distancias");
            System.out.println("  5. Volver");
            System.out.println("------------------------------------------");
            System.out.print("  Seleccione una opcion: ");

            int opcion = leerEntero();

            switch (opcion) {
                case 1:
                    ejecutarJiCuadrada();
                    pausar();
                    break;
                case 2:
                    ejecutarKolmogorov();
                    pausar();
                    break;
                case 3:
                    ejecutarSeries();
                    pausar();
                    break;
                case 4:
                    ejecutarDistancias();
                    pausar();
                    break;
                case 5:
                    volver = true;
                    break;
                default:
                    System.out.println("\n  Opcion no valida, intente de nuevo.");
            }
        }
    }

    private void ejecutarJiCuadrada() {
        String ruta = seleccionarCSV();
        if (ruta == null)
            return;

        List<Double> numeros = gestorCSV.leerNumeros(ruta);
        if (numeros.isEmpty()) {
            System.out.println("  El archivo no tiene datos validos.");
            return;
        }

        double[] arreglo = new double[numeros.size()];
        for (int i = 0; i < numeros.size(); i++) {
            arreglo[i] = numeros.get(i);
        }

        PruebaJiCuadrado prueba = new PruebaJiCuadrado();
        System.out.println();
        prueba.ejecutarPrueba(arreglo);
    }

    private void ejecutarKolmogorov() {
        String ruta = seleccionarCSV();
        if (ruta == null)
            return;

        List<Double> numeros = gestorCSV.leerNumeros(ruta);
        if (numeros.isEmpty()) {
            System.out.println("  El archivo no tiene datos validos.");
            return;
        }

        double[] arreglo = new double[numeros.size()];
        for (int i = 0; i < numeros.size(); i++) {
            arreglo[i] = numeros.get(i);
        }

        System.out.print("\n  Ingrese el valor D critico: ");
        double dCritico = leerDouble();

        if (dCritico <= 0) {
            System.out.println("  Error: Valor no válido, prueba cancelada.");
            return;
        }

        String resultado = PruebaKolmogorovSmirnov.verificar(arreglo, dCritico);
        System.out.println("\n  Resultado: " + resultado);
    }

    private void ejecutarSeries() {
        String ruta = seleccionarCSV();
        if (ruta == null)
            return;

        List<Double> numeros = gestorCSV.leerNumeros(ruta);
        if (numeros.isEmpty()) {
            System.out.println("  El archivo no tiene datos validos.");
            return;
        }

        PruebaSeries prueba = new PruebaSeries();
        System.out.println();
        prueba.ejecutar(numeros);
    }

    private void ejecutarDistancias() {
        String ruta = seleccionarCSV();
        if (ruta == null)
            return;

        List<Double> numeros = gestorCSV.leerNumeros(ruta);
        if (numeros.isEmpty()) {
            System.out.println("  El archivo no tiene datos validos.");
            return;
        }

        double[] arreglo = new double[numeros.size()];
        for (int k = 0; k < numeros.size(); k++) {
            arreglo[k] = numeros.get(k);
        }

        System.out.print("\n  Ingrese alfa del intervalo: ");
        double alfa = leerDouble();
        System.out.print("  Ingrese beta del intervalo: ");
        double beta = leerDouble();
        System.out.print("  Ingrese nMax: ");
        int nMax = leerEntero();
        System.out.print("  Ingrese X2 critico: ");
        double x2Critico = leerDouble();

        if (nMax <= 0 || alfa < 0 || beta < alfa || x2Critico <= 0) {
            System.out.println("  Error: Valores para la prueba no validos, cancelando.");
            return;
        }

        String resultado = PruebaDistancias.verificar(arreglo, alfa, beta, nMax, x2Critico);
        System.out.println("\n  Resultado: " + resultado);
    }

    private String seleccionarCSV() {
        File carpeta = new File(CARPETA_DATOS);
        if (!carpeta.exists() || !carpeta.isDirectory()) {
            System.out.println("  No se encontro la carpeta: " + CARPETA_DATOS);
            return null;
        }

        File[] archivos = carpeta.listFiles((dir, nombre) -> nombre.endsWith(".csv"));
        if (archivos == null || archivos.length == 0) {
            System.out.println("  No hay archivos CSV disponibles.");
            return null;
        }

        System.out.println("\n  Archivos CSV disponibles:");
        for (int i = 0; i < archivos.length; i++) {
            System.out.println("    " + (i + 1) + ". " + archivos[i].getName());
        }
        System.out.print("  Seleccione un archivo: ");

        int seleccion = leerEntero();
        if (seleccion < 1 || seleccion > archivos.length) {
            System.out.println("  Seleccion no valida.");
            return null;
        }

        return archivos[seleccion - 1].getPath();
    }

    private int leerEntero() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private double leerDouble() {
        try {
            return Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void pausar() {
        System.out.println("\n ENTER para continuar");
        scanner.nextLine();
    }
}
