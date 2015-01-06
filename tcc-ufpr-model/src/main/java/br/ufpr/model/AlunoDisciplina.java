package br.ufpr.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AlunoDisciplina extends BusinessModel {

	private static final long serialVersionUID = 1L;
	
	private Integer aluno;
	private Integer disciplina;
	private BigDecimal nota;
	private Date dataCurso;
	private Integer faltas;
	
	public AlunoDisciplina(Integer aluno, Integer disciplina){
		this.aluno = aluno;
		this.disciplina = disciplina;
	}
	
	public AlunoDisciplina(){
		super();
	}
	
	public BigDecimal getNota() {
		return nota;
	}
	public void setNota(BigDecimal nota) {
		this.nota = nota;
	}
	public Date getDataCurso() {
		return dataCurso;
	}
	public void setDataCurso(Date dataCurso) {
		this.dataCurso = dataCurso;
	}
	public Integer getFaltas() {
		return faltas;
	}
	public void setFaltas(Integer faltas) {
		this.faltas = faltas;
	}

	public Integer getAluno() {
		return aluno;
	}

	public void setAluno(Integer aluno) {
		this.aluno = aluno;
	}

	public Integer getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Integer disciplina) {
		this.disciplina = disciplina;
	}
	

}
