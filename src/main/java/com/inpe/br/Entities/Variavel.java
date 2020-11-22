package com.inpe.br.Entities;

import java.util.List;

public class Variavel {
	
	private String nomeVariavel;
	private String frequencia;
	private String unidadeMedida;
	
	
	
	

	public Variavel(String nomeVariavel, String frequencia, String unidadeMedida) {
		super();
		this.nomeVariavel = nomeVariavel;
		this.frequencia = frequencia;
		this.unidadeMedida = unidadeMedida;
		
		
	}

	public String getNomeVariavel() {
		return nomeVariavel;
	}

	public void setNomeVariavel(String nomeVariavel) {
		this.nomeVariavel = nomeVariavel;
	}

	public String getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(String frequencia) {
		this.frequencia = frequencia;
	}

	

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}
	

	
	
	
	

	
	
	@Override
	public String toString() {
		return "Variavel [nomeVariavel=" + nomeVariavel + ", frequencia=" + frequencia + ", unidadeMedida="
				+ unidadeMedida + "]";
	}

	public void ajustaFrequencia(List<Variavel> lista,List<Coluna>listFrequencia) {
		int cont = 0;
		int cont2 = 1;
		for(int x = 0; x < lista.size();x++) {
			if(cont2 < listFrequencia.get(cont).getTamanhoFrequencia()) {
				lista.get(x).setFrequencia(listFrequencia.get(cont).getFrequencia());
			}else {
				cont2 = 0;
				cont++;
				lista.get(x).setFrequencia(listFrequencia.get(cont).getFrequencia());
			}
			cont2++; 
		}
	}
	
	

}
