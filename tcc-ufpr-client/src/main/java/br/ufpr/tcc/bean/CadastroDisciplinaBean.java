package br.ufpr.tcc.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ufpr.model.Disciplina;
import br.ufpr.model.Professor;


@ViewScoped
@ManagedBean(name = "disciplinaBean")
public class CadastroDisciplinaBean {

	private String nomeProfessor;
	private Disciplina disciplina;
	private ResourceBundle rb;
	private List<Professor> lstProfessores;
	
	@PostConstruct
	public void init(){
		disciplina = new Disciplina();
		lstProfessores = new ArrayList<>();
		rb = ResourceBundle.getBundle("msg");
	}
	
	public void buscaProfessor(){
		
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public ResourceBundle getRb() {
		return rb;
	}

	public void setRb(ResourceBundle rb) {
		this.rb = rb;
	}

	public List<Professor> getLstProfessores() {
		return lstProfessores;
	}

	public void setLstProfessores(List<Professor> lstProfessores) {
		this.lstProfessores = lstProfessores;
	}

	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}
	
	
	
}
