package com.accenture.ra.criteria.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.ra.criteria.entity.User;
import com.accenture.ra.criteria.entity.User_;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class UserDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public UserDao(EntityManager em) {
		this.em = em;
	}
	
	public User findByUsername(String username) {
		// take creteriaBuilder from entityManager
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		
		// create criteriaQuery from CriteriaBuilder
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		
		// Setting createriaQuery
			// setting from object query of criteriaQuery
		Root<User> root = criteriaQuery.from(User.class);
		
			// setting query of criteruaQuery
		Path<String> path = root.get(User_.username);
		Predicate predicate = criteriaBuilder.equal(path, username);
		CriteriaQuery<User> selectQuery = criteriaQuery.where(predicate);
		
		// create query from criteriaQuery
		TypedQuery<User> typedQuery =  em.createQuery(selectQuery);
		User user =  typedQuery.getSingleResult();
	
		return user;
	}
	
//	public User findByUsername(String username) {
//		CriteriaQuery<User> criteriaQuery = em
//				.getCriteriaBuilder()
//				.createQuery( User.class );
//		
//		CriteriaQuery<User> selectQuery = criteriaQuery
//				.select( criteriaQuery.from(User.class) );
//		
//		TypedQuery<User> typedQuery =  em
//				.createQuery( selectQuery );
//		
//		User user =  typedQuery.getSingleResult();
//		return user;
//	}
	
	public List<User> searchUserBy(SearchUserFilter searchParam) {
		// 1] Take criteriaBuilder from entityManager
		CriteriaBuilder criteriaBuilder = em
			.getCriteriaBuilder();
		
		// 2] Create criteriaQuery
		CriteriaQuery<User> criteriaQuery = criteriaBuilder
			.createQuery(User.class);
		
		// 3] Create root
		Root<User> root = criteriaQuery
			.from(User.class);

		List<Predicate> predicates = new ArrayList<>();
		
		if( Objects.isNull(searchParam.firstname()) &&
			Objects.isNull(searchParam.lastname()) &&
			Objects.isNull(searchParam.email()) &&
			Objects.isNull(searchParam.username()) ) {
			TypedQuery<User> typedQuery =  em
					.createQuery( criteriaQuery );
			
			List<User> usersFetched = typedQuery.getResultList();
			return usersFetched;
		} 
		if( !Objects.isNull(searchParam.id()) ) {
			// adding predicate
			predicates.add(
				criteriaBuilder.equal(
					root.get(User_.id),
					Integer.valueOf( searchParam.id() )
				)
			);
		}
		if( !Objects.isNull(searchParam.firstname()) ) {
			// adding predicate
			predicates.add(
				criteriaBuilder.like(
					criteriaBuilder.lower( root.get(User_.firstname) ),
					"%".concat( searchParam.firstname().toLowerCase() ).concat("%")
				)
			);
		}
		if( !Objects.isNull(searchParam.lastname()) ) {
			// adding predicate
			predicates.add(
				criteriaBuilder.like(
					criteriaBuilder.lower( root.get(User_.lastname) ),
					"%".concat( searchParam.lastname().toLowerCase() ).concat("%")
				)
			);
		}
		if( !Objects.isNull(searchParam.email()) ) {
			// adding predicate
			predicates.add(
				criteriaBuilder.like(
					criteriaBuilder.lower( root.get(User_.email) ),
					"%".concat( searchParam.email().toLowerCase() ).concat("%")
				)
			);
		}
		if( !Objects.isNull(searchParam.username()) ) {
			// adding predicate
			predicates.add(
				criteriaBuilder.like(
					criteriaBuilder.lower( root.get(User_.username) ),
					"%".concat( searchParam.username().toLowerCase() ).concat("%")
				)
			);
		}
		if( !Objects.isNull(searchParam.age()) ) {
			// adding predicate
			predicates.add(
				criteriaBuilder.equal(
					root.get(User_.age),
					Integer.valueOf(searchParam.age())
				)
			);
		}
		if(  Objects.isNull(searchParam.age()) &&
		     ( !Objects.isNull(searchParam.rangeMinAge()) 
		    	&& !Objects.isNull(searchParam.rangeMaxAge()) )) {
			// adding predicate
			predicates.add(
				criteriaBuilder.between(
					root.get(User_.age),
					Integer.valueOf( searchParam.rangeMinAge() ),
					Integer.valueOf( searchParam.rangeMaxAge() )
				)
			);
		}
		
		// Convert List<Predicate> into array of Predicate
		Predicate[] predicatesArray = predicates.toArray(
			new Predicate[predicates.size()]
		);
		
		// Setting predicates to criteriaQuery already defined 
		criteriaQuery.where( predicatesArray );
		
		TypedQuery<User> typedQuery =  em
				.createQuery( criteriaQuery );
		
		List<User> usersFetched = typedQuery.getResultList();
		return usersFetched;
	}

	@Transactional
	public void save(User user) {
		this.em.persist(user);
	}
	
	@Transactional
	public void saveAll(User... users) {
		Stream.of(users)
			.forEach(this::save);
	}
}