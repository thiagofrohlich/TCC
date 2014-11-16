package br.ufpr.tcc.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.ufpr.model.Disciplina;
import br.ufpr.model.Professor;
import br.ufpr.model.Usuario;
import br.ufpr.service.handler.impl.DisciplinaServiceHandlerImpl;
import br.ufpr.service.handler.impl.ProfessorServiceHandlerImpl;


@ViewScoped
@ManagedBean(name = "disciplinaBean")
public class CadastroDisciplinaBean {

	private String nomeProfessor;
	private Disciplina disciplina;
	private ResourceBundle rb;
	private List<Professor> lstProfessores;
	private Professor professor;
	private ProfessorServiceHandlerImpl professorService;
	private DisciplinaServiceHandlerImpl disciplinaService;
	private boolean updateDisciplina;
	
	@PostConstruct
	public void init(){
		disciplina = (Disciplina) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("editDisciplina");
		if(disciplina == null){
			professor = new Professor();
			disciplina = new Disciplina();
			updateDisciplina = false;
		}else{
			setProfessor(disciplina.getProfessor());
			updateDisciplina = true;
		}
		professorService = new ProfessorServiceHandlerImpl();
		disciplinaService = new DisciplinaServiceHandlerImpl();
		lstProfessores = new ArrayList<>();
		rb = ResourceBundle.getBundle("msg");
	}
	
	public void buscaProfessor(){
		lstProfessores = professorService.getLista(nomeProfessor);
	}
	
	public void salvaDisciplina(){
		if(verificaDisciplina()){
			if(updateDisciplina){
				disciplinaService.update(disciplina);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Disciplina salva com sucesso"));
			}else{
				disciplinaService.create(disciplina);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Disciplina salva com sucesso"));
			}
		}
	}
	
	
	public boolean verificaDisciplina(){
		boolean ret = true;
		if(disciplina.getNome() == null || disciplina.equals("")){
			ret = false;
		}
		if(disciplina.getPeriodo() == null || disciplina.getPeriodo() == 0){
			ret = false;
		}
		if(disciplina.getTurno() == null){
			ret = false;
		}
		if(professor == null || professor.getId() == null){
			ret = false;
		}
		return ret;
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

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	
	
}
