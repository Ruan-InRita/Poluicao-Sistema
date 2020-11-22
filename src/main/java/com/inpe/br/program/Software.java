/**
 * 
 */
package com.inpe.br.program;

import java.io.IOException;

import Model.CadastrarMedicao;

/**
 * @author Ruan Rita
 *
 */
public class Software {

	/**
	 * @param without args
	 * @throws IOException 
	 */
	
	public static void main(String[] args) throws IOException {		
		String localArquivo = "C:\\Users\\ruan_\\Desktop\\Poluicao\\POLUICAO\\INEA\\2000\\DadosBrutos_MetropolitanaRio_2000.xls";
		
		/*------------------------------		LOCALARQIVO 2 FEITA COM SUCESSO		---------------------------------------------------------------------------------*/
		String localArquivo2 = "C:\\Users\\ruan_\\Desktop\\Poluicao\\POLUICAO\\INEA\\2013\\\\ATMOS_DadosDeMedicoes_MP_2013_PT4.xls";
		CadastrarMedicao cm = new CadastrarMedicao();
		cm.mostrarResultado( 238 , 8876  ,localArquivo2);//6656//842
	

	}

}
