package br.com.yaw.spgae.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.yaw.spgae.dao.AlunoDAOObjectify;
import br.com.yaw.spgae.dao.AulaDAOObjectify;
import br.com.yaw.spgae.dao.UCDAOObjectify;
import br.com.yaw.spgae.model.Aluno;
import br.com.yaw.spgae.model.Aula;
import br.com.yaw.spgae.model.Nota;
import br.com.yaw.spgae.model.UC;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

public class ConfigStartup implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ObjectifyService.register(Aluno.class);
		ObjectifyService.register(Aula.class);
		ObjectifyService.register(UC.class);
		ObjectifyService.register(Nota.class);
		
//		inserirAlunosParaTeste();
//		inserirUCsParaTeste();
//		inserirAulasParaTeste();
	}
	
	private void inserirAlunosParaTeste()
	{
		AlunoDAOObjectify persistidorAluno = new AlunoDAOObjectify();
		Aluno[] alunos = new Aluno[7];

		for (int i = 0; i < alunos.length; i++) {
			alunos[i] = new Aluno();
			alunos[i].setRa( Integer.parseInt("" + (i+1) + (i+1) + (i+1) + (i+1) + (i+1) + (i+1)) );
		}
		
		alunos[0].setNome("Luis");
		alunos[1].setNome("Daniel");
		alunos[2].setNome("Rafael");
		alunos[3].setNome("Marcela");
		alunos[4].setNome("Yachi");
		alunos[5].setNome("Patricia");
		alunos[6].setNome("Renata");
		
		for (Aluno aluno : alunos) {
			persistidorAluno.save(aluno);
		}	
	}
	
	private void inserirUCsParaTeste()
	{
		/* Testes */
		UCDAOObjectify persistidorDeUCs = new UCDAOObjectify();
		AlunoDAOObjectify persistidorAluno = new AlunoDAOObjectify();

		UC novaUC01 = new UC();
		novaUC01.setAno(2013);
		novaUC01.setNome("novaUC01");
		novaUC01.setSemestre(2);

		List<Key<Aluno>> alunosAux = new ArrayList<Key<Aluno>>();
		List<Aluno> todosAlunos = persistidorAluno.getAll();

		for(int i = 0; i < todosAlunos.size(); i++)
		{
			alunosAux.add(todosAlunos.get(i).getKey());
		}
		
		novaUC01.setIdAlunos(alunosAux);
		
		persistidorDeUCs.save(novaUC01);		
	}

	private void inserirAulasParaTeste()
	{
		
		AulaDAOObjectify persistidorAula = new AulaDAOObjectify();
		UCDAOObjectify ucObjectify = new UCDAOObjectify();
		AlunoDAOObjectify alunoObjectify = new AlunoDAOObjectify();

		Aula aula01 = new Aula();
		Aula aula02 = new Aula();
		Aula aula03 = new Aula();
		
		aula01.setData("21/11/2013");
		aula02.setData("25/11/2013");
		aula03.setData("27/11/2013");
		
		aula01.setUc(ucObjectify.getAll().get(0).getKey());
		aula02.setUc(ucObjectify.getAll().get(0).getKey());
		aula03.setUc(ucObjectify.getAll().get(0).getKey());
		
		List<Key<Aluno>> alunosPresentes = new ArrayList<Key<Aluno>>();
		List<Aluno> alunosAux = alunoObjectify.getAll();
		
		alunosPresentes.add(alunosAux.get(0).getKey());
		alunosPresentes.add(alunosAux.get(3).getKey());
		alunosPresentes.add(alunosAux.get(4).getKey());
		alunosPresentes.add(alunosAux.get(6).getKey());
		
		aula01.setIdAlunosPresentes(alunosPresentes);
		aula02.setIdAlunosPresentes(alunosPresentes);
		aula03.setIdAlunosPresentes(alunosPresentes);

		persistidorAula.save(aula01);
		persistidorAula.save(aula02);
		persistidorAula.save(aula03);
	}


	@Override
	public void contextDestroyed(ServletContextEvent arg0) {}

}
