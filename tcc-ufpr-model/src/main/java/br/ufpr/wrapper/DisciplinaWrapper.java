package br.ufpr.wrapper;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.domain.Page;

import br.ufpr.model.Disciplina;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
public class DisciplinaWrapper extends Wrapper<Disciplina> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DisciplinaWrapper(Page<? extends Object> page) {
		super(page);
	}

	public DisciplinaWrapper(){
		super();
	}
}
