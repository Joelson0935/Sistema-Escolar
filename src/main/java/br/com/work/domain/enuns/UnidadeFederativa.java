package br.com.work.domain.enuns;

public enum UnidadeFederativa {

	AM("AM"), AL("AL"), AC("AC"), AP("AP"), BA("BA"), PA("PA"), MT("MT"), MG("MG"), MS("MS"), GO("GO"), MA("MA"),
	RS("RS"), TO("TO"), PI("PI"), SP("SP"), RO("RO"), RR("RR"), PR("PR"), CE("CE"), PE("PE"), SC("SC"), PB("PB"),
	RN("RN"), ES("ES"), RJ("RJ"), SE("SE"), DF("DF");

	private final String nome;

	private UnidadeFederativa(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
