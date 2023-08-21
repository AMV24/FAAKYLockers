package FAAKYPackage.Entidades;

import FAAKYPackage.*;
import FAAKYPackage.DB.*;
import java.sql.*;
import java.util.Scanner;

public class Administrativo {
    private int num_empleado, numSolicitante,  departamento;
    private String descripcion,name, ap, am, numeroT, puesto;

    private Connection cDBF;
    private Statement statement;

    Scanner read = new Scanner(System.in);
    Otros rc = new Otros();
    ConsultasDB db = new ConsultasDB();

    public Administrativo() {
    }

    public Administrativo(int idA, String g, String c, String n, String ap, String am, int d, String nt, int ns) {
        this.num_empleado = idA;
        this.descripcion = g;
        this.puesto = c;
        this.name = n;
        this.ap = ap;
        this.am = am;
        this.departamento = d;
        this.numeroT = nt;
        this.numSolicitante = ns;
    }

    public int getNumEmpleado() {
        return num_empleado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPuesto() {
        return puesto;
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

    public int getDepartamento() {
        return departamento;
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

    public void setPuesto(String c) {
        this.puesto = c;
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

    public void setDepartamento(int d) {
        this.departamento = d;
    }

    public void setNumeroTelefono(String nt) {
        this.numeroT = nt;
    }

    public void setNumeroSolicitante(int ns) {
        this.numSolicitante = ns;
    }

    public void registrarAdm(DBC dbConexion) {
        try {
            // Establecer la conexión a la base de datos
            cDBF = dbConexion.getConexion();
            statement = cDBF.createStatement();

            System.out.println("==== REGISTRO DE ADMINISTRATIVO ====");
            System.out.print("Ingrese el nombre del administrativo: ");
            this.name = read.nextLine(); // Lee el nombre ingresado por el usuario
            read.nextLine();

            System.out.print("Ingrese el apellido paterno del administrativo: ");
            this.ap = read.nextLine(); // Lee el apellido paterno ingresado por el usuario
            read.nextLine();

            System.out.print("Ingrese el apellido materno del administrativo: ");
            this.am = read.nextLine(); // Lee el apellido materno ingresado por el usuario
            read.nextLine();

            System.out.print("Ingrese la descripción del puesto: ");
            this.descripcion = read.nextLine(); // Lee la descripción ingresada por el usuario
            read.nextLine();

            System.out.print("Ingrese el número de teléfono del administrativo: ");
            this.numeroT = read.nextLine(); // Lee el número de teléfono ingresado por el usuario
            read.nextLine();

            db.consultarListaDepartamento();
            System.out.print("Ingrese el numero del departamento: ");
            this.departamento = read.nextInt(); // Lee el ID del departamento ingresado por el usuario
            read.nextLine();

            db.consultarListaPuestos(this.departamento);
            System.out.print("Ingrese el código de puesto: ");
            this.puesto = read.nextLine(); // Lee el código de puesto ingresado por el usuario
            read.nextLine();


            
            boolean numEmpAdmiValido = false;
            while (!numEmpAdmiValido) {
                System.out.print("Ingrese el numero de empleado: ");
                try {
                    this.num_empleado = Integer.parseInt(read.nextLine());
                    numEmpAdmiValido = true;
                } catch (NumberFormatException e) {
                    System.out.println("NUMERO DE EMPLEADO INVALIDO. Ingrese un numero de empleado VALIDO.");

                }
            }

            // Consulta SQL para insertar el administrativo en la tabla "Administrativo"
            String insertAdministrativoQuery = "INSERT INTO Admnistrativo VALUES (" +
                    getNumEmpleado() + ", '" +
                    getDescripcion() + "', '" +
                    getPuesto() + "', " +
                    getDepartamento() + ")";
            statement.executeUpdate(insertAdministrativoQuery);

            // Consulta SQL para insertar el solicitante en la tabla "Solicitante"
            String insertSolicitanteQuery = "INSERT INTO Solicitante VALUES (null, '" +
                    getNombre() + "', '" +
                    getApellidoPa() + "', '" +
                    getApellidoMa() + "', null, null, " +
                    getNumEmpleado() + ")";
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
            String formato = "%-12s| %-40s| %-15s%n ";

            System.out.println("ADMINISTRATIVO REGISTRADO EXITOSAMENTE");
            System.out.println("----------------------------------------------------------------------------------------");
            System.out.printf(formato, "Num. Empleado", "Nombre", "Numero de telefono");
            System.out.println("----------------------------------------------------------------------------------------");
            System.out.printf(formato, "0" + getNumEmpleado(),
                    getNombre() + " " + getApellidoPa() + " " + getApellidoMa(),
                    getNumeroTelefono());

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
                db.consultarSolicitanteAdm(id);
                System.out.println("==== MENÚ DE ACTUALIZACIÓN DE ADMINISTRATIVO ====");
                System.out.println("1. Actualizar nombre");
                System.out.println("2. Actualizar apellido paterno");
                System.out.println("3. Actualizar apellido materno");
                System.out.println("4. Salir");
                System.out.println("=================================================");
                System.out.print("Ingrese la opcion: ");
                opcion = read.next().toUpperCase().charAt(0);

                switch (opcion) {
                    case '1':
                        System.out.print("Ingrese el nuevo nombre: ");
                        read.nextLine(); // Consumir salto de línea pendiente
                        String nuevoNombre = read.nextLine();
                        String actualizarNombre = "UPDATE Solicitante SET Nombre_Pila = '" + nuevoNombre
                                + "' WHERE Admnistrativo = " + id;
                        statement.executeUpdate(actualizarNombre);
                        System.out.println("Se ha guardado el nuevo nombre: " + nuevoNombre);
                        break;
                    case '2':
                        System.out.print("Ingrese el nuevo apellido paterno: ");
                        read.nextLine(); // Consumir salto de línea pendiente
                        String nuevoApellidoPaterno = read.nextLine();
                        String actualizarApellidoPaterno = "UPDATE Solicitante SET Ap_Paterno = '"
                                + nuevoApellidoPaterno + "' WHERE Admnistrativo = " + id;
                        statement.executeUpdate(actualizarApellidoPaterno);
                        System.out.println("Se ha guardado el nuevo apellido paterno: " + nuevoApellidoPaterno);
                        break;
                    case '3':
                        System.out.print("Ingrese el nuevo apellido materno: ");
                        read.nextLine(); // Consumir salto de línea pendiente
                        String nuevoApellidoMaterno = read.nextLine();
                        String actualizarApellidoMaterno = "UPDATE Solicitante SET ApMaterno = '" + nuevoApellidoMaterno
                                + "' WHERE Admnistrativo = " + id;
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
