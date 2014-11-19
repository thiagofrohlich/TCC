package br.ufpr.tcc.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.ufpr.model.Professor;
import br.ufpr.model.Usuario;
import br.ufpr.service.handler.impl.ProfessorServiceHandlerImpl;
import br.ufpr.wrapper.ProfessorWrapper;


@ViewScoped
@ManagedBean(name = "buscaProfessorBean")
public class BuscaProfessor {

	private static final long serialVersionUID = 1L;
	
	private Integer tipoPesquisa;
	private Professor professor;
	private ProfessorWrapper lstProfessores;
	private ResourceBundle rb;
	private ProfessorServiceHandlerImpl professorService;
	private String nomeProfessor;
	private Professor professorSelecionado;
	
	private boolean renderInfo;
	
	@PostConstruct
	public void init(){
		renderInfo = false;
		tipoPesquisa = 1;
		professor = new Professor();
		professorSelecionado = new Professor();
		lstProfessores = new ProfessorWrapper();
		professorService = new ProfessorServiceHandlerImpl();
		rb = ResourceBundle.getBundle("msg");
		
		
	}
	
	
	public String editaProfessor(){
		FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("editProfessor", professor);  
		return "cadastroProfessor.xhtml?faces-redirect=true";
	}
	
	public void buscaProfessorPorNome(){
		lstProfessores = professorService.findByNome(nomeProfessor);
		renderInfo = true;
	}
	
	
	public void selecionaProfessor(){
		professor = professorSelecionado;
		professorSelecionado = new Professor();
		
	}
	
	

	public Integer getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(Integer tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setRenderInfo(boolean renderInfo) {
		this.renderInfo = renderInfo;
	}


	public Professor getProfessor() {
		return professor;
	}


	public void setProfessor(Professor professor) {
		this.professor = professor;
	}


	


	public boolean isRenderInfo() {
		return renderInfo;
	}


	public String getNomeProfessor() {
		return nomeProfessor;
	}


	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}


	public Professor getProfessorSelecionado() {
		return professorSelecionado;
	}


	public void setProfessorSelecionado(Professor professorSelecionado) {
		this.professorSelecionado = professorSelecionado;
	}


	public ProfessorWrapper getLstProfessores() {
		return lstProfessores;
	}


	public void setLstProfessores(ProfessorWrapper lstProfessores) {
		this.lstProfessores = lstProfessores;
	}

	
	
}
