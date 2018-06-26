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
	public List<URSlink> findByUserIdUpcoming(long id) {
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		Query query = entityManager.createQuery("SELECT urs FROM URSlink urs JOIN urs.user u JOIN urs.reservation r JOIN urs.screening s WHERE u.id=:id AND s.timestamp>:ts AND r.cancelled=FALSE");
		query.setParameter("id", id);
		query.setParameter("ts", now, TemporalType.TIMESTAMP);
		return query.getResultList();
	}
	public List<URSlink> findByUserIdPast(long id) {
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		Query query = entityManager.createQuery("SELECT urs FROM URSlink urs JOIN urs.user u JOIN urs.reservation r JOIN urs.screening s WHERE u.id=:id AND s.timestamp<:ts");
		query.setParameter("id", id);
		query.setParameter("ts", now, TemporalType.TIMESTAMP);
		return query.getResultList();
	}
	public List<URSlink> findByUserIdCancelled(long id){
		Query query = entityManager.createQuery("SELECT urs FROM URSlink urs JOIN urs.user u JOIN urs.reservation r JOIN urs.screening s WHERE u.id=:id AND r.cancelled=true");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
