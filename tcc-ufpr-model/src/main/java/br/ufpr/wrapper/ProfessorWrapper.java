package br.ufpr.wrapper;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.springframework.data.domain.Page;

import br.ufpr.model.Professor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@XmlRootElement
@XmlSeeAlso(Professor.class)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ProfessorWrapper extends Wrapper<Professor> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProfessorWrapper(Page<? extends Object> page) {
		super(page);
	}

	public ProfessorWrapper(){
		super();
	}

	public ProfessorWrapper(List<Professor> returnList) {
		super(returnList);
	}
}
