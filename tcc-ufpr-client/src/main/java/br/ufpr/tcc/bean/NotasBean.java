package br.ufpr.tcc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ufpr.model.Aluno;
import br.ufpr.model.Disciplina;


@ViewScoped
@ManagedBean(name = "notasBean")
public class NotasBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Disciplina> lstDisciplinas;
	private Disciplina disciplina;
	private List<Aluno> lstAlunos;
	private ResourceBundle rb;
	
	@PostConstruct
	public void init(){
		lstDisciplinas = new ArrayList<>();
		disciplina = new Disciplina();
		lstAlunos = new ArrayList<>();
		rb = ResourceBundle.getBundle("msg");
	}

	public List<Disciplina> getLstDisciplinas() {
		return lstDisciplinas;
	}

	public void setLstDisciplinas(List<Disciplina> lstDisciplinas) {
		this.lstDisciplinas = lstDisciplinas;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<Aluno> getLstAlunos() {
		return lstAlunos;
	}

	public void setLstAlunos(List<Aluno> lstAlunos) {
		this.lstAlunos = lstAlunos;
	}

	
	
}
