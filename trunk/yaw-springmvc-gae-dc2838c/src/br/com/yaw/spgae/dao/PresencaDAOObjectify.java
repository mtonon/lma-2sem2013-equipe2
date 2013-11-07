package br.com.yaw.spgae.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.yaw.spgae.model.Presenca;

import com.googlecode.objectify.Key;

@Repository
public class PresencaDAOObjectify implements Serializable, PresencaDAO {

	@Override
	public Presenca save(Presenca presenca) {
		ofy().save().entity(presenca).now();
		return presenca;
	}
	
	@Override
	public List<Presenca> getAll() {
		return ofy().load().type(Presenca.class).list();
	}
	
	@Override
	public Boolean remove(Presenca presenca) {
		ofy().delete().entity(presenca).now();
		return true;
	}
	
	@Override
	public Presenca findById(Long id) {
		Key<Presenca> k = Key.create(Presenca.class, id);
		return ofy().load().key(k).get();
	}
}
