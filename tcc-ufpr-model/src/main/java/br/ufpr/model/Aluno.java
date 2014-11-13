package br.ufpr.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Aluno extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer matricula;
	private Boolean ativo;
	private Date dataMatricula;
	private List<Disciplina> disciplinas;
	private Integer periodo;
	
	public Aluno(){
		super();
	}
	
	public Integer getMatricula() {
		return matricula;
	}
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public Date getDataMatricula() {
		return dataMatricula;
	}
	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	@Override
	public Integer getId() {
		return getMatricula();
	}
	@Override
	public void setId(Integer id) {
		setMatricula(id);
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}
	
}
