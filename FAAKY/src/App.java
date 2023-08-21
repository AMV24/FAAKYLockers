/*
 * ================================
 *       FAAKY CORPORATION
 * ================================
 *
 * DESARROLLADORES:
 * - Dominguez Correa Yaretzi Veymar
 * - Gutiérrez Hernandez Karime
 * - Matehuala Valenzuela Abraham
 * - Ruiz Padilla Julián Aldyerik
 * - Hernandez Muñoz Fernando
 */
import FAAKYPackage.*;
import FAAKYPackage.DB.registrosDB;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        // Bienvenida, creación de objetos y variables del programa.
        System.out.println("¡BIENVENIDOS A FAAKY LOCKERS CORPORATION!");

        // Creacion del objeto de Rentas, Lockers para mostrar su menú y otros.
        Renta r = new Renta();
        Locker l = new Locker();
        registrosDB rDB = new registrosDB();

        // Creación de instancia de Otros para utilizar funciones útiles y limpiar la
        // pantalla.
        Otros recursos = new Otros();

        char opcion;

        // Ciclo que muestra el menú principal hasta que el usuario elija la opción 'E'.
        do {
            recursos.limpiarPantalla(); // Limpia la pantalla antes de mostrar el menú.
            recursos.menuPrincipal(); // Muestra el menú principal.
            opcion = read.next().toUpperCase().charAt(0); // Lee la opción ingresada por el usuario.

            switch (opcion) {
                case '1':
                    r.menuRentas(); // Muestra el menú de rentas.
                    break;
                case '2':
                    l.menuLockers(); // Muestra el menú de lockers.
                    break;
                case '3':
                    rDB.menuRegistros(); // Muestra el menú de registros.
                    break;
                case '4':
                    System.out.println("SALIENDO DEL PROGRAMA"); // Sale del programa.
                    break;
                default:
                    System.out.println("Ingrese un valor válido!"); // Opción inválida.
                    break;
            }
        } while (opcion != '4'); // Repetir el bucle mientras no se elija '4'.

        read.close(); // Cerrar el objeto Scanner al finalizar el programa.
    }
}