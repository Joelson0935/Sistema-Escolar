package br.com.work.domain.enuns;

public enum Profissao {
	PROFESSOR("professor"), DIRETOR("diretor"), COZINHEIRO("cozinheiro"), SECRETARIO("secretario"),
	SEGURANCA("seguranca"), SERVICOS_GERAIS("servicos gerais");

	private String profissao;

	private Profissao(String profissao) {
		this.profissao = profissao;
	}

	public String getProfissao() {
		return profissao;
	}

	public static Profissao getProfissao(String profissao) {
		for(Profissao p : Profissao.values()) {
			if(p.getProfissao() == profissao) {
				return p;
			}
		}
		 throw new IllegalArgumentException("Profissão informada não existe.");
	}
	
}
