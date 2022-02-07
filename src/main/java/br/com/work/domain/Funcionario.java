package br.com.work.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
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
	@Column(unique = true)
	@Pattern(regexp = "[0-9]{3}[.][0-9]{3}[.][0-9]{3}[-][0-9]{2}", message = "Insira o seu cpf corretamente")
	private String cpf;
	@Column(unique = true)
	@Pattern(regexp = "[0-9]{2}[.][0-9]{3}[.][0-9]{3}[-][0-9]{2}", message = "Insira o seu rg corretamente.")
	private String rg;
	@OneToOne
	private Endereco endereco;
	@Enumerated(EnumType.STRING)
	private Profissao profissao;
	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne
	private Departamento departamento;

	public Funcionario() {
		super();
	}

	public Funcionario(Long id, String nome_completo, Integer idade, Genero genero, String cpf, String rg,
			Endereco endereco, Profissao profissao, Departamento departamento) {
		super();
		this.id = id;
		this.nome_completo = nome_completo;
		this.idade = idade;
		this.genero = genero;
		this.cpf = cpf;
		this.rg = rg;
		this.endereco = endereco;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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
		return Objects.hash(cpf, id, rg);
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
		return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id) && Objects.equals(rg, other.rg);
	}

}
