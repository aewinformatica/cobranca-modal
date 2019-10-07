package com.aewinformatica.cobranca.model;

public enum StatusTitulo {

	PENDENTE("Pendente"),
	RECEBIDO("Recebido");
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	StatusTitulo (String descricao) {
		this.descricao = descricao;
	}
}
