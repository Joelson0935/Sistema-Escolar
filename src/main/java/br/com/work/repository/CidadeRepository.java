package br.com.work.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.work.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	@Query("select c from Cidade c where c.nome like %?1%")
	List<Cidade> buscarCidadePorNome(String nome);
}
