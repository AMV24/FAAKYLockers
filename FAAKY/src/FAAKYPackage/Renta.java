package FAAKYPackage;

import java.util.Scanner;
import FAAKYPackage.DB.ConsultasDB;

public class Renta {
    // Variables de clase
    private int id_renta;
    private int id_solicitante;
    private int docencia;

    // Otros objetos necesario
    Otros recursos = new Otros(); // Para limpiar la pantalla
    ConsultasDB dbFAAKY = new ConsultasDB(); // Para la conexion a la base de datos
    Scanner read = new Scanner(System.in); // Para leer datos por teclado

    // Constructores
    public Renta() {
    }

    public Renta(int r, int s, int d) {
        this.id_renta = r;
        this.id_solicitante = s;
    }

    // Setters y Getters
    public int getIDRenta() {
        return id_renta;
    }

    public void setIDRenta(int r) {
        this.id_renta = r;
    }

    public int getIDSolicitante() {
        return id_solicitante;
    }

    public void setIDSolicitante(int s) {
        this.id_solicitante = s;
    }

    public int getDocencia() {
        return docencia;
    }

    public void setDocencia(int d) {
        this.docencia = d;
    }

    // Creacion del menu rentas
    public void menuRentas() {

        char opcion;

        // Limpiar la pantalla
        recursos.limpiarPantalla();

        // Ciclo del menú principal de rentas
        do {
            // Mostrar opciones del menú
            System.out.println("=========================================");
            System.out.println("          MENÚ DE RENTAS");
            System.out.println("=========================================");
            System.out.println("| 1. Datos de una renta                 |");
            System.out.println("| 2. Alumno con renta                   |");
            System.out.println("| 3. Rentas por docencia                |");
            System.out.println("| 4. Solicitantes                       |");
            System.out.println("| 5. Mostrar rentas por finalizar       |");
            System.out.println("| 6. Salir del Menú de Rentas           |");
            System.out.println("=========================================");
            System.out.print("Elige una opción: ");

            // Leer opción ingresada por el usuario
            opcion = read.next().toUpperCase().charAt(0);

            switch (opcion) {
                case '1':
                    // Submenú para consultar datos de una renta por tipo de solicitante (Alumno,
                    // Docente o Administrativo)
                    System.out.println("=========================================");
                    System.out.println("    CONSULTA LOS DATOS DE LA RENTA");
                    System.out.println("=========================================");
                    System.out.println("   1. Alumno");
                    System.out.println("   2. Docente");
                    System.out.println("   3. Administrativo");
                    System.out.println("=========================================");
                    System.out.println();
                    System.out.print("Elige una opción: ");
                    char subopcion = read.next().toUpperCase().charAt(0);
                    read.nextLine();

                    switch (subopcion) {
                        case '1':
                            // Consultar datos de una renta para un Alumno
                            System.out.println("========================================");
                            System.out.print("Ingresa el número de matrícula: ");
                            setIDSolicitante(read.nextInt());
                            recursos.limpiarPantalla();
                            dbFAAKY.consultarDatosRentaAlumno(getIDSolicitante());
                            break;
                        case '2':
                            // Consultar datos de una renta para un Docente
                            System.out.println("========================================");
                            System.out.print("Ingresa el número de empleado: ");
                            setIDSolicitante(read.nextInt());
                            recursos.limpiarPantalla();
                            dbFAAKY.consultarDatosRentaDocente(getIDSolicitante());
                            break;
                        case '3':
                            // Consultar datos de una renta para un Administrativo
                            System.out.println("========================================");
                            System.out.print("Ingresa el número de empleado: ");
                            setIDSolicitante(read.nextInt());
                            recursos.limpiarPantalla();
                            dbFAAKY.consultarDatosRentaAdministrativo(getIDSolicitante());
                            break;
                        default:
                            System.out.println("Opción no válida. Vuelve a intentarlo.");
                            break;
                    }

                    break;
                case '3':
                    // Consultar lista de rentas por docencia (Docencia 1, 2, 3, 4)
                    recursos.limpiarPantalla();
                    char subopcionDocencia;
                    do {
                        System.out.println("===== LISTAS POR DOCENCIA =====");
                        System.out.println("1. Docencia 1.");
                        System.out.println("2. Docencia 2.");
                        System.out.println("3. Docencia 3.");
                        System.out.println("4. Docencia 4.");
                        System.out.println("5. Salir.");
                        System.out.println("===========================");
                        System.out.print("Ingrese la opción: ");
                        subopcionDocencia = read.next().toUpperCase().charAt(0);

                        switch (subopcionDocencia) {
                            case '1':
                                // Consultar lista de rentas para Docencia 1
                                dbFAAKY.consultarListaRentasDocencia(1);
                                break;
                            case '2':
                                // Consultar lista de rentas para Docencia 2
                                dbFAAKY.consultarListaRentasDocencia(2);
                                break;
                            case '3':
                                // Consultar lista de rentas para Docencia 3
                                dbFAAKY.consultarListaRentasDocencia(3);
                                break;
                            case '4':
                                // Consultar lista de rentas para Docencia 4
                                dbFAAKY.consultarListaRentasDocencia(4);
                                break;
                            case '5':
                                System.out.println("Saliendo del Submenú Docencia...");
                                break;
                            default:
                                System.out.println("Opción inválida. Por favor, ingrese un número de opción válido.");
                                break;
                        }
                    } while (subopcionDocencia != '5');
                    break;
                case '2':
                    // Consultar lista de rentas de alumnos
                    recursos.limpiarPantalla();
                    dbFAAKY.consultarListaRentaAlumnos();
                    break;
                case '4':
                    // Consultar lista de solicitantes
                    // Submenú para consultar datos de una renta por tipo de solicitante (Alumno,
                    // Docente o Administrativo)
                    System.out.println("=========================================");
                    System.out.println("    CONSULTAR EL SOLICITANTE");
                    System.out.println("=========================================");
                    read.nextLine();
                    int numero;
                    boolean numSolicitanteValido = false;
                    while (!numSolicitanteValido) {
                        System.out.print("Ingrese el numero de empleado o matricula: ");
                        try {
                            numero = Integer.parseInt(read.nextLine());
                            dbFAAKY.consultarListaSolicitantes(numero);
                            numSolicitanteValido = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Número inválido. Ingrese un número válido.");

                        }
                    }
                    break;
                case '5':
                    dbFAAKY.consultarRentasPorFinalizar();
                    break;
                case '6':
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Vuelve a intentarlo.");
                    break;
            }
        } while (opcion != '6');
    }

}