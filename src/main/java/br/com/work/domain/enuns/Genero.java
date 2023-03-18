package br.com.work.domain.enuns;

public enum Genero {

	MASCULINO("masculino"), FEMININO("feminino"), TRANSGENERO("transgenero");
	
	private String genero;

	private Genero(String genero) {
		this.genero = genero;
	}

	public String getGenero() {
		return genero;
	}
	
	
	
}
