package br.com.work.domain.dtos;

import java.io.Serializable;
import java.util.Objects;

import br.com.work.domain.Cidade;
import br.com.work.domain.enuns.UnidadeFederativa;

public class CidadeDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private UnidadeFederativa uf;

	public CidadeDto(Cidade cidade) {
		this.id = cidade.getId();
		this.nome = cidade.getNomeCidade();
		this.uf = cidade.getUf();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public UnidadeFederativa getUf() {
		return uf;
	}

	public void setUf(UnidadeFederativa uf) {
		this.uf = uf;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome, uf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CidadeDto other = (CidadeDto) obj;
		return Objects.equals(id, other.id) && Objects.equals(nome, other.nome) && uf == other.uf;
	}

}
