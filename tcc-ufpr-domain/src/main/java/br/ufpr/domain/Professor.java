package br.ufpr.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the professor database table.
 * 
 */
@Entity
@NamedQuery(name="Professor.findAll", query="SELECT p FROM Professor p")
public class Professor implements Serializable, DomainObject {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PROFESSOR_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="PROFESSOR_ID_GENERATOR")
	private Integer id;

	private Boolean ativo;

	@Temporal(TemporalType.DATE)
	@Column(name="updated_at")
	private Date updatedAt;

	

	//bi-directional many-to-one association to Pessoa
	@ManyToOne
	private Pessoa pessoa;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="updated_by")
	private Usuario updatedBy;

	public Professor() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean isAtivo() {
		return this.ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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

	@Override
	public boolean isDeleted() {
		return !isAtivo();
	}
	
	@Override
	public void delete() {
		setAtivo(false);
	}

}