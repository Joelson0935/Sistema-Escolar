package br.com.work.domain.dtos;

import java.io.Serializable;

import br.com.work.domain.Aluno;
import br.com.work.domain.enuns.Genero;

public class AlunoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome_completo;
	private Integer idade;
	private Genero genero;

	public AlunoDto(Aluno aluno) {
		this.id = aluno.getId();
		this.nome_completo = aluno.getNome_completo();
		this.idade = aluno.getIdade();
		this.genero = aluno.getGenero();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome_completo == null) ? 0 : nome_completo.hashCode());
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
		AlunoDto other = (AlunoDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome_completo == null) {
			if (other.nome_completo != null)
				return false;
		} else if (!nome_completo.equals(other.nome_completo))
			return false;
		return true;
	}

}
