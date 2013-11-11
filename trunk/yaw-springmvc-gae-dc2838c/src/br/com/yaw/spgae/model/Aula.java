package br.com.yaw.spgae.model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.yaw.spgae.dao.AlunoDAOObjectify;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Aula implements Serializable{
	
	@Id
	private Long id;
	
	private String data;
	
	List<Key<Aluno>> idAlunos;
	List<Key<Aluno>> idAlunosPresentes;
	
	public Aula() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<Key<Aluno>> getIdTodosAlunos() {
		return idAlunos;
	}

	public void setIdTodosAlunos(List<Key<Aluno>> idAlunos) {
		this.idAlunos = idAlunos;
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
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		
		for(int i = 0; i < idAlunos.size(); i++)
		{
			alunos.add(alunoObjetify.findById(idAlunos.get(i).getId()));
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
		String listaDeTodosAlunos = "";
		String listaDeAlunosPresentes = "";
		Aluno alunoAux;
		
		for(int i = 0; i < idAlunos.size(); i++)
		{
			AlunoDAOObjectify alunoObjectify = new AlunoDAOObjectify();
			alunoAux = alunoObjectify.findById(idAlunos.get(i).getId());
			
			listaDeTodosAlunos += "\n\tID do aluno="+alunoAux.getId()+ 
					"\n\tNome do aluno="+alunoAux.getNome() +
					"\n\tRA do aluno="+alunoAux.getRa();
		}
		
		for(int i = 0; i < idAlunosPresentes.size(); i++)
		{
			AlunoDAOObjectify alunoObjectify = new AlunoDAOObjectify();
			alunoAux = alunoObjectify.findById(idAlunosPresentes.get(i).getId());
			
			listaDeAlunosPresentes += "\n\tID do aluno="+alunoAux.getId()+ 
					"\n\tNome do aluno="+alunoAux.getNome() +
					"\n\tRA do aluno="+alunoAux.getRa();
		}
		
		return "Aula: Id=" + id + ", Data=" + data + "\nLista de alunos:" + listaDeTodosAlunos +
				"\nLista de alunos presentes:" + listaDeAlunosPresentes;
	}
}
