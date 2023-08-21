package FAAKYPackage.DB;

import FAAKYPackage.Otros;
import FAAKYPackage.Entidades.*;

import java.util.Scanner;

public class registrosDB {
    Otros recursos = new Otros();

    Alumno alumno = new Alumno();
    Docente docente = new Docente();
    Administrativo adm = new Administrativo();
    Rentas renta = new Rentas();
    Pago pago = new Pago();

    DBC dbFAAKY = new DBC();
    ConsultasDB db = new ConsultasDB();
    ActualizacionesDB adb = new ActualizacionesDB();
    Scanner read = new Scanner(System.in);

    public void menuRegistros() {
        char opcion;

        // Loop principal del menú, se repetirá hasta que se elija 'E' (Salir)
        do {
            recursos.limpiarPantalla(); // Limpiar la pantalla para mostrar el menú de forma limpia
            System.out.println("========================================");
            System.out.println("  MENÚ DE REGISTROS Y MODIFICACIONES");
            System.out.println("========================================");
            System.out.println("1. Realizar un registro");
            System.out.println("2. Realizar una modificacion");
            System.out.println("3. Salir del Menú de Registros");
            System.out.println("========================================");
            System.out.print("Elige una opción: ");

            opcion = read.next().toUpperCase().charAt(0); // Leer la opción ingresada por el usuario

            switch (opcion) {
                case '1': // Registrar un alumno
                    recursos.limpiarPantalla();
                    char subopcionRegistros;
                    do {
                        // Submenú para el registro de un alumno

                        recursos.limpiarPantalla(); // Limpiar la pantalla para mostrar el menú de forma limpia
                        System.out.println("========================================");
                        System.out.println("        MENÚ DE REGISTROS");
                        System.out.println("========================================");
                        System.out.println("1. Registrar un alumno");
                        System.out.println("2. Registrar un docente");
                        System.out.println("3. Registrar un administrativo");
                        System.out.println("4. Registrar una renta");
                        System.out.println("5. Registrar el pago de una renta");
                        System.out.println("6. Salir del Menú de Registros");
                        System.out.println("========================================");
                        System.out.print("Elige una opción: ");
                        subopcionRegistros = read.next().toUpperCase().charAt(0);

                        switch (subopcionRegistros) {
                            case '1':

                                recursos.limpiarPantalla();
                                char subopcionAlumno;
                                do {
                                    // Submenú para el registro de un docente
                                    System.out.println("===== MENÚ REGISTRO ALUMNO =====");
                                    System.out.println("1. Proceder con el registro");
                                    System.out.println("2. Salir");
                                    System.out.println("========================================");
                                    System.out.print("Ingrese la opcion: ");
                                    subopcionAlumno = read.next().toUpperCase().charAt(0);

                                    switch (subopcionAlumno) {
                                        case '1': 
                                            alumno.registrarAlumno(dbFAAKY);
                                            break;
                                        case '2':
                                            System.out.println("Saliendo del menú Registro Alumno...");
                                            break;
                                        default:
                                            System.out.println(
                                                    "Opción inválida. Por favor, ingrese un número de opción válido.");
                                    }
                                } while (subopcionAlumno != '2');
                                break;
                            case '2':
                                recursos.limpiarPantalla();
                                char subopcionDocente;
                                do {
                                    // Submenú para el registro de un docente
                                    System.out.println("===== MENÚ REGISTRO DOCENTE =====");
                                    System.out.println("1. Proceder con el registro");
                                    System.out.println("2. Salir");
                                    System.out.println("========================================");
                                    System.out.print("Ingrese la opcion: ");
                                    subopcionDocente = read.next().toUpperCase().charAt(0);

                                    switch (subopcionDocente) {
                                        case '1': 
                                            docente.registrarDocente(dbFAAKY);
                                            break;
                                        case '2':
                                            System.out.println("Saliendo del menú Registro Docente...");
                                            break;
                                        default:
                                            System.out.println(
                                                    "Opción inválida. Por favor, ingrese un número de opción válido.");
                                    }
                                } while (subopcionDocente != '2');
                                break;
                            case '3':
                                recursos.limpiarPantalla();
                                char subopcionAdministrativo;
                                do {
                                    // Submenú para el registro de un docente
                                    System.out.println("===== MENÚ REGISTRO ADMINISTRATIVO =====");
                                    System.out.println("1. Proceder con el registro");
                                    System.out.println("2. Salir");
                                    System.out.println("========================================");
                                    System.out.print("Ingrese la opcion: ");
                                    subopcionAdministrativo = read.next().toUpperCase().charAt(0);

                                    switch (subopcionAdministrativo) {
                                        case '1':
                                            adm.registrarAdm(dbFAAKY);
                                            break;
                                        case '2':
                                            System.out.println("Saliendo del menú Registro Administrativo...");
                                            break;
                                        default:
                                            System.out.println(
                                                    "Opción inválida. Por favor, ingrese un número de opción válido.");
                                    }
                                } while (subopcionAdministrativo != '2');
                                break;
                            case '4':
                                recursos.limpiarPantalla();
                                char subopcionRenta;
                                do {
                                    // Submenú para el registro de un docente
                                    System.out.println("===== MENÚ REGISTRO RENTA =====");
                                    System.out.println("1. Proceder con el registro");
                                    System.out.println("2. Salir");
                                    System.out.println("========================================");
                                    System.out.print("Ingrese la opcion: ");
                                    subopcionRenta = read.next().toUpperCase().charAt(0);

                                    switch (subopcionRenta) {
                                        case '1':
                                            renta.registrarRenta(dbFAAKY);
                                            break;
                                        case '2':
                                            System.out.println("Saliendo del menú Registro renta...");
                                            break;
                                        default:
                                            System.out.println(
                                                    "Opción inválida. Por favor, ingrese un número de opción válido.");
                                    }
                                } while (subopcionRenta != '2');
                                break;
                            case '5':
                                recursos.limpiarPantalla();
                                char subopcionPago;
                                do {
                                    // Submenú para el registro de un docente
                                    System.out.println("===== MENÚ REGISTRO PAGO =====");
                                    System.out.println("1. Proceder con el registro");
                                    System.out.println("2. Salir");
                                    System.out.println("========================================");
                                    System.out.print("Ingrese la opcion: ");
                                    subopcionPago = read.next().toUpperCase().charAt(0);

                                    switch (subopcionPago) {
                                        case '1':
                                            pago.registrarPago(dbFAAKY);
                                            break;
                                        case '2':
                                            System.out.println("Saliendo del menú Registro pago...");
                                            break;
                                        default:
                                            System.out.println(
                                                    "Opción inválida. Por favor, ingrese un número de opción válido.");
                                    }
                                } while (subopcionPago != '2');
                                break;
                            
                            case '6':
                                System.out.println("Saliendo del menú Registro Alumno...");
                                break;
                            default:
                                System.out.println("Opción inválida. Por favor, ingrese un número de opción válido.");
                        }
                    } while (subopcionRegistros != '6');
                    break;
                case '2': // Menu de Actualizaciones
                    adb.menuActualizaciones();
                    break;
                case '3':
                    System.out.println("Saliendo del Menú de movimientos.");
                    break;
                default:
                    System.out.println("Opción no válida. Vuelve a intentarlo.");
                    break;
            }

            System.out.println();
        } while (opcion != '3');
    }

}
