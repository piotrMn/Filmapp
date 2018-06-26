package pl.coderslab.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import pl.coderslab.entity.Cinema;
import pl.coderslab.entity.URSlink;
import pl.coderslab.entity.User;

@Component
@Transactional
public class UserDao {

	@PersistenceContext
	EntityManager entityManager;

	public void save(User entity) {
		entityManager.persist(entity);
	}

	public void update(User entity) {
		entityManager.merge(entity);
	}
	
	public void delete(User entity) {
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
	}
	
	public User findById(long id) {
		User theUser;
		Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.id=:id");
		query.setParameter("id", id);
		try {
			theUser = (User) query.getSingleResult();
		} catch (NoResultException e) {
			theUser = null;
		}
		return theUser;
	}
	public User findByUsername(String username) {
		User theUser;
		Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.username=:username");
		query.setParameter("username", username);
		try {
			theUser = (User) query.getSingleResult();
		} catch (NoResultException e) {
			theUser = null;
		}
		return theUser;
	}
	public List<User> getAll(){
		Query query = entityManager.createQuery("SELECT u FROM User u");
		return query.getResultList();
	}

}
