package br.ufpr.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AlunoDisciplina extends BusinessModel {

	private static final long serialVersionUID = 1L;
	
	private Aluno aluno;
	private Disciplina disciplina;
	private BigDecimal nota;
	private Date dataCurso;
	private Integer faltas;
	
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
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

}
