package br.com.work.domain.enuns;

public enum Turma {

	A(1), B(2), C(3), D(4);

	private int valor;

	private Turma(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

}
