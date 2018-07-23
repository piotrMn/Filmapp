package pl.coderslab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import pl.coderslab.entity.Film;

@Component
@Transactional
public class FilmDao {

	@PersistenceContext
	EntityManager entityManager;

	public void save(Film entity) {
		entityManager.persist(entity);
	}

	public void update(Film entity) {
		entityManager.merge(entity);
	}

	public Film findFilmByTitle(String title) {
		Film theFilm = null;
		Query query = entityManager.createQuery("SELECT f FROM Film f WHERE f.title=:title");
		query.setParameter("title", title);
		try {
			theFilm = (Film) query.getSingleResult();
		} catch (NoResultException e) {
			theFilm = null;
		}
		return theFilm;
	}

	public Film findFilmById(long id) {
		Film theFilm = null;
		Query query = entityManager.createQuery("SELECT f FROM Film f WHERE f.id=:id");
		query.setParameter("id", id);
		try {
			theFilm = (Film) query.getSingleResult();
		} catch (NoResultException e) {
			theFilm = null;
		}
		return theFilm;
	}

	public List<Film> findAllFilms() {
		Query query = entityManager.createQuery("SELECT f FROM Film f");
		return query.getResultList();
	}

	public List<String> getAllTitles() {
		Query query = entityManager.createQuery("SELECT f.title FROM Film f");
		return query.getResultList();
	}

}
