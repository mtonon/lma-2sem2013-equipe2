package br.com.yaw.spgae.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Aluno implements Serializable{

	@Id
	private Long id;
	
	@NotNull @Size(min=5, max=200)
	private String nome;
	
	@NotNull
	private int ra;
	
	public Aluno() {}
	
	public Aluno(String nome, int ra) {
		this.nome = nome;
		this.ra = ra;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public int getRa() {
		return ra;
	}

	public void setRa(int ra) {
		this.ra = ra;
	}
}
