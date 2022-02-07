package br.com.work.domain.dtos;

import java.io.Serializable;

import br.com.work.domain.Estado;

public class EstadoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nomeEstado;

	public EstadoDto(Estado estado) {
		super();
		this.id = estado.getId();
		this.nomeEstado = estado.getNomeEstado();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeEstado() {
		return nomeEstado;
	}

	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
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
		EstadoDto other = (EstadoDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
