package br.com.yaw.spgae.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.yaw.spgae.model.Aula;

import com.googlecode.objectify.Key;

@Repository
public class AulaDAOObjectify implements Serializable, AulaDAO {

	@Override
	public Aula save(Aula aula) {
		ofy().save().entity(aula).now();
		return aula;
	}

	@Override
	public List<Aula> getAll() {
		return ofy().load().type(Aula.class).list();
	}

	@Override
	public Boolean remove(Aula aula) {
		ofy().delete().entity(aula).now();
		return true;
	}

	@Override
	public Aula findById(Long id) {
		Key<Aula> k = Key.create(Aula.class, id);
		return ofy().load().key(k).get();
	}
}
