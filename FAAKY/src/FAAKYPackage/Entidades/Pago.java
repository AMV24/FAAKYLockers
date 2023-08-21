package FAAKYPackage.Entidades;

import FAAKYPackage.*;
import FAAKYPackage.DB.*;
import java.sql.*;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Pago {
    private int numRenta, cantidad, metodoPago, idPago, numLocker;
    private String fechaInicio;

    private Connection cDBF;
    private Statement statement;
    private ResultSet resultSet;

    Scanner read = new Scanner(System.in);
    Otros rc = new Otros();
    ConsultasDB db = new ConsultasDB();

    public Pago() {
    }

    public Pago(int nr, int nl, String f) {
        this.numRenta = nr;
        this.metodoPago = nl;
        this.fechaInicio = f;
    }

    public int getNumeroRenta() {
        return numRenta;
    }

    public int getMetodoPago() {
        return metodoPago;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setNumeroRenta(int n) {
        this.numRenta = n;
    }

    public void setMetodoPago(int n) {
        this.metodoPago = n;
    }

    public void setFechaInicio(String f) {
        this.fechaInicio = f;
    }

    public void registrarPago(DBC dbConexion) {
        try {
            // Establecer la conexión a la base de datos
            cDBF = dbConexion.getConexion();
            statement = cDBF.createStatement();

            System.out.println("==== Registro de Pago ====");

            System.out.print("Ingrese el numero de la renta: ");
            this.numRenta = read.nextInt(); // Lee el ID de la renta ingresado por el usuario
            read.nextLine();

            boolean fechaInicioValida = false;
            while (!fechaInicioValida) {
                System.out.print("Ingrese la fecha de pago (DD-MM-YYYY): ");
                this.fechaInicio = read.nextLine();
                try {
                    SimpleDateFormat sdfInput = new SimpleDateFormat("dd-MM-yyyy");
                    sdfInput.setLenient(false); // Desactiva el modo permisivo para la validación estricta
                    sdfInput.parse(this.fechaInicio);
                    fechaInicioValida = true;
                } catch (ParseException e) {
                    System.out.println("Fecha de pago inválida. Ingrese la fecha en el formato DD-MM-YYYY.");
                }
            }

            SimpleDateFormat sdfInput = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyy-MM-dd");

            db.consultarMetodosPago();
            db.consultarListaPrecios();
            System.out.println();

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

            String queryRentaL = "SELECT Lockers FROM Renta WHERE Num_Renta = " + this.numRenta;
            resultSet = statement.executeQuery(queryRentaL);
            if (resultSet.next()) {
                this.numLocker = resultSet.getInt("Lockers");
            } else {
                System.out.println("No se aha encontrado un locker en esta renta.");
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

            char opcionPago;

            System.out.println("=========================================");
            System.out.println("Desea continuar con el registro del pago?");
            System.out.println("1. SI\n2. NO");
            System.out.println("=========================================");
            System.out.println("Ingrese una opcion: ");
            opcionPago = read.next().toUpperCase().charAt(0);
            switch (opcionPago) {
                case '1':

                    try {
                        java.util.Date fechaUtil = sdfInput.parse(this.fechaInicio);
                        String fechaMySQL = sdfOutput.format(fechaUtil);

                        // Consulta SQL para insertar el pago en la base de datos
                        String insertRentaQuery = "INSERT INTO Pago VALUES (null," +
                                this.cantidad + ", '" +
                                fechaMySQL + "', " +
                                getMetodoPago() + ", " +
                                getNumeroRenta() + ")";
                        statement.executeUpdate(insertRentaQuery); // Ejecuta la consulta para insertar el pago en la
                                                                   // base de datos

                    } catch (ParseException e) {
                        System.out.println("Error: Fecha ingresada en formato incorrecto. Use el formato DD-MM-YYYY.");
                    }

                    rc.limpiarPantalla();
                    String formato = "%-12s| %-15s | %-15s| %s%n ";

                    String sqlQueryP = "SELECT MAX(Num_Pago) AS Pago FROM Pago";
                    resultSet = statement.executeQuery(sqlQueryP);

                    // Verificar si hay un registro en el resultSet
                    if (resultSet.next()) {
                        this.idPago = resultSet.getInt("Pago");
                        System.out.println("PAGO REGISTRADO");
                        System.out.println("------------------------------------------------------------------");
                        System.out.printf(formato, "Num. de pago", "Fecha de pago", "Metodo de pago");
                        System.out.println("------------------------------------------------------------------");
                        String mp;
                        if (this.metodoPago == 2) {
                            mp = "Tarjeta";
                        } else {
                            mp = "Efectivo";
                        }
                        System.out.printf(formato, this.idPago, getFechaInicio(), mp);
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

            // Cerrar el statement y la conexión a la base de datos
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
