package br.com.work.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.work.domain.enuns.UnidadeFederativa;

@Entity
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "Insira o nome da cidade")
	@NotBlank(message = "Insira o nome da cidade")
	@Size(min = 3, message = "Insira o nome correto.")
	private String nomeCidade;
	@Enumerated(EnumType.STRING)
	private UnidadeFederativa uf;
	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne
	private Estado estado;

	public Cidade() {
		super();
	}

	public Cidade(Long id, String nomeCidade, UnidadeFederativa uf, Estado estado) {
		super();
		this.id = id;
		this.nomeCidade = nomeCidade;
		this.uf =uf;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public UnidadeFederativa getUf() {
		return uf;
	}

	public void setUf(UnidadeFederativa uf) {
		this.uf = uf;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nomeCidade);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		return Objects.equals(id, other.id) && Objects.equals(nomeCidade, other.nomeCidade);
	}

}
