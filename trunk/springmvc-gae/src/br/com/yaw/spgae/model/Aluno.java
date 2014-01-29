package br.com.yaw.spgae.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.yaw.spgae.dao.NotaDAOObjectify;

import com.googlecode.objectify.Key;
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
	
	public Key<Aluno> getKey() {
		return Key.create(Aluno.class, id);
	}
	
	public List<Nota> getProvasAluno(Key<UC> ucKey) {
		NotaDAOObjectify notaDAOObjectify = new NotaDAOObjectify();
		List<Nota> todasNotas = notaDAOObjectify.getAll();
		List<Nota> provasDoAluno = new ArrayList<Nota>();
		
		for (Nota nota : todasNotas) {
			if( (nota.getIdUC().equals(ucKey)) && (nota.getIdAluno().equals(getKey())) && nota.getNome().contains("Prova") )
			{
				provasDoAluno.add(nota);
			}
		}
		
		return provasDoAluno;
	}
	
	public List<Nota> getTrabalhosAluno(Key<UC> ucKey) {
		NotaDAOObjectify notaDAOObjectify = new NotaDAOObjectify();
		List<Nota> todasNotas = notaDAOObjectify.getAll();
		List<Nota> trabalhosDoAluno = new ArrayList<Nota>();
		
		for (Nota nota : todasNotas) {
			if((nota.getIdUC().equals(ucKey)) && (nota.getIdAluno().equals(getKey())) && nota.getNome().contains("Trabalho") )
			{
				trabalhosDoAluno.add(nota);
			}
		}
		
		return trabalhosDoAluno;
	}
	
	@Override
	public String toString()
	{
		return "Aluno: \n\tID: " + id + "\n\tNome: " + nome + "\n\tRA: " + ra;
	}
}
