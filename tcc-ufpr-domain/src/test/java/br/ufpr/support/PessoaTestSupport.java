package br.ufpr.support;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpr.domain.Pessoa;
import br.ufpr.repository.PessoaRepository;

public abstract class PessoaTestSupport extends SpringTestSupport {

	@Autowired
	private PessoaRepository pessoaRepository;
	protected Pessoa savedPessoa;
	
	@Before
	public void createValidPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("fake name");
		pessoa.setCpf("123456789-00");
		savedPessoa = pessoaRepository.saveAndFlush(pessoa);
	}
	
}
