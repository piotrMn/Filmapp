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

	public User findUserById(long userId) {
		User theUser = null;
		Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.id=:userId");
		query.setParameter("userId", userId);
		try {
			theUser = (User) query.getSingleResult();
		} catch (NoResultException exc) {
			theUser = null;
		}
		return theUser;
	}

	public User findUserByUsername(String username) {
		User theUser = null;
		Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.username=:username");
		query.setParameter("username", username);
		try {
			theUser = (User) query.getSingleResult();
		} catch (NoResultException exc) {
			theUser = null;
		}
		return theUser;
	}

	public List<User> findAllUsers() {
		Query query = entityManager.createQuery("SELECT u FROM User u");
		return query.getResultList();
	}

}
