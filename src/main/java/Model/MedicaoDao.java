package Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicaoDao {

	MedicaoDao() {

	}

	public void cadastrarMedicaoBD(List<String> lista, int tamanhoLista) {
		String sql = "Insert into \"Medicoes\" (\"IdVariavel\",\"idEstacao\",\"DataHoraMedicao\",\"Valor\",\"DataHoraGravacao\")"
				+ "values ";
		String sqla = "";

		int cont = 0;
		int passo = 1000;
		
		sqla = sql;
		List<String> sqls = new ArrayList<String>();
		for (String s : lista) {
			if (cont < passo) {

				if (cont == tamanhoLista - 1) {
					sqla += s + " ; ";
					
					sqls.add(sqla);
				} else {
					sqla += s + " , ";
					
				}

			} else if (cont == passo) {
				sqla += s + " ; ";
				sqls.add(sqla);
				passo += 1000;
				sqla = sql;
			}
			cont++;
		}
		for (String codigo : sqls) {
			System.out.println(codigo);
			PreparedStatement ps = null;
			try {

				ps = Conexao.conectando().prepareStatement(codigo);
				ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				ps.getConnection().close();
				ps.close();
			} catch (SQLException e) { //
				e.printStackTrace();
			}
		}

	}

}
