package br.ufpr.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Disciplina extends BusinessModel {

	private String nome;
	private Integer periodo;
	private Turno turno;
	private Professor professor;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
}
