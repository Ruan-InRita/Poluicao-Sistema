package com.inpe.br.Entities;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class TabelaManager {
	List<Integer> numColAmostraAmarela = new ArrayList<>();
	List<Integer> espacoVazio = new ArrayList<>();

	Coluna frequenciaColuna;
	List<Coluna> listaFrequencia = new ArrayList<Coluna>();

	Coluna coluna;
	List<Coluna> listaColuna = new ArrayList<Coluna>();

	Coluna colunaVariavel;
	List<Coluna> listaColunaVariavel = new ArrayList<Coluna>();

	Variavel variavel;
	List<Variavel> listaVariavel = new ArrayList<Variavel>();

	Estacao estacao;
	List<Estacao> listaEstacao = new ArrayList<Estacao>();

	Medicao medicao;
	Medicao m;
	List<Medicao> listaMedicao = new ArrayList<>();
	List<Medicao> valorm = new ArrayList<>();
	
	

	List<String> dataMedicao = new ArrayList<String>();
	



	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	int tamanhoTabela = 0;
	String data = null;

	public List<Medicao> criar(int skipRowInicial, int skipRowFinal, String localArquivo) {
		
		try {

			FileInputStream fis = new FileInputStream(localArquivo);
			Workbook workbook = new HSSFWorkbook(fis);

			Sheet sheet = workbook.getSheetAt(0);
			List<Row> rows = (List<Row>) toList(sheet.iterator());

			rows.forEach(row -> {
				// Para pular linhas no excel pega row.getRownum que informar valor da linha, ai
				// tu cria um if para condiçao desejada

				if ((row.getRowNum() >= skipRowInicial) && (row.getRowNum() <= skipRowFinal)) {
					// CELLS é coluna
					List<Cell> cells = (List<Cell>) toList(row.cellIterator());

					tamanhoTabela = cells.size();
					// System.out.println(tamanhoTabela);
					if (row.getRowNum() == skipRowInicial)/* - LINHA 1 - PEGAR AS ESTACAO */ {
						int cont = 0;

						for (int numeroDaColuna = 0; numeroDaColuna < tamanhoTabela; numeroDaColuna++) {
							if (numeroDaColuna > 0) {

								String nomeEstacao = cells.get(numeroDaColuna).getStringCellValue();
								// pega um id das colunas de cada estacao

								if (nomeEstacao != "") {
									cont++;
									coluna = new Coluna(numeroDaColuna, cont, nomeEstacao, null, null, 0, 0, 0, 0, 0);
									
									listaColuna.add(coluna);

								}

							}
						}

					}
					

					/*
					 * ########################### LINHA 2 - PEGAR AS VARIAVEL
					 * ###############################
					 */

					if (row.getRowNum() == (skipRowInicial + 3)) {
						coluna.quantidadeColuna(listaColuna, tamanhoTabela);
						int cont = 0;
						int cont2 = 1;

						int contColuna = 1;
						for (int numeroDaColuna = 0; numeroDaColuna < tamanhoTabela; numeroDaColuna++) {
							if (numeroDaColuna > 0) {

								String nomeVariavel = cells.get(numeroDaColuna).getStringCellValue();
								if (cont2 < listaColuna.get(cont).getQuantidade()) {

									if (nomeVariavel != "") {

										//System.out.println("Estacao: " + listaColuna.get(cont).getNomeEstacao()
										//		+ ", Var: " + nomeVariavel + "--- CONTCOLUNA: " + contColuna);
										colunaVariavel = new Coluna(0, 0, null, nomeVariavel, null, 0, 0, contColuna, 0,
												0);
										listaColunaVariavel.add(colunaVariavel);
									}
									// ################### ADD NA LISTA AQUI

									listaVariavel.add(new Variavel(nomeVariavel, null, null));

									// ##################

								} else {
									if (nomeVariavel != "") {

										//System.out.println("Estacao: " + listaColuna.get(cont).getNomeEstacao()
										//		+ ",---------############# Var: " + nomeVariavel + "--- CONTCOLUNA: "
										//		+ contColuna);
										colunaVariavel = new Coluna(0, 0, null, nomeVariavel, null, 0, 0, contColuna, 0,
												0);
										listaColunaVariavel.add(colunaVariavel);
									}
									// ################### ADD NA LISTA AQUI

									listaVariavel.add(new Variavel(nomeVariavel, null, null));

									// ##################
									cont++;
									cont2 = 0;

								}
								contColuna++;
								cont2++;
							}
						}
						colunaVariavel.quantidadeColunaVariavel(listaColunaVariavel, tamanhoTabela);

					}

					/*
					 * ########################### LINHA 3 - PEGAR AS FREQUENCIA
					 * ###############################
					 */

					if (row.getRowNum() == (skipRowInicial + 4)) {
						int cont = 0;
						int cont2 = 1;
						String nome = "OPA NAO ERA";

						for (int numeroDaColuna = 0; numeroDaColuna < tamanhoTabela; numeroDaColuna++) {
							if (numeroDaColuna > 0) {
								String nomeFrequencia = cells.get(numeroDaColuna).getStringCellValue();

								if (cont2 < listaColuna.get(cont).getQuantidade()) {
									if (nomeFrequencia != "") {

										nome = nomeFrequencia;
									}
									//System.out.println("Estacao: " + listaColuna.get(cont).getNomeEstacao()
									//		+ ", FRQUENCIA::::: " + nome + " COL: " + numeroDaColuna);
									// ################### ADD NA LISTA AQUI

									listaVariavel.get(numeroDaColuna - 1).setFrequencia(nome);

									frequenciaColuna = new Coluna(0, 0, null, null, null, 0, 0, 0, numeroDaColuna, 0);
									listaFrequencia.add(frequenciaColuna);

									// ##################
								} else {
									if (nomeFrequencia != "") {

										nome = nomeFrequencia;
									}
									//System.out.println("Estacao: " + listaColuna.get(cont).getNomeEstacao()
									//		+ ",---------############# FRQUENCIA:::: " + nome + " COL: "
									//		+ numeroDaColuna);

									// ################### ADD NA LISTA AQUI

									listaVariavel.get(numeroDaColuna - 1).setFrequencia(nome);
									frequenciaColuna = new Coluna(0, 0, null, null, nomeFrequencia, 0, 0, 0,
											numeroDaColuna, 0);
									listaFrequencia.add(frequenciaColuna);

									// ##################
									cont++;
									cont2 = 0;
									nome = nomeFrequencia;

								}

								cont2++;

							}
						}
					}

					// frequenciaColuna.quantidadeFrequencia(listaFrequencia, tamanhoTabela);
					/*
					 * ########################### LINHA 4 - IDENTIFICAR AS AMOSTRA AMARELA -
					 * POSICAO ###############################
					 */

					if (row.getRowNum() == (skipRowInicial + 5)) {
						for (int numeroDaColuna = 0; numeroDaColuna < tamanhoTabela; numeroDaColuna++) {
							if (numeroDaColuna > 0) {
								String leituraCampo = cells.get(numeroDaColuna).getStringCellValue();
								if (leituraCampo.equals("Amostra Paralela")) {
									//System.out.println(leituraCampo + " nº:" + numeroDaColuna);
									numColAmostraAmarela.add(numeroDaColuna);
								}

							}
						}
					}

					/*
					 * ########################### LINHA 5 - IDENTIFICAR espaço vazio
					 * ###############################
					 */

					if (row.getRowNum() == (skipRowInicial + 6)) {
						for (int numeroDaColuna = 0; numeroDaColuna < tamanhoTabela; numeroDaColuna++) {
							if (numeroDaColuna > 0) {
								String leituraCampo = cells.get(numeroDaColuna).getStringCellValue();
								if (leituraCampo == "") {
									espacoVazio.add(numeroDaColuna);
								}

							}
						}
					}

					/*
					 * ########################### LINHA 5 - PEGAR AS TIPO DE UNIDADE DE MEDIDA DE
					 * CADA VARIAVEL ###############################
					 */
					if (row.getRowNum() == (skipRowInicial + 6)) {
						
						int cont3 = 1;
						int cont3_aux = 0;
						

						for (int numeroDaColuna = 0; numeroDaColuna < tamanhoTabela; numeroDaColuna++) {
							if (numeroDaColuna > 0) {
								String UD = cells.get(numeroDaColuna).getStringCellValue();

								if (cont3 < listaColunaVariavel.get(cont3_aux).getTamanhoVariavel()) {
									// para pular amostra amarela
									int amostra = 0;
									for (Integer x : numColAmostraAmarela) {
										if (x == numeroDaColuna) {
											amostra = 1;
										}
									}
									if (amostra != 1) {
										// Eliminar os espaços em brancos e identificar sua posicao (n° da Coluna)
										if (UD != "") {
											/*System.out.println("Variavel: "
													+ listaColunaVariavel.get(cont3_aux).getNomeVariavel()
													+ "   Unidade: " + UD + " Col: " + cont3_aux + "		TAMANHO :"
													+ listaColunaVariavel.get(cont3_aux).getTamanhoVariavel());*/
											listaVariavel.get(numeroDaColuna - 1).setNomeVariavel(
													listaColunaVariavel.get(cont3_aux).getNomeVariavel());
											listaVariavel.get(numeroDaColuna - 1).setUnidadeMedida(UD);
											// listaVariavel.get(numeroDaColuna -
											// 1).setFrequencia(listaColunaVariavel.get(cont3_aux).getFrequencia());
										}

									}
								} else {
									int amostra = 0;
									for (Integer x : numColAmostraAmarela) {
										if (x == numeroDaColuna) {
											amostra = 1;
										}
									}
									if (amostra != 1) {
										if (UD != "") {
											/*System.out.println("Variavel: "
													+ listaColunaVariavel.get(cont3_aux).getNomeVariavel()
													+ "	Unidade: " + UD + " Col: " + cont3_aux + "		TAMANHO :"
													+ listaColunaVariavel.get(cont3_aux).getTamanhoVariavel() + "\n");*/
											listaVariavel.get(numeroDaColuna - 1).setNomeVariavel(
													listaColunaVariavel.get(cont3_aux).getNomeVariavel());
											listaVariavel.get(numeroDaColuna - 1).setUnidadeMedida(UD);
											// listaVariavel.get(numeroDaColuna -
											// 1).setFrequencia(listaColunaVariavel.get(cont3_aux).getFrequencia());
										} else {
										//	System.out.println("\n");
										}
									} else {
									//	System.out.println("\n");
									}
									cont3_aux++;
									cont3 = 0;
								}

								cont3++;

							}
						}
					}
					/*
					 * ########################### LINHA 6 - VALORES CADA VARIAVEL
					 * ###############################
					 */
					
					if (row.getRowNum() >= (skipRowInicial + 7)) {
						frequenciaColuna.quantidadeFrequencia(listaFrequencia, listaVariavel.size() + 1);					
						List<Double> listaValores = new ArrayList<>();
						for (int numeroDaColuna = 0; numeroDaColuna < tamanhoTabela; numeroDaColuna++) {
							
							// LER AS DATAS DA MEDICAO
							try {
								
								Date valordata = cells.get(0).getDateCellValue();
								data = sdf.format(valordata);
								if (numeroDaColuna > 0) {
									Double valor = cells.get(numeroDaColuna).getNumericCellValue();
									listaValores.add(valor);
								}
								if(numeroDaColuna == (tamanhoTabela-1)) {
									coluna.quantidadeColuna(listaColuna, listaVariavel.size() + 1);		
									m = new Medicao(null, null, null, listaValores);
									valorm.add(m);
									dataMedicao.add(data);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}

						}
					}
					
				}

			});
			
			
			/*
			 * CADASTRANDO DADOS DA LISTA VARIAVEL NAS ESTACOES
			 */
			int cont = 0;
			int cont2 = 1;
			// estacao = new Estacao(listaColuna.get(cont).getNomeEstacao(),
			// listaVariavel.get(numeroDaColuna));
			// listaEstacao.add(estacao);
			//System.out.println(
			//		"***************************************************************************************************************");
			
			List<Variavel> conjunto = new ArrayList<>();
			for (int numeroDaColuna = 0; numeroDaColuna < (listaVariavel.size()+1); numeroDaColuna++) {
				if (numeroDaColuna > 0) {

					if (cont2 < listaColuna.get(cont).getQuantidade()) {

						//System.out.println(listaColuna.get(cont).getNomeEstacao()+", VAR: "+listaVariavel.get(numeroDaColuna-1));
						conjunto.add(listaVariavel.get(numeroDaColuna-1));

					} else {

						

						//System.out.println(listaColuna.get(cont).getNomeEstacao()+", VAR: "+listaVariavel.get(numeroDaColuna-1)+"\n(-------------------------------------)");
						conjunto.add(listaVariavel.get(numeroDaColuna-1));
						estacao = new Estacao(listaColuna.get(cont).getNomeEstacao(),conjunto);
						listaEstacao.add(estacao);
						 conjunto = new ArrayList<>();
						cont++;
						cont2 = 0;
					}

					cont2++;

				}
			}
			/*-------------------------------------------------------------------------------------------------
			 * 							 CADASTRAR DADOS NA LISTA MEDICAO - FINAL
			 * -----------------------------------------------------------------------------------------------*/
			for(int c = 0 ; c < valorm.size(); c++) {
				String da = dataMedicao.get(c);
				medicao = new Medicao(da, listaEstacao, sdf.format(new Date()),valorm.get(c).getValorDeCadaLinhaMedicao());
				listaMedicao.add( medicao);			
			}
			
			System.out.println();
			System.out.println("Listas\nVariavel:"+listaVariavel.size()+", Estacao:"+listaEstacao.size()+", Medicao:"+listaMedicao.size());
			
			//System.out.println(
			//	listaMedicao.add( new Medicao(data, null,sdf.format(new Date()),listaValores));	"***************************************************************************************************************");*/

			workbook.close();

			
			
		

			return listaMedicao;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	public void mostrar(List<Medicao> lista) {
		lista.forEach(System.out::println);
	}

	public List<?> toList(Iterator<?> iterator) {
		return IteratorUtils.toList(iterator);
	}
}
