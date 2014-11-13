package br.ufpr.services;

import br.ufpr.domain.Pessoa;
import br.ufpr.exception.NoResultFoundException;

public interface PessoaService extends CrudService<Pessoa, Integer> {

	Pessoa findPessoaByCpf(String cpf) throws NoResultFoundException;
	
	Pessoa findPessoaByNome(String nome) throws NoResultFoundException;
	
}
