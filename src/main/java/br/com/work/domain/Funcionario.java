package br.com.work.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.work.domain.enuns.Genero;
import br.com.work.domain.enuns.Profissao;

@Entity
public class Funcionario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(min = 15, max = 60, message = "Insira o nome Completo")
	private String nome_completo;
	@NotNull(message = "Idade inexistente")
	@Max(value = 110, message = "Numeração de Idade não aceita.")
	@Min(value = 1, message = "Numeração de Idade não aceita.")
	private Integer idade;
	@Enumerated(EnumType.STRING)
	private Genero genero;
	@Pattern(regexp = "[0-9]{3}[.][0-9]{3}[.][0-9]{3}[-][0-9]{2}", message = "Insira o seu cpf corretamente")
	private String cpf;
	@Pattern(regexp = "[0-9]{2}[.][0-9]{3}[.][0-9]{3}[-][0-9]{2}", message = "Insira o seu rg corretamente.")
	private String rg;
	@NotNull(message = "Por favor insira o nome da cidade.")
	@OneToOne
	private Cidade cidade;
	@NotNull(message = "Por favor insira o nome do estado.")
	@OneToOne
	private Estado estado;
	private String logradouro;
	@Enumerated(EnumType.STRING)
	private Profissao profissao;
	@NotNull(message = "Por favor insira o departamento.")
	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne
	private Departamento departamento;

	public Funcionario() {
		super();
	}

	public Funcionario(Long id, String nome_completo, Integer idade, Genero genero, String cpf, String rg,
			Cidade cidade, Estado estado, String logradouro, Profissao profissao, Departamento departamento) {
		super();
		this.id = id;
		this.nome_completo = nome_completo;
		this.idade = idade;
		this.genero = genero;
		this.cpf = cpf;
		this.rg = rg;
		this.cidade = cidade;
		this.estado = estado;
		this.logradouro = logradouro;
		this.profissao = profissao;
		this.departamento = departamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome_completo() {
		return nome_completo;
	}

	public void setNome_completo(String nome_completo) {
		this.nome_completo = nome_completo;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Profissao getProfissao() {
		return profissao;
	}

	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
