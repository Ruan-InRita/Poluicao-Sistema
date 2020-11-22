package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EstacaoModel {
	
	private int idEstacao;
	private String NomeEstacao;
	private int idRegiao;
	private String tipo;
	
		
	public EstacaoModel() {
		super();
	}
	
	public EstacaoModel(int idEstacao, String nomeEstacao, int idRegiao, String tipo) {
		super();
		this.idEstacao = idEstacao;
		NomeEstacao = nomeEstacao;
		this.idRegiao = idRegiao;
		this.tipo = tipo;
	}
	
	public int getIdEstacao() {
		return idEstacao;
	}
	public void setIdEstacao(int idEstacao) {
		this.idEstacao = idEstacao;
	}
	public String getNomeEstacao() {
		return NomeEstacao;
	}
	public void setNomeEstacao(String nomeEstacao) {
		NomeEstacao = nomeEstacao;
	}
	public int getIdRegiao() {
		return idRegiao;
	}
	public void setIdRegiao(int idRegiao) {
		this.idRegiao = idRegiao;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public List<EstacaoModel> BuscarEstacaoALL()   {
		List<EstacaoModel> lista = new ArrayList<EstacaoModel>();
		Statement stmt=null;
		ResultSet rs = null;
		try {
			stmt = Conexao.conectando().createStatement();
			rs = stmt.executeQuery("SELECT * FROM \"Estacao\" ");
			
			while(rs.next()) {
				
				lista.add(new EstacaoModel(rs.getInt(1),rs.getString(4), rs.getInt(7), rs.getString(5)));
			}			
			return lista;
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
		
		
		return lista;
	}
	
	public void cadastrarEstacao(String nomeEstacao, String tipo) {
		
		RegiaoModel rm = new RegiaoModel();
		String sql = null;
		int idRegiao = 0 ;
		while(idRegiao == 0) {
			idRegiao = rm.buscarIdVazio();
			if(idRegiao == 0) {
				rm.cadastrarRegiaoVAZIO();
			}
		}
		
		
		sql ="Insert into \"Estacao\" (\"NomeEstacao\",\"Tipo\",\"idRegiao\") values ("
				+ " '"+nomeEstacao+"'"
				+ ",'"+tipo+"'"
				+ ",'"+idRegiao+"')";
		
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
	

}
