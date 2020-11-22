package com.inpe.br.Entities;

import java.util.List;

public class Estacao {

	private String nomeEstacao;
	private List<Variavel> listaVariavel;
	
	
	public Estacao(String nomeEstacao, List<Variavel> listaVariavel) {
		super();
		this.nomeEstacao = nomeEstacao;
		this.listaVariavel = listaVariavel;
		
	}
	
	public String getNomeEstacao() {
		return nomeEstacao;
	}
	public void setNomeEstacao(String nomeEstacao) {
		this.nomeEstacao = nomeEstacao;
	}
	public List<Variavel> getListaVariavel() {
		return listaVariavel;
	}
	public void setListaVariavel(List<Variavel> listaVariavel) {
		this.listaVariavel = listaVariavel;
	}

	@Override
	public String toString() {
		return "Estacao [nomeEstacao=" + nomeEstacao + ", listaVariavel=" + listaVariavel + "]";
	}
	
	
}

