package br.com.work.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.work.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	/**
	 * Busca uma lista de cidades por nome ou parte do nome
	 * 
	 * @param nome
	 * @return retorna os nomes de cidades correspondentes
	 */
	@Query("select c from Cidade c where c.nomeCidade like %?1%")
	List<Cidade> buscarCidadePorNome(String nome);

	/**
	 * busca uma cidade dentro da tabela cidade
	 * 
	 * @param name
	 * @return retorna a cidade correspondente a busca
	 */
	@Query("select c from Cidade c where c.nomeCidade = ?1")
	Cidade buscarCidade(String name);
}
