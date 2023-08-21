package FAAKYPackage.Entidades;

import FAAKYPackage.*;
import FAAKYPackage.DB.ConsultasDB;
import FAAKYPackage.DB.DBC;
import java.sql.*;
import java.util.Scanner;

public class Docente {
    private int num_empleado, carrera, numSolicitante;
    private String descripcion, name, ap, am, numeroT, desCarrera;

    private Connection cDBF;
    private Statement statement;
    private ResultSet resultSet;

    Scanner read = new Scanner(System.in);
    Otros rc = new Otros();
    ConsultasDB db = new ConsultasDB();

    public Docente() {
    }

    public Docente(int idA, String g, int c, String n, String ap, String am, String nt, int ns) {
        this.num_empleado = idA;
        this.descripcion = g;
        this.carrera = c;
        this.name = n;
        this.ap = ap;
        this.am = am;
        this.numeroT = nt;
        this.numSolicitante = ns;
    }

    public int getNumEmpleado() {
        return num_empleado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCarrera() {
        return carrera;
    }

    public String getNombre() {
        return name;
    }

    public String getApellidoPa() {
        return ap;
    }

    public String getApellidoMa() {
        return am;
    }

    public String getNumeroTelefono() {
        return numeroT;
    }

    public int getNumeroSolicitante() {
        return numSolicitante;
    }

    public void setNumEmpleado(int id) {
        this.num_empleado = id;
    }

    public void setDescripcion(String g) {
        this.descripcion = g;
    }

    public void setCarrera(int c) {
        this.carrera = c;
    }

    public void setNombre(String n) {
        this.name = n;
    }

    public void setApellidoPa(String a) {
        this.ap = a;
    }

    public void setApellidoMa(String a) {
        this.am = a;
    }

    public void setNumeroTelefono(String n) {
        this.numeroT = n;
    }

    public void setNUmeroSolicitante(int n) {
        this.numSolicitante = n;
    }

    public void registrarDocente(DBC dbConexion) {
        try {
            // Establecer la conexión a la base de datos
            cDBF = dbConexion.getConexion();
            statement = cDBF.createStatement();

            System.out.println("==== Registro de Docente ====");
            System.out.print("Ingrese el nombre del docente: ");
            this.name = read.nextLine(); // Lee el nombre ingresado por el usuario
            read.nextLine();

            System.out.print("Ingrese el apellido paterno del docente: ");
            this.ap = read.nextLine(); // Lee el apellido paterno ingresado por el usuario
            read.nextLine();

            System.out.print("Ingrese el apellido materno del docente: ");
            this.am = read.nextLine(); // Lee el apellido materno ingresado por el usuario
            read.nextLine();

            System.out.print("Ingrese el número de teléfono del docente: ");
            this.numeroT = read.nextLine(); // Lee el número de teléfono ingresado por el usuario
            read.nextLine();

            boolean numDocenteValido = false;
            while (!numDocenteValido) {
                System.out.print("Ingrese el numero de empleado: ");
                try {
                    this.num_empleado = Integer.parseInt(read.nextLine());
                    numDocenteValido = true;
                } catch (NumberFormatException e) {
                    System.out.println("NUMERO DE EMPLEADO INVALIDO. Ingrese un numero de empleado VALIDO");

                }
            }

            // Proceder con el registro de un docente
            // Consultar la lista de carreras
            db.consultarListaCarreras();

            boolean numCarreraValido = false;
            while (!numCarreraValido) {
                db.consultarListaCarreras();
                System.out.print("Ingrese el numero de la carrera: ");
                try {
                    this.carrera = Integer.parseInt(read.nextLine());
                    numCarreraValido = true;
                } catch (NumberFormatException e) {
                    System.out.println("Número inválido. Ingrese un número válido.");

                }
            }

            // Consulta SQL para insertar el docente en la tabla "Docente"
            String insertDocenteQuery = "INSERT INTO Docente VALUES (" +
                    getNumEmpleado() + ", '" +
                    getDescripcion() + "', " +
                    getCarrera() + ")";
            statement.executeUpdate(insertDocenteQuery);

            // Consulta SQL para insertar el solicitante en la tabla "Solicitante"
            String insertSolicitanteQuery = "INSERT INTO Solicitante VALUES (null, '" +
                    getNombre() + "', '" +
                    getApellidoPa() + "', '" +
                    getApellidoMa() + "',  null, " +
                    getNumEmpleado() + ", null)";
            statement.executeUpdate(insertSolicitanteQuery, Statement.RETURN_GENERATED_KEYS);

            // Obtener el valor de Num_Solicitante generado
            ResultSet numSolicitanteG = statement.getGeneratedKeys();
            this.numSolicitante = 0;
            if (numSolicitanteG.next()) {
                this.numSolicitante = numSolicitanteG.getInt(1);
            }

            // Consulta SQL para insertar el número de teléfono en la tabla "Telefono"
            String insertTelefonoQuery = "INSERT INTO Telefono VALUES(null, '" +
                    getNumeroTelefono() + "', " +
                    getNumeroSolicitante() + ")";
            statement.executeUpdate(insertTelefonoQuery);

            rc.limpiarPantalla();
            String formato = "%-12s| %-35s| %-55s| %-15s%n ";

            String sqlQuery2 = "SELECT Descripcion FROM Carrera WHERE Num = ?";
            PreparedStatement preparedStatement = cDBF.prepareStatement(sqlQuery2);
            // Establece el valor de this.numero como parámetro
            preparedStatement.setInt(1, this.carrera);
            // Ejecuta la consulta y obtén el resultado
            resultSet = preparedStatement.executeQuery();

            // Ejecuta la consulta y obtén el resultado
            resultSet = preparedStatement.executeQuery();

            // Verifica si hay un registro en el resultSet
            if (resultSet.next()) {
                this.desCarrera = resultSet.getString("Descripcion");
                System.out.println("DOCENTE REGISTRADO EXITOSAMENTE");
                System.out.println("----------------------------------------------------------------------------------------------------------------------");
                System.out.printf(formato, "Num. Empleado", "Nombre", "Carrera", "Numero de telefono");
                System.out.println("----------------------------------------------------------------------------------------------------------------------");
                System.out.printf(formato, "0" + getNumEmpleado(),
                        getNombre() + " " + getApellidoPa() + " " + getApellidoMa(), this.desCarrera,
                        getNumeroTelefono());
            } else {
                System.out.println("No se encontraron datos relacionados.");
            }

            // Cerrar el statement y la conexión a la base de datos
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mostrarMenuActualizar(DBC dbConexion, int id) {
        char opcion;

        try {
            // Establecer la conexión a la base de datos
            cDBF = dbConexion.getConexion();
            statement = cDBF.createStatement();

            do {
                rc.limpiarPantalla();
                db.consultarSolicitanteDocente(id);
                System.out.println("==== MENÚ DE ACTUALIZACIÓN DE DOCENTE ====");
                System.out.println("1. Actualizar nombre");
                System.out.println("2. Actualizar apellido paterno");
                System.out.println("3. Actualizar apellido materno");
                System.out.println("4. Salir");
                System.out.println("=========================================");
                System.out.print("Seleccione la opción que desea actualizar: ");
                opcion = read.next().toUpperCase().charAt(0);

                switch (opcion) {
                    case '1':
                        System.out.print("Ingrese el nuevo nombre: ");
                        read.nextLine(); // Consumir salto de línea pendiente
                        String nuevoNombre = read.nextLine();
                        String actualizarNombre = "UPDATE Solicitante SET Nombre_Pila = '" + nuevoNombre
                                + "' WHERE Docente = " + id;
                        statement.executeUpdate(actualizarNombre);
                        System.out.println("Se ha guardado el nuevo nombre: " + nuevoNombre);
                        break;
                    case '2':
                        System.out.print("Ingrese el nuevo apellido paterno: ");
                        read.nextLine(); // Consumir salto de línea pendiente
                        String nuevoApellidoPaterno = read.nextLine();
                        String actualizarApellidoPaterno = "UPDATE Solicitante SET Ap_Paterno = '"
                                + nuevoApellidoPaterno + "' WHERE Docente = " + id;
                        statement.executeUpdate(actualizarApellidoPaterno);
                        System.out.println("Se ha guardado el nuevo apellido paterno: " + nuevoApellidoPaterno);
                        break;
                    case '3':
                        System.out.print("Ingrese el nuevo apellido materno: ");
                        read.nextLine(); // Consumir salto de línea pendiente
                        String nuevoApellidoMaterno = read.nextLine();
                        String actualizarApellidoMaterno = "UPDATE Solicitante SET ApMaterno = '" + nuevoApellidoMaterno
                                + "' WHERE Docente = " + id;
                        statement.executeUpdate(actualizarApellidoMaterno);
                        System.out.println("Se ha guardado el nuevoapellido materno: " + nuevoApellidoMaterno);
                        break;
                    case '4':
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                        break;
                }
            } while (opcion != '4');

            // Cerrar la conexión a la base de datos.
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
