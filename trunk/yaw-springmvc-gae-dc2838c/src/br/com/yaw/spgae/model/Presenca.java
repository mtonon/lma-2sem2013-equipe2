package br.com.yaw.spgae.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;


@Entity
public class Presenca implements Serializable{

	@Id
	private Long id;
	
	@NotNull
	private String data;
	
//	private int idAluno;
	
	@NotNull
	private boolean presente;
	
	public Presenca() {}
	
	public Presenca(String data, int idAluno, boolean presente) {
		this.data = data;
//		this.idAluno = idAluno;
		this.presente = presente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDia() {
		return data;
	}

	public void setDia(String data) {
		this.data = data;
	}

//	public int getIdAluno() {
//		return idAluno;
//	}
//
//	public void setIdAluno(int idAluno) {
//		this.idAluno = idAluno;
//	}

	public boolean isPresente() {
		return presente;
	}

	public void setPresente(boolean presente) {
		this.presente = presente;
	}
}
