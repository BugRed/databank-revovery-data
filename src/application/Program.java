package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {

		//Conectar com o banco
		Connection conn = null;
		//Executar um comando no Mysql
		Statement st = null;
		//guarda o resultado da execução do Statement
		ResultSet rs = null;
		
		try {
			
			
			conn = DB.getConnection();
			
			st = conn.createStatement();
			//escrever aqui abaixo o codigo do MySQL que deseja acessar
			rs = st.executeQuery("select * from department");
			
			while(rs.next()) {
				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
			}
			
			
			} catch(SQLException e) {
				e.printStackTrace();
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
