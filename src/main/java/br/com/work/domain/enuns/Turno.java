package br.com.work.domain.enuns;

public enum Turno {

	MATUTINO("matutino"), VESPERTINO("vespertino"), NOTURNO("noturno");

	private String turno;

	private Turno(String turno) {
		this.turno = turno;
	}

	public String getTurno() {
		return turno;
	}

}
