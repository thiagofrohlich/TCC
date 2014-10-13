package br.ufpr.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the aluno database table.
 * 
 */
@Entity
@NamedQuery(name="Aluno.findAll", query="SELECT a FROM Aluno a")
public class Aluno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ALUNO_MATRICULA_GENERATOR", sequenceName="ALUNO-MATRICULA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ALUNO_MATRICULA_GENERATOR")
	private Integer matricula;

	private Boolean ativo;

	@Temporal(TemporalType.DATE)
	@Column(name="data_matricula")
	private Date dataMatricula;

	@Temporal(TemporalType.DATE)
	@Column(name="updated_at")
	private Date updatedAt;

	//bi-directional many-to-one association to Pessoa
	@ManyToOne
	private Pessoa pessoa;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="update_by")
	private Usuario updatedBy;

	//bi-directional many-to-one association to AlunoDisciplina
	@OneToMany(mappedBy="aluno")
	private List<AlunoDisciplina> alunoDisciplinas;

	public Aluno() {
	}

	public Integer getMatricula() {
		return this.matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Date getDataMatricula() {
		return this.dataMatricula;
	}

	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Usuario getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Usuario updatedBy) {
		this.updatedBy = updatedBy;
	}

	public List<AlunoDisciplina> getAlunoDisciplinas() {
		return this.alunoDisciplinas;
	}

	public void setAlunoDisciplinas(List<AlunoDisciplina> alunoDisciplinas) {
		this.alunoDisciplinas = alunoDisciplinas;
	}

	public AlunoDisciplina addAlunoDisciplina(AlunoDisciplina alunoDisciplina) {
		getAlunoDisciplinas().add(alunoDisciplina);
		alunoDisciplina.setAluno(this);

		return alunoDisciplina;
	}

	public AlunoDisciplina removeAlunoDisciplina(AlunoDisciplina alunoDisciplina) {
		getAlunoDisciplinas().remove(alunoDisciplina);
		alunoDisciplina.setAluno(null);

		return alunoDisciplina;
	}

}