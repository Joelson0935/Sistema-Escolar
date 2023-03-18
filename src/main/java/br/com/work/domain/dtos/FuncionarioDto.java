package br.com.work.domain.dtos;

import java.io.Serializable;

import br.com.work.domain.Funcionario;

public class FuncionarioDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome_completo;

	public FuncionarioDto(Funcionario funcionario) {
		super();
		this.id = funcionario.getId();
		this.nome_completo = funcionario.getNome_completo();
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
		FuncionarioDto other = (FuncionarioDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
