package application;

import java.sql.Connection;

import db.DB;

public class Program {

	public static void main(String[] args) {
		Connection conn = DB.getConnection();//conex�o com o banco de dados
		DB.closeConnection();

	}

}
