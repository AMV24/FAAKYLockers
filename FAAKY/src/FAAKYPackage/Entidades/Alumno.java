package FAAKYPackage.Entidades;

import FAAKYPackage.*;
import FAAKYPackage.DB.*;
import java.sql.*;
import java.util.Scanner;

public class Alumno {
    private int matricula, carrera, solicitante;
    private String grupo, name, ap, am, numeroT, desCarrera;

    private Connection cDBF;
    private Statement statement;
    private ResultSet resultSet;

    Scanner read = new Scanner(System.in);
    Otros rc = new Otros();
    ConsultasDB db = new ConsultasDB();

    public Alumno() {
    }

    public Alumno(int idA, String g, int c, String n, String ap, String am, String nt) {
        this.matricula = idA;
        this.grupo = g;
        this.carrera = c;
        this.name = n;
        this.ap = ap;
        this.am = am;
        this.numeroT = nt;
    }

    public int getMatricula() {
        return matricula;
    }

    public String getGrupo() {
        return grupo;
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

    public int getSolicitante() {
        return solicitante;
    }

    public String getNumeroTelefono() {
        return numeroT;
    }

    public void setMatricula(int id) {
        this.matricula = id;
    }

    public void setGrupo(String g) {
        this.grupo = g;
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

    public void setSolicitante(int s) {
        this.solicitante = s;
    }

    public void setNumeroTelefono(String nt) {
        this.numeroT = nt;
    }

    public void registrarAlumno(DBC dbConexion) {
        try {

            // Establecer la conexión a la base de datos
            cDBF = dbConexion.getConexion();
            statement = cDBF.createStatement();

            System.out.println("==== Registro de Alumno ====");
            System.out.print("Ingrese el nombre del alumno: ");
            this.name = read.nextLine(); // Lee el nombre ingresado por el usuario
            read.nextLine();

            System.out.print("Ingrese el apellido paterno del alumno: ");
            this.ap = read.nextLine(); // Lee el apellido paterno ingresado por el usuario
            read.nextLine();

            System.out.print("Ingrese el apellido materno del alumno: ");
            this.am = read.nextLine(); // Lee el apellido materno ingresado por el usuario
            read.nextLine();

            System.out.print("Ingrese el grado y grupo del alumno: ");
            this.grupo = read.nextLine(); // Lee el grupo ingresado por el usuario
            read.nextLine();

            boolean numMatriculaValido = false;
            while (!numMatriculaValido) {
                System.out.print("Ingrese el numero de matricula: ");
                try {
                    this.matricula = Integer.parseInt(read.nextLine());
                    numMatriculaValido = true;
                } catch (NumberFormatException e) {
                    System.out.println("MATRICULA INVALIDA. Ingrese una matricula válida.");

                }
            }

            System.out.print("Ingrese el número de teléfono del alumno: ");
            this.numeroT = read.nextLine(); // Lee el número de teléfono ingresado por el usuario
            read.nextLine();

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

            // Consulta SQL para insertar el alumno en la tabla "Alumno"
            String insertAlumnoQuery = "INSERT INTO Alumno VALUES (" +
                    getMatricula() + ", '" +
                    getGrupo() + "', " +
                    getCarrera() + ")";
            statement.executeUpdate(insertAlumnoQuery);

            // Consulta SQL para insertar el solicitante en la tabla "Solicitante"
            String insertSolicitanteQuery = "INSERT INTO Solicitante VALUES (null, '" +
                    getNombre() + "', '" +
                    getApellidoPa() + "', '" +
                    getApellidoMa() + "', " +
                    getMatricula() + ", null, null)";
            statement.executeUpdate(insertSolicitanteQuery, Statement.RETURN_GENERATED_KEYS);

            // Obtener el valor de Num_Solicitante generado
            ResultSet numSolicitanteG = statement.getGeneratedKeys();
            this.solicitante = 0;
            if (numSolicitanteG.next()) {
                this.solicitante = numSolicitanteG.getInt(1);
            }

            // Consulta SQL para insertar el número de teléfono en la tabla "Telefono"
            String insertTelefonoQuery = "INSERT INTO Telefono VALUES(null, '" +
                    getNumeroTelefono() + "', " +
                    getSolicitante() + ")";
            statement.executeUpdate(insertTelefonoQuery);

            rc.limpiarPantalla();
            String formato = "%-12s| %-35s| %-55s| %-35s%n ";

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
                System.out.println("ALUMNO REGISTRADO EXITOSAMENTE");
                System.out.println("----------------------------------------------------------------------------------------------------------------------");
                System.out.printf(formato, "Matricula ", "Nombre", "Carrera", "Numero de telefono");
                System.out.println("----------------------------------------------------------------------------------------------------------------------");
                System.out.printf(formato, "0" + getMatricula(),
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
                db.consultarSolicitanteAlumno(id);
                System.out.println("==== MENÚ DE ACTUALIZACIÓN DE ALUMNO ====");
                System.out.println("1. Actualizar nombre");
                System.out.println("2. Actualizar apellido paterno");
                System.out.println("3. Actualizar apellido materno");
                System.out.println("4. Salir");
                System.out.println("=========================================");
                System.out.print("Ingrese la opción: ");
                opcion = read.next().toUpperCase().charAt(0);

                switch (opcion) {
                    case '1':
                        System.out.print("Ingrese el nuevo nombre: ");
                        read.nextLine(); // Consumir salto de línea pendiente
                        String nuevoNombre = read.nextLine();
                        String actualizarNombre = "UPDATE Solicitante SET Nombre_Pila = '" + nuevoNombre
                                + "' WHERE Alumno = " + id;
                        statement.executeUpdate(actualizarNombre);
                        System.out.println("Se ha guardado el nuevo nombre: " + nuevoNombre);
                        break;
                    case '2':
                        System.out.print("Ingrese el nuevo apellido paterno: ");
                        read.nextLine(); // Consumir salto de línea pendiente
                        String nuevoApellidoPaterno = read.nextLine();
                        String actualizarApellidoPaterno = "UPDATE Solicitante SET Ap_Paterno = '"
                                + nuevoApellidoPaterno + "' WHERE Alumno = " + id;
                        statement.executeUpdate(actualizarApellidoPaterno);
                        System.out.println("Se ha guardado el nuevo apellido paterno: " + nuevoApellidoPaterno);
                        break;
                    case '3':
                        System.out.print("Ingrese el nuevo apellido materno: ");
                        read.nextLine(); // Consumir salto de línea pendiente
                        String nuevoApellidoMaterno = read.nextLine();
                        String actualizarApellidoMaterno = "UPDATE Solicitante SET ApMaterno = '" + nuevoApellidoMaterno
                                + "' WHERE Alumno = " + id;
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
            } while (opcion != '5');

            // Cerrar la conexión a la base de datos.
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
