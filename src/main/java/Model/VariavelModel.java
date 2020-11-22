package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VariavelModel {

	private int idVariavel;
	private String NomeVariavel;
	private String Frequencia;
	private String UndadeMedida;
	

	public VariavelModel(int idVariavel, String nomeVariavel, String frequencia, String undadeMedida) {
		super();
		this.idVariavel = idVariavel;
		NomeVariavel = nomeVariavel;
		Frequencia = frequencia;
		UndadeMedida = undadeMedida;
	}

	public VariavelModel() {
		// TODO Auto-generated constructor stub
	}

	public int getIdVariavel() {
		return idVariavel;
	}

	public void setIdVariavel(int idVaraivelo) {
		this.idVariavel = idVaraivelo;
	}

	public String getNomeVariavel() {
		return NomeVariavel;
	}

	public void setNomeVariavel(String nomeVariavel) {
		NomeVariavel = nomeVariavel;
	}

	public String getFrequencia() {
		return Frequencia;
	}

	public void setFrequencia(String frequencia) {
		Frequencia = frequencia;
	}

	public String getUndadeMedida() {
		return UndadeMedida;
	}

	public void setUndadeMedida(String undadeMedida) {
		UndadeMedida = undadeMedida;
	}
	
	
	
	@Override
	public String toString() {
		return "VariavelModel [idVaraivelo=" + idVariavel + ", NomeVariavel=" + NomeVariavel + ", Frequencia="
				+ Frequencia + ", UndadeMedida=" + UndadeMedida + "]";
	}

	public List<VariavelModel> BuscaraALLVariavel()   {
		List<VariavelModel> lista = new ArrayList<VariavelModel>();
		Statement stmt=null;
		ResultSet rs = null;
		
		try {
			stmt = Conexao.conectando().createStatement();
			rs = stmt.executeQuery("SELECT * FROM \"Variaveis\" ");
			
			while(rs.next()) {
				lista.add(new VariavelModel(rs.getInt(1),rs.getString(3), rs.getString(4), rs.getString(2)));
				
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
	
	public void mostrarALL(List<VariavelModel> lista) {
		
		try {
			for (VariavelModel variavelModel : lista) {
				System.out.println(variavelModel);
			}
		}catch(Exception e) {
			e.getMessage();
		}
		
		
	}
	
	public  void cadastrarVariavel(String conversaoSigla, String nome, String frequencia) {
	System.out.println(conversaoSigla + " - " + nome + " - " + frequencia);
		String sql ="Insert into \"Variaveis\" (\"UndadeMedida\",\"NomeVariavel\",\"Frequencia\") values ("
				+ " '"+conversaoSigla+"'"
				+ ",'"+nome+"'"
				+ ",'"+frequencia+"')";
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
