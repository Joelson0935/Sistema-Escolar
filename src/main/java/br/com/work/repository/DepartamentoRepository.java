package br.com.work.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.work.domain.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

	@Query("select d from Departamento d where d.nome like %?1%")
	List<Departamento> buscarDepartamentoPorNome(String nome);

}
