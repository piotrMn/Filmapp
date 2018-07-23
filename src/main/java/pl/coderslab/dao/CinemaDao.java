package pl.coderslab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import pl.coderslab.entity.Cinema;

@Component
@Transactional
public class CinemaDao {

	@PersistenceContext
	EntityManager entityManager;

	public void save(Cinema entity) {
		entityManager.persist(entity);
	}

	public void update(Cinema entity) {
		entityManager.merge(entity);
	}

	public List<Cinema> findAllCinemas() {
		Query query = entityManager.createQuery("SELECT c FROM Cinema c");
		return query.getResultList();
	}

	public Cinema findCinemaByName(String cinemaName) {
		Cinema theCinema;
		Query query = entityManager.createQuery("SELECT c FROM Cinema c WHERE c.name=:cinemaName");
		query.setParameter("cinemaName", cinemaName);
		try {
			theCinema = (Cinema) query.getSingleResult();
		} catch (NoResultException exc) {
			theCinema = null;
		}
		return theCinema;
	}

	public List<Cinema> findCinemasByTown(String town) {
		Query query = entityManager.createQuery("SELECT c FROM Cinema c WHERE c.town=:town");
		query.setParameter("town", town);
		return query.getResultList();
	}
}
