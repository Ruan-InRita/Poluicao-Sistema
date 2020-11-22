package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegiaoModel {

	private int idRegiao;
	private String nomeRegiao;
	
	
	public RegiaoModel(int idRegiao, String nomeRegiao) {
		super();
		this.idRegiao = idRegiao;
		this.nomeRegiao = nomeRegiao;
	}

	

	public RegiaoModel() {
		
	}


	public int getIdRegiao() {
		return idRegiao;
	}



	public void setIdRegiao(int idRegiao) {
		this.idRegiao = idRegiao;
	}



	public String getNomeRegiao() {
		return nomeRegiao;
	}



	public void setNomeRegiao(String nomeRegiao) {
		this.nomeRegiao = nomeRegiao;
	}



	@Override
	public String toString() {
		return "RegiaoModel [idRegiao=" + idRegiao + ", nomeRegiao=" + nomeRegiao + "]";
	}
	
	public void cadastrarRegiaoVAZIO() {
		
		String sql ="Insert into \"Regiao\" (\"NomeRegiao\") values ('vazio')";
				
		PreparedStatement ps = null;
		
		try {
			ps = Conexao.conectando().prepareStatement(sql);	
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}		
		
		try {
			ps.getConnection().close();
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public int buscarIdVazio()   {
		Statement stmt=null;
		ResultSet rs = null;
		int idRegiao = 0;
		try {
			stmt = Conexao.conectando().createStatement();
			rs = stmt.executeQuery("SELECT \"idRegiao\" FROM \"Regiao\" where \"NomeRegiao\" = 'vazio' ");
			if(rs.next()) {
				idRegiao = rs.getInt(1);
			}			
			return idRegiao;
		}catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			stmt.getConnection().close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return idRegiao;
	}
	
	
	
	
}
