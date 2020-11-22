package com.inpe.br.Entities;

import java.util.List;

public class Coluna {

	private int numeroColuna;
	private int idEstacao;
	private String nomeEstacao;
	private String nomeVariavel;
	private String frequencia;
	private int quantidade;
	private int TamanhoVariavel;
	private int numeroColunaVariavel;
	private int numeroColunaFrequencia;
	private int tamanhoFrequencia;
	
	public Coluna(int numeroColuna, int idEstacao,String nomeEstacao, String nomeVariavel, String Frequencia, int quantidade, int tamanhoVariavel, int numeroColunaVariavel, int numeroColunaFrequencia, int tamanhoFrequencia ) {
		super();
		this.numeroColuna = numeroColuna;
		this.idEstacao = idEstacao;
		this.nomeEstacao = nomeEstacao;
		this.nomeVariavel = nomeVariavel;
		this.frequencia = Frequencia;
		this.quantidade = quantidade;
		this.TamanhoVariavel = tamanhoVariavel;
		this.numeroColunaVariavel = numeroColunaVariavel;
		this.numeroColunaFrequencia =numeroColunaFrequencia;
		this.tamanhoFrequencia=tamanhoFrequencia;
	}
	
	public int getNumeroColuna() {
		return numeroColuna;
	}
	public void setNumeroColuna(int numeroColuna) {
		this.numeroColuna = numeroColuna;
	}
	public int getIdEstacao() {
		return idEstacao;
	}
	public void setIdEstacao(int idEstacao) {
		this.idEstacao = idEstacao;
	}
	
	public String getNomeEstacao() {
		return nomeEstacao;
	}
	public void setNomeEstacao(String nomeEstacao) {
		this.nomeEstacao = nomeEstacao;
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
	
	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	
	public int getTamanhoVariavel() {
		return TamanhoVariavel;
	}

	public void setTamanhoVariavel(int tamanhoVariavel) {
		TamanhoVariavel = tamanhoVariavel;
	}

	public int getNumeroColunaVariavel() {
		return numeroColunaVariavel;
	}

	public void setNumeroColunaVariavel(int numeroColunaVariavel) {
		this.numeroColunaVariavel = numeroColunaVariavel;
	}

	
	
	public int getNumeroColunaFrequencia() {
		return numeroColunaFrequencia;
	}

	public void setNumeroColunaFrequencia(int numeroColunaFrequencia) {
		this.numeroColunaFrequencia = numeroColunaFrequencia;
	}

	public int getTamanhoFrequencia() {
		return tamanhoFrequencia;
	}

	public void setTamanhoFrequencia(int tamanhoFrequencia) {
		this.tamanhoFrequencia = tamanhoFrequencia;
	}

	

	@Override
	public String toString() {
		return "Coluna [numeroColuna=" + numeroColuna + ", idEstacao=" + idEstacao + ", nomeEstacao=" + nomeEstacao
				+ ", nomeVariavel=" + nomeVariavel + ", frequencia=" + frequencia + ", quantidade=" + quantidade
				+ ", TamanhoVariavel=" + TamanhoVariavel + ", numeroColunaVariavel=" + numeroColunaVariavel
				+ ", numeroColunaFrequencia=" + numeroColunaFrequencia + ", tamanhoFrequencia=" + tamanhoFrequencia
				+ "]";
	}

	public void quantidadeColuna(List<Coluna> lista, int tamnhoTabela) {
		
		for(int x = 0; x < lista.size(); x++) {
			if(x == (lista.size() - 1)) {
				lista.get(x).setQuantidade(tamnhoTabela-lista.get(x).getNumeroColuna()); 
				//System.out.println(tamnhoTabela+" - "+lista.get(x).getNumeroColuna() );
			}else {
				
				lista.get(x).setQuantidade(lista.get(x+1).getNumeroColuna() - lista.get(x).getNumeroColuna()); 
			}
			
		}
		
	}
	
	public void quantidadeColunaVariavel(List<Coluna> lista, int tamnhoTabela) {
		for(int x = 0; x < lista.size(); x++) {
			if(x == (lista.size() - 1)) {
				lista.get(x).setTamanhoVariavel((tamnhoTabela - lista.get(x).getNumeroColunaVariavel())); 
			}else {
				
				lista.get(x).setTamanhoVariavel((lista.get(x+1).getNumeroColunaVariavel() - lista.get(x).getNumeroColunaVariavel())); 
			}
			
		}
		
	}
	
	public void quantidadeFrequencia(List<Coluna> lista, int tamnhoTabela) {
		for(int x = 0; x < lista.size(); x++) {
			if(x == (lista.size() - 1)) {
				lista.get(x).setTamanhoFrequencia((tamnhoTabela - lista.get(x).getNumeroColunaFrequencia())); 
			}else {
				
				lista.get(x).setTamanhoFrequencia((lista.get(x+1).getNumeroColunaFrequencia() - lista.get(x).getNumeroColunaFrequencia())); 
			}
			
		}
		
	}
	
	public int quantTotal(List<Coluna> lista) {
		int total =0;
		for(Coluna x : lista) {
			total += x.getQuantidade();
		}
		return total;
	}
	
	
	
	
	
}
