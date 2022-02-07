package br.com.work.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;

@Entity
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String logradouro;
	private String bairro;
	private String complemento;
	@OneToOne
	private Cidade localidade;
	@Pattern(regexp = "[0-9]{5}[-][0-9]{3}", message = "cep não encontrado")
	private String cep;

	public Endereco() {
		super();
	}

	public Endereco(Long id, String logradouro, String bairro, String complemento, Cidade localidade, String cep) {
		super();
		this.id = id;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.complemento = complemento;
		this.localidade = localidade;
		this.cep = cep;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Cidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Cidade localidade) {
		this.localidade = localidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bairro, cep, complemento, id, localidade, logradouro);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(bairro, other.bairro) && Objects.equals(cep, other.cep)
				&& Objects.equals(complemento, other.complemento) && Objects.equals(id, other.id)
				&& Objects.equals(localidade, other.localidade) && Objects.equals(logradouro, other.logradouro);
	}

}
