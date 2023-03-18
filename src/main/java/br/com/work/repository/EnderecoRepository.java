package br.com.work.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.work.domain.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, String> {

}
