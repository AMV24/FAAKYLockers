package FAAKYPackage;

import java.util.Scanner;
import FAAKYPackage.DB.ConsultasDB;

public class Locker {
    // Definicion de las variables de la clase
    private int id_locker;
    private String tamanio, ubicacion, disponibilidad;

    // Otros objetos necesario
    Otros recursos = new Otros(); // Para limpiar la pantalla
    ConsultasDB dbFAAKY = new ConsultasDB(); // Para la conexion a la base de datps
    Scanner read = new Scanner(System.in); // Para leer datos por teclado

    // Constructores de la clase
    public Locker() {}
    public Locker(int id, String t, String u) {
        this.id_locker = id;
        this.tamanio = t;
        this.ubicacion = u;
    }

    // Setters y Getters

    public int getIDLocker() {
        return id_locker;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String t) {
        this.tamanio = t;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String u) {
        this.ubicacion = u;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String d) {
        this.disponibilidad = d;
    }

    // Menu del apartado de Lockers
    public void menuLockers() {

        char opcion;
    
        recursos.limpiarPantalla(); // Limpia la pantalla
        // Ciclo del menú principal de lockers
        do {
            // Mostrar opciones del menú
            System.out.println("========================================");
            System.out.println("       MENÚ DE OPCIONES DE LOCKERS");
            System.out.println("========================================");
            System.out.println("1. Lockers disponibles para rentar por docencia");
            System.out.println("2. Disponibilidad de lockers de una docencia");
            System.out.println("3. Lista de precios");
            System.out.println("4. Lockers totales");
            System.out.println("5. Lockers por piso");
            System.out.println("6. Salir del Menú de Lockers");
            System.out.println("========================================");
            System.out.print("Elige una opción: ");
            opcion = read.next().toUpperCase().charAt(0); // Lee la opcion del menú
    
            switch (opcion) {
                case '1':
                    recursos.limpiarPantalla(); // Limpia la pantalla
                    char subopcionDocencia;
                    do {
                        // Creación de un submenu para seleccionar la docencia
                        System.out.println("===== LISTAS POR DOCENCIA =====");
                        System.out.println("1. Docencia 1");
                        System.out.println("2. Docencia 2");
                        System.out.println("3. Docencia 3");
                        System.out.println("4. Docencia 4");
                        System.out.println("5. Salir");
                        System.out.println("===========================");
                        System.out.print("Ingrese la opcion: ");
                        subopcionDocencia = read.next().toUpperCase().charAt(0);
    
                        switch (subopcionDocencia) {
                            case '1':
                                dbFAAKY.consultarListaDisponibilidadDocencia(1); // Manda a llamar la consulta con el argumento para la docencia 1
                                break;
                            case '2':
                                dbFAAKY.consultarListaDisponibilidadDocencia(2); // Manda a llamar la consulta con el argumento para la docencia 2
                                break;
                            case '3':
                                dbFAAKY.consultarListaDisponibilidadDocencia(3); // Manda a llamar la consulta con el argumento para la docencia 3
                                break;
                            case '4':
                                dbFAAKY.consultarListaDisponibilidadDocencia(4); // Manda a llamar la consulta con el argumento para la docencia 4
                                break;
                            case '5':
                                System.out.println("Saliendo del Submenú Docencia...");
                                break;
                            default:
                                System.out.println(
                                        "Opción inválida. Por favor, ingrese un número de opción válido.");
                        }
                    } while (subopcionDocencia != '5');
                    break;
                case '2':
                    recursos.limpiarPantalla(); // Limpia la pantalla
                    char subopcionDocencia2;
                    do {
                        // Creación de un submenu para seleccionar la docencia
                        System.out.println("===== LISTAS POR DOCENCIA =====");
                        System.out.println("1. Docencia 1");
                        System.out.println("2. Docencia 2");
                        System.out.println("3. Docencia 3");
                        System.out.println("4. Docencia 4");
                        System.out.println("5. Salir");
                        System.out.println("===========================");
                        System.out.print("Ingrese la opcion: ");
                        subopcionDocencia2 = read.next().toUpperCase().charAt(0);
    
                        switch (subopcionDocencia2) {
                            case '1':
                                dbFAAKY.consultarDisponibilidadLockers(1); // Manda a llamar la consulta con el argumento para la docencia 1
                                break;
                            case '2':
                                dbFAAKY.consultarDisponibilidadLockers(2); // Manda a llamar la consulta con el argumento para la docencia 2
                                break;
                            case '3':
                                dbFAAKY.consultarDisponibilidadLockers(3); // Manda a llamar la consulta con el argumento para la docencia 3
                                break;
                            case '4':
                                dbFAAKY.consultarDisponibilidadLockers(4); // Manda a llamar la consulta con el argumento para la docencia 4
                                break;
                            case '5':
                                System.out.println("Saliendo del Submenú Docencia...");
                                break;
                            default:
                                System.out.println(
                                        "Opción inválida. Por favor, ingrese un número de opción válido.");
                        }
                    } while (subopcionDocencia2 != '5');
                    break;
                case '3':
                    recursos.limpiarPantalla();
                    dbFAAKY.consultarListaPrecios(); // Manda a llamar la consulta de lista de precios
                    break;
                case '4':
                    dbFAAKY.consultarTotalLockersDocencia(); // Manda a llamar la consulta de la cantidad total de lockers en cada docnecia
                    break;
                case '5':
                    recursos.limpiarPantalla();
                    char subopcionDocencia3;
                    do {
                        // Creación de un submenu para seleccionar la docencia
                        System.out.println("===== LISTAS POR DOCENCIA =====");
                        System.out.println("1. Docencia 1");
                        System.out.println("2. Docencia 2");
                        System.out.println("3. Docencia 3");
                        System.out.println("4. Docencia 4");
                        System.out.println("5. Salir");
                        System.out.println("===========================");
                        System.out.print("Ingrese el número de opción que desea ejecutar: ");
                        subopcionDocencia3 = read.next().toUpperCase().charAt(0);
    
                        switch (subopcionDocencia3) {
                            case '1':
                                dbFAAKY.consultarTotalLockersPisoDocencia(1); // Manda a llamar la consulta con el argumento para la docencia 1
                                break;
                            case '2':
                                dbFAAKY.consultarTotalLockersPisoDocencia(2); // Manda a llamar la consulta con el argumento para la docencia 2
                                break;
                            case '3':
                                dbFAAKY.consultarTotalLockersPisoDocencia(3); // Manda a llamar la consulta con el argumento para la docencia 3
                                break;
                            case '4':
                                dbFAAKY.consultarTotalLockersPisoDocencia(4); // Manda a llamar la consulta con el argumento para la docencia 4
                                break;
                            case '5':
                                System.out.println("Saliendo del Submenú Docencia...");
                                break;
                            default:
                                System.out.println(
                                        "Opción inválida. Por favor, ingrese un número de opción válido.");
                        }
                    } while (subopcionDocencia3 != '5'); // Si no se ingresa la 'E', continuará mostrando el menú hasta que se ingrese la 'E'
                    break;
                case '6':
                    System.out.println("Saliendo del Menú de Lockers.");
                    break;
                default:
                    System.out.println("Opción no válida. Vuelve a intentarlo.");
                    break;
            }
    
            System.out.println();
        } while (opcion != '6'); // Si no se ingresa la 'E', continuará mostrando el menú hasta que se ingrese la 'E'
    }
    
}
