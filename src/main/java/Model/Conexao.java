package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	Connection conn;
	static final String URL = "jdbc:postgresql://localhost:5432/poluicaoFinal";
	static final String USER = "postgres";
	static final String PASS = "1234";
	static Connection con;

	
	public static Connection conectando() {
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(URL, USER, PASS);
			if (con != null) {
				return con;
			} else {
				return con;
			}
		} catch (ClassNotFoundException e) {
			e.getMessage();
			return null;
		} catch (SQLException sql) {
			sql.getMessage();
			return null;
		}
	}
}
