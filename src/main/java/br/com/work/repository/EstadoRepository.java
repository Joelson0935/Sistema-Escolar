package br.com.work.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.work.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

	@Query("select e from Estado e where e.nome like %?1%")
	List<Estado> buscarEstadoPorNome(String nome);
}
