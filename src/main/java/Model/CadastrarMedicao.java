package Model;

import java.util.ArrayList;
import java.util.List;

import com.inpe.br.Entities.Medicao;
import com.inpe.br.Entities.TabelaManager;

public class CadastrarMedicao {

	public void mostrarResultado(int SkipInicial, int SkipFinal, String localArquivo) {
		System.out.println("(------------------------------  Cadastrar Medicao  --------------------------------)");
		TabelaManager chequemanager = new TabelaManager();
		List<Medicao> listaMedicao = chequemanager.criar(SkipInicial, SkipFinal, localArquivo);

		// 6658
		/*
		 * _________ __________ _________ _______________ _____ ________________
		 * |idMedicao|IdVariavel|idEstacao|DataHoraMedicao|Valor|DataHoraGravacao| | | |
		 * | | | | | | | | | | |
		 * (_________)__________)_________)_______________)_____)________________)
		 */
		VariavelModel vm = new VariavelModel();
		List<VariavelModel> listaVariavelModel = vm.BuscaraALLVariavel();

		EstacaoModel em = new EstacaoModel();
		List<EstacaoModel> listaEstacaoModel = em.BuscarEstacaoALL();

		MedicaoDao md = new MedicaoDao();
		
		List<String> sqlAux = new  ArrayList<String>();

		EstacaoVariavelModel evm = new EstacaoVariavelModel();

		int contMedDao = 0;

		/*----------------------------------------------------------------------------------------------------
		 * 										FOR DA MEDICAO
		 *---------------------------------------------------------------------------------------------------*/

		for (int contadorM = 0; contadorM < listaMedicao.size(); contadorM++) {
			/*----------------------------------------------------------------------------------------------------
			 * 										FOR DA ESTACAO
			 *---------------------------------------------------------------------------------------------------*/
			int contador = 0;
		

			for (int contadorE = 0; contadorE < listaMedicao.get(contadorM).getListaEstacao().size(); contadorE++) {
				/*----------------------------------------------------------------------------------------------------
				 * 										FOR DE VARIAVEIS
				 *---------------------------------------------------------------------------------------------------*/
				for (int contadorV = 0; contadorV < listaMedicao.get(contadorM).getListaEstacao().get(contadorE)
						.getListaVariavel().size(); contadorV++) {

					if ((listaMedicao.get(contadorM).getListaEstacao().get(contadorE).getListaVariavel().get(contadorV)
							.getFrequencia() != null)
							&& (listaMedicao.get(contadorM).getListaEstacao().get(contadorE).getListaVariavel()
									.get(contadorV).getNomeVariavel() != null)
							&& (listaMedicao.get(contadorM).getListaEstacao().get(contadorE).getListaVariavel()
									.get(contadorV).getUnidadeMedida() != null)) {

						int idVariavel = 0;
						int idEstacao = 0;

						String conversaoSigla = null;

						switch (listaMedicao.get(contadorM).getListaEstacao().get(contadorE).getListaVariavel()
								.get(contadorV).getUnidadeMedida()) {
						case "grau":
							conversaoSigla = "°";
							break;
						case "grau Celsius":
							conversaoSigla = "°C";
							break;
						case "percentual":
							conversaoSigla = "%";
							break;
						case "metro por segundo":
							conversaoSigla = "m/s";
							break;
						case "micrograma por metro cúbico":
							conversaoSigla = "µg/m3";
							break;
						case "parte por bilhão":
							conversaoSigla = "ppb";
							break;
						case "parte por milhão":
							conversaoSigla = "ppm";
							break;
						case "milímetro":
							conversaoSigla = "mm";
							break;
						case "milibar":
							conversaoSigla = "mbar";
							break;
						case "watt por metro quadrado":
							conversaoSigla = "W/m2";
							break;
						case "hectopascal":
							conversaoSigla = "hPa";
							break;
						case "miligrama":
							conversaoSigla = "mg";
							break;
						case "metro cúbico por dia":
							conversaoSigla = "m³/dia";
							break;
						

						}
						

						String frequencia = null;

						if (listaMedicao.get(contadorM).getListaEstacao().get(contadorE).getListaVariavel()
								.get(contadorV).getFrequencia()
								.equalsIgnoreCase("Freqüência 6 Dias com amostra de 24 Horas a 0,0 m")) {
							frequencia = "6 dias com 24 amostra";
						} else {
							frequencia = "1";

						}

						// *********************************************************************
						while (idVariavel == 0) {
							for (VariavelModel var : listaVariavelModel) {
								if ((var.getUndadeMedida().equals(conversaoSigla))
										&& (var.getFrequencia().equals(frequencia))
										&& (var.getNomeVariavel().equals(listaMedicao.get(contadorM).getListaEstacao()
												.get(contadorE).getListaVariavel().get(contadorV).getNomeVariavel()))) {
									idVariavel = var.getIdVariavel();
								}
							}

							if (idVariavel == 0) {
								vm.cadastrarVariavel(conversaoSigla, listaMedicao.get(contadorM).getListaEstacao()
										.get(contadorE).getListaVariavel().get(contadorV).getNomeVariavel(),
										frequencia);
								listaVariavelModel = vm.BuscaraALLVariavel();
							}
						}

						// ********************************************************
						while (idEstacao == 0) {
							for (EstacaoModel lem : listaEstacaoModel) {
								if (lem.getNomeEstacao().equals(listaMedicao.get(contadorM).getListaEstacao()
										.get(contadorE).getNomeEstacao())) {
									idEstacao = lem.getIdEstacao();
								}
							}

							if (idEstacao == 0) {
								String[] tipo = listaMedicao.get(contadorM).getListaEstacao().get(contadorE)
										.getNomeEstacao().split("-");
								em.cadastrarEstacao(
										listaMedicao.get(contadorM).getListaEstacao().get(contadorE).getNomeEstacao(),
										tipo[0]);
								listaEstacaoModel = em.BuscarEstacaoALL();
							}
						}
						

						// *************************************************************************************
						if(contadorM == 0) {
							if(evm.Verificar(idVariavel, idEstacao )== true) {
								// nao cadastra
							}else {
								evm.cadastrarVariavelInEstacao(idVariavel, idEstacao);
						
							}
						}
						
						 
						//*************************************************************************************
						if(contador < listaMedicao.get(contadorM).getValorDeCadaLinhaMedicao().size()) {
							if (listaMedicao.get(contadorM).getValorDeCadaLinhaMedicao().get(contador) != 0
									|| listaMedicao.get(contadorM).getValorDeCadaLinhaMedicao().get(contador) != 0.0) {
								System.out.println(idVariavel + " - "+ idEstacao + " - "
										+ listaMedicao.get(contadorM).getDataMedicao() + " - "
										+ listaMedicao.get(contadorM).getValorDeCadaLinhaMedicao().get(contador) + " - "
										+ listaMedicao.get(contadorM).getDataHoraGravacao()+" - - - "+contadorM);
								sqlAux.add(" ('"+idVariavel+"', '"+idEstacao+"','"+listaMedicao.get(contadorM).getDataMedicao()+"', "
										+ "'"+listaMedicao.get(contadorM).getValorDeCadaLinhaMedicao().get(contador)+"', '"+listaMedicao.get(contadorM).getDataHoraGravacao()+"')");
								contMedDao++;
								
							}
						}
						
					}

					contador++;

				}
			}
		}
		
		
		md.cadastrarMedicaoBD(sqlAux,contMedDao);
	}

}
