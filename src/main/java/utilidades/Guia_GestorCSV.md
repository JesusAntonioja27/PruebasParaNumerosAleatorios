# Guía de Uso: GestorCSV

Esta clase (`GestorCSV`) ha sido diseñada para ser lo más simple posible. Su único objetivo es leer tus archivos `.csv` en la carpeta `datosParaPruebas` y devolverte una lista de los números para que puedas usarlos en tus algoritmos.

## ¿Cómo funciona?
El método principal es `leerNumeros(String rutaArchivo)`.
1. Abre el archivo en la ruta que le indiques.
2. Lee línea por línea.
3. Intenta convertir cada línea en un número decimal (`Double`).
4. **Si la primera línea es texto (ej: "datos", "Valor"), la ignora automáticamente** entendiendo que es un encabezado.
5. Devuelve una lista (`List<Double>`) con todos los valores extraídos, lista para ser procesada.

## ¿Cómo usarla en tus algoritmos de pruebas/generadores?

Para usarla en tu código, solo necesitas instanciarla y mandarle la ruta del archivo.

**Ejemplo de código:**
```java
import utilidades.GestorCSV;
import java.util.List;

public class MainPrueba {
    public static void main(String[] args) {
        GestorCSV gestor = new GestorCSV();
        
        // Coloca la ruta a tu archivo CSV. Puedes usar la ruta relativa desde la raíz del proyecto.
        String ruta = "datosParaPruebas/datos.csv";
        
        // Obtenemos la lista de números
        List<Double> numeros = gestor.leerNumeros(ruta);
        
        // Imprimimos la cantidad y algunos para verificar
        System.out.println("Se leyeron " + numeros.size() + " números.");
        for (int i = 0; i < Math.min(5, numeros.size()); i++) {
            System.out.println(numeros.get(i));
        }
    }
}
```

## ¿Qué pasa si mis archivos CSV cambian de formato?
Actualmente el código espera **un solo número por línea**, sin importar si tiene encabezado o no (ejemplo: `pruebas3.csv` o `datos.csv`).

### Si tu CSV empieza a usar comas para separar decimales (Ej: `0,1234`)
El código **ya está preparado** para esto. Automáticamente reemplaza las comas por puntos antes de intentar leer el número. No necesitas cambiar nada.

### Si tu CSV tiene múltiples columnas separadas por comas (Ej: `ID,Valor`)
Actualmente el código asume 1 solo número por línea. Si en el futuro tus datos vienen en varias columnas (ej. `1,0.9291` donde 1 es el id y 0.9291 el valor), tendrías que modificar el archivo `GestorCSV.java` de la siguiente manera:

1. Modifica la parte donde se lee la línea dentro de `GestorCSV.java`:
```java
// Código actual:
// double numero = Double.parseDouble(linea);

// Código modificado para CSV de múltiples columnas:
String[] columnas = linea.split(",");
// Extraes el número de la columna que te interesa (por ejemplo la segunda columna, índice 1)
double numero = Double.parseDouble(columnas[1]);
```
