package pl.coderslab.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import pl.coderslab.entity.Screening;
import pl.coderslab.entity.URSlink;
import pl.coderslab.entity.User;
import pl.coderslab.util.Converter;

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
	
	public Screening findById(long id) {
		Screening thisScreening = null;
		Query query = entityManager.createQuery("SELECT s FROM Screening s WHERE s.id=:id");
		query.setParameter("id", id);
		try {
			thisScreening = (Screening) query.getSingleResult();
		} catch (NoResultException e) {
			thisScreening = null;
		}
		return thisScreening;
	}
	public List<Screening> findByTitleCinemaDate(String title, String cinema, Timestamp from, Timestamp to){
		Query query = entityManager.createQuery(
				"SELECT s FROM Screening s JOIN s.cinema c JOIN s.film f WHERE f.title=:title AND c.name=:cinema AND s.timestamp>:from AND s.timestamp<:to");
		query.setParameter("title", title);
		query.setParameter("cinema", cinema);
		query.setParameter("from", from, TemporalType.TIMESTAMP);
		query.setParameter("to", to, TemporalType.TIMESTAMP);
		return query.getResultList();
	}
	public List<Screening> findByCinemaDate(String cinema, Timestamp from, Timestamp to){
		Query query = entityManager.createQuery(
				"SELECT s FROM Screening s JOIN s.cinema c JOIN s.film f WHERE c.name=:cinema AND s.timestamp>:from AND s.timestamp<:to");
		query.setParameter("cinema", cinema);
		query.setParameter("from", from, TemporalType.TIMESTAMP);
		query.setParameter("to", to, TemporalType.TIMESTAMP);
		return query.getResultList();
	}
	public List<Screening> findByTitleDate(String title, Timestamp from, Timestamp to){
		Query query = entityManager.createQuery(
				"SELECT s FROM Screening s JOIN s.cinema c JOIN s.film f WHERE f.title=:title AND s.timestamp>:from AND s.timestamp<:to");
		query.setParameter("title", title);
		query.setParameter("from", from, TemporalType.TIMESTAMP);
		query.setParameter("to", to, TemporalType.TIMESTAMP);
		return query.getResultList();
	}
	public List<Screening> findUpcoming(){
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		Query query = entityManager.createQuery("SELECT s FROM Screening s JOIN s.cinema c WHERE s.timestamp>:ts");
		query.setParameter("ts", now, TemporalType.TIMESTAMP);
		query.setMaxResults(200);
		return query.getResultList();
	}
	public List<Screening> findByDate(Timestamp from, Timestamp to){
		Query query = entityManager.createQuery("SELECT s FROM Screening s JOIN s.cinema c WHERE s.timestamp>:from AND s.timestamp<:to");
		query.setParameter("from", from, TemporalType.TIMESTAMP);
		query.setParameter("to", to, TemporalType.TIMESTAMP);
		return query.getResultList();
	}

	public List<Screening> findByTitleCinemaUpcoming(String title, String cinema) {
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		Query query = entityManager.createQuery("SELECT s FROM Screening s JOIN s.cinema c JOIN s.film f WHERE f.title=:title AND c.name=:cinema AND s.timestamp>:ts");
		query.setParameter("title", title);
		query.setParameter("cinema", cinema);
		query.setParameter("ts", now);
		return query.getResultList();
	}
	
	public List<Screening> findByTitleUpcoming(String title){
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		Query query = entityManager.createQuery("SELECT s FROM Screening s JOIN s.cinema c JOIN s.film f WHERE f.title=:title AND s.timestamp>:ts");
		query.setParameter("title", title);
		query.setParameter("ts", now);
		return query.getResultList();
	}
	
	public List<Screening> findByCinemaUpcoming(String cinema){
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		Query query = entityManager.createQuery("SELECT s FROM Screening s JOIN s.cinema c JOIN s.film f WHERE c.name=:cinema AND s.timestamp>:ts");
		query.setParameter("cinema", cinema);
		query.setParameter("ts", now);
		return query.getResultList();
		
	}

}
