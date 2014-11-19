package br.ufpr.wrapper;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.springframework.data.domain.Page;

import br.ufpr.model.Pessoa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@XmlRootElement
@XmlSeeAlso(Pessoa.class)
@JsonIgnoreProperties(ignoreUnknown=true)
public class PessoaWrapper extends Wrapper<Pessoa> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PessoaWrapper(Page<? extends Object> page) {
		super(page);
	}

	public PessoaWrapper(){
		super();
	}
}
