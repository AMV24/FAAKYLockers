package FAAKYPackage.DB;

import FAAKYPackage.Otros;
import FAAKYPackage.Entidades.*;

import java.util.Scanner;

public class ActualizacionesDB {
    Otros recursos = new Otros();

    Alumno alumno = new Alumno();
    Docente docente = new Docente();
    Administrativo adm = new Administrativo();
    Rentas renta = new Rentas();
    Pago pago = new Pago();

    DBC dbFAAKY = new DBC();
    ConsultasDB db = new ConsultasDB();
    Scanner read = new Scanner(System.in);

    public void menuActualizaciones() {
        char opcion;
    
        // Loop principal del menú, se repetirá hasta que se elija 'E' (Salir)
        do {
            recursos.limpiarPantalla(); // Limpiar la pantalla para mostrar el menú de forma limpia
            System.out.println("========================================");
            System.out.println("        MENÚ DE ACTUALIZACIONES");
            System.out.println("========================================");
            System.out.println("1. Actualizar un alumno");
            System.out.println("2. Actualizar un docente");
            System.out.println("3. Actualizar un administrativo");
            System.out.println("4. Salir del Menú de Actualizaciones");
            System.out.println("========================================");
            System.out.print("Elige una opción: ");
    
            opcion = read.next().toUpperCase().charAt(0); // Leer la opción ingresada por el usuario
    
            switch (opcion) {
                case '1':
                    System.out.println("Ingresa la matricula del alumno: ");
                    int idAlu = read.nextInt();
                    alumno.mostrarMenuActualizar(dbFAAKY, idAlu);
                    break;
                case '2':
                    System.out.println("Ingresa el numero de empleado del docente: ");
                    int idDoc = read.nextInt();
                    docente.mostrarMenuActualizar(dbFAAKY, idDoc);
                    break;
                case '3':                    
                    System.out.println("Ingresa el numero de empleado del administrativo: ");
                    int idAdm = read.nextInt();
                    adm.mostrarMenuActualizar(dbFAAKY, idAdm);
                    break;
                case '4':
                    System.out.println("Saliendo del Menú de actualizaciones.");
                    break;
                default:
                    System.out.println("Opción no válida. Vuelve a intentarlo.");
                    break;
            }
    
            System.out.println();
        } while (opcion != '4');
    }
    
}
