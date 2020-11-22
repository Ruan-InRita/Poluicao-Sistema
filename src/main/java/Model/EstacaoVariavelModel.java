package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstacaoVariavelModel {
	
	EstacaoVariavelModel(){
		
	}

	public Boolean Verificar(int idVariavel, int idEstacao) {
		
		String sql ="Select * from \"EstacoeTemVariaveis\" where\"idVariaveis\" = '"+idVariavel+"' and \"idEstacoes\" = '"+idEstacao+"' ";
		PreparedStatement ps = null;
		Boolean valor = false;
		try {
			ps = Conexao.conectando().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				valor = true ;
			}
			
			
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
		return valor;
	}
	
	public void cadastrarVariavelInEstacao(int idVariavel, int idEstacao) {
		String sql ="Insert into \"EstacoeTemVariaveis\" (\"idVariaveis\",\"idEstacoes\") values ("
				+ " '"+idVariavel+"'"
				+ ",'"+idEstacao+" ')";
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
