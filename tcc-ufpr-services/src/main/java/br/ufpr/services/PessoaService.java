package br.ufpr.services;

import java.util.List;

import br.ufpr.domain.Pessoa;
import br.ufpr.exception.NoResultFoundException;

public interface PessoaService extends CrudService<Pessoa, Integer> {

	Pessoa findPessoaByCpf(String cpf) throws NoResultFoundException;
	
	List<Pessoa> findPessoaByNome(String nome) throws NoResultFoundException;
	
}
