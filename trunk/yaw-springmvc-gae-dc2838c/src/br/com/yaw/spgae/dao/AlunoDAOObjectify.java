package br.com.yaw.spgae.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.Serializable;
import java.util.List;

import br.com.yaw.spgae.model.Aluno;
import br.com.yaw.spgae.model.Mercadoria;

import com.googlecode.objectify.Key;

public class AlunoDAOObjectify implements Serializable, AlunoDAO {

	@Override
	public Aluno save(Aluno aluno) {
		ofy().save().entity(aluno).now();
		return aluno;
	}
	
	@Override
	public List<Aluno> getAll() {
		return ofy().load().type(Aluno.class).list();
	}
	
	@Override
	public Boolean remove(Aluno aluno) {
		ofy().delete().entity(aluno).now();
		return true;
	}
	
	@Override
	public Aluno findById(Long id) {
		Key<Aluno> k = Key.create(Aluno.class, id);
		return ofy().load().key(k).get();
	}
}
