package pl.coderslab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import pl.coderslab.entity.Reservation;

@Component
@Transactional
public class ReservationDao {

	@PersistenceContext
	EntityManager entityManager;

	public void save(Reservation entity) {
		entityManager.persist(entity);
	}

	public void update(Reservation entity) {
		entityManager.merge(entity);
	}
	
	public void delete(Reservation entity) {
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
	}
	
	public List<Reservation> findReservationByScreeningId(long screeningId){
		Query query = entityManager.createQuery("SELECT r FROM URSlink urs JOIN urs.reservation r JOIN urs.screening s WHERE s.id=:screeningId");
		query.setParameter("screeningId", screeningId);
		return query.getResultList();
	}

}
