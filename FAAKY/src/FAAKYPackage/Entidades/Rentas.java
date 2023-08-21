package FAAKYPackage.Entidades;

import FAAKYPackage.*;
import FAAKYPackage.DB.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Rentas {
    private int numRenta, numSolicitante, numLocker, cantidad, numeroRenta, metodoPago, numero, idRenta, idPago;
    private String fechaInicio, fechaFin, nombreS, fechaMySQL;

    private Connection cDBF;
    private Statement statement;

    Scanner read = new Scanner(System.in);
    Otros rc = new Otros();
    ConsultasDB db = new ConsultasDB();

    public Rentas() {
    }

    public Rentas(int nr, int ns, int nl, String f) {
        this.numRenta = nr;
        this.numSolicitante = ns;
        this.numLocker = nl;
        this.fechaInicio = f;
    }

    public int getNumeroRenta() {
        return numRenta;
    }

    public int getNumeroSolicitante() {
        return numSolicitante;
    }

    public int getNumeroLocker() {
        return numLocker;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setNumeroRenta(int n) {
        this.numRenta = n;
    }

    public void setNumeroSolicitante(int n) {
        this.numSolicitante = n;
    }

    public void setNumeroLocker(int n) {
        this.numLocker = n;
    }

    public void setFechaInicio(String f) {
        this.fechaInicio = f;
    }

    public static String calcularFechaFinal(String fechaInicio) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate fechaInicial = LocalDate.parse(fechaInicio, formatter);

            // Sumar exactamente 4 meses
            LocalDate fechaFinal = fechaInicial.plusMonths(4);

            return fechaFinal.format(formatter);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void registrarRenta(DBC dbConexion) {
        try {
            // Establecer la conexión a la base de datos
            cDBF = dbConexion.getConexion();
            statement = cDBF.createStatement();
            ResultSet resultSet;

            System.out.println("==== Registro de Renta ====");
            boolean fechaInicioValida = false;
            while (!fechaInicioValida) {
                System.out.print("Ingrese la fecha de inicio (DD-MM-YYYY): ");
                this.fechaInicio = read.nextLine();
                try {
                    SimpleDateFormat sdfInput = new SimpleDateFormat("dd-MM-yyyy");
                    sdfInput.setLenient(false); // Desactiva el modo permisivo para la validación estricta
                    sdfInput.parse(this.fechaInicio);
                    fechaInicioValida = true;
                } catch (ParseException e) {
                    System.out.println("Fecha de inicio inválida. Ingrese la fecha en el formato DD-MM-YYYY.");
                }
            }

            this.fechaFin = calcularFechaFinal(fechaInicio);

            SimpleDateFormat sdfInput = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyy-MM-dd");

            boolean numeroValido = false;
            while (!numeroValido) {
                System.out.print("Ingrese el numero de matricula o numero de empleado: ");
                try {
                    this.numero = Integer.parseInt(read.nextLine());

                    String consultaSolicitanteQuery = "SELECT Num_Solicitante, CONCAT(Nombre_Pila, ' ', Ap_Paterno, ' ', ApMaterno) AS Nombre "
                            +
                            "FROM Solicitante " +
                            "WHERE " + this.numero + " IN (Alumno, Docente, Admnistrativo)";

                    resultSet = statement.executeQuery(consultaSolicitanteQuery);
                    if (!resultSet.isBeforeFirst()) {
                        System.out.println("No hay solicitantes con ese numero de matricula y/o empleado");
                    } else {
                        numeroValido = true;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Ingrese una matricula y/o numero de empleado válido.");
                }
            }

            System.out.println("===== LISTAS DE LOCKERS DISPONIBLES POR DOCENCIA =====");
            System.out.println("1. Docencia 1");
            System.out.println("2. Docencia 2");
            System.out.println("3. Docencia 3");
            System.out.println("4. Docencia 4");
            System.out.println("======================================================");
            System.out.print("Ingrese la opcion: ");
            char subopcionDocencia2 = read.next().toUpperCase().charAt(0);
            switch (subopcionDocencia2) {
                case '1':
                    db.consultarListaDisponibilidadDocencia(1); // Manda a llamar la consulta con el argumento para la
                                                                // docencia 1
                    break;
                case '2':
                    db.consultarListaDisponibilidadDocencia(2); // Manda a llamar la consulta con el argumento para la
                                                                // docencia 2
                    break;
                case '3':
                    db.consultarListaDisponibilidadDocencia(3); // Manda a llamar la consulta con el argumento para la
                                                                // docencia 3
                    break;
                case '4':
                    db.consultarListaDisponibilidadDocencia(4); // Manda a llamar la consulta con el argumento para la
                                                                // docencia 4
                    break;
                default:
                    System.out.println(
                            "Opción inválida. Por favor, ingrese un número de opción válido.");
            }

            boolean numLockerValido = false;
            while (!numLockerValido) {
                System.out.print("Ingrese el numero de locker: ");
                try {
                    this.numLocker = Integer.parseInt(read.nextLine());
                    numLockerValido = true;
                } catch (NumberFormatException e) {
                    System.out.println("Número inválido. Ingrese un número válido.");

                }
            }

            db.consultarNumeroSolicitante(this.numero);
            boolean numSolicitanteValido = false;
            while (!numSolicitanteValido) {
                System.out.print("Ingrese el numero de solicitante: ");
                try {
                    this.numSolicitante = Integer.parseInt(read.nextLine());
                    numSolicitanteValido = true;
                } catch (NumberFormatException e) {
                    System.out.println("Número inválido. Ingrese un número válido.");
                }
            }

            try {

                String updateLocker = "UPDATE lockers SET Disponibilidad = 0 WHERE Num = " + getNumeroLocker();
                statement.executeUpdate(updateLocker);

                // Convertir la fecha ingresada a formato de MySQL
                java.util.Date fechaUtil = sdfInput.parse(this.fechaInicio);
                fechaMySQL = sdfOutput.format(fechaUtil);

                java.util.Date fechaUtil2 = sdfInput.parse(this.fechaFin);
                String fechaMySQL2 = sdfOutput.format(fechaUtil2);

                // Consulta SQL para insertar la renta en la base de datos
                String insertRentaQuery = "INSERT INTO Renta VALUES (null,'" +
                        fechaMySQL + "', '" +
                        fechaMySQL2 + "', " +
                        getNumeroSolicitante() + ", " +
                        getNumeroLocker() + ")";

                statement.executeUpdate(insertRentaQuery, Statement.RETURN_GENERATED_KEYS);

            } catch (ParseException e) {
                System.out.println(
                        "Error: en el registro de los datos.");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            ResultSet numRentas = statement.getGeneratedKeys();
            this.numeroRenta = 0;
            if (numRentas.next()) {
                this.numeroRenta = numRentas.getInt(1);
            }

            String sqlQuery = "SELECT MAX(Num_Renta) AS Ultimo_Num_Renta FROM Renta";
            resultSet = statement.executeQuery(sqlQuery);

            // Verificar si hay un registro en el resultSet
            if (resultSet.next()) {
                this.idRenta = resultSet.getInt("Ultimo_Num_Renta");
            } else {
                // No se encontró ningún registro, realiza la acción correspondiente.
                System.out.println("No se encontraron registros en la tabla Renta.");
            }

            String sqlQuery2 = "SELECT CONCAT(Nombre_Pila, ' ', Ap_Paterno, ' ', ApMaterno) AS Nombre FROM Solicitante WHERE ? IN (Alumno, Docente, Admnistrativo)";

            PreparedStatement preparedStatement = cDBF.prepareStatement(sqlQuery2);
            // Establece el valor de this.numero como parámetro
            preparedStatement.setInt(1, this.numero);
            // Ejecuta la consulta y obtén el resultado
            resultSet = preparedStatement.executeQuery();

            // Verifica si hay un registro en el resultSet
            if (resultSet.next()) {
                this.nombreS = resultSet.getString("Nombre");
                // Resto del código para mostrar el nombreS
            } else {
                System.out.println("No se encontraron datos relacionados.");
            }

            rc.limpiarPantalla();
            String formato = "%-15s| %-15s| %-15s| %-35s| %s%n ";

            System.out.println("RENTA REGISTRADA");
            System.out.println(
                    "-----------------------------------------------------------------------------------------------------------------------");
            System.out.printf(formato, "Num. Renta", "Fecha de inicio", "Fecha fin",
                    "Nombre", "Numero de locker");
            System.out.println(
                    "-----------------------------------------------------------------------------------------------------------------------");
            System.out.printf(formato, this.idRenta, this.fechaInicio, this.fechaFin, this.nombreS,
                    this.numLocker);

            char opcion;
            do {
                // Menú para el proceso de pago
                System.out.println("==== MENÚ DE PAGO ====");
                System.out.println("1. Proceder con el pago");
                System.out.println("2. Salir");
                System.out.println("======================");
                System.out.print("Ingrese la opción: ");
                opcion = read.next().toUpperCase().charAt(0);

                switch (opcion) {
                    case '1':
                        // Consultas para mostrar la lista de métodos de pago y lista de precios
                        db.consultarMetodosPago();
                        db.consultarListaPrecios();
                        System.out.println();

                        // Solicitar los datos del pago al usuario
                        boolean metodoPagoValido = false;
                        while (!metodoPagoValido) {
                            System.out.print("Ingrese el numero del metodo de pago: ");
                            this.metodoPago = read.nextInt();
                            read.nextLine();
                            if (this.metodoPago < 1 || this.metodoPago > 3) {
                                System.out.println("Error: Numero de metodo de pago no válido.");
                            } else {
                                metodoPagoValido = true;
                            }
                        }

                        String query = "SELECT Tamaño FROM Lockers WHERE Num = " + this.numLocker;
                        resultSet = statement.executeQuery(query);
                        if (resultSet.next()) {
                            int lockerSize = resultSet.getInt("Tamaño");
                            if (lockerSize == 1) {
                                this.cantidad = 600;
                            } else {
                                this.cantidad = 400;
                            }
                        } else {
                            System.out.println("Locker con Num " + numLocker + " no encontrado.");
                        }

                        
                        System.out.println("=========================================");
                        System.out.println("Desea continuar con el registro del pago?");
                        System.out.println("1. SI\n2. NO");
                        System.out.println("=========================================");
                        System.out.println("Ingrese una opcion: ");
                        char opcionPago = read.next().toUpperCase().charAt(0);
                        switch (opcionPago) {
                            case '1':

                                // Consulta SQL para insertar el pago en la base de datos
                                String insertarPagoQuery = "INSERT INTO Pago VALUES (null, " +
                                        this.cantidad + ", '" +
                                        fechaMySQL + "', " +
                                        this.metodoPago + ", " +
                                        this.numeroRenta + ")";

                                statement.executeUpdate(insertarPagoQuery);

                                rc.limpiarPantalla();
                                String formato2 = "%-12s | %-15s | %s%n ";

                                String sqlQueryP = "SELECT MAX(Num_Pago) AS Pago FROM Pago";
                                resultSet = statement.executeQuery(sqlQueryP);

                                // Verificar si hay un registro en el resultSet
                                if (resultSet.next()) {
                                    this.idPago = resultSet.getInt("Pago");
                                    System.out.println("PAGO REGISTRADO");
                                    System.out.println(
                                            "------------------------------------------------------------------");
                                    System.out.printf(formato2, "Num. de pago", "Fecha de pago", "Metodo de pago");
                                    System.out.println(
                                            "------------------------------------------------------------------");
                                    String mp;
                                    if (this.metodoPago == 2) {
                                        mp = "Tarjeta";
                                    } else {
                                        mp = "Efectivo";
                                    }
                                    System.out.printf(formato2, this.idPago, getFechaInicio(), mp);
                                } else {
                                    // No se encontró ningún registro, realiza la acción correspondiente.
                                    System.out.println("No se encontraron registros en la tabla Renta.");
                                }
                                break;
                            case '2':
                                System.out.println("No continuaste con el pago, saliendo...");
                                break;
                            default:
                                System.out.println("Ingrese un valor valido.");
                                break;
                        }

                        break;
                    case '2':
                        System.out.println("Saliendo.");
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, ingrese un número de opción válido.");
                }
            } while (opcion != '2');

            // Cerrar el statement y la conexión a la base de datos
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
