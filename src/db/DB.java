package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

	private static Connection conn = null;

	public static Connection getConnection() {
		if(conn == null) {//não conectou
			try{
			Properties props = loadProperties();//pegando as propriedades do banco de dados
			String url = props.getProperty("dburl"); //url do banco de dados
			conn = DriverManager.getConnection(url, props);//conectando com o banco de dados
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	public static void closeConnection() {
		if(conn != null) {
			try {
			  conn.close();
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);// load faz a leitura do arquivo props apontado pelo InputStream fs e vai guarda
							// os dados do objeto props
			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
}
