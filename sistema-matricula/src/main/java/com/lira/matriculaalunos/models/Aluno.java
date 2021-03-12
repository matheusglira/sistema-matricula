package com.lira.matriculaalunos.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;

import com.sun.istack.NotNull;

@Entity
public class Aluno implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@GeneratedValue
	@javax.persistence.Id
	private int id;
	
	@NotNull
	@Size(min=3)
	@Column(name = "nome")
	private String nome;
	
	@NotNull
	@Size(min=3)
	@Column(name = "sobrenome")
	private String sobrenome;
	
	@NotNull
	@Column(name = "matricula", unique = true)
	private int matricula;

	public Aluno() {}

	public Aluno(int id, String nome, String sobrenome, int matricula) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.matricula = matricula;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
}
