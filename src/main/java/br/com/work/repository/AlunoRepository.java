package br.com.work.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.work.domain.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{

	@Query("select a from Aluno a where a.nome_completo like %?1%")
	List<Aluno> buscarAlunoPorNome(String nome);
}
