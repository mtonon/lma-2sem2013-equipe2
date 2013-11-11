package br.com.yaw.spgae.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.yaw.spgae.dao.AlunoDAOObjectify;
import br.com.yaw.spgae.dao.AulaDAOObjectify;
import br.com.yaw.spgae.model.Aluno;
import br.com.yaw.spgae.model.Aula;
import br.com.yaw.spgae.model.Mercadoria;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

/**
 * Componente necessário para registrar no Objectify quais são as entidades que ele deve gerenciar.
 * 
 * <p>Código executado durante a inicialização do aplicativo web.</p> 
 * 
 * @author YaW Tecnologia
 */
public class ConfigStartup implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ObjectifyService.register(Mercadoria.class);
		ObjectifyService.register(Aluno.class);
		ObjectifyService.register(Aula.class);
		
		/* Testes */
		AlunoDAOObjectify persistidorAluno = new AlunoDAOObjectify();
		AulaDAOObjectify persistidorAula = new AulaDAOObjectify();
//
//		Aluno aluno01 = new Aluno();
//		aluno01.setNome("Aluno 01");
//		aluno01.setRa(12345);
//		aluno01.setId((long)1); 
//		Aluno aluno02 = new Aluno();
//		aluno02.setNome("Aluno 02");
//		aluno02.setRa(12346);
//		aluno02.setId((long)2);
//		

		/* Inserir aula */
		
//		Aula aula01 = new Aula();
//		aula01.setData("11/11/2013");
//		List<Key<Aluno>> alunosAux = new ArrayList<Key<Aluno>>();
//		List<Aluno> todosAlunos = persistidorAluno.getAll();
//		List<Aluno> alunosPresentes = new ArrayList<Aluno>();
//		
//		for(int i = 0; i < todosAlunos.size(); i++)
//		{
//			alunosAux.add(todosAlunos.get(i).getKey());
//		}
//		aula01.setIdTodosAlunos(alunosAux);
//		
//		alunosAux = new ArrayList<Key<Aluno>>();
//		alunosAux.add(todosAlunos.get(1).getKey());
//		alunosAux.add(todosAlunos.get(3).getKey());
//		aula01.setIdAlunosPresentes(alunosAux);
//
//		System.out.println(persistidorAula.save(aula01));

		/* Buscar e mostrar aula */
		
		List<Aula> aulas = persistidorAula.getAll();
		for(int i = 0; i < aulas.size(); i++)
		{
			System.out.println(aulas.get(i));
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {}
	
}
