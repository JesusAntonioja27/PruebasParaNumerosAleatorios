package src.main.java.principal;

/**
 * Punto de entrada del programa.
 * Su única responsabilidad es instanciar la clase Menu e iniciar la ejecución.
 */
public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.mostrarMenu();
    }
}
