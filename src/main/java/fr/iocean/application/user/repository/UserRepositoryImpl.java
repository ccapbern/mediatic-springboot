package fr.iocean.application.user.repository;

import fr.iocean.application.repository.AbstractJpaRepository;
import fr.iocean.application.user.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.Optional;

public class UserRepositoryImpl extends AbstractJpaRepository<User> implements UserRepositoryCustom {


    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public Optional<User> findOneByLogin(String login) {
        Criteria criteria = getSession().createCriteria(entityClass);
        criteria.add(Restrictions.eq("login", login));

        return Optional.of((User) criteria.uniqueResult());
    }
}
