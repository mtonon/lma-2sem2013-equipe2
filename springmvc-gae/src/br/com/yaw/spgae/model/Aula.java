package br.com.yaw.spgae.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.yaw.spgae.dao.AlunoDAOObjectify;
import br.com.yaw.spgae.dao.UCDAOObjectify;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Aula implements Serializable{
	
	@Id
	private Long id;
	
	private Key<UC> uc;
	
	private String data;

	private List<Key<Aluno>> idAlunosPresentes;
	
	public Aula() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Key<UC> getUc() {
		return uc;
	}

	public void setUc(Key<UC> uc) {
		this.uc = uc;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<Key<Aluno>> getIdAlunosPresentes() {
		return idAlunosPresentes;
	}

	public void setIdAlunosPresentes(List<Key<Aluno>> idAlunosPresentes) {
		this.idAlunosPresentes = idAlunosPresentes;
	}

	public List<Aluno> getAlunosDaAula()
	{
		AlunoDAOObjectify alunoObjetify = new AlunoDAOObjectify();
		UCDAOObjectify ucObjectify =  new UCDAOObjectify();
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		
		UC objetoUC = ucObjectify.findById( uc.getId() );
		
		List<Key<Aluno>> alunoDaUCKeys = objetoUC.getIdAlunos();
		
		for (Key<Aluno> key : alunoDaUCKeys) {
			alunos.add(alunoObjetify.findById(key.getId()));
		}
		
		return alunos;
	}
	
	public List<Aluno> getAlunosPresentes()
	{
		AlunoDAOObjectify alunoObjetify = new AlunoDAOObjectify();
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		
		for(int i = 0; i < idAlunosPresentes.size(); i++)
		{
			alunos.add(alunoObjetify.findById(idAlunosPresentes.get(i).getId()));
		}
		
		return alunos;
	}
	
	@Override
	public String toString()
	{
		String listaDeAlunosPresentes = "";
		Aluno alunoAux;
		
		for(int i = 0; i < idAlunosPresentes.size(); i++)
		{
			AlunoDAOObjectify alunoObjectify = new AlunoDAOObjectify();
			alunoAux = alunoObjectify.findById(idAlunosPresentes.get(i).getId());
			
			listaDeAlunosPresentes += "\n\tID do aluno="+alunoAux.getId()+ 
					"\n\tNome do aluno="+alunoAux.getNome() +
					"\n\tRA do aluno="+alunoAux.getRa();
		}
		
		return "Aula: Id=" + id + ", Data=" + data + "\nLista de alunos presentes:" + listaDeAlunosPresentes;
	}
}
