package fr.iocean.application.repository;

import fr.iocean.application.persistence.IoEntity;
import org.hibernate.Session;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public abstract class AbstractJpaRepository<T extends IoEntity> {

	protected Class<T> entityClass;
	
	@PersistenceContext
	EntityManager em;

	@PostConstruct
    public void init() {
        entityClass = getEntityClass();
    }
	
	protected abstract Class<T> getEntityClass();
	
	protected Session getSession() {
		return em.unwrap(Session.class);
	}

	@Transactional
    public T save(T entity) {
        if (isNew(entity)) {
            em.persist(entity);
            return entity;
        } else if (!em.contains(entity)) {
            return em.merge(entity);
        }

        return entity;
    }

    @Transactional
    public T findOne(Long id) {
        return em.find(entityClass, id);
    }
	
    @SuppressWarnings("unchecked")
    @Transactional
    public List<T> findAll() {
        return getSession().createCriteria(entityClass).list();
    }
	
    @Transactional
    public void delete(T entity) {
        if (!getSession().contains(entity)) {
            em.remove(getSession().merge(entity));
        } else {
            em.remove(entity);
        }

    }

	public boolean isNew(T entity) {
        return entity.getId() == null;
    }
	
}
