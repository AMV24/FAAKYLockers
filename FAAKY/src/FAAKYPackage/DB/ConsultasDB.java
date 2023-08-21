package FAAKYPackage.DB;

import java.sql.*;

import FAAKYPackage.Otros;

public class ConsultasDB {
    DBC dbF = new DBC();
    Otros rc = new Otros();

    private Connection cDBF;
    private Statement statement;
    private ResultSet resultSet;
    private String sqlConsult;
    private String formato;

    // Consultas pedidas por la MTI. Cleotilde.
    // 1
    public void consultarDatosRentaAlumno(int a) {
        // Consulta para obtener la información de la renta del locker para un alumno
        // específico.
        // La consulta busca información de renta, solicitante, alumno, teléfono,
        // carrera, lockers, tamaño, ubicación, pago y método de pago.
        sqlConsult = "SELECT " +
                "r.Num_Renta AS 'Número de la renta', " +
                "concat(ifnull(concat(s.Ap_Paterno, ' ' ), ' ' ), " +
                "ifnull(concat(s.ApMaterno, ' '), ' '), " +
                "ifnull(concat(s.Nombre_Pila, ' '), ' ')" +
                ") AS 'Nombre completo del alumno', " +
                "t.Num_Tel AS 'Número de teléfono del alumno', " +
                "c.Descripcion AS 'Nombre de la carrera', " +
                "a.Grupo AS 'Grupo del alumno', " +
                "DATE_FORMAT(r.Fecha_Inicio, '%d-%m-%y') AS 'Fecha de inicio de la renta', " +
                "l.Num AS 'Número del locker rentado', " +
                "tam.Descripcion AS 'Tamaño del locker', " +
                "CONCAT('Docencia: ', u.Doncencia, ' - Piso: ', u.Piso) AS 'Ubicación del locker', " +
                "tam.Precio AS 'Precio del locker', " +
                "pa.Num_Pago AS 'Número de pago del locker', " +
                "DATE_FORMAT(pa.Fecha, '%d-%m-%y') AS 'Fecha del pago', " +
                "pa.Cantidad AS 'Monto del pago', " +
                "mp.Descripcion AS 'Método de pago' " +
                "FROM Renta r " +
                "INNER JOIN Solicitante s ON r.Num_Solicitante = s.Num_Solicitante " +
                "INNER JOIN Alumno a ON s.Alumno = a.Matricula " +
                "INNER JOIN telefono t ON s.Num_Solicitante = t.Num_Solicitante " +
                "INNER JOIN Carrera c ON a.Carrera = c.Num " +
                "INNER JOIN Lockers l ON r.Lockers = l.Num " +
                "INNER JOIN Tamaño tam ON l.Tamaño = tam.Num " +
                "INNER JOIN Ubicacion u ON l.Ubicacion = u.Codigo " +
                "INNER JOIN Pago pa ON r.Num_Renta = pa.Num_Renta " +
                "INNER JOIN Metodo_De_Pago mp ON pa.Metodo_De_Pago = mp.Num_Metodo " +
                "WHERE s.Alumno = " + a;

        try {
            cDBF = dbF.getConexion();
            statement = cDBF.createStatement();
            resultSet = statement.executeQuery(sqlConsult);

            // Mostrar la información de la renta del locker para el alumno específico.
            if (!resultSet.isBeforeFirst()) {
                System.out
                        .println("No hay rentas registradas para el alumno con el número de matrícula proporcionado.");
            } else {
                rc.limpiarPantalla();
                while (resultSet.next()) {
                    // Mostrar cada atributo de la consulta para cada registro.
                    
                    System.out.println("DATOS DE LA RENTA");
                    // Imprimir separador
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("Número de la renta: " + resultSet.getInt("Número de la renta"));
                    System.out.println(
                            "Nombre completo del alumno: " + resultSet.getString("Nombre completo del alumno"));
                    System.out.println(
                            "Número de teléfono del alumno: " + resultSet.getString("Número de teléfono del alumno"));
                    System.out.println("Nombre de la carrera: " + resultSet.getString("Nombre de la carrera"));
                    System.out.println("Grupo del alumno: " + resultSet.getString("Grupo del alumno"));
                    System.out.println(
                            "Fecha de inicio de la renta: " + resultSet.getString("Fecha de inicio de la renta"));
                    System.out.println("Número del locker rentado: " + resultSet.getInt("Número del locker rentado"));
                    System.out.println("Tamaño del locker: " + resultSet.getString("Tamaño del locker"));
                    System.out.println("Ubicación del locker: " + resultSet.getString("Ubicación del locker"));
                    System.out.println("Precio del locker: " + resultSet.getFloat("Precio del locker"));
                    System.out.println("Número de pago del locker: " + resultSet.getInt("Número de pago del locker"));
                    System.out.println("Fecha del pago: " + resultSet.getString("Fecha del pago"));
                    System.out.println("Monto del pago: " + resultSet.getFloat("Monto del pago"));
                    System.out.println("Método de pago: " + resultSet.getString("Método de pago"));
                    System.out.println("------------------------------------------------------------------");
                }
            }

            // Cerrar la conexión a la base de datos.
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // 2
    public void consultarDatosRentaDocente(int e) {
        // Consulta para obtener la información de la renta del locker para un docente
        // específico.
        sqlConsult = "SELECT " +
                "r.Num_Renta AS 'Número de la renta', " +
                "concat(ifnull(concat(s.Ap_Paterno, ' ' ), ' ' ), " +
                "ifnull(concat(s.ApMaterno, ' '), ' '), " +
                "ifnull(concat(s.Nombre_Pila, ' '), ' ')" +
                ") AS 'Nombre completo del docente', " +
                "t.Num_Tel AS 'Número de teléfono del docente', " +
                "c.Descripcion AS 'Nombre de la carrera', " +
                "DATE_FORMAT(r.Fecha_Inicio, '%d-%m-%y') AS 'Fecha de inicio de la renta', " +
                "l.Num AS 'Número del locker rentado', " +
                "tam.Descripcion AS 'Tamaño del locker', " +
                "CONCAT('Docencia: ', u.Doncencia, ' - Piso: ', u.Piso) AS 'Ubicación del locker', " +
                "tam.Precio AS 'Precio del locker', " +
                "pa.Num_Pago AS 'Número de pago del locker', " +
                "DATE_FORMAT(pa.Fecha, '%d-%m-%y') AS 'Fecha del pago', " +
                "pa.Cantidad AS 'Monto del pago', " +
                "mp.Descripcion AS 'Método de pago' " +
                "FROM Renta r " +
                "INNER JOIN Solicitante s ON r.Num_Solicitante = s.Num_Solicitante " +
                "INNER JOIN Docente d ON s.Docente = d.Num_Emp " +
                "INNER JOIN telefono t ON s.Num_Solicitante = t.Num_Solicitante " +
                "INNER JOIN Carrera c ON d.Carrera = c.Num " +
                "INNER JOIN Lockers l ON r.Lockers = l.Num " +
                "INNER JOIN Tamaño tam ON l.Tamaño = tam.Num " +
                "INNER JOIN Ubicacion u ON l.Ubicacion = u.Codigo " +
                "INNER JOIN Pago pa ON r.Num_Renta = pa.Num_Renta " +
                "INNER JOIN Metodo_De_Pago mp ON pa.Metodo_De_Pago = mp.Num_Metodo " +
                "WHERE s.Docente = " + e;

        try {
            cDBF = dbF.getConexion();
            statement = cDBF.createStatement();
            resultSet = statement.executeQuery(sqlConsult);

            // Mostrar la información de la renta del locker para el docente específico.
            if (!resultSet.isBeforeFirst()) {
                System.out
                        .println("No hay rentas registradas para el docente con el número de empleado proporcionado.");
            } else {
                rc.limpiarPantalla();
                while (resultSet.next()) {
                    
                    System.out.println("DATOS DE LA RENTA");
                    // Imprimir separador
                    System.out.println("------------------------------------------------------------------");
                    // Mostrar cada atributo de la consulta para cada registro.
                    System.out.println("Número de la renta: " + resultSet.getInt("Número de la renta"));
                    System.out.println(
                            "Nombre completo del docente: " + resultSet.getString("Nombre completo del docente"));
                    System.out.println(
                            "Número de teléfono del docente: " + resultSet.getString("Número de teléfono del docente"));
                    System.out.println("Nombre de la carrera: " + resultSet.getString("Nombre de la carrera"));
                    System.out.println(
                            "Fecha de inicio de la renta: " + resultSet.getString("Fecha de inicio de la renta"));
                    System.out.println("Número del locker rentado: " + resultSet.getInt("Número del locker rentado"));
                    System.out.println("Tamaño del locker: " + resultSet.getString("Tamaño del locker"));
                    System.out.println("Ubicación del locker: " + resultSet.getString("Ubicación del locker"));
                    System.out.println("Precio del locker: " + resultSet.getFloat("Precio del locker"));
                    System.out.println("Número de pago del locker: " + resultSet.getInt("Número de pago del locker"));
                    System.out.println("Fecha del pago: " + resultSet.getString("Fecha del pago"));
                    System.out.println("Monto del pago: " + resultSet.getFloat("Monto del pago"));
                    System.out.println("Método de pago: " + resultSet.getString("Método de pago"));
                    System.out.println("------------------------------------------------------------------");
                }
            }

            // Cerrar la conexión a la base de datos.
            resultSet.close();
            statement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // 3
    public void consultarDatosRentaAdministrativo(int a) {
        // Consulta para obtener la información de la renta del locker para un
        // administrativo específico.
        sqlConsult = "SELECT " +
                "r.Num_Renta AS 'Número de la renta', " +
                "concat(ifnull(concat(s.Ap_Paterno, ' ' ), ' ' ), " +
                "ifnull(concat(s.ApMaterno, ' '), ' '), " +
                "ifnull(concat(s.Nombre_Pila, ' '), ' ')" +
                ") AS 'Nombre completo del administrativo', " +
                "t.Num_Tel AS 'Número de teléfono del administrativo', " +
                "dep.Nombre AS 'Nombre del departamento', " +
                "p.Nombre AS 'Nombre del puesto', " +
                "DATE_FORMAT(r.Fecha_Inicio, '%d-%m-%y') AS 'Fecha de inicio de la renta', " +
                "l.Num AS 'Número del locker rentado', " +
                "tam.Descripcion AS 'Tamaño del locker', " +
                "CONCAT('Docencia: ', u.Doncencia, ' - Piso: ', u.Piso) AS 'Ubicación del locker', " +
                "tam.Precio AS 'Precio del locker', " +
                "pa.Num_Pago AS 'Número de pago del locker', " +
                "DATE_FORMAT(pa.Fecha, '%d-%m-%y') AS 'Fecha del pago', " +
                "pa.Cantidad AS 'Monto del pago', " +
                "mp.Descripcion AS 'Método de pago' " +
                "FROM Renta r " +
                "INNER JOIN Solicitante s ON r.Num_Solicitante = s.Num_Solicitante " +
                "INNER JOIN Admnistrativo adm ON s.Admnistrativo = adm.Num_Emp " +
                "INNER JOIN telefono t ON s.Num_Solicitante = t.Num_Solicitante " +
                "INNER JOIN Departamento dep ON adm.Departamento = dep.Num " +
                "INNER JOIN Puesto p ON adm.Puesto = p.codigo " +
                "INNER JOIN Lockers l ON r.Lockers = l.Num " +
                "INNER JOIN Tamaño tam ON l.Tamaño = tam.Num " +
                "INNER JOIN Ubicacion u ON l.Ubicacion = u.Codigo " +
                "INNER JOIN Pago pa ON r.Num_Renta = pa.Num_Renta " +
                "INNER JOIN Metodo_De_Pago mp ON pa.Metodo_De_Pago = mp.Num_Metodo " +
                "WHERE s.Admnistrativo = " + a;

        try {
            cDBF = dbF.getConexion();
            statement = cDBF.createStatement();
            resultSet = statement.executeQuery(sqlConsult);

            // Mostrar la información de la renta del locker para el administrativo
            // específico.
            if (!resultSet.isBeforeFirst()) {
                System.out.println(
                        "No hay rentas registradas para el administrativo con el número de empleado proporcionado.");
            } else {
                rc.limpiarPantalla();
                while (resultSet.next()) {
                    
                    System.out.println("DATOS DE LA RENTA");
                    // Imprimir separador
                    System.out.println("------------------------------------------------------------------");
                    // Mostrar cada atributo de la consulta para cada registro.
                    System.out.println("Número de la renta: " + resultSet.getInt("Número de la renta"));
                    System.out.println("Nombre completo del administrativo: "
                            + resultSet.getString("Nombre completo del administrativo"));
                    System.out.println("Número de teléfono del administrativo: "
                            + resultSet.getString("Número de teléfono del administrativo"));
                    System.out.println("Nombre del departamento: " + resultSet.getString("Nombre del departamento"));
                    System.out.println("Nombre del puesto: " + resultSet.getString("Nombre del puesto"));
                    System.out.println(
                            "Fecha de inicio de la renta: " + resultSet.getString("Fecha de inicio de la renta"));
                    System.out.println("Número del locker rentado: " + resultSet.getInt("Número del locker rentado"));
                    System.out.println("Tamaño del locker: " + resultSet.getString("Tamaño del locker"));
                    System.out.println("Ubicación del locker: " + resultSet.getString("Ubicación del locker"));
                    System.out.println("Precio del locker: " + resultSet.getFloat("Precio del locker"));
                    System.out.println("Número de pago del locker: " + resultSet.getInt("Número de pago del locker"));
                    System.out.println("Fecha del pago: " + resultSet.getString("Fecha del pago"));
                    System.out.println("Monto del pago: " + resultSet.getFloat("Monto del pago"));
                    System.out.println("Método de pago: " + resultSet.getString("Método de pago"));
                    System.out.println("------------------------------------------------------------------");
                }
            }

            // Cerrar la conexión a la base de datos.
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 4
    public void consultarListaRentaAlumnos() {
        // Consulta para obtener la lista de rentas por alumnos ordenadas por nombre de
        // carrera.
        sqlConsult = "SELECT " +
                "r.Num_Renta AS 'Renta', " +
                "concat(ifnull(concat(s.Ap_Paterno, ' ' ), ' ' ), " +
                "ifnull(concat(s.ApMaterno, ' '), ' '), " +
                "ifnull(concat(s.Nombre_Pila, ' '), ' ')" +
                ") AS 'Nombre', " +
                "c.Descripcion AS 'Carrera', " +
                "l.Num AS 'Locker', " +
                "tam.Descripcion AS 'Tamaño', " +
                "CONCAT('Docencia ', u.Doncencia, ' - Piso ', u.Piso) AS 'Ubicacion' " +
                "FROM Renta r " +
                "INNER JOIN Solicitante s ON r.Num_Solicitante = s.Num_Solicitante " +
                "INNER JOIN Alumno a ON s.Alumno = a.Matricula " +
                "INNER JOIN Carrera c ON a.Carrera = c.Num " +
                "INNER JOIN Lockers l ON r.Lockers = l.Num " +
                "INNER JOIN Tamaño tam ON l.Tamaño = tam.Num " +
                "INNER JOIN Ubicacion u ON l.Ubicacion = u.Codigo " +
                "ORDER BY c.Descripcion";

        try {
            cDBF = dbF.getConexion();
            statement = cDBF.createStatement();
            resultSet = statement.executeQuery(sqlConsult);

            // Mostrar la lista de rentas por alumnos ordenadas por nombre de carrera.
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No hay rentas registradas para ningún alumno.");
            } else {
                rc.limpiarPantalla();
                // Formato de impresión
                formato = "%-5s| %-40s| %-60s| %-6s| %-6s| %-10s%n";

                System.out.println("LISTA DE RENTAS DE ALUMNOS");
                // Imprimir separador
                System.out.println("------------------------------------------------------------------");

                // Imprimir titulos
                System.out.printf(formato, "Renta", "Nombre", "Carrera", "Locker", "Tamaño", "Ubicacion");

                // Imprimir separador
                System.out.println("------------------------------------------------------------------");
                while (resultSet.next()) {

                    // Imprimir fila con formato
                    System.out.printf(formato, resultSet.getInt("Renta"), resultSet.getString("Nombre"),
                            resultSet.getString("Carrera"), resultSet.getInt("Locker"), resultSet.getString("Tamaño"),
                            resultSet.getString("Ubicacion"));
                }
            }

            // Cerrar la conexión a la base de datos.
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // 5
    public void consultarListaRentasDocencia(int d) {
        // Consulta para obtener la lista de lockers rentados en la docencia
        // especificada.
        sqlConsult = "SELECT " +
                "u.Doncencia AS 'Docencia', " +
                "r.Num_Renta AS 'Renta', " +
                "concat(ifnull(concat(s.Ap_Paterno, ' ' ), ' ' ), " +
                "ifnull(concat(s.ApMaterno, ' '), ' '), " +
                "ifnull(concat(s.Nombre_Pila, ' '), ' ')" +
                ") AS 'Nombre', " +
                "r.Fecha_Inicio AS 'Fecha de inicio', " +
                "l.Num AS 'Locker', " +
                "tam.Descripcion AS 'Tamaño', " +
                "u.Piso AS 'Piso', " +
                "tam.precio AS 'Precio' " +
                "FROM Renta r " +
                "INNER JOIN Solicitante s ON r.Num_Solicitante = s.Num_Solicitante " +
                "INNER JOIN Lockers l ON r.Lockers = l.Num " +
                "INNER JOIN Tamaño tam ON l.Tamaño = tam.Num " +
                "INNER JOIN Ubicacion u ON l.Ubicacion = u.Codigo " +
                "WHERE u.Doncencia = " + d + " " +
                "AND l.Disponibilidad = 0 " +
                "ORDER BY u.Doncencia, r.Num_Renta";

        try {
            cDBF = dbF.getConexion();
            statement = cDBF.createStatement();
            resultSet = statement.executeQuery(sqlConsult);

            // Mostrar la información de la lista de lockers rentados en la docencia
            // especificada.
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No hay lockers rentados en la docencia especificada.");
            } else {
                rc.limpiarPantalla();
                // Formato de impresión
                formato = "%-12s| %-35s| %-15s| %-10s| %-20s| %-10s| %-10s%n";

                System.out.println("LISTA DE LOCKERS RENTADOS EN DOCENCIA " + d);
                // Imprimir separador
                System.out.println("------------------------------------------------------------------");

                // Imprimir titulos
                System.out.printf(formato, "Renta", "Nombre", "Fecha", "Locker", "Tamaño", "Piso",
                        "Precio");

                // Imprimir separador
                System.out.println("------------------------------------------------------------------");
                while (resultSet.next()) {

                    // Imprimir fila con formato
                    System.out.printf(formato, resultSet.getInt("Renta"),
                            resultSet.getString("Nombre"), resultSet.getString("Fecha de inicio"),
                            resultSet.getInt("Locker"), resultSet.getString("Tamaño"), resultSet.getInt("Piso"),
                            resultSet.getFloat("Precio"));
                }
            }

            // Cerrar la conexión a la base de datos.
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 6
    public void consultarListaDisponibilidadDocencia(int d) {
        // Consulta para obtener la lista de lockers disponibles en la docencia
        // especificada.
        sqlConsult = "SELECT " +
                "u.Doncencia AS 'Docencia', " +
                "l.Num AS 'Numero del locker', " +
                "tam.Descripcion AS 'Tamaño', " +
                "u.Piso AS 'Piso', " +
                "tam.precio AS 'Precio' " +
                "FROM Lockers l " +
                "INNER JOIN Tamaño tam ON l.Tamaño = tam.Num " +
                "INNER JOIN Ubicacion u ON l.Ubicacion = u.Codigo " +
                "WHERE u.Doncencia = '" + d + "' AND l.Disponibilidad = 1 " +
                "ORDER BY u.Piso";

        try {
            cDBF = dbF.getConexion();
            statement = cDBF.createStatement();
            resultSet = statement.executeQuery(sqlConsult);

            // Mostrar la información de los lockers disponibles en la docencia
            // especificada.
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No hay lockers disponibles en la docencia especificada.");
            } else {
                rc.limpiarPantalla();

                formato = "%-15s | %-15s | %-15s | %s%n ";

                System.out.println("LOCKERS DISPONIBLES PARA RENTAR EN DOCENCIA " + d);
                System.out.println("------------------------------------------------------------------");
                System.out.printf(formato, "Piso", "Numero del locker", "Tamaño", "Precio");
                System.out.println("------------------------------------------------------------------");
                while (resultSet.next()) {

                    // Imprimir los datos con el formato especificado
                    System.out.printf(formato, resultSet.getInt("Piso"),
                            resultSet.getInt("Numero del locker"), resultSet.getString("Tamaño"),
                            resultSet.getInt("Precio"));
                }

            }

            // Cerrar la conexión a la base de datos.
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 7
    public void consultarDisponibilidadLockers(int d) {
        // Consulta para obtener la cantidad de lockers disponibles y no disponibles de
        // una docencia.
        sqlConsult = "SELECT " +
                "u.Doncencia AS 'Docencia', " +
                "SUM(IF(l.Disponibilidad = 1, 1, 0)) AS 'Disponibles', " +
                "SUM(IF(l.Disponibilidad = 0, 1, 0)) AS 'No disponibles' " +
                "FROM Lockers l " +
                "INNER JOIN Ubicacion u ON l.Ubicacion = u.Codigo " +
                "WHERE u.Doncencia = " + d + " " +
                "GROUP BY u.Doncencia";

        try {
            cDBF = dbF.getConexion();
            statement = cDBF.createStatement();
            resultSet = statement.executeQuery(sqlConsult);

            rc.limpiarPantalla();

            formato = "%-15s | %-15s%n ";

            System.out.println("LISTA DE LOCKERS DISPONIBLES EN DOCENCIA " + d);
            System.out.println("------------------------------------------------------------------");
            System.out.printf(formato, "Disponible", "No disponible");
            System.out.println("------------------------------------------------------------------");
            while (resultSet.next()) {

                // Imprimir los datos con el formato especificado
                System.out.printf(formato, resultSet.getInt("Disponibles"),
                        resultSet.getInt("No disponibles"));
            }

            // Cerrar la conexión a la base de datos.
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // 8
    public void consultarListaPrecios() {
        // Consulta para obtener la lista de precios de los lockers.
        sqlConsult = "SELECT " +
                "t.Descripcion AS 'Tamaño', " +
                "t.precio AS 'Precio' " +
                "FROM Tamaño t";

        try {
            cDBF = dbF.getConexion();
            statement = cDBF.createStatement();
            resultSet = statement.executeQuery(sqlConsult);

            // Mostrar la lista de precios de los lockers.
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No hay precios de lockers registrados.");
            } else {
                // Formato de impresión
                formato = "%-12s | %-35s%n";

                System.out.println("LISTA DE PRECIOS");
                // Imprimir separador
                System.out.println("------------------------------------------------------------------");

                // Imprimir titulos
                System.out.printf(formato, "Tamaño", "Precio");

                // Imprimir separador
                System.out.println("------------------------------------------------------------------");
                while (resultSet.next()) {

                    // Imprimir fila con formato
                    System.out.printf(formato, resultSet.getString("Tamaño"), resultSet.getFloat("Precio"));
                }
            }

            // Cerrar la conexión a la base de datos.
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 9
    public void consultarTotalLockersDocencia() {
        // Consulta para obtener la cantidad total de lockers por docencia.
        sqlConsult = "SELECT " +
                "u.Doncencia AS 'Docencia', " +
                "COUNT(l.Num) AS 'Cantidad' " +
                "FROM Ubicacion u " +
                "LEFT JOIN Lockers l ON u.Codigo = l.Ubicacion " +
                "GROUP BY u.Doncencia";

        try {
            cDBF = dbF.getConexion();
            statement = cDBF.createStatement();
            resultSet = statement.executeQuery(sqlConsult);

            // Mostrar la cantidad total de lockers por docencia.
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No hay lockers registrados en ninguna docencia.");
            } else {
                rc.limpiarPantalla();
                // Formato de impresión
                formato = "%-12s | %-15s%n";

                System.out.println("LISTA DE LOCKERS POR DOCENCIA");
                // Imprimir separador
                System.out.println("------------------------------------------------------------------");

                // Imprimir titulos
                System.out.printf(formato, "Docencia", "Cantidad");

                // Imprimir separador
                System.out.println("------------------------------------------------------------------");
                while (resultSet.next()) {

                    // Imprimir fila con formato
                    System.out.printf(formato, resultSet.getInt("Docencia"), resultSet.getInt("Cantidad"));
                }
            }

            // Cerrar la conexión a la base de datos.
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 10
    public void consultarTotalLockersPisoDocencia(int d) {
        // Consulta para obtener la cantidad total de lockers por piso de una docencia
        // específica.
        sqlConsult = "SELECT " +
                "u.Piso, " +
                "COUNT(l.Num) AS 'Cantidad' " +
                "FROM Ubicacion u " +
                "INNER JOIN Lockers l ON u.Codigo = l.Ubicacion " +
                "WHERE u.Doncencia = " + d + " " +
                "GROUP BY u.Piso";

        try {
            cDBF = dbF.getConexion();
            statement = cDBF.createStatement();
            resultSet = statement.executeQuery(sqlConsult);

            // Mostrar la cantidad total de lockers por piso de la docencia especificada.
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No hay lockers registrados en el piso de la docencia especificada.");
            } else {
                rc.limpiarPantalla();
                // Formato de impresión
                formato = "%-12s | %-35s%n";

                System.out.println("LISTA DE LOCKERS POR PISO DE DOCENCIA " + d);
                // Imprimir separador
                System.out.println("------------------------------------------------------------------");

                // Imprimir titulos
                System.out.printf(formato, "PISO", "Cantidad");

                // Imprimir separador
                System.out.println("------------------------------------------------------------------");
                while (resultSet.next()) {

                    // Imprimir fila con formato
                    System.out.printf(formato, resultSet.getInt("Piso"), resultSet.getInt("Cantidad"));
                }

            }

            // Cerrar la conexión a la base de datos.
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 11
    public void consultarListaCarreras() {
        sqlConsult = "SELECT Num AS 'ID', Descripcion FROM Carrera";

        try {
            cDBF = dbF.getConexion();
            statement = cDBF.createStatement();
            resultSet = statement.executeQuery(sqlConsult);

            // Mostrar la cantidad total de lockers por piso de la docencia especificada.
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No hay lockers registrados en el piso de la docencia especificada.");
            } else {
                rc.limpiarPantalla();
                // Formato de impresión
                formato = "%-12s| %-35s%n ";

                System.out.println("LISTA DE CARRERAS");
                // Imprimir separador
                System.out.println("------------------------------------------------------------------");
                // Imprimir titulos
                System.out.printf(formato, "Num. Carrera", "Nombre de la carrera");

                // Imprimir separador
                System.out.println("------------------------------------------------------------------");

                while (resultSet.next()) {
                    System.out.printf(formato, resultSet.getInt("ID"), resultSet.getString("Descripcion"));
                }

            }

            // Cerrar la conexión a la base de datos.
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // 12
    public void consultarListaDepartamento() {
        sqlConsult = "SELECT " +
                "Num AS 'Departamento', Nombre AS 'Nombre del departamento' "
                +
                "FROM Departamento ";

        try {
            cDBF = dbF.getConexion();
            statement = cDBF.createStatement();
            resultSet = statement.executeQuery(sqlConsult);

            // Mostrar la cantidad total de lockers por piso de la docencia especificada.
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No hay lockers registrados en el piso de la docencia especificada.");
            } else {
                rc.limpiarPantalla();
                // Formato de impresión
                formato = "%-15s | %s%n";

                System.out.println("DEPARTAMENTOS");
                // Imprimir separador
                System.out.println("------------------------------------------------------------------");

                // Imprimir titulos
                System.out.printf(formato, "Num. de Departamento",
                        "Nombre del Departamento");

                // Imprimir separador
                System.out.println("------------------------------------------------------------------");
                while (resultSet.next()) {

                    // Imprimir fila con formato
                    System.out.printf(formato, resultSet.getInt("Departamento"),
                            resultSet.getString("Nombre del departamento"));
                }

            }

            // Cerrar la conexión a la base de datos.
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // 13
    public void consultarListaSolicitantes(int d) {
        sqlConsult = "SELECT Num_Solicitante AS ID, CONCAT(Nombre_Pila, ' ', Ap_Paterno, ' ', ApMaterno) AS Nombre " +
                "FROM Solicitante WHERE " + d + " IN (Alumno, Docente, Admnistrativo)";

        try {
            cDBF = dbF.getConexion();
            statement = cDBF.createStatement();
            resultSet = statement.executeQuery(sqlConsult);

            // Mostrar la cantidad total de lockers por piso de la docencia especificada.
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No hay lockers registrados en el piso de la docencia especificada.");
            } else {
                rc.limpiarPantalla();
                // Formato de impresión
                formato = "%-25s | %-35s%n";

                System.out.println("LISTA DE SOLICITANTES");
                // Imprimir separador
                System.out.println("------------------------------------------------------------------");

                // Imprimir titulos
                System.out.printf(formato, "Num. Solicitante", "Nombre");

                // Imprimir separador
                System.out.println("------------------------------------------------------------------");
                while (resultSet.next()) {

                    // Imprimir fila con formato
                    System.out.printf(formato, resultSet.getInt("ID"), resultSet.getString("Nombre"));
                }

            }

            // Cerrar la conexión a la base de datos.
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // 14
    public void consultarMetodosPago() {
        sqlConsult = "SELECT Num_Metodo AS ID, Descripcion FROM metodo_de_pago";

        try {
            cDBF = dbF.getConexion();
            statement = cDBF.createStatement();
            resultSet = statement.executeQuery(sqlConsult);

            // Mostrar la cantidad total de lockers por piso de la docencia especificada.
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No hay lockers registrados en el piso de la docencia especificada.");
            } else {
                rc.limpiarPantalla();
                // Formato de impresión
                formato = "%-25s | %-35s%n";

                System.out.println("LISTA DE METODOS DE PAGO");
                // Imprimir separador
                System.out.println("------------------------------------------------------------------");

                // Imprimir titulos
                System.out.printf(formato, "Opcion de metodo", "Descripcion de pago");

                // Imprimir separador
                System.out.println("------------------------------------------------------------------");
                while (resultSet.next()) {

                    // Imprimir fila con formato
                    System.out.printf(formato, resultSet.getInt("ID"), resultSet.getString("Descripcion"));
                }

            }

            // Cerrar la conexión a la base de datos.
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // 15
    public void consultarListaRentas() {
        sqlConsult = "SELECT r.Num_Renta AS ID, r.Fecha_Inicio AS Fecha, CONCAT(s.Nombre_Pila, ' ', s.Ap_Paterno, ' ', s.ApMaterno) AS Nombre "
                +
                "FROM Renta r " +
                "INNER JOIN Solicitante s ON r.Num_Solicitante = s.Num_Solicitante";

        try {
            cDBF = dbF.getConexion();
            statement = cDBF.createStatement();
            resultSet = statement.executeQuery(sqlConsult);

            // Mostrar la cantidad total de lockers por piso de la docencia especificada.
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No hay lockers registrados en el piso de la docencia especificada.");
            } else {
                rc.limpiarPantalla();
                // Formato de impresión
                formato = "%-12s| %-35s| %s%n";

                System.out.println("LISTA DE RENTAS");
                // Imprimir separador
                System.out.println("------------------------------------------------------------------");

                // Imprimir titulos
                System.out.printf(formato, "ID", "Fecha de inicio", "Nombre");

                // Imprimir separador
                System.out.println("------------------------------------------------------------------");
                while (resultSet.next()) {

                    // Imprimir fila con formato
                    System.out.printf(formato, resultSet.getInt("ID"), resultSet.getString("Fecha"),
                            resultSet.getString("Nombre"));
                }

            }

            // Cerrar la conexión a la base de datos.
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // 16
    public void consultarRentasPorFinalizar() {
        String sqlConsult = "SELECT r.Num_Renta AS ID, r.Fecha_Inicio AS 'Fecha Inicio', r.Fecha_Final as 'Fecha Fin', CONCAT(s.Nombre_Pila, ' ', s.Ap_Paterno, ' ', s.ApMaterno) AS Nombre "
                + "FROM Renta r " +
                "INNER JOIN Solicitante s ON r.Num_Solicitante = s.Num_Solicitante " +
                "WHERE r.Fecha_Final <= DATE_ADD(CURDATE(), INTERVAL 7 DAY)";

        try {
            // Establecer la conexión a la base de datos
            cDBF = dbF.getConexion();
            statement = cDBF.createStatement();
            resultSet = statement.executeQuery(sqlConsult);

            // Mostrar las rentas próximas a finalizar
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No hay rentas próximas a finalizar en los próximos 7 días.");
            } else {
                rc.limpiarPantalla();
                // Formato de impresión
                String formato = "%-12s| %-35s| %-35s| %s%n";

                System.out.println("LISTA DE RENTAS PRÓXIMAS A FINALIZAR (dentro de los próximos 7 días)");
                // Imprimir separador
                System.out.println("------------------------------------------------------------------");

                // Imprimir títulos
                System.out.printf(formato, "Num. Renta", "Fecha de inicio", "Fecha Fin", "Nombre");

                // Imprimir separador
                System.out.println("------------------------------------------------------------------");
                while (resultSet.next()) {
                    // Imprimir fila con formato
                    System.out.printf(formato, resultSet.getInt("ID"), resultSet.getString("Fecha Inicio"),
                            resultSet.getString("Fecha Fin"),
                            resultSet.getString("Nombre"));
                }

            }

            // Cerrar la conexión a la base de datos
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 17
    public void consultarSolicitanteDocente(int d) {
        String sqlConsult = "SELECT Num_Solicitante AS ID, CONCAT(Nombre_Pila, ' ', Ap_Paterno, ' ', ApMaterno) AS Nombre "
                + "FROM Solicitante "
                + "WHERE Docente = " + d;

        try {
            // Establecer la conexión a la base de datos
            cDBF = dbF.getConexion();
            statement = cDBF.createStatement();
            resultSet = statement.executeQuery(sqlConsult);

            // Mostrar las rentas próximas a finalizar
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No hay solicitantes con ese numero de matricula y/o empleado");
            } else {
                rc.limpiarPantalla();
                // Formato de impresión
                String formato = "%-25s| %-35s%n";

                System.out.println("ACTUALIZACION DE DATOS");
                // Imprimir separador
                System.out.println("------------------------------------------------------------------");

                // Imprimir títulos
                System.out.printf(formato, "Num. Solicitante", "Nombre");

                // Imprimir separador
                System.out.println("------------------------------------------------------------------");
                while (resultSet.next()) {
                    // Imprimir fila con formato
                    System.out.printf(formato, resultSet.getInt("ID"), resultSet.getString("Nombre"));
                }

            }

            // Cerrar la conexión a la base de datos
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 18
    public void consultarSolicitanteAlumno(int d) {
        String sqlConsult = "SELECT Num_Solicitante AS ID, CONCAT(Nombre_Pila, ' ', Ap_Paterno, ' ', ApMaterno) AS Nombre "
                + "FROM Solicitante "
                + "WHERE Alumno = " + d;

        try {
            // Establecer la conexión a la base de datos
            cDBF = dbF.getConexion();
            statement = cDBF.createStatement();
            resultSet = statement.executeQuery(sqlConsult);

            // Mostrar las rentas próximas a finalizar
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No hay solicitantes con ese numero de matricula y/o empleado");
            } else {
                rc.limpiarPantalla();
                // Formato de impresión
                String formato = "%-25s| %-35s%n";

                System.out.println("ACTUALIZACION DE DATOS");
                // Imprimir separador
                System.out.println("------------------------------------------------------------------");

                // Imprimir títulos
                System.out.printf(formato, "Nu,. Solicitante", "Nombre");

                // Imprimir separador
                System.out.println("------------------------------------------------------------------");
                while (resultSet.next()) {
                    // Imprimir fila con formato
                    System.out.printf(formato, resultSet.getInt("ID"), resultSet.getString("Nombre"));
                }

            }

            // Cerrar la conexión a la base de datos
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 19
    public void consultarSolicitanteAdm(int d) {
        String sqlConsult = "SELECT Num_Solicitante AS ID, CONCAT(Nombre_Pila, ' ', Ap_Paterno, ' ', ApMaterno) AS Nombre "
                + "FROM Solicitante "
                + "WHERE Admnistrativo = " + d;

        try {
            // Establecer la conexión a la base de datos
            cDBF = dbF.getConexion();
            statement = cDBF.createStatement();
            resultSet = statement.executeQuery(sqlConsult);

            // Mostrar las rentas próximas a finalizar
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No hay solicitantes con ese numero de matricula y/o empleado");
            } else {
                rc.limpiarPantalla();
                // Formato de impresión
                String formato = "%-25s| %-35s%n";

                System.out.println("ACTUALIZACION DE DATOS");
                // Imprimir separador
                System.out.println("------------------------------------------------------------------");

                // Imprimir títulos
                System.out.printf(formato, "Num. Solicitante", "Nombre");

                // Imprimir separador
                System.out.println("------------------------------------------------------------------");
                while (resultSet.next()) {
                    // Imprimir fila con formato
                    System.out.printf(formato, resultSet.getInt("ID"), resultSet.getString("Nombre"));
                }

            }

            // Cerrar la conexión a la base de datos
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 20
    public void consultarNumeroSolicitante(int d) {
        String consultaSolicitanteQuery = "SELECT Num_Solicitante, CONCAT(Nombre_Pila, ' ', Ap_Paterno, ' ', ApMaterno) AS Nombre "
                +
                "FROM Solicitante " +
                "WHERE " + d + " IN (Alumno, Docente, Admnistrativo)";

        try {
            // Establecer la conexión a la base de datos
            cDBF = dbF.getConexion();
            statement = cDBF.createStatement();
            resultSet = statement.executeQuery(consultaSolicitanteQuery);

            // Mostrar las rentas próximas a finalizar
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No hay solicitantes con ese numero de matricula y/o empleado");
            } else {
                rc.limpiarPantalla();
                // Formato de impresión
                String formato = "%-18s| %s%n";

                System.out.println("ACTUALIZACION DE DATOS");
                // Imprimir separador
                System.out.println("------------------------------------------------------------------");

                // Imprimir títulos
                System.out.printf(formato, "Num. Solicitante", "Nombre");

                // Imprimir separador
                System.out.println("------------------------------------------------------------------");
                while (resultSet.next()) {
                    // Imprimir fila con formato
                    System.out.printf(formato, resultSet.getInt("Num_Solicitante"), resultSet.getString("Nombre"));
                }

            }

            // Cerrar la conexión a la base de datos
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void consultarListaPuestos(int d) {
        sqlConsult = "SELECT " +
                "dp.Puesto AS Puesto, p.Nombre AS 'Nombre del puesto'"
                +
                "FROM depa_puesto dp "+
                "INNER JOIN puesto p ON dp.Puesto = p.Codigo "+
                "WHERE dp.Departamento = " + d;

        try {
            cDBF = dbF.getConexion();
            statement = cDBF.createStatement();
            resultSet = statement.executeQuery(sqlConsult);

            // Mostrar la cantidad total de lockers por piso de la docencia especificada.
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No hay lockers registrados en el piso de la docencia especificada.");
            } else {
                rc.limpiarPantalla();
                // Formato de impresión
                formato = "%-15s | %s%n";

                System.out.println("PUESTOS");
                // Imprimir separador
                System.out.println("------------------------------------------------------------------");

                // Imprimir titulos
                System.out.printf(formato, "Codigo de puesto",
                        "Nombre del puesto");

                // Imprimir separador
                System.out.println("------------------------------------------------------------------");
                while (resultSet.next()) {

                    // Imprimir fila con formato
                    System.out.printf(formato, resultSet.getString("Puesto"),
                            resultSet.getString("Nombre del puesto"));
                }

            }

            // Cerrar la conexión a la base de datos.
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}