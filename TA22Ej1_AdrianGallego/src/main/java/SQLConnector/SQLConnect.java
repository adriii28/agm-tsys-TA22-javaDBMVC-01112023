package SQLConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Models.Client;

public class SQLConnect {

	static Connection conexion = null;
	final static String db = "TA22Ej1";
	final static String table_name = "cliente";

	public static Connection ConexionDB() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:33060", "root", "admin1234");
			System.out.println("Server Connected");

		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("No se ha podido conectar con la base de datos");
			System.out.println(e);
		}

		return conexion;
	}

	public static ArrayList<Client> getValues() {
		Connection conexion = ConexionDB();
		ArrayList<Client> listaClientes = new ArrayList<Client>();

		try {

			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "SELECT * FROM " + table_name;
			Statement st = conexion.createStatement();
			ResultSet resultSet;
			resultSet = st.executeQuery(Query);

			while (resultSet.next()) {
				listaClientes.add(new Client(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6)));
			}

			closeConnection();
			return listaClientes;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error en la adquisicion de datos");
		}
		return null;

	}

	public static Client getClient(int id) {
		try {
			Connection conexion = ConexionDB();
			Client c = new Client();

			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "SELECT * FROM " + table_name + " WHERE id = ?";
			PreparedStatement pst = conexion.prepareStatement(Query);
			pst.setInt(1, id);
			System.out.println(Query);

			try {

				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					c.setId(rs.getInt(1));
					c.setNombre(rs.getString(2));
					c.setApellidos(rs.getString(3));
					c.setDireccion(rs.getString(4));
					c.setDni(rs.getInt(5));
					c.setFecha(rs.getString(6));
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Error en la adquisicion de datos");
			}

			closeConnection();
			return c;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error en la adquisicion de datos");
		}
		return null;

	}

	public static boolean insertData(Client c) {
		boolean state = false;
		try {
			Connection conexion = ConexionDB();

			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);
			System.out.println("Esta utilizando la base de datos: " + db);

			String Query = "INSERT INTO " + table_name + " (nombre, apellido, direccion, dni, fecha)" + " VALUES ('"
					+ c.getNombre() + "', '" + c.getApellidos() + "', '" + c.getDireccion() + "', '" + c.getDni()
					+ "', '" + c.getFecha() + "')";
			Statement st = conexion.createStatement();
			st.execute(Query);
			state = true;
			System.out.println("Datos almacenados correctamente");

			closeConnection();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Error en el almacenamiento");
		}
		return state;

	}

	public static boolean updateData(Client c) {
		boolean state = false;
		try {
			Connection conexion = ConexionDB();

			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);
			System.out.println("Esta utilizando la base de datos: " + db);

			String Query = "UPDATE " + table_name + " SET nombre = '" + c.getNombre() + "', apellido= '"
					+ c.getApellidos() + "', direccion = '" + c.getDireccion() + "', dni = '" + c.getDni()
					+ "', fecha = '" + c.getFecha() + "' WHERE id =" + c.getId();
			System.out.println(Query);
			Statement st = conexion.createStatement();
			st.execute(Query);
			state = true;
			System.out.println("Datos modificados correctamente");

			closeConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error al modificar el usuario");
		}
		return state;
	}

	public static void deleteData(int id) {
		try {
			Connection conexion = ConexionDB();

			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);
			System.out.println("Esta utilizando la base de datos: " + db);

			String Query = "DELETE FROM " + table_name + " WHERE id = " + id;
			System.out.println(Query);
			Statement st = conexion.createStatement();
			st.execute(Query);
			System.out.println("Cliente eliminado correctamente");

			closeConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error al eliminar el usuario");
		}
	}

	public static void closeConnection() {
		try {
			conexion.close();
			System.out.println("Se ha finalizado la conexion");
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}
}
