package com.inpe.br.Entities;

import java.util.List;

public class Medicao {
	
	
	private String dataMedicao;
	private List<Estacao> listaEstacao;
	private String dataHoraGravacao;
	private List<Double> valorDeCadaLinhaMedicao;
	public Medicao(String dataMedicao, List<Estacao> listaEstacao, String dataHoraGravacao, List<Double> valorDeCadaLinhaMedicao) {
		super();
		this.dataMedicao = dataMedicao;
		this.listaEstacao = listaEstacao;
		this.dataHoraGravacao = dataHoraGravacao;
		this.valorDeCadaLinhaMedicao = valorDeCadaLinhaMedicao;
	}
	public String getDataMedicao() {
		return dataMedicao;
	}
	public void setDataMedicao(String dataMedicao) {
		this.dataMedicao = dataMedicao;
	}
	public List<Estacao> getListaEstacao() {
		return listaEstacao;
	}
	public void setListaEstacao(List<Estacao> listaEstacao) {
		this.listaEstacao = listaEstacao;
	}
	public String getDataHoraGravacao() {
		return dataHoraGravacao;
	}
	public void setDataHoraGravacao(String dataHoraGravacao) {
		this.dataHoraGravacao = dataHoraGravacao;
	}
	public List<Double> getValorDeCadaLinhaMedicao() {
		return valorDeCadaLinhaMedicao;
	}
	public void setValorDeCadaLinhaMedicao(List<Double> valorDeCadaLinhaMedicao) {
		this.valorDeCadaLinhaMedicao = valorDeCadaLinhaMedicao;
	}
	@Override
	public String toString() {
		return "Medicao [dataMedicao=" + dataMedicao + ", listaEstacao=" + listaEstacao + ", dataHoraGravacao="
				+ dataHoraGravacao + ", valorDeCadaLinhaMedicao=" + valorDeCadaLinhaMedicao + "]";
	}
	
	
	
	
	
	
}
