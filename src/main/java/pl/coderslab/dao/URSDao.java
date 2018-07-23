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

import pl.coderslab.entity.URSlink;

@Component
@Transactional
public class URSDao {

	@PersistenceContext
	EntityManager entityManager;

	public void save(URSlink entity) {
		entityManager.persist(entity);
	}

	public void update(URSlink entity) {
		entityManager.merge(entity);
	}
	
	public void delete(URSlink entity) {
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
	}
	
	public URSlink findByReservationId(long id) {
		URSlink thisURSlink = null;
		Query query = entityManager.createQuery("SELECT urs FROM URSlink urs JOIN urs.user u JOIN urs.reservation r JOIN urs.screening s WHERE r.id=:id ");
		query.setParameter("id", id);
		try {
			thisURSlink = (URSlink) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		return thisURSlink;
	}
	
	public List<URSlink> findUpcomingByUserId(long userId) {
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		Query query = entityManager.createQuery("SELECT urs FROM URSlink urs JOIN urs.user u JOIN urs.reservation r JOIN urs.screening s WHERE u.id=:userId AND s.timestamp>:now AND r.cancelled=FALSE");
		query.setParameter("userId", userId);
		query.setParameter("now", now, TemporalType.TIMESTAMP);
		return query.getResultList();
	}
	
	public List<URSlink> findPastByUserId(long userId) {
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		Query query = entityManager.createQuery("SELECT urs FROM URSlink urs JOIN urs.user u JOIN urs.reservation r JOIN urs.screening s WHERE u.id=:userId AND s.timestamp<:now");
		query.setParameter("userId", userId);
		query.setParameter("now", now, TemporalType.TIMESTAMP);
		return query.getResultList();
	}
	
	public List<URSlink> findCancelledByUserId(long userId){
		Query query = entityManager.createQuery("SELECT urs FROM URSlink urs JOIN urs.user u JOIN urs.reservation r JOIN urs.screening s WHERE u.id=:userId AND r.cancelled=true");
		query.setParameter("userId", userId);
		return query.getResultList();
	}
}
