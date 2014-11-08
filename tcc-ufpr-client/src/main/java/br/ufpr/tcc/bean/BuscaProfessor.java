package br.ufpr.tcc.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.ufpr.model.Professor;


@ViewScoped
@ManagedBean(name = "buscaProfessorBean")
public class BuscaProfessor {

	private static final long serialVersionUID = 1L;
	
	private Integer tipoPesquisa;
	private Professor professor;
	private List<Professor> lstProfessores;
	private ResourceBundle rb;
	
	private boolean renderInfo;
	
	@PostConstruct
	public void init(){
		renderInfo = false;
		tipoPesquisa = 1;
		professor = new Professor();
		lstProfessores = new ArrayList<>();
		rb = ResourceBundle.getBundle("msg");
		//teste
		professor.setNome("Rodrigo");
		professor.setCpf("04943356966");
	}
	
	
	public String editaProfessor(){
		FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("editProfessor", professor);  
		return "cadastroProfessor.xhtml?faces-redirect=true";
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


	public List<Professor> getLstProfessores() {
		return lstProfessores;
	}


	public void setLstProfessores(List<Professor> lstProfessores) {
		this.lstProfessores = lstProfessores;
	}


	public boolean isRenderInfo() {
		return renderInfo;
	}

	
	
}
