package br.ufpr.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable, DomainObject {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USUARIO_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="USUARIO_ID_GENERATOR")
	private Integer id;

	private String acessos;

	private String login;

	private String senha;

	@Temporal(TemporalType.DATE)
	@Column(name="updated_at")
	private Date updatedAt;

	//bi-directional many-to-one association to Aluno
	@OneToMany(mappedBy="updatedBy")
	private List<Aluno> alunos;

	//bi-directional many-to-one association to AlunoDisciplina
	@OneToMany(mappedBy="updatedBy")
	private List<AlunoDisciplina> alunoDisciplinas;

	//bi-directional many-to-one association to Disciplina
	@OneToMany(mappedBy="updatedBy")
	private List<Disciplina> disciplinas;

	//bi-directional many-to-one association to Noticia
	@OneToMany(mappedBy="usuario")
	private List<Noticia> noticias;

	//bi-directional many-to-one association to Pessoa
	@OneToMany(mappedBy="updatedBy")
	private List<Pessoa> pessoas;

	//bi-directional many-to-one association to Professor
	@OneToMany(mappedBy="updatedBy")
	private List<Professor> professors;

	//bi-directional many-to-one association to Pessoa
	@ManyToOne
	private Pessoa pessoa;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="updated_by")
	private Usuario updatedBy;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="updatedBy")
	private List<Usuario> usuarios;

	public Usuario() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAcessos() {
		return this.acessos;
	}

	public void setAcessos(String acessos) {
		this.acessos = acessos == null ? null : acessos.trim();
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Aluno> getAlunos() {
		return this.alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Aluno addAluno(Aluno aluno) {
		getAlunos().add(aluno);
		aluno.setUpdatedBy(this);

		return aluno;
	}

	public Aluno removeAluno(Aluno aluno) {
		getAlunos().remove(aluno);
		aluno.setUpdatedBy(null);

		return aluno;
	}

	public List<AlunoDisciplina> getAlunoDisciplinas() {
		return this.alunoDisciplinas;
	}

	public void setAlunoDisciplinas(List<AlunoDisciplina> alunoDisciplinas) {
		this.alunoDisciplinas = alunoDisciplinas;
	}

	public AlunoDisciplina addAlunoDisciplina(AlunoDisciplina alunoDisciplina) {
		getAlunoDisciplinas().add(alunoDisciplina);
		alunoDisciplina.setUsuario(this);

		return alunoDisciplina;
	}

	public AlunoDisciplina removeAlunoDisciplina(AlunoDisciplina alunoDisciplina) {
		getAlunoDisciplinas().remove(alunoDisciplina);
		alunoDisciplina.setUsuario(null);

		return alunoDisciplina;
	}

	public List<Disciplina> getDisciplinas() {
		return this.disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public Disciplina addDisciplina(Disciplina disciplina) {
		getDisciplinas().add(disciplina);
		disciplina.setUpdatedBy(this);

		return disciplina;
	}

	public Disciplina removeDisciplina(Disciplina disciplina) {
		getDisciplinas().remove(disciplina);
		disciplina.setUpdatedBy(null);

		return disciplina;
	}

	public List<Noticia> getNoticias() {
		return this.noticias;
	}

	public void setNoticias(List<Noticia> noticias) {
		this.noticias = noticias;
	}

	public Noticia addNoticia(Noticia noticia) {
		getNoticias().add(noticia);
		noticia.setUsuario(this);

		return noticia;
	}

	public Noticia removeNoticia(Noticia noticia) {
		getNoticias().remove(noticia);
		noticia.setUsuario(null);

		return noticia;
	}

	public List<Pessoa> getPessoas() {
		return this.pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Pessoa addPessoa(Pessoa pessoa) {
		getPessoas().add(pessoa);
		pessoa.setUpdatedBy(this);

		return pessoa;
	}

	public Pessoa removePessoa(Pessoa pessoa) {
		getPessoas().remove(pessoa);
		pessoa.setUpdatedBy(null);

		return pessoa;
	}

	public List<Professor> getProfessors() {
		return this.professors;
	}

	public void setProfessors(List<Professor> professors) {
		this.professors = professors;
	}

	public Professor addProfessor(Professor professor) {
		getProfessors().add(professor);
		professor.setUpdatedBy(this);

		return professor;
	}

	public Professor removeProfessor(Professor professor) {
		getProfessors().remove(professor);
		professor.setUpdatedBy(null);

		return professor;
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

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setUpdatedBy(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setUpdatedBy(null);

		return usuario;
	}

	@Override
	public boolean isDeleted() {
		return getAcessos() == null || getAcessos().trim().isEmpty();
	}

	@Override
	public void delete() {
		setAcessos(null);
	}

}