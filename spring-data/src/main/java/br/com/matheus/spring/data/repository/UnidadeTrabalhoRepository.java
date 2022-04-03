package br.com.matheus.spring.data.repository;

import br.com.matheus.spring.data.orm.UnidadeTrabalho;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeTrabalhoRepository extends PagingAndSortingRepository<UnidadeTrabalho, Integer> {
}