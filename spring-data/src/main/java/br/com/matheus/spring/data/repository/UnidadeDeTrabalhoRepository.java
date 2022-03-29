package br.com.matheus.spring.data.repository;

import br.com.matheus.spring.data.orm.UnidadeDeTrabalho;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeDeTrabalhoRepository extends CrudRepository<UnidadeDeTrabalho, Integer> {
}