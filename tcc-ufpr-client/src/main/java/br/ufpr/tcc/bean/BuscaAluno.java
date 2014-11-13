package br.ufpr.tcc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DualListModel;

import br.ufpr.model.Aluno;

@ViewScoped
@ManagedBean(name = "buscaAlunoBean")
public class BuscaAluno implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer tipoPesquisa;
	private Aluno aluno;
	private List<Aluno> lstAlunos;
	private ResourceBundle rb;
	private boolean renderInfo;
	
	
	@PostConstruct
	public void init(){
		renderInfo = false;
		tipoPesquisa = 1;
		aluno = new Aluno();
		lstAlunos = new ArrayList<>();
		rb = ResourceBundle.getBundle("msg");
		//teste
		aluno.setNome("Rodrigo");
		aluno.setCpf("04943356966");
	}
	
	
	public String editaAluno(){
		FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("editAluno", aluno);  
		return "cadastroAluno.xhtml?faces-redirect=true";
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





	public Aluno getAluno() {
		return aluno;
	}





	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}





	public List<Aluno> getLstAlunos() {
		return lstAlunos;
	}





	public void setLstAlunos(List<Aluno> lstAlunos) {
		this.lstAlunos = lstAlunos;
	}





	public boolean isRenderInfo() {
		return renderInfo;
	}





	public void setRenderInfo(boolean renderInfo) {
		this.renderInfo = renderInfo;
	}

	
	
}
