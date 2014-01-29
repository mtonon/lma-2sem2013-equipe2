package br.com.yaw.spgae.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.yaw.spgae.model.Nota;

import com.googlecode.objectify.Key;

@Repository
public class NotaDAOObjectify implements Serializable, NotaDAO {

	@Override
	public Nota save(Nota nota) {
		ofy().save().entity(nota).now();
		return nota;
	}
	
	@Override
	public List<Nota> getAll() {
		return ofy().load().type(Nota.class).list();
	}
	
	@Override
	public Boolean remove(Nota nota) {
		ofy().delete().entity(nota).now();
		return true;
	}
	
	@Override
	public Nota findById(Long id) {
		Key<Nota> k = Key.create(Nota.class, id);
		return ofy().load().key(k).get();
	}
}
