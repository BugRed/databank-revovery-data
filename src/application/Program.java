package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	
		Connection conn = null;
		//recebe comoparametro um comando grande de SQL
		PreparedStatement st = null;
		try {
			
			//fazendo a conexão com o banco
			conn = DB.getConnection();
			
			
			st = conn.prepareStatement(
					"INSERT INTO SELLER"
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
					+ "VALUES"
					+ "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);//placeholder: pode adicionar o valor depois
			st.setString(1, "Carl Purple");
			st.setString(2, "carl@gmail.com");
			//para inserir data precisa usar java.sql na declaração
			st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
			st.setDouble(4, 3000.0);
			st.setInt(5, 4);
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while(rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id = " + id);
				}
				
			}else { 
				System.out.println("No rowaffected!");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		finally{
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
