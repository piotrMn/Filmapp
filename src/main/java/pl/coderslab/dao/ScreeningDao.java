package pl.coderslab.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import pl.coderslab.entity.Screening;

@Component
@Transactional
public class ScreeningDao {

	@PersistenceContext
	EntityManager entityManager;

	public void save(Screening entity) {
		entityManager.persist(entity);
	}

	public void update(Screening entity) {
		entityManager.merge(entity);
	}

	public Screening findScreeningById(long screeningId) {
		Screening thisScreening = null;
		Query query = entityManager.createQuery("SELECT s FROM Screening s WHERE s.id=:screeningId");
		query.setParameter("screeningId", screeningId);
		try {
			thisScreening = (Screening) query.getSingleResult();
		} catch (NoResultException e) {
			thisScreening = null;
		}
		return thisScreening;
	}

	public List<Screening> findScreeningByFilmTitleAndCinemaNameAndTimestamps(String filmTitle, String cinemaName,
			Timestamp from, Timestamp to) {
		Query query = entityManager.createQuery(
				"SELECT s FROM Screening s JOIN s.cinema c JOIN s.film f WHERE f.title=:filmTitle AND c.name=:cinemaName AND s.timestamp>:from AND s.timestamp<:to");
		query.setParameter("filmTitle", filmTitle);
		query.setParameter("cinemaName", cinemaName);
		query.setParameter("from", from, TemporalType.TIMESTAMP);
		query.setParameter("to", to, TemporalType.TIMESTAMP);
		return query.getResultList();
	}

	public List<Screening> findScreeningByCinemaNameAndTimestamps(String cinemaName, Timestamp from, Timestamp to) {
		Query query = entityManager.createQuery(
				"SELECT s FROM Screening s JOIN s.cinema c JOIN s.film f WHERE c.name=:cinemaName AND s.timestamp>:from AND s.timestamp<:to");
		query.setParameter("cinemaName", cinemaName);
		query.setParameter("from", from, TemporalType.TIMESTAMP);
		query.setParameter("to", to, TemporalType.TIMESTAMP);
		return query.getResultList();
	}

	public List<Screening> findScreeningByFilmTitleAndTimestamps(String filmTitle, Timestamp from, Timestamp to) {
		Query query = entityManager.createQuery(
				"SELECT s FROM Screening s JOIN s.cinema c JOIN s.film f WHERE f.title=:filmTitle AND s.timestamp>:from AND s.timestamp<:to");
		query.setParameter("filmTitle", filmTitle);
		query.setParameter("from", from, TemporalType.TIMESTAMP);
		query.setParameter("to", to, TemporalType.TIMESTAMP);
		return query.getResultList();
	}

	public List<Screening> findUpcomingScreenings() {
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		Query query = entityManager.createQuery("SELECT s FROM Screening s JOIN s.cinema c WHERE s.timestamp>:now");
		query.setParameter("now", now, TemporalType.TIMESTAMP);
		query.setMaxResults(200);
		return query.getResultList();
	}

	public List<Screening> findScreeningsByTimestamps(Timestamp from, Timestamp to) {
		Query query = entityManager
				.createQuery("SELECT s FROM Screening s JOIN s.cinema c WHERE s.timestamp>:from AND s.timestamp<:to");
		query.setParameter("from", from, TemporalType.TIMESTAMP);
		query.setParameter("to", to, TemporalType.TIMESTAMP);
		return query.getResultList();
	}

	public List<Screening> findUpcomingScreeningsByFilmTitleAndCinemaName(String filmTitle, String cinemaName) {
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		Query query = entityManager.createQuery(
				"SELECT s FROM Screening s JOIN s.cinema c JOIN s.film f WHERE f.title=:filmTitle AND c.name=:cinemaName AND s.timestamp>:now");
		query.setParameter("filmTitle", filmTitle);
		query.setParameter("cinemaName", cinemaName);
		query.setParameter("now", now);
		return query.getResultList();
	}

	public List<Screening> findUpcomingScreeningsByFilmTitle(String filmTitle) {
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		Query query = entityManager.createQuery(
				"SELECT s FROM Screening s JOIN s.cinema c JOIN s.film f WHERE f.title=:filmTitle AND s.timestamp>:now");
		query.setParameter("filmTitle", filmTitle);
		query.setParameter("now", now);
		return query.getResultList();
	}

	public List<Screening> findUpcomingScreeningsByCinemaName(String cinemaName) {
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		Query query = entityManager.createQuery(
				"SELECT s FROM Screening s JOIN s.cinema c JOIN s.film f WHERE c.name=:cinemaName AND s.timestamp>:now");
		query.setParameter("cinemaName", cinemaName);
		query.setParameter("now", now);
		return query.getResultList();
	}

}
