package br.ufpr.wrapper;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.domain.Page;

import br.ufpr.model.Aluno;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
public class AlunoWrapper extends Wrapper<Aluno> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlunoWrapper(Page<? extends Object> page) {
		super(page);
	}

	public AlunoWrapper(){
		super();
	}
	public AlunoWrapper(List<Aluno> list) {
		super(list);
	}
}
