package br.ufpr.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the pessoa database table.
 * 
 */
@Entity
@NamedQuery(name="Pessoa.findAll", query="SELECT p FROM Pessoa p")
public class Pessoa implements Serializable, DomainObject {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PESSOA_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="PESSOA_ID_GENERATOR")
	private Integer id;

	private String cidade;

	private String complemento;

	private String cpf;

	@Temporal(TemporalType.DATE)
	@Column(name="data_nascimento")
	private Date dataNascimento;

	private String email;

	private String endereco;

	private String estado;

	private String nome;

	private String numero;

	private String pais;

	private String sexo;

	private String telefone;

	@Temporal(TemporalType.DATE)
	@Column(name="updated_at")
	private Date updatedAt;

	//bi-directional many-to-one association to Aluno
	@OneToMany(mappedBy="pessoa")
	private List<Aluno> alunos;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="updated_by")
	private Usuario updatedBy;

	//bi-directional many-to-one association to Professor
	@OneToMany(mappedBy="pessoa")
	private List<Professor> professors;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="pessoa")
	private List<Usuario> usuarios;

	public Pessoa() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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
		aluno.setPessoa(this);

		return aluno;
	}

	public Aluno removeAluno(Aluno aluno) {
		getAlunos().remove(aluno);
		aluno.setPessoa(null);

		return aluno;
	}

	public Usuario getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Usuario updatedBy) {
		this.updatedBy = updatedBy;
	}

	public List<Professor> getProfessors() {
		return this.professors;
	}

	public void setProfessors(List<Professor> professors) {
		this.professors = professors;
	}

	public Professor addProfessor(Professor professor) {
		getProfessors().add(professor);
		professor.setPessoa(this);

		return professor;
	}

	public Professor removeProfessor(Professor professor) {
		getProfessors().remove(professor);
		professor.setPessoa(null);

		return professor;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setPessoa(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setPessoa(null);

		return usuario;
	}

}